package com.searshc.hspartcatalog.services.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;

@XmlType(name = "partDetail", propOrder = {"divNo", "plsNo", "partNo"}, namespace = "http://services.hspartcatalog.searshc.com/domain/")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartDetail {

	@XmlElement
	private String divNo;

	@XmlElement
	private String plsNo;

	@XmlElement
	private String partNo;

	public String getDivNo() {
		return divNo;
	}

	public void setDivNo(String divNo) {
		this.divNo = divNo;
	}

	public String getPlsNo() {
		return plsNo;
	}

	public void setPlsNo(String plsNo) {
		this.plsNo = plsNo;
	}

	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this,
				CustomToStringStyle.custom);
		builder.append("divNo", divNo);
		builder.append("plsNo", plsNo);
		builder.append("partNo", partNo);
		return builder.toString();
	}
}
