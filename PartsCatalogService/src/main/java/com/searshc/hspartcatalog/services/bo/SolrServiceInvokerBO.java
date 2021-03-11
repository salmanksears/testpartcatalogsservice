package com.searshc.hspartcatalog.services.bo;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.ASC;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.DESC;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.QUOTE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.ROUND_BRAC_CLOSE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.ROUND_BRAC_OPEN;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.SPACE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.WITHIN_BRACES;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.YES;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.ZERO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.BRAND;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MODEL_NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PART_NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.DATA_NOT_FOUND;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.SOLR_LAYER_EXCEPTION;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.ALL_ROWS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.ALL_ROWS_JSON;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.ALL_SEARCH_CRITERIA;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.BRAND_ALL;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.CONTENT_TYPE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FACET_ON_FIELD_BRAND;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_BRAND;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_BRANDID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_EXCLUSION_BRANDID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_EXCLUSION_ITEM_ATTRIBUTE_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_EXCLUSION_ITEM_RESTRICTION_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_EXCLUSION_PARENT_PRODUCT_TYPE_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_EXCLUSION_PRODUCT_GROUP_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_EXCLUSION_PRODUCT_TYPE_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_EXCLUSION_RESTRICTIONS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_EXCLUSION_SUB_PRODUCT_TYPE_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_ITEM_SELL_PRICE_LIMIT;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_PARENT_PRODUCT_TYPE_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_PRODUCT_GROUP_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_PRODUCT_TYPE_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_SUB_PRODUCT_TYPE_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.JOIN_FROM_ITEM_ATTRIBUTES_TO_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.JOIN_FROM_ITEM_RESTRICTIONS_TO_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.JOIN_FROM_ITEM_SCHEMATICS_TO_MODEL_SCHEMATICS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.MAX_RESPONSE_CODE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.MIN_RESPONSE_CODE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.RESP_FORMAT_JSON;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.ROWS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.SORT;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.START;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.TO_FILTER;
import static com.searshc.hspartcatalog.util.CommonUtils.escapeSOLRSpecialChars;
import static com.searshc.hspartcatalog.util.CommonUtils.escapeSOLRSpecialCharsWildCard;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.searshc.hs.psc.logging.Level;
import com.searshc.hs.psc.logging.annotation.CheckPointLogger;
import com.searshc.hs.psc.logging.annotation.ExtCallAuditLogger;
import com.searshc.hspartcatalog.cache.BrandCache;
import com.searshc.hspartcatalog.cache.CategoryCache;
import com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants;
import com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST;
import com.searshc.hspartcatalog.delegator.SolrServiceDelegator;
import com.searshc.hspartcatalog.exceptions.SolrException;
import com.searshc.hspartcatalog.mapper.EntityDocMapper;
import com.searshc.hspartcatalog.pojo.Doc;
import com.searshc.hspartcatalog.pojo.FacetCounts;
import com.searshc.hspartcatalog.pojo.Groups;
import com.searshc.hspartcatalog.pojo.SolrResponse;
import com.searshc.hspartcatalog.services.bo.tasks.ModelItemsDBTask;
import com.searshc.hspartcatalog.services.bo.tasks.ModelSchematicsTask;
import com.searshc.hspartcatalog.services.domain.AValue;
import com.searshc.hspartcatalog.services.domain.Brand;
import com.searshc.hspartcatalog.services.domain.BrandCcdb;
import com.searshc.hspartcatalog.services.domain.Item;
import com.searshc.hspartcatalog.services.domain.ItemAttributeCcdb;
import com.searshc.hspartcatalog.services.domain.ItemAvailabilityStatusCcdb;
import com.searshc.hspartcatalog.services.domain.ItemRestrictionCcdb;
import com.searshc.hspartcatalog.services.domain.ItemWithoutSubstitutes;
import com.searshc.hspartcatalog.services.domain.Model;
import com.searshc.hspartcatalog.services.domain.ModelFacetCounts;
import com.searshc.hspartcatalog.services.domain.ParentProductType;
import com.searshc.hspartcatalog.services.domain.ProductGroup;
import com.searshc.hspartcatalog.services.domain.ProductGroupCcdb;
import com.searshc.hspartcatalog.services.domain.ProductTypeCcdb;
import com.searshc.hspartcatalog.services.domain.Schematic;
import com.searshc.hspartcatalog.services.vo.base.BaseResponse;
import com.searshc.hspartcatalog.services.vo.request.FullSearchRequest;
import com.searshc.hspartcatalog.services.vo.request.GetBrandListRequest;
import com.searshc.hspartcatalog.services.vo.request.GetBrandsRequest;
import com.searshc.hspartcatalog.services.vo.request.GetItemDetailsRequest;
import com.searshc.hspartcatalog.services.vo.request.GetItemListRequest;
import com.searshc.hspartcatalog.services.vo.request.GetModelDetailsRequest;
import com.searshc.hspartcatalog.services.vo.request.GetModelListRequest;
import com.searshc.hspartcatalog.services.vo.request.GetModelsForItemRequest;
import com.searshc.hspartcatalog.services.vo.request.GetParentProductTypeListRequest;
import com.searshc.hspartcatalog.services.vo.request.GetProductGroupListRequest;
import com.searshc.hspartcatalog.services.vo.request.GetSchematicsRequest;
import com.searshc.hspartcatalog.services.vo.request.ItemDescriptionSearchRequest;
import com.searshc.hspartcatalog.services.vo.request.ItemSearchRequest;
import com.searshc.hspartcatalog.services.vo.request.ModelSearchRequest;
import com.searshc.hspartcatalog.services.vo.response.FullSearchResponse;
import com.searshc.hspartcatalog.services.vo.response.GetBrandListResponse;
import com.searshc.hspartcatalog.services.vo.response.GetBrandsResponse;
import com.searshc.hspartcatalog.services.vo.response.GetItemDetailsResponse;
import com.searshc.hspartcatalog.services.vo.response.GetItemListResponse;
import com.searshc.hspartcatalog.services.vo.response.GetModelDetailsResponse;
import com.searshc.hspartcatalog.services.vo.response.GetModelListResponse;
import com.searshc.hspartcatalog.services.vo.response.GetModelsForItemResponse;
import com.searshc.hspartcatalog.services.vo.response.GetParentProductTypeListResponse;
import com.searshc.hspartcatalog.services.vo.response.GetProductGroupListResponse;
import com.searshc.hspartcatalog.services.vo.response.GetSchematicsResponse;
import com.searshc.hspartcatalog.services.vo.response.ItemSearchResponse;
import com.searshc.hspartcatalog.services.vo.response.ModelSearchResponse;
import com.searshc.hspartcatalog.services.vo.response.SearchResponse;
import com.searshc.hspartcatalog.util.AttributeUtils;
import com.searshc.hspartcatalog.util.CommonUtils;

public class SolrServiceInvokerBO {

	private static final Logger logger = LoggerFactory.getLogger(SolrServiceInvokerBO.class);

	@Autowired
	private EntityDocMapper entityDocMapper;

	@Autowired
	SolrServiceDelegator solrServiceDelegator;
	
	@Autowired
	BrandCache brandCache;
	
	@Autowired
	CategoryCache categoryCache;
	
	@Value("${solrEndpointUrl}")
	private String solrEndpoint;
	@Value("${thresholdTimeForCatalogAPI}")
	public String thresholdTimeCatalog;
	@Value("${thresholdTimeForCacheAPI}")
	public String thresholdTimeCache;
	@Value("${maxRowsForCatalogAPI}")
	private String maxRowsDefault;

	/**
	 * Method : itemSearch Description : This method retrieves items from SOLR
	 * BigDecimal itemSellPriceLimit
	 * 
	 * @return : BaseResponse
	 */
	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse itemSearch(ItemSearchRequest request, List<ProductGroupCcdb> productGroupList,
			List<ItemAvailabilityStatusCcdb> availabilityStatusList, List<ItemRestrictionCcdb> itemRestrictionList,
			BigDecimal itemSellPriceLimit) throws SolrException {

		SolrResponse solrResponse = null;
		ItemSearchResponse itemSearchResponse = new ItemSearchResponse();
		List<Item> itemList = new ArrayList<Item>();
		List<String> messages = new ArrayList<String>();

		solrResponse = getItems(request.getItemId(), request.getPartNo(), request.getProductGroupId(),
				request.getFormatted(), request.getStartingRow(), request.getMaxRows(), request.getSortBy(),
				productGroupList, availabilityStatusList, itemRestrictionList, itemSellPriceLimit.toString());

		if (solrResponse.getResponse().getDocs().length == 0) {
			messages.add(DATA_NOT_FOUND);
			itemSearchResponse.setNumFound(String.valueOf(ZERO));
			itemSearchResponse.setMessages(messages);
			return itemSearchResponse;
		}

		itemList = getOriOrSubsItemList(solrResponse, availabilityStatusList);
		// change for logic if status NLA set for client show part not found
		if (itemList == null || itemList.isEmpty()) {
			logger.info("NLA status set for this client");
			messages.add(DATA_NOT_FOUND);
			itemSearchResponse.setNumFound(String.valueOf(ZERO));
			itemSearchResponse.setMessages(messages);
			return itemSearchResponse;
		}
		itemSearchResponse.setItems(itemList);
		itemSearchResponse.setNumFound(solrResponse.getResponse().getNumFound());

		return itemSearchResponse;
	}

	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse itemDescriptionSearch1(ItemDescriptionSearchRequest request,
			List<ProductGroupCcdb> productGroupList, List<ItemAvailabilityStatusCcdb> availabilityStatusList,
			List<ItemRestrictionCcdb> itemRestrictionList, BigDecimal itemSellPriceLimit) throws SolrException {

		SolrResponse solrResponse = null;
		ItemSearchResponse itemSearchResponse = new ItemSearchResponse();
		Model model = new Model();
		List<String> messages = new ArrayList<String>();
		Doc[] docs = new Doc[1];

		logger.debug("Item Description Search called with values >>> modelNo:{} brand:{} productType:{} desc:{}",
				request.getModelNo(), request.getBrand(), request.getProductType(), request.getDescription());

		solrResponse = getModels(request.getModelNo(), request.getBrand(), request.getProductType());

		docs = solrResponse.getResponse().getDocs();

		if (ArrayUtils.isEmpty(docs)) {
			logger.debug("Model Lookup returned ZERO rows using values >>> modelNo:{} brand:{} productType:{}",
					request.getModelNo(), request.getBrand(), request.getProductType());
			messages.add(DATA_NOT_FOUND);
			itemSearchResponse.setNumFound(String.valueOf(ZERO));
			itemSearchResponse.setMessages(messages);
			return itemSearchResponse;
		}

		ModelItemsBO modelItemsBO = new ModelItemsBO();

		model = entityDocMapper.docToModel(docs[0]);
		List<String> schematics = docs[0].getModelSchematics();
		List<Item> itemList = modelItemsBO.getItems(request, model, schematics);

		// check if ALL items restricted...
		if (CollectionUtils.isEmpty(itemList)) {
			logger.info("NO items were found for brand:{} modelNo: {} productType: {} returning DATA_NOT_FOUND",
					model.getBrand(), model.getModelNo(), model.getProductTypeName());
			messages.add(DATA_NOT_FOUND);
			itemSearchResponse.setNumFound(String.valueOf(ZERO));
			itemSearchResponse.setMessages(messages);
			return itemSearchResponse;
		}

		itemSearchResponse.setItems(itemList);
		itemSearchResponse.setNumFound(solrResponse.getResponse().getNumFound());

		return itemSearchResponse;
	}

	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse itemDescriptionSearch2(ItemDescriptionSearchRequest request,
			List<ProductGroupCcdb> productGroupList, List<ItemAvailabilityStatusCcdb> availabilityStatusList,
			List<ItemRestrictionCcdb> itemRestrictionList, BigDecimal itemSellPriceLimit) throws SolrException {

		ItemSearchResponse itemSearchResponse = new ItemSearchResponse();
		List<String> messages = new ArrayList<String>();
		Doc[] docs = new Doc[1];
		List<Item> itemList = new ArrayList<Item>();

		SolrResponse solrResponse = null;
		StringBuilder query = new StringBuilder();

		logger.debug("Item Description Search TWO called with values >>> modelNo:{} brand:{} productType:{} desc:{}",
				request.getModelNo(), request.getBrand(), request.getProductType(), request.getDescription());

		String modelNo;
		String brand;
		String productTypeName;
		String description;

		try {
			modelNo = URLEncoder.encode(request.getModelNo(), CONTENT_TYPE);
			brand = URLEncoder.encode(request.getBrand(), CONTENT_TYPE);
			productTypeName = URLEncoder.encode(request.getProductType(), CONTENT_TYPE);
			description = URLEncoder.encode(request.getDescription(), CONTENT_TYPE);

			query.append(String.format(
					"{!join+from=modelSchematics+to=itemSchematics}modelNo:\"%s\"+AND+brand:\"%s\"+AND+productTypeName:\"%s\"&fq=itemDescription:\"%s\"+OR+xItemDescription:\"%s\"",
					modelNo, brand, productTypeName, description, description));
			query.append(START).append("0");
			query.append(ALL_ROWS);
			query.append(RESP_FORMAT_JSON);

			solrResponse = callSolr(getThresholdTimeCatalog(), query.toString());

			docs = solrResponse.getResponse().getDocs();

			if (ArrayUtils.isEmpty(docs)) {
				logger.debug(
						"Item Description Search returned ZERO rows using values >>> modelNo:{} brand:{} productType:{}, description:{}",
						modelNo, brand, productTypeName, description);
				messages.add(DATA_NOT_FOUND);
				itemSearchResponse.setNumFound(String.valueOf(ZERO));
				itemSearchResponse.setMessages(messages);
				return itemSearchResponse;
			}

			itemList = getOriOrSubsItemList(solrResponse, availabilityStatusList);
			// change for logic if status NLA set for client show part not found
			if (itemList == null || itemList.isEmpty()) {
				logger.info("NLA Status set for this client");
				messages.add(DATA_NOT_FOUND);
				itemSearchResponse.setNumFound(String.valueOf(ZERO));
				itemSearchResponse.setMessages(messages);
				return itemSearchResponse;
			}
			itemSearchResponse.setItems(itemList);
			itemSearchResponse.setNumFound(solrResponse.getResponse().getNumFound());

			return itemSearchResponse;

		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
	}

	/**
	 * Method : modelSearch Description : This method retrieves models from SOLR
	 * 
	 * @param :
	 *            ModelSearchRequest, List<BrandCcdb> brandList, List
	 *            <ProductTypeCcdb> productTypeList
	 * @return : BaseResponse
	 */
	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse modelSearch(ModelSearchRequest request, List<BrandCcdb> brandList,
			List<ProductTypeCcdb> productTypeList) throws SolrException {

		SolrResponse solrResponse = null;
		ModelSearchResponse modelSearchResponse = new ModelSearchResponse();
		List<Model> modelList = new ArrayList<Model>();
		Model model = new Model();
		List<String> messages = new ArrayList<String>();

		solrResponse = getModels(request.getModelNo(), request.getModelId(), request.getBrand(),
				request.getParentProductTypeId(), request.getProductTypeId(), request.getSubProductTypeId(),
				request.getFormatted(), request.getStartingRow(), request.getMaxRows(), request.getSortBy(), brandList,
				productTypeList);

		if (solrResponse.getResponse().getDocs().length == 0) {
			messages.add(DATA_NOT_FOUND);
			modelSearchResponse.setNumFound(String.valueOf(ZERO));
			modelSearchResponse.setMessages(messages);
			return modelSearchResponse;
		}
		for (Doc doc : solrResponse.getResponse().getDocs()) {
			model = entityDocMapper.docToModel(doc);
			modelList.add(model);
		}
		modelSearchResponse.setModels(modelList);
		modelSearchResponse.setNumFound(solrResponse.getResponse().getNumFound());

		return modelSearchResponse;
	}

	public BaseResponse modelSearchFuzzy(ModelSearchRequest request, List<BrandCcdb> brandList,
			List<ProductTypeCcdb> productTypeList) throws SolrException {

		request.setFuzzySearch(true);
		return modelSearchEnhanced(request, brandList, productTypeList);
	}

	/**
	 * Method : fullSearch Description : This method retrieves models or items
	 * from SOLR based on key. Key can either be modelNo or partNo
	 * 
	 * @param :
	 *            FullSearchRequest, List<BrandCcdb>, List<ProductTypeCcdb>,
	 *            List<ProductGroupCcdb>, List<ItemAvailabilityStatusCcdb>, List
	 *            <ItemRestrictionCcdb>, BigDecimal itemSellPriceLimit
	 * @return : BaseResponse
	 */
	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse fullSearch(FullSearchRequest request, List<BrandCcdb> brandList,
			List<ProductTypeCcdb> productTypeList, List<ProductGroupCcdb> productGroupList,
			List<ItemAvailabilityStatusCcdb> availabilityStatusList, List<ItemRestrictionCcdb> itemRestrictionList,
			BigDecimal itemSellPriceLimit) throws SolrException {

		FullSearchResponse fullSearchResponse = new FullSearchResponse();
		List<Item> itemList = new ArrayList<Item>();
		List<Model> modelList = new ArrayList<Model>();
		SolrResponse solrResponse = new SolrResponse();
		Model model = new Model();
		List<String> messages = new ArrayList<String>();
		int maxRowsDef = Integer.parseInt(maxRowsDefault);

		solrResponse = getItems(null, request.getKey(), null, request.getFormatted(), String.valueOf(1),
				String.valueOf(0), null, productGroupList, availabilityStatusList, itemRestrictionList,
				itemSellPriceLimit.toString());

		int numFoundItems = Integer.parseInt(solrResponse.getResponse().getNumFound());

		solrResponse = getModels(request.getKey(), null, null, null, null, null, request.getFormatted(),
				request.getStartingRow(), request.getMaxRows(), null, brandList, productTypeList);

		int numFoundModel = Integer.parseInt(solrResponse.getResponse().getNumFound());
		int numFoundRecords = numFoundItems + numFoundModel;
		int count = solrResponse.getResponse().getDocs().length;

		if (numFoundRecords == 0) {
			messages.add(DATA_NOT_FOUND);
			fullSearchResponse.setNumFound(String.valueOf(ZERO));
			fullSearchResponse.setMessages(messages);
			return fullSearchResponse;
		}
		if (numFoundModel == 0) {
			solrResponse = getItems(null, request.getKey(), null, request.getFormatted(), request.getStartingRow(),
					request.getMaxRows(), null, productGroupList, availabilityStatusList, itemRestrictionList,
					itemSellPriceLimit.toString());

			numFoundRecords = Integer.parseInt(solrResponse.getResponse().getNumFound());
		} else {
			for (Doc doc : solrResponse.getResponse().getDocs()) {
				if (doc.getModelNo() != null) {
					model = entityDocMapper.docToModel(doc);
					modelList.add(model);
				}
			}
			int reminder = numFoundRecords % maxRowsDef;
			int remainingItems = Integer.parseInt(request.getStartingRow()) - numFoundRecords + maxRowsDef;
			if (remainingItems <= maxRowsDef) {
				if ((count != maxRowsDef && count != 0) && (reminder > 0 && reminder < maxRowsDef)) {
					solrResponse = getItems(null, request.getKey(), null, request.getFormatted(), String.valueOf(1),
							String.valueOf(Integer.parseInt(request.getMaxRows()) - count), null, productGroupList,
							availabilityStatusList, itemRestrictionList, itemSellPriceLimit.toString());
				} else {
					if ((count == 0) && (reminder > 0 && reminder < maxRowsDef)) {
						solrResponse = getItems(null, request.getKey(), null, request.getFormatted(),
								String.valueOf(Integer.parseInt(request.getStartingRow()) - numFoundModel),
								request.getMaxRows(), null, productGroupList, availabilityStatusList,
								itemRestrictionList, itemSellPriceLimit.toString());
					}
				}
			}
		}

		itemList = getOriOrSubsItemList(solrResponse, availabilityStatusList);

		// change for logic if status NLA set for client show part not found
		if (itemList == null || itemList.isEmpty()) {
			logger.info("NLA status set for this client");
			messages.add(DATA_NOT_FOUND);
			fullSearchResponse.setNumFound(String.valueOf(ZERO));
			fullSearchResponse.setMessages(messages);
			return fullSearchResponse;
		}
		// solr sorting is not working properly only for this method, so adding
		// logic

		StringTokenizer tokenizer = new StringTokenizer(request.getSortBy(), ",");
		List<String> sortByList = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			sortByList.add(tokenizer.nextToken());
		}

		for (String sorting : sortByList) {
			StringTokenizer tokenizer1 = new StringTokenizer(sorting, SPACE);
			while (tokenizer1.hasMoreTokens()) {
				String firstToken = tokenizer1.nextToken();
				String secondToken = tokenizer1.nextToken();
				if (firstToken.contains(MODEL_NO) && !modelList.isEmpty()) {
					Collections.sort(modelList);
					if (secondToken.contains(DESC)) {
						Collections.reverse(modelList);
					}
				}
				if (firstToken.contains(PART_NO) && !itemList.isEmpty()) {
					Collections.sort(itemList);
					if (secondToken.contains(DESC))
						Collections.reverse(itemList);
				}
			}
		}

		fullSearchResponse.setItems(itemList);
		fullSearchResponse.setModels(modelList);
		fullSearchResponse.setNumFound(String.valueOf(numFoundRecords));

		return fullSearchResponse;
	}

	/**
	 * Method : getBrands Description : This method retrieves brands from SOLR.
	 * 
	 * @param :
	 *            GetBrandsRequest, List<BrandCcdb>
	 * @return : BaseResponse
	 */
	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse getBrands(GetBrandsRequest request, List<BrandCcdb> brandList) throws SolrException {

		SolrResponse solrResponse = null;
		GetBrandsResponse getBrandsResponse = new GetBrandsResponse();
		List<Brand> brands = new ArrayList<Brand>();
		int i = 0;
		List<String> messages = new ArrayList<String>();
		Brand brand = null;

		solrResponse = getBrands(request.getClientKey(), request.getBrand(), request.getSortBy(), brandList);

		String[] facetBrands = solrResponse.getFacetCounts().getFacetFields().getBrand();

		if (facetBrands.length == 0) {
			messages.add(DATA_NOT_FOUND);
			getBrandsResponse.setNumFound(String.valueOf(ZERO));
			getBrandsResponse.setMessages(messages);
			return getBrandsResponse;
		}
		for (i = 0; i < facetBrands.length; i++) {
			brand = new Brand();
			brand.setBrandName(facetBrands[i]);
			brand.setModelCount(facetBrands[++i]);
			brands.add(brand);
		}
		if (request.getSortBy().contains(BRAND)) {
			Collections.sort(brands, new Comparator<Brand>() {
				public int compare(Brand brand1, Brand brand2) {
					return brand1.getBrandName().compareTo(brand2.getBrandName());
				}
			});

			if (request.getSortBy().contains(DESC))
				Collections.reverse(brands);
		} else {
			if (request.getSortBy().contains(ASC))
				Collections.reverse(brands);
		}
		getBrandsResponse.setBrands(brands);
		getBrandsResponse.setNumFound(String.valueOf(facetBrands.length / 2));

		return getBrandsResponse;
	}

	/**
	 * Method : getSchematics Description : This method retrieves schematics
	 * from SOLR for model and items/parts.
	 * 
	 * @param :
	 *            GetSchematicsRequest, List<ProductGroupCcdb>, List
	 *            <ItemAvailabilityStatusCcdb>, List<ItemRestrictionCcdb>, List
	 *            <BrandCcdb>, List<ProductTypeCcdb>, BigDecimal
	 *            itemSellPriceLimit
	 * @return : BaseResponse
	 */
	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse getSchematics(GetSchematicsRequest request, List<ProductGroupCcdb> productGroupList,
			List<ItemAvailabilityStatusCcdb> availabilityStatusList, List<ItemRestrictionCcdb> itemRestrictionList,
			List<BrandCcdb> brandList, List<ProductTypeCcdb> productTypeList, BigDecimal itemSellPriceLimit)
			throws SolrException {

		GetSchematicsResponse getSchematicsResponse = new GetSchematicsResponse();
		List<Schematic> schematicList = new ArrayList<Schematic>();
		SolrResponse solrResponse = new SolrResponse();
		Schematic schematic = new Schematic();
		List<String> messages = new ArrayList<String>();
		int numFound = 0;

		// first check if the model is restricted to the client, if yes, return
		// numFound 0
		if (request.getModelId() != null || request.getModelNo() != null) {
			solrResponse = getModels(request.getModelNo(), request.getModelId(), null, null, null, null, null, null,
					null, null, brandList, productTypeList);
			Doc[] docs = solrResponse.getResponse().getDocs();

			if (docs.length == 0) {
				messages.add(DATA_NOT_FOUND);
				getSchematicsResponse.setNumFound(String.valueOf(ZERO));
				getSchematicsResponse.setMessages(messages);
				return getSchematicsResponse;
			}
		}

		solrResponse = getSchematicList(request.getModelNo(), request.getModelId(), request.getSortBy());

		if (solrResponse.getResponse().getDocs().length == 0) {
			messages.clear();
			messages.add(DATA_NOT_FOUND);
			getSchematicsResponse.setNumFound(String.valueOf(ZERO));
			getSchematicsResponse.setMessages(messages);
		}
		for (Doc doc : solrResponse.getResponse().getDocs()) {
			schematic = entityDocMapper.docToSchematic(doc);
			schematicList.add(schematic);
		}
		getSchematicsResponse.setSchematics(schematicList);
		getSchematicsResponse.setNumFound(solrResponse.getResponse().getNumFound());

		return getSchematicsResponse;
	}

	/**
	 * Method : getModelDetails Description : This method retrieves schematics
	 * from SOLR for model and items/parts.
	 * 
	 * @param :
	 *            GetModelDetailsRequest , List<BrandCcdb> , List
	 *            <ProductTypeCcdb> , List<ProductGroupCcdb> , List
	 *            <ItemAvailabilityStatusCcdb> , List<ItemAttributeCcdb>, List
	 *            <ItemRestrictionCcdb> , BigDecimal itemSellPriceLimit
	 * @return : BaseResponse
	 */
	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse getModelDetails(GetModelDetailsRequest request, List<BrandCcdb> brandList,
			List<ProductTypeCcdb> productTypeList, List<ProductGroupCcdb> productGroupList,
			List<ItemAvailabilityStatusCcdb> availabilityStatusList, List<ItemAttributeCcdb> attributeList,
			List<ItemRestrictionCcdb> restrictionList, BigDecimal itemSellPriceLimit) throws SolrException {

		SolrResponse solrResponse = null;
		GetModelDetailsResponse getModelDetailsResponse = new GetModelDetailsResponse();
		List<Schematic> schematicList = new ArrayList<Schematic>();
		Schematic schematic = new Schematic();
		Model model = new Model();
		Doc[] docs = new Doc[1];
		String numFound = null;
		List<String> messages = new ArrayList<String>();
		int count = 0;

		solrResponse = getModels(null, request.getModelId(), null, null, null, null, null, null, "1", null, brandList,
				productTypeList);

		docs = solrResponse.getResponse().getDocs();
		numFound = solrResponse.getResponse().getNumFound();

		if (docs.length - count == 0) {
			messages.add(DATA_NOT_FOUND);
			getModelDetailsResponse.setNumFound(String.valueOf(ZERO));
			getModelDetailsResponse.setMessages(messages);
			return getModelDetailsResponse;
		}

		model = entityDocMapper.docToModel(docs[0]);
		List<String> schematicIdList = docs[0].getModelSchematics();

		// Model found next stuff....
		ModelItemsDBTask itemTask = new ModelItemsDBTask(request, productGroupList, availabilityStatusList,
				restrictionList, String.valueOf(itemSellPriceLimit), attributeList, model.getBrandId(),
				model.getModelNo(), schematicIdList);

		ModelSchematicsTask schematicTask = new ModelSchematicsTask(request.getClientKey(), request.getModelId(),
				schematicIdList.size());

		FutureTask<List<Item>> itemFutureTask = new FutureTask<List<Item>>(itemTask);
		FutureTask<SolrResponse> schematicFutureTask = new FutureTask<SolrResponse>(schematicTask);

		List<Item> itemList = new ArrayList<Item>();

		ExecutorService executor = Executors.newFixedThreadPool(2);
		try {
			executor.execute(itemFutureTask);
			executor.execute(schematicFutureTask);

			solrResponse = schematicFutureTask.get();
			logger.debug("The Schematics Future Task is now DONE and the output has been captured...");

			itemList = itemFutureTask.get();
			logger.debug("The Item Future task is now DONE and the output has been captured...");

		} catch (Exception e) {
			logger.error("Exception caught while waiting for Future Tasks to complete >>> {}", e.getMessage());
			e.printStackTrace();
			throw new SolrException(e.getMessage());
		}

		executor.shutdown();
		logger.trace("The Executor has been SHUTDOWN...");

		for (Doc doc : solrResponse.getResponse().getDocs()) {
			schematic = entityDocMapper.docToSchematic(doc);
			schematicList.add(schematic);
		}

		// check if ALL items restricted...
		if (itemList == null || itemList.size() == 0) {
			logger.info("All items were restricted for modelId:{} returning DATA_NOT_FOUND", request.getModelId());
			messages.add(DATA_NOT_FOUND);
			getModelDetailsResponse.setNumFound(String.valueOf(ZERO));
			getModelDetailsResponse.setMessages(messages);
		} else {
			getModelDetailsResponse.setModel(model);
			getModelDetailsResponse.setItems(itemList);
			getModelDetailsResponse.setModelSchematics(schematicList);
			getModelDetailsResponse.setNumFound(numFound);
		}

		return getModelDetailsResponse;
	}

	/**
	 * Method : getModelsForItem Description : This method retrieves models for
	 * a particular item from SOLR.
	 * 
	 * @param :
	 *            GetModelsForItemRequest , List<BrandCcdb> , List
	 *            <ProductTypeCcdb> , List<ProductGroupCcdb> , List
	 *            <ItemAvailabilityStatusCcdb> , List<ItemAttributeCcdb>, List
	 *            <ItemRestrictionCcdb> , BigDecimal itemSellPriceLimit
	 * @return : BaseResponse
	 */
	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse getModelsForItem(GetModelsForItemRequest request, List<BrandCcdb> brandList,
			List<ProductTypeCcdb> productTypeList, List<ProductGroupCcdb> productGroupList,
			List<ItemAvailabilityStatusCcdb> availabilityStatusList, List<ItemRestrictionCcdb> itemRestrictionList,
			BigDecimal itemSellPriceLimit) throws SolrException {

		SolrResponse solrResponse = null;
		GetModelsForItemResponse getModelsForItemResponse = new GetModelsForItemResponse();
		List<Model> modelList = new ArrayList<Model>();
		StringBuilder query = new StringBuilder();
		Model model = new Model();
		List<String> messages = new ArrayList<String>();
		int numFound = 0;
		StringBuilder data = new StringBuilder();
		try {

			// first check if the item/part is restricted to the client, if yes,
			// return numFound 0
			solrResponse = getItems(request.getItemId(), null, null, null, null, null, null, productGroupList,
					availabilityStatusList, itemRestrictionList, String.valueOf(itemSellPriceLimit));

			List<String> availStatusList = new ArrayList<String>();

			for (ItemAvailabilityStatusCcdb list : availabilityStatusList)
				availStatusList.add(list.getItemAvailabilityStatusCd());

			for (Doc doc : solrResponse.getResponse().getDocs()) {
				if (doc.getSubItemAvailabilityStatus() == null)
					numFound++;
				if (doc.getSubItemAvailabilityStatus() != null && doc.getItemAvailabilityStatus() != null) {
					if (!availStatusList.contains(doc.getItemAvailabilityStatus())
							&& !availStatusList.contains(doc.getSubItemAvailabilityStatus()))
						numFound++;
					if (availStatusList.contains(doc.getItemAvailabilityStatus())
							&& !availStatusList.contains(doc.getSubItemAvailabilityStatus()))
						numFound++;
					if (!availStatusList.contains(doc.getItemAvailabilityStatus())
							&& availStatusList.contains(doc.getSubItemAvailabilityStatus()))
						numFound++;
				}
			}

			if (numFound == 0) {
				messages.add(DATA_NOT_FOUND);
				getModelsForItemResponse.setNumFound(String.valueOf(ZERO));
				getModelsForItemResponse.setMessages(messages);
				return getModelsForItemResponse;
			}

			query.append(JOIN_FROM_ITEM_SCHEMATICS_TO_MODEL_SCHEMATICS).append(URLEncoder
					.encode(data.append(QUOTE).append(request.getItemId()).append(QUOTE).toString(), CONTENT_TYPE));

			query.append(getCommonCodeGetModel(brandList, productTypeList));

			/*
			 * as no maxRows is set for the models here, and solr returns only
			 * 10 records by default, setting maximum value of solr rows prop to
			 * get all the records
			 */

			query.append(ALL_ROWS);
			query.append(RESP_FORMAT_JSON);

			solrResponse = callSolr(getThresholdTimeCatalog(), query.toString());

			if (solrResponse.getResponse().getDocs().length == 0) {
				messages.clear();
				messages.add(DATA_NOT_FOUND);
				getModelsForItemResponse.setNumFound(String.valueOf(ZERO));
				getModelsForItemResponse.setMessages(messages);
				return getModelsForItemResponse;
			}

			for (Doc doc : solrResponse.getResponse().getDocs()) {
				model = entityDocMapper.docToModel(doc);
				modelList.add(model);
			}

			getModelsForItemResponse.setModels(modelList);
			getModelsForItemResponse.setNumFound(solrResponse.getResponse().getNumFound());

		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
		return getModelsForItemResponse;
	}

	/**
	 * Method : getItemDetails Description : This method retrieves models for a
	 * particular item from SOLR.
	 * 
	 * @param :
	 *            GetItemDetailsRequest, List<ProductGroupCcdb> , List
	 *            <ItemAvailabilityStatusCcdb> , List<ItemAttributeCcdb> , List
	 *            <ItemRestrictionCcdb> , BigDecimal itemSellPriceLimit
	 * @return : BaseResponse
	 */
	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse getItemDetails(GetItemDetailsRequest request, List<ProductGroupCcdb> productGroupList,
			List<ItemAvailabilityStatusCcdb> availabilityStatusList, List<ItemAttributeCcdb> attributeList,
			List<ItemRestrictionCcdb> restrictionList, BigDecimal itemSellPriceLimit) throws SolrException {

		SolrResponse solrResponse = null;
		GetItemDetailsResponse getItemDetailsResponse = new GetItemDetailsResponse();
		List<Schematic> schematicList = new ArrayList<Schematic>();
		Item item = new Item();
		Schematic schematic = new Schematic();
		Doc[] doc = new Doc[1];
		String numFound = null;
		List<String> messages = new ArrayList<String>();

		solrResponse = getItems(request.getItemId(), null, null, null, null, null, null, productGroupList,
				availabilityStatusList, restrictionList, String.valueOf(itemSellPriceLimit));

		doc = solrResponse.getResponse().getDocs();

		if (doc.length == 0) {
			messages.add(DATA_NOT_FOUND);
			getItemDetailsResponse.setNumFound(String.valueOf(ZERO));
			getItemDetailsResponse.setMessages(messages);
			return getItemDetailsResponse;
		}

		numFound = solrResponse.getResponse().getNumFound();
		List<String> availStatusList = new ArrayList<String>();

		for (ItemAvailabilityStatusCcdb list : availabilityStatusList)
			availStatusList.add(list.getItemAvailabilityStatusCd());

		if (doc[0].getSubItemAvailabilityStatus() == null)
			item = entityDocMapper.docToItemNoSubstituteWithRestrictionAttribute(doc[0]);
		if (doc[0].getSubItemAvailabilityStatus() != null && doc[0].getItemAvailabilityStatus() != null) {
			if (!availStatusList.contains(doc[0].getItemAvailabilityStatus())
					&& !availStatusList.contains(doc[0].getSubItemAvailabilityStatus()))
				item = entityDocMapper.docToItemNoSubstituteWithRestrictionAttribute(doc[0]);
			if (availStatusList.contains(doc[0].getItemAvailabilityStatus())
					&& !availStatusList.contains(doc[0].getSubItemAvailabilityStatus()))
				item = entityDocMapper.docToItemSubstituteWithRestrictionAttribute(doc[0]);
			if (!availStatusList.contains(doc[0].getItemAvailabilityStatus())
					&& availStatusList.contains(doc[0].getSubItemAvailabilityStatus()))
				item = entityDocMapper.docToItemNoSubstituteWithRestrictionAttribute(doc[0]);
		}

		// for(Doc schematicdoc : solrResponse.getResponse().getDocs()){
		// solrResponse = getSchematicList(null, null, null,
		// request.getItemId(), null);
		// schematic = entityDocMapper.docToSchematic(schematicdoc);
		// schematicList.add(schematic);
		// }

		item = AttributeUtils.filter(item, attributeList);

		getItemDetailsResponse.setItem(item);
		// getItemDetailsResponse.setItemSchematics(schematicList);
		getItemDetailsResponse.setNumFound(numFound);

		return getItemDetailsResponse;
	}

	/**
	 * Method : getItemList Description : This method retrieves large result set
	 * of items from SOLR.
	 * 
	 * @param :
	 *            GetItemListRequest, List<ProductGroupCcdb> , List
	 *            <ItemAvailabilityStatusCcdb> , List<ItemAttributeCcdb> , List
	 *            <ItemRestrictionCcdb> , BigDecimal itemSellPriceLimit
	 * @return : BaseResponse
	 */
	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse getItemList(GetItemListRequest request, List<ProductGroupCcdb> productGroupList,
			List<ItemAvailabilityStatusCcdb> availabilityStatusList, List<ItemRestrictionCcdb> restrictions,
			BigDecimal itemSellPriceLimit) throws SolrException {

		GetItemListResponse getItemListResponse = new GetItemListResponse();
		List<ItemWithoutSubstitutes> itemList = new ArrayList<ItemWithoutSubstitutes>();
		SolrResponse solrResponse = null;
		StringBuilder query = new StringBuilder();
		ItemWithoutSubstitutes item = new ItemWithoutSubstitutes();
		List<String> messages = new ArrayList<String>();
		StringBuilder data = new StringBuilder();
		try {

			if (request.getProductGroupId() != null)
				query.append(SOLR_CONST.PRODUCT_GROUP_ID).append(URLEncoder.encode(
						data.append(QUOTE).append(request.getProductGroupId()).append(QUOTE).toString(), CONTENT_TYPE));

			query.append(getCommonCodeGetItem(productGroupList, restrictions, itemSellPriceLimit.toString(),
					availabilityStatusList, false));

			query.append(START).append(URLEncoder
					.encode(String.valueOf((Integer.parseInt(request.getStartingRow().trim()) - 1)), CONTENT_TYPE));
			query.append(ROWS).append(URLEncoder.encode(request.getMaxRows(), CONTENT_TYPE));
			query.append(RESP_FORMAT_JSON);

			solrResponse = callSolr(getThresholdTimeCache(), query.toString());

			if (solrResponse.getResponse().getDocs().length == 0) {
				messages.add(DATA_NOT_FOUND);
				getItemListResponse.setNumFound(String.valueOf(ZERO));
				getItemListResponse.setMessages(messages);
				return getItemListResponse;
			}

			for (Doc doc : solrResponse.getResponse().getDocs()) {
				item = entityDocMapper.docToItemWithoutSubstitutes(doc);
				itemList.add(item);
			}

			getItemListResponse.setItems(itemList);
			getItemListResponse.setNumFound(solrResponse.getResponse().getNumFound());

		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
		return getItemListResponse;
	}

	/**
	 * Method : getModelList Description : This method retrieves large result
	 * set of models from SOLR.
	 * 
	 * @param :
	 *            GetModelListRequest, List<BrandCcdb> , List<ProductTypeCcdb>
	 * @return : BaseResponse
	 */
	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse getModelList(GetModelListRequest request, List<BrandCcdb> brandList,
			List<ProductTypeCcdb> productTypeList) throws SolrException {
		GetModelListResponse getModelListResponse = new GetModelListResponse();
		List<Model> modelList = new ArrayList<Model>();
		SolrResponse solrResponse = null;
		StringBuilder query = new StringBuilder();
		Model model = new Model();
		List<String> messages = new ArrayList<String>();
		StringBuilder data = new StringBuilder();
		try {

			if (request.getParentProductTypeId() != null)
				query.append(SOLR_CONST.PARENT_PRODUCT_TYPE_ID)
						.append(URLEncoder.encode(
								data.append(QUOTE).append(request.getParentProductTypeId()).append(QUOTE).toString(),
								CONTENT_TYPE));
			data.setLength(0);

			if (request.getBrandId() != null)
				query.append(FILTER_BRANDID).append(URLEncoder.encode(
						data.append(QUOTE).append(request.getBrandId()).append(QUOTE).toString(), CONTENT_TYPE));

			query.append(getCommonCodeGetModel(brandList, productTypeList));

			query.append(START).append(URLEncoder
					.encode(String.valueOf((Integer.parseInt(request.getStartingRow().trim()) - 1)), CONTENT_TYPE));
			query.append(ROWS).append(URLEncoder.encode(request.getMaxRows(), CONTENT_TYPE));
			query.append(RESP_FORMAT_JSON);

			solrResponse = callSolr(getThresholdTimeCache(), query.toString());

			if (solrResponse.getResponse().getDocs().length == 0) {
				messages.add(DATA_NOT_FOUND);
				getModelListResponse.setNumFound(String.valueOf(ZERO));
				getModelListResponse.setMessages(messages);
				return getModelListResponse;
			}

			for (Doc doc : solrResponse.getResponse().getDocs()) {
				model = entityDocMapper.docToModel(doc);
				modelList.add(model);
			}

			getModelListResponse.setModels(modelList);
			getModelListResponse.setNumFound(solrResponse.getResponse().getNumFound());

		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
		return getModelListResponse;
	}

	/**
	 * Method : getBrandList Description : This method retrieves large result
	 * set of brands from SOLR.
	 * 
	 * @param :
	 *            GetBrandListRequest, List<BrandCcdb>
	 * @return : BaseResponse
	 */
	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse getBrandList(GetBrandListRequest request, List<BrandCcdb> brandList) throws SolrException {
		SolrResponse solrResponse = null;
		GetBrandListResponse getBrandListResponse = new GetBrandListResponse();
		StringBuilder query = new StringBuilder();
		List<Brand> brands = new ArrayList<Brand>();
		Brand brand = null;
		List<Doc> docs = null;
		List<Groups> groups = null;
		String modelCount = null;
		List<String> messages = new ArrayList<String>();
		StringBuilder data = new StringBuilder();
		try {
			query.append(BRAND_ALL);

			query.append(FILTER_PARENT_PRODUCT_TYPE_ID)
					.append(URLEncoder.encode(
							data.append(QUOTE).append(request.getParentProductTypeId()).append(QUOTE).toString(),
							CONTENT_TYPE));

			query.append(SOLR_CONST.GROUP_BY_BRAND);

			query.append(getCommonCodeGetModel(brandList, null));

			query.append(START).append(URLEncoder
					.encode(String.valueOf((Integer.parseInt(request.getStartingRow().trim()) - 1)), CONTENT_TYPE));
			query.append(ROWS).append(URLEncoder.encode(request.getMaxRows(), CONTENT_TYPE));
			query.append(RESP_FORMAT_JSON);

			solrResponse = callSolr(getThresholdTimeCache(), query.toString());

			groups = solrResponse.getGrouped().getBrand().getGroups();
			String numFound = String.valueOf(groups.size());

			if (groups == null || groups.isEmpty()) {
				messages.add(DATA_NOT_FOUND);
				getBrandListResponse.setNumFound(String.valueOf(ZERO));
				getBrandListResponse.setMessages(messages);
				return getBrandListResponse;
			}

			for (Groups group : groups) {
				docs = group.getDocList().getDocs();
				modelCount = group.getDocList().getModelCount();
				brand = new Brand();
				brand.setBrandId(docs.get(0).getBrandId());
				brand.setBrandName(group.getBrandName());
				brand.setModelCount(modelCount);
				brands.add(brand);
			}

			getBrandListResponse.setBrands(brands);
			getBrandListResponse.setNumFound(numFound);

		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
		return getBrandListResponse;
	}

	/**
	 * Method : getParentProductTypeList Description : This method retrieves
	 * large result set of ParentProductType from SOLR.
	 * 
	 * @param :
	 *            GetParentProductTypeListRequest , List<BrandCcdb> brandList,
	 *            List<ProductTypeCcdb>
	 * @return : BaseResponse
	 */
	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse getParentProductTypeList(GetParentProductTypeListRequest request, List<BrandCcdb> brandList,
			List<ProductTypeCcdb> productTypeList) throws SolrException {

		SolrResponse solrResponse = null;
		GetParentProductTypeListResponse response = new GetParentProductTypeListResponse();
		StringBuilder query = new StringBuilder();
		List<ParentProductType> parentProductTypes = new ArrayList<ParentProductType>();
		ParentProductType parentProductType = null;
		List<Doc> docs = null;
		String count = null;
		List<String> messages = new ArrayList<String>();
		StringBuilder data = new StringBuilder();

		try {

			query.append(SOLR_CONST.MODEL_NO_ALL);

			if (request.getBrandId() != null)
				query.append(FILTER_BRANDID).append(URLEncoder.encode(
						data.append(QUOTE).append(request.getBrandId()).append(QUOTE).toString(), CONTENT_TYPE));

			query.append(SOLR_CONST.GROUP_BY_PARENT_PRODUCT_TYPE_ID);

			query.append(getCommonCodeGetModel(brandList, productTypeList));

			query.append(START).append(URLEncoder
					.encode(String.valueOf((Integer.parseInt(request.getStartingRow().trim()) - 1)), CONTENT_TYPE));
			query.append(ROWS).append(URLEncoder.encode(request.getMaxRows(), CONTENT_TYPE));
			query.append(RESP_FORMAT_JSON);

			solrResponse = callSolr(getThresholdTimeCache(), query.toString());

			if (solrResponse.getGrouped().getParentProductTypeName().getGroups().size() == 0) {
				messages.add(DATA_NOT_FOUND);
				response.setNumFound(String.valueOf(ZERO));
				response.setMessages(messages);
				return response;
			}

			List<Groups> groups = solrResponse.getGrouped().getParentProductTypeName().getGroups();
			String numFound = String.valueOf(groups.size());

			for (Groups group : groups) {
				docs = group.getDocList().getDocs();
				count = group.getDocList().getModelCount();
				parentProductType = new ParentProductType();
				parentProductType.setParentProductTypeId(docs.get(0).getParentProductTypeId());
				parentProductType.setParentProductTypeName(docs.get(0).getParentProductTypeName());
				parentProductType.setModelCount(count);
				parentProductTypes.add(parentProductType);
			}

			response.setParentProductTypes(parentProductTypes);
			response.setNumFound(numFound);

		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
		return response;
	}

	/**
	 * Method : getProductGroupList Description : This method retrieves large
	 * result set of ProductGroup from SOLR.
	 * 
	 * @param :
	 *            GetProductGroupListRequest , List<ProductGroupCcdb> , List
	 *            <ItemAvailabilityStatusCcdb> , List<ItemRestrictionCcdb> ,
	 *            BigDecimal itemSellPriceLimit
	 * @return : BaseResponse
	 */
	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse getProductGroupList(GetProductGroupListRequest request, List<ProductGroupCcdb> productGroupList,
			List<ItemAvailabilityStatusCcdb> availabilityStatusList, List<ItemRestrictionCcdb> itemRestrictionList,
			BigDecimal itemSellPriceLimit) throws SolrException {

		SolrResponse solrResponse = null;
		GetProductGroupListResponse response = new GetProductGroupListResponse();
		StringBuilder query = new StringBuilder();
		List<ProductGroup> productGroups = new ArrayList<ProductGroup>();
		ProductGroup productGroup = null;
		List<Doc> docs = null;
		String count = null;
		List<String> messages = new ArrayList<String>();

		try {
			query.append(SOLR_CONST.PART_ALL_GROUP_BY_PRODUCT_GROUP_ID);

			query.append(getCommonCodeGetItem(productGroupList, itemRestrictionList, itemSellPriceLimit.toString(),
					availabilityStatusList, false));

			query.append(START).append(URLEncoder
					.encode(String.valueOf((Integer.parseInt(request.getStartingRow().trim()) - 1)), CONTENT_TYPE));
			query.append(ROWS).append(URLEncoder.encode(request.getMaxRows(), CONTENT_TYPE));
			query.append(RESP_FORMAT_JSON);

			solrResponse = callSolr(getThresholdTimeCache(), query.toString());

			if (solrResponse.getGrouped().getProductGroupName().getGroups().size() == 0) {
				messages.add(DATA_NOT_FOUND);
				response.setNumFound(String.valueOf(ZERO));
				response.setMessages(messages);
				return response;
			}

			List<Groups> groups = solrResponse.getGrouped().getProductGroupName().getGroups();
			String numFound = String.valueOf(groups.size());

			for (Groups group : groups) {
				docs = group.getDocList().getDocs();
				count = group.getDocList().getModelCount();
				productGroup = new ProductGroup();
				productGroup.setProductGroupId(docs.get(0).getProductGroupId());
				productGroup.setProductGroupName(docs.get(0).getProductGroupName());
				productGroup.setItemCount(count);
				productGroups.add(productGroup);
			}

			response.setProductGroups(productGroups);
			response.setNumFound(numFound);

		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
		return response;
	}

	/**
	 * Method : getItemAttributes Description : This method retrieves all the
	 * attributes for an itemId from SOLR
	 * 
	 * @param :
	 *            String itemId, List<ItemAttributeCcdb> attributes
	 * @return : SolrResponse
	 */
	private SolrResponse getItemAttributes(String itemId, List<ItemAttributeCcdb> attributes) throws SolrException {

		SolrResponse solrResponse = null;
		StringBuilder query = new StringBuilder();
		StringBuilder attributeSb = new StringBuilder();
		try {
			query.append(JOIN_FROM_ITEM_ATTRIBUTES_TO_ID).append(URLEncoder.encode(itemId, CONTENT_TYPE))
					.append(ROUND_BRAC_CLOSE);

			if (attributes != null && !attributes.isEmpty()) {
				for (ItemAttributeCcdb attribute : attributes) {
					attributeSb = attributeSb.append("*_").append(attribute.getAttributeId()).append(SPACE);
				}
				query.append(FILTER_EXCLUSION_ITEM_ATTRIBUTE_ID).append(
						URLEncoder.encode(ROUND_BRAC_OPEN + attributeSb.toString() + ROUND_BRAC_CLOSE, CONTENT_TYPE));
			}

			/*
			 * as no maxRows is set for the itemAttributes, and solr returns
			 * only 10 records by default, setting maximum value of solr rows
			 * prop to get all the records
			 */

			query.append(ALL_ROWS);

			query.append(RESP_FORMAT_JSON);

			solrResponse = callSolr(getThresholdTimeCatalog(), query.toString());

		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
		return solrResponse;
	}

	/**
	 * Method : getItemRestrictions Description : This method retrieves all the
	 * restrictions for an itemId from SOLR
	 * 
	 * @param :
	 *            String itemId, List<ItemRestrictionCcdb> attributes
	 * @return : SolrResponse
	 */
	private SolrResponse getItemRestrictions(String itemId, List<ItemRestrictionCcdb> restrictions)
			throws SolrException {
		SolrResponse solrResponse = null;
		StringBuilder query = new StringBuilder();
		StringBuilder restrictionSb = new StringBuilder();
		try {
			query.append(JOIN_FROM_ITEM_RESTRICTIONS_TO_ID).append(URLEncoder.encode(itemId, CONTENT_TYPE))
					.append(ROUND_BRAC_CLOSE);

			if (restrictions != null && !restrictions.isEmpty()) {
				for (ItemRestrictionCcdb restriction : restrictions) {
					restrictionSb = restrictionSb.append("*_" + restriction.getRestrictionId() + SPACE);
				}
				query.append(FILTER_EXCLUSION_ITEM_RESTRICTION_ID).append(
						URLEncoder.encode(ROUND_BRAC_OPEN + restrictionSb.toString() + ROUND_BRAC_CLOSE, CONTENT_TYPE));
			}

			/*
			 * as no maxRows is set for the itemRestrictions, and solr returns
			 * only 10 records by default, setting maximum value of solr rows
			 * prop to get all the records
			 */

			query.append(ALL_ROWS);

			query.append(RESP_FORMAT_JSON);

			solrResponse = callSolr(getThresholdTimeCatalog(), query.toString());

		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
		return solrResponse;
	}

	/*
	 * private List<Item> getItemsForModel(String processId,
	 * GetModelDetailsRequest request, List<ProductGroupCcdb> productGroupList,
	 * List<ItemAvailabilityStatusCcdb> availabilityStatusList,
	 * List<ItemRestrictionCcdb> restrictionList, String itemSellPriceLimit,
	 * List<ItemAttributeCcdb> attributeList) throws SolrException{
	 * 
	 * List<Item> itemList = new ArrayList<Item>(); SolrResponse solrResponse =
	 * null; StringBuilder query = new StringBuilder(); Item item = new Item();
	 * List<String> attributes = new ArrayList<String>(); StringBuilder data =
	 * new StringBuilder();
	 * 
	 * try {
	 * query.append(SOLR_CONST.JOIN_FROM_MODEL_SCHEMATICS_TO_ITEM_SCHEMATICS)
	 * .append
	 * (URLEncoder.encode(data.append(QUOTE).append(request.getModelId()).
	 * append(QUOTE).toString(), CONTENT_TYPE));
	 * 
	 * query.append(getCommonCodeGetItem(productGroupList, restrictionList,
	 * itemSellPriceLimit.toString(), availabilityStatusList, true));
	 * 
	 * query.append(
	 * "&fl=id,itemId,productGroupIdPls,partNo,productGroupId,productGroupName,itemDescription,itemImageURL,itemAvailabilityStatus,itemSellingPrice,itemAccessoryIndicator,productIndicator,itemCondition,engineModelFlag,formattedItemId,formattedPartNo,subbedFlag,subEngineModelFlag,itemAttributes"
	 * );
	 * 
	 * as no maxRows is set for the item here, and solr returns only 10 records
	 * by default, setting maximum value of solr rows prop to get all the
	 * records
	 * 
	 * 
	 * query.append(ALL_ROWS);
	 * 
	 * query.append(RESP_FORMAT_JSON);
	 * 
	 * solrResponse = callSolr(getThresholdTimeCatalog(), query.toString());
	 * 
	 * List<String> availStatusList = new ArrayList<String>();
	 * 
	 * for(ItemAvailabilityStatusCcdb list : availabilityStatusList)
	 * availStatusList.add(list.getItemAvailabilityStatusCd());
	 * data.setLength(0);
	 * 
	 * for(Doc doc : solrResponse.getResponse().getDocs()){
	 * if(doc.getSubItemAvailabilityStatus() == null){ item =
	 * entityDocMapper.docToItemNoSubstituteWithRestrictionAttribute(doc);
	 * for(ItemAttributeCcdb attribute : attributeList){
	 * attributes.add(data.append
	 * (item.getItemId()).append("_").append(String.valueOf
	 * (attribute.getAttributeId())).toString()); }
	 * if(item.getItemAttributes()!=null && !item.getItemAttributes().isEmpty())
	 * item.getItemAttributes().removeAll(attributes); itemList.add(item);
	 * attributes.clear(); data.setLength(0); }
	 * if(doc.getSubItemAvailabilityStatus() != null &&
	 * doc.getItemAvailabilityStatus() != null){
	 * if(!availStatusList.contains(doc.getItemAvailabilityStatus()) &&
	 * !availStatusList.contains(doc.getSubItemAvailabilityStatus())){ item =
	 * entityDocMapper.docToItemNoSubstituteWithRestrictionAttribute(doc);
	 * for(ItemAttributeCcdb attribute : attributeList){
	 * attributes.add(data.append
	 * (item.getItemId()).append("_").append(String.valueOf
	 * (attribute.getAttributeId())).toString()); data.setLength(0);
	 * attributes.add
	 * (data.append(item.getSubItemId()).append("_").append(String.
	 * valueOf(attribute.getAttributeId())).toString()); data.setLength(0); }
	 * if(item.getItemAttributes()!=null && !item.getItemAttributes().isEmpty())
	 * item.getItemAttributes().removeAll(attributes); itemList.add(item);
	 * attributes.clear(); }
	 * if(availStatusList.contains(doc.getItemAvailabilityStatus()) &&
	 * !availStatusList.contains(doc.getSubItemAvailabilityStatus())){
	 * for(ItemAttributeCcdb attribute : attributeList){
	 * attributes.add(data.append
	 * (doc.getItemId()).append("_").append(String.valueOf
	 * (attribute.getAttributeId())).toString()); data.setLength(0);
	 * attributes.add
	 * (data.append(doc.getSubItemId()).append("_").append(String.valueOf
	 * (attribute.getAttributeId())).toString()); data.setLength(0); } item =
	 * entityDocMapper.docToItemSubstituteWithRestrictionAttribute(doc);
	 * if(item.getItemAttributes()!=null && !item.getItemAttributes().isEmpty())
	 * item.getItemAttributes().removeAll(attributes); itemList.add(item);
	 * attributes.clear(); }
	 * if(!availStatusList.contains(doc.getItemAvailabilityStatus()) &&
	 * availStatusList.contains(doc.getSubItemAvailabilityStatus())){
	 * for(ItemAttributeCcdb attribute : attributeList){
	 * attributes.add(data.append
	 * (doc.getItemId()).append("_").append(String.valueOf
	 * (attribute.getAttributeId())).toString()); data.setLength(0);
	 * attributes.add
	 * (data.append(doc.getSubItemId()).append("_").append(String.valueOf
	 * (attribute.getAttributeId())).toString()); data.setLength(0); } item =
	 * entityDocMapper.docToItemNoSubstituteWithRestrictionAttribute(doc);
	 * if(item.getItemAttributes()!=null && !item.getItemAttributes().isEmpty())
	 * item.getItemAttributes().removeAll(attributes); itemList.add(item);
	 * attributes.clear(); } } }
	 * 
	 * }catch (Exception e) { throw new SolrException(SOLR_LAYER_EXCEPTION, e);
	 * } return itemList; }
	 */

	private SolrResponse getItems(String itemId, String partNo, String productGroupId, String formatted,
			String startingRow, String maxRows, String sortBy, List<ProductGroupCcdb> productGroupList,
			List<ItemAvailabilityStatusCcdb> availabilityStatusList, List<ItemRestrictionCcdb> restrictions,
			String itemSellPriceLimit) throws SolrException {

		SolrResponse solrResponse = null;
		StringBuilder query = new StringBuilder();
		try {
			if (formatted == null || StringUtils.equalsIgnoreCase(formatted, NO)) {
				if (itemId != null) {
					if (itemId.endsWith("*"))
						query.append(SOLR_CONST.ITEM_ID)
								.append(escapeSOLRSpecialCharsWildCard(URLEncoder.encode(itemId, CONTENT_TYPE)));
					else
						query.append(SOLR_CONST.ITEM_ID).append(QUOTE)
								.append(escapeSOLRSpecialChars(URLEncoder.encode(itemId, CONTENT_TYPE))).append(QUOTE);
				} else {
					if (partNo.endsWith("*"))
						query.append(SOLR_CONST.PART_NO)
								.append(escapeSOLRSpecialCharsWildCard(URLEncoder.encode(partNo, CONTENT_TYPE)));
					else
						query.append(SOLR_CONST.PART_NO).append(QUOTE)
								.append(escapeSOLRSpecialChars(URLEncoder.encode(partNo, CONTENT_TYPE))).append(QUOTE);
				}
			} else {
				if (itemId != null)
					if (itemId.endsWith("*"))
						query.append(SOLR_CONST.FORMATTED_ITEM_ID)
								.append(escapeSOLRSpecialCharsWildCard(URLEncoder.encode(itemId, CONTENT_TYPE)));
					else
						query.append(SOLR_CONST.FORMATTED_ITEM_ID).append(QUOTE)
								.append(escapeSOLRSpecialChars(URLEncoder.encode(itemId, CONTENT_TYPE))).append(QUOTE);
				else if (partNo.endsWith("*"))
					query.append(SOLR_CONST.FORMATTED_PART_ID)
							.append(escapeSOLRSpecialCharsWildCard(URLEncoder.encode(partNo, CONTENT_TYPE)));
				else
					query.append(SOLR_CONST.FORMATTED_PART_ID).append(QUOTE)
							.append(escapeSOLRSpecialChars(URLEncoder.encode(partNo, CONTENT_TYPE))).append(QUOTE);
			}

			query.append(getCommonCodeGetItem(productGroupList, restrictions, itemSellPriceLimit,
					availabilityStatusList, true));

			if (productGroupId != null)
				query.append(FILTER_PRODUCT_GROUP_ID).append(URLEncoder.encode(productGroupId, CONTENT_TYPE));

			if (startingRow != null)
				query.append(START).append(
						URLEncoder.encode(String.valueOf((Integer.parseInt(startingRow.trim()) - 1)), CONTENT_TYPE));

			if (maxRows != null)
				query.append(ROWS).append(URLEncoder.encode(maxRows, CONTENT_TYPE));
			else
				query.append(ALL_ROWS);

			if (sortBy != null)
				query.append(SORT).append(URLEncoder.encode(sortBy, CONTENT_TYPE));

			query.append(RESP_FORMAT_JSON);

			solrResponse = callSolr(getThresholdTimeCatalog(), query.toString());

		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
		return solrResponse;
	}

	private SolrResponse getSchematicList(String modelNo, String modelId, String sortBy) throws SolrException {
		SolrResponse solrResponse = null;
		StringBuilder query = new StringBuilder();
		StringBuilder sort = new StringBuilder();
		StringBuilder data = new StringBuilder();
		try {

			if (modelNo != null)
				query.append(SOLR_CONST.JOIN_FROM_MODEL_SCHEMATICS_TO_MODELNO).append(
						URLEncoder.encode(data.append(QUOTE).append(modelNo).append(QUOTE).toString(), CONTENT_TYPE));
			data.setLength(0);
			if (modelId != null)
				query.append(SOLR_CONST.JOIN_FROM_MODEL_SCHEMATICS_TO_MODELID).append(
						URLEncoder.encode(data.append(QUOTE).append(modelId).append(QUOTE).toString(), CONTENT_TYPE));
			data.setLength(0);

			if (sortBy != null) {
				if (sortBy.contains(ASC))
					sortBy = sort.append(SOLR_CONST.SCHEMATIC_DESC_ASC).toString();
				else
					sortBy = sort.append(SOLR_CONST.SCHEMATIC_DESC_DESC).toString();

				query.append(SORT).append(URLEncoder.encode(sortBy, CONTENT_TYPE));
			}

			/*
			 * as no maxRows is set for the schematics, and solr returns only 10
			 * records by default, setting maximum value of solr rows prop to
			 * get all the records
			 */

			query.append(ALL_ROWS_JSON);

			solrResponse = callSolr(getThresholdTimeCatalog(), query.toString());

		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
		return solrResponse;
	}

	private SolrResponse getModelsEnhanced(ModelSearchRequest request, List<BrandCcdb> brandList,
			List<ProductTypeCcdb> productTypeList) throws SolrException {

		SolrResponse solrResponse = null;
		AbstractSolrQueryBuilder builder = new ModelQueryBuilder(request,
				getCommonCodeGetModel(brandList, productTypeList));
		solrResponse = callSolr(getThresholdTimeCatalog(), builder.buildSolrQuery());
		return solrResponse;
	}

	/** Method getModels description: retrieves data from solr based on exact, starts with, contains and fuzzy matches
	 * of modelNo as requested  by client.
	 * **/
	private SolrResponse getModels(ModelSearchRequest request, List<BrandCcdb> brandList,
			List<ProductTypeCcdb> productTypeList) throws SolrException {

		SolrResponse solrResponse = null;
		AbstractSolrQueryBuilder builder = new ModelQueryBuilder(request,
				getCommonCodeGetModel(brandList, productTypeList));
		//calling solr 
		solrResponse = callSolr(getThresholdTimeCatalog(), builder.buildQuery());
		return solrResponse;
	}
	
	private SolrResponse getModelsBySpinId(ModelSearchRequest request, List<BrandCcdb> brandList,
			List<ProductTypeCcdb> productTypeList) throws SolrException {

		SolrResponse solrResponse = null;
		AbstractSolrQueryBuilder builder = new ModelQueryBuilderWithSpinData(request,
				getCommonCodeGetModel(brandList, productTypeList));
		solrResponse = callSolr(getThresholdTimeCatalog(), builder.buildSolrQuery());
		return solrResponse;
	}

	private SolrResponse getModels(String modelNo, String modelId, String brand, String parentProductTypeId,
			String productTypeId, String subProductTypeId, String formatted, String startingRow, String maxRows,
			String sortBy, List<BrandCcdb> brandList, List<ProductTypeCcdb> productTypeList) throws SolrException {

		SolrResponse solrResponse = null;
		StringBuilder query = new StringBuilder();
		StringBuilder data = new StringBuilder();
		try {

			if (formatted == null || StringUtils.equalsIgnoreCase(formatted, NO)) {
				if (modelNo != null) {
					if (modelNo.endsWith("*"))
						query.append(SOLR_CONST.MODEL_NO)
								.append(escapeSOLRSpecialCharsWildCard(URLEncoder.encode(modelNo, CONTENT_TYPE)));
					else
						query.append(SOLR_CONST.MODEL_NO).append(QUOTE)
								.append(escapeSOLRSpecialChars(URLEncoder.encode(modelNo, CONTENT_TYPE))).append(QUOTE);
				} else {
					if (modelId.endsWith("*"))
						query.append(SOLR_CONST.MODEL_ID)
								.append(escapeSOLRSpecialCharsWildCard(URLEncoder.encode(modelId, CONTENT_TYPE)));
					else
						query.append(SOLR_CONST.MODEL_ID).append(QUOTE)
								.append(escapeSOLRSpecialChars(URLEncoder.encode(modelId, CONTENT_TYPE))).append(QUOTE);
				}
			} else {
				if (modelNo != null) {
					if (modelNo.endsWith("*"))
						query.append(SOLR_CONST.FORMATTED_MODEL_NO)
								.append(escapeSOLRSpecialCharsWildCard(URLEncoder.encode(modelNo, CONTENT_TYPE)));
					else
						query.append(SOLR_CONST.FORMATTED_MODEL_NO).append(QUOTE)
								.append(escapeSOLRSpecialChars(URLEncoder.encode(modelNo, CONTENT_TYPE))).append(QUOTE);
				} else if (modelId.endsWith("*"))
					query.append(SOLR_CONST.FORMATTED_MODEL_ID)
							.append(escapeSOLRSpecialCharsWildCard(URLEncoder.encode(modelId, CONTENT_TYPE)));
				else
					query.append(SOLR_CONST.FORMATTED_MODEL_ID).append(QUOTE)
							.append(escapeSOLRSpecialChars(URLEncoder.encode(modelId, CONTENT_TYPE))).append(QUOTE);
			}
			data.setLength(0);
			if (brand != null) {
				if (brand.endsWith("*"))
					query.append(FILTER_BRAND)
							.append(escapeSOLRSpecialCharsWildCard(URLEncoder.encode(brand, CONTENT_TYPE)));
				else
					query.append(FILTER_BRAND).append(QUOTE)
							.append(escapeSOLRSpecialChars(URLEncoder.encode(brand, CONTENT_TYPE))).append(QUOTE);
			}
			data.setLength(0);
			if (productTypeId != null)
				query.append(FILTER_PRODUCT_TYPE_ID).append(URLEncoder
						.encode(data.append(QUOTE).append(productTypeId).append(QUOTE).toString(), CONTENT_TYPE));
			data.setLength(0);
			if (subProductTypeId != null)
				query.append(FILTER_SUB_PRODUCT_TYPE_ID).append(URLEncoder
						.encode(data.append(QUOTE).append(subProductTypeId).append(QUOTE).toString(), CONTENT_TYPE));
			data.setLength(0);
			if (StringUtils.isNotBlank(parentProductTypeId))
				query.append(SOLR_CONST.FILTER_PARENT_PRODUCT_TYPE_ID).append(URLEncoder
						.encode(data.append(QUOTE).append(parentProductTypeId).append(QUOTE).toString(), CONTENT_TYPE));

			query.append(getCommonCodeGetModel(brandList, productTypeList));

			if (startingRow != null)
				query.append(START).append(
						URLEncoder.encode(String.valueOf((Integer.parseInt(startingRow.trim()) - 1)), CONTENT_TYPE));

			if (maxRows != null)
				query.append(ROWS).append(URLEncoder.encode(maxRows, CONTENT_TYPE));
			else
				query.append(ALL_ROWS);

			if (sortBy != null)
				query.append(SORT).append(URLEncoder.encode(sortBy, CONTENT_TYPE));

			query.append(RESP_FORMAT_JSON);

			solrResponse = callSolr(getThresholdTimeCatalog(), query.toString());

		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
		return solrResponse;
	}

	private SolrResponse getModels(String modelNo, String brand, String productType) throws SolrException {

		SolrResponse solrResponse = null;
		StringBuilder query = new StringBuilder();
		StringBuilder data = new StringBuilder();

		String subModelNo = null;

		if (modelNo.length() > 3)
			subModelNo = modelNo.substring(3);

		try {
			if (subModelNo != null)
				query.append("(");

			query.append(SOLR_CONST.MODEL_NO).append(QUOTE).append(URLEncoder.encode(modelNo, CONTENT_TYPE))
					.append(QUOTE);

			if (subModelNo != null) {
				query.append("+OR+").append(SOLR_CONST.MODEL_NO).append(QUOTE)
						.append(URLEncoder.encode(subModelNo, CONTENT_TYPE)).append(QUOTE).append(")");
			}

			query.append("+AND+").append(SOLR_CONST.BRAND).append(QUOTE).append(URLEncoder.encode(brand, CONTENT_TYPE))
					.append(QUOTE);

			query.append("+AND+").append(SOLR_CONST.PRODUCT_TYPE_NAME).append(QUOTE)
					.append(URLEncoder.encode(productType, CONTENT_TYPE)).append(QUOTE);

			query.append(START).append("0");
			query.append(ALL_ROWS);

			query.append(RESP_FORMAT_JSON);

			// String encQuery = URLEncoder.encode(query.toString(),
			// CONTENT_TYPE);

			solrResponse = callSolr(getThresholdTimeCatalog(), query.toString());

		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
		return solrResponse;
	}

	private SolrResponse getBrands(String clientKey, String brand, String sortBy, List<BrandCcdb> brandIdList)
			throws SolrException {
		SolrResponse solrResponse = null;
		StringBuilder query = new StringBuilder();
		StringBuilder brandListSb = new StringBuilder();
		try {
			if (StringUtils.equals(brand, ALL_SEARCH_CRITERIA))
				query.append(BRAND_ALL);
			else {
				if (brand.endsWith("*"))
					query.append(SOLR_CONST.BRAND)
							.append(escapeSOLRSpecialCharsWildCard(URLEncoder.encode(brand, CONTENT_TYPE)));
				else
					query.append(SOLR_CONST.BRAND).append(QUOTE)
							.append(escapeSOLRSpecialChars(URLEncoder.encode(brand, CONTENT_TYPE))).append(QUOTE);
			}

			query.append(FACET_ON_FIELD_BRAND);

			if (brandIdList != null && !brandIdList.isEmpty()) {
				for (BrandCcdb brands : brandIdList) {
					brandListSb = brandListSb.append(brands.getBrandId()).append(SPACE);
				}
				query.append(FILTER_EXCLUSION_BRANDID)
						.append(URLEncoder.encode(String.format(WITHIN_BRACES, brandListSb.toString()), CONTENT_TYPE));
			}

			query.append(RESP_FORMAT_JSON);

			solrResponse = callSolr(getThresholdTimeCatalog(), query.toString());

		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
		return solrResponse;
	}

	/**
	 * Method : callSolr Description : This method calls SOLR for all the SOLR
	 * queries
	 * 
	 * @param :
	 *            String query
	 * @return : SolrResponse
	 */
	private SolrResponse callSolr(String thresholdTime, String query) throws SolrException {
		SolrResponse solrResponse = null;
		URL solrRequest = null;
		ObjectMapper objectMapper = new ObjectMapper();
		HttpURLConnection conn = null;
		BufferedReader bReader = null;
		long startTime = System.currentTimeMillis();

		try {
			logger.debug("executing SOLR Query[{}{}]", solrEndpoint.trim(), query.toString());

			solrRequest = new URL(solrEndpoint.trim() + query);
			conn = (HttpURLConnection) (solrRequest.openConnection());

			int code = conn.getResponseCode();
			if (code < MIN_RESPONSE_CODE && code >= MAX_RESPONSE_CODE) {
				throw new SolrException(SOLR_LAYER_EXCEPTION);
			}

			bReader = new BufferedReader(new InputStreamReader(new DataInputStream(conn.getInputStream())));
			solrResponse = objectMapper.readValue(bReader, SolrResponse.class);

			return solrResponse;

		} catch (Exception e) {
			logger.error("Exception Occured for. SOLR Query : {}", query);
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		} finally {
			if (bReader != null) {
				try {
					bReader.close();
				} catch (IOException exp) {
					logger.error("Exception Occured for SOLR Query : {}", query);
					logger.error("IOException occurred : " + exp.getMessage());
				}
			}
			if (conn != null)
				conn.disconnect();

			long endTime = System.currentTimeMillis();
			long responseTime = CommonUtils.getServiceResponseTime(startTime, endTime);

			logger.debug("SOLR Query[{}] FINISHED in {} MS", query.toString(), responseTime);

			if (responseTime > Long.parseLong(thresholdTime))
				logger.warn("SOLR Query[{}] EXCEEDED max threshold : {}", query.toString(), thresholdTime);
		}
	}

	private String getThresholdTimeCatalog() {
		return thresholdTimeCatalog.trim();
	}

	private String getThresholdTimeCache() {
		return thresholdTimeCache.trim();
	}

	private StringBuilder getCommonCodeGetItem(List<ProductGroupCcdb> productGroupList,
			List<ItemRestrictionCcdb> restrictions, String itemSellPriceLimit,
			List<ItemAvailabilityStatusCcdb> availabilityStatusList, boolean subbedFlag) throws SolrException {

		StringBuilder query = new StringBuilder();
		StringBuilder productGroupListSb = new StringBuilder();
		StringBuilder availabilityStatusSb = new StringBuilder();
		StringBuilder restrictionSb = new StringBuilder();

		try {
			if (productGroupList != null && !productGroupList.isEmpty()) {
				for (ProductGroupCcdb productGroup : productGroupList) {
					productGroupListSb = productGroupListSb.append(productGroup.getProductGroupId()).append(SPACE);
				}
				query.append(FILTER_EXCLUSION_PRODUCT_GROUP_ID).append(
						URLEncoder.encode(String.format(WITHIN_BRACES, productGroupListSb.toString()), CONTENT_TYPE));
			}

			if (restrictions != null && !restrictions.isEmpty()) {
				for (ItemRestrictionCcdb restriction : restrictions) {
					restrictionSb = restrictionSb.append(restriction.getRestrictionId()).append(",* ");
				}
				query.append(FILTER_EXCLUSION_RESTRICTIONS).append(
						URLEncoder.encode(String.format(WITHIN_BRACES, restrictionSb.toString()), CONTENT_TYPE));
			}
			if (itemSellPriceLimit != null)
				query.append(FILTER_ITEM_SELL_PRICE_LIMIT).append(URLEncoder.encode(itemSellPriceLimit, CONTENT_TYPE))
						.append(TO_FILTER);
			return query;
		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}

	}

	private StringBuilder getCommonCodeGetModel(List<BrandCcdb> brandList, List<ProductTypeCcdb> productTypeList)
			throws SolrException {
		StringBuilder query = new StringBuilder();
		StringBuilder brandListSb = new StringBuilder();
		StringBuilder productTypeListSb = new StringBuilder();
		try {
			if (brandList != null && !brandList.isEmpty()) {
				for (BrandCcdb ccdbBrand : brandList) {
					brandListSb = brandListSb.append(ccdbBrand.getBrandId() + SPACE);
				}
				query.append(FILTER_EXCLUSION_BRANDID)
						.append(URLEncoder.encode(String.format(WITHIN_BRACES, brandListSb.toString()), CONTENT_TYPE));
			}

			if (productTypeList != null && !productTypeList.isEmpty()) {
				for (ProductTypeCcdb productType : productTypeList) {
					productTypeListSb = productTypeListSb.append(productType.getProductTypeCd()).append(SPACE);
				}
				final String productType = URLEncoder.encode(String.format(WITHIN_BRACES, productTypeListSb.toString()),
						CONTENT_TYPE);
				query.append(FILTER_EXCLUSION_PRODUCT_TYPE_ID).append(productType);
				query.append(FILTER_EXCLUSION_PARENT_PRODUCT_TYPE_ID).append(productType);
				query.append(FILTER_EXCLUSION_SUB_PRODUCT_TYPE_ID).append(productType);
			}
			return query;
		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
	}

	private List<Item> getOriOrSubsItemList(SolrResponse solrResponse,
			List<ItemAvailabilityStatusCcdb> availabilityStatusList) {
		List<Item> itemList = new ArrayList<Item>();
		Item item = new Item();
		List<String> availStatusList = new ArrayList<String>();

		for (ItemAvailabilityStatusCcdb list : availabilityStatusList)
			availStatusList.add(list.getItemAvailabilityStatusCd());

		for (Doc doc : solrResponse.getResponse().getDocs()) {
			if (doc.getItemId() != null) {
				if (StringUtils.equals(doc.getSubbedFlag(), PartsCatalogServiceConstants.GEN_CONST.YES)) {
					item = entityDocMapper.docToItemSubstitute(doc);
					itemList.add(item);
				} else if (!availStatusList.contains(doc.getItemAvailabilityStatus())) {
					item = entityDocMapper.docToItemNoSubstitute(doc);
					itemList.add(item);
				}
			}
		}
		return itemList;
	}

	/**
	 * Method : modelSearchEnhanced Description : This method retrieves models
	 * from SOLR and ignores
	 * 
	 * @param :
	 *            ModelSearchRequest, List<BrandCcdb> brandList, List
	 *            <ProductTypeCcdb> productTypeList
	 * @return : BaseResponse
	 */
	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse modelSearchEnhanced(ModelSearchRequest request, List<BrandCcdb> brandList,
			List<ProductTypeCcdb> productTypeList) throws SolrException {

		SolrResponse solrResponse = null;
		ModelSearchResponse modelSearchResponse = new ModelSearchResponse();
		List<Model> modelList = new ArrayList<Model>();
		Model model = new Model();
		List<String> messages = new ArrayList<String>();

		solrResponse = getModelsEnhanced(request, brandList, productTypeList);

		if (solrResponse.getResponse().getDocs().length == 0) {
			messages.add(DATA_NOT_FOUND);
			modelSearchResponse.setNumFound(String.valueOf(ZERO));
			modelSearchResponse.setMessages(messages);
			return modelSearchResponse;
		}
		for (Doc doc : solrResponse.getResponse().getDocs()) {
			model = entityDocMapper.docToModelWithItemCount(doc);
			if (request.isFuzzySearch()) {
				model.setFuzzySearchMatch(YES);
			} else {
				model.setFuzzySearchMatch(NO);
			}
			modelList.add(model);
		}

		addFacetCountsToResponse(solrResponse, modelSearchResponse);

		modelSearchResponse.setModels(modelList);
		modelSearchResponse.setNumFound(solrResponse.getResponse().getNumFound());

		return modelSearchResponse;
	}
	
	/**
	 * Method : modelSearchForExactAndContainsMatch Description : This method retrieves models
	 * from SOLR and ignores
	 * 
	 * @param :
	 *            ModelSearchRequest, List<BrandCcdb> brandList, List
	 *            <ProductTypeCcdb> productTypeList           
	 * @return : BaseResponse
	 */
	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse modelSearchForExactAndContainsMatch(ModelSearchRequest request, List<BrandCcdb> brandList,
			List<ProductTypeCcdb> productTypeList) throws SolrException {

		SolrResponse solrResponse = null;
		ModelSearchResponse modelSearchResponse = new ModelSearchResponse();
		List<Model> modelList = new ArrayList<Model>();
		Model model = new Model();
		List<String> messages = new ArrayList<String>();

		solrResponse = getModels(request, brandList, productTypeList);

		if (solrResponse.getResponse().getDocs().length == 0) {
			messages.add(DATA_NOT_FOUND);
			modelSearchResponse.setNumFound(String.valueOf(ZERO));
			modelSearchResponse.setMessages(messages);
			return modelSearchResponse;
		}
		for (Doc doc : solrResponse.getResponse().getDocs()) {
			model = entityDocMapper.docToModelWithItemCount(doc);
			if (request.isFuzzySearch()) {
				model.setFuzzySearchMatch(YES);
			} else {
				model.setFuzzySearchMatch(NO);
			}
			modelList.add(model);
		}

		addFacetCountsToResponse(solrResponse, modelSearchResponse);

		modelSearchResponse.setModels(modelList);
		modelSearchResponse.setNumFound(solrResponse.getResponse().getNumFound());

		return modelSearchResponse;
	}

	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse search(String value) throws SolrException {

		SolrResponse solrResponse = null;
		value = value.trim();
		AbstractSolrQueryBuilder builder = new SearchQueryBuilder(value);
		solrResponse = callSolr(getThresholdTimeCatalog(), builder.buildSolrQuery());

		SearchResponse searchResponse = new SearchResponse();
		List<AValue> avalues = new ArrayList<AValue>();
		AValue avalue = new AValue();
		List<String> messages = new ArrayList<String>();

		if (solrResponse.getResponse().getDocs().length == 0) {
			messages.add(DATA_NOT_FOUND);
			searchResponse.setNumFound(String.valueOf(ZERO));
			searchResponse.setMessages(messages);
			return searchResponse;
		}

		for (Doc doc : solrResponse.getResponse().getDocs()) {
			avalue = entityDocMapper.docToSearch(doc);
			avalues.add(avalue);
		}
		
		List<com.searshc.hspartcatalog.pojo.Brand> brands = brandCache.getBrands(value);
		if(brands != null) {
			for (com.searshc.hspartcatalog.pojo.Brand brand : brands) {
				avalue = new AValue();
				avalue.setId(brand.getId());
				avalue.setNumber(brand.getId());
				avalue.setDescription(brand.getName());
				avalue.setType("B");
				
				avalues.add(avalue);
			}
		}
		
		List<com.searshc.hspartcatalog.pojo.Category> categories = categoryCache.getCategories(value);
		if(categories != null) {
			for (com.searshc.hspartcatalog.pojo.Category category : categories) {
				avalue = new AValue();
				avalue.setId(category.getId());
				avalue.setNumber(category.getId());
				avalue.setDescription(category.getName());
				avalue.setType("C");
				
				avalues.add(avalue);
			}
		}
		
		// look for EXACT values
		List<AValue> evalues = new ArrayList<AValue>();
		for (AValue val : avalues) {
			if (StringUtils.equalsIgnoreCase(val.getNumber(), value)
					|| StringUtils.equalsIgnoreCase(val.getDescription(), value))
				evalues.add(val);
		}

		if (evalues.size() > 0) {
			searchResponse.setValues(evalues);
			searchResponse.setNumFound(String.valueOf(evalues.size()));
		}	
		else {
			searchResponse.setValues(avalues);
			searchResponse.setNumFound(String.valueOf(avalues.size()));
		}	
		

		return searchResponse;
	}

	private void addFacetCountsToResponse(SolrResponse solrResponse, ModelSearchResponse modelSearchResponse) {
		FacetCounts facetCount = solrResponse.getFacetCounts();
		ModelFacetCounts modelFacets = entityDocMapper.facetToModelFacetCount(facetCount);
		modelSearchResponse.setFacetCounts(modelFacets);
	}

	/**
	 * Method : modelSearchBySearsId Description : This method retrieves models
	 * from SOLR by sears model id
	 * 
	 * @param :
	 *            ModelSearchRequest, List<BrandCcdb> brandList, List
	 *            <ProductTypeCcdb> productTypeList
	 * @return : BaseResponse
	 */
	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse modelSearchBySearsId(ModelSearchRequest request, List<BrandCcdb> brandList,
			List<ProductTypeCcdb> productTypeList) throws SolrException {

		SolrResponse solrResponse = null;
		ModelSearchResponse modelSearchResponse = new ModelSearchResponse();
		List<Model> modelList = new ArrayList<Model>();
		Model model = new Model();
		List<String> messages = new ArrayList<String>();

		solrResponse = getModelsBySpinId(request, brandList, productTypeList);

		boolean noResultsFromSolr = (solrResponse != null && solrResponse.getResponse().getDocs().length == 0);

		if (noResultsFromSolr) {
			messages.add(DATA_NOT_FOUND);
			modelSearchResponse.setNumFound(String.valueOf(ZERO));
			modelSearchResponse.setMessages(messages);
			return modelSearchResponse;
		}
		for (Doc doc : solrResponse.getResponse().getDocs()) {
			model = entityDocMapper.docToModelWithItemCount(doc);
			model.setFuzzySearchMatch(NO);
			modelList.add(model);
		}

		addFacetCountsToResponse(solrResponse, modelSearchResponse);

		modelSearchResponse.setModels(modelList);
		modelSearchResponse.setNumFound(solrResponse.getResponse().getNumFound());

		return modelSearchResponse;
	}
}
