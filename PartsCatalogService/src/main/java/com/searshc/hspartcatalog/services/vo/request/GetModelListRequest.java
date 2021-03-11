/**
 * 
 */
package com.searshc.hspartcatalog.services.vo.request;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;
import com.searshc.hspartcatalog.services.vo.base.BaseRequest;

/**
* Title			:   GetModelListRequest
* Description	:	this class is pojo for GetModelListRequest 
* @author		:	Abhishek Jain
*/
public class GetModelListRequest extends BaseRequest{

	/**
	 * This is a required field that needs to be passed in.
	 * The data will be segmented by parentProductType. For a listing of the parentProductTypes, 
	 * request getParentProductTypes service method.
	 */
	private String parentProductTypeId;
	
	/**
	 * By default, all brands will be returned, however you could specify a particular brand you want to limit the results. 
	 * For a listing of all the brands, request getBrandList service method
	 */
	private String brandId;
	
	/**
	 * This field when left out a full list of data will be returned. 
	 * When passed in, only data updates on or after the version date will be returned.
	 */
	private String versionDate;
	
	/**
	 * By default, the response will be in XML,
	 * but the response could be returned in JSON or XML.
	 */
	private String responseFormat;
	
	/**
	 * By default value is 'N' and is set to return the the results starting at the first row. 
	 * If pagination is needed the starting row can be offset to return the results at a particular row number.
	 */
	private String startingRow;
	
	/**
	 * By default value is 'N' and the max rows per result set is set to return the first 15000 records. 
	 * This number can be over-ridden by applying a different value.
	 */
	private String maxRows;
	
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
	
	public String getVersionDate() {
		return versionDate;
	}

	public void setVersionDate(String versionDate) {
		this.versionDate = versionDate;
	}

	public String getStartingRow() {
		return startingRow;
	}

	public void setStartingRow(String startingRow) {
		this.startingRow = startingRow;
	}

	public String getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(String maxRows) {
		this.maxRows = maxRows;
	}

	
	public String getParentProductTypeId() {
		return parentProductTypeId;
	}

	public void setParentProductTypeId(String parentProductTypeId) {
		this.parentProductTypeId = parentProductTypeId;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("parentProductTypeId", parentProductTypeId);
		builder.append("brandId", brandId);
		builder.append("versionDate", versionDate);
		builder.append("responseFormat", responseFormat);
		builder.append("startingRow", startingRow);
		builder.append("maxRows", maxRows);
		return builder.toString();
	}

	
}
