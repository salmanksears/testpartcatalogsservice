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
public class ItemAvailabilityStatusCcdb {

	private String itemAvailabilityStatusCd;

	public String getItemAvailabilityStatusCd() {
		return itemAvailabilityStatusCd;
	}

	public void setItemAvailabilityStatusCd(String itemAvailabilityStatusCd) {
		this.itemAvailabilityStatusCd = itemAvailabilityStatusCd;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("itemAvailabilityStatusCd", itemAvailabilityStatusCd);
		return builder.toString();
	}
	
	
}
