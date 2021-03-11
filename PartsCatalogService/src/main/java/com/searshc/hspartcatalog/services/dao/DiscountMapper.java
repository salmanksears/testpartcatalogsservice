package com.searshc.hspartcatalog.services.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.searshc.hspartcatalog.domain.discount.ChannelPrice;
import com.searshc.hspartcatalog.domain.discount.ClientDiscount;

public interface DiscountMapper {

	static final String SELECT_MINIMUM_PRICE = "select min_prc_amt from prctmp_min_price";
	
	static final String SELECT_CLIENT_DISCOUNTS = "select bus_cd, dsc_typ, dsc_pc, lst_minus_typ from prctcd_sls_chn_dsc "
			+ "where bus_cd = #{businessCd} and sls_chn_typ_cd = 'WEBCOM' and dsc_pc > 0";
	
	static final String SELECT_ALL_CHANNEL_ITEM_PRICES = "select itm_no, prc_amt from prctsc_sls_chn where sls_chn_typ_cd = 'WEBCOM' and eff_dt <= sysdate";
	
	@Select(SELECT_MINIMUM_PRICE)
	BigDecimal getMinimumPrice() throws Exception;

	@Select(SELECT_CLIENT_DISCOUNTS)
	@Results(value = { @Result(property = "businessCd", column = "bus_cd"),
			@Result(property = "discountType", column = "dsc_typ"),
			@Result(property = "discountPercent", column = "dsc_pc"),
			@Result(property = "listMinusType", column = "lst_minus_typ") })
	List<ClientDiscount> getDiscounts(@Param("businessCd") String businessCd) throws Exception;
	
	@Select(SELECT_ALL_CHANNEL_ITEM_PRICES)
	@Results(value = { @Result(property = "itemNo", column = "itm_no"),
			@Result(property = "price", column = "prc_amt") })
	List<ChannelPrice> getAllChannelItemPrices() throws Exception;
}