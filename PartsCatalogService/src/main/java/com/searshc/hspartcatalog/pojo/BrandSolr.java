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
public class BrandSolr {

	@JsonProperty("matches")	
	private String matches;
	
	@JsonProperty("groups")
	private List<Groups> groups;

	public String getMatches() {
		return matches;
	}

	public void setMatches(String matches) {
		this.matches = matches;
	}

	public List<Groups> getGroups() {
		return groups;
	}

	public void setGroups(List<Groups> groups) {
		this.groups = groups;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("matches", matches);
		builder.append("groups", groups);
		return builder.toString();
	}

	
}
