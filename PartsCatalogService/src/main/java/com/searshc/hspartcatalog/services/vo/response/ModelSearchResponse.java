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
import com.searshc.hspartcatalog.services.domain.Model;
import com.searshc.hspartcatalog.services.domain.ModelFacetCounts;
import com.searshc.hspartcatalog.services.vo.base.BaseResponse;

/**
* Title			:   ModelSearchResponse
* Description	:	this class is pojo for ModelSearchResponse 
* @author		:	Abhishek Jain
*/
@XmlRootElement(name="modelSearchResponse" , namespace="http://vo.services.hspartcatalog.searshc.com/response/")
@XmlType(propOrder = {
		"numFound",
		"models",
		"facetCounts"})
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ModelSearchResponse extends BaseResponse {
	
	private String numFound;
	
	private List<Model> models;
	
	private ModelFacetCounts facetCounts;
	
	@XmlElement
	public ModelFacetCounts getFacetCounts() {
		return facetCounts;
	}
	public void setFacetCounts(ModelFacetCounts facetCounts) {
		this.facetCounts = facetCounts;
	}
	
	@XmlElement(nillable=false, required=true)
	public String getNumFound() {
		return numFound;
	}
	public void setNumFound(String numFound) {
		this.numFound = numFound;
	}
	
	@XmlElementWrapper(name="models")
	@XmlElement(name="model")
	public List<Model> getModels() {
		if(models!=null && !models.isEmpty())
			return models;
		return null;
	}
	public void setModels(List<Model> model) {
		this.models = model;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("numFound", numFound);
		builder.append("models cnt", (models == null ? 0 : models.size()));
		
		return builder.toString();
	}
	
	public int getModelCnt() {
		if(numFound == null)
			return 0;
		
		return Integer.parseInt(numFound);
	}

}
