package com.searshc.hspartcatalog.domain.discount;

import com.searshc.hspartcatalog.util.DiscountConstants;

public class ClientDiscount {

	private String businessCd;
	private String discountType;
	private String discountPercent;
	private String listMinusType;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Discount [businessCd=");
		builder.append(businessCd);
		builder.append(", discountType=");
		builder.append(discountType);
		builder.append(", discountPercent=");
		builder.append(discountPercent);
		builder.append(", listMinusType=");
		builder.append(listMinusType);
		builder.append("]");
		return builder.toString();
	}

	public String getBusinessCd() {
		return businessCd;
	}

	public void setBusinessCd(String businessCd) {
		this.businessCd = businessCd;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public String getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(String discountPercent) {
		this.discountPercent = discountPercent;
	}

	public String getListMinusType() {
		return listMinusType;
	}

	public void setListMinusType(String listMinusType) {
		this.listMinusType = listMinusType;
	}
	public boolean isCostPlus() {
		if (discountType != null && discountType.compareTo(DiscountConstants.COST_PLUS) == 0)
			return true;
		else
			return false;
	}
	public boolean isListMinus() {
		if (discountType != null && discountType.compareTo(DiscountConstants.LIST_MINUS) == 0)
			return true;
		else
			return false;
	}
	public boolean isListMinus2() {
		if (discountType != null && discountType.compareTo(DiscountConstants.LIST_MINUS2) == 0)
			return true;
		else
			return false;
	}
}
