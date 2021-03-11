package com.searshc.hspartcatalog.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;

import com.searshc.hspartcatalog.pojo.Doc;
import com.searshc.hspartcatalog.pojo.FacetCounts;
import com.searshc.hspartcatalog.pojo.FacetFields;
import com.searshc.hspartcatalog.services.domain.AValue;
import com.searshc.hspartcatalog.services.domain.FacetValue;
import com.searshc.hspartcatalog.services.domain.Item;
import com.searshc.hspartcatalog.services.domain.ItemAttribute;
import com.searshc.hspartcatalog.services.domain.ItemRestriction;
import com.searshc.hspartcatalog.services.domain.ItemWithoutSubstitutes;
import com.searshc.hspartcatalog.services.domain.Model;
import com.searshc.hspartcatalog.services.domain.ModelFacetCounts;
import com.searshc.hspartcatalog.services.domain.Schematic;
import com.searshc.hspartcatalog.util.AttributeUtils;
import com.searshc.hspartcatalog.util.RestrictionUtils;

/**
 * Title : EntityDocMapper Description : class to convert Doc object to domain
 * objects
 * 
 * @author : Abhishek Jain
 */

public class EntityDocMapper {

	public Model docToModel(Doc doc) {

		Model model = new Model();

		if (!StringUtils.isBlank(doc.getModelId()))
			model.setModelId(doc.getModelId());
		if (!StringUtils.isBlank(doc.getModelNo()))
			model.setModelNo(doc.getModelNo());
		if (!StringUtils.isBlank(doc.getModelDescription()))
			model.setModelDescription(doc.getModelDescription());
		if (!StringUtils.isBlank(doc.getBrandId()))
			model.setBrandId(doc.getBrandId());
		if (!StringUtils.isBlank(doc.getBrand()))
			model.setBrand(doc.getBrand());
		if (!StringUtils.isBlank(doc.getParentProductTypeId()))
			model.setParentProductTypeId(doc.getParentProductTypeId());
		if (!StringUtils.isBlank(doc.getParentProductTypeName()))
			model.setParentProductTypeName(doc.getParentProductTypeName());
		if (!StringUtils.isBlank(doc.getProductTypeId()))
			model.setProductTypeId(doc.getProductTypeId());
		if (!StringUtils.isBlank(doc.getProductTypeName()))
			model.setProductTypeName(doc.getProductTypeName());
		if (!StringUtils.isBlank(doc.getSubProductTypeId()))
			model.setSubProductTypeId(doc.getSubProductTypeId());
		if (!StringUtils.isBlank(doc.getSubProductTypeName()))
			model.setSubProductTypeName(doc.getSubProductTypeName());
		if (!StringUtils.isBlank(doc.getModelDiagramCount()))
			model.setModelDiagramCount(doc.getModelDiagramCount());
		if (!StringUtils.isBlank(doc.getOwnersManualURL()))
			model.setOwnerManualURL(doc.getOwnersManualURL());
		if (!StringUtils.isBlank(doc.getInstallationManualURL()))
			model.setInstallationManualURL(doc.getInstallationManualURL());
		if (!CollectionUtils.isEmpty(doc.getModelAttributes()))
			model.setAttributes(AttributeUtils.convert(doc.getModelAttributes()));
		if (!StringUtils.isBlank(doc.getModelImageURL()))
			model.setImage(doc.getModelImageURL());
		
		return model;
	}
	
	public Model docToModelWithItemCount(Doc doc) {

		Model model = docToModel(doc);
		if (!StringUtils.isBlank(doc.getItemCount()))
			model.setItemCount(doc.getItemCount());
		return model;
	}
	
	
	
	public ModelFacetCounts facetToModelFacetCount(FacetCounts facetCount) {

	     if(facetCount == null){
	    	 return null;
	     }
		ModelFacetCounts modelFacet = new ModelFacetCounts();
		
		List<FacetValue> brandFacetObjects = new ArrayList<FacetValue>();
		List<FacetValue> brandIdFacetObjects = new ArrayList<FacetValue>();
		List<FacetValue> productTypeFacetObjects = new ArrayList<FacetValue>();
		
		
		FacetFields facetFields= facetCount.getFacetFields();
		String[] brands = facetFields.getBrand()==null?new String[0]:facetFields.getBrand();
		String[] brandids =facetFields.getBrandId()==null?new String[0]:facetFields.getBrandId();
		String[] productTypeId =facetFields.getProductTypeId()==null?new String[0]:facetFields.getProductTypeId();
		
		for (int i = 0; i < brands.length; i++) {
			FacetValue facetVal = new FacetValue();
			facetVal.setValue(brands[i]);
			facetVal.setCount(brands[++i]);
			brandFacetObjects.add(facetVal );
		}
		
		for (int i = 0; i < brandids.length; i++) {
			FacetValue facetVal = new FacetValue();
			facetVal.setValue(brandids[i]);
			facetVal.setCount(brandids[++i]);
			brandIdFacetObjects.add(facetVal );
		}
		
		for (int i = 0; i < productTypeId.length; i++) {
			FacetValue facetVal = new FacetValue();
			facetVal.setValue(productTypeId[i]);
			facetVal.setCount(productTypeId[++i]);
			productTypeFacetObjects.add(facetVal );
		}
		
		modelFacet.setBrand(brandFacetObjects);
		modelFacet.setBrandId(brandIdFacetObjects);
		modelFacet.setProductTypeId(productTypeFacetObjects);
		
		return modelFacet;
	}

	

	public Schematic docToSchematic(Doc doc) {

		Schematic schematic = new Schematic();

		if (!StringUtils.isBlank(doc.getSchematicId()))
			schematic.setSchematicId(doc.getSchematicId());
		if (!StringUtils.isBlank(doc.getSchematicDescription()))
			schematic.setSchematicName(doc.getSchematicDescription());
		if (!StringUtils.isBlank(doc.getSchematicURL()))
			schematic.setSchematicURL(doc.getSchematicURL());

		return schematic;
	}

	public ItemWithoutSubstitutes docToItemWithoutSubstitutes(Doc doc) {

		ItemWithoutSubstitutes itemWithoutSubstitutes = new ItemWithoutSubstitutes();
		if (!StringUtils.isBlank(doc.getItemId()))
			itemWithoutSubstitutes.setItemId(doc.getItemId());
		if (!StringUtils.isBlank(doc.getPartNo()))
			itemWithoutSubstitutes.setPartNo(doc.getPartNo());
		if (!StringUtils.isBlank(doc.getProductGroupId()))
			itemWithoutSubstitutes.setProductGroupId(doc.getProductGroupId());
		if (!StringUtils.isBlank(doc.getProductGroupName()))
			itemWithoutSubstitutes.setProductGroupName(doc
					.getProductGroupName());
		if (!StringUtils.isBlank(doc.getItemDescription()))
			itemWithoutSubstitutes.setItemDescription(doc.getItemDescription());
		if (!StringUtils.isBlank(doc.getItemImageURL()))
			itemWithoutSubstitutes.setItemImageUrl(doc.getItemImageURL());
		if (!StringUtils.isBlank(doc.getItemAvailabilityStatus()))
			itemWithoutSubstitutes.setItemAvailabilityStatus(doc
					.getItemAvailabilityStatus());
		if (!StringUtils.isBlank(doc.getItemSellingPrice()))
			itemWithoutSubstitutes.setItemSellingPrice(doc
					.getItemSellingPrice());
		if (!StringUtils.isBlank(doc.getItemCost()))
			itemWithoutSubstitutes.setItemCost(doc.getItemCost());
		if (!StringUtils.isBlank(doc.getItemAddDate()))
			itemWithoutSubstitutes.setItemAddDate(doc.getItemAddDate());
		if (!StringUtils.isBlank(doc.getItemAccessoryIndicator()))
			itemWithoutSubstitutes.setItemAccessoryIndicator(doc
					.getItemAccessoryIndicator());
		if (!StringUtils.isBlank(doc.getItemDisclosure()))
			itemWithoutSubstitutes.setItemDisclosure(doc.getItemDisclosure());
		if (!StringUtils.isBlank(doc.getHazardousMaterialCode()))
			itemWithoutSubstitutes.setHazardousMaterialCode(doc
					.getHazardousMaterialCode());
		if (!StringUtils.isBlank(doc.getProductIndicator()))
			itemWithoutSubstitutes.setProductIndicator(doc
					.getProductIndicator());
		if (!StringUtils.isBlank(doc.getItemComment()))
			itemWithoutSubstitutes.setItemComment(doc.getItemComment());
		if (!StringUtils.isBlank(doc.getItemCondition()))
			itemWithoutSubstitutes.setItemCondition(doc.getItemCondition());
		if (!StringUtils.isBlank(doc.getItemSuggestedQty()))
			itemWithoutSubstitutes.setItemSuggestedQty(doc
					.getItemSuggestedQty());
		if (!StringUtils.isBlank(doc.getSubbedFlag()))
			itemWithoutSubstitutes.setSubbedFlag(doc.getSubbedFlag());
		if (!StringUtils.isBlank(doc.getUpcCode()))
			itemWithoutSubstitutes.setUpcCode(doc.getUpcCode());
		if (!StringUtils.isBlank(doc.getItemSynonymName()))
			itemWithoutSubstitutes.setItemSynonymName(doc.getItemSynonymName());
		if (!StringUtils.isBlank(doc.getEngineModelFlag()))
			itemWithoutSubstitutes.setEngineModelFlag(doc.getEngineModelFlag());
		if (!StringUtils.isBlank(doc.getItemModelId()))
			itemWithoutSubstitutes.setItemModelId(doc.getItemModelId());
		if (!StringUtils.isBlank(doc.getItemSchematicId()))
			itemWithoutSubstitutes.setItemSchematicId(doc.getItemSchematicId());
		if (!StringUtils.isBlank(doc.getItemKeyId()))
			itemWithoutSubstitutes.setItemKeyId(doc.getItemKeyId());

		return itemWithoutSubstitutes;
	}

	public ItemAttribute docToItemAttribute(Doc doc) {

		ItemAttribute itemAttribute = new ItemAttribute();

		if (!StringUtils.isBlank(doc.getAttributeId()))
			itemAttribute.setAttributeId(doc.getAttributeId());
		if (!StringUtils.isBlank(doc.getAttributeName()))
			itemAttribute.setAttributeName(doc.getAttributeName());
		if (!StringUtils.isBlank(doc.getAttributeValue()))
			itemAttribute.setAttributeValue(doc.getAttributeValue());

		return itemAttribute;
	}

	public ItemRestriction docToItemRestriction(Doc doc) {

		ItemRestriction itemRestriction = new ItemRestriction();

		if (!StringUtils.isBlank(doc.getRestrictionDescription()))
			itemRestriction.setRestrictionDescription(doc
					.getRestrictionDescription());
		if (!StringUtils.isBlank(doc.getRestrictionId()))
			itemRestriction.setRestrictionId(doc.getRestrictionId());
		if (!StringUtils.isBlank(doc.getRestrictionTypeCd()))
			itemRestriction.setRestrictionTypeCd(doc.getRestrictionTypeCd());

		return itemRestriction;
	}

	public Item docToItemSubstitute(Doc doc) {

		Item item = new Item();
		// change for adding original item id with substitute item
		if (!StringUtils.isBlank(doc.getItemId()))
			item.setItemId(doc.getItemId());
		if (!StringUtils.isBlank(doc.getPartNo()))
			item.setPartNo(doc.getPartNo());
		if (!StringUtils.isBlank(doc.getProductGroupId()))
			item.setProductGroupId(doc.getProductGroupId());
		if (!StringUtils.isBlank(doc.getProductGroupName()))
			item.setProductGroupName(doc.getProductGroupName());
		
		if (!StringUtils.isBlank(doc.getSubItemId()))
			item.setSubItemId(doc.getSubItemId());
		if (!StringUtils.isBlank(doc.getSubPartNo()))
			item.setSubPartNo(doc.getSubPartNo());
		if (!StringUtils.isBlank(doc.getSubItemDescription()))
			item.setSubItemDescription(doc.getSubItemDescription());
		if (!StringUtils.isBlank(doc.getSubProductGroupId()))
			item.setSubProductGroupId(doc.getSubProductGroupId());
		if (!StringUtils.isBlank(doc.getSubProductGroupName()))
			item.setSubProductGroupName(doc.getSubProductGroupName());
		if (!StringUtils.isBlank(doc.getSubItemImageURL()))
			item.setSubItemImageURL(doc.getSubItemImageURL());
		if (!StringUtils.isBlank(doc.getSubItemAvailabilityStatus()))
			item.setSubItemAvailabilityStatus(doc
					.getSubItemAvailabilityStatus());
		if (!StringUtils.isBlank(doc.getSubItemSellingPrice()))
			item.setSubItemSellingPrice(doc.getSubItemSellingPrice());
		if (!StringUtils.isBlank(doc.getSubItemCost()))
			item.setSubItemCost(doc.getSubItemCost());
		if (!StringUtils.isBlank(doc.getSubItemAccessoryIndicator()))
			item.setSubItemAccessoryIndicator(doc
					.getSubItemAccessoryIndicator());
		if (!StringUtils.isBlank(doc.getSubHazardousMaterialCode()))
			item.setSubItemDisclosure(doc.getSubItemDisclosure());
		if (!StringUtils.isBlank(doc.getSubHazardousMaterialCode()))
			item.setSubHazardousMaterialCode(doc.getSubHazardousMaterialCode());
		if (!StringUtils.isBlank(doc.getSubProductIndicator()))
			item.setSubProductIndicator(doc.getSubProductIndicator());
		if (!StringUtils.isBlank(doc.getSubItemComment()))
			item.setSubItemComment(doc.getSubItemComment());
		if (!StringUtils.isBlank(doc.getSubItemCondition()))
			item.setSubItemCondition(doc.getSubItemCondition());
		if (!StringUtils.isBlank(doc.getSubItemSuggestedQty()))
			item.setSubItemSuggestedQty(doc.getSubItemSuggestedQty());
		if (!StringUtils.isBlank(doc.getSubUPCcode()))
			item.setSubUPCcode(doc.getSubUPCcode());
		if (!StringUtils.isBlank(doc.getItemSchematicId()))
			item.setItemSchematicId(doc.getItemSchematicId());
		if (!StringUtils.isBlank(doc.getItemKeyId()))
			item.setItemKeyId(doc.getItemKeyId());
		return item;
	}

	public Item docToItemNoSubstitute(Doc doc) {

		Item item = new Item();

		if (!StringUtils.isBlank(doc.getItemId()))
			item.setItemId(doc.getItemId());
		if (!StringUtils.isBlank(doc.getPartNo()))
			item.setPartNo(doc.getPartNo());
		if (!StringUtils.isBlank(doc.getProductGroupId()))
			item.setProductGroupId(doc.getProductGroupId());
		if (!StringUtils.isBlank(doc.getProductGroupName()))
			item.setProductGroupName(doc.getProductGroupName());
		if (!StringUtils.isBlank(doc.getItemDescription()))
			item.setItemDescription(doc.getItemDescription());
		if (!StringUtils.isBlank(doc.getItemImageURL()))
			item.setItemImageUrl(doc.getItemImageURL());
		if (!StringUtils.isBlank(doc.getItemAvailabilityStatus()))
			item.setItemAvailabilityStatus(doc.getItemAvailabilityStatus());
		if (!StringUtils.isBlank(doc.getItemSellingPrice()))
			item.setItemSellingPrice(doc.getItemSellingPrice());
		if (!StringUtils.isBlank(doc.getItemCost()))
			item.setItemCost(doc.getItemCost());
		if (!StringUtils.isBlank(doc.getItemAddDate()))
			item.setItemAddDate(doc.getItemAddDate());
		if (!StringUtils.isBlank(doc.getItemAccessoryIndicator()))
			item.setItemAccessoryIndicator(doc.getItemAccessoryIndicator());
		if (!StringUtils.isBlank(doc.getItemDisclosure()))
			item.setItemDisclosure(doc.getItemDisclosure());
		if (!StringUtils.isBlank(doc.getHazardousMaterialCode()))
			item.setHazardousMaterialCode(doc.getHazardousMaterialCode());
		if (!StringUtils.isBlank(doc.getProductIndicator()))
			item.setProductIndicator(doc.getProductIndicator());
		if (!StringUtils.isBlank(doc.getItemComment()))
			item.setItemComment(doc.getItemComment());
		if (!StringUtils.isBlank(doc.getItemCondition()))
			item.setItemCondition(doc.getItemCondition());
		if (!StringUtils.isBlank(doc.getItemSuggestedQty()))
			item.setItemSuggestedQty(doc.getItemSuggestedQty());
		if (!StringUtils.isBlank(doc.getSubbedFlag()))
			item.setSubbedFlag(doc.getSubbedFlag());
		if (!StringUtils.isBlank(doc.getUpcCode()))
			item.setUpcCode(doc.getUpcCode());
		if (!StringUtils.isBlank(doc.getItemSynonymName()))
			item.setItemSynonymName(doc.getItemSynonymName());
		if (!StringUtils.isBlank(doc.getEngineModelFlag()))
			item.setEngineModelFlag(doc.getEngineModelFlag());
		if (!StringUtils.isBlank(doc.getItemSchematicId()))
			item.setItemSchematicId(doc.getItemSchematicId());
		if (!StringUtils.isBlank(doc.getItemKeyId()))
			item.setItemKeyId(doc.getItemKeyId());
		return item;
	}

	public Item docToItemNoSubstituteWithRestrictionAttribute(Doc doc) {

		Item item = new Item();

		if (!StringUtils.isBlank(doc.getItemId()))
			item.setItemId(doc.getItemId());
		if (!StringUtils.isBlank(doc.getPartNo()))
			item.setPartNo(doc.getPartNo());
		if (!StringUtils.isBlank(doc.getProductGroupId()))
			item.setProductGroupId(doc.getProductGroupId());
		if (!StringUtils.isBlank(doc.getProductGroupName()))
			item.setProductGroupName(doc.getProductGroupName());
		if (!StringUtils.isBlank(doc.getItemDescription()))
			item.setItemDescription(doc.getItemDescription());
		if (!StringUtils.isBlank(doc.getItemImageURL()))
			item.setItemImageUrl(doc.getItemImageURL());
		if (!StringUtils.isBlank(doc.getItemAvailabilityStatus()))
			item.setItemAvailabilityStatus(doc.getItemAvailabilityStatus());
		if (!StringUtils.isBlank(doc.getItemSellingPrice()))
			item.setItemSellingPrice(doc.getItemSellingPrice());
		if (!StringUtils.isBlank(doc.getItemCost()))
			item.setItemCost(doc.getItemCost());
		if (!StringUtils.isBlank(doc.getItemAddDate()))
			item.setItemAddDate(doc.getItemAddDate());
		if (!StringUtils.isBlank(doc.getItemAccessoryIndicator()))
			item.setItemAccessoryIndicator(doc.getItemAccessoryIndicator());
		if (!StringUtils.isBlank(doc.getItemDisclosure()))
			item.setItemDisclosure(doc.getItemDisclosure());
		if (!StringUtils.isBlank(doc.getHazardousMaterialCode()))
			item.setHazardousMaterialCode(doc.getHazardousMaterialCode());
		if (!StringUtils.isBlank(doc.getProductIndicator()))
			item.setProductIndicator(doc.getProductIndicator());
		if (!StringUtils.isBlank(doc.getItemComment()))
			item.setItemComment(doc.getItemComment());
		if (!StringUtils.isBlank(doc.getItemCondition()))
			item.setItemCondition(doc.getItemCondition());
		if (!StringUtils.isBlank(doc.getItemSuggestedQty()))
			item.setItemSuggestedQty(doc.getItemSuggestedQty());
		if (!StringUtils.isBlank(doc.getSubbedFlag()))
			item.setSubbedFlag(doc.getSubbedFlag());
		if (!StringUtils.isBlank(doc.getUpcCode()))
			item.setUpcCode(doc.getUpcCode());
		if (!StringUtils.isBlank(doc.getItemSynonymName()))
			item.setItemSynonymName(doc.getItemSynonymName());
		if (!StringUtils.isBlank(doc.getEngineModelFlag()))
			item.setEngineModelFlag(doc.getEngineModelFlag());
		if (!StringUtils.isBlank(doc.getItemSchematicId()))
			item.setItemSchematicId(doc.getItemSchematicId());
		if (!StringUtils.isBlank(doc.getItemKeyId()))
			item.setItemKeyId(doc.getItemKeyId());
		if (!CollectionUtils.isEmpty(doc.getItemAttributes()))
			item.setAttributes(AttributeUtils.convert(doc.getItemAttributes()));
		if (!CollectionUtils.isEmpty(doc.getItemRestrictions()))
			item.setRestrictions(RestrictionUtils.convert(doc.getItemRestrictions()));

		return item;
	}

	public Item docToItemSubstituteWithRestrictionAttribute(Doc doc) {

		Item item = new Item();
		if (!StringUtils.isBlank(doc.getSubItemId()))
			item.setSubItemId(doc.getSubItemId());
		if (!StringUtils.isBlank(doc.getSubPartNo()))
			item.setSubPartNo(doc.getSubPartNo());
		if (!StringUtils.isBlank(doc.getSubItemDescription()))
			item.setSubItemDescription(doc.getSubItemDescription());
		if (!StringUtils.isBlank(doc.getSubProductGroupId()))
			item.setSubProductGroupId(doc.getSubProductGroupId());
		if (!StringUtils.isBlank(doc.getSubProductGroupName()))
			item.setSubProductGroupName(doc.getSubProductGroupName());
		if (!StringUtils.isBlank(doc.getSubItemImageURL()))
			item.setSubItemImageURL(doc.getSubItemImageURL());
		if (!StringUtils.isBlank(doc.getSubItemAvailabilityStatus()))
			item.setSubItemAvailabilityStatus(doc
					.getSubItemAvailabilityStatus());
		if (!StringUtils.isBlank(doc.getSubItemSellingPrice()))
			item.setSubItemSellingPrice(doc.getSubItemSellingPrice());
		if (!StringUtils.isBlank(doc.getSubItemCost()))
			item.setItemCost(doc.getSubItemCost());
		if (!StringUtils.isBlank(doc.getSubItemAccessoryIndicator()))
			item.setSubItemAccessoryIndicator(doc
					.getSubItemAccessoryIndicator());
		if (!StringUtils.isBlank(doc.getSubHazardousMaterialCode()))
			item.setSubItemDisclosure(doc.getSubItemDisclosure());
		if (!StringUtils.isBlank(doc.getSubHazardousMaterialCode()))
			item.setSubHazardousMaterialCode(doc.getSubHazardousMaterialCode());
		if (!StringUtils.isBlank(doc.getSubProductIndicator()))
			item.setSubProductIndicator(doc.getSubProductIndicator());
		if (!StringUtils.isBlank(doc.getSubItemComment()))
			item.setSubItemComment(doc.getSubItemComment());
		if (!StringUtils.isBlank(doc.getSubItemCondition()))
			item.setSubItemCondition(doc.getSubItemCondition());
		if (!StringUtils.isBlank(doc.getSubItemSuggestedQty()))
			item.setSubItemSuggestedQty(doc.getSubItemSuggestedQty());
		if (!StringUtils.isBlank(doc.getSubUPCcode()))
			item.setSubUPCcode(doc.getSubUPCcode());
		if (!StringUtils.isBlank(doc.getItemSchematicId()))
			item.setItemSchematicId(doc.getItemSchematicId());
		if (!StringUtils.isBlank(doc.getItemKeyId()))
			item.setItemKeyId(doc.getItemKeyId());
		if (!CollectionUtils.isEmpty(doc.getItemAttributes()))
			item.setAttributes(AttributeUtils.convert(doc.getItemAttributes()));
		if (!CollectionUtils.isEmpty(doc.getItemRestrictions()))
			item.setRestrictions(RestrictionUtils.convert(doc.getItemRestrictions()));

		return item;
	}
	
	public AValue docToSearch(Doc doc) {

		AValue avalue = new AValue();
		
		if (StringUtils.isNotBlank(doc.getModelId())) {
			avalue.setId(doc.getModelId());
			avalue.setNumber(doc.getModelNo());
			avalue.setDescription(doc.getModelDescription());
			avalue.setType("M");
		}
		else if (StringUtils.isNotBlank(doc.getItemId())) {
			avalue.setId(doc.getItemId());
			avalue.setNumber(doc.getPartNo());
			avalue.setDescription(doc.getItemDescription());
			avalue.setType("P");
		}
		else {
			return null;
		}
		
		return avalue;
	}
}