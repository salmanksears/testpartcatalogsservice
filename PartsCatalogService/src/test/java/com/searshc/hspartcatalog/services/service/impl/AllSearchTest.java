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
import com.searshc.hspartcatalog.services.domain.AValue;
import com.searshc.hspartcatalog.services.domain.Client;
import com.searshc.hspartcatalog.services.service.PartsCatalogService;
import com.searshc.hspartcatalog.services.vo.response.SearchResponse;
import com.searshc.hspartcatalog.util.ThreadLocalContainer;

public class AllSearchTest {

	PartsCatalogService partsCatalogService;

	private String clientKey;
	private String responseFormat;
	private String startingRow;
	private String maxRows;
	private UriInfo uriInfo;
	private ApplicationContext context;

	ClientConfigBO clientConfigBO;

	private static final Logger LOGGER = LoggerFactory.getLogger(AllSearchTest.class);

	@Before
	public void setup() {

		System.setProperty("data.directory",
				"C:\\Users\\dgold1\\mars\\workspace\\sandbox\\PartsCatalogService\\src\\test\\resources\\");
		
		clientKey = "1234567890";
		responseFormat = PartsCatalogServiceConstants.GEN_CONST.JSON;
		startingRow = "1";
		maxRows = "100";
		context = new ClassPathXmlApplicationContext("classpath:applicationContextTest-beans.xml");
		uriInfo = Mockito.mock(UriInfo.class);
		Mockito.when(uriInfo.getQueryParameters()).thenReturn(new MultivaluedHashMap<String, String>());
	}
	
	@Test
	public void search() {

		LOGGER.debug("Test is starting...");	

		//String modelNo = "LBC22520SB01";  //EXACT
		//String modelNo = "LBC22520SB0";     //WILD
		//modelNo = "79574033410";			//EXACT
		//modelNo = "74033";					//CONTAINS
		String value = "CK7000SH1SS";  		//MODEL
		//value =  "2909";				//PART
		value = "Water Filter"; 			//Part descrr
		//modelNo = "LFC20760SB0";
		
		try {
			clientConfigBO = (ClientConfigBO) context.getBean("clientConfigBO");
			Client client = clientConfigBO.getClientDetailsByKey(clientKey);
			ThreadLocalContainer.setClient(client);

			partsCatalogService = (PartsCatalogService) context.getBean("partsCatalogService");
			
			SearchResponse response = (SearchResponse) partsCatalogService.search(clientKey, responseFormat, value, 
					startingRow, maxRows, uriInfo);
			
			LOGGER.debug("Search >>> {}", response);
			
			List<AValue> list = response.getValues();
			if (list == null) {
				LOGGER.debug("ZERO FOUND for {}", value);
			} else {
				LOGGER.debug("FOUND {} for {}", response.getValueCnt(), value);
				for (AValue avalue : list) {
					LOGGER.debug("{}", avalue);
				}
			}

			LOGGER.debug("Response >>> {} msg >>> {}", response, response.getMessages());
			
		} catch (Exception e) {
			LOGGER.error("Test had some issues", e);
		}
		LOGGER.debug("Test has completed...");
	}
}