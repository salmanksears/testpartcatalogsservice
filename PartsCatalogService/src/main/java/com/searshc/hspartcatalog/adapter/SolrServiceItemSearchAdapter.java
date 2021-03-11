package com.searshc.hspartcatalog.adapter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.solr.client.solrj.SolrQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SolrServiceItemSearchAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(SolrServiceItemSearchAdapter.class);
	
	@Value("${model.similarity.factor}")
	private String modelSimilarityFactor;
	
	public SolrQuery prepareSearchModelFuzzyQuery(String modelNo){
		String formattedModelNo = modelNo.replaceAll("[^A-Za-z0-9]", ""); // TODO - move to utility
		
		SolrQuery query = new SolrQuery();
		String queryStr = String.format("formattedModelNo:%s~%s", formattedModelNo, modelSimilarityFactor);    
		query.setQuery(queryStr);
		query.setStart(0);
		query.setRows(1500); //TODO - fetch from request other wise properties file.
		
		queryLogger(query);
		
		return query;
	}
	
	
	
	
	public SolrQuery prepareSearchItemModelJoinSchematicQuery(String formattedModelNo){
		SolrQuery query = new SolrQuery();
		String queryStr = String.format("{!join from=modelSchematics to=itemSchematics}formattedModelNo:%s", formattedModelNo.replaceAll("[^A-Za-z0-9]", ""));
		query.setQuery(queryStr);
		query.setStart(0);
		query.setRows(1500); //TODO - fetch from request other wise properties file.
		
		queryLogger(query);
		
		return query;
	}
	
	
	private static void queryLogger(SolrQuery query){
		String str = query.toString();
		try {
			str = URLDecoder.decode(str, "UTF-8");
		}
		catch(UnsupportedEncodingException usee) {
			LOGGER.error("UnsupportedEncodingException >>> {}", usee.getMessage());
		}
		
		LOGGER.debug("SOLR query --> {}", str);
	}
}
