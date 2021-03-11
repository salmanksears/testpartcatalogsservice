package com.searshc.hspartcatalog.services.ad;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.XML;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.searshc.hs.psc.service.constants.Constants;
import com.searshc.hspartcatalog.exceptions.CcdbException;
import com.searshc.hspartcatalog.services.bo.ClientConfigBO;
import com.searshc.hspartcatalog.services.bo.SolrServiceInvokerBO;
import com.searshc.hspartcatalog.services.domain.BrandCcdb;
import com.searshc.hspartcatalog.services.domain.Item;
import com.searshc.hspartcatalog.services.domain.ItemAttributeCcdb;
import com.searshc.hspartcatalog.services.domain.ItemAvailabilityStatusCcdb;
import com.searshc.hspartcatalog.services.domain.ItemRestrictionCcdb;
import com.searshc.hspartcatalog.services.domain.ProductGroupCcdb;
import com.searshc.hspartcatalog.services.domain.ProductTypeCcdb;
//import com.searshc.hspartcatalog.services.task.ItemSearchTest;
import com.searshc.hspartcatalog.services.vo.request.ItemDescriptionSearchRequest;
import com.searshc.hspartcatalog.services.vo.response.ItemSearchResponse;
import com.searshc.hspartcatalog.util.CommonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextTest-beans.xml")
public class ItemDescriptionSearchTest {

	@Autowired
	SolrServiceInvokerBO solrServiceInvokerBO;

	@Autowired
	private ClientConfigBO clientConfigBO;

	private ItemDescriptionSearchRequest request = new ItemDescriptionSearchRequest();
	
	private String clientKey = "123";
	// private String clientKey = "6AnAaqYHAzuqW6KQP4unb2ZgA4Al6nAu"; // FIXYA
	// private String clientKey = "DcXeSK4QEvA3tblQdqEhoJCzvXgW5CeW"; //ERP

	List<BrandCcdb> brandIdList = null;
	List<ProductTypeCcdb> productTypeList = null;
	List<ProductGroupCcdb> productGroupList = null;
	List<ItemAvailabilityStatusCcdb> availabilityStatusList = null;
	List<ItemAttributeCcdb> attributeList = null;
	List<ItemRestrictionCcdb> restrictionList = null;
	List<String> definedParamList = null;
	private BigDecimal itemSellPriceLimit;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ItemDescriptionSearchTest.class);

	@Before
	public void setUp() throws Exception {

		Integer clientId = getClientId(clientKey);
		request.setClientKey(clientKey);
		request.setResponseFormat(XML);
		
		brandIdList = getBrandIds(clientId);
		productTypeList = getProductTypes(clientId);
		productGroupList = getProductGroups(clientId);
		availabilityStatusList = getItemAvailabilityStatus(clientId);
		attributeList = getItemAttributes(clientId);
		restrictionList = getItemRestriction(clientId);
		itemSellPriceLimit = getItemSellPriceLimit(clientId);

		String id = CommonUtils.getProcessId();
		MDC.put(Constants.PROCESS_ID, id);
		MDC.put(Constants.CLIENT, "DGOLD1");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void search1() {

		//request.setModelNo("11070602991");
		//request.setBrand("KENMORE");
		//request.setProductType("Laundry*");
		//request.setDescription("Non-resettable Thermal Fuse");
		
		request.setModelNo("41774182300");
		request.setBrand("KENMORE");
		request.setProductType("Laundry*");
		request.setDescription("xxxScrew");
			
		//request.setModelNo("GTDP300EM1WS");
		//request.setBrand("GE");
		//request.setProductType("Laundry*");
		//request.setDescription("Screw");
					
		//request.setModelNo("DV400EWHDWR/AA");
		//request.setBrand("SAMSUNG");
		//request.setProductType("Laundry*");
		//request.setDescription("30A CORD");
		
		ItemSearchResponse response = null;
		
		//q=(modelNo:MGDE200XW1+OR+modelNo:E200XW1)+AND+brand:MAYTAG+AND+productTypeName:Laundry*
		
		try {
				response = (ItemSearchResponse) solrServiceInvokerBO
						.itemDescriptionSearch2(request, productGroupList,
								availabilityStatusList, restrictionList,
								itemSellPriceLimit);
				assertNotNull("ItemSearchResponse was NULL", response);
				
				String numFound = response.getNumFound();
				assertFalse("Number found ZERO", (numFound.compareTo("0") == 0));
				
				List<Item> items = response.getItems();
				logger.debug(
							"Number of Items found >>> {} for item description >>> {}",
							items.size(), request.getDescription());
		
				 for (Item item : items) {
					 logger.debug("ItemId: {} Part No: {} Desc: {}", item.getItemId(), item.getPartNo(),
							 item.getItemDescription());
				 }
		
		} catch (Exception e) {
			logger.error("Item Search Description Exception >>> {}", e.getMessage());
			e.printStackTrace();
		}
		
		logger.debug("The Test has ended...");
	}

	private Integer getClientId(String clientKey) throws Exception {
		logger.trace("Fetching ClientId from ClientConfigDB");
		return clientConfigBO.getClientId(clientKey);
	}

	private List<BrandCcdb> getBrandIds(Integer clientId) throws Exception {
		logger.debug("Fetching BrandIds from ClientConfigDB");
		List<BrandCcdb> list = clientConfigBO.getBrandIds(clientId);

		for (BrandCcdb obj : list) {
			logger.debug("Brand >>> {}", obj.getBrandId());
		}

		return list;
	}

	private List<ProductTypeCcdb> getProductTypes(Integer clientId)
			throws Exception {
		logger.debug("Fetching ProductTypes Status from ClientConfigDB");
		List<ProductTypeCcdb> list = clientConfigBO.getProductType(clientId);
		for (ProductTypeCcdb obj : list) {
			logger.debug("Product Type >>> {}", obj.getProductTypeCd());
		}

		return list;
	}

	private List<ItemAttributeCcdb> getItemAttributes(Integer clientId)
			throws Exception {
		logger.debug("Fetching ItemAttributes from ClientConfigDB");
		List<ItemAttributeCcdb> list = clientConfigBO.getAttributes(clientId);
		for (ItemAttributeCcdb obj : list) {
			logger.debug("Item Attributes >>> {}", obj.getAttributeId());
		}

		return list;
	}

	private List<ItemRestrictionCcdb> getItemRestriction(Integer clientId)
			throws Exception {
		logger.debug("Fetching ItemRestrictions from ClientConfigDB");
		List<ItemRestrictionCcdb> list = clientConfigBO
				.getRestrictions(clientId);
		for (ItemRestrictionCcdb obj : list) {
			logger.debug("Item Restriction >>> {}", obj.getRestrictionId());
		}

		return list;
	}

	private BigDecimal getItemSellPriceLimit(Integer clientId)
			throws CcdbException {
		logger.debug("Fetching ItemSellingPriceLimit from ClientConfigDB");
		BigDecimal bd = clientConfigBO.getItemSellingPriceLimit(clientId);
		logger.debug("ItemSellingPriceLimit >>> {}", bd);

		return bd;
	}

	private List<ProductGroupCcdb> getProductGroups(Integer clientId)
			throws Exception {
		logger.debug("Fetching ProductGroups from ClientConfigDB");
		List<ProductGroupCcdb> list = clientConfigBO.getProductGroup(clientId);
		for (ProductGroupCcdb obj : list) {
			logger.debug("Item Attributes >>> {}", obj.getProductGroupId());
		}

		return list;
	}

	private List<ItemAvailabilityStatusCcdb> getItemAvailabilityStatus(
			Integer clientId) throws Exception {
		logger.trace("Fetching AvailabilityStatus from ClientConfigDB");

		logger.debug("Fetching AvailabilityStatus from ClientConfigDB");
		List<ItemAvailabilityStatusCcdb> list = clientConfigBO
				.getItemAvailabilityStatus(clientId);
		for (ItemAvailabilityStatusCcdb obj : list) {
			logger.debug("Item Attributes >>> {}",
					obj.getItemAvailabilityStatusCd());
		}

		return list;
	}
}
