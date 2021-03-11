/**
 * 
 */
package com.searshc.hspartcatalog.validator;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.CATALOG;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.BRAND;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.DESCRIPTION;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MAX_ROWS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MAX_ROWS_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MODEL_NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PART_NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PRODUCT_TYPE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.SORT_BY;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.SORT_BY_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.STARTING_ROW;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.STARTING_ROW_FIXED_LENGTH;
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
import com.searshc.hspartcatalog.services.vo.request.ItemDescriptionSearchRequest;
import com.searshc.hspartcatalog.util.CommonUtils;

public class ItemDescriptionSearchRequestValidator extends CommonValidator implements Validator<ItemDescriptionSearchRequest>{

	@Override
	public ValidatorOutput validate(ItemDescriptionSearchRequest request) {
		
		List<LengthAttributes> reqAttrsForExactValidation = new ArrayList<LengthAttributes>();
		List<LengthAttributes> reqAttrsForValidation = new ArrayList<LengthAttributes>();
		List<String> fieldValidationMessages = new ArrayList<String>();
		List<String> messages = new ArrayList<String>();
		List<String> msgList = new ArrayList<String>();
		String msg = null;
		
		/* Mandatory field validation*/ 
		Map<String, String> fieldsMap = new HashMap<String, String>();
		fieldsMap.put(BRAND, request.getBrand());
		fieldsMap.put(PRODUCT_TYPE, request.getProductType());
		fieldsMap.put(MODEL_NO, request.getModelNo());
		fieldsMap.put(DESCRIPTION, request.getDescription());
		
		msg = mandatoryFieldValidation(fieldsMap);
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

		msgList = sortByValidation(request.getSortBy(), PART_NO);
		if(msgList!=null && !msgList.isEmpty())
			messages.addAll(msgList);

		fieldsMap.remove(BRAND);
		fieldsMap.remove(PRODUCT_TYPE);
		fieldsMap.remove(MODEL_NO);
		fieldsMap.remove(DESCRIPTION);

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
