package com.searshc.hspartcatalog.services.bo.tasks;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.QUOTE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.SOLR_LAYER_EXCEPTION;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.CONTENT_TYPE;

import java.net.URLEncoder;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.searshc.hs.psc.logging.annotation.ExtCallAuditLogger;
import com.searshc.hspartcatalog.exceptions.SolrException;
import com.searshc.hspartcatalog.pojo.SolrResponse;
import com.searshc.hspartcatalog.util.ApplicationContextUtils;

public class ModelSchematicsTask implements Callable<SolrResponse> {

	private SolrConnection solrConnection;

	private String clientKey;
	private String modelId;
	private int schematicCnt;

	private static final Logger logger = LoggerFactory
			.getLogger(ModelSchematicsTask.class);

	public ModelSchematicsTask() {
	}

	public ModelSchematicsTask(String clientKey, String modelId, int schematicCnt) {
		this.clientKey = clientKey;
		this.modelId = modelId;
		this.schematicCnt = schematicCnt;
	}

	@Override
	public SolrResponse call() throws Exception {
		return getSchematicList(clientKey, modelId);
	}

	@ExtCallAuditLogger(logInput = false, logOutput = false)
	private SolrResponse getSchematicList(String clientKey, 
			String modelId) throws SolrException {
		SolrResponse solrResponse = null;

		StringBuilder query = new StringBuilder();
		try {

			query.append("{!join+from=modelSchematics+to=id}modelId:").append(
					URLEncoder.encode(QUOTE + modelId + QUOTE, CONTENT_TYPE));

			query.append("&rows=").append(schematicCnt).append("&wt=json");
			
			solrResponse = getSolrConnection().invoke(clientKey, query.toString());
		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
		return solrResponse;
	}
	
	public SolrConnection getSolrConnection() {
		if (solrConnection == null) {
			ApplicationContext appCtx = ApplicationContextUtils
					.getApplicationContext();
			solrConnection = (SolrConnection) appCtx.getBean("solrConnection");
		}

		return solrConnection;
	}
}
