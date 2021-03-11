/**
 * 
 */
package com.searshc.hspartcatalog.validator;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.BRAND;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.BRAND_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.COUNT;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.RESPONSE_FORMAT;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.RESPONSE_FORMAT_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.SORT_BY;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.SORT_BY_FIXED_LENGTH;
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
import com.searshc.hspartcatalog.services.vo.request.GetBrandsRequest;
import com.searshc.hspartcatalog.util.CommonUtils;

/**
* Title			:   GetBrandsRequestValidator
* Description	:	this class validates GetBrandsRequest. Validation includes mandatory params, default values, datatype, 
* 					alpha space, length etc.  
* @author		:	Abhishek Jain
*/
public class GetBrandsRequestValidator extends CommonValidator implements Validator<GetBrandsRequest>{
	@Override
	public ValidatorOutput validate(GetBrandsRequest request) {
		
		List<LengthAttributes> reqAttrsForExactValidation = new ArrayList<LengthAttributes>();
		List<LengthAttributes> reqAttrsForValidation = new ArrayList<LengthAttributes>();
		List<String> fieldValidationMessages = new ArrayList<String>();
		List<String> messages = new ArrayList<String>();
		List<String> msgList = new ArrayList<String>();
		String msg = null;
		
		/* Mandatory field validation*/ 
		
		Map<String, String> fieldsMap = new HashMap<String, String>();
		fieldsMap.put(BRAND, request.getBrand());

		msg = mandatoryFieldValidation(fieldsMap);
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);
		
		/* common validation*/
		
		msg = responseFormatValidation(request.getResponseFormat());
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);
		
		msgList = sortByValidation(request.getSortBy(), BRAND, COUNT);
		if(msgList!=null && !msgList.isEmpty())
			messages.addAll(msgList);


/*		 Field length validation*/ 

		String brand = (request.getBrand());
		if(!StringUtils.isEmpty(brand)){
			LengthAttributes respAttr = new LengthAttributes();
			respAttr.setFieldName(BRAND);
			respAttr.setFieldValue(brand);
			respAttr.setLength(BRAND_FIXED_LENGTH);
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
