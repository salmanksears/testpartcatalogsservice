/**
 * 
 */
package com.searshc.hspartcatalog.pojo;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.searshc.hs.psc.logging.CustomToStringStyle;

/**
 * @author ajain0
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocList {

	@JsonProperty("numFound")	
	private String modelCount;
	
	@JsonProperty("docs")	
	private List<Doc> docs;

	public String getModelCount() {
		return modelCount;
	}

	public void setModelCount(String modelCount) {
		this.modelCount = modelCount;
	}

	public List<Doc> getDocs() {
		return docs;
	}

	public void setDocs(List<Doc> docs) {
		this.docs = docs;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("modelCount", modelCount);
		builder.append("docs", docs);
		return builder.toString();
	}

}
