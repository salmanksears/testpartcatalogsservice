/**
 * 
 */
package com.searshc.hspartcatalog.services.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.searshc.hspartcatalog.domain.discount.ChannelPrice;
import com.searshc.hspartcatalog.domain.discount.ClientDiscount;

import junit.framework.Assert;

public class DiscountTest {
	
	@SuppressWarnings("unused")
	private ApplicationContext context;
	
	DiscountMapper mapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(DiscountTest.class);

	@Before
	public void setup() {

		System.setProperty("data.directory",
				"C:\\Users\\dgold1\\mars\\workspace\\sandbox\\PartsCatalogService\\src\\test\\resources");
		context = new ClassPathXmlApplicationContext("classpath:applicationContextTest-beans.xml");
		mapper = (DiscountMapper)context.getBean("discountMapper");
	}

	public void selectMinPrice() {

		LOGGER.debug("Test is starting...");

		try {
			BigDecimal bd = mapper.getMinimumPrice();
			LOGGER.debug("Minumum Price >>> {}", bd);

		} catch (Exception e) {
			LOGGER.error("Test had some issues >>> ", e);
		}
		LOGGER.debug("Test is ALL DONE......");
	}
	
	public void selectClientDiscounts() {

		LOGGER.debug("Test is starting...");

		try {
			List<ClientDiscount> discounts = mapper.getDiscounts("FP6");
			for (ClientDiscount discount : discounts) {
				LOGGER.debug("{}", discount);
			}
			
		} catch (Exception e) {
			LOGGER.error("Test had some issues >>> ", e);
		}
		LOGGER.debug("Test is ALL DONE......");
	}
		
	@Test
	public void selectChannelItemPrices() {

		LOGGER.debug("Test is starting...");

		try {
			List<ChannelPrice> prices = mapper.getAllChannelItemPrices();
			for (ChannelPrice price : prices) {
				LOGGER.debug("{}", price);
			}
			LOGGER.debug("{} Channel Item Prices selected...", prices.size());

			Map<String, ChannelPrice> map = new HashMap<String, ChannelPrice>();
			for (ChannelPrice price : prices) {
				map.put(price.getItemNo(), price);
			}
			LOGGER.debug("Map size >>> {}", map.size());
			
			Assert.assertEquals(prices.size(), map.size());
			
			
		} catch (Exception e) {
			LOGGER.error("Test had some issues >>> ", e);
		}
		LOGGER.debug("Test is ALL DONE......");
	}
}