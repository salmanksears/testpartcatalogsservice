/**
 * 
 */
package com.searshc.hspartcatalog.services.vo.request;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;
import com.searshc.hspartcatalog.services.vo.base.BaseRequest;

/**
* Title			:   GetModelDetailsRequest
* Description	:	this class is pojo for GetModelDetailsRequest 
* @author		:	Abhishek Jain
*/
public class GetModelDetailsRequest extends BaseRequest {

	/**
	 * Retrieves diagrams for a particular modelId
	 */
	private String modelId;

	/**
	 * By default, the response will be in XML
	 */
	private String responseFormat;

	/**
	 * @return the modelId
	 */
	public String getModelId() {
		return modelId;
	}

	/**
	 * @param modelId the modelId to set
	 */
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	

	public String getResponseFormat() {
		return responseFormat;
	}

	public void setResponseFormat(String responseFormat) {
		this.responseFormat = responseFormat;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("modelId", modelId);
		builder.append("responseFormat", responseFormat);
		return builder.toString();
	}

}
