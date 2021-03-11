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
import com.searshc.hspartcatalog.services.domain.ProductGroup;
import com.searshc.hspartcatalog.services.vo.base.BaseResponse;

/**
* Title			:   GetProductGroupListResponse
* Description	:	this class is pojo for GetProductGroupListResponse 
* @author		:	Abhishek Jain
*/
@XmlRootElement(name="getProductGroupListResponse", namespace="http://vo.services.hspartcatalog.searshc.com/response/")
@XmlType(propOrder = {
		"numFound",
		"productGroups"})
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GetProductGroupListResponse extends BaseResponse {
	
	private String numFound;
	
	private List<ProductGroup> productGroups;
	
	@XmlElement(nillable=false, required=true)
	public String getNumFound() {
		return numFound;
	}

	public void setNumFound(String numFound) {
		this.numFound = numFound;
	}

	@XmlElementWrapper(name="productGroups")
	@XmlElement(name="productGroup")
	public List<ProductGroup> getProductGroups() {
		if(productGroups!=null && !productGroups.isEmpty())
			return productGroups;
		return null;
	}

	public void setProductGroups(List<ProductGroup> productGroup) {
		this.productGroups = productGroup;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("numFound", numFound);
		builder.append("productGroups cnt", (productGroups == null ? 0 : productGroups.size()));
		
		return builder.toString();
	}

}
