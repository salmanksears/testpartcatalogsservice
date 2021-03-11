package com.searshc.hspartcatalog.cache;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.searshc.hspartcatalog.pojo.Brand;
import com.searshc.hspartcatalog.services.dao.PartsCatalogDao;
import com.searshc.hspartcatalog.util.ApplicationContextUtils;

@Component
public class BrandCacheJob implements Job {

	private PartsCatalogDao partsCatalogDao;
	private BrandCache brandCache;

	private static final String PARTSCATALOG_DAO_BEAN = "partsCatalogDao";
	private static final String BRAND_CACHE_BEAN = "brandCache";

	private static final Logger LOGGER = LoggerFactory.getLogger(BrandCacheJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		LOGGER.info("BrandCacheJob has started....");

		List<Brand> brands = null;

		try {
			brands = getPartsCatalogDao().getBrands();
			getBrandCache().setBrands(brands);
			LOGGER.info("BrandCacheJob has ended and has loaded {} Brands into cache....", brands.size());
		} catch (Exception e) {
			LOGGER.error("Failed to select Channel Prices >>> {}", e.getMessage(), e);
		}
	}

	private PartsCatalogDao getPartsCatalogDao() {
		if (partsCatalogDao == null) {
			ApplicationContext appCtx = ApplicationContextUtils.getApplicationContext();
			partsCatalogDao = (PartsCatalogDao) appCtx.getBean(PARTSCATALOG_DAO_BEAN);
		}

		return partsCatalogDao;
	}

	private BrandCache getBrandCache() {
		if (brandCache == null) {
			ApplicationContext appCtx = ApplicationContextUtils.getApplicationContext();
			brandCache = (BrandCache) appCtx.getBean(BRAND_CACHE_BEAN);
		}

		return brandCache;
	}
}
