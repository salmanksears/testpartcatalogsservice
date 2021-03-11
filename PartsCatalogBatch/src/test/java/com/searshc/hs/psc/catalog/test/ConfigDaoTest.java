package com.searshc.hs.psc.catalog.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.searshc.hs.psc.catalog.dao.ConfigDao;
import com.searshc.hs.psc.catalog.util.Constants;
import com.searshc.hs.psc.catalog.vo.Column;
import com.searshc.hs.psc.catalog.vo.Config;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class ConfigDaoTest implements Constants {

	@Autowired
	ConfigDao configDao;

	private static Logger LOGGER = LoggerFactory.getLogger(ConfigDaoTest.class);
	
	@BeforeClass
	public static void before() {
		System.setProperty("JOB_PROPERTIES", "job.properties");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.searshc.hs.psc.catalog");
	}
	
	public void selectByFile() {
		try {
			String name = "attributes.txt";

			Config config = configDao.selectByFile(name);
			assertNotNull(config);
			LOGGER.debug("Config:{} SELECTED for name:{}", config, name);
		} catch (Exception e) {
			LOGGER.error("Test failed...", e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void insertMasterAccessoryFlag() {
		
		try {
			String name = "masteraccfl";
			delete(name);
			
			Config config = new Config(name);
			config.setFile("bulk-masteraccfl.dat");
			config.setTable("prtxtpm_prt_mast");
			config.setActions(DELETE_ACTION + UPDATE_ACTION);
			List<Column> columns = new ArrayList<Column>();
			columns.add(new Column("itm_id", 1, INDEX_TRUE, CALC_FALSE, DUP_FALSE));
			columns.add(new Column("prd_gro_id", 2, INDEX_TRUE, CALC_TRUE, DUP_FALSE));
			columns.add(new Column("spp_id", 3, INDEX_TRUE, CALC_TRUE, DUP_FALSE));
			columns.add(new Column("orb_itm_id", 4, INDEX_TRUE, CALC_TRUE, DUP_FALSE));
			columns.add(new Column("acy_prt_fl", 5, INDEX_FALSE, CALC_FALSE, DUP_FALSE));  
			config.setColumns(columns);
			
			config.setCreatedBy("tester");
			
			int i = configDao.insert(config);
			assertTrue(i == 1);
			
			LOGGER.debug("Config:{} INSERTED SUCCESSFULLY...", config);
		} catch (Exception e) {
			LOGGER.error("Test failed...", e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void insertMasterDesc() {
		
		try {
			String name = "masterdesc";
			delete(name);
			
			Config config = new Config(name);
			config.setFile("bulk-masterdesc.dat");
			config.setTable("prtxtpm_prt_mast");
			config.setActions(DELETE_ACTION + UPDATE_ACTION);
			List<Column> columns = new ArrayList<Column>();
			columns.add(new Column("itm_id", 1, INDEX_TRUE, CALC_FALSE, DUP_FALSE));
			columns.add(new Column("prd_gro_id", 2, INDEX_TRUE, CALC_TRUE, DUP_FALSE));
			columns.add(new Column("spp_id", 3, INDEX_TRUE, CALC_TRUE, DUP_FALSE));
			columns.add(new Column("orb_itm_id", 4, INDEX_TRUE, CALC_TRUE, DUP_FALSE));
			columns.add(new Column("mst_prt_ds", 5, INDEX_FALSE, CALC_FALSE, DUP_FALSE));  
			columns.add(new Column("dqm_title", 6, INDEX_FALSE, CALC_FALSE, DUP_FALSE));  
			
			config.setColumns(columns);
			
			config.setCreatedBy("tester");
			
			int i = configDao.insert(config);
			assertTrue(i == 1);
			
			LOGGER.debug("Config:{} INSERTED SUCCESSFULLY...", config);
		} catch (Exception e) {
			LOGGER.error("Test failed...", e);
			fail(e.getMessage());
		}
	}

	
	public void insertAttributes() {
		
		try {
			String name = "attributes";
			delete(name);
			
			Config config = new Config(name);
			config.setFile("bulk-attributes.dat");
			config.setTable("prtxtpa_att_val");
			config.setActions(DELETE_ACTION + UPDATE_ACTION + INSERT_ACTION);
			List<Column> columns = new ArrayList<Column>();
			columns.add(new Column("itm_id", 1, INDEX_TRUE, CALC_FALSE, DUP_FALSE));
			columns.add(new Column("prd_gro_id", 2, INDEX_TRUE, CALC_TRUE, DUP_FALSE));
			columns.add(new Column("spp_id", 3, INDEX_TRUE, CALC_TRUE, DUP_FALSE));
			columns.add(new Column("orb_itm_id", 4, INDEX_TRUE, CALC_TRUE, DUP_FALSE));
			columns.add(new Column("att_id", 5, INDEX_TRUE, CALC_FALSE, DUP_FALSE));  
			columns.add(new Column("att_val_hi", 6, INDEX_FALSE, CALC_FALSE, DUP_FALSE));
			config.setColumns(columns);
			
			config.setCreatedBy("tester");
			
			int i = configDao.insert(config);
			assertTrue(i == 1);
			
			LOGGER.debug("Config:{} INSERTED SUCCESSFULLY...", config);
		} catch (Exception e) {
			LOGGER.error("Test failed...", e);
			fail(e.getMessage());
		}
	}
	
	private void delete(String name) {
		try {
			int i = configDao.delete(name);
			assertTrue(i == 1);
			LOGGER.debug("Config:{} DELETED SUCCESSFULLY...", name);
		} catch (Exception e) {
			LOGGER.error("Test failed...", e);
			fail(e.getMessage());
		}
	}
	
	public void insertRestriction() {
		try {
			
			delete("restrictions");
			
			Config config = new Config("restrictions");
			config.setFile("bulk-restrictions.dat");
			config.setTable("prtxtie_prt_rsr");
			config.setActions(DELETE_ACTION + UPDATE_ACTION + INSERT_ACTION);
			
			List<Column> columns = new ArrayList<Column>();
			columns.add(new Column("itm_id", 1, INDEX_TRUE, CALC_FALSE, DUP_FALSE));
			columns.add(new Column("prd_gro_id", 2, INDEX_TRUE, CALC_TRUE, DUP_FALSE));
			columns.add(new Column("spp_id", 3, INDEX_TRUE, CALC_TRUE, DUP_FALSE));
			columns.add(new Column("orb_itm_id", 4, INDEX_TRUE, CALC_TRUE, DUP_FALSE));
			columns.add(new Column("rsr_id", 5, INDEX_TRUE, CALC_FALSE, DUP_FALSE));
			columns.add(new Column("icl_xcl_cd", 6, INDEX_FALSE, CALC_FALSE, DUP_FALSE));  
			columns.add(new Column("rsr_id", 7, INDEX_FALSE, CALC_FALSE, DUP_TRUE));
			config.setColumns(columns);
		
			config.setCreatedBy("tester");
			
			int i = configDao.insert(config);
			assertTrue(i == 1);
			
			LOGGER.debug("Config:{} INSERTED SUCCESSFULLY...", config);
		} catch (Exception e) {
			LOGGER.error("Test failed...", e);
			fail(e.getMessage());
		}
	}
	
	public void insertModelAttr() {

		try {
			
			String name = "modelattr";
			delete(name);
			
			Config config = new Config(name);
			config.setFile("bulk-modelattr.dat");
			config.setTable("prtxtma_mdl_att");
			config.setActions(DELETE_ACTION + UPDATE_ACTION + INSERT_ACTION);
			
			List<Column> columns = new ArrayList<Column>();
			
			columns.add(new Column("prd_typ_id", 1, INDEX_TRUE, CALC_FALSE, DUP_FALSE));
			columns.add(new Column("bnd_id", 2, INDEX_TRUE, CALC_FALSE, DUP_FALSE));
			columns.add(new Column("mdl_nm", 3, INDEX_TRUE, CALC_FALSE, DUP_FALSE));
			columns.add(new Column("att_id", 4,  INDEX_TRUE, CALC_FALSE, DUP_FALSE));
			columns.add(new Column("att_val_hi", 5, INDEX_FALSE, CALC_FALSE, DUP_FALSE));
			
			config.setColumns(columns);
			config.setCreatedBy("tester");
			
			int i = configDao.insert(config);
			assertTrue(i == 1);
			
			LOGGER.debug("Config:{} INSERTED SUCCESSFULLY...", config);
		} catch (Exception e) {
			LOGGER.error("Test failed...", e);
			fail(e.getMessage());
		}
	}
	
	public void insertMaster() {

		try {
			
			String name = "modelattr";
			delete(name);
			
			Config config = new Config(name);
			config.setFile("bulk-modelattr.dat");
			config.setTable("prtxtma_mdl_att");
			config.setActions(DELETE_ACTION + UPDATE_ACTION + INSERT_ACTION);
			
			List<Column> columns = new ArrayList<Column>();
			
			columns.add(new Column("prd_typ_id", 1, INDEX_TRUE, CALC_FALSE, DUP_FALSE));
			columns.add(new Column("bnd_id", 2, INDEX_TRUE, CALC_FALSE, DUP_FALSE));
			columns.add(new Column("mdl_nm", 3, INDEX_TRUE, CALC_FALSE, DUP_FALSE));
			columns.add(new Column("att_id", 4,  INDEX_TRUE, CALC_FALSE, DUP_FALSE));
			columns.add(new Column("att_val_hi", 5, INDEX_FALSE, CALC_FALSE, DUP_FALSE));
			
			config.setColumns(columns);
			config.setCreatedBy("tester");
			
			int i = configDao.insert(config);
			assertTrue(i == 1);
			
			LOGGER.debug("Config:{} INSERTED SUCCESSFULLY...", config);
		} catch (Exception e) {
			LOGGER.error("Test failed...", e);
			fail(e.getMessage());
		}
	}
}
