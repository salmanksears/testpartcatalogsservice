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
import com.searshc.hspartcatalog.services.domain.Item;
import com.searshc.hspartcatalog.services.domain.Model;
import com.searshc.hspartcatalog.services.vo.base.BaseResponse;

/**
* Title			:   FullSearchResponse
* Description	:	this class is pojo for FullSearchResponse 
* @author		:	Abhishek Jain
*/

@XmlRootElement(name="fullSearchResponse", namespace="http://vo.services.hspartcatalog.searshc.com/response/")
@XmlType(propOrder = {
		"numFound",
		"models",
		"items"})
@XmlAccessorType(XmlAccessType.PROPERTY)
public class FullSearchResponse extends BaseResponse {
	
	private String numFound;
	
	private List<Item> items;
	
	private List<Model> models;

	@XmlElement(nillable=false, required=true)
	public String getNumFound() {
		return numFound;
	}
	public void setNumFound(String numFound) {
		this.numFound = numFound;
	}
	
	@XmlElementWrapper(name="items")
	@XmlElement(name="item")
	public List<Item> getItems() {
		if(items!=null && !items.isEmpty())
			return items;
		else 
			return null;
	}
	public void setItems(List<Item> item) {
		this.items = item;
	}
	
	@XmlElementWrapper(name="models")
	@XmlElement(name="model")
	public List<Model> getModels() {
		if(models!=null && !models.isEmpty())
			return models;
		else 
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
		builder.append("items cnt", (items == null ? 0 : items.size()));
		return builder.toString();
	}
	
}
