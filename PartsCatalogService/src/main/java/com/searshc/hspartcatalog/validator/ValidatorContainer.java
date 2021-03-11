package com.searshc.hspartcatalog.validator;

import java.util.Map;

@SuppressWarnings("unchecked")
public class ValidatorContainer {
	
	private Map<String, Validator> validatorMap;

	public Map<String, Validator> getValidatorMap() {
		return validatorMap;
	}

	public void setValidatorMap(Map<String, Validator> validatorMap) {
		this.validatorMap = validatorMap;
	}
	
	public Validator getValidator(String key) {
		return validatorMap.get(key);
	}
}
