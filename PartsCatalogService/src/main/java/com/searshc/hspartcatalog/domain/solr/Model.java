package com.searshc.hspartcatalog.domain.solr;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class Model {
	
	@Field
	String id;
	
	@Field
	String brand;
	
	@Field
	String productTypeName;

	@Field
	String modelNo;

	@Field
	String modelDescription;
	
	@Field
	String formattedModelNo;

	@Field
	List<String> modelSchematics;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Model [id=");
		builder.append(id);
		builder.append(", brand=");
		builder.append(brand);
		builder.append(", productTypeName=");
		builder.append(productTypeName);
		builder.append(", modelNo=");
		builder.append(modelNo);
		builder.append(", modelDescription=");
		builder.append(modelDescription);
		builder.append(", modelSchematics=");
		builder.append(modelSchematics);
		builder.append("]");
		return builder.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	
	/**
	 * @return the formattedModelNo
	 */
	public String getFormattedModelNo() {
		return formattedModelNo;
	}

	/**
	 * @param formattedModelNo the formattedModelNo to set
	 */
	
	public void setFormattedModelNo(String formattedModelNo) {
		this.formattedModelNo = formattedModelNo;
	}

	public String getModelDescription() {
		return modelDescription;
	}

	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}

	public List<String> getModelSchematics() {
		return modelSchematics;
	}

	public void setModelSchematics(List<String> modelSchematics) {
		this.modelSchematics = modelSchematics;
	}
	
}
