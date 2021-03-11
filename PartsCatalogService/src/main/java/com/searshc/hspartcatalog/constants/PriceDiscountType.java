package com.searshc.hspartcatalog.constants;

public enum PriceDiscountType {
	LIST_MINUS("List Minus"),
	COST_PLUS("Cost Plus");
	
	private String type;
	
	PriceDiscountType(String type){
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
