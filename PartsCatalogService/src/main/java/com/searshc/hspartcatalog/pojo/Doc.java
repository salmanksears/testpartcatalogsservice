/**
 * 
 */
package com.searshc.hspartcatalog.pojo;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.searshc.hs.psc.logging.CustomToStringStyle;

/**
* Title			:   Doc
* Description	:	pojo which holds data that comes from SOLR. 
* 					It gets created by jackson to convert SOLR json response into Java object
* @author		:	Abhishek Jain
*/

@JsonIgnoreProperties(ignoreUnknown = true)
public class Doc {
	
	@JsonProperty("itemId") private String itemId;
	@JsonProperty("partNo") private String partNo;
	@JsonProperty("productGroupId") private String productGroupId;
	@JsonProperty("productGroupName") private String productGroupName;
	@JsonProperty("itemDescription") private String itemDescription;
	@JsonProperty("itemImageURL") private String itemImageURL;
	@JsonProperty("itemAvailabilityStatus") private String itemAvailabilityStatus;
	@JsonProperty("itemSellingPrice") private String itemSellingPrice;
	@JsonProperty("itemCost") private String itemCost;
	@JsonProperty("itemAddDate") private String itemAddDate;
	@JsonProperty("itemAccessoryIndicator") private String itemAccessoryIndicator;
	@JsonProperty("itemDisclosure") private String itemDisclosure;
	@JsonProperty("hazardousMaterialCode") private String hazardousMaterialCode;
	@JsonProperty("productIndicator") private String productIndicator;
	@JsonProperty("itemComment") private String itemComment;
	@JsonProperty("itemCondition") private String itemCondition;
	@JsonProperty("itemSuggestedQty") private String itemSuggestedQty;
	@JsonProperty("subbedFlag") private String subbedFlag;
	@JsonProperty("upcCode") private String upcCode;
	@JsonProperty("itemSynonynName") private String itemSynonymName;
	@JsonProperty("engineModelFlag") private String engineModelFlag;
	@JsonProperty("subItemId") private String subItemId;
	@JsonProperty("subPartNo") private String subPartNo;
	@JsonProperty("subItemDescription") private String subItemDescription;
	@JsonProperty("subProductGroupId") private String subProductGroupId;
	@JsonProperty("subProductGroupName") private String subProductGroupName;
	@JsonProperty("subItemImageURL") private String subItemImageURL;
	@JsonProperty("subItemAvailabilityStatus") private String subItemAvailabilityStatus;
	@JsonProperty("subItemSellingPrice") private String subItemSellingPrice;
	@JsonProperty("subItemCost") private String subItemCost;
	@JsonProperty("subItemAccessoryIndicator") private String subItemAccessoryIndicator;
	@JsonProperty("subItemDisclosure") private String subItemDisclosure;
	@JsonProperty("subHazardousMaterialCode") private String subHazardousMaterialCode;
	@JsonProperty("subProductIndicator") private String subProductIndicator;
	@JsonProperty("subItemComment") private String subItemComment;
	@JsonProperty("subItemCondition") private String subItemCondition;
	@JsonProperty("subItemSuggestedQty") private String subItemSuggestedQty;
	@JsonProperty("subUPCcode") private String subUPCcode;
	
	@JsonProperty("modelId") private String modelId;
	@JsonProperty("modelNo") private String modelNo;
	@JsonProperty("modelDescription") private String modelDescription;
	@JsonProperty("brandId") private String brandId;
	@JsonProperty("brand") private String brand;
	@JsonProperty("parentProductTypeId") private String parentProductTypeId;
	@JsonProperty("parentProductTypeName") private String parentProductTypeName;
	@JsonProperty("productTypeId") private String productTypeId;
	@JsonProperty("productTypeName") private String productTypeName;
	@JsonProperty("subProductTypeId") private String subProductTypeId;
	@JsonProperty("subProductTypeName") private String subProductTypeName;
	@JsonProperty("modelDiagramCount") private String modelDiagramCount;
	@JsonProperty("itemCount") private String itemCount;
	@JsonProperty("ownersManualURL") private String ownersManualURL;
	@JsonProperty("installationManualURL") private String installationManualURL;
	
	@JsonProperty("modelCount") private String modelCount;
	
	@JsonProperty("schematicId") private String schematicId;
	@JsonProperty("schematicDescription") private String schematicDescription;
	@JsonProperty("schematicURL") private String schematicURL;
	
	@JsonProperty("itemRestrictionId") private String  itemRestrictionId;
	@JsonProperty("restrictionId") private String restrictionId;
	@JsonProperty("restrictionTypeCd") private String restrictionTypeCd;
	@JsonProperty("restrictionDescription") private String restrictionDescription;
	@JsonProperty("restrictionStates") private String restrictionStates;
	
	@JsonProperty("itemAttributeId") private String  itemAttributeId;
	@JsonProperty("attributeId") private String attributeId;
	@JsonProperty("attributeName") private String attributeName;
	@JsonProperty("attributeValue") private String attributeValue;
	
	@JsonProperty("itemModelId") private String  itemModelId;
	@JsonProperty("itemSchematicId") private String itemSchematicId;
	@JsonProperty("itemKeyId") private String itemKeyId;
	
	@JsonProperty("itemAttributes") private List<String> itemAttributes;
	@JsonProperty("itemRestrictions") private List<String> itemRestrictions;
	@JsonProperty("itemSchematics") private List<String> itemSchematics;
	@JsonProperty("modelSchematics") private List<String> modelSchematics;
	@JsonProperty("modelAttributes") private List<String> modelAttributes;
	@JsonProperty("modelImageURL") private String modelImageURL;
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getProductGroupId() {
		return productGroupId;
	}
	public void setProductGroupId(String productGroupId) {
		this.productGroupId = productGroupId;
	}
	public String getProductGroupName() {
		return productGroupName;
	}
	public void setProductGroupName(String productGroupName) {
		this.productGroupName = productGroupName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getItemImageURL() {
		return itemImageURL;
	}
	public void setItemImageURL(String itemImageUrl) {
		this.itemImageURL = itemImageUrl;
	}
	public String getItemAvailabilityStatus() {
		return itemAvailabilityStatus;
	}
	public void setItemAvailabilityStatus(String itemAvailabilityStatus) {
		this.itemAvailabilityStatus = itemAvailabilityStatus;
	}
	public String getItemSellingPrice() {
		return itemSellingPrice;
	}
	public void setItemSellingPrice(String itemSellingPrice) {
		this.itemSellingPrice = itemSellingPrice;
	}
	public String getItemAccessoryIndicator() {
		return itemAccessoryIndicator;
	}
	public void setItemAccessoryIndicator(String itemAccessoryIndicator) {
		this.itemAccessoryIndicator = itemAccessoryIndicator;
	}
	public String getItemDisclosure() {
		return itemDisclosure;
	}
	public void setItemDisclosure(String itemDisclosure) {
		this.itemDisclosure = itemDisclosure;
	}
	public String getHazardousMaterialCode() {
		return hazardousMaterialCode;
	}
	public void setHazardousMaterialCode(String hazardousMaterialCode) {
		this.hazardousMaterialCode = hazardousMaterialCode;
	}
	public String getProductIndicator() {
		return productIndicator;
	}
	public void setProductIndicator(String productIndicator) {
		this.productIndicator = productIndicator;
	}
	public String getItemComment() {
		return itemComment;
	}
	public void setItemComment(String itemComment) {
		this.itemComment = itemComment;
	}
	public String getItemCondition() {
		return itemCondition;
	}
	public void setItemCondition(String itemCondition) {
		this.itemCondition = itemCondition;
	}
	public String getItemSuggestedQty() {
		return itemSuggestedQty;
	}
	public void setItemSuggestedQty(String itemSuggestedQty) {
		this.itemSuggestedQty = itemSuggestedQty;
	}
	public String getSubbedFlag() {
		return subbedFlag;
	}
	public void setSubbedFlag(String subbedFlag) {
		this.subbedFlag = subbedFlag;
	}
	public String getUpcCode() {
		return upcCode;
	}
	public void setUpcCode(String upcCode) {
		this.upcCode = upcCode;
	}
	public String getItemSynonymName() {
		return itemSynonymName;
	}
	public void setItemSynonymName(String itemSynonymName) {
		this.itemSynonymName = itemSynonymName;
	}
	public String getEngineModelFlag() {
		return engineModelFlag;
	}
	public void setEngineModelFlag(String engineModelFlag) {
		this.engineModelFlag = engineModelFlag;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	public String getModelDescription() {
		return modelDescription;
	}
	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getParentProductTypeId() {
		return parentProductTypeId;
	}
	public void setParentProductTypeId(String parentProductTypeId) {
		this.parentProductTypeId = parentProductTypeId;
	}
	public String getParentProductTypeName() {
		return parentProductTypeName;
	}
	public void setParentProductTypeName(String parentProductTypeName) {
		this.parentProductTypeName = parentProductTypeName;
	}
	public String getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getSubProductTypeId() {
		return subProductTypeId;
	}
	public void setSubProductTypeId(String subProductTypeId) {
		this.subProductTypeId = subProductTypeId;
	}
	public String getSubProductTypeName() {
		return subProductTypeName;
	}
	public void setSubProductTypeName(String subProductTypeName) {
		this.subProductTypeName = subProductTypeName;
	}
	public String getModelDiagramCount() {
		return modelDiagramCount;
	}
	public void setModelDiagramCount(String modelDiagramCount) {
		this.modelDiagramCount = modelDiagramCount;
	}
	public String getItemCount() {
		return itemCount;
	}
	public void setItemCount(String itemCount) {
		this.itemCount = itemCount;
	}
	public String getOwnersManualURL() {
		return ownersManualURL;
	}
	public void setOwnersManualURL(String ownerManualURL) {
		this.ownersManualURL = ownerManualURL;
	}
	public String getInstallationManualURL() {
		return installationManualURL;
	}
	public void setInstallationManualURL(String installationManualURL) {
		this.installationManualURL = installationManualURL;
	}
	public String getModelCount() {
		return modelCount;
	}
	public void setModelCount(String modelCount) {
		this.modelCount = modelCount;
	}
	public String getSubItemId() {
		return subItemId;
	}
	public void setSubItemId(String subItemId) {
		this.subItemId = subItemId;
	}
	public String getSubPartNo() {
		return subPartNo;
	}
	public void setSubPartNo(String subPartNo) {
		this.subPartNo = subPartNo;
	}
	public String getSubItemDescription() {
		return subItemDescription;
	}
	public void setSubItemDescription(String subItemDescription) {
		this.subItemDescription = subItemDescription;
	}
	public String getSubProductGroupId() {
		return subProductGroupId;
	}
	public void setSubProductGroupId(String subProductGroupId) {
		this.subProductGroupId = subProductGroupId;
	}
	public String getSubProductGroupName() {
		return subProductGroupName;
	}
	public void setSubProductGroupName(String subProductGroupName) {
		this.subProductGroupName = subProductGroupName;
	}
	public String getSubItemImageURL() {
		return subItemImageURL;
	}
	public void setSubItemImageURL(String subItemImageURL) {
		this.subItemImageURL = subItemImageURL;
	}
	public String getSubItemAvailabilityStatus() {
		return subItemAvailabilityStatus;
	}
	public void setSubItemAvailabilityStatus(String subItemAvailabilityStatus) {
		this.subItemAvailabilityStatus = subItemAvailabilityStatus;
	}
	public String getSubItemSellingPrice() {
		return subItemSellingPrice;
	}
	public void setSubItemSellingPrice(String subItemSellingPrice) {
		this.subItemSellingPrice = subItemSellingPrice;
	}
	public String getSubItemAccessoryIndicator() {
		return subItemAccessoryIndicator;
	}
	public void setSubItemAccessoryIndicator(String subItemAccessoryIndicator) {
		this.subItemAccessoryIndicator = subItemAccessoryIndicator;
	}
	public String getSubItemDisclosure() {
		return subItemDisclosure;
	}
	public void setSubItemDisclosure(String subItemDisclosure) {
		this.subItemDisclosure = subItemDisclosure;
	}
	public String getSubHazardousMaterialCode() {
		return subHazardousMaterialCode;
	}
	public void setSubHazardousMaterialCode(String subHazardousMaterialCode) {
		this.subHazardousMaterialCode = subHazardousMaterialCode;
	}
	public String getSubProductIndicator() {
		return subProductIndicator;
	}
	public void setSubProductIndicator(String subProductIndicator) {
		this.subProductIndicator = subProductIndicator;
	}
	public String getSubItemComment() {
		return subItemComment;
	}
	public void setSubItemComment(String subItemComment) {
		this.subItemComment = subItemComment;
	}
	public String getSubItemCondition() {
		return subItemCondition;
	}
	public void setSubItemCondition(String subItemCondition) {
		this.subItemCondition = subItemCondition;
	}
	public String getSubItemSuggestedQty() {
		return subItemSuggestedQty;
	}
	public void setSubItemSuggestedQty(String subItemSuggestedQty) {
		this.subItemSuggestedQty = subItemSuggestedQty;
	}
	public String getSubUPCcode() {
		return subUPCcode;
	}
	public void setSubUPCcode(String subUPCcode) {
		this.subUPCcode = subUPCcode;
	}
	public String getSchematicId() {
		return schematicId;
	}
	public void setSchematicId(String schematicId) {
		this.schematicId = schematicId;
	}
	public String getSchematicDescription() {
		return schematicDescription;
	}
	public void setSchematicDescription(String schematicDescription) {
		this.schematicDescription = schematicDescription;
	}
	public String getSchematicURL() {
		return schematicURL;
	}
	public void setSchematicURL(String schematicURL) {
		this.schematicURL = schematicURL;
	}
	public String getItemRestrictionId() {
		return itemRestrictionId;
	}
	public void setItemRestrictionId(String itemRestrictionId) {
		this.itemRestrictionId = itemRestrictionId;
	}
	public String getRestrictionId() {
		return restrictionId;
	}
	public void setRestrictionId(String restrictionId) {
		this.restrictionId = restrictionId;
	}
	public String getRestrictionTypeCd() {
		return restrictionTypeCd;
	}
	public void setRestrictionTypeCd(String restrictionTypeCd) {
		this.restrictionTypeCd = restrictionTypeCd;
	}
	public String getRestrictionDescription() {
		return restrictionDescription;
	}
	public void setRestrictionDescription(String restrictionDescription) {
		this.restrictionDescription = restrictionDescription;
	}
	public String getItemAttributeId() {
		return itemAttributeId;
	}
	public void setItemAttributeId(String itemAttributeId) {
		this.itemAttributeId = itemAttributeId;
	}
	public String getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	
	public String getItemModelId() {
		return itemModelId;
	}
	public void setItemModelId(String itemModelId) {
		this.itemModelId = itemModelId;
	}
	public String getItemSchematicId() {
		return itemSchematicId;
	}
	public void setItemSchematicId(String itemSchematicId) {
		this.itemSchematicId = itemSchematicId;
	}
	public String getItemKeyId() {
		return itemKeyId;
	}
	public void setItemKeyId(String itemKeyId) {
		this.itemKeyId = itemKeyId;
	}
	public List<String> getItemAttributes() {
		return itemAttributes;
	}
	public void setItemAttributes(List<String> itemAttributes) {
		this.itemAttributes = itemAttributes;
	}
	public List<String> getItemRestrictions() {
		return itemRestrictions;
	}
	public void setItemRestrictions(List<String> itemRestrictions) {
		this.itemRestrictions = itemRestrictions;
	}
	public List<String> getItemSchematics() {
		return itemSchematics;
	}
	public void setItemSchematics(List<String> itemSchematics) {
		this.itemSchematics = itemSchematics;
	}
	public List<String> getModelSchematics() {
		return modelSchematics;
	}
	public void setModelSchematics(List<String> modelSchematics) {
		this.modelSchematics = modelSchematics;
	}
	public String getRestrictionStates() {
		return restrictionStates;
	}
	public void setRestrictionStates(String restrictionStates) {
		this.restrictionStates = restrictionStates;
	}
	public List<String> getModelAttributes() {
		return modelAttributes;
	}
	public void setModelAttributes(List<String> modelAttributes) {
		this.modelAttributes = modelAttributes;
	}
	public String getModelImageURL() {
		return modelImageURL;
	}
	public void setModelImageURL(String modelImageURL) {
		this.modelImageURL = modelImageURL;
	}
	public String getItemCost() {
		return itemCost;
	}
	public void setItemCost(String itemCost) {
		this.itemCost = itemCost;
	}
	public String getItemAddDate() {
		return itemAddDate;
	}
	public void setItemAddDate(String itemAddDate) {
		this.itemAddDate = itemAddDate;
	}
	public String getSubItemCost() {
		return subItemCost;
	}
	public void setSubItemCost(String subItemCost) {
		this.subItemCost = subItemCost;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("itemId", itemId);
		builder.append("partNo", partNo);
		builder.append("productGroupId", productGroupId);
		builder.append("productGroupName", productGroupName);
		builder.append("itemDescription", itemDescription);
		builder.append("itemImageURL", itemImageURL);
		builder.append("itemAvailabilityStatus", itemAvailabilityStatus);
		builder.append("itemSellingPrice", itemSellingPrice);
		builder.append("itemCost", itemCost);
		builder.append("itemAddDate", itemAddDate);
		builder.append("itemAccessoryIndicator", itemAccessoryIndicator);
		builder.append("itemDisclosure", itemDisclosure);
		builder.append("hazardousMaterialCode", hazardousMaterialCode);
		builder.append("productIndicator", productIndicator);
		builder.append("itemComment", itemComment);
		builder.append("itemCondition", itemCondition);
		builder.append("itemSuggestedQty", itemSuggestedQty);
		builder.append("subbedFlag", subbedFlag);
		builder.append("upcCode", upcCode);
		builder.append("itemSynonymName", itemSynonymName);
		builder.append("engineModelFlag", engineModelFlag);
		builder.append("subItemId", subItemId);
		builder.append("subPartNo", subPartNo);
		builder.append("subItemDescription", subItemDescription);
		builder.append("subProductGroupId", subProductGroupId);
		builder.append("subProductGroupName", subProductGroupName);
		builder.append("subItemImageURL", subItemImageURL);
		builder.append("subItemAvailabilityStatus", subItemAvailabilityStatus);
		builder.append("subItemSellingPrice", subItemSellingPrice);
		builder.append("subItemCost", subItemCost);
		builder.append("subItemAccessoryIndicator", subItemAccessoryIndicator);
		builder.append("subItemDisclosure", subItemDisclosure);
		builder.append("subHazardousMaterialCode", subHazardousMaterialCode);
		builder.append("subProductIndicator", subProductIndicator);
		builder.append("subItemComment", subItemComment);
		builder.append("subItemCondition", subItemCondition);
		builder.append("subItemSuggestedQty", subItemSuggestedQty);
		builder.append("subUPCcode", subUPCcode);
		builder.append("modelId", modelId);
		builder.append("modelNo", modelNo);
		builder.append("modelDescription", modelDescription);
		builder.append("brandId", brandId);
		builder.append("brand", brand);
		builder.append("parentProductTypeId", parentProductTypeId);
		builder.append("parentProductTypeName", parentProductTypeName);
		builder.append("productTypeId", productTypeId);
		builder.append("productTypeName", productTypeName);
		builder.append("subProductTypeId", subProductTypeId);
		builder.append("subProductTypeName", subProductTypeName);
		builder.append("modelDiagramCount", modelDiagramCount);
		builder.append("itemCount", itemCount);
		builder.append("ownersManualURL", ownersManualURL);
		builder.append("installationManualURL", installationManualURL);
		builder.append("modelCount", modelCount);
		builder.append("schematicId", schematicId);
		builder.append("schematicDescription", schematicDescription);
		builder.append("schematicURL", schematicURL);
		builder.append("itemRestrictionId", itemRestrictionId);
		builder.append("restrictionId", restrictionId);
		builder.append("restrictionTypeCd", restrictionTypeCd);
		builder.append("restrictionDescription", restrictionDescription);
		builder.append("restrictionStates", restrictionStates);
		builder.append("itemAttributeId", itemAttributeId);
		builder.append("attributeId", attributeId);
		builder.append("attributeName", attributeName);
		builder.append("attributeValue", attributeValue);
		builder.append("itemModelId", itemModelId);
		builder.append("itemSchematicId", itemSchematicId);
		builder.append("itemKeyId", itemKeyId);
		builder.append("itemAttributes", itemAttributes);
		builder.append("itemRestrictions", itemRestrictions);
		builder.append("itemSchematics", itemSchematics);
		builder.append("modelSchematics", modelSchematics);
		builder.append("modelAttributes", modelAttributes);
		builder.append("modelImageURL", modelImageURL);
		
		return builder.toString();
	}

}
