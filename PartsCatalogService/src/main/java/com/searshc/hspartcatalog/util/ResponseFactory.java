package com.searshc.hspartcatalog.util;

import com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SERVICE_METHOD;
import com.searshc.hspartcatalog.services.vo.base.BaseResponse;
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

/**
* Title			:   ResponseFactory
* Description	:	this class is the factory for the response objects  
* @author		:	Abhishek Jain
*/
public class ResponseFactory {
	
	public BaseResponse getResponse(String method){
		if(method != null && !method.isEmpty()){
			if(method.equals(SERVICE_METHOD.ITEM_SEARCH))
				return new ItemSearchResponse();
			if(method.equals(SERVICE_METHOD.ITEM_DESCRIPTION_SEARCH))
				return new ItemSearchResponse();
			if(method.equals(SERVICE_METHOD.MODEL_SEARCH))
				return new ModelSearchResponse();
			if(method.equals(SERVICE_METHOD.FULL_SEARCH))
				return new FullSearchResponse();
			if(method.equals(SERVICE_METHOD.GET_BRANDS))
				return new GetBrandsResponse();
			if(method.equals(SERVICE_METHOD.GET_SCHEMATICS))
				return new GetSchematicsResponse();
			if(method.equals(SERVICE_METHOD.GET_MODEL_DETAILS))
				return new GetModelDetailsResponse();
			if(method.equals(SERVICE_METHOD.GET_MODELS_FOR_ITEM))
				return new GetModelsForItemResponse();
			if(method.equals(SERVICE_METHOD.GET_ITEM_DETAILS))
				return new GetItemDetailsResponse();
			if(method.equals(SERVICE_METHOD.GET_ITEM_LIST))
				return new GetItemListResponse();
			if(method.equals(SERVICE_METHOD.GET_MODEL_LIST))
				return new GetModelListResponse();
			if(method.equals(SERVICE_METHOD.GET_BRAND_LIST))
				return new GetBrandListResponse();
			if(method.equals(SERVICE_METHOD.GET_PARENT_PRODUCT_TYPE_LIST))
				return new GetParentProductTypeListResponse();		
			if(method.equals(SERVICE_METHOD.GET_PRODUCT_GROUP_LIST))
				return new GetProductGroupListResponse();
			if(method.equals(SERVICE_METHOD.GET_ACCESSORIES))
				return new AccessoryResponse();
			if(method.equals(SERVICE_METHOD.CORE_ENV_CHARGE_AMOUNT))
				return new GetCoreAndEnvChargeAmountResponse();
		}
		return null;
	}
}
