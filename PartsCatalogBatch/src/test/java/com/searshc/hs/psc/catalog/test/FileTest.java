package com.searshc.hs.psc.catalog.test;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.searshc.hs.psc.catalog.bo.BulkUpdateBo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring\\applicationContext.xml" })
public class FileTest {

	@Autowired
	BulkUpdateBo bulkUpdateBo;

	private static Logger LOGGER = LoggerFactory.getLogger(FileTest.class);

	@BeforeClass
	public static void before() {
		System.setProperty("JOB_PROPERTIES", "job.properties");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.searshc.hs.psc.catalog");
	}
	
	@Test
	public void fileMoves() {
		try {
			bulkUpdateBo.process();
			LOGGER.debug("TEST ENDED SUCCESSFULLY...");
		} catch (Exception e) {
			LOGGER.error("Test failed...", e);
			fail(e.getMessage());
		}
	}
}
