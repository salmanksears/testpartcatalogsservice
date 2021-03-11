/**
 * 
 */
package com.searshc.hspartcatalog.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.searshc.hs.psc.logging.CustomToStringStyle;

/**
 * @author ajain0
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Groups {

	@JsonProperty("doclist")
	private DocList docList;
	
	@JsonProperty("groupValue")
	private String brandName; 

	public DocList getDocList() {
		return docList;
	}

	public void setDocList(DocList docList) {
		this.docList = docList;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("docList", docList);
		builder.append("brandName", brandName);
		return builder.toString();
	}
	
}

