package com.searshc.hspartcatalog.util;

import java.math.BigDecimal;

public interface DiscountConstants {
	
	public static final String COST_PLUS = "CostPlus";
	public static final String LIST_MINUS = "ListMinus";
	public static final String LIST_MINUS2 = "ListMinus2";
	
	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100).setScale(2);
	public static final BigDecimal ONE  = BigDecimal.ONE.setScale(2);
	public static final BigDecimal ZERO = BigDecimal.ZERO.setScale(2);
	
	public static final String DEFAULT_DOLLAR_FORMAT_PATTERN = "0.00";
}
