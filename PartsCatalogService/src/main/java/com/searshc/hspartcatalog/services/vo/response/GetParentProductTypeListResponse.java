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
import com.searshc.hspartcatalog.services.domain.ParentProductType;
import com.searshc.hspartcatalog.services.vo.base.BaseResponse;

/**
* Title			:   GetParentProductTypeListResponse
* Description	:	this class is pojo for GetParentProductTypeListResponse 
* @author		:	Abhishek Jain
*/
@XmlRootElement(name="getParentProductTypeListResponse", namespace="http://vo.services.hspartcatalog.searshc.com/response/")
@XmlType(propOrder = {
		"numFound",
		"parentProductTypes"})
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GetParentProductTypeListResponse extends BaseResponse {
	
	private String numFound;
	
	private List<ParentProductType> parentProductTypes;
	
	@XmlElement(nillable=false, required=true)
	public String getNumFound() {
		return numFound;
	}

	public void setNumFound(String numFound) {
		this.numFound = numFound;
	}

	@XmlElementWrapper(name="parentProductTypes")
	@XmlElement(name="parentProductType")
	public List<ParentProductType> getParentProductTypes() {
		if(parentProductTypes!=null && !parentProductTypes.isEmpty())
			return parentProductTypes;
		return null;
	}

	public void setParentProductTypes(List<ParentProductType> parentProductType) {
		this.parentProductTypes = parentProductType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("numFound", numFound);
		builder.append("parentProductTypes cnt", (parentProductTypes == null ? 0 : parentProductTypes.size()));
	
		return builder.toString();
	}


	
}
