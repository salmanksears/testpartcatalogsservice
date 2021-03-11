package com.searshc.hspartcatalog.services.bo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.searshc.hs.psc.logging.annotation.ExtCallAuditLogger;
import com.searshc.hspartcatalog.services.dao.PartsCatalogDao;

import com.searshc.hspartcatalog.services.domain.PartDetailReturn;
import com.searshc.hspartcatalog.services.vo.request.GetCoreAndEnvChargeAmountRequest;

@Component
public class CoreAndEnvChargeAmountBO {
	
	@Autowired
	private PartsCatalogDao partsCatalogDao;

	private static final Logger logger = LoggerFactory.getLogger(CoreAndEnvChargeAmountBO.class);
	
	@ExtCallAuditLogger(logInput = false, logOutput = false)
	public List<PartDetailReturn> getPartCharges(GetCoreAndEnvChargeAmountRequest request) throws Exception {

		List<PartDetailReturn> parts = new ArrayList<PartDetailReturn>();
		
		parts = partsCatalogDao.getCoreAndEnvChargeAmount(request);
		
		logger.debug("{} Part details found:)", parts.size());
		return parts;
	}

}
