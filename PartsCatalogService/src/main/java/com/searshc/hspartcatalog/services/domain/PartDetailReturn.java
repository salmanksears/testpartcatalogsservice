package com.searshc.hspartcatalog.services.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;

@XmlType(name = "partDetailReturn", propOrder = {"divNo", "plsNo", "partNo", "stateCode" , "coreChargeAmt", "environmentChargeAmt"}, 
namespace = "http://services.hspartcatalog.searshc.com/domain/")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartDetailReturn {
	
	@XmlElement
	private String  divNo;
	@XmlElement
	private String	plsNo;
	@XmlElement
	private String	partNo;
	@XmlElement
	private String  stateCode;
	@XmlElement
	private Double coreChargeAmt;
	@XmlElement
	private Double environmentChargeAmt;
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
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public Double getCoreChargeAmt() {
		return coreChargeAmt;
	}
	public void setCoreChargeAmt(Double coreChargeAmt) {
		this.coreChargeAmt = coreChargeAmt;
	}
	public Double getEnvironmentChargeAmt() {
		return environmentChargeAmt;
	}
	public void setEnvironmentChargeAmt(Double environmentChargeAmt) {
		this.environmentChargeAmt = environmentChargeAmt;
	}
	
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this,
				CustomToStringStyle.custom);
		builder.append("divNo", divNo);
		builder.append("plsNo", plsNo);
		builder.append("partNo", partNo);
		builder.append("stateCode", stateCode);
		builder.append("coreChargeAmt", coreChargeAmt);
		builder.append("environmentChargeAmt", environmentChargeAmt);
		return builder.toString();
	}

}
