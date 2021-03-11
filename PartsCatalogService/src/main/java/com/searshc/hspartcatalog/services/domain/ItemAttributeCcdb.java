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
public class ItemAttributeCcdb {

	private Integer attributeId;
	
	public Integer getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("attributeId", attributeId);
		return builder.toString();
	}
	
	
}
