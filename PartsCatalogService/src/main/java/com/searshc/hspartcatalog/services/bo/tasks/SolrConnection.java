package com.searshc.hspartcatalog.services.bo.tasks;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.SOLR_LAYER_EXCEPTION;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.MAX_RESPONSE_CODE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.MIN_RESPONSE_CODE;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.searshc.hs.psc.logging.annotation.ExtCallAuditLogger;
import com.searshc.hspartcatalog.exceptions.SolrException;
import com.searshc.hspartcatalog.pojo.SolrResponse;

public class SolrConnection {
	
	@Value("${solrEndpointUrl}")
	private String solrEndpoint;
	
	private static final Logger logger = LoggerFactory.getLogger(SolrConnection.class);
	
	@ExtCallAuditLogger(logInput = false, logOutput = false, method="SOLR.invoke")
	public SolrResponse invoke(String clientKey,String query) throws SolrException{
		
		SolrResponse solrResponse = null;
		URL solrRequest = null;
		ObjectMapper objectMapper = new ObjectMapper();
		HttpURLConnection conn=null;
		BufferedReader bReader = null;
		
		try{
			StringBuilder sb = new StringBuilder(solrEndpoint).append(query);
			logger.debug("Executing SOLR Query Length: {} URL : [{}]", sb.toString().length(), sb.toString());
			
			solrRequest = new URL(sb.toString());
			conn = (HttpURLConnection) (solrRequest.openConnection());
			
			int code = conn.getResponseCode();
			if (code < MIN_RESPONSE_CODE && code >= MAX_RESPONSE_CODE){
				throw new SolrException(SOLR_LAYER_EXCEPTION);
			}
			
			bReader = new BufferedReader(new InputStreamReader(new DataInputStream(conn.getInputStream())));

			solrResponse = objectMapper.readValue(bReader, SolrResponse.class);

			return solrResponse;
			
		}catch (Exception e) {
			logger.error("Exception SOLR Query : {}", query);
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}finally{
			if (bReader != null) {
		        try {
		        	bReader.close();
		        } catch (IOException exp) {
		        	logger.error("Exception Occured SOLR Query : {}", query);
		        	logger.error("IOException occurred : {}", exp.getMessage());
		        }
		    }
			if(conn != null)
				conn.disconnect();

		}
	}	
}
