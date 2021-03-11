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
public class FacetCounts {
	
	@JsonProperty("facet_fields")	
	private FacetFields facetFields;

	public FacetFields getFacetFields() {
		return facetFields;
	}
	public void setFacetFields(FacetFields facetFields) {
		this.facetFields = facetFields;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("facetFields", facetFields);
		return builder.toString();
	}

}
