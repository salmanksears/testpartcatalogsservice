package com.searshc.hspartcatalog.cache;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.searshc.hspartcatalog.domain.discount.ChannelPrice;
import com.searshc.hspartcatalog.services.dao.DiscountMapper;
import com.searshc.hspartcatalog.util.ApplicationContextUtils;

@Component
public class PriceOverrideCacheJob implements Job {

	private DiscountMapper discountMapper;
	private PriceOverrideCache priceOverrideCache;
	
	private static final String DISCOUNT_MAPPER_BEAN = "discountMapper";
	private static final String PRICE_OVERRIDE_CACHE_BEAN = "priceOverrideCache";

	private static final Logger LOGGER = LoggerFactory.getLogger(PriceOverrideCacheJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		LOGGER.info("PriceOverrideCacheJob has started....");

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();

		try {
			List<ChannelPrice> prices = getDiscountMapper().getAllChannelItemPrices();
			String itemId = null;
			for (ChannelPrice price : prices) {
				itemId = "0" + price.getItemNo();
				map.put(itemId, price.getPrice());
			}
			getPriceOverrideCache().setMap(map);
			LOGGER.info("PriceOverrideCacheJob has loaded {} Channel Price overrides into map....", map.size());
			
		} catch (Exception e) {
			LOGGER.error("Failed to select Channel Prices >>> {}", e.getMessage(), e);
		}
		
		try {
			BigDecimal price = getDiscountMapper().getMinimumPrice();
			LOGGER.info("PriceOverrideCacheJob has SUCCESSFULLY set Minimum Price {} to Cache....", price);
			getPriceOverrideCache().setMinimumPrice(price);
		} catch (Exception e) {
			LOGGER.error("Failed to select Minimum Price >>> {}", e.getMessage(), e);
		}
	}
	
	private DiscountMapper getDiscountMapper() {
		if (discountMapper == null) {
			ApplicationContext appCtx = ApplicationContextUtils
					.getApplicationContext();
			discountMapper = (DiscountMapper) appCtx.getBean(DISCOUNT_MAPPER_BEAN);
		}

		return discountMapper;
	}
	
	private PriceOverrideCache getPriceOverrideCache() {
		if (priceOverrideCache == null) {
			ApplicationContext appCtx = ApplicationContextUtils
					.getApplicationContext();
			priceOverrideCache = (PriceOverrideCache) appCtx.getBean(PRICE_OVERRIDE_CACHE_BEAN);
		}

		return priceOverrideCache;
	}
}
