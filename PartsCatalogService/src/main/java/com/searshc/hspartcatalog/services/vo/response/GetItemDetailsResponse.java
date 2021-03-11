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
import com.searshc.hspartcatalog.services.domain.ItemAttribute;
import com.searshc.hspartcatalog.services.domain.ItemRestriction;
import com.searshc.hspartcatalog.services.domain.Schematic;
import com.searshc.hspartcatalog.services.vo.base.BaseResponse;

/**
* Title			:   GetItemDetailsResponse
* Description	:	this class is pojo for GetItemDetailsResponse 
* @author		:	Abhishek Jain
*/
@XmlRootElement(name="getItemDetailsResponse" , namespace="http://vo.services.hspartcatalog.searshc.com/response/")
@XmlType(propOrder = {
		"numFound",
		"item"})
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GetItemDetailsResponse extends BaseResponse {
	
	private String numFound;
	
	private Item item;
	
	@XmlElement(nillable=false, required=true)
	public String getNumFound() {
		return numFound;
	}

	public void setNumFound(String numFound) {
		this.numFound = numFound;
	}

		
	@XmlElement
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("numFound", numFound);
		builder.append("item", item);
		
		return builder.toString();
	}
}
