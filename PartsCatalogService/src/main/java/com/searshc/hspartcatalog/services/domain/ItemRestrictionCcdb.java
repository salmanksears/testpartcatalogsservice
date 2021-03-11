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
public class ItemRestrictionCcdb {

	private Integer restrictionId;

	public Integer getRestrictionId() {
		return restrictionId;
	}
	public void setRestrictionId(Integer restrictionId) {
		this.restrictionId = restrictionId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("restrictionId", restrictionId);
		return builder.toString();
	}
	
}
