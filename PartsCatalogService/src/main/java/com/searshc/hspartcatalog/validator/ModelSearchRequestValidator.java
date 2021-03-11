/**
 * 
 */
package com.searshc.hspartcatalog.validator;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.CATALOG;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.COMMA;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.BRAND;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.BRAND_ID;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.BRAND_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.FORMATTED;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.FORMATTED_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MAX_ROWS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MAX_ROWS_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MODEL_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MODEL_ID_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MODEL_NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MODEL_NO_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PARENT_PRODUCT_TYPE_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PARENT_PRODUCT_TYPE_ID_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PRODUCT_TYPE_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PRODUCT_TYPE_ID_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.RESPONSE_FORMAT;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.RESPONSE_FORMAT_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.SORT_BY;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.SORT_BY_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.STARTING_ROW;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.STARTING_ROW_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.SUB_PRODUCT_TYPE_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.SUB_PRODUCT_TYPE_ID_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_CODE.DATA_VALIDATION_ERROR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_CODE.SUCCESS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.SUCCESS_MSG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.searshc.hspartcatalog.pojo.LengthAttributes;
import com.searshc.hspartcatalog.pojo.ValidatorOutput;
import com.searshc.hspartcatalog.services.vo.request.ModelSearchRequest;
import com.searshc.hspartcatalog.util.CommonUtils;

/**
* Title			:   ModelSearchRequestValidator
* Description	:	this class validates ModelSearchRequest. Validation includes mandatory params, default values, datatype, 
* 					alpha space, length etc.  
* @author		:	Abhishek Jain
*/

public class ModelSearchRequestValidator extends CommonValidator implements Validator<ModelSearchRequest>{

	@Override
	public ValidatorOutput validate(ModelSearchRequest request) {
		
		List<LengthAttributes> reqAttrsForExactValidation = new ArrayList<LengthAttributes>();
		List<LengthAttributes> reqAttrsForValidation = new ArrayList<LengthAttributes>();
		List<String> fieldValidationMessages = new ArrayList<String>();
		List<String> messages = new ArrayList<String>();
		List<String> msgList = new ArrayList<String>();
		String msg = null;
		
		/* Mandatory field validation*/ 
		
		Map<String, String> fieldsMap = new HashMap<String, String>();
		fieldsMap.put(MODEL_ID, request.getModelId());
		fieldsMap.put(MODEL_NO, request.getModelNo());

		msg = mandatoryFieldValidation(fieldsMap);
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);
		
		/* common validation*/

		msg = formattedValidation(request.getFormatted());
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);
		
		msg = responseFormatValidation(request.getResponseFormat());
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);
		
		msg = startingRowValidation(request.getStartingRow());
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);

		msg = maxRowsValidation(request.getMaxRows());
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);

		msgList = sortByValidation(request.getSortBy(), MODEL_ID, MODEL_NO, BRAND, PARENT_PRODUCT_TYPE_ID, PRODUCT_TYPE_ID, 
				SUB_PRODUCT_TYPE_ID);
		
		msgList = facetByValidation(request.getFacetBy(),BRAND,BRAND_ID,PRODUCT_TYPE_ID,PARENT_PRODUCT_TYPE_ID);
		
		if(msgList!=null && !msgList.isEmpty())
			messages.addAll(msgList);

		/* AlphaSpace check */
		
		fieldsMap.remove(MODEL_ID);
		fieldsMap.remove(MODEL_NO);
		
		fieldsMap.put(PARENT_PRODUCT_TYPE_ID, request.getParentProductTypeId());
		fieldsMap.put(PRODUCT_TYPE_ID, request.getProductTypeId());
		fieldsMap.put(SUB_PRODUCT_TYPE_ID, request.getSubProductTypeId());
		
		msg = spaceValidation(fieldsMap);
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);
		
		/*wildcard verification*/
		
		fieldsMap.put(BRAND, request.getBrand());

		msg = wildCardValidation(fieldsMap);
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);
		
/*		 Field length validation*/ 

		String modelNo = (request.getModelNo());
		if(!StringUtils.isEmpty(modelNo)){
			LengthAttributes reqAttr = new LengthAttributes();
			reqAttr.setFieldName(MODEL_NO);
			reqAttr.setFieldValue(modelNo);
			reqAttr.setLength(MODEL_NO_FIXED_LENGTH);
			reqAttrsForValidation.add(reqAttr);
		}
		String modelId = (request.getModelId());
		if(!StringUtils.isEmpty(modelId)){
			LengthAttributes reqAttr = new LengthAttributes();
			reqAttr.setFieldName(MODEL_ID);
			reqAttr.setFieldValue(modelId);
			reqAttr.setLength(MODEL_ID_FIXED_LENGTH);
			reqAttrsForValidation.add(reqAttr);
		}
		String brand = (request.getBrand());
		if(!StringUtils.isEmpty(brand)){
			LengthAttributes reqAttr = new LengthAttributes();
			reqAttr.setFieldName(BRAND);
			reqAttr.setFieldValue(brand);
			reqAttr.setLength(BRAND_FIXED_LENGTH);
			reqAttrsForValidation.add(reqAttr);
		}
		String responseFormat = (request.getResponseFormat());
		if(!StringUtils.isEmpty(responseFormat)){
			LengthAttributes reqAttr = new LengthAttributes();
			reqAttr.setFieldName(RESPONSE_FORMAT);
			reqAttr.setFieldValue(responseFormat);
			reqAttr.setLength(RESPONSE_FORMAT_FIXED_LENGTH);
			reqAttrsForValidation.add(reqAttr);
		}
		String formatted = (request.getFormatted());
		if(!StringUtils.isEmpty(formatted)){
			LengthAttributes reqAttr = new LengthAttributes();
			reqAttr.setFieldName(FORMATTED);
			reqAttr.setFieldValue(formatted);
			reqAttr.setLength(FORMATTED_FIXED_LENGTH);
			reqAttrsForExactValidation.add(reqAttr);
		}
		String startingRow = (request.getStartingRow());
		if(!StringUtils.isEmpty(startingRow)){
			LengthAttributes reqAttr = new LengthAttributes();
			reqAttr.setFieldName(STARTING_ROW);
			reqAttr.setFieldValue(startingRow);
			reqAttr.setLength(STARTING_ROW_FIXED_LENGTH);
			reqAttrsForValidation.add(reqAttr);
		}
		String maxRows = (request.getMaxRows());
		if(!StringUtils.isEmpty(maxRows)){
			LengthAttributes reqAttr = new LengthAttributes();
			reqAttr.setFieldName(MAX_ROWS);
			reqAttr.setFieldValue(maxRows);
			reqAttr.setLength(MAX_ROWS_FIXED_LENGTH);
			reqAttrsForValidation.add(reqAttr);
		}
		String parentProductTypeId = (request.getParentProductTypeId());
		if(!StringUtils.isEmpty(parentProductTypeId)){
			LengthAttributes reqAttr = new LengthAttributes();
			reqAttr.setFieldName(PARENT_PRODUCT_TYPE_ID);
			reqAttr.setFieldValue(parentProductTypeId);
			reqAttr.setLength(PARENT_PRODUCT_TYPE_ID_FIXED_LENGTH);
			reqAttrsForValidation.add(reqAttr);
		}
		String productTypeId = (request.getProductTypeId());
		
		if(!StringUtils.isEmpty(productTypeId)){
			
			String[] productTypeIds = productTypeId.split(COMMA);
			
			for (int i = 0; i < productTypeIds.length; i++) {
				LengthAttributes reqAttr = new LengthAttributes();
				reqAttr.setFieldName(PRODUCT_TYPE_ID);
				reqAttr.setFieldValue(productTypeIds[i]);
				reqAttr.setLength(PRODUCT_TYPE_ID_FIXED_LENGTH);
				reqAttrsForValidation.add(reqAttr);
			}
				
		/*	LengthAttributes reqAttr = new LengthAttributes();
			reqAttr.setFieldName(PRODUCT_TYPE_ID);
			reqAttr.setFieldValue(productTypeId);
			reqAttr.setLength(PRODUCT_TYPE_ID_FIXED_LENGTH);
			reqAttrsForValidation.add(reqAttr);*/
		}
		String subProductTypeId = (request.getSubProductTypeId());
		if(!StringUtils.isEmpty(subProductTypeId)){
			LengthAttributes reqAttr = new LengthAttributes();
			reqAttr.setFieldName(SUB_PRODUCT_TYPE_ID);
			reqAttr.setFieldValue(subProductTypeId);
			reqAttr.setLength(SUB_PRODUCT_TYPE_ID_FIXED_LENGTH);
			reqAttrsForValidation.add(reqAttr);
		}
		String sortBy = (request.getSortBy());
		if(!StringUtils.isEmpty(sortBy)){
			LengthAttributes reqAttr = new LengthAttributes();
			reqAttr.setFieldName(SORT_BY);
			reqAttr.setFieldValue(sortBy);
			reqAttr.setLength(SORT_BY_FIXED_LENGTH);
			reqAttrsForValidation.add(reqAttr);
		}
		if(reqAttrsForExactValidation != null & !reqAttrsForExactValidation.isEmpty()){
			fieldValidationMessages = lengthExactValidation(reqAttrsForExactValidation);
			if(fieldValidationMessages!= null && !fieldValidationMessages.isEmpty())
				messages.addAll(fieldValidationMessages);
		}
		if(reqAttrsForValidation != null & !reqAttrsForValidation.isEmpty()){
			fieldValidationMessages = lengthValidation(reqAttrsForValidation);
			if(fieldValidationMessages!= null && !fieldValidationMessages.isEmpty())
				messages.addAll(fieldValidationMessages);
		}
		if(messages!=null &&!messages.isEmpty())
			return CommonUtils.buildValidatorOutput(DATA_VALIDATION_ERROR, messages);

		msg = maxRowsGreaterThanDefaultValidation(request.getMaxRows(), CATALOG);
		if(!StringUtils.isEmpty(msg)){
			messages.add(msg);
			return CommonUtils.buildValidatorOutput(SUCCESS, messages);
		}

		messages.add(SUCCESS_MSG);

		
		return CommonUtils.buildValidatorOutput(SUCCESS, messages);
	}
	
}

