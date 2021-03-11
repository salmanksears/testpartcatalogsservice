/**
 * 
 */
package com.searshc.hspartcatalog.services.vo.request;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;
import com.searshc.hspartcatalog.services.vo.base.BaseRequest;

/**
* Title			:   ItemSearchRequest
* Description	:	this class is pojo for ItemSearchRequest 
* @author		:	Abhishek Jain
*/

public class ItemSearchRequest  extends BaseRequest{

	/**
	 * Searches for exact matches for an itemId.
	 */
	private String itemId;
	/**
	 * Searches exact matches for a partNo
	 */
	private String partNo;
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
	 * By default, all product groups 
	 * will be returned related to the item/part searched, 
	 * however you can specify which productGroupId 
	 * you want to limit the results to.
	 */
	private String productGroupId;
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
	 * @return the partNo
	 */
	public String getPartNo() {
		return partNo;
	}
	/**
	 * @param partNo the partNo to set
	 */
	public void setPartNo(String partNo) {
		this.partNo = partNo;
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
	/**
	 * @return the productGroupId
	 */
	public String getProductGroupId() {
		return productGroupId;
	}
	/**
	 * @param productGroupId the productGroupId to set
	 */
	public void setProductGroupId(String productGroupId) {
		this.productGroupId = productGroupId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("itemId", itemId);
		builder.append("partNo", partNo);
		builder.append("responseFormat", responseFormat);
		builder.append("formatted", formatted);
		builder.append("sortBy", sortBy);
		builder.append("startingRow", startingRow);
		builder.append("maxRows", maxRows);
		builder.append("productGroupId", productGroupId);
		return builder.toString();
	}
	
}
