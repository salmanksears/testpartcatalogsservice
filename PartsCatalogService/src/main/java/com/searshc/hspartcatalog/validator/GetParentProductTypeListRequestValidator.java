/**
 * 
 */
package com.searshc.hspartcatalog.validator;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.CACHE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.BRAND_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.BRAND_ID_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MAX_ROWS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MAX_ROWS_FIXED_LENGTH_CACHE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.RESPONSE_FORMAT;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.RESPONSE_FORMAT_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.STARTING_ROW;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.STARTING_ROW_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.VERSION_DATE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.VERSION_DATE_FIXED_LENGTH;
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
import com.searshc.hspartcatalog.services.vo.request.GetParentProductTypeListRequest;
import com.searshc.hspartcatalog.util.CommonUtils;

/**
* Title			:   GetParentProductTypeListRequestValidator
* Description	:	this class validates GetParentProductTypeListRequest. Validation includes mandatory params, default values, datatype, 
* 					alpha space, length etc.  
* @author		:	Abhishek Jain
*/

public class GetParentProductTypeListRequestValidator extends CommonValidator implements Validator<GetParentProductTypeListRequest>{

	@Override
	public ValidatorOutput validate(GetParentProductTypeListRequest request) {
		
		List<LengthAttributes> reqAttrsForExactValidation = new ArrayList<LengthAttributes>();
		List<LengthAttributes> reqAttrsForValidation = new ArrayList<LengthAttributes>();
		List<String> fieldValidationMessages = new ArrayList<String>();
		List<String> messages = new ArrayList<String>();
		String msg = null;
		
		Map<String, String> fieldsMap = new HashMap<String, String>();
		fieldsMap.put(BRAND_ID, request.getBrandId());

		/* common validation*/
		
		msg = responseFormatValidation(request.getResponseFormat());
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);
		
		msg = startingRowValidation(request.getStartingRow());
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);

		msg = maxRowsValidation(request.getMaxRows());
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);

		msg = versionDateValidation(request.getVersionDate());
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);
		
		/* AlphaSpace check */
		
		msg = spaceValidation(fieldsMap);
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);
		
		/*wildcard verification*/
		
		msg = wildCardValidation(fieldsMap);
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);
		
/*		 Field length validation*/ 
		String brandId = request.getBrandId();
		if(!StringUtils.isEmpty(brandId)){
			LengthAttributes reqAttr = new LengthAttributes();
			reqAttr.setFieldName(BRAND_ID);
			reqAttr.setFieldValue(brandId);
			reqAttr.setLength(BRAND_ID_FIXED_LENGTH);
			reqAttrsForValidation.add(reqAttr);
		}
		String versionDate = request.getVersionDate();
		if(!StringUtils.isEmpty(versionDate)){
			LengthAttributes reqAttr = new LengthAttributes();
			reqAttr.setFieldName(VERSION_DATE);
			reqAttr.setFieldValue(versionDate);
			reqAttr.setLength(VERSION_DATE_FIXED_LENGTH);
			reqAttrsForExactValidation.add(reqAttr);
		}
		String responseFormat = (request.getResponseFormat());
		if(!StringUtils.isEmpty(responseFormat)){
			LengthAttributes reqAttr = new LengthAttributes();
			reqAttr.setFieldName(RESPONSE_FORMAT);
			reqAttr.setFieldValue(responseFormat);
			reqAttr.setLength(RESPONSE_FORMAT_FIXED_LENGTH);
			reqAttrsForValidation.add(reqAttr);
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
			reqAttr.setLength(MAX_ROWS_FIXED_LENGTH_CACHE);
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
		
		msg = maxRowsGreaterThanDefaultValidation(request.getMaxRows(), CACHE);
		if(!StringUtils.isEmpty(msg)){
			messages.add(msg);
			return CommonUtils.buildValidatorOutput(SUCCESS, messages);
		}

		messages.add(SUCCESS_MSG);

		return CommonUtils.buildValidatorOutput(SUCCESS, messages);
	}
	
}
