package com.searshc.hspartcatalog.domain.solr;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class Item {

	@Field
	private String id;

	@Field
	private String itemId;

	@Field
	private String productGroupIdPls;

	@Field
	private String partNo;

	private String productGroupId;

	@Field
	private String productGroupName;

	@Field
	private String itemDescription;

	@Field
	private String itemAvailabilityStatus;

	@Field
	private String itemSellingPrice;

	@Field
	private String itemCost;

	@Field
	private String itemAddDate;

	@Field
	private String engineModelFlag;

	@Field
	private String formattedItemId;

	@Field
	private String formattedPartNo;

	@Field
	private String ItemImageURL;

	@Field
	private String ItemAccessoryIndicator;

	@Field
	private String ItemDisclosure;

	@Field
	private String HazardousMaterialCode;

	@Field
	private String ProductIndicator;

	@Field
	private String ItemComment;

	@Field
	private String ItemCondition;

	@Field
	private String ItemSuggestedQty;

	@Field
	private String UpcCode;

	@Field
	private String ItemSynonymName;

	@Field
	private String ItemSchematicId;

	@Field
	private String ItemKeyId;

	@Field
	private String subbedFlag;

	@Field
	private String subEngineModelFlag;

	@Field
	private String subItemId;

	@Field
	private String subItemDescription;

	@Field
	private String subItemAvailabilityStatus;

	@Field
	private String SubPartNo;

	@Field
	private String SubProductGroupId;

	@Field
	private String SubProductGroupName;

	@Field
	private String SubItemImageURL;

	@Field
	private String SubItemSellingPrice;

	@Field
	private String SubItemCost;

	@Field
	private String SubItemAccessoryIndicator;

	@Field
	private String SubHazardousMaterialCode;

	@Field
	private String SubItemDisclosure;

	@Field
	private String SubProductIndicator;

	@Field
	private String SubItemComment;

	@Field
	private String SubItemCondition;

	@Field
	private String SubItemSuggestedQty;

	@Field
	private String SubUPCcode;

	@Field
	private List<String> itemSchematics;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the productGroupIdPls
	 */
	public String getProductGroupIdPls() {
		return productGroupIdPls;
	}

	/**
	 * @param productGroupIdPls
	 *            the productGroupIdPls to set
	 */
	public void setProductGroupIdPls(String productGroupIdPls) {
		this.productGroupIdPls = productGroupIdPls;
	}

	/**
	 * @return the partNo
	 */
	public String getPartNo() {
		return partNo;
	}

	/**
	 * @param partNo
	 *            the partNo to set
	 */
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	/**
	 * @return the productGroupId
	 */
	public String getProductGroupId() {
		return productGroupId;
	}

	/**
	 * @param productGroupId
	 *            the productGroupId to set
	 */
	public void setProductGroupId(String productGroupId) {
		this.productGroupId = productGroupId;
	}

	/**
	 * @return the productGroupName
	 */
	public String getProductGroupName() {
		return productGroupName;
	}

	/**
	 * @param productGroupName
	 *            the productGroupName to set
	 */
	public void setProductGroupName(String productGroupName) {
		this.productGroupName = productGroupName;
	}

	/**
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * @param itemDescription
	 *            the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	/**
	 * @return the itemAvailabilityStatus
	 */
	public String getItemAvailabilityStatus() {
		return itemAvailabilityStatus;
	}

	/**
	 * @param itemAvailabilityStatus
	 *            the itemAvailabilityStatus to set
	 */
	public void setItemAvailabilityStatus(String itemAvailabilityStatus) {
		this.itemAvailabilityStatus = itemAvailabilityStatus;
	}

	/**
	 * @return the itemSellingPrice
	 */
	public String getItemSellingPrice() {
		return itemSellingPrice;
	}

	/**
	 * @param itemSellingPrice
	 *            the itemSellingPrice to set
	 */
	public void setItemSellingPrice(String itemSellingPrice) {
		this.itemSellingPrice = itemSellingPrice;
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

	/**
	 * @return the engineModelFlag
	 */
	public String getEngineModelFlag() {
		return engineModelFlag;
	}

	/**
	 * @param engineModelFlag
	 *            the engineModelFlag to set
	 */
	public void setEngineModelFlag(String engineModelFlag) {
		this.engineModelFlag = engineModelFlag;
	}

	/**
	 * @return the formattedItemId
	 */
	public String getFormattedItemId() {
		return formattedItemId;
	}

	/**
	 * @param formattedItemId
	 *            the formattedItemId to set
	 */
	public void setFormattedItemId(String formattedItemId) {
		this.formattedItemId = formattedItemId;
	}

	/**
	 * @return the formattedPartNo
	 */
	public String getFormattedPartNo() {
		return formattedPartNo;
	}

	/**
	 * @param formattedPartNo
	 *            the formattedPartNo to set
	 */
	public void setFormattedPartNo(String formattedPartNo) {
		this.formattedPartNo = formattedPartNo;
	}

	/**
	 * @return the itemImageURL
	 */
	public String getItemImageURL() {
		return ItemImageURL;
	}

	/**
	 * @param itemImageURL
	 *            the itemImageURL to set
	 */
	public void setItemImageURL(String itemImageURL) {
		ItemImageURL = itemImageURL;
	}

	/**
	 * @return the itemAccessoryIndicator
	 */
	public String getItemAccessoryIndicator() {
		return ItemAccessoryIndicator;
	}

	/**
	 * @param itemAccessoryIndicator
	 *            the itemAccessoryIndicator to set
	 */
	public void setItemAccessoryIndicator(String itemAccessoryIndicator) {
		ItemAccessoryIndicator = itemAccessoryIndicator;
	}

	/**
	 * @return the itemDisclosure
	 */
	public String getItemDisclosure() {
		return ItemDisclosure;
	}

	/**
	 * @param itemDisclosure
	 *            the itemDisclosure to set
	 */
	public void setItemDisclosure(String itemDisclosure) {
		ItemDisclosure = itemDisclosure;
	}

	/**
	 * @return the hazardousMaterialCode
	 */
	public String getHazardousMaterialCode() {
		return HazardousMaterialCode;
	}

	/**
	 * @param hazardousMaterialCode
	 *            the hazardousMaterialCode to set
	 */
	public void setHazardousMaterialCode(String hazardousMaterialCode) {
		HazardousMaterialCode = hazardousMaterialCode;
	}

	/**
	 * @return the productIndicator
	 */
	public String getProductIndicator() {
		return ProductIndicator;
	}

	/**
	 * @param productIndicator
	 *            the productIndicator to set
	 */
	public void setProductIndicator(String productIndicator) {
		ProductIndicator = productIndicator;
	}

	/**
	 * @return the itemComment
	 */
	public String getItemComment() {
		return ItemComment;
	}

	/**
	 * @param itemComment
	 *            the itemComment to set
	 */
	public void setItemComment(String itemComment) {
		ItemComment = itemComment;
	}

	/**
	 * @return the itemCondition
	 */
	public String getItemCondition() {
		return ItemCondition;
	}

	/**
	 * @param itemCondition
	 *            the itemCondition to set
	 */
	public void setItemCondition(String itemCondition) {
		ItemCondition = itemCondition;
	}

	/**
	 * @return the itemSuggestedQty
	 */
	public String getItemSuggestedQty() {
		return ItemSuggestedQty;
	}

	/**
	 * @param itemSuggestedQty
	 *            the itemSuggestedQty to set
	 */
	public void setItemSuggestedQty(String itemSuggestedQty) {
		ItemSuggestedQty = itemSuggestedQty;
	}

	/**
	 * @return the upcCode
	 */
	public String getUpcCode() {
		return UpcCode;
	}

	/**
	 * @param upcCode
	 *            the upcCode to set
	 */
	public void setUpcCode(String upcCode) {
		UpcCode = upcCode;
	}

	/**
	 * @return the itemSynonymName
	 */
	public String getItemSynonymName() {
		return ItemSynonymName;
	}

	/**
	 * @param itemSynonymName
	 *            the itemSynonymName to set
	 */
	public void setItemSynonymName(String itemSynonymName) {
		ItemSynonymName = itemSynonymName;
	}

	/**
	 * @return the itemSchematicId
	 */
	public String getItemSchematicId() {
		return ItemSchematicId;
	}

	/**
	 * @param itemSchematicId
	 *            the itemSchematicId to set
	 */
	public void setItemSchematicId(String itemSchematicId) {
		ItemSchematicId = itemSchematicId;
	}

	/**
	 * @return the itemKeyId
	 */
	public String getItemKeyId() {
		return ItemKeyId;
	}

	/**
	 * @param itemKeyId
	 *            the itemKeyId to set
	 */
	public void setItemKeyId(String itemKeyId) {
		ItemKeyId = itemKeyId;
	}

	/**
	 * @return the subbedFlag
	 */
	public String getSubbedFlag() {
		return subbedFlag;
	}

	/**
	 * @param subbedFlag
	 *            the subbedFlag to set
	 */
	public void setSubbedFlag(String subbedFlag) {
		this.subbedFlag = subbedFlag;
	}

	/**
	 * @return the subEngineModelFlag
	 */
	public String getSubEngineModelFlag() {
		return subEngineModelFlag;
	}

	/**
	 * @param subEngineModelFlag
	 *            the subEngineModelFlag to set
	 */
	public void setSubEngineModelFlag(String subEngineModelFlag) {
		this.subEngineModelFlag = subEngineModelFlag;
	}

	/**
	 * @return the subItemId
	 */
	public String getSubItemId() {
		return subItemId;
	}

	/**
	 * @param subItemId
	 *            the subItemId to set
	 */
	public void setSubItemId(String subItemId) {
		this.subItemId = subItemId;
	}

	/**
	 * @return the subItemDescription
	 */
	public String getSubItemDescription() {
		return subItemDescription;
	}

	/**
	 * @param subItemDescription
	 *            the subItemDescription to set
	 */
	public void setSubItemDescription(String subItemDescription) {
		this.subItemDescription = subItemDescription;
	}

	/**
	 * @return the subItemAvailabilityStatus
	 */
	public String getSubItemAvailabilityStatus() {
		return subItemAvailabilityStatus;
	}

	/**
	 * @param subItemAvailabilityStatus
	 *            the subItemAvailabilityStatus to set
	 */
	public void setSubItemAvailabilityStatus(String subItemAvailabilityStatus) {
		this.subItemAvailabilityStatus = subItemAvailabilityStatus;
	}

	/**
	 * @return the subPartNo
	 */
	public String getSubPartNo() {
		return SubPartNo;
	}

	/**
	 * @param subPartNo
	 *            the subPartNo to set
	 */
	public void setSubPartNo(String subPartNo) {
		SubPartNo = subPartNo;
	}

	/**
	 * @return the subProductGroupId
	 */
	public String getSubProductGroupId() {
		return SubProductGroupId;
	}

	/**
	 * @param subProductGroupId
	 *            the subProductGroupId to set
	 */
	public void setSubProductGroupId(String subProductGroupId) {
		SubProductGroupId = subProductGroupId;
	}

	/**
	 * @return the subProductGroupName
	 */
	public String getSubProductGroupName() {
		return SubProductGroupName;
	}

	/**
	 * @param subProductGroupName
	 *            the subProductGroupName to set
	 */
	public void setSubProductGroupName(String subProductGroupName) {
		SubProductGroupName = subProductGroupName;
	}

	/**
	 * @return the subItemImageURL
	 */
	public String getSubItemImageURL() {
		return SubItemImageURL;
	}

	/**
	 * @param subItemImageURL
	 *            the subItemImageURL to set
	 */
	public void setSubItemImageURL(String subItemImageURL) {
		SubItemImageURL = subItemImageURL;
	}

	/**
	 * @return the subItemSellingPrice
	 */
	public String getSubItemSellingPrice() {
		return SubItemSellingPrice;
	}

	/**
	 * @param subItemSellingPrice
	 *            the subItemSellingPrice to set
	 */
	public void setSubItemSellingPrice(String subItemSellingPrice) {
		SubItemSellingPrice = subItemSellingPrice;
	}

	public String getSubItemCost() {
		return SubItemCost;
	}

	public void setSubItemCost(String subItemCost) {
		SubItemCost = subItemCost;
	}

	/**
	 * @return the subItemAccessoryIndicator
	 */
	public String getSubItemAccessoryIndicator() {
		return SubItemAccessoryIndicator;
	}

	/**
	 * @param subItemAccessoryIndicator
	 *            the subItemAccessoryIndicator to set
	 */
	public void setSubItemAccessoryIndicator(String subItemAccessoryIndicator) {
		SubItemAccessoryIndicator = subItemAccessoryIndicator;
	}

	/**
	 * @return the subHazardousMaterialCode
	 */
	public String getSubHazardousMaterialCode() {
		return SubHazardousMaterialCode;
	}

	/**
	 * @param subHazardousMaterialCode
	 *            the subHazardousMaterialCode to set
	 */
	public void setSubHazardousMaterialCode(String subHazardousMaterialCode) {
		SubHazardousMaterialCode = subHazardousMaterialCode;
	}

	/**
	 * @return the subItemDisclosure
	 */
	public String getSubItemDisclosure() {
		return SubItemDisclosure;
	}

	/**
	 * @param subItemDisclosure
	 *            the subItemDisclosure to set
	 */
	public void setSubItemDisclosure(String subItemDisclosure) {
		SubItemDisclosure = subItemDisclosure;
	}

	/**
	 * @return the subProductIndicator
	 */
	public String getSubProductIndicator() {
		return SubProductIndicator;
	}

	/**
	 * @param subProductIndicator
	 *            the subProductIndicator to set
	 */
	public void setSubProductIndicator(String subProductIndicator) {
		SubProductIndicator = subProductIndicator;
	}

	/**
	 * @return the subItemComment
	 */
	public String getSubItemComment() {
		return SubItemComment;
	}

	/**
	 * @param subItemComment
	 *            the subItemComment to set
	 */
	public void setSubItemComment(String subItemComment) {
		SubItemComment = subItemComment;
	}

	/**
	 * @return the subItemCondition
	 */
	public String getSubItemCondition() {
		return SubItemCondition;
	}

	/**
	 * @param subItemCondition
	 *            the subItemCondition to set
	 */
	public void setSubItemCondition(String subItemCondition) {
		SubItemCondition = subItemCondition;
	}

	/**
	 * @return the subItemSuggestedQty
	 */
	public String getSubItemSuggestedQty() {
		return SubItemSuggestedQty;
	}

	/**
	 * @param subItemSuggestedQty
	 *            the subItemSuggestedQty to set
	 */
	public void setSubItemSuggestedQty(String subItemSuggestedQty) {
		SubItemSuggestedQty = subItemSuggestedQty;
	}

	/**
	 * @return the subUPCcode
	 */
	public String getSubUPCcode() {
		return SubUPCcode;
	}

	/**
	 * @param subUPCcode
	 *            the subUPCcode to set
	 */
	public void setSubUPCcode(String subUPCcode) {
		SubUPCcode = subUPCcode;
	}

	/**
	 * @return the itemSchematics
	 */
	public List<String> getItemSchematics() {
		return itemSchematics;
	}

	/**
	 * @param itemSchematics
	 *            the itemSchematics to set
	 */
	public void setItemSchematics(List<String> itemSchematics) {
		this.itemSchematics = itemSchematics;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [id=").append(id).append(", itemId=").append(itemId).append(", productGroupIdPls=")
				.append(productGroupIdPls).append(", partNo=").append(partNo).append(", productGroupId=")
				.append(productGroupId).append(", productGroupName=").append(productGroupName)
				.append(", itemDescription=").append(itemDescription).append(", itemAvailabilityStatus=")
				.append(itemAvailabilityStatus).append(", itemSellingPrice=").append(itemSellingPrice)
				.append(", itemCost=").append(itemCost).append(", itemAddDate=").append(itemAddDate)
				.append(", engineModelFlag=").append(engineModelFlag).append(", formattedItemId=")
				.append(formattedItemId).append(", formattedPartNo=").append(formattedPartNo).append(", ItemImageURL=")
				.append(ItemImageURL).append(", ItemAccessoryIndicator=").append(ItemAccessoryIndicator)
				.append(", ItemDisclosure=").append(ItemDisclosure).append(", HazardousMaterialCode=")
				.append(HazardousMaterialCode).append(", ProductIndicator=").append(ProductIndicator)
				.append(", ItemComment=").append(ItemComment).append(", ItemCondition=").append(ItemCondition)
				.append(", ItemSuggestedQty=").append(ItemSuggestedQty).append(", UpcCode=").append(UpcCode)
				.append(", ItemSynonymName=").append(ItemSynonymName).append(", ItemSchematicId=")
				.append(ItemSchematicId).append(", ItemKeyId=").append(ItemKeyId).append(", subbedFlag=")
				.append(subbedFlag).append(", subEngineModelFlag=").append(subEngineModelFlag).append(", subItemId=")
				.append(subItemId).append(", subItemDescription=").append(subItemDescription)
				.append(", subItemAvailabilityStatus=").append(subItemAvailabilityStatus).append(", SubPartNo=")
				.append(SubPartNo).append(", SubProductGroupId=").append(SubProductGroupId)
				.append(", SubProductGroupName=").append(SubProductGroupName).append(", SubItemImageURL=")
				.append(SubItemImageURL).append(", SubItemSellingPrice=").append(SubItemSellingPrice)
				.append(", SubItemCost=").append(SubItemCost).append(", SubItemAccessoryIndicator=")
				.append(SubItemAccessoryIndicator).append(", SubHazardousMaterialCode=")
				.append(SubHazardousMaterialCode).append(", SubItemDisclosure=").append(SubItemDisclosure)
				.append(", SubProductIndicator=").append(SubProductIndicator).append(", SubItemComment=")
				.append(SubItemComment).append(", SubItemCondition=").append(SubItemCondition)
				.append(", SubItemSuggestedQty=").append(SubItemSuggestedQty).append(", SubUPCcode=").append(SubUPCcode)
				.append(", itemSchematics=").append(itemSchematics).append("]");
		return builder.toString();
	}
}
