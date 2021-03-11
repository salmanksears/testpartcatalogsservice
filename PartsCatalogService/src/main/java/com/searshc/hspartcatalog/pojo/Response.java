/**
 * 
 */
package com.searshc.hspartcatalog.pojo;

import java.util.Arrays;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.searshc.hs.psc.logging.CustomToStringStyle;

/**
 * @author ajain0
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
	
	@JsonProperty("numFound")	
	private String numFound;
	
	@JsonProperty("docs")	
	private Doc[] docs;
		
	public void setNumFound(String numFound) {
		this.numFound = numFound;
	}
	public String getNumFound() {
		return numFound;
	}
	public void setDocs(Doc[] docs) {
		this.docs = docs;
	}
	public Doc[] getDocs() {
		return docs;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("numFound", numFound);
		builder.append("docs", Arrays.toString(docs));
		return builder.toString();
	}

}
