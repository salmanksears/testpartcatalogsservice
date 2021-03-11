package com.searshc.hspartcatalog.pojo;

public class ModelPart {
	
	private String docPageId;
	private String itemId;
	private String itemKeyId;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModelPart [docPageId=");
		builder.append(docPageId);
		builder.append(", itemId=");
		builder.append(itemId);
		builder.append(", itemKeyId=");
		builder.append(itemKeyId);
		builder.append("]");
	
		return builder.toString();
	}
	
	public String getDocPageId() {
		return docPageId;
	}
	public void setDocPageId(String docPageId) {
		this.docPageId = docPageId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemKeyId() {
		return itemKeyId;
	}
	public void setItemKeyId(String itemKeyId) {
		this.itemKeyId = itemKeyId;
	}
}
