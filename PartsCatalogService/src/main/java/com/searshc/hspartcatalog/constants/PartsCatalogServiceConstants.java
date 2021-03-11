 package com.searshc.hspartcatalog.constants;


/**
* Title			:   PartsCatalogServiceConstants
* 
* Description	:	The class contains all the constants used in partsCatalogAPI 
*
* @author		:	Abhishek Jain
*/

public class PartsCatalogServiceConstants {
	
	/*Service method constants*/
	public static class SERVICE_METHOD {
		public static final String ITEM_SEARCH = "itemSearch";
		public static final String ITEM_DESCRIPTION_SEARCH = "itemDescriptionSearch";
		public static final String MODEL_SEARCH = "modelSearch";
		public static final String FULL_SEARCH = "fullSearch";
		public static final String GET_BRANDS = "getBrands";
		public static final String GET_SCHEMATICS = "getSchematics";
		public static final String GET_MODEL_DETAILS = "getModelDetails";
		public static final String GET_MODELS_FOR_ITEM = "getModelsForItem";
		public static final String GET_ITEM_DETAILS = "getItemDetails";
		public static final String GET_ITEM_LIST = "getItemList";
		public static final String GET_MODEL_LIST = "getModelList";
		public static final String GET_BRAND_LIST = "getBrandList";
		public static final String GET_PARENT_PRODUCT_TYPE_LIST = "getParentProductTypeList";
		public static final String GET_PRODUCT_GROUP_LIST = "getProductGroupList";
		public static final String GET_ACCESSORIES = "accessories";
		public static final String CORE_ENV_CHARGE_AMOUNT = "getCoreAndEnvChargeAmount";
		
	}
	
	/* General Constants*/
	public static class GEN_CONST {
		public static final String SPACE = " ";
		public static final String OR = " or ";
		public static final String VALID = "valid";
		public static final String JSON = "application/json";
		public static final String XML = "application/xml";
		public static final String LESS_THAN = "less than";
		public static final String EQUALS_TO = "equals to ";
		public static final String GREATER_THAN = " greater than";
		
		public static final Integer STARTING_ROW_DEFAULT = 1;
		public static final String DATE_FORMAT = "MM-dd-yyyy";
		public static final String SOLR_DATE_FORMAT = "yyyy-MM-dd";
		public static final String YES = "Y";
		public static final String NO = "N";
		public static final String ASC = "asc";
		public static final String DESC = "desc";
		public static final int ONE = 1;
		public static final int ZERO = 0;
		public static final String EQUALS = "=";
		public static final String COMMA = ",";
		public static final String COLON = ":";
		public static final String CURL_OPEN = "{";
		public static final String CURL_CLOSE = "}";
		public static final String BRAC_OPEN ="[";
		public static final String BRAC_CLOSE ="]";
		public static final String QUOTE ="\"";
		public static final String ROUND_BRAC_OPEN ="(";
		public static final String ROUND_BRAC_CLOSE = ")";
		public static final String MINUS = "-";
		public static final String WILDCARD = "*";
		public static final String TRUE = "true";
		public static final String FALSE = "false";
		public static final String FORMAT = "in such format : ";
		public static final String CACHE = "cache";
		public static final String CATALOG = "catalog";
		public static final String WITHIN_BRACES = "(%s)";
	}
	
	/* Request fields and fixed length*/
	public static class REQUEST_FIELDS {
		public static final String PART_NO_MODEL_ASC = "partNo asc,modelNo asc";
		public static final String PART_NO_ASC = "partNo asc";
		public static final String MODEL_NO_ASC = "modelNo asc";
		public static final String BRAND_ASC = "brand asc";
		public static final String ITEM_ID = "itemId";
		public static final String DESCRIPTION = "description";
		public static final String PART_NO = "partNo";
		public static final String PRODUCT_GROUP_ID = "productGroupId";
		public static final String MODEL_ID = "modelId";
		public static final String MODEL_NO = "modelNo";
		public static final String BRAND_ID = "brandId";
		public static final String BRAND = "brand";
		public static final String PARENT_PRODUCT_TYPE_ID = "parentProductTypeId";
		public static final String PARENT_PRODUCT_TYPE_NAME = "parentProductTypeName";
		public static final String PRODUCT_TYPE_ID = "productTypeId";
		public static final String PRODUCT_TYPE = "productType";
		public static final String SUB_PRODUCT_TYPE_ID = "subProductTypeId";
		public static final String RESPONSE_FORMAT = "responseFormat";
		public static final String FORMATTED = "formatted";
		public static final String STARTING_ROW = "startingRow";
		public static final String MAX_ROWS = "maxRows";
		public static final String SORT_BY = "sortBy";
		public static final String KEY = "key";
		public static final String VERSION_DATE = "versionDate";
		public static final String SCHEMATIC_DESC = "schematicDescription";
		public static final String SCHEMATIC_NAME_ASC = "schematicName asc";
		public static final String SCHEMATIC_NAME = "schematicName";
		public static final String CLIENT_KEY="clientKey";
		public static final String SORT_BY_FIELD="sortBy field";
		public static final String SORT_BY_ORDER="sortBy order";
		public static final String PRODUCT_GROUP_NAME = "productGroupName";
		public static final String COUNT="count";
		public static final String SUBBED_FLAG = "subbedFlag";
		public static final String SUB_AVAILABILITY_STATUS="subItemAvailabilityStatus";
		public static final String API_KEY="API-Key";
		public static final String ACCEPT="Accept";
		public static final String TRANS_CODE="transCode";
		public static final String DIV_NO = "divNo";
		public static final String PLS_NO = "plsNo";
		public static final String PART_DETAIL_NO = "partNo";
		
		public static final int ITEM_ID_FIXED_LENGTH = 110;
		public static final int PART_NO_FIXED_LENGTH = 50;
		public static final int PRODUCT_GROUP_ID_FIXED_LENGTH = 4;
		public static final int MODEL_ID_FIXED_LENGTH = 50;
		public static final int MODEL_NO_FIXED_LENGTH = 35;
		public static final int BRAND_ID_FIXED_LENGTH = 6;
		public static final int BRAND_FIXED_LENGTH = 254;
		public static final int PARENT_PRODUCT_TYPE_ID_FIXED_LENGTH =  7;
		public static final int PRODUCT_TYPE_ID_FIXED_LENGTH = 7;
		public static final int SUB_PRODUCT_TYPE_ID_FIXED_LENGTH = 7;
		public static final int RESPONSE_FORMAT_FIXED_LENGTH = 50;
		public static final int FORMATTED_FIXED_LENGTH = 1;
		public static final int STARTING_ROW_FIXED_LENGTH = 6;
		public static final int MAX_ROWS_FIXED_LENGTH = 3;
		public static final int MAX_ROWS_FIXED_LENGTH_CACHE = 5;
		public static final int SORT_BY_FIXED_LENGTH = 200;
		public static final int KEY_FIXED_LENGTH = 110;
		public static final int VERSION_DATE_FIXED_LENGTH = 10;
		public static final int ITEM_DESCRIPTION_MIN_LENGTH = 5;
		public static final int DIV_NO_MAX_LENGTH = 3; 
		public static final int PLS_NO_MAX_LENGTH = 3;
		public static final int PART_DETAIL_NO_MAX_LENGTH = 24;
		
		public static final String FACET_BY = "facetBy";
		public static final String FACET_BY_FIELD="facetBy field";
	
		public static final String ENABLE_SEARS_ID_SEARCH="enableSearsIdSearch";
		public static final String ENABLE_FUZZY_SEARCH="enableFuzzySearch";
		
		
		
	}

	public static class RESPONSE_CODE {
		public static final String SUCCESS = "000";
		public static final String WARNING = "100";
		public static final String DATA_VALIDATION_ERROR = "200";
		public static final String SYSTEM_ERROR = "300";
	}

	public static class RESPONSE_DESC {
		public static final String INVALID_REQUEST =" Invalid request ";
		public static final String SYSTEM_ERROR_MSG = "System Error";
		public static final String INVALID_FIELD_LENGTH = "%s should be %s characters long or less";
		public static final String WILDCARD_NOT_SUPPORTED_FOR_FIELD = "Wildcard not supported for field : %s";
		public static final String INVALID_EXACT_FIELD_LENGTH = "%s should be exactly %s characters long";
		public static final String INVALID_FIELD_VALUE = "Invalid field value. %s must be %s.";
		public static final String SORT_EXAMPLE = " For Exp : modelId asc, itemId desc";
		public static final String REQUIRED_FIELD_MISSING = "Required field missing : %s";
		public static final String INVALID_DATE = "Invalid Date";
		public static final String INVALID_DATE_FORMAT = "Invalid date format. Expected format : %s";
		public static final String FIELD_MUST_BE_NUMERIC = "Field : %s must be numeric";
		public static final String DATA_NOT_FOUND = "No matching record found";
		public static final String INVALID_NUMBER_OF_REQUEST_PARAMS = "Invalid number of mandatory request parameters. Expected : %s";
		public static final String SUCCESS_MSG = "Success";
		public static final String SUCCESS_MSG_FUZZY = "fuzzy model search has found matches";
		
		public static final String CLIENT_INVALID="Invalid Client. Client either does not exist or inactive.";
		public static final String SOLR_LAYER_EXCEPTION="exception at SOLR layer";
		public static final String CCDB_LAYER_EXCEPTION="exception at CCDB layer";
		public static final String NON_NEGATIVE="Field : %s should be non negative.";
		public static final String NON_ZERO="Field : %s should be greater than 0.";
		public static final String SPACES_NOT_ALLOWED_IN_BETWEEN="Spaces not allowed in between for field : %s";
		public static final String UNDEFINED_REQUEST_PARAM="Undefined request parameter : %s";
		public static final String BAD_REQUEST = "Bad Request";
		public static final String INVALID_MAXROWS_LESSTHAN_EQUALTO = "Invalid field value. maxRows must be less than or equals to %s.";
		public static final String FACET_EXAMPLE = " For Exp : facetBy=brandId,productTypeId";
	}

	public static class VALIDATOR {
		public static final String ITEM_SEARCH_REQUEST_VALIDATOR="ItemSearchRequestValidator";
		public static final String ITEM_DESCRIPTION_SEARCH_REQUEST_VALIDATOR="ItemDescriptionSearchRequestValidator";
		public static final String MODEL_SEARCH_REQUEST_VALIDATOR="ModelSearchRequestValidator";
		public static final String FULL_SEARCH_REQUEST_VALIDATOR="FullSearchRequestValidator";
		public static final String GET_BRANDS_REQUEST_VALIDATOR="GetBrandsRequestValidator";
		public static final String GET_SCHEMATICS_REQUEST_VALIDATOR="GetSchematicsRequestValidator";
		public static final String GET_MODEL_DETAILS_REQUEST_VALIDATOR="GetModelDetailsRequestValidator";
		public static final String GET_MODELS_FOR_ITEM_REQUEST_VALIDATOR="GetModelsForItemRequestValidator";
		public static final String GET_ITEM_DETAILS_REQUEST_VALIDATOR="GetItemDetailsRequestValidator";
		public static final String GET_ITEM_LIST_REQUEST_VALIDATOR="GetItemListRequestValidator";
		public static final String GET_MODEL_LIST_REQUEST_VALIDATOR="GetModelListRequestValidator";
		public static final String GET_BRAND_LIST_REQUEST_VALIDATOR="GetBrandListRequestValidator";
		public static final String GET_PARENT_PRODUCT_TYPE_LIST_REQUEST_VALIDATOR="GetParentProductTypeListRequestValidator";
		public static final String GET_PRODUCT_GROUP_LIST_REQUEST_VALIDATOR="GetProductGroupListRequestValidator";
		public static final String ACCESSORY_REQUEST_VALIDATOR="AccessoryRequestValidator";
		public static final String CORE_ENV_CHARGE_AMOUNT_VALIDATOR="GetCoreAndEnvChargeValidator";
	}		
	
	public static class CLIENT_CONFIG_DB_FIELDS{
		public static final String RESTRICTIONS="itemRestrictions";
		public static final String ATTRIBUTES="itemAttributes";
		public static final String AVAILABILITY_STATUS="itemAvailabilityStatus";
		public static final String BRANDS="brands";
		public static final String BRAND_ID="brandId";
		public static final String DIV_PLS="divPls";
		public static final String CLIENT_ID="clientId";
		public static final String CLIENT_KEY="clientKey";
		public static final String PRODUCT_TYPE="productType";
		public static final String ITEM_SELL_PRICE_LIMIT = "itemSellingPrice";
		public static final String CLIENT_DETAILS = "client_details";
	}
	
	public static class SOLR_CONST{
		
		public static final int MIN_RESPONSE_CODE=100;
		public static final int MAX_RESPONSE_CODE=300;
		public static final String CONTENT_TYPE="UTF-8";
		public static final String FUZZY_SEARCH_ENCODED = "%7E";
		public static final String START="&start=";
		public static final String ROWS="&rows=";
		public static final String SORT="&sort=";
		public static final String TO_FILTER="+TO+*]";
		public static final String FILTER_EXCLUSION="&fq=!";
		public static final String ALL_SEARCH_CRITERIA="ALL";
		public static final String ALL="*";
		public static final String OR=")+OR+";
		public static final String AND="+AND+";
		
		public static final String PART_ALL_GROUP_BY_PRODUCT_GROUP_ID="partNo:*&group=true&fl=productGroupId,productGroupName&group.field=productGroupName";
		
		public static final String GROUP_BY_BRAND="&group=true&fl=brandId&group.field=brand&sort=brand+asc";
		public static final String GROUP_BY_PARENT_PRODUCT_TYPE_ID="&group=true&fl=parentProductTypeId,parentProductTypeName&group.field=parentProductTypeName";
		public static final String GROUP_BY_PRODUCT_GROUP_ID="&group=true&fl=productGroupId,productGroupName&group.field=productGroupName";
		public static final String GROUP_FIELD="&group.field=";
		
		public static final String FACET_FIELD="&facet.field=";
		
		public static final String FACET_ON = "&facet=on";
		
		public static final String FACET_MIN_COUNT_DEFAULT = "&facet.mincount=1";
		
		
		public static final String ITEM_ID = "itemId:";
		public static final String ITEM_DESCRIPTION = "itemDescription:";
		public static final String PART_NO = "partNo:";
		public static final String MODEL_ID = "modelId:";
		public static final String MODEL_NO = "modelNo:";
		public static final String BRAND = "brand:";
		public static final String PRODUCT_GROUP_ID = "productGroupId:";
		public static final String PRODUCT_TYPE_NAME = "productTypeName:";
		public static final String PARENT_PRODUCT_TYPE_ID = "parentProductTypeId:";
		public static final String FORMATTED_ITEM_ID="formattedItemId:";
		public static final String FORMATTED_PART_ID="formattedPartNo:";
		public static final String FORMATTED_MODEL_NO="formattedModelNo:";
		public static final String FORMATTED_MODEL_ID="formattedModelId:";
		public static final String BRAND_ALL="brand:*";
		public static final String PART_ALL="partNo:*";
		public static final String MODEL_NO_ALL="modelNo:*";
		public static final String AVAILABILITY_STATUS="itemAvailabilityStatus:";
		public static final String SUB_AVAILABILITY_STATUS="subItemAvailabilityStatus:";
		
		public static final String FILTER_PRODUCT_GROUP_ID = "&fq=productGroupIdPls:";
		public static final String FILTER_BRAND = "&fq=brand:";
		public static final String FILTER_BRANDID = "&fq=brandId:";
		public static final String FILTER_PRODUCT_TYPE_ID="&fq=productTypeId:";
		public static final String FILTER_PARENT_PRODUCT_TYPE_ID="&fq=parentProductTypeId:";
		public static final String FILTER_SUB_PRODUCT_TYPE_ID="&fq=subProductTypeId:";
		public static final String FILTER_ITEM_SELL_PRICE_LIMIT="&fq=itemSellingPrice:[";
		
		public static final String FILTER="&fq=(";
		public static final String FILTER_EXCLUSION_BRANDID="&fq=!brandId:";
		public static final String FILTER_EXCLUSION_PRODUCT_GROUP_ID="&fq=!productGroupIdPls:";
		public static final String FILTER_EXCLUSION_PRODUCT_TYPE_ID="&fq=!productTypeId:";
		public static final String FILTER_EXCLUSION_SUB_PRODUCT_TYPE_ID="&fq=!subProductTypeId:";
		public static final String FILTER_EXCLUSION_PARENT_PRODUCT_TYPE_ID="&fq=!parentProductTypeId:";
		public static final String FILTER_EXCLUSION_RESTRICTIONS="&fq=!itemRestrictions:";
		public static final String FILTER_EXCLUSION_AVAILABILITY_STATUS="&fq=!itemAvailabilityStatus:";
		public static final String FILTER_EXCLUSION_ITEM_ATTRIBUTE_ID="&fq=!itemAttributeId:";
		public static final String FILTER_EXCLUSION_ITEM_RESTRICTION_ID="&fq=!itemRestrictionId:";
		
		public static final String ALL_ROWS="&rows=1500";
		public static final String RESP_FORMAT_JSON="&wt=json";
		
		public static final String ALL_ROWS_JSON="&rows=1500&wt=json";
		
		public static final String JOIN_FROM_ITEM_RESTRICTIONS_TO_ID="{!join+from=itemRestrictions+to=id}itemId:("; 
		public static final String JOIN_FROM_ITEM_ATTRIBUTES_TO_ID="{!join+from=itemAttributes+to=id}itemId:("; 
		public static final String JOIN_FROM_MODEL_SCHEMATICS_TO_MODELNO="{!join+from=modelSchematics+to=id}modelNo:"; 
		public static final String JOIN_FROM_MODEL_SCHEMATICS_TO_MODELID="{!join+from=modelSchematics+to=id}modelId:"; 
		public static final String JOIN_FROM_ITEM_SCHEMATICS_TO_PARTNO="{!join+from=itemSchematics+to=id}partNo:"; 
		public static final String JOIN_FROM_ITEM_SCHEMATICS_TO_ITEMID="{!join+from=itemSchematics+to=id}itemId:"; 
		public static final String JOIN_FROM_ITEM_SCHEMATICS_TO_MODEL_SCHEMATICS = "{!join+from=itemSchematics+to=modelSchematics}itemId:";
		public static final String JOIN_FROM_MODEL_SCHEMATICS_TO_ITEM_SCHEMATICS = "{!join+from=modelSchematics+to=itemSchematics}modelId:";
	
		public static final String JOIN_FROM_SPIN_TO_MODEL_NO = "{!join+from=catchAllSpin+to=modelNoEnhancedSearch}catchAllSpin:";
		public static final String JOIN_FROM_SPIN_PLS_ITEM_TO_MODEL_NO = "{!join+from=spinModelPlsItem+to=modelNoEnhancedSearch}catchAllSpin:";
	    public static final String JOIN_FROM_SPIN_VENDOR_STOCK_NMBR_TO_MODEL_NO = "{!join+from=spinModelVendorStockNumber+to=modelNoEnhancedSearch}catchAllSpin:";
	    public static final String JOIN_FROM_SPIN_MANUFACTURER_NMBR_TO_MODEL_NO = "{!join+from=spinModelManufacturerModelNumber+to=modelNoEnhancedSearch}catchAllSpin:";
	    public static final String FILTER_EXCLUSION_MODEL_NO="&fq={!cache=false}!formattedModelNo:";
	    
	    		
		
	 
		
		
		public static final String FACET_ON_FIELD_BRAND = "&facet=on&facet.field=brand&rows=0&facet.mincount=1&facet.limit=-1&facet.sort=count";

		public static final String AVAILABILITY_STATUS_EXCLUDE="(!itemAvailabilityStatus:";
		public static final String SUB_AVAILABILITY_STATUS_EXCLUDE="!subItemAvailabilityStatus:";
		
		public static final String SUBBED_FLAG_NO = "+AND+subbedFlag:N)+OR+";
		public static final String SUBBED_FLAG_YES = "+AND+subbedFlag:Y+AND+";
		
		public static final String SCHEMATIC_DESC_ASC = "schematicDescription asc";
		public static final String SCHEMATIC_DESC_DESC = "schematicDescription desc";
		
		public static final String SPIN_CATCH_ALL = "catchAllSpin:";
		
		
		public static final String MODEL_NO_FOR_ENHANCED_SEARCH = "modelNoEnhancedSearch:";
		
		public static final String DEFAULT_FUZZY_FACTOR = "~1";
		
		
		
	
	}

	
}
