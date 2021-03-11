package com.searshc.hs.psc.catalog.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.searshc.hs.psc.catalog.dao.CatalogDao;
import com.searshc.hs.psc.catalog.dao.ConfigDao;
import com.searshc.hs.psc.catalog.util.Constants;
import com.searshc.hs.psc.catalog.vo.Column;
import com.searshc.hs.psc.catalog.vo.Config;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class DaoTest implements Constants {

	@Autowired
	CatalogDao dao;

	private static Logger LOGGER = LoggerFactory.getLogger(DaoTest.class);

	@BeforeClass
	public static void before() {
		System.setProperty("JOB_PROPERTIES", "job.properties");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.searshc.hs.psc.catalog");
	}

	@Test
	public void update() {

		try {
			String sql = "UPDATE prtxtpa_att_val SET att_val_hi = :att_val_hi, upd_by_id = 'BULKY', upd_by_ts = CURRENT YEAR TO SECOND  WHERE itm_id = :itm_id AND prd_gro_id = :prd_gro_id AND spp_id = :spp_id AND orb_itm_id = :orb_itm_id AND att_id = :att_id";

			Map<String, String> map = new HashMap<String, String>();
			map.put("att_val_hi", "1");
			map.put("itm_id", "2");
			map.put("prd_gro_id", "3");
			map.put("prd_gro_id", "4");
			map.put("spp_id", "5");
			map.put("orb_itm_id", "6");
			map.put("att_id", "7");

			int i = dao.update(sql, map);
			assertTrue(i == 1);

			LOGGER.debug("UPDATE SUCCESS...");
		} catch (Exception e) {
			LOGGER.error("Test failed...", e);
			fail(e.getMessage());
		}
	}
}
