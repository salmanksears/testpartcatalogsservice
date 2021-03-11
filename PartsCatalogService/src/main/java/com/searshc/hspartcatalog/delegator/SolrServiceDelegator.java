package com.searshc.hspartcatalog.delegator;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.searshc.hspartcatalog.adapter.SolrServiceItemSearchAdapter;
import com.searshc.hspartcatalog.domain.solr.Item;
import com.searshc.hspartcatalog.domain.solr.Model;
import com.searshc.hspartcatalog.domain.solr.SpinItem;

@Component
public class SolrServiceDelegator {

	private final static Logger LOGGER = LoggerFactory.getLogger(SolrServiceDelegator.class);
	
	private HttpSolrServer httpSolrServer;
	
	@Autowired
	private SolrServiceItemSearchAdapter solrServiceItemSearchAdapter;
	
	public List<Model> searchModelFuzzy(String modelNo) throws Exception {
		
		SolrQuery query = solrServiceItemSearchAdapter.prepareSearchModelFuzzyQuery(modelNo);
		
		QueryResponse response = executeSolrQuery(query);
		
		List<Model> models = response.getBeans(Model.class);
		LOGGER.debug("models results size >>> {}", models.size());
		return models;
	}
	
	
	public List<Item> searchItemModelJoinSchematic(String formattedModelNo) throws Exception{
		SolrQuery query = solrServiceItemSearchAdapter.prepareSearchItemModelJoinSchematicQuery(formattedModelNo);
		
		QueryResponse response = executeSolrQuery(query);
		
		List<Item> items = response.getBeans(Item.class);
		LOGGER.debug("items results size >>> {}", items.size());
		LOGGER.debug("{}", items);
		return items;
	}
 
	private QueryResponse executeSolrQuery(SolrQuery query) throws Exception{
		QueryResponse response = httpSolrServer.query(query);
		SolrDocumentList results = response.getResults();
		LOGGER.debug("SolrDocumentList results size >>> {}", results.size());
		return response;
	}
}
