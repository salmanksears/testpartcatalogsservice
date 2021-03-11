/**
 * 
 */
package com.searshc.hspartcatalog.services.service.impl;

import java.util.List;

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
import com.searshc.hspartcatalog.services.domain.ModelFacetCounts;
import com.searshc.hspartcatalog.services.service.PartsCatalogService;
import com.searshc.hspartcatalog.services.vo.response.ModelSearchResponse;
import com.searshc.hspartcatalog.util.ThreadLocalContainer;

public class PartCatalogModelSearchTest {

	PartsCatalogService partsCatalogService;

	private String clientKey;
	private String  brand;
	private String modelId;
	private String parentProductTypeId;
	private String productTypeId;
	private String subProductTypeId;
	private String responseFormat;
	private String startingRow;
	private String maxRows;
	private String sortBy;
	private String facetBy;
	private String enableSearsIdSearch;
	private String enableFuzzySearch;
	private UriInfo uriInfo;
	private ApplicationContext context;

	ClientConfigBO clientConfigBO;

	private static final Logger LOGGER = LoggerFactory.getLogger(PartCatalogModelSearchTest.class);

	@Before
	public void setup() {

		System.setProperty("data.directory",
				"C:\\Users\\dgold1\\mars\\workspace\\catalog\\PartsCatalogService\\src\\test\\resources\\");

		clientKey = "1234567890";
		responseFormat = PartsCatalogServiceConstants.GEN_CONST.JSON;
		startingRow = "1";
		maxRows = "100";
		enableSearsIdSearch = PartsCatalogServiceConstants.GEN_CONST.NO;
		enableFuzzySearch = PartsCatalogServiceConstants.GEN_CONST.NO;
		context = new ClassPathXmlApplicationContext("classpath:applicationContextTest-beans.xml");
		uriInfo = Mockito.mock(UriInfo.class);
		Mockito.when(uriInfo.getQueryParameters()).thenReturn(new MultivaluedHashMap<String, String>());
	}
	
	@Test
	public void wild() {

		LOGGER.debug("Test is starting...");	

		//String modelNo = "LBC22520SB01";  //EXACT
		//String modelNo = "LBC22520SB0";     //WILD
		//modelNo = "79574033410";			//EXACT
		//modelNo = "74033";					//CONTAINS
		String modelNo = "CK7000SH1SS";  	//FUZZY
		//modelNo = "LFC20760SB0";
		
		try {
			clientConfigBO = (ClientConfigBO) context.getBean("clientConfigBO");
			Client client = clientConfigBO.getClientDetailsByKey(clientKey);
			ThreadLocalContainer.setClient(client);

			partsCatalogService = (PartsCatalogService) context.getBean("partsCatalogService");

			ModelSearchResponse response = (ModelSearchResponse) partsCatalogService.modelSearchWild(clientKey,modelNo, brand,
					parentProductTypeId, productTypeId, subProductTypeId, responseFormat,
					startingRow, maxRows, sortBy, facetBy, enableFuzzySearch,  uriInfo);
			LOGGER.debug("ModelSearchResponse >>> {}", response);

			ModelFacetCounts facets = response.getFacetCounts();
			LOGGER.debug("Facets >>> {}", facets);
			List<Model> models = response.getModels();
			if (models == null) {
				LOGGER.debug("ZERO MODELS FOUND for {}", modelNo);
			} else {
				LOGGER.debug("FOUND {} MODELS FOUND for {}", models.size(), modelNo);
				for (Model model : models) {
					LOGGER.debug("{}", model);
				}
			}

			LOGGER.debug("Response >>> {} msg >>> {}", response, response.getMessages());
			
		} catch (Exception e) {
			LOGGER.error("Test had some issues", e);
		}
		LOGGER.debug("Test has completed...");
	}
	
	public void enhanced() {

		LOGGER.debug("Test is starting...");

		String modelNo = "CK7000SH1SS";

		try {
			clientConfigBO = (ClientConfigBO) context.getBean("clientConfigBO");
			Client client = clientConfigBO.getClientDetailsByKey(clientKey);
			ThreadLocalContainer.setClient(client);

			partsCatalogService = (PartsCatalogService) context.getBean("partsCatalogService");
			
			ModelSearchResponse response = (ModelSearchResponse) partsCatalogService.modelSearchFuzzy(clientKey,
					modelNo, null, null, null, null, null, responseFormat, startingRow, maxRows, sortBy, facetBy, uriInfo);
			LOGGER.debug("ModelSearchResponse >>> {}", response);

			ModelFacetCounts facets = response.getFacetCounts();
			LOGGER.debug("Facets >>> {}", facets);
			List<Model> models = response.getModels();
			if (models == null) {
				LOGGER.debug("ZERO MODELS FOUND for {}", modelNo);
			} else {
				LOGGER.debug("FOUND {} MODELS FOUND for {}", models.size(), modelNo);
				for (Model model : models) {
					LOGGER.debug("{}", model);
				}
			}

			LOGGER.debug("Response >>> {}", response);
		} catch (Exception e) {
			LOGGER.error("Test had some issues", e);
		}
		LOGGER.debug("Test has completed...");
	}
	
	public void searsid() {

		LOGGER.debug("Test is starting...");

		String modelNo = "ABCDEFG";

		try {
			clientConfigBO = (ClientConfigBO) context.getBean("clientConfigBO");
			Client client = clientConfigBO.getClientDetailsByKey(clientKey);
			ThreadLocalContainer.setClient(client);

			partsCatalogService = (PartsCatalogService) context.getBean("partsCatalogService");
			
			ModelSearchResponse response = (ModelSearchResponse) partsCatalogService.modelSearchBySearsId(clientKey, modelNo, brand, parentProductTypeId,
					productTypeId, subProductTypeId, responseFormat, startingRow, maxRows,
					sortBy, facetBy, uriInfo);
			LOGGER.debug("ModelSearchResponse >>> {}", response);

			ModelFacetCounts facets = response.getFacetCounts();
			LOGGER.debug("Facets >>> {}", facets);
			List<Model> models = response.getModels();
			if (models == null) {
				LOGGER.debug("ZERO MODELS FOUND for {}", modelNo);
			} else {
				LOGGER.debug("FOUND {} MODELS FOUND for {}", models.size(), modelNo);
				for (Model model : models) {
					LOGGER.debug("{}", model);
				}
			}

			LOGGER.debug("Response >>> {}", response);
		} catch (Exception e) {
			LOGGER.error("Test had some issues", e);
		}
		LOGGER.debug("Test has completed...");
	}

	public void fuzzy() {

		LOGGER.debug("Test is starting...");

		String modelNo = "ABCDEFG";

		try {
			clientConfigBO = (ClientConfigBO) context.getBean("clientConfigBO");
			Client client = clientConfigBO.getClientDetailsByKey(clientKey);
			ThreadLocalContainer.setClient(client);

			partsCatalogService = (PartsCatalogService) context.getBean("partsCatalogService");
			
			ModelSearchResponse response = (ModelSearchResponse) partsCatalogService.modelSearchFuzzy(clientKey, modelNo, modelId, brand,
					parentProductTypeId, productTypeId,  subProductTypeId, responseFormat,
					 startingRow,  maxRows,  sortBy,  facetBy,  uriInfo);
			LOGGER.debug("ModelSearchResponse >>> {}", response);

			ModelFacetCounts facets = response.getFacetCounts();
			LOGGER.debug("Facets >>> {}", facets);
			List<Model> models = response.getModels();
			if (models == null) {
				LOGGER.debug("ZERO MODELS FOUND for {}", modelNo);
			} else {
				LOGGER.debug("FOUND {} MODELS FOUND for {}", models.size(), modelNo);
				for (Model model : models) {
					LOGGER.debug("{}", model);
				}
			}

			LOGGER.debug("Response >>> {}", response);
		} catch (Exception e) {
			LOGGER.error("Test had some issues", e);
		}
		LOGGER.debug("Test has completed...");
	}
}