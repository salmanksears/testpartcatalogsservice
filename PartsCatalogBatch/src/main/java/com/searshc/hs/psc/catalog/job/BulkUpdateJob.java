package com.searshc.hs.psc.catalog.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.searshc.hs.psc.catalog.bo.BulkUpdateBo;

@Component
public class BulkUpdateJob implements Job {

	@Autowired
	private BulkUpdateBo bulkUpdateBo;

	private final static Logger LOGGER = LoggerFactory
			.getLogger(BulkUpdateBo.class);

	public int start(String[] args) throws Exception {

		int rc = 0;
	
		try {
			bulkUpdateBo.process();
		} catch (Exception e) {
			LOGGER.error("Exception:{} was caught...", e.getMessage(), e);
			rc = -1;
		}

		return rc;
	}
}
