/**
 * 
 */
package com.searshc.hspartcatalog.services.vo.request;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;
import com.searshc.hspartcatalog.services.vo.base.BaseRequest;

/**
* Title			:   ModelSearchRequest
* Description	:	this class is pojo for ModelSearchRequest 
* @author		:	Abhishek Jain
*/
public class ModelSearchRequest extends BaseRequest {
	/**
	 * Searches exact matches from a modelNo.
	 */
	private String modelNo;
	
	/**
	 * Searches exact matches from a modelID.
	 */
	private String modelId;
	
	/**
	 * By default, the response will be in XML,
	 * but the response could be returned in JSON or XML.
	 */
	private String responseFormat;
	
	/**
	 * By default, all brands will be returned,
	 * however you could specify a particular brand 
	 * you want to limit the results to. (i.e. GE)
	 */
	private String brand;
	
	/**
	 * By default value is 'N'. 
	 * If set to 'Y' the modelSearch assumes 
	 * special characters have been removed from 
	 * the model number or modelId and the search
	 * should be against a formatted value.
	 */
	private String formatted;
	
	/**
	 * By default value is 'N' 
	 * and is set to return the the 
	 * results starting at the first row. 
	 * If pagination is needed the starting row 
	 * can be offset to return the results 
	 * at a particular row number.
	 */
	private String startingRow;
	
	/**
	 * By default value is 'N' 
	 * and the max rows per result 
	 * set is set to return the first 
	 * 100 records. This number can be 
	 * over-ridden by applying a different value.
	 */
	private String maxRows;
	
	/**
	 * By default, all parentproductTypes 
	 * will be included; however you can 
	 * specify which value you want to limit
	 * the results to.
	 */
	private String parentProductTypeId;
	
	/**
	 * By default, all productTypes 
	 * will be included; however you 
	 * can specify which values you 
	 * want to limit the results to.
	 */
	private String productTypeId;
	
	/**
	 * By default, all subProductTypes
	 * will be included; however you can
	 * specify which value you want to 
	 * limit the results to.
	 */
	private String subProductTypeId;
	
	/**
	 * By default sort order (i.e. orderby)
	 * will be by modelNo asc (in ascending order).
	 * This can be over-ridden by passing in the 
	 * field and order you want the results produced.
	 */
	private String sortBy;
	
	/**
	 * csv of fields to facet by
	 */
	private String facetBy;
	
	/**
	 * Y or N flag to perform fuzzy search. Default value is N.
	 */
	private boolean fuzzySearch;

	public String getFacetBy() {
		return facetBy;
	}

	public void setFacetBy(String facetBy) {
		this.facetBy = facetBy;
	}

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
	 * @return the parentProductTypeId
	 */
	public String getParentProductTypeId() {
		return parentProductTypeId;
	}

	/**
	 * @param parentProductTypeId the parentProductTypeId to set
	 */
	public void setParentProductTypeId(String parentProductTypeId) {
		this.parentProductTypeId = parentProductTypeId;
	}

	/**
	 * @return the productTypeId
	 */
	public String getProductTypeId() {
		return productTypeId;
	}

	/**
	 * @param productTypeId the productTypeId to set
	 */
	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	/**
	 * @return the subProductTypeId
	 */
	public String getSubProductTypeId() {
		return subProductTypeId;
	}

	/**
	 * @param subProductTypeId the subProductTypeId to set
	 */
	public void setSubProductTypeId(String subProductTypeId) {
		this.subProductTypeId = subProductTypeId;
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
		builder.append("brand", brand);
		builder.append("formatted", formatted);
		builder.append("startingRow", startingRow);
		builder.append("maxRows", maxRows);
		builder.append("parentProductTypeId", parentProductTypeId);
		builder.append("productTypeId", productTypeId);
		builder.append("subProductTypeId", subProductTypeId);
		builder.append("sortBy", sortBy);
		builder.append("facetBy", facetBy);
		builder.append("fuzzySearch", fuzzySearch);
		
		
		return builder.toString();
	}

	public boolean isFuzzySearch() {
		return fuzzySearch;
	}

	public void setFuzzySearch(boolean fuzzySearch) {
		this.fuzzySearch = fuzzySearch;
	}
	
}
