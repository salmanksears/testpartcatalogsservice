/**
 * 
 */
package com.searshc.hspartcatalog.services.vo.request;

import com.searshc.hspartcatalog.services.vo.base.BaseRequest;

public class ItemDescriptionSearchRequest  extends BaseRequest{
	
	private String brand;
	private String productType;
	private String modelNo;
	private String description;
	private String responseFormat;
	private String sortBy;
	private String startingRow;
	private String maxRows;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getResponseFormat() {
		return responseFormat;
	}
	public void setResponseFormat(String responseFormat) {
		this.responseFormat = responseFormat;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getStartingRow() {
		return startingRow;
	}
	public void setStartingRow(String startingRow) {
		this.startingRow = startingRow;
	}
	public String getMaxRows() {
		return maxRows;
	}
	public void setMaxRows(String maxRows) {
		this.maxRows = maxRows;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DescriptionSearchRequest [brand=");
		builder.append(brand);
		builder.append(", productType=");
		builder.append(productType);
		builder.append(", modelNo=");
		builder.append(modelNo);
		builder.append(", description=");
		builder.append(description);
		builder.append(", responseFormat=");
		builder.append(responseFormat);
		builder.append(", sortBy=");
		builder.append(sortBy);
		builder.append(", startingRow=");
		builder.append(startingRow);
		builder.append(", maxRows=");
		builder.append(maxRows);
		builder.append("]");
		return builder.toString();
	}
	
}
