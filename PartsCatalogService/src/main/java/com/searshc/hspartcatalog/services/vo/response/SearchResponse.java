/**
 * 
 */
package com.searshc.hspartcatalog.services.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;
import com.searshc.hspartcatalog.services.domain.AValue;
import com.searshc.hspartcatalog.services.vo.base.BaseResponse;

@XmlRootElement(name="searchResponse" , namespace="http://vo.services.hspartcatalog.searshc.com/response/")
@XmlType(propOrder = {
		"numFound",
		"values"})

@XmlAccessorType(XmlAccessType.PROPERTY)
public class SearchResponse extends BaseResponse {
	
	private String numFound;
	
	private List<AValue> values;
	
	@XmlElement(nillable=false, required=true)
	public String getNumFound() {
		return numFound;
	}
	public void setNumFound(String numFound) {
		this.numFound = numFound;
	}
	
	@XmlElementWrapper(name="values")
	@XmlElement(name="value")
	public List<AValue> getValues() {
		if(values!=null && !values.isEmpty())
			return values;
		return null;
	}
	public void setValues(List<AValue> values) {
		this.values = values;
	}
	
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("numFound", numFound);
		if(values != null) {
			builder.append(" ").append(values.toString());
		}	
		
		return builder.toString();
	}
	
	public int getValueCnt() {
		if(numFound == null)
			return 0;
		
		return Integer.parseInt(numFound);
	}

}
