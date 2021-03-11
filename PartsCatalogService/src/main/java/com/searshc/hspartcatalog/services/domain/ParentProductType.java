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

/**
* Title			:   ParentProductType
* Description	:	domain object for ParentProductType 
* @author		:	Abhishek Jain
*/
@XmlType(name = "parentProductType", propOrder = {
		"parentProductTypeId",
		"parentProductTypeName",
		"modelCount"
}, namespace="http://services.hspartcatalog.searshc.com/domain/")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParentProductType {
	
	@XmlElement
	public String parentProductTypeId;
	
	@XmlElement
	public String parentProductTypeName;
	
	@XmlElement
	public String modelCount;

	public String getParentProductTypeId() {
		return parentProductTypeId;
	}

	public void setParentProductTypeId(String parentProductTypeId) {
		this.parentProductTypeId = parentProductTypeId;
	}

	public String getParentProductTypeName() {
		return parentProductTypeName;
	}

	public void setParentProductTypeName(String parentProductTypeName) {
		this.parentProductTypeName = parentProductTypeName;
	}

	public String getModelCount() {
		return modelCount;
	}

	public void setModelCount(String count) {
		this.modelCount = count;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("parentProductTypeId", parentProductTypeId);
		builder.append("parentProductTypeName", parentProductTypeName);
		builder.append("modelCount", modelCount);
		return builder.toString();
	}
	
}
