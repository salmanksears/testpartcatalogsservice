package com.searshc.hspartcatalog.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class DiscountUtils implements DiscountConstants {

	private static DecimalFormat DEFAULT_DOLLAR_FORMATTER = new DecimalFormat(DEFAULT_DOLLAR_FORMAT_PATTERN);

	public static String bigDecimalToString(BigDecimal bd) {
		if(bd == null)
			return null;
		
		return DEFAULT_DOLLAR_FORMATTER.format(bd);
	}
	
	public static BigDecimal stringToBigDecimal(String str) {
		if(StringUtils.isBlank(str) || !NumberUtils.isNumber(str))
			return null;
		
		return new BigDecimal(str);
	}

	public static boolean isValidOverride(BigDecimal price, BigDecimal minPrice) {

		if (price == null)
			return false;

		if (price.compareTo(ZERO) <= 0)
			return false;

		if (price.compareTo(minPrice) <= 0)
			return false;

		return true;
	}
}