/**
 * 
 */
package com.searshc.hspartcatalog.services.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;

/**
* Title			:   ItemWithoutSubstitutes
* Description	:	domain object for ItemWithoutSubstitutes, this object does not contain the substitute part props 
* @author		:	Abhishek Jain
*/

@XmlType(name = "item", propOrder = {
		"itemId",
		"partNo",
		"productGroupId",
		"productGroupName",
		"itemDescription",
		"itemImageUrl",
		"itemAvailabilityStatus",
		"itemSellingPrice",
		/*"itemCost",
		"itemAddDate",*/
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
		"itemModelId",
		"itemSchematicId",
		"itemKeyId"
}, namespace="http://services.hspartcatalog.searshc.com/domain/cache")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemWithoutSubstitutes {
	
	/**
	 * This field is the unique 
	 * identifier for a particular 
	 * item. 
	 */
	@XmlElement
	private String itemId;
	
	/**
	 * The Part Number
	 * which identifies a product services part.
	 * This number is assigned by Sears.Part Number 
	 * is used in combination with the Div/Pls
	 */
	@XmlElement
	private String partNo;
	
	/**
	 * The number assigned
	 * to each product group
	 */
	@XmlElement
	private String productGroupId;
	
	/**
	 * The Product Group of
	 * the Item that the part is used on,
	 * such as RefrigeratorandFreezer, 
	 * Lawn and Garden, etc.
	 */
	@XmlElement
	private String productGroupName;
	
	/**
	 * Describes a Product Services Part, 
	 * e.g. aRepair Part,Consumable Part
	 */
	@XmlElement
	private String itemDescription;
	
	/**
	 * Item image URL
	 * that contains the image
	 * of an item
	 */
	@XmlElement
	private String itemImageUrl;
	
	/**
	 * The Part Availability Status.
	 */
	@XmlElement
	private String itemAvailabilityStatus;
	
	/**
	 * Selling Price
	 */
	@XmlElement
	private String itemSellingPrice;
	
	@XmlTransient
	private String itemCost;
	
	@XmlTransient
	private String itemAddDate;
	
	/**
	 * A flag that indicates if
	 * a part is an accessory part or not
	 */
	@XmlElement
	private String itemAccessoryIndicator;
	
	/**
	 * The Item disclosure info
	 */
	@XmlElement
	private String itemDisclosure;
	
	/**
	 * A code that indicates if a part is a 
	 * hazardous material part or not.
	 */
	@XmlElement
	private String hazardousMaterialCode;
	
	/**
	 * A flag that indicates if an 
	 * Item is a product or not:
	 */
	@XmlElement
	private String productIndicator;
	
	/**
	 * Additional comments for an Item
	 */
	@XmlElement
	private String itemComment;
	
	/**
	 * A flag that indicates the condition of an Item 
	 * - R: refurbished part - N: new part
	 */
	private String itemCondition;
	
	/**
	 * The part quantity that is suggested to buy
	 */
	@XmlElement
	private String itemSuggestedQty;
	
	/**
	 * A flag that indicates if 
	 * an Item is substituted to another item.
	 */
	@XmlElement
	private String subbedFlag;
	
	/**
	 * The Universal Product Code of an Item.
	 */
	@XmlElement
	private String upcCode;
	
	/**
	 * Item Synonym Name. 
	 * For example: 
	 * The item synonym name is THROTTLE WIRE 
	 * for part description of THROTTLE CABLE. 
	 */
	@XmlElement
	private String itemSynonymName;
	
	/**
	 * A flag that indicates if a 
	 * part number is an engine model or not.
	 */
	@XmlElement
	private String engineModelFlag;

	@XmlElement
	private String itemModelId;

	@XmlElement
	private String itemSchematicId;

	@XmlElement
	private String itemKeyId;
	
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

	public String getItemImageUrl() {
		return itemImageUrl;
	}

	public void setItemImageUrl(String itemImageUrl) {
		this.itemImageUrl = itemImageUrl;
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
		builder.append("itemImageUrl", itemImageUrl);
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
		builder.append("itemModelId", itemModelId);
		builder.append("itemSchematicId", itemSchematicId);
		builder.append("itemKeyId", itemKeyId);
		
		return builder.toString();
	}
	
}
