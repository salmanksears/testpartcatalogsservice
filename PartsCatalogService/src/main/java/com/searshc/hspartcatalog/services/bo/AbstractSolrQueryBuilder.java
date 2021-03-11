package com.searshc.hspartcatalog.services.bo;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.QUOTE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.CONTENT_TYPE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.RESP_FORMAT_JSON;
import static com.searshc.hspartcatalog.util.CommonUtils.escapeSOLRSpecialChars;
import static com.searshc.hspartcatalog.util.CommonUtils.escapeSOLRSpecialCharsWildCard;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.searshc.hspartcatalog.exceptions.SolrException;

public abstract class AbstractSolrQueryBuilder {
	
	private StringBuilder queryBuffer;
	
	
	public abstract String buildSolrQuery() throws SolrException;
	
	public abstract String buildQuery() throws SolrException;

	public StringBuilder getQueryBuffer() {
		return queryBuffer;
	}
	

	public void setQueryBuffer(StringBuilder queryBuffer) {
		this.queryBuffer = queryBuffer;
	}

	protected void appendArbitraryString(String value) throws UnsupportedEncodingException {
		getQueryBuffer().append(escapeSOLRSpecialChars(URLEncoder
				.encode(value, CONTENT_TYPE)));
	}
	
	protected void appendArbitraryStringAsIs(String value){
		getQueryBuffer().append(value);
	}

	/**
	 * @param searchField
	 * @param searchFor
	 * @param escapeSpecialChars
	 * @throws UnsupportedEncodingException
	 */
	protected void appendSearchParameterAsIs(String searchField,
			String searchFor, boolean escapeSolrWildCarsChars)
			throws UnsupportedEncodingException {
		
		String searchForEncoded;
		if (escapeSolrWildCarsChars) {
			searchForEncoded = escapeSOLRSpecialCharsWildCard(URLEncoder.encode(searchFor,CONTENT_TYPE)); 
		}else{
			searchForEncoded = URLEncoder.encode(searchFor, CONTENT_TYPE);
		}
		
		getQueryBuffer().append(searchField).append(searchForEncoded);
	

	}

	/**
	 * @param searchField
	 * @param searchFor
	 * @param escapeSpecialChars
	 * @throws UnsupportedEncodingException
	 */
	protected void appendSearchParameterWithQuotes(String searchField,
			String searchFor, boolean escapeSpecialChars)
			throws UnsupportedEncodingException {
		
		String searchForEncoded;  
		if (escapeSpecialChars) {
			searchForEncoded = escapeSOLRSpecialChars(URLEncoder.encode(searchFor,CONTENT_TYPE)); 
				
		}else{
			searchForEncoded = URLEncoder.encode(searchFor, CONTENT_TYPE);
		}
		
		getQueryBuffer().append(searchField)
					.append(QUOTE)
					.append(searchForEncoded).append(QUOTE);
		

	}

	/**
	 * 
	 */
	protected void appendResponseFormat() {
		getQueryBuffer().append(RESP_FORMAT_JSON);
		
	}

}
