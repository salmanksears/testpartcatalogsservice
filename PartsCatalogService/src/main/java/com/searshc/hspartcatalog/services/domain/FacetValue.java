package com.searshc.hspartcatalog.services.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;


/**
 * @author ajaco17
 *
 */
@XmlType(name = "facetValue", propOrder = {
		"value",
		"count"
}, namespace="http://services.hspartcatalog.searshc.com/domain/")
@XmlAccessorType(XmlAccessType.FIELD)
public class FacetValue {
	
	
	@XmlElement
	String count;

	@XmlElement
	String value;

	public String getCount() {
		return count;
	}

	public String getValue() {
		return value;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("value", value);
		builder.append("count", count);
		return builder.toString();
	}

}
