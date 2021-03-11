package com.searshc.hspartcatalog.services.task;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.List;

import org.junit.After;
import org.junit.Before;
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
import com.searshc.hspartcatalog.services.domain.ItemAttributeCcdb;
import com.searshc.hspartcatalog.services.domain.ItemAvailabilityStatusCcdb;
import com.searshc.hspartcatalog.services.domain.ItemRestrictionCcdb;
import com.searshc.hspartcatalog.services.domain.ProductGroupCcdb;
import com.searshc.hspartcatalog.services.domain.ProductTypeCcdb;
import com.searshc.hspartcatalog.util.CommonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextTest-beans.xml")
public class ItemDescriptionTest {

	@Autowired
	SolrServiceInvokerBO solrServiceInvokerBO;

	@Autowired
	private ClientConfigBO clientConfigBO;

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

	//private static Charset ENCODING = StandardCharsets.UTF_8;

	private static final Logger logger = LoggerFactory
			.getLogger(ItemDescriptionTest.class);

	@Before
	public void setUp() throws Exception {

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
		MDC.put(Constants.CLIENT, "DGOLD1");
	}

	@After
	public void tearDown() throws Exception {
	}

	/*public final void itemSearch() {

		String INPUT_FILE = "c:\\temp\\solr\\partsds2-in.txt";
		String OUTPUT_FILE = "c:\\temp\\solr\\partsds2-out.txt";

		ItemSearchRequest request = new ItemSearchRequest();
		ItemSearchResponse response = null;

		request = new ItemSearchRequest();
		request.setClientKey(clientKey);
		request.setResponseFormat(XML);

		try {

			// load values from file...
			Set<String> descriptions = reader(INPUT_FILE);
			List<String> results = new ArrayList<String>();

			assertTrue("Descriptions List is EMPTY", descriptions.size() > 0);

			for (String description : descriptions) {
				// line is item description for now...
				if (StringUtils.isBlank(description))
					continue;

				

				response = (ItemSearchResponse) solrServiceInvokerBO
						.itemSearch(request, productGroupList,
								availabilityStatusList, restrictionList,
								itemSellPriceLimit);
				assertNotNull("ItemSearchResponse was NULL", response);
				String numFound = response.getNumFound();

				if (numFound.compareTo("0") != 0) {

					List<Item> items = response.getItems();
					logger.debug(
							"Number of Items found >>> {} for item description >>> {}",
							items.size(), description);
				}

				String result = description + "," + numFound;
				results.add(result);

				// for (Item item : items) {
				// logger.debug("Item ID: {} Desc: {}", item.getItemId(),
				// item.getItemDescription());
				// }

			}

			writer(results, OUTPUT_FILE);

			logger.debug("The Test has ended...");
		} catch (Exception e) {
			logger.error("Get Item Details Exception >>> {}", e.getMessage());
			e.printStackTrace();
		}
	}*/

	/*@Test
	public final void testModelSearch() {

		String INPUT_FILE = "c:\\temp\\solr\\models-in.txt";
		String OUTPUT_FILE = "c:\\temp\\solr\\models-out.txt";

		ModelSearchRequest request = new ModelSearchRequest();
		ModelSearchResponse response = null;

		request.setClientKey(clientKey);
		request.setResponseFormat(XML);

		try {

			// load values from file...
			Set<String> list = reader(INPUT_FILE);
			List<String> results = new ArrayList<String>();

			int totalCnt = 0;
			int zeroCnt = 0;
			int exactCnt = 0;
			int multiCnt = 0;
			int match = 0;

			for (String modelNo : list) {

				if (StringUtils.isBlank(modelNo))
					continue;

				totalCnt++;
				
				logger.debug("{} >>> Checking Model No {}...",
						totalCnt, modelNo);
				
				request.setModelNo(modelNo);

				response = (ModelSearchResponse) solrServiceInvokerBO
						.modelSearch(request, brandIdList, productTypeList);

				assertNotNull("ModelSearchResponse was NULL", response);
				String numFound = response.getNumFound();
				//logger.debug("Model No {} found {} models by modelNo...",
				//		modelNo, numFound);

				match = Integer.parseInt(numFound);

				// try substring of model no
				if (match == 0) {
					if (modelNo.length() > 3) {
						String str = modelNo.substring(3);
						//logger.debug(
						//		"Model No {} NOT found trying Substring {}...",
						//		modelNo, str);
						request.setModelNo(str);

						response = (ModelSearchResponse) solrServiceInvokerBO
								.modelSearch(request, brandIdList,
										productTypeList);
						assertNotNull("ModelSearchResponse was NULL", response);
						numFound = response.getNumFound();
						//logger.debug(
						//		"Shortened Model No {} found {} models by modelNo...",
						//		str, numFound);
						match = Integer.parseInt(numFound);
					}
				}

				if (match == 0) {
					logger.debug("Model No {} found ZERO models by modelNo...",
							modelNo);
					zeroCnt++;
				} else if (match == 1) {
					logger.debug(
							"Model No {} found EXACT MATCH model by modelNo...",
							modelNo);
					exactCnt++;
				} else {
					logger.debug(
							"Model No {} found MULTIPLE MATCHES model by modelNo...",
							modelNo);
					multiCnt++;
				}
				
				String result = modelNo + "," + numFound;
				results.add(result);
			}

			logger.debug("Total Models processed {}...", totalCnt);
			logger.debug("Zero Match count {}...", zeroCnt);
			logger.debug("Exact Match count {}...", exactCnt);
			logger.debug("Multi Match count {}...", multiCnt);

			writer(results, OUTPUT_FILE);

			logger.debug("The Test has ended...");
		} catch (Exception e) {
			logger.error("Get Item Details Exception >>> {}", e.getMessage());
			e.printStackTrace();
		}
	}*/

/*	private Set<String> reader(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		List<String> list = Files.readAllLines(path, ENCODING);

		logger.debug("{} ADDED {} values to Lines List....", fileName,
				list.size());

		Set<String> set = new HashSet<String>(list);

		logger.debug("New total after DUPS removed {}...", set.size());

		return set;
	}

	private long writer(List<String> lines, String fileName) throws IOException {
		Path path = Paths.get(fileName);
		path = Files.write(path, lines, ENCODING);

		long size = Files.size(path);

		logger.debug("Output file {} size {} after write....", fileName, size);

		return size;
	}
*/
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
