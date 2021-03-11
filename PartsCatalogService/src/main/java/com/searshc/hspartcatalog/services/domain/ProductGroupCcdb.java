/**
 * 
 */
package com.searshc.hspartcatalog.services.domain;

public class ProductGroupCcdb {

	private String productGroupId;
	private String supplierId;
	private String divPls;

	public String getProductGroupId() {
		return productGroupId;
	}
	public void setProductGroupId(String div) {
		this.productGroupId = div;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String pls) {
		this.supplierId = pls;
	}
	
	public String getDivPls() {
		return divPls;
	}
	public void setDivPls(String divPls) {
		this.divPls = divPls;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductGroupCcdb [productGroupId=");
		builder.append(productGroupId);
		builder.append(", supplierId=");
		builder.append(supplierId);
		builder.append(", divPls=");
		builder.append(divPls);
		builder.append("]");
		return builder.toString();
	}
	
}
