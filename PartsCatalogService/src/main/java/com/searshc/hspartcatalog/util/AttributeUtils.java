package com.searshc.hspartcatalog.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.searshc.hspartcatalog.services.domain.Item;
import com.searshc.hspartcatalog.services.domain.ItemAttribute;
import com.searshc.hspartcatalog.services.domain.ItemAttributeCcdb;

public class AttributeUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(AttributeUtils.class);

	public static Item filter(Item item, List<ItemAttributeCcdb> ccdbAttributes) {

		List<ItemAttribute> itemAttributes = item.getAttributes();

		if (CollectionUtils.isEmpty(ccdbAttributes)
				|| CollectionUtils.isEmpty(itemAttributes))
			return item;

		List<ItemAttribute> newItemAttributes = new ArrayList<ItemAttribute>();

		List<String> ccdbAttributeIds = new ArrayList<String>();
		for (ItemAttributeCcdb attributeCcdb : ccdbAttributes) {
			ccdbAttributeIds
					.add(String.valueOf(attributeCcdb.getAttributeId()));
		}

		for (ItemAttribute itemAttribute : itemAttributes) {
			String itemAttributeId = itemAttribute.getAttributeId();
			if (ccdbAttributeIds.contains(itemAttributeId))
				logger.debug(
						"Attribute ID:{} was FILTERED from Item:{} via CCDB Attribute ID:{}",
						itemAttribute, item.getItemId(), itemAttributeId);
			else
				newItemAttributes.add(itemAttribute);
		}

		item.setAttributes(newItemAttributes);

		return item;
	}

	public static List<ItemAttribute> convert(List<String> attributes) {

		List<ItemAttribute> itemAttributes = new ArrayList<ItemAttribute>();

		if (CollectionUtils.isEmpty(attributes))
			return itemAttributes;

		int idx = 0;
		int pos = 0;

		for (String attribute : attributes) {
			ItemAttribute itemAttribute = new ItemAttribute();
			try {
				idx = 0;
				pos = attribute.indexOf(',');
				itemAttribute.setAttributeId(attribute.substring(idx, pos));
				idx = pos + 1;
				pos = attribute.indexOf('=');
				itemAttribute.setAttributeName(attribute.substring(idx, pos));
				pos++;
				itemAttribute.setAttributeValue(attribute.substring(pos));
				itemAttributes.add(itemAttribute);
			} catch (Exception e) {
				logger.error(
						"Failed to parse Attribute {} into an ItemAttribute",
						attribute);
			}
		}

		return itemAttributes;
	}
}
