package com.searshc.hspartcatalog.services.task;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.searshc.hspartcatalog.services.dao.PartsCatalogDao;
import com.searshc.hspartcatalog.services.domain.Accessory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextTest-beans.xml")
public class AccessoryDaoTest {

	@Autowired
	PartsCatalogDao partsCatalogDao;

	private static final Logger logger = LoggerFactory
			.getLogger(AccessoryDaoTest.class);

	@Before
	public void setUp() throws Exception {
		System.setProperty("data.directory", "PartsCatalogService/src/test/resources/");
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test1() {
		String prdTypId = "0151200";	  	                        
		String bndId = "1428";
		String mdlNo = "AEQB6000ES0";
				
		try {
			
			List<Accessory> accessories = partsCatalogDao.getAccessoryByModel(prdTypId, bndId,  mdlNo, "10");
			assertNotNull(accessories);

			assertTrue(accessories.size() > 0);
			logger.debug("ModelPart result size >>> {}", accessories.size());
			int i = 0;
			
			for (Accessory accessory : accessories) {
				i++;
				logger.debug("{} >>> {}", i, accessory);
			}

		} catch (Exception e) {
			logger.error("Exception >>> {}",
					e.getMessage());
			//e.printStackTrace();
		}
	}
}
