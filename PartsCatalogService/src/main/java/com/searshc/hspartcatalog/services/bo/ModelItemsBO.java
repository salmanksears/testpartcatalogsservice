package com.searshc.hspartcatalog.services.bo;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.SOLR_LAYER_EXCEPTION;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.CONTENT_TYPE;
import static com.searshc.hspartcatalog.util.CommonUtils.escapeSOLRSpecialChars;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.QUOTE;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.searshc.hs.psc.logging.annotation.ExtCallAuditLogger;
import com.searshc.hspartcatalog.exceptions.SolrException;
import com.searshc.hspartcatalog.mapper.EntityDocMapper;
import com.searshc.hspartcatalog.pojo.Doc;
import com.searshc.hspartcatalog.pojo.ModelPart;
import com.searshc.hspartcatalog.pojo.SolrResponse;
import com.searshc.hspartcatalog.services.bo.tasks.SolrConnection;
import com.searshc.hspartcatalog.services.dao.PartsCatalogDao;
import com.searshc.hspartcatalog.services.domain.Item;
import com.searshc.hspartcatalog.services.domain.Model;
import com.searshc.hspartcatalog.services.vo.request.ItemDescriptionSearchRequest;
import com.searshc.hspartcatalog.services.vo.request.ItemSearchRequest;
import com.searshc.hspartcatalog.util.ApplicationContextUtils;

public class ModelItemsBO {

	private PartsCatalogDao partsCatalogDao;
	private EntityDocMapper entityDocMapper = new EntityDocMapper();
	private SolrConnection solrConnection;

	private static final String SOLR_COLUMNS = "&fl=id,itemId,productGroupIdPls,partNo,productGroupId,productGroupName,itemDescription,itemImageURL,itemAvailabilityStatus,itemSellingPrice,itemCost,itemAddDate,itemAccessoryIndicator,productIndicator,itemCondition,engineModelFlag,formattedItemId,formattedPartNo,subbedFlag,subEngineModelFlag,itemAttributes,itemRestrictions";
	
	private static final int MAX_NUMBER_OF_ITEMS = 300;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ModelItemsBO.class);

	public ModelItemsBO() {

	}

	@ExtCallAuditLogger(logInput = false, logOutput = false)
	public List<Item> getItems(ItemDescriptionSearchRequest request, Model model, List<String> schematicIdList) throws SolrException {
		
		String brandId = model.getBrandId();
		String modelNo = model.getModelNo();
		//first 7-byte of modelId
		String productTypeId = model.getModelId().substring(0, 7);

		List<Item> itemList = new ArrayList<Item>();
		
		Item item = new Item();
	
		try {
			// load Items for Schematics from PartsCatlog DB (ItemId is KEY)
			Map<String, ModelPart> modelParts = getModelParts(schematicIdList, productTypeId, brandId, modelNo);
			if(modelParts == null || modelParts.isEmpty()) {
				logger.warn("modelNo:{} productTypeId:{} brandId:{} using schematic list:{} returned ZERO parts from Parts Catalog DB returning...", modelNo, productTypeId, brandId, StringUtils.join(schematicIdList, "|"));
				return itemList;
			}

			List<Doc> docs = queryItems(request.getClientKey(), request.getDescription(), modelParts);
			
			for (Doc doc : docs) {
				//no sub available
				if (doc.getSubItemAvailabilityStatus() == null) {
					item = entityDocMapper
							.docToItemNoSubstituteWithRestrictionAttribute(doc);
				}
				//else sub available but item is also...
				else if (doc.getSubItemAvailabilityStatus() != null
						&& doc.getItemAvailabilityStatus() != null) {
						item = entityDocMapper
								.docToItemNoSubstituteWithRestrictionAttribute(doc);
				}
				//sub available but real item is not
				else {
						item = entityDocMapper
								.docToItemSubstituteWithRestrictionAttribute(doc);
				}
				
				//add fields from DB
				ModelPart modelPart = modelParts.get(doc.getItemId());
				if(modelPart != null) {
					item.setItemSchematicId(modelPart.getDocPageId());
					item.setItemKeyId(modelPart.getItemKeyId());
					logger.trace("getItemsForModel >>> itemId : {}) FOUND setting Scematic Id : {} and Key ID : {}...", doc.getItemId(), modelPart.getDocPageId(), modelPart.getItemKeyId());
				}
				else {
					logger.warn("getItemsForModel >>> itemId : {}) was NOT found Scematic Id and Key ID Not set...", doc.getItemId()); 
				}
				
				itemList.add(item);
				
			}
		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
		return itemList;
	}

	private Map<String, ModelPart> getModelParts(List<String>schematicIdList, String productTypeId, String brandId, String modelNo) {

		// load Items for Schematics from PartsCatlog DB (ItemId is KEY)
		Map<String, ModelPart> modelParts = getPartsCatalogDao().getModelPartsByDocPage2(schematicIdList, productTypeId, brandId, modelNo);
		logger.debug("getModelParts >>>  returned {} Items(s) selected for ProductTypeId:{} BrandId:{} ModelNo:{}  Schematics:{}...",
		modelParts.size(), productTypeId, brandId, modelNo,
		StringUtils.join(schematicIdList, "|"));
	
		return modelParts;
	}

	private String getItemFilter(List<String> items, String description) throws UnsupportedEncodingException {

		StringBuilder sb = new StringBuilder("itemId:(");
		boolean first = true;
		for (String item : items) {
			if(first)
				first = false;
			else
				sb.append(",");
			sb.append("\"")
					.append(escapeSOLRSpecialChars(URLEncoder.encode(item,
							CONTENT_TYPE))).append("\"");
		}
		sb.append(")");
		sb.append("+AND+(itemDescription:").append(QUOTE).append(URLEncoder.encode(description, CONTENT_TYPE)).append(QUOTE);
		sb.append("+OR+xItemDescription:").append(QUOTE).append(URLEncoder.encode(description, CONTENT_TYPE)).append(QUOTE).append(")");
		
		String str = sb.toString();
		
		logger.debug(
				"getItemFilter >>> Item query filter created length:{} filter: {}",
				sb.length(), sb.toString());

		return str;
	}
	
	@ExtCallAuditLogger(logInput = false, logOutput = false)
	private List<Doc> queryItems(String clientKey, String description, Map<String, ModelPart> modelParts)throws Exception {
		
		StringBuilder query = new StringBuilder();
		SolrResponse solrResponse = null;
		List<Doc> docs = new ArrayList<Doc>();
		
		List<String>list = new ArrayList<String>(modelParts.keySet());
		List<List<String>> subLists = subList(list); 
			
		for (List<String> subList : subLists) {
			query.setLength(0);
			query.append(getItemFilter(subList, description));
			query.append(SOLR_COLUMNS);
			query.append("&rows=").append(subList.size()).append("&wt=json");

			logger.debug("queryItems >>> SOLR query generated, Length : {} Items : {} Query string : {}",
				query.length(), subList.size(), query.toString());
			solrResponse = getSolrConnection().invoke(clientKey, query.toString());
			Doc[] arr = solrResponse.getResponse().getDocs();
			logger.debug("queryItems >>> returned {} Docs...", arr.length);
			
			docs.addAll(Arrays.asList(arr));
			logger.debug("queryItems >>> Docs List current size {}...", docs.size());
		}
		
		logger.debug("queryItems >>> returning a List of {} Doc objects...", docs.size());
				
		return docs;
	}
	
	private List<List<String>> subList(List<String> items) { 
		
		List<List<String>> subLists = new ArrayList<List<String>>(); 
		if(items == null) { 
			logger.debug("NULL items list passed returning an EMPTY list back...");
			return subLists;
		}
		
		int cnt = (items.size() / MAX_NUMBER_OF_ITEMS) + 1;
		logger.debug("Total Items : {} Max Items per subList : {} Number of subLists to be created : {} ", items.size(), MAX_NUMBER_OF_ITEMS, cnt);
		
		if(cnt == 1) {
			logger.debug("No need to create MULTIPLE subLists only one is needed...");
			subLists.add(items);
			return subLists;
		}
		
		int startIdx = 0;
		int endIdx = MAX_NUMBER_OF_ITEMS;

		for (int i = 1; i <= cnt; i++) {
			logger.debug("Loop count: {} >>> START idx : {} END idx: {}", i, startIdx, endIdx);
			List<String> list = items.subList(startIdx, endIdx);
			subLists.add(list);
			
			if (endIdx == items.size())
				break;
	
			startIdx = endIdx;
			endIdx = Math.min((startIdx + MAX_NUMBER_OF_ITEMS), items.size());
		}
		
		logger.debug("Returning {} total number of subLists...", subLists.size());
		
		return subLists;
	}
	
	private SolrConnection getSolrConnection() {
		if (solrConnection == null) {
			ApplicationContext appCtx = ApplicationContextUtils
					.getApplicationContext();
			solrConnection = (SolrConnection) appCtx.getBean("solrConnection");
		}

		return solrConnection;
	}
	
	public PartsCatalogDao getPartsCatalogDao() {
		if (partsCatalogDao == null) {
			ApplicationContext appCtx = ApplicationContextUtils
					.getApplicationContext();
			partsCatalogDao = (PartsCatalogDao) appCtx
					.getBean("partsCatalogDao");
		}

		return partsCatalogDao;
	}
}