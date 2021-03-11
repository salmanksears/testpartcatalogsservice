package com.searshc.hspartcatalog.services.domain;

public class Accessory {

	private String productGroupId;
	private String supplierId;
	private String partNo;
	private String partDs;
	private String imageUrl;
	private int rank;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Accessory [productGroupId=").append(productGroupId).append(", supplierId=").append(supplierId)
				.append(", partNo=").append(partNo).append(", partDs=").append(partDs).append(", imageUrl=")
				.append(imageUrl).append(", rank=").append(rank)
				.append("]");
		return builder.toString();
	}

	public String getProductGroupId() {
		return productGroupId;
	}

	public void setProductGroupId(String productGroupId) {
		this.productGroupId = productGroupId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public String getPartDs() {
		return partDs;
	}

	public void setPartDs(String partDs) {
		this.partDs = partDs;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
}
