/**
 * 
 */
package com.searshc.hspartcatalog.validator;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.ONE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.ITEM_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.ITEM_ID_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MODEL_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MODEL_ID_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MODEL_NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MODEL_NO_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PART_NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PART_NO_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.RESPONSE_FORMAT;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.RESPONSE_FORMAT_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.SORT_BY;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.SORT_BY_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.SCHEMATIC_NAME;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_CODE.DATA_VALIDATION_ERROR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_CODE.SUCCESS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.INVALID_NUMBER_OF_REQUEST_PARAMS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.SUCCESS_MSG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.searshc.hspartcatalog.pojo.LengthAttributes;
import com.searshc.hspartcatalog.pojo.ValidatorOutput;
import com.searshc.hspartcatalog.services.vo.request.GetSchematicsRequest;
import com.searshc.hspartcatalog.util.CommonUtils;

/**
* Title			:   GetSchematicsRequestValidator
* Description	:	this class validates GetSchematicsRequest. Validation includes mandatory params, default values, datatype, 
* 					alpha space, length etc.  
* @author		:	Abhishek Jain
*/

public class GetSchematicsRequestValidator extends CommonValidator implements Validator<GetSchematicsRequest>{

	@Override
	public ValidatorOutput validate(GetSchematicsRequest request) {

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
		
		if((request.getModelNo()== null && request.getModelId() == null))
				messages.add(String.format(INVALID_NUMBER_OF_REQUEST_PARAMS, ONE));
		
		/* common validation*/
		
		msg = responseFormatValidation(request.getResponseFormat());
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);
		
		msgList = sortByValidation(request.getSortBy(), SCHEMATIC_NAME);
		if(msgList!=null && !msgList.isEmpty())
			messages.addAll(msgList);

		/*wildcard verification*/
		
		msg = wildCardValidation(fieldsMap);
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);
		
		/* Field length validation*/ 

		String modelNo = (request.getModelNo());
		if(!StringUtils.isEmpty(modelNo)){
			LengthAttributes respAttr = new LengthAttributes();
			respAttr.setFieldName(MODEL_NO);
			respAttr.setFieldValue(modelNo);
			respAttr.setLength(MODEL_NO_FIXED_LENGTH);
			reqAttrsForValidation.add(respAttr);
		}
		String modelId = (request.getModelId());
		if(!StringUtils.isEmpty(modelId)){
			LengthAttributes respAttr = new LengthAttributes();
			respAttr.setFieldName(MODEL_ID);
			respAttr.setFieldValue(modelId);
			respAttr.setLength(MODEL_ID_FIXED_LENGTH);
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
		String sortBy = (request.getSortBy());
		if(!StringUtils.isEmpty(sortBy)){
			LengthAttributes respAttr = new LengthAttributes();
			respAttr.setFieldName(SORT_BY);
			respAttr.setFieldValue(sortBy);
			respAttr.setLength(SORT_BY_FIXED_LENGTH);
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
