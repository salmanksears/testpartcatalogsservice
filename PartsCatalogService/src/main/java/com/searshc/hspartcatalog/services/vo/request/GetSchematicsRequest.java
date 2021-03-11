/**
 * 
 */
package com.searshc.hspartcatalog.services.vo.request;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;
import com.searshc.hspartcatalog.services.vo.base.BaseRequest;

/**
* Title			:   GetSchematicsRequest
* Description	:	this class is pojo for GetSchematicsRequest 
* @author		:	Abhishek Jain
*/
public class GetSchematicsRequest extends BaseRequest {

	/**
	 * Retrieves diagrams for a particular modelNo
	 */
	private String modelNo;
	
	/**
	 * Retrieves diagrams for a particular modelId
	 */
	private String modelId;
	
	/**
	 * By default, the response will be in XML
	 */
	private String responseFormat;

	/**
	 * By default the sort order (i.e. orderBy) 
	 * will be by partNo asc (in ascending order). 
	 * This can be over-ridden by 
	 * passing in the field and order
	 * you want the results produced.
	 */
	private String sortBy;
	
	/**
	 * @return the modelNo
	 */
	public String getModelNo() {
		return modelNo;
	}

	/**
	 * @param modelNo the modelNo to set
	 */
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

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
		builder.append("modelNo", modelNo);
		builder.append("modelId", modelId);
		builder.append("responseFormat", responseFormat);
		builder.append("sortBy", sortBy);
		return builder.toString();
	}

}
