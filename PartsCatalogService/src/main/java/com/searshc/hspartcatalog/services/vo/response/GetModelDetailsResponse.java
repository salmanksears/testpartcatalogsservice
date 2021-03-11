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
import com.searshc.hspartcatalog.services.domain.Schematic;
import com.searshc.hspartcatalog.services.vo.base.BaseResponse;

/**
* Title			:   GetModelDetailsResponse
* Description	:	this class is pojo for GetModelDetailsResponse 
* @author		:	Abhishek Jain
*/
@XmlRootElement(name="getModelDetailsResponse" , namespace="http://vo.services.hspartcatalog.searshc.com/response/")
@XmlType(propOrder = {
		"numFound",
		"model",
		"items",
		"modelSchematics"
		})
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GetModelDetailsResponse extends BaseResponse {
	
	private String numFound;
	
	private Model model;
	
	private List<Schematic> modelSchematics;

	private List<Item> items;
	
	@XmlElement(nillable=false, required=true)
	public String getNumFound() {
		return numFound;
	}

	public void setNumFound(String numFound) {
		this.numFound = numFound;
	}

	@XmlElement
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	@XmlElementWrapper(name="modelSchematics")
	@XmlElement(name="modelSchematic")
	public List<Schematic> getModelSchematics() {
		if(modelSchematics!= null && !modelSchematics.isEmpty())
			return modelSchematics;
		else
			return null;
	}

	public void setModelSchematics(List<Schematic> modelSchematic) {
		this.modelSchematics = modelSchematic;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("numFound", numFound);
		builder.append("model", model);
		builder.append("modelSchematics cnt", (modelSchematics == null ? 0 : modelSchematics.size()));
		builder.append("items cnt", (items == null ? 0 : items.size()));
		return builder.toString();
	}

}
