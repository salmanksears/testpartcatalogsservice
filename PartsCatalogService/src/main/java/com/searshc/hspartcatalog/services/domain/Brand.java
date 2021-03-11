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
* Title			:   Brand
* Description	:	domain object for brand 
* @author		:	Abhishek Jain
*/
@XmlType(name = "brand", propOrder = {
		"brandId",
		"brandName",
		"modelCount"
}, namespace="http://services.hspartcatalog.searshc.com/domain/")
@XmlAccessorType(XmlAccessType.FIELD)
public class Brand {
	
	@XmlElement
	public String brandId;
	
	@XmlElement
	public String brandName;
	
	@XmlElement
	public String modelCount;

	
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getModelCount() {
		return modelCount;
	}

	public void setModelCount(String modelCount) {
		this.modelCount = modelCount;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("brandId", brandId);
		builder.append("brandName", brandName);
		builder.append("modelCount", modelCount);
		return builder.toString();
	}
	
	
}
