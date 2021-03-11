package com.searshc.hspartcatalog.cache;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.searshc.hspartcatalog.pojo.Brand;

@Component
public class BrandCache {

	private static final Logger LOGGER = LoggerFactory.getLogger(BrandCache.class);

	private static List<Brand> BRANDS = new ArrayList<Brand>();

	public List<Brand> getBrands(String name) {

		List<Brand> brands = new ArrayList<Brand>();

		try {
			for (Brand brand : BRANDS) {
				if(StringUtils.startsWithIgnoreCase(brand.getName(), name)) 
					brands.add(brand);
			}	
		} catch (Exception e) {
			LOGGER.error("Cound NOT lookup Brand for name:{} >>> {}", name, e.getMessage());
		}

		return brands;
	}

	public synchronized void setBrands(List<Brand> brands) {
		if (brands != null && brands.size() > 0) {
			BRANDS = brands;
			LOGGER.debug("Brand cache has been SUCCESSFULLY updated with {} brands...", BRANDS.size());
		} else {
			LOGGER.error("Brand cache FAILED to update the list it was EMPTY......");
		}
	}
}