/**
 * 
 */
package com.searshc.hspartcatalog.services.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;


/**
 * @author ajain0
 *
 */
public class BrandCcdb {
	
	private String brandId;
	private String brand;

	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("brandId", brandId);
		builder.append("brand", brand);
		return builder.toString();
	}
	
	
}
