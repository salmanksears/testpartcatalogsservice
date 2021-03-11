package com.searshc.hspartcatalog.services.service.impl;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.STARTING_ROW_DEFAULT;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.XML;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.YES;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.ZERO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.API_KEY;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.BRAND;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.BRAND_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.DESCRIPTION;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.ENABLE_FUZZY_SEARCH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.ENABLE_SEARS_ID_SEARCH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.FACET_BY;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.FORMATTED;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.ITEM_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.KEY;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MAX_ROWS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MODEL_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MODEL_NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PARENT_PRODUCT_TYPE_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PART_NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PRODUCT_GROUP_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PRODUCT_TYPE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PRODUCT_TYPE_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.SORT_BY;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.STARTING_ROW;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.SUB_PRODUCT_TYPE_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_CODE.DATA_VALIDATION_ERROR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_CODE.SUCCESS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_CODE.SYSTEM_ERROR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.CLIENT_INVALID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.DATA_NOT_FOUND;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.REQUIRED_FIELD_MISSING;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.SUCCESS_MSG;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.SYSTEM_ERROR_MSG;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SERVICE_METHOD.CORE_ENV_CHARGE_AMOUNT;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SERVICE_METHOD.FULL_SEARCH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SERVICE_METHOD.GET_ACCESSORIES;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SERVICE_METHOD.GET_BRANDS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SERVICE_METHOD.GET_BRAND_LIST;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SERVICE_METHOD.GET_ITEM_DETAILS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SERVICE_METHOD.GET_ITEM_LIST;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SERVICE_METHOD.GET_MODELS_FOR_ITEM;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SERVICE_METHOD.GET_MODEL_DETAILS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SERVICE_METHOD.GET_MODEL_LIST;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SERVICE_METHOD.GET_PARENT_PRODUCT_TYPE_LIST;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SERVICE_METHOD.GET_PRODUCT_GROUP_LIST;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SERVICE_METHOD.GET_SCHEMATICS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SERVICE_METHOD.ITEM_SEARCH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SERVICE_METHOD.MODEL_SEARCH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.VALIDATOR.ACCESSORY_REQUEST_VALIDATOR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.VALIDATOR.CORE_ENV_CHARGE_AMOUNT_VALIDATOR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.VALIDATOR.FULL_SEARCH_REQUEST_VALIDATOR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.VALIDATOR.GET_BRANDS_REQUEST_VALIDATOR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.VALIDATOR.GET_BRAND_LIST_REQUEST_VALIDATOR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.VALIDATOR.GET_ITEM_DETAILS_REQUEST_VALIDATOR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.VALIDATOR.GET_ITEM_LIST_REQUEST_VALIDATOR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.VALIDATOR.GET_MODELS_FOR_ITEM_REQUEST_VALIDATOR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.VALIDATOR.GET_MODEL_DETAILS_REQUEST_VALIDATOR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.VALIDATOR.GET_MODEL_LIST_REQUEST_VALIDATOR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.VALIDATOR.GET_PARENT_PRODUCT_TYPE_LIST_REQUEST_VALIDATOR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.VALIDATOR.GET_PRODUCT_GROUP_LIST_REQUEST_VALIDATOR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.VALIDATOR.GET_SCHEMATICS_REQUEST_VALIDATOR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.VALIDATOR.ITEM_DESCRIPTION_SEARCH_REQUEST_VALIDATOR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.VALIDATOR.ITEM_SEARCH_REQUEST_VALIDATOR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.VALIDATOR.MODEL_SEARCH_REQUEST_VALIDATOR;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

import com.searshc.hs.psc.logging.annotation.ServiceAuditLogger;
import com.searshc.hs.psc.service.constants.Constants;
import com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST;
import com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS;
import com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC;
import com.searshc.hspartcatalog.exceptions.CcdbException;
import com.searshc.hspartcatalog.helper.ItemDiscountHelper;
import com.searshc.hspartcatalog.pojo.ValidatorOutput;
import com.searshc.hspartcatalog.services.bo.AccessoryBO;
import com.searshc.hspartcatalog.services.bo.ClientConfigBO;
import com.searshc.hspartcatalog.services.bo.CoreAndEnvChargeAmountBO;
import com.searshc.hspartcatalog.services.bo.ItemDescriptionSearchBO;
import com.searshc.hspartcatalog.services.bo.SolrServiceInvokerBO;
import com.searshc.hspartcatalog.services.domain.Accessory;
import com.searshc.hspartcatalog.services.domain.BrandCcdb;
import com.searshc.hspartcatalog.services.domain.ItemAttributeCcdb;
import com.searshc.hspartcatalog.services.domain.ItemAvailabilityStatusCcdb;
import com.searshc.hspartcatalog.services.domain.ItemRestrictionCcdb;
import com.searshc.hspartcatalog.services.domain.Maintenance;
import com.searshc.hspartcatalog.services.domain.Model;
import com.searshc.hspartcatalog.services.domain.PartDetailReturn;
import com.searshc.hspartcatalog.services.domain.ProductGroupCcdb;
import com.searshc.hspartcatalog.services.domain.ProductTypeCcdb;
import com.searshc.hspartcatalog.services.service.PartsCatalogService;
import com.searshc.hspartcatalog.services.vo.base.BaseRequest;
import com.searshc.hspartcatalog.services.vo.base.BaseResponse;
import com.searshc.hspartcatalog.services.vo.request.AccessoryRequest;
import com.searshc.hspartcatalog.services.vo.request.FullSearchRequest;
import com.searshc.hspartcatalog.services.vo.request.GetBrandListRequest;
import com.searshc.hspartcatalog.services.vo.request.GetBrandsRequest;
import com.searshc.hspartcatalog.services.vo.request.GetCoreAndEnvChargeAmountRequest;
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
import com.searshc.hspartcatalog.services.vo.response.AccessoryResponse;
import com.searshc.hspartcatalog.services.vo.response.FullSearchResponse;
import com.searshc.hspartcatalog.services.vo.response.GetBrandListResponse;
import com.searshc.hspartcatalog.services.vo.response.GetBrandsResponse;
import com.searshc.hspartcatalog.services.vo.response.GetCoreAndEnvChargeAmountResponse;
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
import com.searshc.hspartcatalog.util.ClientUtils;
import com.searshc.hspartcatalog.util.ResponseFactory;
import com.searshc.hspartcatalog.util.ThreadLocalContainer;
import com.searshc.hspartcatalog.validator.Validator;
import com.searshc.hspartcatalog.validator.ValidatorContainer;

public class PartsCatalogServiceImpl implements PartsCatalogService {

	private static final Logger logger = LoggerFactory.getLogger(PartsCatalogServiceImpl.class);

	@Autowired
	private ClientConfigBO clientConfigBO;
	@Autowired
	private ValidatorContainer validatorContainer;
	@Autowired
	private SolrServiceInvokerBO solrServiceInvokerBO;

	@Autowired
	private AccessoryBO accessoryBO;
	@Autowired
	private CoreAndEnvChargeAmountBO coreAndEnvChargeAmountBO;

	@Autowired
	private ItemDescriptionSearchBO itemDescriptionSearchBO;

	@Autowired
	private ItemDiscountHelper discountHelper;

	@Value("${maxRowsForCacheAPI}")
	private String maxRowsCacheDefault;

	@Value("${maxRowsForCatalogAPI}")
	private String maxRowsDefault;

	/**
	 * Method : ping Description : This method checks the health of service
	 * 
	 * @return
	 */
	@ServiceAuditLogger
	@Override
	public Response ping() {
		ModelSearchRequest request = createModelSearchRequest(ClientUtils.getClientKey(), "*", null, null, null, null,
				null, null, null, null, null, null, null);
		try {
			solrServiceInvokerBO.modelSearch(request, null, null);
		} catch (Exception ex) {
			logger.error("{}", ex);
			return Response.status(Response.Status.BAD_REQUEST).build();
		} finally {
			ThreadLocalContainer.clear();
		}
		return Response.status(Response.Status.OK).build();
	}

	/**
	 * Method : itemSearch Description : This method retrieves items based on
	 * itemId or partNo. Those items can be filtered out based on
	 * productGroupId.
	 * 
	 * @param :
	 *            String itemId, String partNo, String productGroupId
	 * @return : BaseResponse
	 */

	@ServiceAuditLogger(responseCode = "responseCode", responseMessage = "messages")
	@Override
	public BaseResponse itemSearch(String clientKey, String itemId, String partNo, String productGroupId,
			String responseFormat, String formatted, String startingRow, String maxRows, String sortBy,
			UriInfo uriInfo) {

		ItemSearchRequest request = new ItemSearchRequest();
		ItemSearchResponse response = null;
		List<String> messages = new ArrayList<String>();
		List<ItemAvailabilityStatusCcdb> availabilityStatusList = null;
		List<ProductGroupCcdb> productGroupList = null;
		List<ItemRestrictionCcdb> restrictionList = null;
		List<String> definedParamaList = null;

		try {

			request = createItemSearchRequest(clientKey, itemId, partNo, responseFormat, formatted, startingRow,
					maxRows, sortBy, productGroupId);

			// validating client, if invalid service will return from this point
			// itself

			response = (ItemSearchResponse) validateClient(request.getClientKey(), ITEM_SEARCH);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamaList = getDefinedParamList(ITEM_ID, PART_NO, PRODUCT_GROUP_ID, null, null, null, null,
						null, null, null, null, FORMATTED, STARTING_ROW, MAX_ROWS, SORT_BY, null, null);

				// validating request params, if invalid service will return
				// from this point itself

				response = (ItemSearchResponse) validateRequest(request, ITEM_SEARCH_REQUEST_VALIDATOR, ITEM_SEARCH,
						uriInfo, definedParamaList);
				if (response.getResponseCode().equals(SUCCESS)) {
					final String messg = String.format(RESPONSE_DESC.INVALID_MAXROWS_LESSTHAN_EQUALTO,
							getMaxRowsDefault());
					if (response.getMessages().contains(messg)) {
						request.setMaxRows(getMaxRowsDefault());
						messages.add(messg);
					}

					// fetching exclusion data from client config DB

					Integer clientId = ClientUtils.getClientId();
					productGroupList = getProductGroups(clientId);
					restrictionList = getItemRestriction(clientId);
					availabilityStatusList = getItemAvailabilityStatus(clientId);
					BigDecimal itemSellPriceLimit = getItemSellPriceLimit(clientId);

					// querying SOLR to fetch data based on the request params
					// with exclusion applied

					response = (ItemSearchResponse) solrServiceInvokerBO.itemSearch(request, productGroupList,
							availabilityStatusList, restrictionList, itemSellPriceLimit);
					if (Integer.parseInt(response.getNumFound()) == 0) {
						response = (ItemSearchResponse) buildResponse(SUCCESS, messages, response);
						return response;
					}
					if (messages.isEmpty())
						messages.add(SUCCESS_MSG);
					response.setMessages(messages);
					response.setProcessId(MDC.get(Constants.PROCESS_ID));
					response.setResponseCode(SUCCESS);
					discountHelper.applyDiscount(response, clientKey);
				}
			}

			return response;
		} catch (Exception ex) {
			logger.error("{}", ex);
			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (ItemSearchResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}
	}

	@ServiceAuditLogger(responseCode = "responseCode", responseMessage = "messages")
	@Override
	public BaseResponse itemDescriptionSearch(String clientKey, String brand, String productType, String modelNo,
			String description, String responseFormat, String startingRow, String maxRows, String sortBy,
			UriInfo uriInfo) {

		ItemDescriptionSearchRequest request = new ItemDescriptionSearchRequest();
		ItemSearchResponse response = null;
		List<String> messages = new ArrayList<String>();
		List<ItemAvailabilityStatusCcdb> availabilityStatusList = null;
		List<ProductGroupCcdb> productGroupList = null;
		List<ItemRestrictionCcdb> restrictionList = null;
		List<String> definedParamaList = null;

		try {

			request = createDescriptionSearchRequest(clientKey, brand, productType, modelNo, description,
					responseFormat, startingRow, maxRows, sortBy);

			response = (ItemSearchResponse) validateClient(request.getClientKey(), ITEM_SEARCH);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamaList = Arrays.asList(BRAND, PRODUCT_TYPE, MODEL_NO, DESCRIPTION, STARTING_ROW, MAX_ROWS,
						SORT_BY);

				// validating request params, if invalid service will return
				// from this point itself

				response = (ItemSearchResponse) validateRequest(request, ITEM_DESCRIPTION_SEARCH_REQUEST_VALIDATOR,
						ITEM_SEARCH, uriInfo, definedParamaList);

				if (response.getResponseCode().equals(SUCCESS)) {
					final String messg = String.format(RESPONSE_DESC.INVALID_MAXROWS_LESSTHAN_EQUALTO,
							getMaxRowsDefault());
					if (response.getMessages().contains(messg)) {
						request.setMaxRows(getMaxRowsDefault());
						messages.add(messg);
					}

					// fetching exclusion data from client config DB

					Integer clientId = ClientUtils.getClientId();
					productGroupList = getProductGroups(clientId);
					restrictionList = getItemRestriction(clientId);
					availabilityStatusList = getItemAvailabilityStatus(clientId);
					BigDecimal itemSellPriceLimit = getItemSellPriceLimit(clientId);

					/*
					 * response = (ItemSearchResponse)
					 * solrServiceInvokerBO.itemDescriptionSearch2(request,
					 * productGroupList, availabilityStatusList,
					 * restrictionList, itemSellPriceLimit);
					 */

					response = (ItemSearchResponse) itemDescriptionSearchBO.itemDescriptionSearch(request,
							availabilityStatusList);

					if (Integer.parseInt(response.getNumFound()) == 0) {
						response = (ItemSearchResponse) buildResponse(SUCCESS, messages, response);
						return response;
					}
					if (messages.isEmpty())
						messages.add(SUCCESS_MSG);
					response.setMessages(messages);
					response.setProcessId(MDC.get(Constants.PROCESS_ID));
					response.setResponseCode(SUCCESS);
				}
			}

			return response;
		} catch (Exception ex) {
			logger.error("{}", ex);
			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (ItemSearchResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}
	}

	/**
	 * Method : modelSearch Description : This method retrieves models based on
	 * modelNo or modelId. Those models can be filtered out based on
	 * parentProductTypeId, productTypeId, subProductTypeId, or brand
	 * 
	 * @param :
	 *            String modelNo, String modelId, String parentProductTypeId,
	 *            String productTypeId, String subProductTypeId, String brand
	 * @return : BaseResponse
	 */

	@ServiceAuditLogger(responseCode = "responseCode", responseMessage = "messages")
	@Override
	public BaseResponse modelSearch(String clientKey, String modelNo, String modelId, String brand,
			String parentProductTypeId, String productTypeId, String subProductTypeId, String responseFormat,
			String formatted, String startingRow, String maxRows, String sortBy, UriInfo uriInfo) {

		ModelSearchRequest request = new ModelSearchRequest();
		ModelSearchResponse response = new ModelSearchResponse();
		List<String> messages = new ArrayList<String>();
		List<BrandCcdb> brandIdList = null;
		List<ProductTypeCcdb> productTypeList = null;
		List<String> definedParamaList = null;

		try {

			request = createModelSearchRequest(clientKey, modelNo, modelId, responseFormat, brand, formatted,
					startingRow, maxRows, parentProductTypeId, productTypeId, subProductTypeId, sortBy, null);

			// validating client, if invalid service will return from this point
			// itself

			response = (ModelSearchResponse) validateClient(request.getClientKey(), MODEL_SEARCH);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamaList = getDefinedParamList(null, null, null, MODEL_ID, MODEL_NO, null, BRAND,
						PARENT_PRODUCT_TYPE_ID, PRODUCT_TYPE_ID, SUB_PRODUCT_TYPE_ID, null, FORMATTED, STARTING_ROW,
						MAX_ROWS, SORT_BY, null, null);

				// validating request params, if invalid service will return
				// from this point itself

				response = (ModelSearchResponse) validateRequest(request, MODEL_SEARCH_REQUEST_VALIDATOR, MODEL_SEARCH,
						uriInfo, definedParamaList);
				if (response.getResponseCode().equals(SUCCESS)) {
					final String messg = String.format(RESPONSE_DESC.INVALID_MAXROWS_LESSTHAN_EQUALTO,
							getMaxRowsDefault());
					if (response.getMessages().contains(messg)) {
						request.setMaxRows(getMaxRowsDefault());
						messages.add(messg);
					}
					// fetching exclusion data from client config DB

					Integer clientId = ClientUtils.getClientId();
					brandIdList = getBrandIds(clientId);
					productTypeList = getProductTypes(clientId);

					// querying SOLR to fetch data based on the request params
					// with exclusion applied

					response = (ModelSearchResponse) solrServiceInvokerBO.modelSearch(request, brandIdList,
							productTypeList);

					if (Integer.parseInt(response.getNumFound()) == 0) {
						response = (ModelSearchResponse) buildResponse(SUCCESS, messages, response);
						return response;
					}
					if (messages.isEmpty())
						messages.add(SUCCESS_MSG);
					response.setMessages(messages);
					response.setProcessId(MDC.get(Constants.PROCESS_ID));
					response.setResponseCode(SUCCESS);
				}
			}

			return response;

		} catch (Exception ex) {
			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (ModelSearchResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}
	}

	/**
	 * Method : fullSearch Description : This method retrieves models or items
	 * based on key. The value of key can either be modelNo or partNo.
	 * 
	 * @param :
	 *            String key
	 * @return : BaseResponse
	 */
	@ServiceAuditLogger(responseCode = "responseCode", responseMessage = "messages")
	@Override
	public BaseResponse fullSearch(String clientKey, String key, String responseFormat, String formatted,
			String startingRow, String maxRows, String sortBy, UriInfo uriInfo) {

		FullSearchRequest request = new FullSearchRequest();
		FullSearchResponse response = new FullSearchResponse();
		List<String> messages = new ArrayList<String>();
		List<BrandCcdb> brandIdList = null;
		List<ProductTypeCcdb> productTypeList = null;
		List<ProductGroupCcdb> productGroupList = null;
		List<ItemAvailabilityStatusCcdb> availabilityStatusList = null;
		List<ItemRestrictionCcdb> restrictionList = null;
		List<String> definedParamaList = null;

		try {

			request = createFullSearchRequest(clientKey, key, responseFormat, formatted, startingRow, maxRows, sortBy);

			// validating client, if invalid service will return from this point
			// itself

			response = (FullSearchResponse) validateClient(request.getClientKey(), FULL_SEARCH);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamaList = getDefinedParamList(null, null, null, null, null, null, null, null, null, null, KEY,
						FORMATTED, STARTING_ROW, MAX_ROWS, SORT_BY, null, null);

				// validating request params, if invalid service will return
				// from this point itself

				response = (FullSearchResponse) validateRequest(request, FULL_SEARCH_REQUEST_VALIDATOR, FULL_SEARCH,
						uriInfo, definedParamaList);
				if (response.getResponseCode().equals(SUCCESS)) {
					final String messg = String.format(RESPONSE_DESC.INVALID_MAXROWS_LESSTHAN_EQUALTO,
							getMaxRowsDefault());
					if (response.getMessages().contains(messg)) {
						request.setMaxRows(getMaxRowsDefault());
						messages.add(messg);
					}
					// fetching exclusion data from client config DB
					Integer clientId = ClientUtils.getClientId();
					brandIdList = getBrandIds(clientId);
					productTypeList = getProductTypes(clientId);
					productGroupList = getProductGroups(clientId);
					availabilityStatusList = getItemAvailabilityStatus(clientId);
					restrictionList = getItemRestriction(clientId);
					BigDecimal itemSellPriceLimit = getItemSellPriceLimit(clientId);

					// querying SOLR to fetch data based on the request params
					// with exclusion applied

					response = (FullSearchResponse) solrServiceInvokerBO.fullSearch(request, brandIdList,
							productTypeList, productGroupList, availabilityStatusList, restrictionList,
							itemSellPriceLimit);

					if (Integer.parseInt(response.getNumFound()) == 0) {
						response = (FullSearchResponse) buildResponse(SUCCESS, messages, response);
						return response;
					}
					if (messages.isEmpty())
						messages.add(SUCCESS_MSG);
					response.setMessages(messages);
					response.setProcessId(MDC.get(Constants.PROCESS_ID));
					response.setResponseCode(SUCCESS);
					// HSPDME-391 apply discount
					discountHelper.applyDiscount(response, clientKey);
				}
			}

			return response;
		} catch (Exception ex) {
			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (FullSearchResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}
	}

	/**
	 * Method : getBrands Description : This method retrieves brands based on
	 * brand (brandName).
	 * 
	 * @param :
	 *            String brand
	 * @return : BaseResponse
	 */
	@ServiceAuditLogger(responseCode = "responseCode", responseMessage = "messages")
	@Override
	public BaseResponse getBrands(String clientKey, String brand, String responseFormat, String sortBy,
			UriInfo uriInfo) {

		GetBrandsRequest request = new GetBrandsRequest();
		GetBrandsResponse response = new GetBrandsResponse();
		List<String> messages = new ArrayList<String>();
		List<BrandCcdb> brandIdList = null;
		List<BrandCcdb> brandList = null;
		boolean solrQueryRequired = true;
		List<String> definedParamaList = null;

		try {

			request = createGetBrandsRequest(clientKey, brand, responseFormat, sortBy);

			// validating client, if invalid service will return from this point
			// itself

			response = (GetBrandsResponse) validateClient(request.getClientKey(), GET_BRANDS);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamaList = getDefinedParamList(null, null, null, null, null, null, BRAND, null, null, null,
						null, null, null, null, SORT_BY, null, null);

				// validating request params, if invalid service will return
				// from this point itself

				response = (GetBrandsResponse) validateRequest(request, GET_BRANDS_REQUEST_VALIDATOR, GET_BRANDS,
						uriInfo, definedParamaList);
				if (response.getResponseCode().equals(SUCCESS)) {

					// fetching exclusion data from client config DB

					Integer clientId = ClientUtils.getClientId();
					brandList = getBrands(clientId);
					brandIdList = getBrandIds(clientId);
					if (!request.getBrand().endsWith("*")) {
						for (BrandCcdb brnd : brandList) {
							if (StringUtils.equals(brnd.getBrand(), request.getBrand()))
								solrQueryRequired = false;
						}
						if (!solrQueryRequired) {
							messages.add(DATA_NOT_FOUND);
							response = (GetBrandsResponse) buildResponse(SUCCESS, messages, response);
							return response;
						}
					}
					// querying SOLR to fetch data based on the request params
					// with exclusion applied

					response = (GetBrandsResponse) solrServiceInvokerBO.getBrands(request, brandIdList);

					if (Integer.parseInt(response.getNumFound()) == 0) {
						response = (GetBrandsResponse) buildResponse(SUCCESS, messages, response);
						return response;
					}
					messages.add(SUCCESS_MSG);
					response.setMessages(messages);
					response.setProcessId(MDC.get(Constants.PROCESS_ID));
					response.setResponseCode(SUCCESS);
				}
			}

			return response;
		} catch (Exception ex) {
			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (GetBrandsResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}
	}

	/**
	 * Method : getSchematics Description : This method retrieves schematics of
	 * model or item based on provided modelNo/modelId/partNo or itemId.
	 * 
	 * @param :
	 *            String modelNo, String modelId, String partNo, String itemId
	 * @return : BaseResponse
	 */

	@ServiceAuditLogger(responseCode = "responseCode", responseMessage = "messages")
	@Override
	public BaseResponse getSchematics(String clientKey, String modelNo, String modelId, String responseFormat,
			String sortBy, UriInfo uriInfo) {

		GetSchematicsRequest request = new GetSchematicsRequest();
		GetSchematicsResponse response = new GetSchematicsResponse();
		List<String> messages = new ArrayList<String>();
		List<ItemAvailabilityStatusCcdb> availabilityStatusList = null;
		List<ProductGroupCcdb> productGroupList = null;
		List<ItemRestrictionCcdb> restrictionList = null;
		List<BrandCcdb> brandIdList = null;
		List<ProductTypeCcdb> productTypeList = null;
		List<String> definedParamaList = null;

		try {

			request = createGetSchematicsRequest(clientKey, modelNo, modelId, responseFormat, sortBy);

			// validating client, if invalid service will return from this point
			// itself

			response = (GetSchematicsResponse) validateClient(request.getClientKey(), GET_SCHEMATICS);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamaList = getDefinedParamList(ITEM_ID, PART_NO, null, MODEL_ID, MODEL_NO, null, null, null,
						null, null, null, null, null, null, SORT_BY, null, null);

				// validating request params, if invalid service will return
				// from this point itself

				response = (GetSchematicsResponse) validateRequest(request, GET_SCHEMATICS_REQUEST_VALIDATOR,
						GET_SCHEMATICS, uriInfo, definedParamaList);
				if (response.getResponseCode().equals(SUCCESS)) {

					// fetching exclusion data from client config DB

					Integer clientId = ClientUtils.getClientId();
					productGroupList = getProductGroups(clientId);
					restrictionList = getItemRestriction(clientId);
					availabilityStatusList = getItemAvailabilityStatus(clientId);
					brandIdList = getBrandIds(clientId);
					productTypeList = getProductTypes(clientId);
					BigDecimal itemSellPriceLimit = getItemSellPriceLimit(clientId);

					// querying SOLR to fetch data based on the request params
					// with exclusion applied

					response = (GetSchematicsResponse) solrServiceInvokerBO.getSchematics(request, productGroupList,
							availabilityStatusList, restrictionList, brandIdList, productTypeList, itemSellPriceLimit);
					if (Integer.parseInt(response.getNumFound()) == 0) {
						response = (GetSchematicsResponse) buildResponse(SUCCESS, messages, response);
						return response;
					}
					messages.add(SUCCESS_MSG);
					response.setMessages(messages);
					response.setProcessId(MDC.get(Constants.PROCESS_ID));
					response.setResponseCode(SUCCESS);
				}
			}

			return response;

		} catch (Exception ex) {
			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (GetSchematicsResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}
	}

	/**
	 * Method : getModelDetails Description : This method retrieves full detail
	 * of a particular model based on modelId. The response includes model, all
	 * the items that model have and model schematics. Items includes attributes
	 * and restriction also.
	 * 
	 * @param :
	 *            String modelId
	 * @return : BaseResponse
	 */

	@ServiceAuditLogger(responseCode = "responseCode", responseMessage = "messages")
	@Override
	public BaseResponse getModelDetails(String clientKey, String modelId, String responseFormat, UriInfo uriInfo) {

		GetModelDetailsRequest request = new GetModelDetailsRequest();
		GetModelDetailsResponse response = new GetModelDetailsResponse();
		List<String> messages = new ArrayList<String>();
		List<BrandCcdb> brandIdList = null;
		List<ProductTypeCcdb> productTypeList = null;
		List<ProductGroupCcdb> productGroupList = null;
		List<ItemAvailabilityStatusCcdb> availabilityStatusList = null;
		List<ItemAttributeCcdb> attributeList = null;
		List<ItemRestrictionCcdb> restrictionList = null;
		List<String> definedParamList = null;

		try {
			request = createGetModelDetailsRequest(clientKey, modelId, responseFormat);

			// validating client, if invalid service will return from this point
			// itself

			response = (GetModelDetailsResponse) validateClient(request.getClientKey(), GET_MODEL_DETAILS);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamList = getDefinedParamList(null, null, null, MODEL_ID, null, null, null, null, null, null,
						null, null, null, null, null, null, null);

				// validating request params, if invalid service will return
				// from this point itself

				response = (GetModelDetailsResponse) validateRequest(request, GET_MODEL_DETAILS_REQUEST_VALIDATOR,
						GET_MODEL_DETAILS, uriInfo, definedParamList);
				if (response.getResponseCode().equals(SUCCESS)) {

					// fetching exclusion data from client config DB

					Integer clientId = ClientUtils.getClientId();
					brandIdList = getBrandIds(clientId);
					productTypeList = getProductTypes(clientId);
					productGroupList = getProductGroups(clientId);
					availabilityStatusList = getItemAvailabilityStatus(clientId);
					attributeList = getItemAttributes(clientId);
					restrictionList = getItemRestriction(clientId);
					BigDecimal itemSellPriceLimit = getItemSellPriceLimit(clientId);

					// querying SOLR to fetch data based on the request params
					// with exclusion applied

					response = (GetModelDetailsResponse) solrServiceInvokerBO.getModelDetails(request, brandIdList,
							productTypeList, productGroupList, availabilityStatusList, attributeList, restrictionList,
							itemSellPriceLimit);

					if (Integer.parseInt(response.getNumFound()) == 0) {
						response = (GetModelDetailsResponse) buildResponse(SUCCESS, messages, response);
						return response;
					}
					messages.add(SUCCESS_MSG);
					response.setMessages(messages);
					response.setProcessId(MDC.get(Constants.PROCESS_ID));
					response.setResponseCode(SUCCESS);
					discountHelper.applyDiscount(response, clientKey);
				}
			}

			return response;
		} catch (Exception ex) {
			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (GetModelDetailsResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}
	}

	/**
	 * Method : getModelDetails Description : This method retrieves full detail
	 * of a particular model based on modelId. The response includes model, all
	 * the items that model have and model schematics. Items includes attributes
	 * and restriction also.
	 * 
	 * @param :
	 *            String modelId
	 * @return : BaseResponse
	 */

	// @Override
	// public BaseResponse getModelDetails(String clientKey, String modelId,
	// String responseFormat, UriInfo uriInfo) {
	//
	// long startTime = System.currentTimeMillis();
	// GetModelDetailsRequest request = new GetModelDetailsRequest();
	// GetModelDetailsResponse response = new GetModelDetailsResponse();
	// String processId = CommonUtils.getProcessId();
	// Map<String , String> requestParams = new HashMap<String, String>();
	// List<String> messages = new ArrayList<String>();
	// List<BrandCcdb> brandIdList = null;
	// List<ProductTypeCcdb> productTypeList = null;
	// List<ProductGroupCcdb> productGroupList = null;
	// List<ItemAvailabilityStatusCcdb> availabilityStatusList = null;
	// List<ItemAttributeCcdb> attributeList = null;
	// List<ItemRestrictionCcdb> restrictionList = null;
	// List<String> definedParamList = null;
	//
	// String GET_MODEL_DETAILS_ASYNC = "getModelDetailsASYNC";
	//
	// try{
	// requestParams = createRequestMapForLog( null, null, null, modelId, null,
	// null, null,
	// null, null, null, null, null, responseFormat, null, null, null, null );
	//
	// LogUtils.buildStartLog(clientKey, processId, GET_MODEL_DETAILS_ASYNC,
	// requestParams);
	//
	// request = createGetModelDetailsRequest(clientKey, modelId,
	// responseFormat);
	//
	// // validating client, if invalid service will return from this point
	// itself
	//
	// response = (GetModelDetailsResponse)
	// validateClient(request.getClientKey(),
	// GET_MODEL_DETAILS, processId);
	//
	// if(response.getResponseCode().equals(SUCCESS)){
	// definedParamList = getDefinedParamList(null, null, null, MODEL_ID, null,
	// null, null, null, null, null,
	// null, null, null, null, null, null);
	//
	// // validating request params, if invalid service will return from this
	// point itself
	//
	// response = (GetModelDetailsResponse) validateRequest(clientKey, request,
	// GET_MODEL_DETAILS_REQUEST_VALIDATOR,
	// GET_MODEL_DETAILS, processId, uriInfo, definedParamList);
	// if(response.getResponseCode().equals(SUCCESS)){
	//
	// // fetching exclusion data from client config DB
	//
	// Integer clientId = getClientId(clientKey);
	// brandIdList = getBrandIds(clientId);
	// productTypeList = getProductTypes(clientId);
	// productGroupList = getProductGroups(clientId);
	// availabilityStatusList = getItemAvailabilityStatus(clientId);
	// attributeList = getItemAttributes(clientId);
	// restrictionList = getItemRestriction(clientId);
	// BigDecimal itemSellPriceLimit = getItemSellPriceLimit(clientId);
	//
	// // querying SOLR to fetch data based on the request params with exclusion
	// applied
	//
	// response = (GetModelDetailsResponse)
	// solrServiceInvokerTaskBO.getModelDetails(processId, request, brandIdList,
	// productTypeList,
	// productGroupList, availabilityStatusList, attributeList, restrictionList,
	// itemSellPriceLimit);
	//
	// if(Integer.parseInt(response.getNumFound()) == 0){
	// response = (GetModelDetailsResponse) buildResponse(SUCCESS, messages,
	// processId, response);
	// LogUtils.buildInvalidRequestLog(clientKey, processId,
	// GET_MODEL_DETAILS_ASYNC,
	// response.getResponseCode(), response.getMessages());
	// return response;
	// }
	// messages.add(SUCCESS_MSG);
	// response.setMessages(messages);
	// response.setProcessId(processId);
	// response.setResponseCode(SUCCESS);
	//
	// LogUtils.buildFinishLog(clientKey, processId, GET_MODEL_DETAILS_ASYNC,
	// response.getResponseCode(),
	// response.getMessages(), Integer.parseInt(response.getNumFound()));
	// }
	// }
	//
	// return response;
	//
	// }catch(CcdbException ccdbEx){
	// if(response!=null)
	// response.getMessages().clear();
	// LogUtils.buildExceptionLog(clientKey, processId, GET_MODEL_DETAILS_ASYNC,
	// ccdbEx);
	// messages.add(SYSTEM_ERROR_MSG);
	// response = (GetModelDetailsResponse) buildResponse(SYSTEM_ERROR,
	// messages, processId, response);
	// return response;
	// }catch(SolrException solrEx){
	// response.getMessages().clear();
	// LogUtils.buildExceptionLog(clientKey, processId, GET_MODEL_DETAILS_ASYNC,
	// solrEx);
	// messages.add(SYSTEM_ERROR_MSG);
	// response = (GetModelDetailsResponse) buildResponse(SYSTEM_ERROR,
	// messages, processId, response);
	// return response;
	// }catch(Exception ex){
	// if(response!=null)
	// response.getMessages().clear();
	// LogUtils.buildExceptionLog(clientKey, processId, GET_MODEL_DETAILS_ASYNC,
	// ex);
	// messages.add(SYSTEM_ERROR_MSG);
	// response = (GetModelDetailsResponse) buildResponse(SYSTEM_ERROR,
	// messages, processId, response);
	// return response;
	// }finally {
	// long endTime = System.currentTimeMillis();
	// long responseTime = CommonUtils.getServiceResponseTime(startTime,
	// endTime);
	// LogUtils.buildFinallyLog(clientKey, processId, GET_MODEL_DETAILS_ASYNC,
	// responseTime);
	// }
	// }

	/**
	 * Method : getModelsForItem Description : This method retrieves models
	 * related to a particular item.
	 * 
	 * @param :
	 *            String itemId
	 * @return : BaseResponse
	 */
	@ServiceAuditLogger(responseCode = "responseCode", responseMessage = "messages")
	@Override
	public BaseResponse getModelsForItem(String clientKey, String itemId, String responseFormat, UriInfo uriInfo) {

		GetModelsForItemRequest request = new GetModelsForItemRequest();
		GetModelsForItemResponse response = new GetModelsForItemResponse();
		List<String> messages = new ArrayList<String>();
		List<BrandCcdb> brandIdList = null;
		List<ProductTypeCcdb> productTypeList = null;
		List<ItemAvailabilityStatusCcdb> availabilityStatusList = null;
		List<ProductGroupCcdb> productGroupList = null;
		List<ItemRestrictionCcdb> restrictionList = null;
		List<String> definedParamList = null;

		try {
			request = createGetModelsForItemRequest(clientKey, itemId, responseFormat);

			// validating client, if invalid service will return from this point
			// itself

			response = (GetModelsForItemResponse) validateClient(request.getClientKey(), GET_MODELS_FOR_ITEM);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamList = getDefinedParamList(ITEM_ID, PART_NO, PRODUCT_GROUP_ID, null, null, null, null, null,
						null, null, null, FORMATTED, STARTING_ROW, MAX_ROWS, SORT_BY, null, null);

				// validating request params, if invalid service will return
				// from this point itself

				response = (GetModelsForItemResponse) validateRequest(request, GET_MODELS_FOR_ITEM_REQUEST_VALIDATOR,
						GET_MODELS_FOR_ITEM, uriInfo, definedParamList);
				if (response.getResponseCode().equals(SUCCESS)) {

					// fetching exclusion data from client config DB

					Integer clientId = ClientUtils.getClientId();
					brandIdList = getBrandIds(clientId);
					productTypeList = getProductTypes(clientId);
					productGroupList = getProductGroups(clientId);
					restrictionList = getItemRestriction(clientId);
					availabilityStatusList = getItemAvailabilityStatus(clientId);
					BigDecimal itemSellPriceLimit = getItemSellPriceLimit(clientId);

					// querying SOLR to fetch data based on the request params
					// with exclusion applied

					response = (GetModelsForItemResponse) solrServiceInvokerBO.getModelsForItem(request, brandIdList,
							productTypeList, productGroupList, availabilityStatusList, restrictionList,
							itemSellPriceLimit);

					if (Integer.parseInt(response.getNumFound()) == 0) {
						response = (GetModelsForItemResponse) buildResponse(SUCCESS, messages, response);
						return response;
					}
					messages.add(SUCCESS_MSG);
					response.setMessages(messages);
					response.setProcessId(MDC.get(Constants.PROCESS_ID));
					response.setResponseCode(SUCCESS);
				}
			}

			return response;
		} catch (Exception ex) {
			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (GetModelsForItemResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}
	}

	/**
	 * Method : getItemDetails Description : This method retrieves details of a
	 * particular item. The response includes item, item schematics, attributes
	 * and restrictions
	 * 
	 * @param :
	 *            String itemId
	 * @return : BaseResponse
	 */
	@ServiceAuditLogger(responseCode = "responseCode", responseMessage = "messages")
	@Override
	public BaseResponse getItemDetails(String clientKey, String itemId, String responseFormat, UriInfo uriInfo) {

		GetItemDetailsRequest request = new GetItemDetailsRequest();
		GetItemDetailsResponse response = new GetItemDetailsResponse();
		List<String> messages = new ArrayList<String>();
		List<ProductGroupCcdb> productGroupList = null;
		List<ItemAvailabilityStatusCcdb> availabilityStatusList = null;
		List<ItemAttributeCcdb> attributeList = null;
		List<ItemRestrictionCcdb> restrictionList = null;
		List<String> definedParamList = null;

		try {

			request = createGetItemDetailsRequest(clientKey, itemId, responseFormat);

			// validating client, if invalid service will return from this point
			// itself

			response = (GetItemDetailsResponse) validateClient(request.getClientKey(), GET_ITEM_DETAILS);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamList = getDefinedParamList(ITEM_ID, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null);

				// validating request params, if invalid service will return
				// from this point itself

				response = (GetItemDetailsResponse) validateRequest(request, GET_ITEM_DETAILS_REQUEST_VALIDATOR,
						GET_ITEM_DETAILS, uriInfo, definedParamList);
				if (response.getResponseCode().equals(SUCCESS)) {
					// fetching exclusion data from client config DB

					Integer clientId = ClientUtils.getClientId();
					productGroupList = getProductGroups(clientId);
					availabilityStatusList = getItemAvailabilityStatus(clientId);
					attributeList = getItemAttributes(clientId);
					restrictionList = getItemRestriction(clientId);
					BigDecimal itemSellPriceLimit = getItemSellPriceLimit(clientId);

					// querying SOLR to fetch data based on the request params
					// with exclusion applied

					response = (GetItemDetailsResponse) solrServiceInvokerBO.getItemDetails(request, productGroupList,
							availabilityStatusList, attributeList, restrictionList, itemSellPriceLimit);

					if (Integer.parseInt(response.getNumFound()) == 0) {
						response = (GetItemDetailsResponse) buildResponse(SUCCESS, messages, response);
						return response;
					}
					messages.add(SUCCESS_MSG);
					response.setMessages(messages);
					response.setProcessId(MDC.get(Constants.PROCESS_ID));
					response.setResponseCode(SUCCESS);
					// HSPDME-391 apply discount
					discountHelper.applyDiscount(response, clientKey);
				}
			}

			return response;
		} catch (Exception ex) {
			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (GetItemDetailsResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}
	}

	/**
	 * Method : getItemList Description : This method retrieves large data set
	 * of items based on productGroupId.
	 * 
	 * @param :
	 *            String productGroupId
	 * @return : BaseResponse
	 */
	@ServiceAuditLogger(responseCode = "responseCode", responseMessage = "messages")
	@Override
	public GetItemListResponse getItemList(String clientKey, String productGroupId, String responseFormat,
			String startingRow, String maxRows, UriInfo uriInfo) {

		GetItemListRequest request = new GetItemListRequest();
		GetItemListResponse response = new GetItemListResponse();
		List<String> messages = new ArrayList<String>();
		List<ItemAvailabilityStatusCcdb> availabilityStatusList = null;
		List<ProductGroupCcdb> productGroupList = null;
		List<ItemRestrictionCcdb> restrictionList = null;
		List<String> definedParamList = null;

		try {

			request = createGetItemListRequest(clientKey, productGroupId, null, responseFormat, startingRow, maxRows);

			// validating client, if invalid service will return from this point
			// itself

			response = (GetItemListResponse) validateClient(request.getClientKey(), GET_ITEM_LIST);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamList = getDefinedParamList(null, null, PRODUCT_GROUP_ID, null, null, null, null, null, null,
						null, null, null, STARTING_ROW, MAX_ROWS, null, null, null);

				// validating request params, if invalid service will return
				// from this point itself

				response = (GetItemListResponse) validateRequest(request, GET_ITEM_LIST_REQUEST_VALIDATOR,
						GET_ITEM_LIST, uriInfo, definedParamList);
				if (response.getResponseCode().equals(SUCCESS)) {
					final String messg = String.format(RESPONSE_DESC.INVALID_MAXROWS_LESSTHAN_EQUALTO,
							getMaxRowsCacheDefault());
					if (response.getMessages().contains(messg)) {
						request.setMaxRows(getMaxRowsCacheDefault());
						messages.add(messg);
					}

					// fetching exclusion data from client config DB

					Integer clientId = ClientUtils.getClientId();
					productGroupList = getProductGroups(clientId);

					if (!request.getProductGroupId().endsWith("*")) {
						if (productGroupList.contains(request.getProductGroupId())) {
							messages.add(DATA_NOT_FOUND);
							response = (GetItemListResponse) buildResponse(SUCCESS, messages, response);
							return response;
						}
					}
					availabilityStatusList = getItemAvailabilityStatus(clientId);
					restrictionList = getItemRestriction(clientId);
					BigDecimal itemSellPriceLimit = getItemSellPriceLimit(clientId);

					// querying SOLR to fetch data based on the request params
					// with exclusion applied

					response = (GetItemListResponse) solrServiceInvokerBO.getItemList(request, productGroupList,
							availabilityStatusList, restrictionList, itemSellPriceLimit);

					if (Integer.parseInt(response.getNumFound()) == 0) {
						response = (GetItemListResponse) buildResponse(SUCCESS, messages, response);
						return response;
					}
					if (messages.isEmpty())
						messages.add(SUCCESS_MSG);
					response.setMessages(messages);
					response.setProcessId(MDC.get(Constants.PROCESS_ID));
					response.setResponseCode(SUCCESS);
					// HSPDME-391 apply discount
					discountHelper.applyDiscount(response, clientKey);
				}
			}

			return response;
		} catch (Exception ex) {
			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (GetItemListResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}

	}

	/**
	 * Method : getModelList Description : This method retrieves large data set
	 * of models based on parentProductTypeId. The result set can be filtered on
	 * brandId
	 * 
	 * @param :
	 *            String parentProductTypeId, String brandId
	 * @return : BaseResponse
	 */
	@ServiceAuditLogger(responseCode = "responseCode", responseMessage = "messages")
	@Override
	public BaseResponse getModelList(String clientKey, String parentProductTypeId, String brandId,
			String responseFormat, String startingRow, String maxRows, UriInfo uriInfo) {

		GetModelListRequest request = new GetModelListRequest();
		GetModelListResponse response = new GetModelListResponse();
		List<String> messages = new ArrayList<String>();
		List<BrandCcdb> brandIdList = null;
		List<ProductTypeCcdb> productTypes = null;
		List<String> definedParamList = null;

		try {
			request = createGetModelListRequest(clientKey, parentProductTypeId, brandId, null, responseFormat,
					startingRow, maxRows);

			// validating client, if invalid service will return from this point
			// itself

			response = (GetModelListResponse) validateClient(request.getClientKey(), GET_MODEL_LIST);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamList = getDefinedParamList(null, null, null, null, null, BRAND_ID, null,
						PARENT_PRODUCT_TYPE_ID, null, null, null, null, STARTING_ROW, MAX_ROWS, null, null, null);

				// validating request params, if invalid service will return
				// from this point itself

				response = (GetModelListResponse) validateRequest(request, GET_MODEL_LIST_REQUEST_VALIDATOR,
						GET_MODEL_LIST, uriInfo, definedParamList);
				if (response.getResponseCode().equals(SUCCESS)) {
					final String messg = String.format(RESPONSE_DESC.INVALID_MAXROWS_LESSTHAN_EQUALTO,
							getMaxRowsCacheDefault());
					if (response.getMessages().contains(messg)) {
						request.setMaxRows(getMaxRowsCacheDefault());
						messages.add(messg);
					}
					// fetching exclusion data from client config DB

					Integer clientId = ClientUtils.getClientId();
					brandIdList = getBrandIds(clientId);
					productTypes = getProductTypes(clientId);

					// querying SOLR to fetch data based on the request params
					// with exclusion applied

					response = (GetModelListResponse) solrServiceInvokerBO.getModelList(request, brandIdList,
							productTypes);
					if (Integer.parseInt(response.getNumFound()) == 0) {
						response = (GetModelListResponse) buildResponse(SUCCESS, messages, response);
						return response;
					}
					if (messages.isEmpty())
						messages.add(SUCCESS_MSG);
					response.setMessages(messages);
					response.setProcessId(MDC.get(Constants.PROCESS_ID));
					response.setResponseCode(SUCCESS);
				}
			}

			return response;
		} catch (Exception ex) {
			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (GetModelListResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}

	}

	/**
	 * Method : getBrandList Description : This method retrieves large data set
	 * of brands based on parentProductTypeId.
	 * 
	 * @param :
	 *            String parentProductTypeId
	 * @return : BaseResponse
	 */
	@ServiceAuditLogger(responseCode = "responseCode", responseMessage = "messages")
	@Override
	public GetBrandListResponse getBrandList(String clientKey, String parentProductTypeId, String responseFormat,
			String startingRow, String maxRows, UriInfo uriInfo) {

		GetBrandListRequest request = new GetBrandListRequest();
		GetBrandListResponse response = new GetBrandListResponse();
		List<String> messages = new ArrayList<String>();
		List<BrandCcdb> brandIdList = null;
		List<String> definedParamList = null;

		try {
			request = createGetBrandListRequest(clientKey, parentProductTypeId, null, responseFormat, startingRow,
					maxRows);

			// validating client, if invalid service will return from this point
			// itself

			response = (GetBrandListResponse) validateClient(request.getClientKey(), GET_BRAND_LIST);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamList = getDefinedParamList(null, null, null, null, null, null, null, PARENT_PRODUCT_TYPE_ID,
						null, null, null, null, STARTING_ROW, MAX_ROWS, null, null, null);

				// validating request params, if invalid service will return
				// from this point itself

				response = (GetBrandListResponse) validateRequest(request, GET_BRAND_LIST_REQUEST_VALIDATOR,
						GET_BRAND_LIST, uriInfo, definedParamList);
				if (response.getResponseCode().equals(SUCCESS)) {
					final String messg = String.format(RESPONSE_DESC.INVALID_MAXROWS_LESSTHAN_EQUALTO,
							getMaxRowsCacheDefault());
					if (response.getMessages().contains(messg)) {
						request.setMaxRows(getMaxRowsCacheDefault());
						messages.add(messg);
					}
					// fetching exclusion data from client config DB

					Integer clientId = ClientUtils.getClientId();
					brandIdList = getBrandIds(clientId);

					// querying SOLR to fetch data based on the request params
					// with exclusion applied

					response = (GetBrandListResponse) solrServiceInvokerBO.getBrandList(request, brandIdList);
					if (Integer.parseInt(response.getNumFound()) == 0) {
						response = (GetBrandListResponse) buildResponse(SUCCESS, messages, response);
						return response;
					}
					if (messages.isEmpty())
						messages.add(SUCCESS_MSG);
					response.setMessages(messages);
					response.setProcessId(MDC.get(Constants.PROCESS_ID));
					response.setResponseCode(SUCCESS);
				}
			}

			return response;
		} catch (Exception ex) {
			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (GetBrandListResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}

	}

	/**
	 * Method : getParentProductTypeList Description : This method retrieves all
	 * the parentProductTypes. The result set can be filtered based on brandId.
	 * 
	 * @param :
	 *            String parentProductTypeId
	 * @return : BaseResponse
	 */
	@ServiceAuditLogger(responseCode = "responseCode", responseMessage = "messages")
	@Override
	public BaseResponse getParentProductTypeList(String clientKey, String brandId, String responseFormat,
			String startingRow, String maxRows, UriInfo uriInfo) {

		GetParentProductTypeListRequest request = new GetParentProductTypeListRequest();
		GetParentProductTypeListResponse response = new GetParentProductTypeListResponse();
		List<String> messages = new ArrayList<String>();
		List<BrandCcdb> brandIdList = null;
		List<String> definedParamList = null;
		List<ProductTypeCcdb> productTypeList = null;

		try {

			request = createGetParentProductTypeListRequest(clientKey, brandId, null, responseFormat, startingRow,
					maxRows);

			// validating client, if invalid service will return from this point
			// itself

			response = (GetParentProductTypeListResponse) validateClient(request.getClientKey(),
					GET_PARENT_PRODUCT_TYPE_LIST);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamList = getDefinedParamList(null, null, null, null, null, BRAND_ID, null, null, null, null,
						null, null, STARTING_ROW, MAX_ROWS, null, null, null);

				// validating request params, if invalid service will return
				// from this point itself

				response = (GetParentProductTypeListResponse) validateRequest(request,
						GET_PARENT_PRODUCT_TYPE_LIST_REQUEST_VALIDATOR, GET_PARENT_PRODUCT_TYPE_LIST, uriInfo,
						definedParamList);
				if (response.getResponseCode().equals(SUCCESS)) {
					final String messg = String.format(RESPONSE_DESC.INVALID_MAXROWS_LESSTHAN_EQUALTO,
							getMaxRowsCacheDefault());
					if (response.getMessages().contains(messg)) {
						request.setMaxRows(getMaxRowsCacheDefault());
						messages.add(messg);
					}
					// fetching exclusion data from client config DB

					Integer clientId = getClientId(clientKey);
					brandIdList = getBrandIds(clientId);
					productTypeList = getProductTypes(clientId);

					if (request.getBrandId() != null && !request.getBrandId().endsWith("*")) {
						if (brandIdList.contains(request.getBrandId())) {
							messages.add(DATA_NOT_FOUND);
							response = (GetParentProductTypeListResponse) buildResponse(SUCCESS, messages, response);
							return response;
						}
					}

					// querying SOLR to fetch data based on the request params
					// with exclusion applied

					response = (GetParentProductTypeListResponse) solrServiceInvokerBO.getParentProductTypeList(request,
							brandIdList, productTypeList);

					if (Integer.parseInt(response.getNumFound()) == 0) {
						response = (GetParentProductTypeListResponse) buildResponse(SUCCESS, messages, response);
						return response;
					}
					if (messages.isEmpty())
						messages.add(SUCCESS_MSG);
					response.setMessages(messages);
					response.setProcessId(MDC.get(Constants.PROCESS_ID));
					response.setResponseCode(SUCCESS);
				}
			}

			return response;
		} catch (Exception ex) {
			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (GetParentProductTypeListResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}

	}

	/**
	 * Method : getProductGroupList Description : This method retrieves all the
	 * productGroups.
	 * 
	 * @return : BaseResponse
	 */
	@ServiceAuditLogger(responseCode = "responseCode", responseMessage = "messages")
	@Override
	public BaseResponse getProductGroupList(String clientKey, String responseFormat, String startingRow, String maxRows,
			UriInfo uriInfo) {

		GetProductGroupListRequest request = new GetProductGroupListRequest();
		GetProductGroupListResponse response = new GetProductGroupListResponse();
		List<String> messages = new ArrayList<String>();
		List<String> definedParamList = null;
		List<ItemAvailabilityStatusCcdb> availabilityStatusList = null;
		List<ProductGroupCcdb> productGroupList = null;
		List<ItemRestrictionCcdb> restrictionList = null;

		try {

			request = createGetProductGroupListRequest(clientKey, null, responseFormat, startingRow, maxRows);

			// validating client, if invalid service will return from this point
			// itself

			response = (GetProductGroupListResponse) validateClient(request.getClientKey(), GET_PRODUCT_GROUP_LIST);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamList = getDefinedParamList(null, null, null, null, null, null, null, null, null, null, null,
						null, STARTING_ROW, MAX_ROWS, null, null, null);

				// validating request params, if invalid service will return
				// from this point itself

				response = (GetProductGroupListResponse) validateRequest(request,
						GET_PRODUCT_GROUP_LIST_REQUEST_VALIDATOR, GET_PRODUCT_GROUP_LIST, uriInfo, definedParamList);
				if (response.getResponseCode().equals(SUCCESS)) {
					final String messg = String.format(RESPONSE_DESC.INVALID_MAXROWS_LESSTHAN_EQUALTO,
							getMaxRowsCacheDefault());
					if (response.getMessages().contains(messg)) {
						request.setMaxRows(getMaxRowsCacheDefault());
						messages.add(messg);
					}
					// fetching exclusion data from client config DB
					Integer clientId = ClientUtils.getClientId();
					productGroupList = getProductGroups(clientId);
					restrictionList = getItemRestriction(clientId);
					availabilityStatusList = getItemAvailabilityStatus(clientId);
					BigDecimal itemSellPriceLimit = getItemSellPriceLimit(clientId);

					// querying SOLR to fetch data based on the request params
					// with exclusion applied

					response = (GetProductGroupListResponse) solrServiceInvokerBO.getProductGroupList(request,
							productGroupList, availabilityStatusList, restrictionList, itemSellPriceLimit);
					if (Integer.parseInt(response.getNumFound()) == 0) {
						response = (GetProductGroupListResponse) buildResponse(SUCCESS, messages, response);
						return response;
					}
					if (messages.isEmpty())
						messages.add(SUCCESS_MSG);
					response.setMessages(messages);
					response.setProcessId(MDC.get(Constants.PROCESS_ID));
					response.setResponseCode(SUCCESS);
				}
			}

			return response;
		} catch (Exception ex) {
			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (GetProductGroupListResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}
	}

	/**
	 * Method : createItemSearchRequest Description : This method creates
	 * itemSearchRequest
	 * 
	 * @return : ItemSearchRequest
	 */
	private ItemSearchRequest createItemSearchRequest(String clientKey, String itemId, String partNo,
			String responseFormat, String formatted, String startingRow, String maxRows, String sortBy,
			String productGroupId) {

		ItemSearchRequest request = new ItemSearchRequest();

		if (!StringUtils.isEmpty(clientKey))
			request.setClientKey(clientKey.trim());
		if (!StringUtils.isEmpty(productGroupId))
			request.setProductGroupId(productGroupId.trim());

		if (!StringUtils.isEmpty(formatted)) {
			if (formatted.equalsIgnoreCase("Y")) {
				request.setFormatted(formatted.trim());
				if (!StringUtils.isEmpty(itemId)) {
					itemId = removeSpecialChars(itemId.trim());
					request.setItemId(itemId);
				}
				if (!StringUtils.isEmpty(partNo)) {
					partNo = removeSpecialChars(partNo.trim());
					request.setPartNo(partNo);
				}
			} else {
				request.setFormatted(formatted.trim());
				if (!StringUtils.isEmpty(itemId))
					request.setItemId(itemId.trim());
				if (!StringUtils.isEmpty(partNo))
					request.setPartNo(partNo.trim());
			}
		} else {
			request.setFormatted("N");
			if (!StringUtils.isEmpty(partNo))
				request.setPartNo(partNo.trim());
			if (!StringUtils.isEmpty(itemId))
				request.setItemId(itemId.trim());
		}
		if (!StringUtils.isEmpty(responseFormat))
			request.setResponseFormat(responseFormat.trim());
		else
			request.setResponseFormat(XML);
		if (!StringUtils.isEmpty(sortBy))
			request.setSortBy(sortBy.trim());
		else
			request.setSortBy(REQUEST_FIELDS.PART_NO_ASC);
		if (!StringUtils.isEmpty(startingRow))
			request.setStartingRow(startingRow.trim());
		else
			request.setStartingRow(String.valueOf(STARTING_ROW_DEFAULT));
		if (!StringUtils.isEmpty(maxRows))
			request.setMaxRows(maxRows.trim());
		else
			request.setMaxRows(getMaxRowsDefault());

		return request;
	}

	private ItemDescriptionSearchRequest createDescriptionSearchRequest(String clientKey, String brand,
			String productType, String modelNo, String description, String responseFormat, String startingRow,
			String maxRows, String sortBy) {

		ItemDescriptionSearchRequest request = new ItemDescriptionSearchRequest();

		if (StringUtils.isNotBlank(clientKey))
			request.setClientKey(clientKey.trim());

		if (StringUtils.isNotBlank(brand))
			request.setBrand(brand.trim());

		if (StringUtils.isNotBlank(productType))
			request.setProductType(productType.trim());

		if (StringUtils.isNotBlank(modelNo))
			request.setModelNo(modelNo.trim());

		if (StringUtils.isNotBlank(description))
			request.setDescription(description.trim());

		if (StringUtils.isNotBlank(responseFormat))
			request.setResponseFormat(responseFormat.trim());
		else
			request.setResponseFormat(XML);

		if (StringUtils.isNotBlank(sortBy))
			request.setSortBy(sortBy.trim());
		else
			request.setSortBy(REQUEST_FIELDS.PART_NO_ASC);

		if (StringUtils.isNotBlank(startingRow))
			request.setStartingRow(startingRow.trim());
		else
			request.setStartingRow(String.valueOf(STARTING_ROW_DEFAULT));

		if (StringUtils.isNotBlank(maxRows))
			request.setMaxRows(maxRows.trim());
		else
			request.setMaxRows(getMaxRowsDefault());

		return request;
	}

	/**
	 * Method : createModelSearchRequest Description : This method creates
	 * modelSearchRequest
	 * 
	 * @return : ModelSearchRequest
	 */
	private ModelSearchRequest createModelSearchRequest(String clientKey, String modelNo, String modelId,
			String responseFormat, String brand, String formatted, String startingRow, String maxRows,
			String parentProductTypeId, String productTypeId, String subProductTypeId, String sortBy, String facetBy) {

		ModelSearchRequest request = new ModelSearchRequest();

		if (!StringUtils.isEmpty(clientKey))
			request.setClientKey(clientKey.trim());
		if (!StringUtils.isEmpty(brand))
			request.setBrand(brand.trim());
		if (!StringUtils.isEmpty(parentProductTypeId))
			request.setParentProductTypeId(parentProductTypeId.trim());
		if (!StringUtils.isEmpty(productTypeId))
			request.setProductTypeId(productTypeId.trim());
		if (!StringUtils.isEmpty(subProductTypeId))
			request.setSubProductTypeId(subProductTypeId.trim());

		if (!StringUtils.isEmpty(formatted)) {
			if (formatted.equalsIgnoreCase("Y")) {
				request.setFormatted(formatted.trim());
				if (!StringUtils.isEmpty(modelNo)) {
					modelNo = removeSpecialChars(modelNo.trim());
					request.setModelNo(modelNo);
				}
				if (!StringUtils.isEmpty(modelId)) {
					modelId = removeSpecialChars(modelId.trim());
					request.setModelId(modelId);
				}
			} else {
				request.setFormatted(formatted.trim());
				if (!StringUtils.isEmpty(modelId))
					request.setModelId(modelId.trim());
				if (!StringUtils.isEmpty(modelNo))
					request.setModelNo(modelNo.trim());
			}
		} else {
			request.setFormatted("N");
			if (!StringUtils.isEmpty(modelId))
				request.setModelId(modelId.trim());
			if (!StringUtils.isEmpty(modelNo))
				request.setModelNo(modelNo.trim());
		}
		if (!StringUtils.isEmpty(responseFormat))
			request.setResponseFormat(responseFormat.trim());
		else
			request.setResponseFormat(XML);
		if (!StringUtils.isEmpty(startingRow))
			request.setStartingRow(startingRow.trim());
		else
			request.setStartingRow(String.valueOf(STARTING_ROW_DEFAULT));
		if (!StringUtils.isEmpty(maxRows))
			request.setMaxRows(maxRows.trim());
		else
			request.setMaxRows(getMaxRowsDefault());
		if (!StringUtils.isEmpty(sortBy))
			request.setSortBy(sortBy.trim());
		else
			request.setSortBy(REQUEST_FIELDS.MODEL_NO_ASC);

		if (!StringUtils.isEmpty(facetBy)) {
			request.setFacetBy(facetBy);
		}

		return request;
	}

	/**
	 * Method : createFullSearchRequest Description : This method creates
	 * FullSearchRequest
	 * 
	 * @return : FullSearchRequest
	 */
	private FullSearchRequest createFullSearchRequest(String clientKey, String key, String responseFormat,
			String formatted, String startingRow, String maxRows, String sortBy) {

		FullSearchRequest request = new FullSearchRequest();

		if (!StringUtils.isEmpty(clientKey))
			request.setClientKey(clientKey.trim());

		if (!StringUtils.isEmpty(formatted)) {
			if (formatted.equalsIgnoreCase("Y")) {
				request.setFormatted(formatted.trim());
				if (!StringUtils.isEmpty(key)) {
					key = removeSpecialChars(key.trim());
					request.setKey(key);
				}
			} else {
				request.setFormatted(formatted.trim());
				if (!StringUtils.isEmpty(key))
					request.setKey(key.trim());
			}
		} else {
			request.setFormatted("N");
			if (!StringUtils.isEmpty(key))
				request.setKey(key.trim());
		}
		if (!StringUtils.isEmpty(responseFormat))
			request.setResponseFormat(responseFormat.trim());
		else
			request.setResponseFormat(XML);
		if (!StringUtils.isEmpty(startingRow))
			request.setStartingRow(startingRow.trim());
		else
			request.setStartingRow(String.valueOf(STARTING_ROW_DEFAULT));
		if (!StringUtils.isEmpty(maxRows))
			request.setMaxRows(maxRows.trim());
		else
			request.setMaxRows(getMaxRowsDefault());
		if (!StringUtils.isEmpty(sortBy))
			request.setSortBy(sortBy.trim());
		else
			request.setSortBy(REQUEST_FIELDS.PART_NO_MODEL_ASC);

		return request;
	}

	/**
	 * Method : createGetBrandsRequest Description : This method creates
	 * GetBrandsRequest
	 * 
	 * @return : GetBrandsRequest
	 */
	private GetBrandsRequest createGetBrandsRequest(String clientKey, String brand, String responseFormat,
			String sortBy) {

		GetBrandsRequest request = new GetBrandsRequest();

		if (!StringUtils.isEmpty(clientKey))
			request.setClientKey(clientKey.trim());
		if (!StringUtils.isEmpty(brand))
			request.setBrand(brand.trim());
		if (!StringUtils.isEmpty(responseFormat))
			request.setResponseFormat(responseFormat.trim());
		else
			request.setResponseFormat(XML);
		if (!StringUtils.isEmpty(sortBy)) {
			request.setSortBy(sortBy.trim());
		} else
			request.setSortBy(REQUEST_FIELDS.BRAND_ASC);

		return request;
	}

	/**
	 * Method : createGetSchematicsRequest Description : This method creates
	 * GetSchematicsRequest
	 * 
	 * @return : GetSchematicsRequest
	 */
	private GetSchematicsRequest createGetSchematicsRequest(String clientKey, String modelNo, String modelId,
			String responseFormat, String sortBy) {

		GetSchematicsRequest request = new GetSchematicsRequest();

		if (!StringUtils.isEmpty(clientKey))
			request.setClientKey(clientKey.trim());
		if (!StringUtils.isEmpty(modelNo))
			request.setModelNo(modelNo.trim());
		if (!StringUtils.isEmpty(modelId))
			request.setModelId(modelId.trim());
		if (!StringUtils.isEmpty(responseFormat))
			request.setResponseFormat(responseFormat.trim());
		else
			request.setResponseFormat(XML);

		if (!StringUtils.isEmpty(sortBy))
			request.setSortBy(sortBy.trim());
		else
			request.setSortBy(REQUEST_FIELDS.SCHEMATIC_NAME_ASC);

		return request;
	}

	/**
	 * Method : createGetItemListRequest Description : This method creates
	 * GetItemListRequest
	 * 
	 * @return : GetItemListRequest
	 */
	private GetItemListRequest createGetItemListRequest(String clientKey, String productGroupId, String versionDate,
			String responseFormat, String startingRow, String maxRows) {
		GetItemListRequest request = new GetItemListRequest();

		if (!StringUtils.isEmpty(clientKey))
			request.setClientKey(clientKey.trim());
		if (productGroupId != null && !(productGroupId.isEmpty()))
			request.setProductGroupId(productGroupId.trim());
		if (!StringUtils.isEmpty(versionDate))
			request.setVersionDate(versionDate.trim());
		if (!StringUtils.isEmpty(responseFormat))
			request.setResponseFormat(responseFormat.trim());
		else
			request.setResponseFormat(XML);
		if (!StringUtils.isEmpty(startingRow))
			request.setStartingRow(startingRow.trim());
		else
			request.setStartingRow(String.valueOf(STARTING_ROW_DEFAULT));
		if (!StringUtils.isEmpty(maxRows))
			request.setMaxRows(maxRows.trim());
		else
			request.setMaxRows(getMaxRowsCacheDefault());

		return request;
	}

	/**
	 * Method : createGetModelListRequest Description : This method creates
	 * GetModelListRequest
	 * 
	 * @return : GetModelListRequest
	 */
	private GetModelListRequest createGetModelListRequest(String clientKey, String parentProductTypeId, String brandId,
			String versionDate, String responseFormat, String startingRow, String maxRows) {
		GetModelListRequest request = new GetModelListRequest();

		if (!StringUtils.isEmpty(clientKey))
			request.setClientKey(clientKey.trim());
		if (parentProductTypeId != null && !(parentProductTypeId.isEmpty()))
			request.setParentProductTypeId(parentProductTypeId.trim());
		if (brandId != null && !(brandId.isEmpty()))
			request.setBrandId(brandId.trim());
		if (!StringUtils.isEmpty(versionDate))
			request.setVersionDate(versionDate.trim());
		if (!StringUtils.isEmpty(responseFormat))
			request.setResponseFormat(responseFormat.trim());
		else
			request.setResponseFormat(XML);
		if (!StringUtils.isEmpty(startingRow))
			request.setStartingRow(startingRow.trim());
		else
			request.setStartingRow(String.valueOf(STARTING_ROW_DEFAULT));
		if (!StringUtils.isEmpty(maxRows))
			request.setMaxRows(maxRows.trim());
		else
			request.setMaxRows(getMaxRowsCacheDefault());

		return request;
	}

	/**
	 * Method : createGetBrandListRequest Description : This method creates
	 * GetBrandListRequest
	 * 
	 * @return : GetBrandListRequest
	 */
	private GetBrandListRequest createGetBrandListRequest(String clientKey, String parentProductTypeId,
			String versionDate, String responseFormat, String startingRow, String maxRows) {
		GetBrandListRequest request = new GetBrandListRequest();

		if (!StringUtils.isEmpty(clientKey))
			request.setClientKey(clientKey.trim());
		if (parentProductTypeId != null && !(parentProductTypeId.isEmpty()))
			request.setParentProductTypeId(parentProductTypeId.trim());
		if (!StringUtils.isEmpty(versionDate))
			request.setVersionDate(versionDate.trim());
		if (!StringUtils.isEmpty(responseFormat))
			request.setResponseFormat(responseFormat.trim());
		else
			request.setResponseFormat(XML);
		if (!StringUtils.isEmpty(startingRow))
			request.setStartingRow(startingRow.trim());
		else
			request.setStartingRow(String.valueOf(STARTING_ROW_DEFAULT));
		if (!StringUtils.isEmpty(maxRows))
			request.setMaxRows(maxRows.trim());
		else
			request.setMaxRows(getMaxRowsCacheDefault());

		return request;
	}

	/**
	 * Method : createGetParentProductTypeListRequest Description : This method
	 * creates GetParentProductTypeListRequest
	 * 
	 * @return : GetParentProductTypeListRequest
	 */
	private GetParentProductTypeListRequest createGetParentProductTypeListRequest(String clientKey, String brandId,
			String versionDate, String responseFormat, String startingRow, String maxRows) {
		GetParentProductTypeListRequest request = new GetParentProductTypeListRequest();

		if (!StringUtils.isEmpty(clientKey))
			request.setClientKey(clientKey.trim());
		if (brandId != null && !(brandId.isEmpty()))
			request.setBrandId(brandId.trim());
		if (!StringUtils.isEmpty(versionDate))
			request.setVersionDate(versionDate.trim());
		if (!StringUtils.isEmpty(responseFormat))
			request.setResponseFormat(responseFormat.trim());
		else
			request.setResponseFormat(XML);
		if (!StringUtils.isEmpty(startingRow))
			request.setStartingRow(startingRow.trim());
		else
			request.setStartingRow(String.valueOf(STARTING_ROW_DEFAULT));
		if (!StringUtils.isEmpty(maxRows))
			request.setMaxRows(maxRows.trim());
		else
			request.setMaxRows(getMaxRowsCacheDefault());

		return request;
	}

	/**
	 * Method : createGetProductGroupListRequest Description : This method
	 * creates GetProductGroupListRequest
	 * 
	 * @return : GetProductGroupListRequest
	 */
	private GetProductGroupListRequest createGetProductGroupListRequest(String clientKey, String versionDate,
			String responseFormat, String startingRow, String maxRows) {
		GetProductGroupListRequest request = new GetProductGroupListRequest();

		if (!StringUtils.isEmpty(clientKey))
			request.setClientKey(clientKey.trim());
		if (!StringUtils.isEmpty(versionDate))
			request.setVersionDate(versionDate.trim());
		if (!StringUtils.isEmpty(responseFormat))
			request.setResponseFormat(responseFormat.trim());
		else
			request.setResponseFormat(XML);
		if (!StringUtils.isEmpty(startingRow))
			request.setStartingRow(startingRow.trim());
		else
			request.setStartingRow(String.valueOf(STARTING_ROW_DEFAULT));
		if (!StringUtils.isEmpty(maxRows))
			request.setMaxRows(maxRows.trim());
		else
			request.setMaxRows(getMaxRowsCacheDefault());

		return request;
	}

	/**
	 * Method : createGetModelDetailsRequest Description : This method creates
	 * GetModelDetailsRequest
	 * 
	 * @return : GetModelDetailsRequest
	 */
	private GetModelDetailsRequest createGetModelDetailsRequest(String clientKey, String modelId,
			String responseFormat) {
		GetModelDetailsRequest request = new GetModelDetailsRequest();

		if (!StringUtils.isEmpty(clientKey))
			request.setClientKey(clientKey.trim());
		if (!StringUtils.isEmpty(modelId))
			request.setModelId(modelId.trim());
		if (!StringUtils.isEmpty(responseFormat))
			request.setResponseFormat(responseFormat.trim());
		else
			request.setResponseFormat(XML);

		return request;
	}

	/**
	 * Method : createGetModelsForItemRequest Description : This method creates
	 * GetModelsForItemRequest
	 * 
	 * @return : GetModelsForItemRequest
	 */
	private GetModelsForItemRequest createGetModelsForItemRequest(String clientKey, String itemId,
			String responseFormat) {

		GetModelsForItemRequest request = new GetModelsForItemRequest();

		if (!StringUtils.isEmpty(clientKey))
			request.setClientKey(clientKey.trim());
		if (itemId != null && !itemId.isEmpty())
			request.setItemId(itemId.trim());
		if (!StringUtils.isEmpty(responseFormat))
			request.setResponseFormat(responseFormat.trim());
		else
			request.setResponseFormat(XML);

		return request;
	}

	/**
	 * Method : createGetItemDetailsRequest Description : This method creates
	 * GetItemDetailsRequest
	 * 
	 * @return : GetItemDetailsRequest
	 */
	private GetItemDetailsRequest createGetItemDetailsRequest(String clientKey, String itemId, String responseFormat) {

		GetItemDetailsRequest request = new GetItemDetailsRequest();

		if (!StringUtils.isEmpty(clientKey))
			request.setClientKey(clientKey.trim());
		if (itemId != null && !itemId.isEmpty())
			request.setItemId(itemId.trim());
		if (!StringUtils.isEmpty(responseFormat))
			request.setResponseFormat(responseFormat.trim());
		else
			request.setResponseFormat(XML);

		return request;
	}

	private AccessoryRequest createAccessoryRequest(String clientKey, String modelNo, String brandId,
			String productTypeId, String responseFormat, String maxRows) {

		AccessoryRequest request = new AccessoryRequest();

		if (StringUtils.isNotEmpty(clientKey))
			request.setClientKey(clientKey.trim());

		if (StringUtils.isNotEmpty(responseFormat))
			request.setResponseFormat(responseFormat.trim());
		else
			request.setResponseFormat(XML);

		if (StringUtils.isNotEmpty(maxRows))
			request.setMaxRows(maxRows.trim());
		else
			request.setMaxRows(getMaxRowsDefault());

		if (StringUtils.isNotEmpty(brandId))
			request.setBrandId(brandId.trim());

		if (StringUtils.isNotEmpty(productTypeId))
			request.setProductTypeId(productTypeId.trim());

		if (StringUtils.isNotEmpty(modelNo))
			request.setModelNo(modelNo.trim());

		return request;
	}

	/*
	 * private GetCoreAndEnvChargeAmountRequest
	 * createCoreAndEnvChargeAmountRequest(String clientKey, String transCode,
	 * String stateCode, String divNo, String responseFormat, String plsNo,
	 * String partNo) {
	 * 
	 * GetCoreAndEnvChargeAmountRequest request = new
	 * GetCoreAndEnvChargeAmountRequest();
	 * 
	 * if (!StringUtils.isEmpty(clientKey))
	 * request.setClientKey(clientKey.trim());
	 * 
	 * if (!StringUtils.isEmpty(stateCode))
	 * request.setStateCode(stateCode.trim());
	 * 
	 * if (!StringUtils.isEmpty(divNo))
	 * request.setDivNo(StringUtils.leftPad(divNo.trim(), 4, '0'));
	 * 
	 * if (!StringUtils.isEmpty(plsNo)) request.setPlsNo(plsNo.trim());
	 * 
	 * if (!StringUtils.isEmpty(partNo)) request.setPartNo(partNo.trim());
	 * 
	 * if (StringUtils.isNotBlank(responseFormat))
	 * request.setResponseFormat(responseFormat.trim()); else
	 * request.setResponseFormat(GEN_CONST.JSON);
	 * 
	 * return request; }
	 */

	/**
	 * Method : removeSpecialChars Description : This method removes special
	 * characters
	 * 
	 * @return : String
	 */
	private static String removeSpecialChars(String value) {
		return value.replaceAll("[-/,#().&+\"=\\\\_~!@$%^<>?;:\\[\\]{}|`' ]", "");
	}

	/**
	 * Method : createRequestMapForLog Description : This method creates map of
	 * request params and values, this map is further used for logging the
	 * request params.
	 * 
	 * @return : Map<String, String>
	 */
	/*
	 * private Map<String, String> createRequestMapForLog(String itemId, String
	 * partNo, String modelNo, String modelId, String brand, String brandId,
	 * String productGroupId, String parentProductTypeId, String productTypeId,
	 * String subProductTypeId, String key, String versionDate, String
	 * responseFormat, String formatted, String startingRow, String maxRows,
	 * String sortBy ){
	 * 
	 * Map<String, String> requestParams = new HashMap<String, String>();
	 * 
	 * requestParams.put(PART_NO, partNo); requestParams.put(ITEM_ID, itemId);
	 * requestParams.put(MODEL_NO, modelNo); requestParams.put(MODEL_ID,
	 * modelId); requestParams.put(BRAND, brand); requestParams.put(BRAND_ID,
	 * brandId); requestParams.put(PRODUCT_GROUP_ID, productGroupId);
	 * requestParams.put(PARENT_PRODUCT_TYPE_ID, parentProductTypeId);
	 * requestParams.put(PRODUCT_TYPE_ID, productTypeId);
	 * requestParams.put(SUB_PRODUCT_TYPE_ID, subProductTypeId);
	 * requestParams.put(KEY, key); requestParams.put(VERSION_DATE,
	 * versionDate); requestParams.put(RESPONSE_FORMAT, responseFormat);
	 * requestParams.put(FORMATTED, formatted); requestParams.put(STARTING_ROW,
	 * startingRow); requestParams.put(MAX_ROWS, maxRows);
	 * requestParams.put(SORT_BY, sortBy);
	 * 
	 * return requestParams; }
	 */
	/**
	 * Method : validateClient Description : This method validates client. It
	 * checks if the client is present in client config DB and active.
	 * 
	 * @return : BaseResponse
	 */
	private BaseResponse validateClient(String clientKey, String methodName) throws Exception {
		boolean isClientValid = false;
		List<String> messages = new ArrayList<String>();

		ResponseFactory responseFactory = new ResponseFactory();
		BaseResponse response = responseFactory.getResponse(methodName);

		if (clientKey != null) {
			isClientValid = ClientUtils.isClientActive();
		} else {
			logger.info("Invalid client. API-Key: {}", clientKey);
			messages.add(String.format(REQUIRED_FIELD_MISSING, API_KEY));
			response = buildResponse(DATA_VALIDATION_ERROR, messages, response);
			return response;
		}
		if (!isClientValid) {
			logger.info("Invalid client. API-Key: {}", clientKey);
			messages.add(CLIENT_INVALID);
			response = buildResponse(DATA_VALIDATION_ERROR, messages, response);
			return response;
		}
		logger.trace("Valid client");

		messages.add(SUCCESS_MSG);
		response.setMessages(messages);
		response.setProcessId(MDC.get(Constants.PROCESS_ID));
		response.setResponseCode(SUCCESS);

		return response;
	}

	/**
	 * Method : validateRequest Description : This method validates request. It
	 * validates mandatory fields, length, default values, alpha space and if
	 * wildcard can be applied on fields.
	 * 
	 * @return : BaseResponse
	 */
	@SuppressWarnings("unchecked")
	private BaseResponse validateRequest(BaseRequest request, String validatorConst, String methodName, UriInfo uriInfo,
			List<String> paramList) throws Exception {

		Validator validator = null;
		ValidatorOutput validatorOutput = null;

		ResponseFactory responseFactory = new ResponseFactory();
		BaseResponse response = responseFactory.getResponse(methodName);

		validator = validatorContainer.getValidator(validatorConst);
		validatorOutput = validator.validate(request);

		if (validatorOutput != null && !StringUtils.equals(validatorOutput.getReturnCode(), SUCCESS)) {
			logger.info("Invalid request");
			response = buildResponse(validatorOutput.getReturnCode(), validatorOutput.getMessages(), response);
			return response;
		}

		response.setMessages(validatorOutput.getMessages());
		response.setProcessId(MDC.get(Constants.PROCESS_ID));
		response.setResponseCode(validatorOutput.getReturnCode());
		logger.trace("Valid request");
		return response;
	}

	/**
	 * Method : getProductGroups Description : This method fetches ProductGroups
	 * from client config DB.
	 * 
	 * @return : List<ProductGroupCcdb>
	 */
	private Integer getClientId(String clientKey) throws Exception {
		logger.trace("Fetching ClientId from ClientConfigDB");
		return clientConfigBO.getClientId(clientKey);
	}

	/**
	 * Method : getProductGroups Description : This method fetches ProductGroups
	 * from client config DB.
	 * 
	 * @return : List<ProductGroupCcdb>
	 */
	private List<ProductGroupCcdb> getProductGroups(Integer clientId) throws Exception {
		logger.trace("Fetching ProductGroups from ClientConfigDB");
		return clientConfigBO.getProductGroup(clientId);
	}

	/**
	 * Method : getItemAvailabilityStatus Description : This method fetches
	 * ItemAvailabilityStatus from client config DB.
	 * 
	 * @return : List<ItemAvailabilityStatusCcdb>
	 */
	private List<ItemAvailabilityStatusCcdb> getItemAvailabilityStatus(Integer clientId) throws Exception {
		logger.trace("Fetching AvailabilityStatus from ClientConfigDB");
		return clientConfigBO.getItemAvailabilityStatus(clientId);
	}

	/**
	 * Method : getBrandIds Description : This method fetches BrandIds from
	 * client config DB.
	 * 
	 * @return : List<BrandCcdb>
	 */
	private List<BrandCcdb> getBrandIds(Integer clientId) throws Exception {
		logger.trace("Fetching BrandIds from ClientConfigDB");
		return clientConfigBO.getBrandIds(clientId);
	}

	/**
	 * Method : getBrands Description : This method fetches Brands from client
	 * config DB.
	 * 
	 * @return : List<BrandCcdb>
	 */
	private List<BrandCcdb> getBrands(Integer clientId) throws Exception {
		logger.trace("Fetching Brands from ClientConfigDB");
		return clientConfigBO.getBrands(clientId);
	}

	/**
	 * Method : getProductTypes Description : This method fetches ProductTypes
	 * from client config DB.
	 * 
	 * @return : List<ProductTypeCcdb>
	 */
	private List<ProductTypeCcdb> getProductTypes(Integer clientId) throws Exception {
		logger.trace("Fetching ProductTypes Status from ClientConfigDB");
		return clientConfigBO.getProductType(clientId);
	}

	/**
	 * Method : getItemAttributes Description : This method fetches
	 * ItemAttributes from client config DB.
	 * 
	 * @return : List<ItemAttributeCcdb>
	 */
	private List<ItemAttributeCcdb> getItemAttributes(Integer clientId) throws Exception {
		logger.trace("Fetching ItemAttributes from ClientConfigDB");
		return clientConfigBO.getAttributes(clientId);
	}

	/**
	 * Method : getItemRestriction Description : This method fetches
	 * itemRestriction from client config DB.
	 * 
	 * @return : List<ItemRestrictionCcdb>
	 */
	private List<ItemRestrictionCcdb> getItemRestriction(Integer clientId) throws Exception {
		logger.trace("Fetching ItemRestrictions from ClientConfigDB");
		return clientConfigBO.getRestrictions(clientId);
	}

	/**
	 * Method : buildResponse Description : This method creates BaseResponse
	 * 
	 * @return : BaseResponse
	 */
	private BaseResponse buildResponse(String returnCode, List<String> messages, BaseResponse response) {
		response.setResponseCode(returnCode);
		if (response.getMessages() != null && !response.getMessages().isEmpty())
			response.getMessages().addAll(messages);
		else {
			List<String> list = new ArrayList<String>();
			list.addAll(messages);
			response.setMessages(list);
		}
		response.setProcessId(MDC.get(Constants.PROCESS_ID));

		return response;
	}

	/**
	 * Method : getMaxRowsDefault Description : This method retrieves default
	 * value of MaxRows which is set in the property file
	 * 
	 * @return : BaseResponse
	 */
	private String getMaxRowsDefault() {
		return maxRowsDefault.trim();
	}

	/**
	 * Method : getMaxRowsCacheDefault Description : This method retrieves
	 * default value of MaxRows of cache api which is set in the property file
	 * 
	 * @return : BaseResponse
	 */
	private String getMaxRowsCacheDefault() {
		return maxRowsCacheDefault.trim();
	}

	/**
	 * Method : getDefinedParamList Description : This method checks what are
	 * the defined param of a particular method. The purpose is to revert the
	 * client with warning if it sends any undefined param in the request
	 * 
	 * @return : List<String>
	 */
	private List<String> getDefinedParamList(String itemId, String partNo, String productGroupId, String modelId,
			String modelNo, String brandId, String brand, String parentProductTypeId, String productTypeId,
			String subProductTypeId, String key, String formatted, String startingRow, String maxRows, String sortBy,
			String versionDate, String facetBy) {

		List<String> list = new ArrayList<String>();

		if (itemId != null)
			list.add(itemId);
		if (partNo != null)
			list.add(partNo);
		if (productGroupId != null)
			list.add(productGroupId);
		if (modelId != null)
			list.add(modelId);
		if (modelNo != null)
			list.add(modelNo);
		if (brandId != null)
			list.add(brandId);
		if (brand != null)
			list.add(brand);
		if (parentProductTypeId != null)
			list.add(parentProductTypeId);
		if (productTypeId != null)
			list.add(productTypeId);
		if (subProductTypeId != null)
			list.add(subProductTypeId);
		if (key != null)
			list.add(key);
		if (formatted != null)
			list.add(formatted);
		if (startingRow != null)
			list.add(startingRow);
		if (maxRows != null)
			list.add(maxRows);
		if (sortBy != null)
			list.add(sortBy);
		if (versionDate != null)
			list.add(versionDate);
		if (facetBy != null) {
			list.add(facetBy);
		}

		return list;
	}

	/**
	 * Method : getItemSellPriceLimit Description : This method fetches the item
	 * selling price of a client from client config DB.
	 * 
	 * @return : BigDecimal
	 */
	private BigDecimal getItemSellPriceLimit(Integer clientId) throws CcdbException {
		logger.trace("Fetching ItemSellingPriceLimit from ClientConfigDB");
		return clientConfigBO.getItemSellingPriceLimit(clientId);
	}

	@Override
	public BaseResponse modelSearchEnhanced(String clientKey, String modelNo, String modelId, String brand,
			String parentProductTypeId, String productTypeId, String subProductTypeId, String responseFormat,
			String startingRow, String maxRows, String sortBy, String facetBy, String enableSearsIdSearch,
			String enableFuzzySearch, UriInfo uriInfo) {

		ModelSearchRequest request = new ModelSearchRequest();
		ModelSearchResponse response = new ModelSearchResponse();
		List<String> messages = new ArrayList<String>();
		List<BrandCcdb> brandIdList = null;
		List<ProductTypeCcdb> productTypeList = null;
		List<String> definedParamaList = null;

		try {

			request = createModelSearchRequest(clientKey, modelNo, modelId, responseFormat, brand, null, startingRow,
					maxRows, parentProductTypeId, productTypeId, subProductTypeId, sortBy, facetBy);

			// validating client, if invalid service will return from this point
			// itself

			response = (ModelSearchResponse) validateClient(request.getClientKey(), MODEL_SEARCH);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamaList = getDefinedParamList(null, null, null, MODEL_ID, MODEL_NO, null, BRAND,
						PARENT_PRODUCT_TYPE_ID, PRODUCT_TYPE_ID, SUB_PRODUCT_TYPE_ID, null, FORMATTED, STARTING_ROW,
						MAX_ROWS, SORT_BY, null, FACET_BY);

				definedParamaList.add(ENABLE_FUZZY_SEARCH);
				definedParamaList.add(ENABLE_SEARS_ID_SEARCH);

				// validating request params, if invalid service will return
				// from this point itself

				response = (ModelSearchResponse) validateRequest(request, MODEL_SEARCH_REQUEST_VALIDATOR, MODEL_SEARCH,
						uriInfo, definedParamaList);
				if (response.getResponseCode().equals(SUCCESS)) {
					final String messg = String.format(RESPONSE_DESC.INVALID_MAXROWS_LESSTHAN_EQUALTO,
							getMaxRowsDefault());
					if (response.getMessages().contains(messg)) {
						request.setMaxRows(getMaxRowsDefault());
						messages.add(messg);
					}
					// fetching exclusion data from client config DB

					Integer clientId = ClientUtils.getClientId();
					brandIdList = getBrandIds(clientId);
					productTypeList = getProductTypes(clientId);

					// querying SOLR to fetch data based on the request params
					// with exclusion applied
					response = (ModelSearchResponse) solrServiceInvokerBO.modelSearchEnhanced(request, brandIdList,
							productTypeList);

					ModelSearchResponse responseBySearsId = new ModelSearchResponse();
					ModelSearchResponse responseFuzzySearch = new ModelSearchResponse();

					if (StringUtils.isNotEmpty(enableSearsIdSearch) && enableSearsIdSearch.equalsIgnoreCase(YES)) {
						responseBySearsId = (ModelSearchResponse) solrServiceInvokerBO.modelSearchBySearsId(request,
								brandIdList, productTypeList);
					}
					// do fuzzy search only if regular search does not find a
					// match
					if (StringUtils.isNotEmpty(enableFuzzySearch) && enableFuzzySearch.equalsIgnoreCase(YES)
							&& Integer.parseInt(response.getNumFound()) == 0) {
						// ignore sort by for fuzzy search and default to
						// sorting on relevancy score desc.
						request.setSortBy(null);
						responseFuzzySearch = (ModelSearchResponse) solrServiceInvokerBO.modelSearchFuzzy(request,
								brandIdList, productTypeList);

						if (responseFuzzySearch.getModels() != null) {
							response.setModels(responseFuzzySearch.getModels());
							response.setNumFound(responseFuzzySearch.getNumFound());
							response.setFacetCounts(responseFuzzySearch.getFacetCounts());
						}

					}

					if (responseBySearsId.getModels() != null) {

						if (Integer.parseInt(response.getNumFound()) == 0) {
							response.setModels(responseBySearsId.getModels());
							response.setNumFound(responseBySearsId.getNumFound());
						} else {
							int totalCount = Integer.parseInt(response.getNumFound())
									+ Integer.parseInt(responseBySearsId.getNumFound());
							response.setNumFound(String.valueOf(totalCount));
							response.getModels().addAll(responseBySearsId.getModels());
						}
					}

					if (Integer.parseInt(response.getNumFound()) == 0) {
						response = (ModelSearchResponse) buildResponse(SUCCESS, messages, response);
						return response;
					}
					if (messages.isEmpty())
						messages.add(SUCCESS_MSG);
					response.setMessages(messages);
					response.setProcessId(MDC.get(Constants.PROCESS_ID));
					response.setResponseCode(SUCCESS);
				}
			}

			return response;

		} catch (Exception ex) {
			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (ModelSearchResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}
	}

	@Override
	public BaseResponse modelSearchWild(String clientKey, String modelNo, String brand, String parentProductTypeId,
			String productTypeId, String subProductTypeId, String responseFormat, String startingRow, String maxRows,
			String sortBy, String facetBy, String enableFuzzySearch, UriInfo uriInfo) {

		boolean wildcard = false;
		ModelSearchRequest request = new ModelSearchRequest();
		ModelSearchResponse response = new ModelSearchResponse();
		List<String> messages = new ArrayList<String>();
		List<BrandCcdb> brandIdList = null;
		List<ProductTypeCcdb> productTypeList = null;
		List<String> definedParamaList = null;

		try {
			request = createModelSearchRequest(clientKey, modelNo, null, responseFormat, brand, null, startingRow,
					maxRows, parentProductTypeId, productTypeId, subProductTypeId, sortBy, facetBy);

			response = (ModelSearchResponse) validateClient(request.getClientKey(), MODEL_SEARCH);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamaList = getDefinedParamList(null, null, null, null, MODEL_NO, null, BRAND,
						PARENT_PRODUCT_TYPE_ID, PRODUCT_TYPE_ID, SUB_PRODUCT_TYPE_ID, null, FORMATTED, STARTING_ROW,
						MAX_ROWS, SORT_BY, null, FACET_BY);

				if (StringUtils.contains(modelNo, GEN_CONST.WILDCARD))
					wildcard = true;

				definedParamaList.add(ENABLE_FUZZY_SEARCH);

				response = (ModelSearchResponse) validateRequest(request, MODEL_SEARCH_REQUEST_VALIDATOR, MODEL_SEARCH,
						uriInfo, definedParamaList);
				if (response.getResponseCode().equals(SUCCESS)) {
					final String messg = String.format(RESPONSE_DESC.INVALID_MAXROWS_LESSTHAN_EQUALTO,
							getMaxRowsDefault());
					if (response.getMessages().contains(messg)) {
						request.setMaxRows(getMaxRowsDefault());
						messages.add(messg);
					}
					// fetching exclusion data from client config DB

					Integer clientId = ClientUtils.getClientId();
					brandIdList = getBrandIds(clientId);
					productTypeList = getProductTypes(clientId);
					
					//exact and wild card...
					response = (ModelSearchResponse) solrServiceInvokerBO.modelSearchEnhanced(request, brandIdList,
							productTypeList);
					//look for exact match in results...
					if (response.getModelCnt() > 0) {
						List<Model> models = response.getModels();
						List<Model> list = null;
						for (Model model : models) {
							if (model.getModelNo().equals(request.getModelNo())) {
								if (list == null)
									list = new ArrayList<Model>();
								list.add(model);
							}
						}

						if (list != null) {
							response.setModels(list);
							response.setNumFound(String.valueOf(list.size()));
						}
					} 
					else {
						if(wildcard)
							modelNo = GEN_CONST.WILDCARD + modelNo;
						else
							modelNo =  GEN_CONST.WILDCARD + modelNo +  GEN_CONST.WILDCARD;
						request.setModelNo(modelNo);
						response = (ModelSearchResponse) solrServiceInvokerBO.modelSearch(request, brandIdList,
								productTypeList);
					}
					
					// do fuzzy if ALL else failed...
					if (response.getModelCnt() == 0) {
						if (StringUtils.equals(enableFuzzySearch, YES)) {
							request.setSortBy(null);
							response = (ModelSearchResponse) solrServiceInvokerBO.modelSearchFuzzy(request,
									brandIdList, productTypeList);
						}
					}
					
					if (messages.isEmpty())
						messages.add(SUCCESS_MSG);
					response.setMessages(messages);
					response.setProcessId(MDC.get(Constants.PROCESS_ID));
					response.setResponseCode(SUCCESS);
				}
			}
			
			return response;

		} catch (Exception ex) {
			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (ModelSearchResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}
	}

	/** After request is received from the client for model no search through v1_2/model search uri then this method gets invoked.
	 * This method first validates the Client and the request.
	 * Then it gets the respomnse from solr by passing the request to modelSearchForExactAndContainsMatch
	 * Receiving the response it returns the response to the client  
	 * **/
	public BaseResponse modelNumSearch(String clientKey, String modelNo, String brand, String parentProductTypeId,
			String productTypeId, String subProductTypeId, String responseFormat, String startingRow, String maxRows,
			String sortBy, String facetBy, String enableFuzzySearch, UriInfo uriInfo) {

		boolean wildcard = false;
		ModelSearchRequest request = new ModelSearchRequest();
		ModelSearchResponse response = new ModelSearchResponse();
		List<String> messages = new ArrayList<String>();
		List<BrandCcdb> brandIdList = null;
		List<ProductTypeCcdb> productTypeList = null;
		List<String> definedParamaList = null;

		try {
			request = createModelSearchRequest(clientKey, modelNo, null, responseFormat, brand, null, startingRow,
					maxRows, parentProductTypeId, productTypeId, subProductTypeId, sortBy, facetBy);

			response = (ModelSearchResponse) validateClient(request.getClientKey(), MODEL_SEARCH);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamaList = getDefinedParamList(null, null, null, null, MODEL_NO, null, BRAND,
						PARENT_PRODUCT_TYPE_ID, PRODUCT_TYPE_ID, SUB_PRODUCT_TYPE_ID, null, FORMATTED, STARTING_ROW,
						MAX_ROWS, SORT_BY, null, FACET_BY);

				if (StringUtils.contains(modelNo, GEN_CONST.WILDCARD))
					wildcard = true;

				definedParamaList.add(ENABLE_FUZZY_SEARCH);

				response = (ModelSearchResponse) validateRequest(request, MODEL_SEARCH_REQUEST_VALIDATOR, MODEL_SEARCH,
						uriInfo, definedParamaList);
				if (response.getResponseCode().equals(SUCCESS)) {
					final String messg = String.format(RESPONSE_DESC.INVALID_MAXROWS_LESSTHAN_EQUALTO,
							getMaxRowsDefault());
					if (response.getMessages().contains(messg)) {
						request.setMaxRows(getMaxRowsDefault());
						messages.add(messg);
					}
					// fetching exclusion data from client config DB

					Integer clientId = ClientUtils.getClientId();
					brandIdList = getBrandIds(clientId);
					productTypeList = getProductTypes(clientId);

					
					request.setSortBy(null);
					// Search for exact, starts with, contains and fuzzy matches
					response = (ModelSearchResponse) solrServiceInvokerBO.modelSearchForExactAndContainsMatch(request, brandIdList,
							productTypeList);

					if (messages.isEmpty())
						messages.add(SUCCESS_MSG);
					response.setMessages(messages);
					response.setProcessId(MDC.get(Constants.PROCESS_ID));
					response.setResponseCode(SUCCESS);
				}
			}

			return response;

		} catch (Exception ex) {
			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (ModelSearchResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}
	}

	@Override
	public BaseResponse modelSearchFuzzy(String clientKey, String modelNo, String modelId, String brand,
			String parentProductTypeId, String productTypeId, String subProductTypeId, String responseFormat,
			String startingRow, String maxRows, String sortBy, String facetBy, UriInfo uriInfo) {

		ModelSearchRequest request = new ModelSearchRequest();
		ModelSearchResponse response = new ModelSearchResponse();
		List<String> messages = new ArrayList<String>();
		List<BrandCcdb> brandIdList = null;
		List<ProductTypeCcdb> productTypeList = null;
		List<String> definedParamaList = null;

		try {

			request = createModelSearchRequest(clientKey, modelNo, modelId, responseFormat, brand, null, startingRow,
					maxRows, parentProductTypeId, productTypeId, subProductTypeId, sortBy, facetBy);

			// validating client, if invalid service will return from this point
			// itself

			response = (ModelSearchResponse) validateClient(request.getClientKey(), MODEL_SEARCH);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamaList = getDefinedParamList(null, null, null, MODEL_ID, MODEL_NO, null, BRAND,
						PARENT_PRODUCT_TYPE_ID, PRODUCT_TYPE_ID, SUB_PRODUCT_TYPE_ID, null, null, STARTING_ROW,
						MAX_ROWS, SORT_BY, null, FACET_BY);

				// validating request params, if invalid service will return
				// from this point itself

				response = (ModelSearchResponse) validateRequest(request, MODEL_SEARCH_REQUEST_VALIDATOR, MODEL_SEARCH,
						uriInfo, definedParamaList);
				if (response.getResponseCode().equals(SUCCESS)) {
					final String messg = String.format(RESPONSE_DESC.INVALID_MAXROWS_LESSTHAN_EQUALTO,
							getMaxRowsDefault());
					if (response.getMessages().contains(messg)) {
						request.setMaxRows(getMaxRowsDefault());
						messages.add(messg);
					}
					// fetching exclusion data from client config DB

					Integer clientId = ClientUtils.getClientId();
					brandIdList = getBrandIds(clientId);
					productTypeList = getProductTypes(clientId);

					// This will ensure default relevancy for fuzzy search
					if (StringUtils.isEmpty(sortBy)) {
						request.setSortBy(null);
					}
					// querying SOLR to fetch data based on the request params
					// with exclusion applied
					response = (ModelSearchResponse) solrServiceInvokerBO.modelSearchFuzzy(request, brandIdList,
							productTypeList);
					if (Integer.parseInt(response.getNumFound()) == 0) {
						response = (ModelSearchResponse) buildResponse(SUCCESS, messages, response);
						return response;
					}
					if (messages.isEmpty())
						messages.add(SUCCESS_MSG);
					response.setMessages(messages);
					response.setProcessId(MDC.get(Constants.PROCESS_ID));
					response.setResponseCode(SUCCESS);
				}
			}

			return response;

		} catch (Exception ex) {
			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (ModelSearchResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}
	}

	@Override
	public BaseResponse search(String clientKey, String responseFormat, String value, String startingRow,
			String maxRows, UriInfo uriInfo) {

		SearchResponse response = new SearchResponse();
		List<String> messages = new ArrayList<String>();

		try {
			response = (SearchResponse) solrServiceInvokerBO.search(value);
			if (Integer.parseInt(response.getNumFound()) == 0) {
				response = (SearchResponse) buildResponse(SUCCESS, messages, response);
				return response;
			}

			if (messages.isEmpty())
				messages.add(SUCCESS_MSG);
			response.setMessages(messages);
			response.setProcessId(MDC.get(Constants.PROCESS_ID));
			response.setResponseCode(SUCCESS);

			return response;

		} catch (Exception ex) {
			response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (SearchResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}
	}

	@Override
	public BaseResponse modelSearchBySearsId(String clientKey, String modelNo, String brand, String parentProductTypeId,
			String productTypeId, String subProductTypeId, String responseFormat, String startingRow, String maxRows,
			String sortBy, String facetBy, UriInfo uriInfo) {
		ModelSearchRequest request = new ModelSearchRequest();
		ModelSearchResponse response = new ModelSearchResponse();
		List<String> messages = new ArrayList<String>();
		List<BrandCcdb> brandIdList = null;
		List<ProductTypeCcdb> productTypeList = null;
		List<String> definedParamaList = null;

		try {

			request = createModelSearchRequest(clientKey, modelNo, null, responseFormat, brand, null, startingRow,
					maxRows, parentProductTypeId, productTypeId, subProductTypeId, sortBy, facetBy);

			// validating client, if invalid service will return from this point
			// itself

			response = (ModelSearchResponse) validateClient(request.getClientKey(), MODEL_SEARCH);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamaList = getDefinedParamList(null, null, null, null, MODEL_NO, null, BRAND,
						PARENT_PRODUCT_TYPE_ID, PRODUCT_TYPE_ID, SUB_PRODUCT_TYPE_ID, null, null, STARTING_ROW,
						MAX_ROWS, SORT_BY, null, FACET_BY);

				// validating request params, if invalid service will return
				// from this point itself

				response = (ModelSearchResponse) validateRequest(request, MODEL_SEARCH_REQUEST_VALIDATOR, MODEL_SEARCH,
						uriInfo, definedParamaList);
				if (response.getResponseCode().equals(SUCCESS)) {
					final String messg = String.format(RESPONSE_DESC.INVALID_MAXROWS_LESSTHAN_EQUALTO,
							getMaxRowsDefault());
					if (response.getMessages().contains(messg)) {
						request.setMaxRows(getMaxRowsDefault());
						messages.add(messg);
					}
					// fetching exclusion data from client config DB

					Integer clientId = ClientUtils.getClientId();
					brandIdList = getBrandIds(clientId);
					productTypeList = getProductTypes(clientId);

					// querying SOLR to fetch data based on the request params
					// with exclusion applied

					response = (ModelSearchResponse) solrServiceInvokerBO.modelSearchBySearsId(request, brandIdList,
							productTypeList);

					if (Integer.parseInt(response.getNumFound()) == 0) {
						response = (ModelSearchResponse) buildResponse(SUCCESS, messages, response);
						return response;
					}
					if (messages.isEmpty())
						messages.add(SUCCESS_MSG);
					response.setMessages(messages);
					response.setProcessId(MDC.get(Constants.PROCESS_ID));
					response.setResponseCode(SUCCESS);
				}
			}

			return response;

		} catch (Exception ex) {

			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (ModelSearchResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}
	}

	@Override
	public BaseResponse getAccessories(String clientKey, String responseFormat, String productTypeId, String brandId,
			String modelNo, String maxRows, UriInfo uriInfo, HttpHeaders headers) {

		AccessoryRequest request = null;
		AccessoryResponse response = null;
		List<String> definedParamaList = null;
		List<String> messages = new ArrayList<String>();

		try {
			request = createAccessoryRequest(clientKey, modelNo, brandId, productTypeId, responseFormat, maxRows);
			response = (AccessoryResponse) validateClient(request.getClientKey(), GET_ACCESSORIES);

			if (response.getResponseCode().equals(SUCCESS)) {
				definedParamaList = getDefinedParamList(null, null, null, null, MODEL_NO, BRAND_ID, null, null,
						PRODUCT_TYPE_ID, null, null, null, null, MAX_ROWS, null, null, null);

				response = (AccessoryResponse) validateRequest(request, ACCESSORY_REQUEST_VALIDATOR, GET_ACCESSORIES,
						uriInfo, definedParamaList);
				if (response.getResponseCode().equals(SUCCESS)) {
					List<Accessory> accessories = accessoryBO.getAccessories(request);
					List<Maintenance> maintenanceParts = accessoryBO.getMaintenanceParts(request);

					if (accessories.size() == 0 & maintenanceParts.size() == 0) {
						messages.add(DATA_NOT_FOUND);
						response.setNumFound(String.valueOf(ZERO));
					} else {
						int numFound = accessories.size() + maintenanceParts.size();
						messages.add(SUCCESS_MSG);
						response.setNumFound(String.valueOf(numFound));
						response.setAccessories(accessories);
						response.setMaintenances(maintenanceParts);
					}

					response = (AccessoryResponse) buildResponse(SUCCESS, messages, response);
				}
			}

			return response;

		} catch (Exception ex) {

			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (AccessoryResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}
	}

	@Override
	public GetCoreAndEnvChargeAmountResponse getCoreAndEnvChargeAmount(String clientId, UriInfo uriInfo,
			GetCoreAndEnvChargeAmountRequest coreAndEnvChargeAmountRequest) {

		GetCoreAndEnvChargeAmountResponse response = null;
		List<String> messages = new ArrayList<String>();

		try {
			response = (GetCoreAndEnvChargeAmountResponse) validateClient(clientId, CORE_ENV_CHARGE_AMOUNT);
			if (response.getResponseCode().equals(SUCCESS)) {

				response = (GetCoreAndEnvChargeAmountResponse) validateRequest(coreAndEnvChargeAmountRequest,
						CORE_ENV_CHARGE_AMOUNT_VALIDATOR, CORE_ENV_CHARGE_AMOUNT, uriInfo, null);
				if (response.getResponseCode().equals(SUCCESS)) {

					List<PartDetailReturn> parts = coreAndEnvChargeAmountBO
							.getPartCharges(coreAndEnvChargeAmountRequest);

					if (CollectionUtils.isEmpty(parts)) {
						messages.add(DATA_NOT_FOUND);
					} else {
						response.setPartDetailReturn(parts);
					}
				}
				response = (GetCoreAndEnvChargeAmountResponse) buildResponse(SUCCESS, messages, response);
			}
			return response;
		} catch (Exception e) {
			logger.error("{}", e);
			if (response != null)
				response.getMessages().clear();
			messages.add(SYSTEM_ERROR_MSG);
			response = (GetCoreAndEnvChargeAmountResponse) buildResponse(SYSTEM_ERROR, messages, response);
			return response;
		} finally {
			ThreadLocalContainer.clear();
		}
	}

}