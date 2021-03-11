package com.searshc.hspartcatalog.services.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.searshc.hs.psc.logging.CustomToStringStyle;

@XmlType(name = "modelFacetCounts", propOrder = {
		"brand",
		"brandId",
		"productTypeId"
}, namespace="http://services.hspartcatalog.searshc.com/domain/")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ModelFacetCounts {
	
    private List<FacetValue> brand;

	private List<FacetValue> brandId;

	private List<FacetValue> productTypeId;

	@XmlElementWrapper(name="brand")
	@XmlElement(name="facetValue")
	public List<FacetValue> getBrand() {
		if(brand!=null && !brand.isEmpty())
			return brand;
		return null;
	}
    
	@XmlElementWrapper(name="brandId")
	@XmlElement(name="facetValue")
	public List<FacetValue> getBrandId() {
		if(brandId!=null && !brandId.isEmpty())
			return brandId;
		return null;
	}


	@XmlElementWrapper(name="productTypeId")
	@XmlElement(name="facetValue")
	public List<FacetValue> getProductTypeId() {
		if(productTypeId!=null && !productTypeId.isEmpty())
			return productTypeId;
		return null;
	}

	public void setBrand(List<FacetValue> brand) {
		this.brand = brand;
	}
	
    public void setBrandId(List<FacetValue> brandId) {
		this.brandId = brandId;
	}
	
    public void setProductTypeId(List<FacetValue> productTypeId) {
		this.productTypeId = productTypeId;
	}
    
    
    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("brand", brand);
		builder.append("brandId", (brandId == null ? "" : brandId));
		builder.append("productTypeId", (productTypeId == null ? "" : productTypeId));
		
		return builder.toString();
	}
    
	

}
