/**
 * 
 */
package com.searshc.hspartcatalog.services.vo.request;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;
import com.searshc.hspartcatalog.services.vo.base.BaseRequest;

/**
* Title			:   GetBrandsRequest
* Description	:	this class is pojo for GetBrandsRequest 
* @author		:	Abhishek Jain
*/
public class GetBrandsRequest extends BaseRequest{

	/**
	 * Searches exact matches for a brandName.
	 * Default value is ALL
	 */
	private String brand;
	
	/**
	 * By default, the response will be in XML,
	 * but the response could be returned in JSON or XML.
	 */
	private String responseFormat;
	
	/**
	 * By default sort order (i.e. sortby) 
	 * will be by brandName (in ascending order)
	 */
	private String sortBy;

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the responseFormat
	 */
	public String getResponseFormat() {
		return responseFormat;
	}

	/**
	 * @param responseFormat the responseFormat to set
	 */
	public void setResponseFormat(String responseFormat) {
		this.responseFormat = responseFormat;
	}

	/**
	 * @return the sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}

	/**
	 * @param sortBy the sortBy to set
	 */
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("brand", brand);
		builder.append("responseFormat", responseFormat);
		builder.append("sortBy", sortBy);
		return builder.toString();
	}

	
}
