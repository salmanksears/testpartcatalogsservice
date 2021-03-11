package com.searshc.hspartcatalog.services.task;

import static junit.framework.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.searshc.hspartcatalog.pojo.ValidatorOutput;
import com.searshc.hspartcatalog.services.dao.PartsCatalogDao;
import com.searshc.hspartcatalog.services.domain.PartDetail;
import com.searshc.hspartcatalog.services.domain.PartDetailReturn;
import com.searshc.hspartcatalog.services.domain.Trans;
import com.searshc.hspartcatalog.services.vo.request.GetCoreAndEnvChargeAmountRequest;
import com.searshc.hspartcatalog.validator.GetCoreAndEnvChargeValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextTest-beans.xml")
public class GetCoreAndEnvChargeDaoTest {
	
	@Autowired
	PartsCatalogDao partsCatalogDao;
	@Autowired
	GetCoreAndEnvChargeValidator getCoreAndEnvChargeValidator;
	
	private static final Logger logger = LoggerFactory.getLogger(GetCoreAndEnvChargeDaoTest.class);
	
	@Test
	public final void test1() {
		try {
			GetCoreAndEnvChargeAmountRequest request  = new GetCoreAndEnvChargeAmountRequest();
			List<PartDetail> partDetails = new ArrayList<PartDetail>();
			
			Trans trans = new Trans();
			trans.setStateCode("IL");
			trans.setTransCode("B");
			
			PartDetail part1 = new PartDetail();
			part1.setDivNo("234");
			part1.setPartNo("100000000000000000000001");
			part1.setPlsNo("567");
			
			PartDetail part2 = new PartDetail();
			part2.setDivNo("2");
			part2.setPartNo("2");
			part2.setPlsNo("2");
			
			partDetails.add(part1); 
			//partDetails.add(part2);
			
			request.setPartDetails(partDetails);
			request.setTrans(trans);
			
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("*********************************************");
			mapper.writeValue(System.out, request);
			System.out.println("*********************************************");
			
			ValidatorOutput validator = getCoreAndEnvChargeValidator.validate(request);
			
			List<PartDetailReturn> result = partsCatalogDao.getCoreAndEnvChargeAmount(request);
			assertNotNull(result);

			} catch (Exception e) {
			logger.error("GetCoreAndEnvChargeDaoTest Exception >>> {}",
					e.getMessage());
			//e.printStackTrace();
		}
	}

}
