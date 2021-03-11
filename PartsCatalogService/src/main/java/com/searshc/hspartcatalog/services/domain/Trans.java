package com.searshc.hspartcatalog.services.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;

@XmlType(name = "trans", propOrder = {"transCode", "stateCode"}, namespace = "http://services.hspartcatalog.searshc.com/domain/")
@XmlAccessorType(XmlAccessType.FIELD)
public class Trans {

	@XmlElement
	private String transCode;
	@XmlElement
	private String stateCode;
	
	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this,
				CustomToStringStyle.custom);
		builder.append("transCode", transCode);
		builder.append("stateCode", stateCode);
		return builder.toString();
	}
}
