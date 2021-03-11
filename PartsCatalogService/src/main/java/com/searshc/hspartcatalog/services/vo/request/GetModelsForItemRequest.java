/**
 * 
 */
package com.searshc.hspartcatalog.services.vo.request;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;
import com.searshc.hspartcatalog.services.vo.base.BaseRequest;

/**
* Title			:   GetModelsForItemRequest
* Description	:	this class is pojo for GetModelsForItemRequest 
* @author		:	Abhishek Jain
*/

public class GetModelsForItemRequest  extends BaseRequest{

	/**
	 * Searches for exact matches for an itemId.
	 */
	private String itemId;
	/**
	 * Searches exact matches for a partNo
	 */
	private String responseFormat;

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("itemId", itemId);
		builder.append("responseFormat", responseFormat);
		return builder.toString();
	}

}
