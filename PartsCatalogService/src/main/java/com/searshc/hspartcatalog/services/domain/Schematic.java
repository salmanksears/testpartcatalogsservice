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
* Title			:   Schematic
* Description	:	domain object for schematic 
* @author		:	Abhishek Jain
*/

@XmlType(name = "schematic", propOrder = {
		"schematicId",
		"schematicName",
		"schematicURL"
}, namespace="http://services.hspartcatalog.searshc.com/domain/")
@XmlAccessorType(XmlAccessType.FIELD)
public class Schematic {
		
	/**
	 * The Manufacturer or Brand name
	 * of a product (e.g., Whirlpool, Craftsman, Kenmore, etc.).
	 */
	@XmlElement
	private String schematicId;
	
	/**
	 * The name of the diagram component being returned
	 */
	@XmlElement
	private String schematicName;
	
	/**
	 * The installation manual URL.
	 * The URL will be available if 
	 * the models that have an associated installation manual
	 */
	@XmlElement
	private String schematicURL;

	public String getSchematicId() {
		return schematicId;
	}

	public void setSchematicId(String subComponentId) {
		this.schematicId = subComponentId;
	}

	public String getSchematicName() {
		return schematicName;
	}

	public void setSchematicName(String subComponentName) {
		this.schematicName = subComponentName;
	}

	public String getSchematicURL() {
		return schematicURL;
	}

	public void setSchematicURL(String subComponentURL) {
		this.schematicURL = subComponentURL;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("schematicId", schematicId);
		builder.append("schematicName", schematicName);
		builder.append("schematicURL", schematicURL);
		return builder.toString();
	}
	
	
}
