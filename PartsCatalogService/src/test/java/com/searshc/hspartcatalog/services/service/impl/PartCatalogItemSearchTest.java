/**
 * 
 */
package com.searshc.hspartcatalog.services.service.impl;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.List;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.UriInfo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.searshc.hspartcatalog.cache.PriceOverrideCacheJob;
import com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants;
import com.searshc.hspartcatalog.services.bo.ClientConfigBO;
import com.searshc.hspartcatalog.services.domain.Client;
import com.searshc.hspartcatalog.services.domain.Item;
import com.searshc.hspartcatalog.services.service.PartsCatalogService;
import com.searshc.hspartcatalog.services.vo.response.ItemSearchResponse;
import com.searshc.hspartcatalog.util.RandomUtils;
import com.searshc.hspartcatalog.util.ThreadLocalContainer;

public class PartCatalogItemSearchTest {

	PartsCatalogService partsCatalogService;
	Scheduler scheduler = null;
	private String clientKey;
	private String itemId;
	private String partNo;
	private String formattedPartNo;
	private String productGroupId;
	private String responseFormat;
	private String startingRow;
	private String maxRows;
	private String sortBy;
	private UriInfo uriInfo;
	private ApplicationContext context;

	ClientConfigBO clientConfigBO;
	
	private static final String SCHEDULE_PATTERN = "0 %s %s * * ?";
	private static final Logger LOGGER = LoggerFactory.getLogger(PartCatalogItemSearchTest.class);

	@Before
	public void setup() {

		//ITEM
		//00461062196094
		//itemSellingPrice=5.03
		//itemCost=3.77
		//itemId=00461062203385
		//itemSellingPrice=58.29
		//itemCost=43.72
		
		//SUBITEM
		//itemId=00461062196094
		//itemSellingPrice=5.03
		//itemCost=3.77
		//subItemId=00461062196097
		//subItemCost=3.15
		//itemId=00461062203385
		//itemSellingPrice=58.29
		//itemCost=43.72
		//subItemId=00461062252155
		//subItemCost=104.57
		
		//BUSINESS CD
		//AAA	ListMinus2	50	
		//AAA	ListMinus	31	
		//AAA	CostPlus	11
		
		//ACZ	ListMinus	12
		//ACZ	CostPlus	12
		//ACZ	ListMinus2	43
		
		//AI2	CostPlus	22
		//AI2	ListMinus	10
	
		//dgold1 123 - AAA
			
		System.setProperty("data.directory",
				"C:\\Users\\dgold1\\mars\\workspace\\sandbox\\PartsCatalogService\\src\\test\\resources");
		         
		//clientKey = "1234567890";  //pd.com
		//clientKey = "6twCkByZyg5ltf1oDFHJIYRGvAAWuM9M"; //Discount City
		//clientKey = "DcXeSK4QEvA3tblQdqEhoJCzvXgW5CeW";  //ERP List Minus 10%
		//clientKey = "0Pv7C427zZ0EPBkaC6RiRrC9zHSYDHmQ"; // SB Cost Plus
		//clientKey = "123"; 									// SB Cost Plus
		clientKey = "sbSwdDOGAAAvprfQ69M9Ygc4up8nQdlp";   // Warrentech
		
		responseFormat = PartsCatalogServiceConstants.GEN_CONST.JSON;
		startingRow = "1";
		maxRows = "100";
		context = new ClassPathXmlApplicationContext("classpath:applicationContextTest-beans.xml");
		uriInfo = Mockito.mock(UriInfo.class);
		Mockito.when(uriInfo.getQueryParameters()).thenReturn(new MultivaluedHashMap<String, String>());
		
		JobKey jobKey = JobKey.jobKey("ChannelPriceJob", "CacheGroup");
		JobDetail job = newJob(PriceOverrideCacheJob.class).withIdentity(jobKey).build();
		
		String schedule = createSchedule();
	
		Trigger trigger = newTrigger().withIdentity("ChannelPriceTriggerDaily", "CacheGroup").startNow()
				.withSchedule(CronScheduleBuilder.cronSchedule(schedule)).build();

		try {
		// Setup the Job and Trigger with Scheduler & schedule jobs
		scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);
		
		//fire job NOW
		scheduler.triggerJob(jobKey);
		}
		catch(SchedulerException se) {
			LOGGER.error("OOPS >>> {}", se.getMessage());
		}
	}

	@Test
	public void partNoSearch() {

		LOGGER.debug("Test is starting...");

		//partNo = "530015810"; //NLA
		//partNo = "03659R"; //item cost
		partNo = "-STR6046A";

		try {
			clientConfigBO = (ClientConfigBO) context.getBean("clientConfigBO");
			Client client = clientConfigBO.getClientDetailsByKey(clientKey);
			ThreadLocalContainer.setClient(client);

			partsCatalogService = (PartsCatalogService) context.getBean("partsCatalogService");

			ItemSearchResponse response = (ItemSearchResponse) partsCatalogService.itemSearch(clientKey, itemId, partNo,
					productGroupId, responseFormat, formattedPartNo, startingRow, maxRows, sortBy, uriInfo);
			LOGGER.debug("ItemSearchResponse >>> {}", response);

			List<Item> items = response.getItems();
			if (items == null) {
				LOGGER.debug("ZERO ITEMS FOUND for partNo >>> {}", partNo);
			} else {
				LOGGER.debug("FOUND {} ITEMS FOUND for {}", items.size(), partNo);
				for (Item item : items) {
					LOGGER.debug("Part No:{} Item sell ${} Sub sell ${}", item.getPartNo(), item.getItemSellingPrice(), item.getSubItemSellingPrice());
				}
			}

			LOGGER.debug("Response >>> {} msg >>> {}", response, response.getMessages());

		} catch (Exception e) {
			LOGGER.error("Test had some issues", e);
		}
		LOGGER.debug("Test has completed...");
	}
	
	private String createSchedule() {

		// Daily try to randomize a little across servers...
		int min = RandomUtils.inRange(0, 60);
		int hour = RandomUtils.inRange(1, 4);
		String schedule = String.format(SCHEDULE_PATTERN, min, hour);
		LOGGER.info("ChannelPriceJob to load and cache prices will be scheduled using >>> {}", schedule);

		return schedule;

	}
}