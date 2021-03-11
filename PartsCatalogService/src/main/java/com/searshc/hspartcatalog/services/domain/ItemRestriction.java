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
* Title			:   itemRestriction
* Description	:	domain object for itemRestriction 
* @author		:	Abhishek Jain
*/

@XmlType(name = "restriction", propOrder = {
		"restrictionId",
		"restrictionTypeCd", 
		"restrictionDescription"
}, namespace="http://services.hspartcatalog.searshc.com/domain/")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemRestriction {
	
	@XmlElement
	private String restrictionId;
	
	@XmlElement
	private String restrictionTypeCd;
	
	@XmlElement
	private String restrictionDescription;
	
	public String getRestrictionId() {
		return restrictionId;
	}

	public void setRestrictionId(String restrictionId) {
		this.restrictionId = restrictionId;
	}

	public String getRestrictionTypeCd() {
		return restrictionTypeCd;
	}

	public void setRestrictionTypeCd(String restrictionTypeCd) {
		this.restrictionTypeCd = restrictionTypeCd;
	}

	public String getRestrictionDescription() {
		return restrictionDescription;
	}

	public void setRestrictionDescription(String restrictionDescription) {
		this.restrictionDescription = restrictionDescription;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("restrictionId", restrictionId);
		builder.append("restrictionTypeCd", restrictionTypeCd);
		builder.append("restrictionDescription", restrictionDescription);

		return builder.toString();
	}
}
