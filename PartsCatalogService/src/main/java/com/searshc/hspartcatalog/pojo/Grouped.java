/**
 * 
 */
package com.searshc.hspartcatalog.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.searshc.hs.psc.logging.CustomToStringStyle;

/**
 * @author ajain0
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Grouped {
	
	@JsonProperty("brand")	
	private BrandSolr brand;
	
	@JsonProperty("parentProductTypeName")	
	private ParentProductTypeSolr parentProductTypeName;
	
	@JsonProperty("productGroupName")	
	private ProductGroupNameSolr productGroupName;

	public BrandSolr getBrand() {
		return brand;
	}

	public void setBrand(BrandSolr brand) {
		this.brand = brand;
	}

	public ParentProductTypeSolr getParentProductTypeName() {
		return parentProductTypeName;
	}

	public void setParentProductTypeName(ParentProductTypeSolr parentProductTypeName) {
		this.parentProductTypeName = parentProductTypeName;
	}

	public ProductGroupNameSolr getProductGroupName() {
		return productGroupName;
	}

	public void setProductGroupName(ProductGroupNameSolr productGroupName) {
		this.productGroupName = productGroupName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("brand", brand);
		builder.append("parentProductTypeName", parentProductTypeName);
		builder.append("productGroupName", productGroupName);
		return builder.toString();
	}
}
