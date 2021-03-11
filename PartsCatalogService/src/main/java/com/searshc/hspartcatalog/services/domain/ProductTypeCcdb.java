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
public class ProductTypeCcdb {

	private String productTypeCd;

	public String getProductTypeCd() {
		return productTypeCd;
	}
	public void setProductTypeCd(String productTypeCd) {
		this.productTypeCd = productTypeCd;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("productTypeCd", productTypeCd);
		return builder.toString();
	}
	
	
}
