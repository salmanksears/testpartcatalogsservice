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
import com.searshc.hspartcatalog.services.domain.Brand;
import com.searshc.hspartcatalog.services.vo.base.BaseResponse;

/**
* Title			:   GetBrandsResponse
* Description	:	this class is pojo for GetBrandsResponse 
* @author		:	Abhishek Jain
*/
@XmlRootElement(name="getBrandsResponse" , namespace="http://vo.services.hspartcatalog.searshc.com/response/")
@XmlType(propOrder = {
		"numFound",
		"brands"})
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GetBrandsResponse extends BaseResponse {
	
	private String numFound;
	
	private List<Brand> brands;

	@XmlElement(nillable=false, required=true)
	public String getNumFound() {
		return numFound;
	}

	public void setNumFound(String numFound) {
		this.numFound = numFound;
	}

	@XmlElementWrapper(name="brands")
	@XmlElement(name="brand")
	public List<Brand> getBrands() {
		if(brands!=null && !brands.isEmpty())
			return brands;
		else 
			return null;
	}

	public void setBrands(List<Brand> brand) {
		this.brands = brand;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("numFound", numFound);
		builder.append("brands cnt", (brands == null ? 0 : brands.size()));
		return builder.toString();
	}
	
}


