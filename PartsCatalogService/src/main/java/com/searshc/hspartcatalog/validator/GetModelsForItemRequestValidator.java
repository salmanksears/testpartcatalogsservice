/**
 * 
 */
package com.searshc.hspartcatalog.validator;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.ITEM_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.ITEM_ID_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.RESPONSE_FORMAT;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.RESPONSE_FORMAT_FIXED_LENGTH;
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
import com.searshc.hspartcatalog.services.vo.request.GetModelsForItemRequest;
import com.searshc.hspartcatalog.util.CommonUtils;

/**
* Title			:   GetModelsForItemRequestValidator
* Description	:	this class validates GetModelsForItemRequest. Validation includes mandatory params, default values, datatype, 
* 					alpha space, length etc.  
* @author		:	Abhishek Jain
*/

public class GetModelsForItemRequestValidator extends CommonValidator implements Validator<GetModelsForItemRequest>{

	@Override
	public ValidatorOutput validate(GetModelsForItemRequest request) {
		
		List<LengthAttributes> reqAttrsForExactValidation = new ArrayList<LengthAttributes>();
		List<LengthAttributes> reqAttrsForValidation = new ArrayList<LengthAttributes>();
		List<String> fieldValidationMessages = new ArrayList<String>();
		List<String> messages = new ArrayList<String>();
		String msg = null;
		
		/* Mandatory field validation*/ 
		
		Map<String, String> fieldsMap = new HashMap<String, String>();
		fieldsMap.put(ITEM_ID, request.getItemId());

		msg = mandatoryFieldValidation(fieldsMap);
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);
		
		/* common validation*/
		
		msg = responseFormatValidation(request.getResponseFormat());
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);
		
		/*wildcard verification*/
		
		msg = wildCardValidation(fieldsMap);
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);
		
/*		 Field length validation*/ 

		String itemId = (request.getItemId());
		if(!StringUtils.isEmpty(itemId)){
			LengthAttributes respAttr = new LengthAttributes();
			respAttr.setFieldName(ITEM_ID);
			respAttr.setFieldValue(itemId);
			respAttr.setLength(ITEM_ID_FIXED_LENGTH);
			reqAttrsForValidation.add(respAttr);
		}
		String responseFormat = (request.getResponseFormat());
		if(!StringUtils.isEmpty(responseFormat)){
			LengthAttributes respAttr = new LengthAttributes();
			respAttr.setFieldName(RESPONSE_FORMAT);
			respAttr.setFieldValue(responseFormat);
			respAttr.setLength(RESPONSE_FORMAT_FIXED_LENGTH);
			reqAttrsForValidation.add(respAttr);
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
		else
			messages.add(SUCCESS_MSG);
		
		return CommonUtils.buildValidatorOutput(SUCCESS, messages);
	}
	
}
