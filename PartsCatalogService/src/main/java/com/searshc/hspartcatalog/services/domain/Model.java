/**
 * 
 */
package com.searshc.hspartcatalog.services.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;

/**
* Title			:   model
* Description	:	domain object for model 
* @author		:	Abhishek Jain
*/
@XmlType(name = "model", propOrder = {
		"modelId",
		"modelNo",
		"modelDescription",
		"brandId",
		"brand",
		"parentProductTypeId",
		"parentProductTypeName",
		"productTypeId",
		"productTypeName",
		"subProductTypeId",
		"subProductTypeName",
		"modelDiagramCount",
		"itemCount",
		"ownerManualURL",
		"installationManualURL",
		"fuzzySearchMatch",
		"attributes",
		"image"
}, namespace="http://services.hspartcatalog.searshc.com/domain/")
@XmlAccessorType(XmlAccessType.FIELD)
public class Model implements Comparable<Model>{
		
	/**
	 * This field is the unique 
	 * identifier for a particular model.
	 * It is a composed key and may contain characters.
	 */
	@XmlElement
	private String modelId;
	
	/**
	 * Model number of the Item that the Part is used on
	 */
	@XmlElement
	private String modelNo;
	
	/**
	 * The Model Description of an Item.
	 */
	@XmlElement
	private String modelDescription;
	
	/**
	 * Code that identifies a Brand Name
	 */
	@XmlElement
	private String brandId;
	
	/**
	 * The Manufacturer or Brand name 
	 * of a product (e.g., Whirlpool, Craftsman, Kenmore, etc.).
	 */
	@XmlElement
	private String brand;
	
	/**
	 * Code that identifies a Parent Product Type
	 */
	@XmlElement
	private String parentProductTypeId;
	
	/**
	 * The Parent Product Type Name
	 */
	@XmlElement
	private String parentProductTypeName;
	
	/**
	 * Code that identifies a Product Type
	 */
	@XmlElement
	private String productTypeId;
	
	/**
	 * The Product Type Name such as 
	 * Lawn Mowers, Refrigerators, Appliance, etc
	 */
	@XmlElement
	private String productTypeName;
	
	/**
	 * Code that identifies a Sub Product Type
	 */
	@XmlElement
	private String subProductTypeId;
	
	/**
	 * 
	 */
	@XmlElement
	private String subProductTypeName;
	
	/**
	 * The total number of Model Diagrams 
	 * for each model
	 */
	@XmlElement
	private String modelDiagramCount;
	
	/**
	 * The total number of Items for each model
	 */
	@XmlElement
	private String itemCount;
	
	/**
	 * The owner manual URL will be available 
	 * if the model product has an associated 
	 * owner's manual.
	 */
	@XmlElement
	private String ownerManualURL;
	
	/**
	 * The installation manual URL
	 * will be available if the model 
	 * product has an associated installation manual.
	 */
	@XmlElement
	private String installationManualURL;
	
	/**
	 * Indicator that the  model is a result of an fuzzy(inexact) search.
	 */
	@XmlElement
	private String fuzzySearchMatch;
	
	
	@XmlElementWrapper(name="attributes")
	@XmlElement(name="attribute")
	private List<ItemAttribute> attributes;
	
	@XmlElement
	private String image;
	
	public String getFuzzySearchMatch() {
		return fuzzySearchMatch;
	}

	public void setFuzzySearchMatch(String fuzzySearchMatch) {
		this.fuzzySearchMatch = fuzzySearchMatch;
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

	public String getOwnerManualURL() {
		return ownerManualURL;
	}

	public void setOwnerManualURL(String ownerManualURL) {
		this.ownerManualURL = ownerManualURL;
	}

	public String getInstallationManualURL() {
		return installationManualURL;
	}

	public void setInstallationManualURL(String installationManualURL) {
		this.installationManualURL = installationManualURL;
	}

	public List<ItemAttribute> getAttributes() {
		if(attributes!= null && !attributes.isEmpty())
			return attributes;
		else 
			return null;
	}

	public void setAttributes(List<ItemAttribute> attributes) {
		this.attributes = attributes;
	}
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public int compareTo(Model model) {
		return this.modelNo.compareTo(model.getModelNo());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
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
		builder.append("ownerManualURL", ownerManualURL);
		builder.append("installationManualURL", installationManualURL);
		builder.append("fuzzySearchMatch", fuzzySearchMatch);
		builder.append("attributes", attributes);
		builder.append("image", image);
		
		return builder.toString();
	}
}
