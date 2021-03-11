/**
 * 
 */
package com.searshc.hspartcatalog.services.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;

@XmlType(name = "attribute", propOrder = {
		"attributeId",
		"attributeName", 
		"attributeValue"
}, namespace="http://services.hspartcatalog.searshc.com/domain/")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemAttribute {
	
	@XmlElement
	private String attributeId;
	
	@XmlElement
	private String attributeName;
	
	@XmlElement
	private String attributeValue;

	public String getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("attributeId", attributeId);
		builder.append("attributeName", attributeName);
		builder.append("attributeValue", attributeValue);
		return builder.toString();
	}
	
}
