package com.searshc.hspartcatalog.services.bo;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.COMMA;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.ROUND_BRAC_CLOSE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.ROUND_BRAC_OPEN;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.SPACE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.PRODUCT_TYPE_ID_FIXED_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.SOLR_LAYER_EXCEPTION;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.ALL_ROWS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FACET_FIELD;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FACET_MIN_COUNT_DEFAULT;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FACET_ON;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_BRAND;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_PRODUCT_TYPE_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_SUB_PRODUCT_TYPE_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.ROWS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.SORT;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.START;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.DEFAULT_FUZZY_FACTOR;


import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;

import com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST;
import com.searshc.hspartcatalog.exceptions.SolrException;
import com.searshc.hspartcatalog.services.vo.request.ModelSearchRequest;

public class ModelQueryBuilder extends AbstractSolrQueryBuilder{

	
	private StringBuilder exclusionQuery;
	private ModelSearchRequest modelSearchRequest;

	public ModelQueryBuilder(ModelSearchRequest modelSearchRequest,
			StringBuilder exclusionQuery) {
		this.setQueryBuffer(new StringBuilder());
		this.modelSearchRequest = modelSearchRequest;
		this.exclusionQuery = exclusionQuery;
	}

	
	
	/**
	 * @return
	 * @throws SolrException
	 */
	@Override
	public String  buildSolrQuery() throws SolrException {

		try {

			appendModelQuery().appendBrandQuery().appendProductTypeQuery()
					.appendSubProductTypeQuery().appendParentProductTypeQuery()
					.appendExclusionQuery().appendFacets().appendSolrResponseFormattingParameters();

		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}

		return getQueryBuffer().toString();

	}
	
	/** Method buildQuery : builds the query that needs to be passed to solr for fetching results 
	 * for exact, starts with, contains and fuzzy matches
	 * **/
	public String  buildQuery() throws SolrException {

		try {

					appendModelNoQuery().appendProductTypeQuery()
					.appendSubProductTypeQuery().appendParentProductTypeQuery()
					.appendExclusionQuery().appendFacets().appendSolrResponseFormattingParameters();

		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}

		return getQueryBuffer().toString();

	}




	public ModelSearchRequest getModelSearchRequest() {
		return modelSearchRequest;
	}

	/**
	 * 
	 */
	private void  appendFieldsToDisplay() {
		getQueryBuffer().append("&fl=*,score");
	}

	/**
	 * @param modelNo
	 * @throws UnsupportedEncodingException
	 */
	private void appendModelId()
			throws UnsupportedEncodingException {

		if (StringUtils.isEmpty(modelSearchRequest.getModelId())) {
			return;
		}
		
		String formattedModelId= modelSearchRequest.getModelId().replaceAll("[^A-Za-z0-9]","");
		if (modelSearchRequest.getModelId().endsWith("*")){
			appendSearchParameterAsIs(SOLR_CONST.MODEL_ID,modelSearchRequest.getModelId(), true);
			appendArbitraryString(" OR ");
			appendSearchParameterAsIs(SOLR_CONST.FORMATTED_MODEL_ID,formattedModelId, true);
		
		}else{
			appendSearchParameterWithQuotes(SOLR_CONST.MODEL_ID,modelSearchRequest.getModelId(), true);
			appendArbitraryString(" OR ");
			appendSearchParameterWithQuotes( SOLR_CONST.FORMATTED_MODEL_ID, formattedModelId, true);
		
		}	
	}

	/**
	 * 
	 * @throws UnsupportedEncodingException
	 */
	private void appendModelNumberOrModelId()
			throws UnsupportedEncodingException {

		String modelNo = this.modelSearchRequest.getModelNo();
	
		if (StringUtils.isNotEmpty(modelNo)) {
			 appendModelNo(modelNo);
	
		} else {
			appendModelId();
		}
	}

	private void appendModelNumber()
			throws UnsupportedEncodingException {

		String modelNo = this.modelSearchRequest.getModelNo();
	
		if (StringUtils.isNotEmpty(modelNo)) {
			appendModelNumber(modelNo);
		} 
	}

	private void appendModelNo(String modelNo)
			throws UnsupportedEncodingException {
		String formattedModelNo = modelNo.replaceAll("[^A-Za-z0-9]", "");
        StringBuilder builder = new StringBuilder(formattedModelNo);
		
		if(modelSearchRequest.isFuzzySearch()){
			   appendSearchParameterAsIs(SOLR_CONST.MODEL_NO_FOR_ENHANCED_SEARCH, builder.append(DEFAULT_FUZZY_FACTOR).toString(), false);
				
		 }else{
			 
			 appendSearchParameterWithQuotes(SOLR_CONST.FORMATTED_MODEL_NO, formattedModelNo, false);
			 appendArbitraryString(" OR ");
			 appendSearchParameterAsIs(SOLR_CONST.MODEL_NO_FOR_ENHANCED_SEARCH, builder.append("*").toString(), false);
			 	 
		 }
	}
	
	private void appendModelNumber(String modelNo)
			throws UnsupportedEncodingException {
		String formattedModelNo = modelNo;
		if(formattedModelNo.contains("(*)")){
			formattedModelNo = formattedModelNo.replaceAll("\\(\\*\\)", "*");
		}
		formattedModelNo = formattedModelNo.replaceAll("\\(.*?\\)", "");
		if(formattedModelNo.contains(" ")){
			formattedModelNo = formattedModelNo.replaceAll(" ", "*");
		}
		appendSearchParameterWithQuotes(SOLR_CONST.MODEL_NO, modelNo, false);
		appendArbitraryString(" OR ");
		String startsWith = formattedModelNo+"*";
		appendSearchParameterAsIs(SOLR_CONST.MODEL_NO, startsWith, false);
		appendArbitraryString(" OR ");
		String contains = "*"+formattedModelNo+"*";
		appendSearchParameterAsIs(SOLR_CONST.MODEL_NO, contains, false);
	}
	
/*	
	*//**
	 * 
	 * @throws UnsupportedEncodingException
	 *//*
	private void appendModelNumberOrModelIdOld()
			throws UnsupportedEncodingException {

		String modelNo = this.modelSearchRequest.getModelNo();
	
		if (StringUtils.isNotEmpty(modelNo)) {
			
			if (modelNo.endsWith("*")) {
				modelNo = modelNo.substring(0,modelNo.length() - 1);
			}			
			
			String formattedModelNo = modelNo.replaceAll("[^A-Za-z0-9]", "");

			appendSearchParameterAsIs(SOLR_CONST.MODEL_NO, modelNo.concat("*"),
					true);

			appendArbitraryString(" OR ");

			appendSearchParameterWithQuotes(SOLR_CONST.MODEL_NO, modelNo, false);

			appendArbitraryString(" OR ");

			appendSearchParameterAsIs(SOLR_CONST.FORMATTED_MODEL_NO,
					formattedModelNo.concat("*"), true);
			
			appendArbitraryString(" OR ");

			appendSearchParameterWithQuotes(SOLR_CONST.FORMATTED_MODEL_NO,
					formattedModelNo, true);
			
			appendArbitraryString(" OR ");
     
     		appendSearchParameterAsIs(SOLR_CONST.MODEL_NO_FOR_ENHANCED_SEARCH, formattedModelNo, true);

		} else {
			if (modelSearchRequest.getModelId().endsWith("*"))
				appendSearchParameterAsIs(SOLR_CONST.MODEL_ID,
						modelSearchRequest.getModelId(), true);

			else

				appendSearchParameterWithQuotes(SOLR_CONST.MODEL_ID,
						modelSearchRequest.getModelId(), true);
		}
	}*/
	

	/**
	 * @throws UnsupportedEncodingException
	 */
	private ModelQueryBuilder appendSortByQuery() throws UnsupportedEncodingException {
		if (modelSearchRequest.getSortBy() != null)
			appendSearchParameterAsIs(SORT, modelSearchRequest.getSortBy(),
					false);
		return this;
	}

	
	/**
	 * @throws UnsupportedEncodingException
	 */
	protected  ModelQueryBuilder appendBrandQuery() throws UnsupportedEncodingException {
		if (modelSearchRequest.getBrand() != null) {
			if (modelSearchRequest.getBrand().endsWith("*"))
				appendSearchParameterAsIs(FILTER_BRAND,
						modelSearchRequest.getBrand(), true);

			else
				appendSearchParameterWithQuotes(FILTER_BRAND,
						modelSearchRequest.getBrand(), true);
		}
		
		return this;
	}
	

	/**
	 * 
	 */
	protected ModelQueryBuilder appendExclusionQuery() {
		getQueryBuffer().append(exclusionQuery);
		return this;
	}




	/**
	 * 
	 */
	protected ModelQueryBuilder appendFacets() {
		if(modelSearchRequest.getFacetBy() == null) return this;
		
		String[] facetByFieldNames = modelSearchRequest.getFacetBy().split(",");
		
		getQueryBuffer().append(FACET_ON);
		
		for (String fieldName : facetByFieldNames) {
			getQueryBuffer().append(FACET_FIELD).append(fieldName);
		}
		getQueryBuffer().append(FACET_MIN_COUNT_DEFAULT);
		return this;
	}

	/**
	 * @throws UnsupportedEncodingException
	 */
	protected ModelQueryBuilder appendModelQuery() throws UnsupportedEncodingException {

		appendModelNumberOrModelId();
		return this;
	}
	
	//Appends model No to Query
	protected ModelQueryBuilder appendModelNoQuery() throws UnsupportedEncodingException {

		appendModelNumber();
		return this;
	}

	/**
	 * @throws UnsupportedEncodingException
	 */
	protected ModelQueryBuilder appendParentProductTypeQuery()
			throws UnsupportedEncodingException {
		if (StringUtils.isNotBlank(modelSearchRequest.getParentProductTypeId()))
			appendSearchParameterWithQuotes(
					SOLR_CONST.FILTER_PARENT_PRODUCT_TYPE_ID,
					modelSearchRequest.getParentProductTypeId(), false);
		return this;
	}

	/**
	 * @throws UnsupportedEncodingException
	 */
	protected ModelQueryBuilder appendProductTypeQuery() throws UnsupportedEncodingException {
		
		 if (StringUtils.isEmpty(modelSearchRequest.getProductTypeId()))
		  return this;
		
		 if(modelSearchRequest.getProductTypeId().length() > PRODUCT_TYPE_ID_FIXED_LENGTH){
			appendMutipleProductTypes();
			 
		 }else{
			 appendSearchParameterWithQuotes(FILTER_PRODUCT_TYPE_ID,  modelSearchRequest.getProductTypeId(), false);
		 }
		 return this;
	}



	private void appendMutipleProductTypes()
			throws UnsupportedEncodingException {
		
		 String mutipleProductTypeIds = modelSearchRequest.getProductTypeId().replace(COMMA, SPACE);
		 StringBuilder builder = new StringBuilder();
		 builder.append(ROUND_BRAC_OPEN);
		 builder.append(mutipleProductTypeIds);
		 builder.append(ROUND_BRAC_CLOSE);
		 appendSearchParameterAsIs(FILTER_PRODUCT_TYPE_ID, builder.toString(), false);
	}

	/**
	 * @throws UnsupportedEncodingException
	 */
	protected ModelQueryBuilder appendRowsToReturnQuery() throws UnsupportedEncodingException {
		if (modelSearchRequest.getMaxRows() != null)
			appendSearchParameterAsIs(ROWS, modelSearchRequest.getMaxRows(),
					false);
		else
			getQueryBuffer().append(ALL_ROWS);
		return this;
	}

	protected void appendSolrResponseFormattingParameters()
			throws UnsupportedEncodingException {
		appendStartRowQuery();
		appendRowsToReturnQuery();
		appendSortByQuery();
		appendResponseFormat();
		appendFieldsToDisplay();
	}

	/**
	 * @throws UnsupportedEncodingException
	 */
	protected ModelQueryBuilder appendStartRowQuery() throws UnsupportedEncodingException {
		if (modelSearchRequest.getStartingRow() != null)
			appendSearchParameterAsIs(START,
					String.valueOf((Integer.parseInt(modelSearchRequest
							.getStartingRow().trim()) - 1)), false);
		return this;
	}

	/**
	 * @throws UnsupportedEncodingException
	 */
	protected ModelQueryBuilder appendSubProductTypeQuery()
			throws UnsupportedEncodingException {
		if (modelSearchRequest.getSubProductTypeId() != null)
			appendSearchParameterWithQuotes(FILTER_SUB_PRODUCT_TYPE_ID,
					modelSearchRequest.getSubProductTypeId(), false);
		return this;
	}



}