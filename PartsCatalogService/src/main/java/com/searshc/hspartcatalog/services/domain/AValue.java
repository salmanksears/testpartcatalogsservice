/**
 * 
 */
package com.searshc.hspartcatalog.services.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "avalue", propOrder = {
		"id",
		"number",
		"description",
		"type"
}, namespace="http://services.hspartcatalog.searshc.com/domain/")
@XmlAccessorType(XmlAccessType.FIELD)
public class AValue implements Comparable<AValue>{
		
	@XmlElement
	private String id;
	
	@XmlElement
	private String number;
	
	@XmlElement
	private String description;
	
	@XmlElement
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AValue [id=");
		builder.append(id);
		builder.append(", number=");
		builder.append(number);
		builder.append(", description=");
		builder.append(description);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		
		return builder.toString();
	}

	@Override
	public int compareTo(AValue avalue) {
		String arg0 = this.getType() + this.getNumber();
		String arg1 = avalue.getType() + avalue.getNumber();
		
		return arg0.compareTo(arg1);
	}
}
