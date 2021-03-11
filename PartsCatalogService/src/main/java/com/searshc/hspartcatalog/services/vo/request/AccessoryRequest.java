/**
 * 
 */
package com.searshc.hspartcatalog.services.vo.request;

import com.searshc.hspartcatalog.services.vo.base.BaseRequest;

public class AccessoryRequest extends BaseRequest {
	
	private String productTypeId;
	private String brandId;
	private String modelNo;
	private String responseFormat;
	private String maxRows;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccessoryRequest [productTypeId=").append(productTypeId).append(", brandId=").append(brandId)
				.append(", modelNo=").append(modelNo).append(", responseFormat=").append(responseFormat)
				.append(", maxRows=").append(maxRows).append("]");
		return builder.toString();
	}
	
	public String getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	public String getResponseFormat() {
		return responseFormat;
	}
	public void setResponseFormat(String responseFormat) {
		this.responseFormat = responseFormat;
	}
	public String getMaxRows() {
		return maxRows;
	}
	public void setMaxRows(String maxRows) {
		this.maxRows = maxRows;
	}

	
}
