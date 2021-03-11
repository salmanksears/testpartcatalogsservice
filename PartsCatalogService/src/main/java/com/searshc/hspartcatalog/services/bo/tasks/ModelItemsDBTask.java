package com.searshc.hspartcatalog.services.bo.tasks;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.SOLR_LAYER_EXCEPTION;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.CONTENT_TYPE;
import static com.searshc.hspartcatalog.util.CommonUtils.escapeSOLRSpecialChars;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

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
import com.searshc.hspartcatalog.services.dao.PartsCatalogDao;
import com.searshc.hspartcatalog.services.domain.Item;
import com.searshc.hspartcatalog.services.domain.ItemAttributeCcdb;
import com.searshc.hspartcatalog.services.domain.ItemAvailabilityStatusCcdb;
import com.searshc.hspartcatalog.services.domain.ItemRestrictionCcdb;
import com.searshc.hspartcatalog.services.domain.ProductGroupCcdb;
import com.searshc.hspartcatalog.services.vo.request.GetModelDetailsRequest;
import com.searshc.hspartcatalog.util.ApplicationContextUtils;
import com.searshc.hspartcatalog.util.AttributeUtils;
import com.searshc.hspartcatalog.util.QueryUtils;

public class ModelItemsDBTask implements Callable<List<Item>> {

	private GetModelDetailsRequest request;
	private List<ProductGroupCcdb> productGroupCcdbList;
	private List<ItemAvailabilityStatusCcdb> availabilityStatusCcdbList;
	private List<ItemRestrictionCcdb> restrictionCcdbList;
	private String itemSellPriceLimit;
	private List<ItemAttributeCcdb> attributeCcdbList;
	private String brandId = null;
	private String modelNo = null;
	private String productTypeId = null;

	private List<String> schematicIdList;

	private PartsCatalogDao partsCatalogDao;
	private EntityDocMapper entityDocMapper = new EntityDocMapper();
	private SolrConnection solrConnection;

	private static final String SOLR_COLUMNS = "&fl=id,itemId,productGroupIdPls,partNo,productGroupId,productGroupName,itemDescription,itemImageURL,itemAvailabilityStatus,itemSellingPrice,itemCost,itemAddDate,itemAccessoryIndicator,productIndicator,itemCondition,engineModelFlag,formattedItemId,formattedPartNo,subbedFlag,subEngineModelFlag,itemAttributes,itemRestrictions";
	
	private static final int MAX_NUMBER_OF_ITEMS = 300;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ModelItemsDBTask.class);

	public ModelItemsDBTask() {

	}

	public ModelItemsDBTask(GetModelDetailsRequest request,
			List<ProductGroupCcdb> productGroupCcdbList,
			List<ItemAvailabilityStatusCcdb> availabilityStatusCcdbList,
			List<ItemRestrictionCcdb> restrictionCcdbList,
			String itemSellPriceLimit, 
			List<ItemAttributeCcdb> attributeCcdbList,
			String brandId,
			String modelNo,
			List<String> schematicIdList) {

		this.request = request;
		this.productGroupCcdbList = productGroupCcdbList;
		this.availabilityStatusCcdbList = availabilityStatusCcdbList;
		this.restrictionCcdbList = restrictionCcdbList;
		this.itemSellPriceLimit = itemSellPriceLimit;
		this.attributeCcdbList = attributeCcdbList;
		this.brandId = brandId;
		this.modelNo = modelNo;
		this.productTypeId = request.getModelId().substring(0, 7);
		this.schematicIdList = schematicIdList;
	}

	@Override
	public List<Item> call() throws Exception {
		return getItemsForModel();
	}
	
/*	@ExtCallAuditLogger(logInput = false, logOutput = false)
	private List<Item> getItemsForModel() throws SolrException {

		List<Item> itemList = new ArrayList<Item>();
		Item item = new Item();
		
		try {
			// load Items for Schematics from PartsCatlog DB (ItemId is KEY)
			Map<String, ModelPart> modelParts = getModelParts();
			if(modelParts == null || modelParts.isEmpty()) {
				logger.warn("Model Id:{} using schematic list:{} returned ZERO parts from Parts Catalog DB returning...", request.getModelId(), StringUtils.join(schematicIdList, "|"));
				return itemList;
			}

			List<Doc> docs = queryItems(modelParts);
			 
			List<String> availStatusList = new ArrayList<String>();

			for (ItemAvailabilityStatusCcdb availabilityStatusCcdb : availabilityStatusCcdbList)
				availStatusList.add(availabilityStatusCcdb.getItemAvailabilityStatusCd());

			for (Doc doc : docs) {
				
				if (doc.getSubItemAvailabilityStatus() == null) {
					item = entityDocMapper
							.docToItemNoSubstituteWithRestrictionAttribute(doc);
				}
				if (doc.getSubItemAvailabilityStatus() != null
						&& doc.getItemAvailabilityStatus() != null) {
					if (!availStatusList.contains(doc
							.getItemAvailabilityStatus())
							&& !availStatusList.contains(doc
									.getSubItemAvailabilityStatus())) {
						item = entityDocMapper
								.docToItemNoSubstituteWithRestrictionAttribute(doc);
					}
					if (availStatusList.contains(doc
							.getItemAvailabilityStatus())
							&& !availStatusList.contains(doc
									.getSubItemAvailabilityStatus())) {
						item = entityDocMapper
								.docToItemSubstituteWithRestrictionAttribute(doc);
					}
					if (!availStatusList.contains(doc
							.getItemAvailabilityStatus())
							&& availStatusList.contains(doc
									.getSubItemAvailabilityStatus())) {
						item = entityDocMapper
								.docToItemNoSubstituteWithRestrictionAttribute(doc);
					}
				}
				
				item = AttributeUtils.filter(item, attributeCcdbList);
				itemList.add(item);
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
			}
		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
		return itemList;
	}*/
	
	@ExtCallAuditLogger(logInput = false, logOutput = false)
	private List<Item> getItemsForModel() throws SolrException {

		List<Item> itemList = new ArrayList<Item>();
		Item item = new Item();
		Map<String, Doc> docMap = new HashMap<String, Doc>();
		
		try {
			// load Items for Schematics from PartsCatlog DB (ItemId is KEY)
			List<ModelPart> modelPartList = getModelParts();
			if(modelPartList == null || modelPartList.isEmpty()) {
				logger.warn("Model Id:{} using schematic list:{} returned ZERO parts from Parts Catalog DB returning...", request.getModelId(), StringUtils.join(schematicIdList, "|"));
				return itemList;
			}

			docMap = queryItems(modelPartList);
			 
			List<String> availStatusList = new ArrayList<String>();

			for (ItemAvailabilityStatusCcdb availabilityStatusCcdb : availabilityStatusCcdbList)
				availStatusList.add(availabilityStatusCcdb.getItemAvailabilityStatusCd());

			for (ModelPart modelPart: modelPartList) {
				
				Doc doc = docMap.get(modelPart.getItemId());
				if(null != doc){
					if (doc.getSubItemAvailabilityStatus() == null) {
						item = entityDocMapper
								.docToItemNoSubstituteWithRestrictionAttribute(doc);
					}
					if (doc.getSubItemAvailabilityStatus() != null
							&& doc.getItemAvailabilityStatus() != null) {
						if (!availStatusList.contains(doc
								.getItemAvailabilityStatus())
								&& !availStatusList.contains(doc
										.getSubItemAvailabilityStatus())) {
							item = entityDocMapper
									.docToItemNoSubstituteWithRestrictionAttribute(doc);
						}
						if (availStatusList.contains(doc
								.getItemAvailabilityStatus())
								&& !availStatusList.contains(doc
										.getSubItemAvailabilityStatus())) {
							item = entityDocMapper
									.docToItemSubstituteWithRestrictionAttribute(doc);
						}
						if (!availStatusList.contains(doc
								.getItemAvailabilityStatus())
								&& availStatusList.contains(doc
										.getSubItemAvailabilityStatus())) {
							item = entityDocMapper
									.docToItemNoSubstituteWithRestrictionAttribute(doc);
						}
					}
					
					item = AttributeUtils.filter(item, attributeCcdbList);
					itemList.add(item);
					
					//add fields from DB
					item.setItemSchematicId(modelPart.getDocPageId());
					item.setItemKeyId(modelPart.getItemKeyId());
					logger.trace("getItemsForModel >>> itemId : {}) FOUND setting Scematic Id : {} and Key ID : {}...", doc.getItemId(), modelPart.getDocPageId(), modelPart.getItemKeyId());

				}
			}
		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
		return itemList;
	}

	private List<ModelPart> getModelParts() {
		
		List<ModelPart> modelParts = new ArrayList<ModelPart>();

		// load Items for Schematics from PartsCatlog DB
		modelParts = getPartsCatalogDao().getModelPartsByDocPage(schematicIdList, productTypeId, brandId, modelNo);
		if(modelParts == null || modelParts.isEmpty()) {
			logger.debug("getModelParts >>>  returned {} Items(s) selected for ProductTypeId:{} BrandId:{} ModelNo:{}  Schematics:{}...",
			modelParts.size(), productTypeId, brandId, modelNo, StringUtils.join(schematicIdList, "|"));
		}
	
		return modelParts;
	}

	private String getItemFilter(List<String> items) throws UnsupportedEncodingException {

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

		String str = sb.toString();
		
		logger.debug(
				"getItemFilter >>> Item query filter created length:{} filter: {}",
				sb.length(), sb.toString());

		return str;
	}
	
//	Commenting out as part of fix for INC1332989
/*	@ExtCallAuditLogger(logInput = false, logOutput = false)
	private List<Doc> queryItems(Map<String, ModelPart> modelParts)throws Exception {
		
		StringBuilder query = new StringBuilder();
		SolrResponse solrResponse = null;
		List<Doc> docs = new ArrayList<Doc>();
		
		List<String>list = new ArrayList<String>(modelParts.keySet());
		List<List<String>> subLists = subList(list); 
			
		for (List<String> subList : subLists) {
			query.setLength(0);
			query.append(getItemFilter(subList));
			query.append(QueryUtils.getCommonCodeGetItem(productGroupCcdbList,
				restrictionCcdbList, itemSellPriceLimit.toString(),
				availabilityStatusCcdbList, true));
			query.append(SOLR_COLUMNS);
			query.append("&rows=").append(subList.size()).append("&wt=json");

			logger.debug("queryItems >>> SOLR query generated, Length : {} Items : {} Query string : {}",
				query.length(), subList.size(), query.toString());
			solrResponse = getSolrConnection().invoke(request.getClientKey(), query.toString());
			Doc[] arr = solrResponse.getResponse().getDocs();
			logger.debug("queryItems >>> returned {} Docs...", arr.length);
			
			docs.addAll(Arrays.asList(arr));
			logger.debug("queryItems >>> Docs List current size {}...", docs.size());
		}
		
		logger.debug("queryItems >>> returning a List of {} Doc objects...", docs.size());
				
		return docs;
	}*/
	
	@ExtCallAuditLogger(logInput = false, logOutput = false)
	private Map<String, Doc> queryItems(List<ModelPart> modelPartList)throws Exception {
		
		StringBuilder query = new StringBuilder();
		SolrResponse solrResponse = null;
		List<Doc> docs = new ArrayList<Doc>();
		Map<String, Doc> docMap = new HashMap<String, Doc>();
		
		// Create a modelPartSet from List<ModelPart> for unique ItemIds.
		// Also not modifying the subList(list) method as it could be used as a reusable method.
		Set<String> modelPartSet = new HashSet<String>();
		for(ModelPart modelPart: modelPartList){
			modelPartSet.add(modelPart.getItemId());
		}		
		
		List<String>list = new ArrayList<String>(modelPartSet);
		List<List<String>> subLists = subList(list); 
			
		for (List<String> subList : subLists) {
			query.setLength(0);
			query.append(getItemFilter(subList));
			query.append(QueryUtils.getCommonCodeGetItem(productGroupCcdbList,
				restrictionCcdbList, itemSellPriceLimit.toString(),
				availabilityStatusCcdbList, true));
			query.append(SOLR_COLUMNS);
			query.append("&rows=").append(subList.size()).append("&wt=json");

			logger.debug("queryItems >>> SOLR query generated, Length : {} Items : {} Query string : {}",
				query.length(), subList.size(), query.toString());
			solrResponse = getSolrConnection().invoke(request.getClientKey(), query.toString());
			Doc[] arr = solrResponse.getResponse().getDocs();
			logger.debug("queryItems >>> returned {} Docs...", arr.length);
			
			docs.addAll(Arrays.asList(arr));
			logger.debug("queryItems >>> Docs List current size {}...", docs.size());
			for(Doc doc: docs){
				docMap.put(doc.getItemId(), doc);
			}
		}
		
		logger.debug("queryItems >>> returning a Map of {} Doc objects...", docMap.size());
				
		return docMap;
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