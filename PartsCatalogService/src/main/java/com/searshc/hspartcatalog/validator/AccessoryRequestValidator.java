/**
 * 
 */
package com.searshc.hspartcatalog.validator;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.BRAND_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MAX_ROWS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MAX_ROWS_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MODEL_NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PRODUCT_TYPE_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_CODE.SUCCESS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.SUCCESS_MSG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.searshc.hspartcatalog.pojo.LengthAttributes;
import com.searshc.hspartcatalog.pojo.ValidatorOutput;
import com.searshc.hspartcatalog.services.vo.request.AccessoryRequest;
import com.searshc.hspartcatalog.util.CommonUtils;

public class AccessoryRequestValidator extends CommonValidator implements Validator<AccessoryRequest>{

	@Override
	public ValidatorOutput validate(AccessoryRequest request) {
		
		List<LengthAttributes> reqAttrsForValidation = new ArrayList<LengthAttributes>();
		List<String> messages = new ArrayList<String>();
		List<String> msgList = new ArrayList<String>();
		String msg = null;
		
		
		Map<String, String> fieldsMap = new HashMap<String, String>();
		fieldsMap.put(PRODUCT_TYPE_ID, request.getModelNo());
		fieldsMap.put(BRAND_ID, request.getModelNo());
		fieldsMap.put(MODEL_NO, request.getModelNo());

		msg = mandatoryFieldValidation(fieldsMap);
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);
			
		msg = responseFormatValidation(request.getResponseFormat());
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);
		
		msg = maxRowsValidation(request.getMaxRows());
		if(!StringUtils.isEmpty(msg))
			messages.add(msg);

		if(msgList!=null && !msgList.isEmpty())
			messages.addAll(msgList);

		String maxRows = (request.getMaxRows());
		if(!StringUtils.isEmpty(maxRows)){
			LengthAttributes reqAttr = new LengthAttributes();
			reqAttr.setFieldName(MAX_ROWS);
			reqAttr.setFieldValue(maxRows);
			reqAttr.setLength(MAX_ROWS_FIXED_LENGTH);
			reqAttrsForValidation.add(reqAttr);
		}
	
		if(!StringUtils.isEmpty(msg)){
			messages.add(msg);
			return CommonUtils.buildValidatorOutput(SUCCESS, messages);
		}

		messages.add(SUCCESS_MSG);
		
		return CommonUtils.buildValidatorOutput(SUCCESS, messages);
	}
}

