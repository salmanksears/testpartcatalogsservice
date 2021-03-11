package com.searshc.hspartcatalog.domain.discount;

import java.math.BigDecimal;
import java.util.Date;

public class ChannelPrice {
	
	private String itemNo;
	private BigDecimal price;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChannelItemPrice [itemNo=");
		builder.append(itemNo);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}
	
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
