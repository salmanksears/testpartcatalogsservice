package com.searshc.hspartcatalog.services.bo;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.SOLR_LAYER_EXCEPTION;

import java.io.UnsupportedEncodingException;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.OR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.ROUND_BRAC_OPEN;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.ROUND_BRAC_CLOSE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.JOIN_FROM_SPIN_TO_MODEL_NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.JOIN_FROM_SPIN_PLS_ITEM_TO_MODEL_NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.JOIN_FROM_SPIN_VENDOR_STOCK_NMBR_TO_MODEL_NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.JOIN_FROM_SPIN_MANUFACTURER_NMBR_TO_MODEL_NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_EXCLUSION_MODEL_NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.AND;
import com.searshc.hspartcatalog.exceptions.SolrException;
import com.searshc.hspartcatalog.services.vo.request.ModelSearchRequest;

public class ModelQueryBuilderWithSpinData extends ModelQueryBuilder {

	
	

	public ModelQueryBuilderWithSpinData(ModelSearchRequest modelSearchRequest,
			StringBuilder exclusionQuery) {
		super(modelSearchRequest, exclusionQuery);
	
	}	

	@Override
	public String buildSolrQuery() throws SolrException {

		try {
			appendSpinData().appendBrandQuery().appendProductTypeQuery()
					.appendSubProductTypeQuery().appendParentProductTypeQuery()
					.appendExclusionQuery().appendFacets()
					.appendSolrResponseFormattingParameters();

		} catch (UnsupportedEncodingException e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);

		}

		return getQueryBuffer().toString();

	}

	/**
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	protected ModelQueryBuilderWithSpinData appendSpinData()
			throws UnsupportedEncodingException {
		
			 String modelNo = getModelSearchRequest().getModelNo();
	         String formattedModelNo = modelNo.replaceAll("[^A-Za-z0-9]", "");
	         appendSpinQuery(formattedModelNo,JOIN_FROM_SPIN_PLS_ITEM_TO_MODEL_NO);
	         appendArbitraryString(OR);
	         appendSpinQuery(formattedModelNo,JOIN_FROM_SPIN_MANUFACTURER_NMBR_TO_MODEL_NO);
	         appendArbitraryString(OR);
	         appendSpinQuery(formattedModelNo,JOIN_FROM_SPIN_VENDOR_STOCK_NMBR_TO_MODEL_NO);
	         appendArbitraryStringAsIs(FILTER_EXCLUSION_MODEL_NO);
	         appendArbitraryStringAsIs(formattedModelNo);
	         appendArbitraryStringAsIs("*");
      		 return this;
	}

	
	/**
	 * @param formattedModelNo
	 * @param join
	 * @throws UnsupportedEncodingException
	 */
	private void appendSpinQuery(String formattedModelNo,String join)
			throws UnsupportedEncodingException {
		 appendArbitraryStringAsIs(ROUND_BRAC_OPEN);
		 appendArbitraryStringAsIs(join);
         appendArbitraryString(formattedModelNo); 
         appendArbitraryStringAsIs(ROUND_BRAC_CLOSE);
     
	}



	

}
