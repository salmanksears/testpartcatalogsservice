package com.searshc.hspartcatalog.services.bo;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.SOLR_LAYER_EXCEPTION;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.ALL_ROWS_JSON;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.CONTENT_TYPE;
import static com.searshc.hspartcatalog.util.CommonUtils.escapeSOLRSpecialChars;

import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;

import com.searshc.hspartcatalog.exceptions.SolrException;

public class SearchQueryBuilder extends AbstractSolrQueryBuilder {

	private String avalue;

	public SearchQueryBuilder(String avalue) {
		this.setQueryBuffer(new StringBuilder());
		this.avalue = avalue;
	}

	@Override
	public String buildSolrQuery() throws SolrException {

		try {
			boolean spaces = (avalue.contains(" ") ? true : false);
			boolean special = (StringUtils.isAlphanumeric(avalue) ? false : true);
			
			if(special)
				avalue = escapeSOLRSpecialChars(URLEncoder.encode(avalue, CONTENT_TYPE));
			if (spaces)
				avalue = "\"" + avalue + "\"";
			getQueryBuffer().append("catchAllSearch:").append(avalue).append(ALL_ROWS_JSON);
		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}

		return getQueryBuffer().toString();
	}

	@Override
	public String buildQuery() throws SolrException {
		return null;
	}
}