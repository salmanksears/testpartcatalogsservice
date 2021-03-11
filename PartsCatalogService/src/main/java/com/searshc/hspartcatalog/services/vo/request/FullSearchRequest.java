/**
 * 
 */
package com.searshc.hspartcatalog.services.vo.request;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;
import com.searshc.hspartcatalog.services.vo.base.BaseRequest;

/**
* Title			:   FullSearchRequest
* Description	:	this class is pojo for FullSearchRequest 
* @author		:	Abhishek Jain
*/
public class FullSearchRequest extends BaseRequest {
	
	/**
	 * Searches exact matches from a 
	 * modelNo or partNo.
	 */
	private String key;
	/**
	 * By default, the response will be in XML
	 */
	private String responseFormat;
	/**
	 * By default value is 'N'. 
	 * If set to 'Y' this search assumes special 
	 * characters have been removed from the 
	 * partNo or itemId and the search should be
	 * against a formatted partNo or itemId value. 
	 */
	private String formatted;
	/**
	 * By default the sort order (i.e. orderBy) 
	 * will be by partNo asc (in ascending order). 
	 * This can be over-ridden by 
	 * passing in the field and order
	 * you want the results produced.
	 */
	private String sortBy;
	
	/**
	 * By default value is 'N' and
	 * is set to return the the
	 * results starting at the firstrow.
	 * If pagination is needed the starting row
	 * can be offset to return the results
	 * at a particular rownumber
	 * 	 */
	private String startingRow;
	
	/**
	 * By default value is 'N'
	 * and the max rows per result 
	 * set is set to return the first 100 records.
	 * This number can be overridden by applying
	 * a different value.
	 * */
	private String maxRows;

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
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
	 * @return the formatted
	 */
	public String getFormatted() {
		return formatted;
	}

	/**
	 * @param formatted the formatted to set
	 */
	public void setFormatted(String formatted) {
		this.formatted = formatted;
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

	/**
	 * @return the startingRow
	 */
	public String getStartingRow() {
		return startingRow;
	}

	/**
	 * @param startingRow the startingRow to set
	 */
	public void setStartingRow(String startingRow) {
		this.startingRow = startingRow;
	}

	/**
	 * @return the maxRows
	 */
	public String getMaxRows() {
		return maxRows;
	}

	/**
	 * @param maxRows the maxRows to set
	 */
	public void setMaxRows(String maxRows) {
		this.maxRows = maxRows;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("key", key);
		builder.append("responseFormat", responseFormat);
		builder.append("formatted", formatted);
		builder.append("sortBy", sortBy);
		builder.append("startingRow", startingRow);
		builder.append("maxRows", maxRows);
		return builder.toString();
	}

	
}
