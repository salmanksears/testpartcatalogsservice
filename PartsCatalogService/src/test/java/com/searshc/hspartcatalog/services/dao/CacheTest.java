/**
 * 
 */
package com.searshc.hspartcatalog.services.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.searshc.hspartcatalog.pojo.Brand;
import com.searshc.hspartcatalog.pojo.Category;

public class CacheTest {
	
	@SuppressWarnings("unused")
	private ApplicationContext context;
	
	private PartsCatalogDao dao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheTest.class);

	@Before
	public void setup() {

		System.setProperty("data.directory",
				"C:\\Users\\dgold1\\mars\\workspace\\sandbox\\PartsCatalogService\\src\\test\\resources");
		context = new ClassPathXmlApplicationContext("classpath:applicationContextTest-beans.xml");
		dao = (PartsCatalogDao)context.getBean("partsCatalogDao");
	}

	@Test
	public void selectBrands() {

		LOGGER.debug("Test is starting...");

		try {
			List<Brand> list = dao.getBrands();
			LOGGER.debug("Brand size >>> {}", list.size());
			for (Brand o : list) {
				LOGGER.debug("{}", o);
			}
			
		} catch (Exception e) {
			LOGGER.error("Test had some issues >>> ", e);
		}
		LOGGER.debug("Test is ALL DONE......");
	}
	
	public void selectCategories() {

		LOGGER.debug("Test is starting...");

		try {
			List<Category> list = dao.getCategories();
			LOGGER.debug("Category size >>> {}", list.size());
			for (Category o : list) {
				LOGGER.debug("{}", o);
			}
			
		} catch (Exception e) {
			LOGGER.error("Test had some issues >>> ", e);
		}
		LOGGER.debug("Test is ALL DONE......");
	}
}