/**
 * 
 */
package com.searshc.hspartcatalog.pojo;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.searshc.hs.psc.logging.CustomToStringStyle;

/**
 * @author ajain0
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FacetFields {
	
	@JsonProperty("brand")	
	private String[] brand;
	
	@JsonProperty("brandId")	
	private String[] brandId;
	
	@JsonProperty("productTypeId")	
	private String[] productTypeId;
	
	
	public String[] getBrand() {
		return brand;
	}
	public String[] getBrandId() {
		return brandId;
	}
	public String[] getProductTypeId() {
		return productTypeId;
	}
	public void setBrand(String[] brand) {
		this.brand = brand;
	}
	public void setBrandId(String[] brandId) {
		this.brandId = brandId;
	}
	public void setProductTypeId(String[] productTypeId) {
		this.productTypeId = productTypeId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("brand", Arrays.toString(brand));
		builder.append("brandId",Arrays.toString(brandId));
		builder.append("productTypeId", Arrays.toString(productTypeId));
		return builder.toString();
	}

}
