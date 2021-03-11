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
import com.searshc.hspartcatalog.services.domain.Schematic;
import com.searshc.hspartcatalog.services.vo.base.BaseResponse;

/**
* Title			:   GetSchematicsResponse
* Description	:	this class is pojo for GetSchematicsResponse 
* @author		:	Abhishek Jain
*/
@XmlRootElement(name="getSchematicsResponse", namespace="http://vo.services.hspartcatalog.searshc.com/response/")
@XmlType(propOrder = {
		"numFound",
		"schematics"})
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GetSchematicsResponse extends BaseResponse {
	
	private String numFound;
	
	private List<Schematic> schematics;
	
	@XmlElement(nillable=false, required=true)
	public String getNumFound() {
		return numFound;
	}
	public void setNumFound(String numFound) {
		this.numFound = numFound;
	}
	
	@XmlElementWrapper(name="schematics")
	@XmlElement(name="schematic")
	public List<Schematic> getSchematics() {
		if(schematics!=null && !schematics.isEmpty())
			return schematics;
		return null;
	}
	public void setSchematics(List<Schematic> schematic) {
		this.schematics = schematic;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("numFound", numFound);
		builder.append("schematics cnt", (schematics == null ? 0 : schematics.size()));
	
		return builder.toString();
	}
	
	
}
