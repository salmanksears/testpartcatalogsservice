package com.searshc.hspartcatalog.services.task;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.JSON;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.XML;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

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
import org.springframework.util.CollectionUtils;

import com.searshc.hs.psc.service.constants.Constants;
import com.searshc.hspartcatalog.exceptions.CcdbException;
import com.searshc.hspartcatalog.services.bo.ClientConfigBO;
import com.searshc.hspartcatalog.services.bo.SolrServiceInvokerBO;
import com.searshc.hspartcatalog.services.domain.BrandCcdb;
import com.searshc.hspartcatalog.services.domain.Item;
import com.searshc.hspartcatalog.services.domain.ItemAttribute;
import com.searshc.hspartcatalog.services.domain.ItemAttributeCcdb;
import com.searshc.hspartcatalog.services.domain.ItemAvailabilityStatusCcdb;
import com.searshc.hspartcatalog.services.domain.ItemRestriction;
import com.searshc.hspartcatalog.services.domain.ItemRestrictionCcdb;
import com.searshc.hspartcatalog.services.domain.Model;
import com.searshc.hspartcatalog.services.domain.ProductGroupCcdb;
import com.searshc.hspartcatalog.services.domain.ProductTypeCcdb;
import com.searshc.hspartcatalog.services.domain.Schematic;
import com.searshc.hspartcatalog.services.vo.request.GetItemDetailsRequest;
import com.searshc.hspartcatalog.services.vo.request.GetModelDetailsRequest;
import com.searshc.hspartcatalog.services.vo.request.ItemSearchRequest;
import com.searshc.hspartcatalog.services.vo.response.GetItemDetailsResponse;
import com.searshc.hspartcatalog.services.vo.response.GetModelDetailsResponse;
import com.searshc.hspartcatalog.services.vo.response.ItemSearchResponse;
import com.searshc.hspartcatalog.util.CommonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextTest-beans.xml")
public class ItemSearchTest {

	@Autowired
	SolrServiceInvokerBO solrServiceInvokerBO;

	@Autowired
	private ClientConfigBO clientConfigBO;

	private String clientKey = "6AnAaqYHAzuqW6KQP4unb2ZgA4Al6nAu"; //FIXYA
	//private String clientKey = "DcXeSK4QEvA3tblQdqEhoJCzvXgW5CeW";	//ERP
	
	 //private String itemId = "0057528329769433";
	//private String itemId = "0057528325C124090";
	private String itemId = "0057528325C111010";

	private ItemSearchRequest request = null; 

	List<BrandCcdb> brandIdList = null;
	List<ProductTypeCcdb> productTypeList = null;
	List<ProductGroupCcdb> productGroupList = null;
	List<ItemAvailabilityStatusCcdb> availabilityStatusList = null;
	List<ItemAttributeCcdb> attributeList = null;
	List<ItemRestrictionCcdb> restrictionList = null;
	List<String> definedParamList = null;
	private BigDecimal itemSellPriceLimit;

	private static final Logger logger = LoggerFactory
			.getLogger(ItemSearchTest.class);

	@Before
	public void setUp() throws Exception {
		request = new ItemSearchRequest();
		request.setClientKey(clientKey);
		request.setItemId(itemId);
		request.setResponseFormat(XML);

		Integer clientId = getClientId(clientKey);
		brandIdList = getBrandIds(clientId);
		productTypeList = getProductTypes(clientId);
		productGroupList = getProductGroups(clientId);
		availabilityStatusList = getItemAvailabilityStatus(clientId);
		attributeList = getItemAttributes(clientId);
		restrictionList = getItemRestriction(clientId);
		itemSellPriceLimit = getItemSellPriceLimit(clientId);

		String id = CommonUtils.getProcessId();
		MDC.put(Constants.PROCESS_ID, id);
		MDC.put(Constants.CLIENT, "ERP");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testItemSearch() {
		try {
			
			ItemSearchResponse response = (ItemSearchResponse) solrServiceInvokerBO
					.itemSearch(request, productGroupList,
							availabilityStatusList, 
							restrictionList, itemSellPriceLimit);
			assertNotNull(response);
			List<Item> items = response.getItems();
			String numFound = response.getNumFound();
			
			if(items == null || items.size() == 0) {
				logger.debug("NO Items found...");
				return;
			}
			
			logger.debug("Items number found >>> {} list size >>> {}", numFound, items.size());
			
			for (Item item : items) {
				//logger.debug("Item ID: {} Desc: {}", item.getItemId(), item.getItemDescription());
			}
			
			//List<Schematic> schematics = response.();
			// assertTrue(schematics.size() > 0);getItemSchematics
			//logger.debug("Number of Schematics >>> {}", (schematics == null ? 0
			//		: schematics.size()));
			//if (schematics != null) {
			//	for (Schematic o : schematics) {
			//		logger.trace("Schematics URL >>> {}", o.getSchematicURL());
			//	}
			//}

			//List<ItemAttribute> attributes = item.getAttributes();
			// assertTrue(attributes.size() > 0);
			//logger.debug("Number of Attributes >>> {}", (attributes == null ? 0
			//		: attributes.size()));
			//if (attributes != null) {
			//	for (ItemAttribute o : attributes) {
			//		logger.debug("Attribute >>> {} ", o);
			//	}
			//}

			//List<ItemRestriction> restrictions = item.getRestrictions();
			// assertTrue(restrictions.size() > 0);
			//logger.debug("Number of Restrictions >>> {}",
			//		(restrictions == null ? 0 : restrictions.size()));
			//if (restrictions != null) {
			//	for (ItemRestriction o : restrictions) {
			//		logger.debug("Restriction >>> {} ",
			//				o);
			//	}
			//}
			
			
			logger.debug("The Test has ended processId: {} response code : {}",
					response.getProcessId(), response.getResponseCode());
		} catch (Exception e) {
			logger.error("Get Item Details Exception >>> {}", e.getMessage());
			e.printStackTrace();
		}
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
