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
public class SolrResponse {
	
	@JsonProperty("response")	
	private Response response;
	
	@JsonProperty("facet_counts")	
	private FacetCounts facetCounts;
	
	@JsonProperty("grouped")	
	private Grouped grouped;
	
	public void setResponse(Response response) {
		this.response = response;
	}
	public Response getResponse() {
		return response;
	}
	public FacetCounts getFacetCounts() {
		return facetCounts;
	}
	public void setFacetCounts(FacetCounts facetCounts) {
		this.facetCounts = facetCounts;
	}
	public Grouped getGrouped() {
		return grouped;
	}
	public void setGrouped(Grouped grouped) {
		this.grouped = grouped;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("response", response);
		builder.append("facetCounts", facetCounts);
		builder.append("grouped", grouped);
		return builder.toString();
	}
}
