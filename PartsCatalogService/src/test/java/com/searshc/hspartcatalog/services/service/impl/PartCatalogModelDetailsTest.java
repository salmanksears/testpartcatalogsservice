/**
 * 
 */
package com.searshc.hspartcatalog.services.service.impl;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.UriInfo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants;
import com.searshc.hspartcatalog.services.bo.ClientConfigBO;
import com.searshc.hspartcatalog.services.domain.Client;
import com.searshc.hspartcatalog.services.domain.Model;
import com.searshc.hspartcatalog.services.service.PartsCatalogService;
import com.searshc.hspartcatalog.services.vo.response.GetModelDetailsResponse;
import com.searshc.hspartcatalog.util.ThreadLocalContainer;

public class PartCatalogModelDetailsTest {

	PartsCatalogService partsCatalogService;

	private String clientKey;
	private String responseFormat;
	private UriInfo uriInfo;
	private ApplicationContext context;

	ClientConfigBO clientConfigBO;

	private static final Logger LOGGER = LoggerFactory.getLogger(PartCatalogModelDetailsTest.class);

	@Before
	public void setup() {

		System.setProperty("data.directory",
				"C:\\Users\\dgold1\\mars\\workspace\\catalog\\PartsCatalogService\\src\\test\\resources\\");

		clientKey = "1234567890";
		responseFormat = PartsCatalogServiceConstants.GEN_CONST.JSON;
		context = new ClassPathXmlApplicationContext("classpath:applicationContextTest-beans.xml");
		uriInfo = Mockito.mock(UriInfo.class);
		Mockito.when(uriInfo.getQueryParameters()).thenReturn(new MultivaluedHashMap<String, String>());
	}
	
	@Test
	public void runit() {

		LOGGER.debug("Test is starting...");	

		String modelId = "15060000247247290005";
		
		try {
			clientConfigBO = (ClientConfigBO) context.getBean("clientConfigBO");
			Client client = clientConfigBO.getClientDetailsByKey(clientKey);
			ThreadLocalContainer.setClient(client);

			partsCatalogService = (PartsCatalogService) context.getBean("partsCatalogService");
			
			GetModelDetailsResponse response = (GetModelDetailsResponse) partsCatalogService.getModelDetails(clientKey, modelId, responseFormat, uriInfo);
			
			LOGGER.debug("ModelDetailsResponse >>> {}", response);

			Model model = response.getModel();
			if (model == null) {
				LOGGER.debug("ZERO MODELS FOUND for ID:{}", modelId);
			} else {
				LOGGER.debug("FOUND MODEL FOUND for {}", modelId);
				LOGGER.debug("Model Details >>> {}", model);
			}

			LOGGER.debug("Response >>> {} msg >>> {}", response, response.getMessages());
			
		} catch (Exception e) {
			LOGGER.error("Test had some issues", e);
		}
		LOGGER.debug("Test has completed...");
	}
}