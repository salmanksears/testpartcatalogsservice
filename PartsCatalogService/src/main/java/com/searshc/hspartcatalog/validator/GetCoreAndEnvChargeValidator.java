package com.searshc.hspartcatalog.validator;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.DIV_NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.DIV_NO_MAX_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PART_DETAIL_NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PART_DETAIL_NO_MAX_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PLS_NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PLS_NO_MAX_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_CODE.DATA_VALIDATION_ERROR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_CODE.SUCCESS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.searshc.hspartcatalog.pojo.LengthAttributes;
import com.searshc.hspartcatalog.pojo.ValidatorOutput;
import com.searshc.hspartcatalog.services.domain.PartDetail;
import com.searshc.hspartcatalog.services.domain.Trans;
import com.searshc.hspartcatalog.services.vo.request.GetCoreAndEnvChargeAmountRequest;
import com.searshc.hspartcatalog.util.CommonUtils;

public class GetCoreAndEnvChargeValidator extends CommonValidator implements Validator<GetCoreAndEnvChargeAmountRequest>{
	
	private static List<String> validTranCodes = Arrays.asList("A","P","S","B");
	
	@Override
	public ValidatorOutput validate(GetCoreAndEnvChargeAmountRequest request) {
		Trans trans = request.getTrans();
		String transCode = trans.getTransCode();
		String stateCode = trans.getStateCode();
		List<PartDetail> partDetails = request.getPartDetails();
		List<String> messages = new ArrayList<String>();
		
		if(StringUtils.isNotBlank(transCode) && transCode.length() == 1){
			if(validTranCodes.contains(transCode)){
					if(transCode.equals("S") || transCode.equals("B")){
						if(StringUtils.isNotBlank(stateCode)&& stateCode.length() == 2){
							if(transCode.equals("B")){
								if(partDetails != null && partDetails.size() >= 1){
									validatePartDetail(partDetails, messages);
								}else{
									messages.add("At least one part should be present in request when transCode is:"+ transCode+".");
								}
							}
						}else{
							messages.add("stateCode cannot be blank and length should be two.");
						}
					}
					else if(transCode.equals("P")){
						if(CollectionUtils.isEmpty(partDetails)){
							messages.add("At least one part should be present in request when transCode is:"+ transCode+".");
						}else{
							validatePartDetail(partDetails, messages);
						}
					}
			}
		}else{
			messages.add("transCode cannot be blank and length should be one.");
		}
		
		if(CollectionUtils.isEmpty(messages)){
			return CommonUtils.buildValidatorOutput(SUCCESS, messages);
		} else {
			return CommonUtils.buildValidatorOutput(DATA_VALIDATION_ERROR, messages);
		}
	}
	
	private void validatePartDetail(List<PartDetail> partDetails, List<String> messages){
		List<String> fieldValidationMessages = null;
		for(int prt=0;  prt < partDetails.size(); prt++){
			//new list for each partDetail
			List<LengthAttributes> reqAttrsForValidation = new ArrayList<LengthAttributes>();
			PartDetail prtDetail = partDetails.get(prt);
			String divNo = prtDetail.getDivNo();
			String plsNo = prtDetail.getPlsNo();
			String partNo = prtDetail.getPartNo();
			
			Map<String, String> fieldsMap = new HashMap<String, String>();
			fieldsMap.put(DIV_NO, divNo);
			fieldsMap.put(PLS_NO, plsNo);
			fieldsMap.put(PART_DETAIL_NO, partNo);

			String msg = mandatoryFieldValidation(fieldsMap);
			if(!StringUtils.isEmpty(msg))
				messages.add(msg);
			if(!StringUtils.isEmpty(divNo)){
				divNo = divNo.trim();
				LengthAttributes reqAttr = new LengthAttributes();
				reqAttr.setFieldName(DIV_NO);
				reqAttr.setFieldValue(divNo);
				reqAttr.setLength(DIV_NO_MAX_LENGTH);
				reqAttrsForValidation.add(reqAttr);
			}
			if(!StringUtils.isEmpty(plsNo)){
				plsNo = plsNo.trim();
				LengthAttributes reqAttr = new LengthAttributes();
				reqAttr.setFieldName(PLS_NO);
				reqAttr.setFieldValue(plsNo);
				reqAttr.setLength(PLS_NO_MAX_LENGTH);
				reqAttrsForValidation.add(reqAttr);
			}
			if(!StringUtils.isEmpty(partNo)){
				partNo = partNo.trim();
				LengthAttributes reqAttr = new LengthAttributes();
				reqAttr.setFieldName(PART_DETAIL_NO);
				reqAttr.setFieldValue(partNo);
				reqAttr.setLength(PART_DETAIL_NO_MAX_LENGTH);
				reqAttrsForValidation.add(reqAttr);
			}
			if(! CollectionUtils.isEmpty(reqAttrsForValidation)){
				fieldValidationMessages = lengthValidation(reqAttrsForValidation);
				if(! CollectionUtils.isEmpty(fieldValidationMessages)){
					messages.add(String.format("Validation failed for Part Detail: %s-%s-%s",divNo, plsNo, partNo));
					messages.addAll(fieldValidationMessages);
				}
			}
		}
	}
}
