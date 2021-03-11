/**
 * 
 */
package com.searshc.hspartcatalog.services.domain;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;
import com.searshc.hspartcatalog.domain.discount.ClientDiscount;


/**
* Title			:   Client
* Description	:	domain object for client.   
* @author		:	Abhishek Jain
*/
public class Client {

	private Integer clientId;
	private String clientName;
	private String clientKey;
	private Character activeFlag;
	private String commercialId;
	private Double priceDiscountPercent;	//NOT USED DISCOUNTS DEFINED VIA COST PLUS
	private String priceDiscountType;	    //NOT USED DISCOUNTS DEFINED VIA COST PLUS
	private String paymentMethodCd;
	private String paymentNo;
	private String businessCd;
	private Character orderSrcCd;
	private String orderTypeCd;
	private Double itemSellPriceLimit;
	private Character billAddressFlag;
	private String billName;
	private String billAddress1;
	private String billAddress2;
	private String billCity;
	private String billStateCd;
	private String billZipCd;
	private String billZipCdSuffix;
	private String billCountryCd;
	private Character shippingOverrideFlag;
	private Character calcTaxShippingFlag;
	private List<ClientDiscount> discounts;
	
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientKey() {
		return clientKey;
	}
	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}
	public Character getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(Character activeFlag) {
		this.activeFlag = activeFlag;
	}
	/**
	 * @return the commercialId
	 */
	public String getCommercialId() {
		return commercialId;
	}
	/**
	 * @param commercialId the commercialId to set
	 */
	public void setCommercialId(String commercialId) {
		this.commercialId = commercialId;
	}
	/**
	 * @return the priceDiscountPercent
	 */
	public Double getPriceDiscountPercent() {
		return priceDiscountPercent;
	}
	/**
	 * @param priceDiscountPercent the priceDiscountPercent to set
	 */
	public void setPriceDiscountPercent(Double priceDiscountPercent) {
		this.priceDiscountPercent = priceDiscountPercent;
	}
	/**
	 * @return the priceDiscountType
	 */
	public String getPriceDiscountType() {
		return priceDiscountType;
	}
	/**
	 * @param priceDiscountType the priceDiscountType to set
	 */
	public void setPriceDiscountType(String priceDiscountType) {
		this.priceDiscountType = priceDiscountType;
	}
	/**
	 * @return the paymentMethodCd
	 */
	public String getPaymentMethodCd() {
		return paymentMethodCd;
	}
	/**
	 * @param paymentMethodCd the paymentMethodCd to set
	 */
	public void setPaymentMethodCd(String paymentMethodCd) {
		this.paymentMethodCd = paymentMethodCd;
	}
	/**
	 * @return the paymentNo
	 */
	public String getPaymentNo() {
		return paymentNo;
	}
	/**
	 * @param paymentNo the paymentNo to set
	 */
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	/**
	 * @return the businessCd
	 */
	public String getBusinessCd() {
		return businessCd;
	}
	/**
	 * @param businessCd the businessCd to set
	 */
	public void setBusinessCd(String businessCd) {
		this.businessCd = businessCd;
	}
	/**
	 * @return the orderSrcCd
	 */
	public Character getOrderSrcCd() {
		return orderSrcCd;
	}
	/**
	 * @param orderSrcCd the orderSrcCd to set
	 */
	public void setOrderSrcCd(Character orderSrcCd) {
		this.orderSrcCd = orderSrcCd;
	}
	/**
	 * @return the orderTypeCd
	 */
	public String getOrderTypeCd() {
		return orderTypeCd;
	}
	/**
	 * @param orderTypeCd the orderTypeCd to set
	 */
	public void setOrderTypeCd(String orderTypeCd) {
		this.orderTypeCd = orderTypeCd;
	}
	/**
	 * @return the itemSellPriceLimit
	 */
	public Double getItemSellPriceLimit() {
		return itemSellPriceLimit;
	}
	/**
	 * @param itemSellPriceLimit the itemSellPriceLimit to set
	 */
	public void setItemSellPriceLimit(Double itemSellPriceLimit) {
		this.itemSellPriceLimit = itemSellPriceLimit;
	}
	/**
	 * @return the billAddressFlag
	 */
	public Character getBillAddressFlag() {
		return billAddressFlag;
	}
	/**
	 * @param billAddressFlag the billAddressFlag to set
	 */
	public void setBillAddressFlag(Character billAddressFlag) {
		this.billAddressFlag = billAddressFlag;
	}
	/**
	 * @return the billName
	 */
	public String getBillName() {
		return billName;
	}
	/**
	 * @param billName the billName to set
	 */
	public void setBillName(String billName) {
		this.billName = billName;
	}
	/**
	 * @return the billAddress1
	 */
	public String getBillAddress1() {
		return billAddress1;
	}
	/**
	 * @param billAddress1 the billAddress1 to set
	 */
	public void setBillAddress1(String billAddress1) {
		this.billAddress1 = billAddress1;
	}
	/**
	 * @return the billAddress2
	 */
	public String getBillAddress2() {
		return billAddress2;
	}
	/**
	 * @param billAddress2 the billAddress2 to set
	 */
	public void setBillAddress2(String billAddress2) {
		this.billAddress2 = billAddress2;
	}
	/**
	 * @return the billCity
	 */
	public String getBillCity() {
		return billCity;
	}
	/**
	 * @param billCity the billCity to set
	 */
	public void setBillCity(String billCity) {
		this.billCity = billCity;
	}
	/**
	 * @return the billStateCd
	 */
	public String getBillStateCd() {
		return billStateCd;
	}
	/**
	 * @param billStateCd the billStateCd to set
	 */
	public void setBillStateCd(String billStateCd) {
		this.billStateCd = billStateCd;
	}
	/**
	 * @return the billZipCd
	 */
	public String getBillZipCd() {
		return billZipCd;
	}
	/**
	 * @param billZipCd the billZipCd to set
	 */
	public void setBillZipCd(String billZipCd) {
		this.billZipCd = billZipCd;
	}
	/**
	 * @return the billZipCdSuffix
	 */
	public String getBillZipCdSuffix() {
		return billZipCdSuffix;
	}
	/**
	 * @param billZipCdSuffix the billZipCdSuffix to set
	 */
	public void setBillZipCdSuffix(String billZipCdSuffix) {
		this.billZipCdSuffix = billZipCdSuffix;
	}
	/**
	 * @return the billCountryCd
	 */
	public String getBillCountryCd() {
		return billCountryCd;
	}
	/**
	 * @param billCountryCd the billCountryCd to set
	 */
	public void setBillCountryCd(String billCountryCd) {
		this.billCountryCd = billCountryCd;
	}
	/**
	 * @return the shippingOverrideFlag
	 */
	public Character getShippingOverrideFlag() {
		return shippingOverrideFlag;
	}
	/**
	 * @param shippingOverrideFlag the shippingOverrideFlag to set
	 */
	public void setShippingOverrideFlag(Character shippingOverrideFlag) {
		this.shippingOverrideFlag = shippingOverrideFlag;
	}
	/**
	 * @return the calcTaxShippingFlag
	 */
	public Character getCalcTaxShippingFlag() {
		return calcTaxShippingFlag;
	}
	/**
	 * @param calcTaxShippingFlag the calcTaxShippingFlag to set
	 */
	public void setCalcTaxShippingFlag(Character calcTaxShippingFlag) {
		this.calcTaxShippingFlag = calcTaxShippingFlag;
	}
	public List<ClientDiscount> getDiscounts() {
		return discounts;
	}
	public void setDiscounts(List<ClientDiscount> discounts) {
		this.discounts = discounts;
	}
	
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("clientId", clientId);
		builder.append("clientName", clientName);
		builder.append("clientKey", clientKey);
		builder.append("activeFlag", activeFlag);
		builder.append("commercialId", commercialId);
		builder.append("priceDiscountPercent", priceDiscountPercent);
		builder.append("priceDiscountType", priceDiscountType);
		builder.append("paymentMethodCd", paymentMethodCd);
		builder.append("paymentNo", paymentNo);
		builder.append("businessCd", businessCd);
		builder.append("orderSrcCd", orderSrcCd);
		builder.append("orderTypeCd", orderTypeCd);
		builder.append("itemSellPriceLimit", itemSellPriceLimit);
		builder.append("billAddressFlag", billAddressFlag);
		builder.append("billName", billName);
		builder.append("billAddress1", billAddress1);
		builder.append("billAddress2", billAddress2);
		builder.append("billCity", billCity);
		builder.append("billStateCd", billStateCd);
		builder.append("billZipCd", billZipCd);
		builder.append("billZipCdSuffix", billZipCdSuffix);
		builder.append("billCountryCd", billCountryCd);
		builder.append("shippingOverrideFlag", shippingOverrideFlag);
		builder.append("calcTaxShippingFlag", calcTaxShippingFlag);
		builder.append("discounts", discounts);
		return builder.toString();
	}
	
}
