package com.searshc.hspartcatalog.services.task;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.searshc.hspartcatalog.pojo.ModelPart;
import com.searshc.hspartcatalog.services.dao.PartsCatalogDao;
import com.searshc.hspartcatalog.services.domain.Schematic;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextTest-beans.xml")
public class ModelPartsDaoTest {

	@Autowired
	PartsCatalogDao partsCatalogDao;

	List<String> schematicIds = null;

	private static final Logger logger = LoggerFactory
			.getLogger(ModelPartsDaoTest.class);

	@Before
	public void setUp() throws Exception {
		
		List<Schematic> schematics = new ArrayList<Schematic>();
		
		Schematic schematic = new Schematic();
		schematic.setSchematicId("5003719200001");
		schematics.add(schematic);

//		schematic = new Schematic();
//		schematic.setSchematicId("5003719200002");
//		schematics.add(schematic);
//
//		schematic = new Schematic();
//		schematic.setSchematicId("5003719200003");
//		schematics.add(schematic);
//
//		schematic = new Schematic();
//		schematic.setSchematicId("5003719200004");
//		schematics.add(schematic);
//
//		schematic = new Schematic();
//		schematic.setSchematicId("5003719200005");
//		schematics.add(schematic);
//
//		schematic = new Schematic();
//		schematic.setSchematicId("5003719200006");
//		schematics.add(schematic);
//
//		schematic = new Schematic();
//		schematic.setSchematicId("5003719200007");
//		schematics.add(schematic);
		
		schematicIds = getSchematicIds(schematics);
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test1() {
		try {
			Map<String, ModelPart> result = partsCatalogDao.getModelParts(schematicIds);
			assertNotNull(result);

			assertTrue(result.size() > 0);
			logger.debug("ModelPart result size >>> {}", result.size());
			int i = 0;
			
			for (Map.Entry<String, ModelPart> entry : result.entrySet()) {
				i++;
				logger.debug("{} >>> Key: {} Value: {} ", i, entry.getKey(), entry.getValue());
			}

		} catch (Exception e) {
			logger.error("ModelPartsMapperTest Exception >>> {}",
					e.getMessage());
			//e.printStackTrace();
		}
	}
	
	private List<String>getSchematicIds(List<Schematic> schematics) {	
		List<String> list = new ArrayList<String>();
		
		for (Schematic schematic : schematics) {
			list.add(schematic.getSchematicId());
		}
		
		StringUtils.join(list, "|");
		
		logger.debug("getSchematicIds >>> created a Schematic list of {}",  StringUtils.join(list, "|"));
		
		return list;
				
}
}
