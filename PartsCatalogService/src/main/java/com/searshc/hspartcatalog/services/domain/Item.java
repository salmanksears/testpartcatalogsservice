/**
 * 
 */
package com.searshc.hspartcatalog.services.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;

@XmlType(name = "item", namespace="http://services.hspartcatalog.searshc.com/domain/")
/**, propOrder = {
		"itemId",
		"partNo",
		"productGroupId",
		"productGroupName",
		"itemDescription",
		"itemImageUrl",
		"itemAvailabilityStatus",
		"itemSellingPrice",
		"itemAccessoryIndicator",
		"itemDisclosure",
		"hazardousMaterialCode",
		"productIndicator",
		"itemComment",
		"itemCondition",
		"itemSuggestedQty",
		"subbedFlag",
		"upcCode",
		"itemSynonymName",
		"engineModelFlag",
		"subItemId",
		"subPartNo",
		"subItemDescription",
		"subProductGroupId",
		"subProductGroupName",
		"subItemImageURL",
		"subItemAvailabilityStatus",
		"subItemSellingPrice",
		"subItemAccessoryIndicator",
		"subItemDisclosure",
		"subHazardousMaterialCode",
		"subProductIndicator",
		"subItemComment",
		"subItemCondition",
		"subItemSuggestedQty",
		"subUPCcode",
		"itemSchematicId",
		"itemKeyId",
		"attributes",
		"restrictions"
}
, namespace="http://services.hspartcatalog.searshc.com/domain/")
**/

@XmlAccessorType(XmlAccessType.PROPERTY)
public class Item implements Comparable<Item>{
		
	/**
	 * This field is the unique 
	 * identifier for a particular 
	 * item. 
	 */

	private String itemId;
	
	/**
	 * The Part Number
	 * which identifies a product services part.
	 * This number is assigned by Sears.Part Number 
	 * is used in combination with the Div/Pls
	 */
	private String partNo;
	
	/**
	 * The number assigned
	 * to each product group
	 */
	private String productGroupId;
	
	/**
	 * The Product Group of
	 * the Item that the part is used on,
	 * such as RefrigeratorandFreezer, 
	 * Lawn and Garden, etc.
	 */
	private String productGroupName;
	
	/**
	 * Describes a Product Services Part, 
	 * e.g. aRepair Part,Consumable Part
	 */
	private String itemDescription;
	
	/**
	 * Item image URL
	 * that contains the image
	 * of an item
	 */
	private String itemImageUrl;
	
	/**
	 * The Part Availability Status.
	 */
	private String itemAvailabilityStatus;
	
	/**
	 * Selling Price
	 */
	private String itemSellingPrice;
	
	/**
	 * A flag that indicates if
	 * a part is an accessory part or not
	 */
	private String itemAccessoryIndicator;
	
	/**
	 * The Item disclosure info
	 */
	private String itemDisclosure;
	
	/**
	 * A code that indicates if a part is a 
	 * hazardous material part or not.
	 */
	private String hazardousMaterialCode;
	
	/**
	 * A flag that indicates if an 
	 * Item is a product or not:
	 */
	private String productIndicator;
	
	/**
	 * Additional comments for an Item
	 */
	private String itemComment;
	
	/**
	 * A flag that indicates the condition of an Item 
	 * - R: refurbished part - N: new part
	 */
	private String itemCondition;
	
	/**
	 * The part quantity that is suggested to buy
	 */
	private String itemSuggestedQty;
	
	/**
	 * A flag that indicates if 
	 * an Item is substituted to another item.
	 */
	private String subbedFlag;
	
	/**
	 * The Universal Product Code of an Item.
	 */
	private String upcCode;
	
	/**
	 * Item Synonym Name. 
	 * For example: 
	 * The item synonym name is THROTTLE WIRE 
	 * for part description of THROTTLE CABLE. 
	 */
	private String itemSynonymName;
	
	/**
	 * A flag that indicates if a 
	 * part number is an engine model or not.
	 */
	private String engineModelFlag;
	
	private String subItemId;
	
	private String subPartNo;
	
	private String subItemDescription;
	
	private String subProductGroupId;
	
	private String subProductGroupName;
	
	private String subItemImageURL;
	
	private String subItemAvailabilityStatus;

	private String subItemSellingPrice;
	
	private String subItemAccessoryIndicator;
	
	private String subItemDisclosure;
	
	private String subHazardousMaterialCode;
	
	private String subProductIndicator;
	
	private String subItemComment;
	
	private String subItemCondition;
	
	private String subItemSuggestedQty;
	
	private String subUPCcode;
	
	private String itemSchematicId;
	
	private String itemKeyId;
	
	private String itemCost;
	
	private String itemAddDate;
	
	private String subItemCost;
	
	private List<ItemAttribute> attributes;
	
	private List<ItemRestriction> restrictions;
	
	@XmlElement public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	@XmlElement public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	@XmlElement public String getProductGroupId() {
		return productGroupId;
	}
	public void setProductGroupId(String productGroupId) {
		this.productGroupId = productGroupId;
	}
	@XmlElement public String getProductGroupName() {
		return productGroupName;
	}
	public void setProductGroupName(String productGroupName) {
		this.productGroupName = productGroupName;
	}
	@XmlElement public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	@XmlElement public String getItemImageUrl() {
		return itemImageUrl;
	}
	public void setItemImageUrl(String itemImageUrl) {
		this.itemImageUrl = itemImageUrl;
	}
	@XmlElement public String getItemAvailabilityStatus() {
		return itemAvailabilityStatus;
	}
	public void setItemAvailabilityStatus(String itemAvailabilityStatus) {
		this.itemAvailabilityStatus = itemAvailabilityStatus;
	}
	@XmlElement public String getItemSellingPrice() {
		return itemSellingPrice;
	}
	public void setItemSellingPrice(String itemSellingPrice) {
		this.itemSellingPrice = itemSellingPrice;
	}
	@XmlElement public String getItemAccessoryIndicator() {
		return itemAccessoryIndicator;
	}
	public void setItemAccessoryIndicator(String itemAccessoryIndicator) {
		this.itemAccessoryIndicator = itemAccessoryIndicator;
	}
	@XmlElement public String getItemDisclosure() {
		return itemDisclosure;
	}
	public void setItemDisclosure(String itemDisclosure) {
		this.itemDisclosure = itemDisclosure;
	}
	@XmlElement public String getHazardousMaterialCode() {
		return hazardousMaterialCode;
	}
	public void setHazardousMaterialCode(String hazardousMaterialCode) {
		this.hazardousMaterialCode = hazardousMaterialCode;
	}
	@XmlElement public String getProductIndicator() {
		return productIndicator;
	}
	public void setProductIndicator(String productIndicator) {
		this.productIndicator = productIndicator;
	}
	@XmlElement public String getItemComment() {
		return itemComment;
	}
	public void setItemComment(String itemComment) {
		this.itemComment = itemComment;
	}
	@XmlElement public String getItemCondition() {
		return itemCondition;
	}
	public void setItemCondition(String itemCondition) {
		this.itemCondition = itemCondition;
	}
	@XmlElement public String getItemSuggestedQty() {
		return itemSuggestedQty;
	}
	public void setItemSuggestedQty(String itemSuggestedQty) {
		this.itemSuggestedQty = itemSuggestedQty;
	}
	@XmlElement public String getSubbedFlag() {
		return subbedFlag;
	}
	public void setSubbedFlag(String subbedFlag) {
		this.subbedFlag = subbedFlag;
	}
	@XmlElement public String getUpcCode() {
		return upcCode;
	}
	public void setUpcCode(String upcCode) {
		this.upcCode = upcCode;
	}
	@XmlElement public String getItemSynonymName() {
		return itemSynonymName;
	}
	public void setItemSynonymName(String itemSynonymName) {
		this.itemSynonymName = itemSynonymName;
	}
	@XmlElement public String getEngineModelFlag() {
		return engineModelFlag;
	}
	public void setEngineModelFlag(String engineModelFlag) {
		this.engineModelFlag = engineModelFlag;
	}
	@XmlElement public String getSubItemId() {
		return subItemId;
	}
	public void setSubItemId(String subItemId) {
		this.subItemId = subItemId;
	}
	@XmlElement public String getSubPartNo() {
		return subPartNo;
	}
	public void setSubPartNo(String subPartNo) {
		this.subPartNo = subPartNo;
	}
	@XmlElement public String getSubItemDescription() {
		return subItemDescription;
	}
	public void setSubItemDescription(String subItemDescription) {
		this.subItemDescription = subItemDescription;
	}
	@XmlElement public String getSubProductGroupId() {
		return subProductGroupId;
	}
	public void setSubProductGroupId(String subProductGroupId) {
		this.subProductGroupId = subProductGroupId;
	}
	@XmlElement public String getSubProductGroupName() {
		return subProductGroupName;
	}
	public void setSubProductGroupName(String subProductGroupName) {
		this.subProductGroupName = subProductGroupName;
	}
	@XmlElement public String getSubItemImageURL() {
		return subItemImageURL;
	}
	public void setSubItemImageURL(String subItemImageURL) {
		this.subItemImageURL = subItemImageURL;
	}
	@XmlElement public String getSubItemAvailabilityStatus() {
		return subItemAvailabilityStatus;
	}
	public void setSubItemAvailabilityStatus(String subItemAvailabilityStatus) {
		this.subItemAvailabilityStatus = subItemAvailabilityStatus;
	}
	@XmlElement public String getSubItemSellingPrice() {
		return subItemSellingPrice;
	}
	public void setSubItemSellingPrice(String subItemSellingPrice) {
		this.subItemSellingPrice = subItemSellingPrice;
	}
	@XmlElement public String getSubItemAccessoryIndicator() {
		return subItemAccessoryIndicator;
	}
	public void setSubItemAccessoryIndicator(String subItemAccessoryIndicator) {
		this.subItemAccessoryIndicator = subItemAccessoryIndicator;
	}
	@XmlElement public String getSubItemDisclosure() {
		return subItemDisclosure;
	}
	public void setSubItemDisclosure(String subItemDisclosure) {
		this.subItemDisclosure = subItemDisclosure;
	}
	@XmlElement public String getSubHazardousMaterialCode() {
		return subHazardousMaterialCode;
	}
	public void setSubHazardousMaterialCode(String subHazardousMaterialCode) {
		this.subHazardousMaterialCode = subHazardousMaterialCode;
	}
	@XmlElement public String getSubProductIndicator() {
		return subProductIndicator;
	}
	public void setSubProductIndicator(String subProductIndicator) {
		this.subProductIndicator = subProductIndicator;
	}
	@XmlElement public String getSubItemComment() {
		return subItemComment;
	}
	public void setSubItemComment(String subItemComment) {
		this.subItemComment = subItemComment;
	}
	@XmlElement public String getSubItemCondition() {
		return subItemCondition;
	}
	public void setSubItemCondition(String subItemCondition) {
		this.subItemCondition = subItemCondition;
	}
	@XmlElement public String getSubItemSuggestedQty() {
		return subItemSuggestedQty;
	}
	public void setSubItemSuggestedQty(String subItemSuggestedQty) {
		this.subItemSuggestedQty = subItemSuggestedQty;
	}
	@XmlElement public String getSubUPCcode() {
		return subUPCcode;
	}
	public void setSubUPCcode(String subUPCcode) {
		this.subUPCcode = subUPCcode;
	}
	
	@XmlElement public String getItemSchematicId() {
		return itemSchematicId;
	}
	public void setItemSchematicId(String itemSchematicId) {
		this.itemSchematicId = itemSchematicId;
	}
	
	@XmlElement public String getItemKeyId() {
		return itemKeyId;
	}
	public void setItemKeyId(String itemKeyId) {
		this.itemKeyId = itemKeyId;
	}
	
	@XmlTransient
	public String getItemCost() {
		return itemCost;
	}
	public void setItemCost(String itemCost) {
		this.itemCost = itemCost;
	}
	
	@XmlTransient
	public String getSubItemCost() {
		return subItemCost;
	}
	public void setSubItemCost(String subItemCost) {
		this.subItemCost = subItemCost;
	}
	
	@XmlTransient 
	public String getItemAddDate() {
		return itemAddDate;
	}
	public void setItemAddDate(String itemAddDate) {
		this.itemAddDate = itemAddDate;
	}
	
    @XmlElementWrapper(name="attributes")
	@XmlElement(name="attribute")
	public List<ItemAttribute> getAttributes() {
		if(attributes!= null && !attributes.isEmpty())
			return attributes;
		else 
			return null;
	}
	
	public void setAttributes(List<ItemAttribute> attributes) {
		this.attributes = attributes;
	}
	
	@XmlElementWrapper(name="restrictions")
	@XmlElement(name="restriction")
	public List<ItemRestriction> getRestrictions() {
		if(restrictions!= null && !restrictions.isEmpty())
			return restrictions;
		else
			return null;
	}
	
	public void setRestrictions(List<ItemRestriction> restrictions) {
		this.restrictions = restrictions;
	}
	@Override
	public int compareTo(Item item) {
		if(item.getPartNo()== null || item.getSubPartNo() == null || this.partNo == null || this.subPartNo==null){
			if(this.partNo == null)
				this.partNo = this.subPartNo;
			if(item.getPartNo() == null)
				item.setPartNo(item.getSubPartNo()); 
			return this.partNo.compareTo(item.getPartNo());
		}
		else
			return this.partNo.compareTo(item.getPartNo()); 
	}
	
	
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		
		builder.append("Item [itemId=");
		builder.append(itemId);
		builder.append(", partNo=");
		builder.append(partNo);
		builder.append(", productGroupId=");
		builder.append(productGroupId);
		builder.append(", productGroupName=");
		builder.append(productGroupName);
		builder.append(", itemDescription=");
		builder.append(itemDescription);
		builder.append(", itemImageUrl=");
		builder.append(itemImageUrl);
		builder.append(", itemAvailabilityStatus=");
		builder.append(itemAvailabilityStatus);
		builder.append(", itemSellingPrice=");
		builder.append(itemSellingPrice);
		builder.append(", itemCost=");
		builder.append(itemCost);
		builder.append(", itemAddDate=");
		builder.append(itemAddDate);
		builder.append(", itemAccessoryIndicator=");
		builder.append(itemAccessoryIndicator);
		builder.append(", itemDisclosure=");
		builder.append(itemDisclosure);
		builder.append(", hazardousMaterialCode=");
		builder.append(hazardousMaterialCode);
		builder.append(", productIndicator=");
		builder.append(productIndicator);
		builder.append(", itemComment=");
		builder.append(itemComment);
		builder.append(", itemCondition=");
		builder.append(itemCondition);
		builder.append(", itemSuggestedQty=");
		builder.append(itemSuggestedQty);
		builder.append(", subbedFlag=");
		builder.append(subbedFlag);
		builder.append(", upcCode=");
		builder.append(upcCode);
		builder.append(", itemSynonymName=");
		builder.append(itemSynonymName);
		builder.append(", engineModelFlag=");
		builder.append(engineModelFlag);
		builder.append(", subItemId=");
		builder.append(subItemId);
		builder.append(", subPartNo=");
		builder.append(subPartNo);
		builder.append(", subItemDescription=");
		builder.append(subItemDescription);
		builder.append(", subProductGroupId=");
		builder.append(subProductGroupId);
		builder.append(", subProductGroupName=");
		builder.append(subProductGroupName);
		builder.append(", subItemImageURL=");
		builder.append(subItemImageURL);
		builder.append(", subItemAvailabilityStatus=");
		builder.append(subItemAvailabilityStatus);
		builder.append(", subItemSellingPrice=");
		builder.append(subItemSellingPrice);
		builder.append(", subItemCost=");
		builder.append(subItemCost);
		builder.append(", subItemAccessoryIndicator=");
		builder.append(subItemAccessoryIndicator);
		builder.append(", subItemDisclosure=");
		builder.append(subItemDisclosure);
		builder.append(", subHazardousMaterialCode=");
		builder.append(subHazardousMaterialCode);
		builder.append(", subProductIndicator=");
		builder.append(subProductIndicator);
		builder.append(", subItemComment=");
		builder.append(subItemComment);
		builder.append(", subItemCondition=");
		builder.append(subItemCondition);
		builder.append(", subItemSuggestedQty=");
		builder.append(subItemSuggestedQty);
		builder.append(", subUPCcode=");
		builder.append(subUPCcode);
		builder.append(", itemSchematicId=");
		builder.append(itemSchematicId);
		builder.append(", itemKeyId=");
		builder.append(itemKeyId);
		builder.append("]");
		
		return builder.toString();
	}
}
