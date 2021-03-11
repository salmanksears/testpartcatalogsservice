package com.searshc.hspartcatalog.mapper;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.searshc.hspartcatalog.services.domain.Item;


@Component
public class ItemDomainMapper {
	
	public Item noSubstituteItems(com.searshc.hspartcatalog.domain.solr.Item solrItem) {

		Item item = new Item();

		if (!StringUtils.isBlank(solrItem.getItemId()))
			item.setItemId(solrItem.getItemId());
		if (!StringUtils.isBlank(solrItem.getPartNo()))
			item.setPartNo(solrItem.getPartNo());
		if (!StringUtils.isBlank(solrItem.getProductGroupId()))
			item.setProductGroupId(solrItem.getProductGroupId());
		if (!StringUtils.isBlank(solrItem.getProductGroupName()))
			item.setProductGroupName(solrItem.getProductGroupName());
		if (!StringUtils.isBlank(solrItem.getItemDescription()))
			item.setItemDescription(solrItem.getItemDescription());
		if (!StringUtils.isBlank(solrItem.getItemImageURL()))
			item.setItemImageUrl(solrItem.getItemImageURL());
		if (!StringUtils.isBlank(solrItem.getItemAvailabilityStatus()))
			item.setItemAvailabilityStatus(solrItem.getItemAvailabilityStatus());
		if (!StringUtils.isBlank(solrItem.getItemSellingPrice()))
			item.setItemSellingPrice(solrItem.getItemSellingPrice());
		if (!StringUtils.isBlank(solrItem.getItemCost()))
			item.setItemCost(solrItem.getItemCost());
		if (!StringUtils.isBlank(solrItem.getItemAddDate()))
			item.setItemAddDate(solrItem.getItemAddDate());
		if (!StringUtils.isBlank(solrItem.getItemAccessoryIndicator()))
			item.setItemAccessoryIndicator(solrItem.getItemAccessoryIndicator());
		if (!StringUtils.isBlank(solrItem.getItemDisclosure()))
			item.setItemDisclosure(solrItem.getItemDisclosure());
		if (!StringUtils.isBlank(solrItem.getHazardousMaterialCode()))
			item.setHazardousMaterialCode(solrItem.getHazardousMaterialCode());
		if (!StringUtils.isBlank(solrItem.getProductIndicator()))
			item.setProductIndicator(solrItem.getProductIndicator());
		if (!StringUtils.isBlank(solrItem.getItemComment()))
			item.setItemComment(solrItem.getItemComment());
		if (!StringUtils.isBlank(solrItem.getItemCondition()))
			item.setItemCondition(solrItem.getItemCondition());
		if (!StringUtils.isBlank(solrItem.getItemSuggestedQty()))
			item.setItemSuggestedQty(solrItem.getItemSuggestedQty());
		if (!StringUtils.isBlank(solrItem.getSubbedFlag()))
			item.setSubbedFlag(solrItem.getSubbedFlag());
		if (!StringUtils.isBlank(solrItem.getUpcCode()))
			item.setUpcCode(solrItem.getUpcCode());
		if (!StringUtils.isBlank(solrItem.getItemSynonymName()))
			item.setItemSynonymName(solrItem.getItemSynonymName());
		if (!StringUtils.isBlank(solrItem.getEngineModelFlag()))
			item.setEngineModelFlag(solrItem.getEngineModelFlag());
		if (!StringUtils.isBlank(solrItem.getItemSchematicId()))
			item.setItemSchematicId(solrItem.getItemSchematicId());
		if (!StringUtils.isBlank(solrItem.getItemKeyId()))
			item.setItemKeyId(solrItem.getItemKeyId());

		return item;
	}
	
	
	public Item substituteItem(com.searshc.hspartcatalog.domain.solr.Item solrItem) {

		Item item = new Item();
		if (!StringUtils.isBlank(solrItem.getSubItemId()))
			item.setSubItemId(solrItem.getSubItemId());
		if (!StringUtils.isBlank(solrItem.getSubPartNo()))
			item.setSubPartNo(solrItem.getSubPartNo());
		if (!StringUtils.isBlank(solrItem.getSubItemDescription()))
			item.setSubItemDescription(solrItem.getSubItemDescription());
		if (!StringUtils.isBlank(solrItem.getSubProductGroupId()))
			item.setSubProductGroupId(solrItem.getSubProductGroupId());
		if (!StringUtils.isBlank(solrItem.getSubProductGroupName()))
			item.setSubProductGroupName(solrItem.getSubProductGroupName());
		if (!StringUtils.isBlank(solrItem.getSubItemImageURL()))
			item.setSubItemImageURL(solrItem.getSubItemImageURL());
		if (!StringUtils.isBlank(solrItem.getSubItemAvailabilityStatus()))
			item.setSubItemAvailabilityStatus(solrItem
					.getSubItemAvailabilityStatus());
		if (!StringUtils.isBlank(solrItem.getSubItemSellingPrice()))
			item.setSubItemSellingPrice(solrItem.getSubItemSellingPrice());
		if (!StringUtils.isBlank(solrItem.getSubItemCost()))
			item.setSubItemCost(solrItem.getSubItemCost());
		if (!StringUtils.isBlank(solrItem.getSubItemAccessoryIndicator()))
			item.setSubItemAccessoryIndicator(solrItem
					.getSubItemAccessoryIndicator());
		if (!StringUtils.isBlank(solrItem.getSubHazardousMaterialCode()))
			item.setSubItemDisclosure(solrItem.getSubItemDisclosure());
		if (!StringUtils.isBlank(solrItem.getSubHazardousMaterialCode()))
			item.setSubHazardousMaterialCode(solrItem.getSubHazardousMaterialCode());
		if (!StringUtils.isBlank(solrItem.getSubProductIndicator()))
			item.setSubProductIndicator(solrItem.getSubProductIndicator());
		if (!StringUtils.isBlank(solrItem.getSubItemComment()))
			item.setSubItemComment(solrItem.getSubItemComment());
		if (!StringUtils.isBlank(solrItem.getSubItemCondition()))
			item.setSubItemCondition(solrItem.getSubItemCondition());
		if (!StringUtils.isBlank(solrItem.getSubItemSuggestedQty()))
			item.setSubItemSuggestedQty(solrItem.getSubItemSuggestedQty());
		if (!StringUtils.isBlank(solrItem.getSubUPCcode()))
			item.setSubUPCcode(solrItem.getSubUPCcode());
		if (!StringUtils.isBlank(solrItem.getItemSchematicId()))
			item.setItemSchematicId(solrItem.getItemSchematicId());
		if (!StringUtils.isBlank(solrItem.getItemKeyId()))
			item.setItemKeyId(solrItem.getItemKeyId());

		return item;
	}
}
