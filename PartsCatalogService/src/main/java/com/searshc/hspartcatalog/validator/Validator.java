package com.searshc.hspartcatalog.validator;

import com.searshc.hspartcatalog.pojo.ValidatorOutput;

public interface Validator<T> {
	public ValidatorOutput validate(T t);
}
