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

import com.searshc.hspartcatalog.services.domain.Accessory;
import com.searshc.hspartcatalog.services.domain.Maintenance;
import com.searshc.hspartcatalog.services.vo.base.BaseResponse;

@XmlRootElement(name="accessoryResponse" , namespace="http://vo.services.hspartcatalog.searshc.com/response/")
@XmlType(propOrder = {
		"numFound",
		"accessories","maintenances"
		})

@XmlAccessorType(XmlAccessType.PROPERTY)
public class AccessoryResponse extends BaseResponse {
	
	private String numFound;
	private List<Accessory> accessories;
	private List<Maintenance> maintenances;
	
	@XmlElement(nillable=false, required=true)
	public String getNumFound() {
		return numFound;
	}
	public void setNumFound(String numFound) {
		this.numFound = numFound;
	}
	
	@XmlElementWrapper(name="accessories")
	@XmlElement(name="accessory")
	public List<Accessory> getAccessories() {
		if(accessories!=null && !accessories.isEmpty())
			return accessories;
		return null;
	}
	
	@XmlElementWrapper(name="maintenances")
	@XmlElement(name="maintenance")
	public List<Maintenance> getMaintenances() {
		if(maintenances!=null && !maintenances.isEmpty())
			return maintenances;
		return null;
	}
	
	public void setMaintenances(List<Maintenance> maintenances) {
		this.maintenances = maintenances;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccessoryResponse [numFound=").append(numFound).append(", accessories=").append(accessories)
		.append(", maintenances=").append(maintenances)
		.append("]");
		return builder.toString();
	}
	public void setAccessories(List<Accessory> accessories) {
		this.accessories = accessories;
	}
}
