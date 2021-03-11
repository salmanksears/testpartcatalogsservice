package com.searshc.hspartcatalog.domain.discount;

public class DiscountItem {

	private String itemId;
	private String itemSellingPrice;
	private boolean itemOverridePriceFl;
	private String subItemId;
	private String subIitemSellingPrice;
	private boolean subItemOverridePriceFl;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DiscountItem [itemId=");
		builder.append(itemId);
		builder.append(", itemSellingPrice=");
		builder.append(itemSellingPrice);
		builder.append(", itemOverridePriceFl=");
		builder.append(itemOverridePriceFl);
		builder.append(", subItemId=");
		builder.append(subItemId);
		builder.append(", subIitemSellingPrice=");
		builder.append(subIitemSellingPrice);
		builder.append(", subItemOverridePriceFl=");
		builder.append(subItemOverridePriceFl);
		builder.append("]");
		return builder.toString();
	}

	public boolean isSubbed() {
		if (subItemId != null && subItemId.trim().length() > 0)
			return true;
		else
			return false;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemSellingPrice() {
		return itemSellingPrice;
	}

	public void setItemSellingPrice(String itemSellingPrice) {
		this.itemSellingPrice = itemSellingPrice;
	}

	public boolean isItemOverridePriceFl() {
		return itemOverridePriceFl;
	}

	public void setItemOverridePriceFl(boolean itemOverridePriceFl) {
		this.itemOverridePriceFl = itemOverridePriceFl;
	}

	public String getSubItemId() {
		return subItemId;
	}

	public void setSubItemId(String subItemId) {
		this.subItemId = subItemId;
	}

	public String getSubIitemSellingPrice() {
		return subIitemSellingPrice;
	}

	public void setSubIitemSellingPrice(String subIitemSellingPrice) {
		this.subIitemSellingPrice = subIitemSellingPrice;
	}

	public boolean isSubItemOverridePriceFl() {
		return subItemOverridePriceFl;
	}

	public void setSubItemOverridePriceFl(boolean subItemOverridePriceFl) {
		this.subItemOverridePriceFl = subItemOverridePriceFl;
	}
}
