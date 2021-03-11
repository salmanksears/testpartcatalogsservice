package com.searshc.hspartcatalog.services.task;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.JSON;
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
import com.searshc.hspartcatalog.services.vo.request.GetModelDetailsRequest;
import com.searshc.hspartcatalog.services.vo.request.ModelSearchRequest;
import com.searshc.hspartcatalog.services.vo.response.GetModelDetailsResponse;
import com.searshc.hspartcatalog.services.vo.response.ModelSearchResponse;
import com.searshc.hspartcatalog.util.CommonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextTest-beans.xml")
public class ModelDetailsTest {
	
	@Autowired
	SolrServiceInvokerBO solrServiceInvokerBO;
	
	@Autowired
	private ClientConfigBO clientConfigBO;

	private String clientKey = "DcXeSK4QEvA3tblQdqEhoJCzvXgW5CeW";
	private String erp = "DcXeSK4QEvA3tblQdqEhoJCzvXgW5CeW";
	private String fixya = "6AnAaqYHAzuqW6KQP4unb2ZgA4Al6nAu";
	
	//private String modelId = "07552000546IR244";
	//private String modelId = "07022500430GP7500E-5943-5";
	//private String modelId = "13071503266SCHWINN 438";
	//private String modelId = "06450000789MC-V6965";
	//private String modelId = "0333000065454471950";
	//private String modelId = "01650000593106KSSS36QDW05";
	private String modelId = "13072001185WLEX27180";
	
	private GetModelDetailsRequest request = null;
	
	List<BrandCcdb> brandIdList = null;
	List<ProductTypeCcdb> productTypeList  = null;
	List<ProductGroupCcdb> productGroupList = null;
	List<ItemAvailabilityStatusCcdb> availabilityStatusList = null;
	List<ItemAttributeCcdb> attributeList = null; 
	List<ItemRestrictionCcdb> restrictionList = null;
	List<String> definedParamList = null;
	private BigDecimal itemSellPriceLimit;
	
	private static final Logger logger = LoggerFactory.getLogger(ModelDetailsTest.class);
	
	@Before
	public void setUp() throws Exception {
		String id = CommonUtils.getProcessId();
		MDC.put(Constants.PROCESS_ID, id);
		MDC.put(Constants.CLIENT, "ERP");
		System.setProperty("data.directory", "C:\\Users\\dgold1\\mars\\workspace\\solr\\PartsCatalogService\\src\test\\resources\\");
		System.out.println("ALL DONE...");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public final void getModelDetails() {
		try {
			
			GetModelDetailsRequest request = new GetModelDetailsRequest();
			request.setClientKey(clientKey);
			request.setModelId("012400214291111WRW");
			request.setResponseFormat(JSON);
			
			Integer clientId = getClientId(clientKey);
			brandIdList = getBrandIds(clientId);
			productTypeList = getProductTypes(clientId);
			productGroupList = getProductGroups(clientId);
			availabilityStatusList = getItemAvailabilityStatus(clientId);
			attributeList = getItemAttributes(clientId); 
			restrictionList = getItemRestriction(clientId);
			
			GetModelDetailsResponse response = (GetModelDetailsResponse)solrServiceInvokerBO.getModelDetails(request, brandIdList, productTypeList, productGroupList, availabilityStatusList, attributeList, restrictionList, itemSellPriceLimit);
			assertNotNull(response);
			Model model = response.getModel();
			assertNotNull(model);
			logger.debug("Model No >>> {}", model.getModelNo());
			
			List<Schematic> schematics = response.getModelSchematics();
			assertTrue(schematics.size() > 0);
			logger.debug("Number of Schematics >>> {}", schematics.size());
			for(Schematic o : schematics) {
				logger.trace("Schematics URL >>> {}", o.getSchematicURL());
			}
			
			List<Item> items = response.getItems();
			assertTrue(items.size() > 0);
			boolean b = false;
			logger.debug("Number of Items >>> {}", items.size());
			for(Item o : items) {
				logger.trace("Item ID >>> {}", o.getItemId());
				List<ItemAttribute> attributes = o.getAttributes();
				if(!CollectionUtils.isEmpty(attributes)) {
					for(ItemAttribute ia : attributes) {
						logger.debug("Item ID >>> {} ItemAttributes >>> {} ", o.getItemId(), ia);
					}
				}
				List<ItemRestriction> restrictions = o.getRestrictions();
				if(!CollectionUtils.isEmpty(restrictions)) {
					for(ItemRestriction ir : restrictions) {
						logger.debug("Item ID >>> {} ItemRestrictions >>> {} ", o.getItemId(), ir);
					}
				}
			}
			
			logger.debug("The Test has ended processId: {} response code : {}", response.getProcessId(), response.getResponseCode());
		} catch(Exception e) {
			logger.error("Get Model Details Exception >>> {}", e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public final void modelSearchEnhanced() {
		try {
			
			ModelSearchRequest request = new ModelSearchRequest();
			request.setClientKey(clientKey);
			request.setModelId("012400214291111WRW");
			request.setResponseFormat(JSON);
			
			Integer clientId = getClientId(clientKey);
			brandIdList = getBrandIds(clientId);
			productTypeList = getProductTypes(clientId);
			productGroupList = getProductGroups(clientId);
			availabilityStatusList = getItemAvailabilityStatus(clientId);
			attributeList = getItemAttributes(clientId); 
			restrictionList = getItemRestriction(clientId);
			itemSellPriceLimit = getItemSellPriceLimit(clientId);
			
			ModelSearchResponse response = (ModelSearchResponse)solrServiceInvokerBO.modelSearchEnhanced(request, brandIdList, productTypeList);
			assertNotNull(response);
			List<Model> models = response.getModels();
			assertNotNull(models);
			
			for (Model amodel : models) {
				logger.debug("{}", amodel);
			}
			
			
			logger.debug("The Test has ended processId: {} response code : {}", response.getProcessId(), response.getResponseCode());
		} catch(Exception e) {
			logger.error("Get Model Details Exception >>> {}", e.getMessage());
			e.printStackTrace();
		}
	}

	
	private Integer getClientId(String clientKey) throws Exception{
		logger.trace("Fetching ClientId from ClientConfigDB");
		return clientConfigBO.getClientId(clientKey);
	}

	private List<BrandCcdb> getBrandIds(Integer clientId) throws Exception{
		logger.debug("Fetching BrandIds from ClientConfigDB");			
		List<BrandCcdb> list = clientConfigBO.getBrandIds(clientId);
		
		for (BrandCcdb obj : list) {
			logger.debug("Brand >>> {}", obj.getBrandId());
		}
	 
		return list;
	}
	
	private List<ProductTypeCcdb> getProductTypes(Integer clientId) throws Exception{
		logger.debug("Fetching ProductTypes Status from ClientConfigDB");					
		List<ProductTypeCcdb> list = clientConfigBO.getProductType(clientId);
		for (ProductTypeCcdb obj : list) {
			logger.debug("Product Type >>> {}", obj.getProductTypeCd());
		}

		return list;
	}
	
	private List<ItemAttributeCcdb> getItemAttributes(Integer clientId) throws Exception{
		logger.debug("Fetching ItemAttributes from ClientConfigDB");
		List<ItemAttributeCcdb> list = clientConfigBO.getAttributes(clientId);
		for (ItemAttributeCcdb obj : list) {
			logger.debug("Item Attributes >>> {}", obj.getAttributeId());
		}

		return list;
	}
	
	private List<ItemRestrictionCcdb> getItemRestriction(Integer clientId) throws Exception{
		logger.debug("Fetching ItemRestrictions from ClientConfigDB");
		List<ItemRestrictionCcdb> list = clientConfigBO.getRestrictions(clientId);
		for (ItemRestrictionCcdb obj : list) {
			logger.debug("Item Attributes >>> {}", obj.getRestrictionId());
		}

		return list;
	}
	
	private BigDecimal getItemSellPriceLimit(Integer clientId) throws CcdbException{
		logger.debug("Fetching ItemSellingPriceLimit from ClientConfigDB");			
		BigDecimal bd = clientConfigBO.getItemSellingPriceLimit(clientId);
		logger.debug("ItemSellingPriceLimit >>> {}", bd);
		
		return bd;
	}
	
	private List<ProductGroupCcdb> getProductGroups(Integer clientId) throws Exception{
		logger.debug("Fetching ProductGroups from ClientConfigDB");
		List<ProductGroupCcdb> list = clientConfigBO.getProductGroup(clientId);
		for (ProductGroupCcdb obj : list) {
			logger.debug("Item Attributes >>> {}", obj.getProductGroupId());
		}

		return list;
	}

	private List<ItemAvailabilityStatusCcdb> getItemAvailabilityStatus(Integer clientId) throws Exception{
		logger.trace("Fetching AvailabilityStatus from ClientConfigDB");					
		
		logger.debug("Fetching AvailabilityStatus from ClientConfigDB");
		List<ItemAvailabilityStatusCcdb> list = clientConfigBO.getItemAvailabilityStatus(clientId);
		for (ItemAvailabilityStatusCcdb obj : list) {
			logger.debug("Item Attributes >>> {}", obj.getItemAvailabilityStatusCd());
		}

		return list;
	}
}
