package com.searshc.hspartcatalog.cache;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PriceOverrideCache {

	private static final Logger LOGGER = LoggerFactory.getLogger(PriceOverrideCache.class);

	private static BigDecimal MINIMUM_PRICE;
	private static Map<String, BigDecimal> CHANNEL_PRICE_MAP = new HashMap<String, BigDecimal>();

	public BigDecimal getPrice(String itemNo) {

		BigDecimal price = null;

		try {
			price = CHANNEL_PRICE_MAP.get(itemNo);
			if (price != null)
				LOGGER.debug("Channel Override price:{} found in cache and will be used for item no:{}", price, itemNo);
		} catch (Exception e) {
			LOGGER.error("Cound NOT lookup Channel Override price for itemNo:{} >>> {}", itemNo, e.getMessage());
			price = null;
		}

		return price;
	}

	public synchronized void setMap(Map<String, BigDecimal> map) {
		if (map != null && map.size() > 0) {
			CHANNEL_PRICE_MAP = map;
			LOGGER.debug("Channel Price cache has been SUCCESSFULLY updated with {} items...", CHANNEL_PRICE_MAP.size());
		} else {
			LOGGER.error("Channel Price cache FAILED to update the map was EMPTY......");
		}
	}
	
	public BigDecimal getMinimumPrice() {
		return MINIMUM_PRICE;
	}
	
	public synchronized void setMinimumPrice(BigDecimal price) {
		MINIMUM_PRICE = price;
		LOGGER.debug("Minimum price SUCCESSFULLY updated with {} price...", MINIMUM_PRICE);
	}
}
