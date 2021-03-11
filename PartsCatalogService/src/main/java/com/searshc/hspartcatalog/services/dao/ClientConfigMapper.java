package com.searshc.hspartcatalog.services.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.searshc.hspartcatalog.services.domain.BrandCcdb;
import com.searshc.hspartcatalog.services.domain.Client;
import com.searshc.hspartcatalog.services.domain.ItemAttributeCcdb;
import com.searshc.hspartcatalog.services.domain.ItemAvailabilityStatusCcdb;
import com.searshc.hspartcatalog.services.domain.ItemRestrictionCcdb;
import com.searshc.hspartcatalog.services.domain.ProductGroupCcdb;
import com.searshc.hspartcatalog.services.domain.ProductTypeCcdb;

public interface ClientConfigMapper {
	
	static final String SELECT_CLIENT_ID = "select client.CLIENT_ID from CLIENT client where client.CLIENT_KEY = #{clientKey}";

	static final String IS_CLIENT_VALID_AND_ACTIVE = "select client.ACTIVE_FLAG from CLIENT client where client.CLIENT_KEY = #{clientKey}";

	static final String SELECT_BRAND_IDS = "select brand.BRAND_CD from BRAND brand, BRAND_ASSN assn where brand.BRAND_ID = assn.BRAND_ID and assn.CLIENT_ID=#{clientId}";

	static final String SELECT_BRANDS = "select brand.BRAND_DESC from BRAND brand, BRAND_ASSN assn where brand.BRAND_ID = assn.BRAND_ID and assn.CLIENT_ID=#{clientId}";

	static final String SELECT_DIVS = "select distinct(concat(divPls.DIV, divPls.PLS)) as divPls from DIV_PLS divPls, DIV_PLS_ASSN assn where divPls.DIV_PLS_ID = assn.DIV_PLS_ID and assn.CLIENT_ID=#{clientId}";

	static final String SELECT_ATTRIBUTES = "select attr.ATTRIBUTE_ID from ITEM_ATTRIBUTE attr, ITEM_ATTRIBUTE_ASSN assn where attr.ITEM_ATTRIBUTE_ID = assn.ITEM_ATTRIBUTE_ID and assn.CLIENT_ID=#{clientId}";

	static final String SELECT_AVAILABILITY_STATUS = "select avail.ITEM_AVAILABILITY_STATUS_CD from ITEM_AVAILABILITY_STATUS avail, ITEM_AVAILABILITY_STATUS_ASSN assn where avail.ITEM_AVAILABILITY_STATUS_ID = assn.ITEM_AVAILABILITY_STATUS_ID and assn.CLIENT_ID=#{clientId}";

	static final String SELECT_RESTRICTIONS = "select res.restriction_id from ITEM_RESTRICTION res, ITEM_RESTRICTION_ASSN assn where res.item_restriction_id = assn.item_restriction_id and assn.client_id=#{clientId}";

	static final String SELECT_PRODUCT_TYPE = "select productType.PRODUCT_TYPE_CD from PRODUCT_TYPE productType, PRODUCT_TYPE_ASSN assn where productType.PRODUCT_TYPE_ID = assn.PRODUCT_TYPE_ID and assn.CLIENT_ID=#{clientId}";

	static final String SELECT_ITEM_SELL_PRICE_LIMIT = "select client.ITEM_SELL_PRICE_LIMIT from CLIENT client where client.CLIENT_ID = #{clientId}";

	static final String SELECT_CLIENT_DETAILS = "select client.CLIENT_ID, client.CLIENT_NAME, client.CLIENT_KEY, client.ACTIVE_FLAG from CLIENT client where client.CLIENT_KEY = #{clientKey}";
	
	static final String SELECT_CLIENT_ALL_DETAILS = "select * from CLIENT client where client.CLIENT_KEY = #{clientKey}";

	@Select(SELECT_CLIENT_ALL_DETAILS)
	@Results(value = { @Result(property = "clientId", column = "CLIENT_ID"),
			@Result(property = "clientName", column = "CLIENT_NAME"),
			@Result(property = "clientKey", column = "CLIENT_KEY"),
			@Result(property = "activeFlag", column = "ACTIVE_FLAG"),
			@Result(property = "commercialId", column = "COMMERCIAL_ID"),
			@Result(property = "priceDiscountPercent", column = "PRICE_DISCOUNT_PERCENTAGE"),
			@Result(property = "priceDiscountType", column = "PRICE_DISCOUNT_TYPE"),
			@Result(property = "paymentMethodCd", column = "PAYMENT_METHOD_CD"),
			@Result(property = "paymentNo", column = "PAYMENT_NO"),
			@Result(property = "businessCd", column = "BUSINESS_CD"),
			@Result(property = "orderSrcCd", column = "ORDER_SOURCE_CD"),
			@Result(property = "orderTypeCd", column = "ORDER_TYPE_CD"),
			@Result(property = "itemSellPriceLimit", column = "ITEM_SELL_PRICE_LIMIT"),
			@Result(property = "billAddressFlag", column = "BILL_ADDRESS_FLAG"),
			@Result(property = "billName", column = "BILL_NAME"),
			@Result(property = "billAddress1", column = "BILL_ADDRESS_1"),
			@Result(property = "billAddress2", column = "BILL_ADDRESS_2"),
			@Result(property = "billCity", column = "BILL_CITY"),
			@Result(property = "billStateCd", column = "BILL_STATE_CD"),
			@Result(property = "billZipCd", column = "BILL_ZIP_CD"),
			@Result(property = "billZipCdSuffix", column = "BILL_ZIP_CD_SUFFIX"),
			@Result(property = "billCountryCd", column = "BILL_COUNTRY_CD"),
			@Result(property = "shippingOverrideFlag", column = "SHIPPING_OVERRIDE_FLAG"),
			@Result(property = "calcTaxShippingFlag", column = "CALC_TAX_SHIPPING_FLAG")
	})
	Client getClientByKey(@Param("clientKey") String clientKey);
	
	@Select(SELECT_CLIENT_ID)
	Integer getClientId(@Param("clientKey") String clientKey) throws Exception;
	
	@Select(IS_CLIENT_VALID_AND_ACTIVE)
	String isClientValidAndActive(@Param("clientKey") String clientKey) throws Exception;
	
	@Select(SELECT_BRAND_IDS)
	@Results(value = {@Result(property="brandId", column="brand_cd")})
	List<BrandCcdb> getBrandIds(Integer id) throws Exception;
	
	@Select(SELECT_BRANDS)
	@Results(value = {@Result(property="brand", column="brand_desc")})
	List<BrandCcdb> getBrands(Integer id) throws Exception;
	
	@Select(SELECT_DIVS)
	@Results(value = {
		@Result(property="productGroupId", column="divPls")
	})
	List<ProductGroupCcdb> getProductGroups(Integer id) throws Exception;
	
	@Select(SELECT_AVAILABILITY_STATUS)
	@Results(value = {@Result(property="itemAvailabilityStatusCd", column="item_availability_status_cd")})
	List<ItemAvailabilityStatusCcdb> getAvailabilityStatus(Integer id) throws Exception;
	
	@Select(SELECT_ATTRIBUTES)
	@Results(value = {@Result(property="attributeId", column="attribute_id")})
	List<ItemAttributeCcdb> getAttributes(Integer id) throws Exception;

	@Select(SELECT_RESTRICTIONS)
	@Results(value = {@Result(property="restrictionId", column="restriction_id")})
	List<ItemRestrictionCcdb> getRestrictions(Integer clientId) throws Exception;

	@Select(SELECT_PRODUCT_TYPE)
	@Results(value = {@Result(property="productTypeCd", column="product_type_cd")})
	List<ProductTypeCcdb> getProductType(Integer id) throws Exception;

	@Select(SELECT_ITEM_SELL_PRICE_LIMIT)
	BigDecimal getItemSellPriceLimit(@Param("clientId") Integer clientId) throws Exception;
	
}
