package com.searshc.hspartcatalog.cache;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.searshc.hspartcatalog.pojo.Category;
import com.searshc.hspartcatalog.services.dao.PartsCatalogDao;
import com.searshc.hspartcatalog.util.ApplicationContextUtils;

@Component
public class CategoryCacheJob implements Job {

	private PartsCatalogDao partsCatalogDao;
	private CategoryCache categoryCache;

	private static final String PARTSCATALOG_DAO_BEAN = "partsCatalogDao";
	private static final String CATEGORY_CACHE_BEAN = "categoryCache";

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryCacheJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		LOGGER.info("CategoryCacheJob has started....");

		List<Category> categories = null;

		try {
			categories = getPartsCatalogDao().getCategories();
			getCache().setCategories(categories);
			LOGGER.info("CategoryCacheJob has ended and has loaded {} Categories into cache....", categories.size());
		} catch (Exception e) {
			LOGGER.error("Failed to select Categories >>> {}", e.getMessage(), e);
		}
	}

	private PartsCatalogDao getPartsCatalogDao() {
		if (partsCatalogDao == null) {
			ApplicationContext appCtx = ApplicationContextUtils.getApplicationContext();
			partsCatalogDao = (PartsCatalogDao) appCtx.getBean(PARTSCATALOG_DAO_BEAN);
		}

		return partsCatalogDao;
	}

	private CategoryCache getCache() {
		if (categoryCache == null) {
			ApplicationContext appCtx = ApplicationContextUtils.getApplicationContext();
			categoryCache = (CategoryCache) appCtx.getBean(CATEGORY_CACHE_BEAN);
		}

		return categoryCache;
	}
}
