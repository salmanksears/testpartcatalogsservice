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
* Title			:   productGroup
* Description	:	domain object for productGroup 
* @author		:	Abhishek Jain
*/
@XmlType(name = "productGroup", propOrder = {
		"productGroupId",
		"productGroupName",
		"itemCount"
}, namespace="http://services.hspartcatalog.searshc.com/domain/")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductGroup {
	
	@XmlElement
	public String productGroupId;
	
	@XmlElement
	public String productGroupName;
	
	@XmlElement
	public String itemCount;

	public String getProductGroupName() {
		return productGroupName;
	}

	public void setProductGroupName(String productGroupName) {
		this.productGroupName = productGroupName;
	}

	public String getItemCount() {
		return itemCount;
	}

	public void setItemCount(String count) {
		this.itemCount = count;
	}

	public String getProductGroupId() {
		return productGroupId;
	}

	public void setProductGroupId(String productGroupId) {
		this.productGroupId = productGroupId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("productGroupId", productGroupId);
		builder.append("productGroupName", productGroupName);
		builder.append("itemCount", itemCount);
		return builder.toString();
	}
	
	
}
