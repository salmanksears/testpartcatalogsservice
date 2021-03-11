package com.searshc.hspartcatalog.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.searshc.hspartcatalog.services.domain.ItemRestriction;

public class RestrictionUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(RestrictionUtils.class);

	public static List<ItemRestriction> convert(List<String> restrictions) {

		List<ItemRestriction> itemRestrictions = new ArrayList<ItemRestriction>();

		if (CollectionUtils.isEmpty(restrictions))
			return itemRestrictions;

		int idx = 0;
		int pos = 0;
		
		for (String restriction : restrictions) {
			ItemRestriction itemRestriction = new ItemRestriction();
			try {
				idx = 0;
				pos = restriction.indexOf(',');
				itemRestriction.setRestrictionId(restriction.substring(idx, pos));
				idx = pos + 1;
				pos = restriction.indexOf('=');
				itemRestriction.setRestrictionTypeCd(restriction.substring(idx, pos));
				pos++;
				itemRestriction.setRestrictionDescription(restriction.substring(pos));
				itemRestrictions.add(itemRestriction);
			} catch (Exception e) {
				logger.error(
						"Failed to parse Restriction {} into an ItemRestriction",
						restriction);
			}
		}

		return itemRestrictions;
	}
}
