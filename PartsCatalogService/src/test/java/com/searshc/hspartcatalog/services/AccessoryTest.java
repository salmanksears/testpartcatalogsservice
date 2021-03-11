/**
 * 
 */
package com.searshc.hspartcatalog.services;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.Context;
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
import com.searshc.hspartcatalog.services.domain.Accessory;
import com.searshc.hspartcatalog.services.domain.Client;
import com.searshc.hspartcatalog.services.domain.Maintenance;
import com.searshc.hspartcatalog.services.service.PartsCatalogService;
import com.searshc.hspartcatalog.services.service.impl.PartsCatalogServiceImpl;
import com.searshc.hspartcatalog.services.vo.response.AccessoryResponse;
import com.searshc.hspartcatalog.util.ThreadLocalContainer;

public class AccessoryTest {

	private String clientKey = null;
	private String responseFormat = null;
	private String maxRows = null;

	@Context
	private UriInfo uriInfo;

	private static final Logger logger = LoggerFactory.getLogger(AccessoryTest.class);

	@Before
	public void setup() {
		clientKey = "123";
		responseFormat = "application/xml";
		maxRows = "10";
		uriInfo = Mockito.mock(UriInfo.class);
		Mockito.when(uriInfo.getAbsolutePath()).thenReturn(URI.create("http://localhost:8080/test"));
	}

	@Test
	public void happyFlow() {

		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContextTest-beans.xml");
			PartsCatalogService service = (PartsCatalogServiceImpl) ctx.getBean("partsCatalogService");
			ClientConfigBO clientBO = (ClientConfigBO) ctx.getBean("clientConfigBO");

			Client client = clientBO.getClientDetailsByKey(clientKey);
			ThreadLocalContainer.setClient(client);
			String modelNo = "AEQB6000ES0";
			String brand = "1428";
			String productTypeId = "0151200";

			AccessoryResponse response = (AccessoryResponse) service.getAccessories(clientKey, productTypeId, brand,
					modelNo, responseFormat, maxRows, uriInfo, null);
			logger.debug("{}", response);
			if (response.getResponseCode().equals(PartsCatalogServiceConstants.RESPONSE_CODE.SUCCESS)) {
				List<Accessory> accessories = response.getAccessories();
				List<Maintenance> maintenanceParts = response.getMaintenances();
				int cnt = Integer.parseInt(response.getNumFound());
				logger.debug("Total number of Accessory/Maintenance parts found >>> {}", response.getNumFound());
				if (cnt > 1) {
					for (Accessory accessory : accessories) {
						logger.debug("{}", accessory);
					}
					for(Maintenance maintenance: maintenanceParts){
						logger.debug("{}", maintenance);
					}
				}
			}

		} catch (Exception e) {
			logger.error("{}", e.getMessage(), e);
		}
	}
	
	@Test
	public void testGetClientByKey() throws Exception{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContextTest-beans.xml");
		ClientConfigBO clientBO = (ClientConfigBO) ctx.getBean("clientConfigBO");
		Client client = clientBO.getClientDetailsByKey("0000000002"); // SEARS_WEB
		logger.debug("Client is:: [{}]", client);
	}
}