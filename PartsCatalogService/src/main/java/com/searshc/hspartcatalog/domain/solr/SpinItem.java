package com.searshc.hspartcatalog.domain.solr;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class SpinItem {

	@Field
	private List<String> catchAllSpin;
	
	@Field
	private String id;
	
	
	@Field
	private String spinBrandName;

	@Field
	private String spinModelCoreItemNumber;

	@Field
	private String spinModelDivItemSku;

	@Field
	private String spinModelManufacturerModelNumber;

	@Field
	private String spinModelPlsItem;

	@Field
	private String spinModelUpc;

	@Field
	private String spinModelVendorStockNumber;

	public List<String> getCatchAllSpin() {
		return catchAllSpin;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	public String getSpinBrandName() {
		return spinBrandName;
	}

	public String getSpinModelCoreItemNumber() {
		return spinModelCoreItemNumber;
	}

	public String getSpinModelDivItemSku() {
		return spinModelDivItemSku;
	}

	public String getSpinModelManufacturerModelNumber() {
		return spinModelManufacturerModelNumber;
	}

	public String getSpinModelPlsItem() {
		return spinModelPlsItem;
	}

	public String getSpinModelUpc() {
		return spinModelUpc;
	}

	public String getSpinModelVendorStockNumber() {
		return spinModelVendorStockNumber;
	}





	public void setCatchAllSpin(List<String> catchAllSpin) {
		this.catchAllSpin = catchAllSpin;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	public void setSpinBrandName(String spinBrandName) {
		this.spinBrandName = spinBrandName;
	}
	
	public void setSpinModelCoreItemNumber(String spinModelCoreItemNumber) {
		this.spinModelCoreItemNumber = spinModelCoreItemNumber;
	}
	
	public void setSpinModelDivItemSku(String spinModelDivItemSku) {
		this.spinModelDivItemSku = spinModelDivItemSku;
	}
	

	public void setSpinModelManufacturerModelNumber(
			String spinModelManufacturerModelNumber) {
		this.spinModelManufacturerModelNumber = spinModelManufacturerModelNumber;
	}
	
	
	public void setSpinModelPlsItem(String spinModelPlsItem) {
		this.spinModelPlsItem = spinModelPlsItem;
	}

	public void setSpinModelUpc(String spinModelUpc) {
		this.spinModelUpc = spinModelUpc;
	}

	public void setSpinModelVendorStockNumber(String spinModelVendorStockNumber) {
		this.spinModelVendorStockNumber = spinModelVendorStockNumber;
	}

	

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [id=").append(id).append(", spinModelDivItemSku=")
				.append(spinModelDivItemSku).append(", spinModelPlsItem=")
				.append(spinModelPlsItem).append(", spinModelCoreItemNumber=").append(spinModelCoreItemNumber)
				.append(", spinModelVendorStockNumber=").append(spinModelVendorStockNumber)
				.append(", spinModelManufacturerModelNumber=").append(spinModelManufacturerModelNumber)
				.append(", spinModelUpc=").append(spinModelUpc)
				.append(", spinBrandName=")
				.append(spinBrandName).append(", catchAllSpin=").append(catchAllSpin).append("]");
		return builder.toString();
	}
}
