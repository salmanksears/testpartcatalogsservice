/**
 * 
 */
package com.searshc.hspartcatalog.services.vo.base;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;

/**
* Title			:   BaseRequest
* Description	:	vo object for BaseRequest 
* @author		:	Abhishek Jain
*/

public class BaseRequest {

	/**
	 * clientKey : password of a particular client
	 */
	private String clientKey;

	/**
	 * @return the clientKey
	 */
	public String getClientKey() {
		return clientKey;
	}
	/**
	 * @param clientKey the clientKey to set
	 */
	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("clientKey", clientKey);
		return builder.toString();
	}
	
	
}
