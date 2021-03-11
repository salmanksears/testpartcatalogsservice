package com.searshc.hspartcatalog.cache;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.searshc.hspartcatalog.pojo.Category;

@Component
public class CategoryCache {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryCache.class);

	private static List<Category> CATEGORIES = new ArrayList<Category>();

	public List<Category> getCategories(String name) {

		List<Category> list = new ArrayList<Category>();

		try {
			for (Category category : CATEGORIES) {
				if(StringUtils.startsWithIgnoreCase(category.getName(), name))
					list.add(category);
			}
		} catch (Exception e) {
			LOGGER.error("Could NOT lookup Category for name:{} >>> {}", name, e.getMessage());
		}

		return list;
	}

	public synchronized void setCategories(List<Category> categories) {
		if (categories != null && categories.size() > 0) {
			CATEGORIES = categories;
			LOGGER.debug("Category cache has been SUCCESSFULLY updated with {} categories...", CATEGORIES.size());
		} else {
			LOGGER.error("Category cache FAILED to update the list it was EMPTY......");
		}
	}
}