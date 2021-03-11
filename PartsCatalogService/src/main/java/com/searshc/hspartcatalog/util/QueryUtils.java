package com.searshc.hspartcatalog.util;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.ROUND_BRAC_CLOSE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.ROUND_BRAC_OPEN;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.SPACE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.SOLR_LAYER_EXCEPTION;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.AVAILABILITY_STATUS_EXCLUDE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.CONTENT_TYPE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_EXCLUSION_AVAILABILITY_STATUS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_EXCLUSION_PRODUCT_GROUP_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_EXCLUSION_RESTRICTIONS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.FILTER_ITEM_SELL_PRICE_LIMIT;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.OR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.SUBBED_FLAG_NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.SUBBED_FLAG_YES;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.SUB_AVAILABILITY_STATUS_EXCLUDE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.TO_FILTER;

import java.net.URLEncoder;
import java.util.List;

import com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST;
import com.searshc.hspartcatalog.exceptions.SolrException;
import com.searshc.hspartcatalog.services.domain.ItemAvailabilityStatusCcdb;
import com.searshc.hspartcatalog.services.domain.ItemRestrictionCcdb;
import com.searshc.hspartcatalog.services.domain.ProductGroupCcdb;

public class QueryUtils {

	public static StringBuilder getCommonCodeGetItem(List<ProductGroupCcdb> productGroupList, List<ItemRestrictionCcdb> 
	restrictions, String itemSellPriceLimit, List<ItemAvailabilityStatusCcdb> availabilityStatusList, boolean subbedFlag)throws SolrException{
	
	StringBuilder query = new StringBuilder();
	StringBuilder productGroupListSb = new StringBuilder();
	StringBuilder availabilityStatusSb = new StringBuilder();
	StringBuilder restrictionSb = new StringBuilder();
	
	try{
		if(productGroupList!=null && !productGroupList.isEmpty()){
			for(ProductGroupCcdb productGroup : productGroupList){
				productGroupListSb = productGroupListSb.append(productGroup.getProductGroupId()).append(SPACE);
			}
			query.append(FILTER_EXCLUSION_PRODUCT_GROUP_ID).append(URLEncoder.encode(ROUND_BRAC_OPEN + 
				productGroupListSb.toString() + ROUND_BRAC_CLOSE, CONTENT_TYPE));
		}
		
		if(restrictions!=null && !restrictions.isEmpty()){
			for(ItemRestrictionCcdb restriction : restrictions){
				restrictionSb = restrictionSb.append("*_").append(restriction.getRestrictionId()).append(SPACE);
			}
			query.append(FILTER_EXCLUSION_RESTRICTIONS).append(URLEncoder.encode(ROUND_BRAC_OPEN + 
				restrictionSb.toString() + ROUND_BRAC_CLOSE, CONTENT_TYPE));
		}
		if(itemSellPriceLimit != null)
			query.append(FILTER_ITEM_SELL_PRICE_LIMIT).append(URLEncoder.encode(itemSellPriceLimit, CONTENT_TYPE)).append(TO_FILTER);
		
		if(availabilityStatusList!=null && !availabilityStatusList.isEmpty()){
			for(ItemAvailabilityStatusCcdb availabilityStatus : availabilityStatusList){
				availabilityStatusSb = availabilityStatusSb.append(availabilityStatus.getItemAvailabilityStatusCd()).append(SPACE);
			}
			if(subbedFlag){
				query.append(FILTER);
				query.append(AVAILABILITY_STATUS_EXCLUDE).append(URLEncoder.encode(ROUND_BRAC_OPEN + availabilityStatusSb.toString() 
					+ ROUND_BRAC_CLOSE, CONTENT_TYPE)).append(SUBBED_FLAG_NO);			
				query.append(AVAILABILITY_STATUS_EXCLUDE).append(URLEncoder.encode(ROUND_BRAC_OPEN + availabilityStatusSb.toString() 
					+ ROUND_BRAC_CLOSE, CONTENT_TYPE)).append(SUBBED_FLAG_YES)
					.append(SUB_AVAILABILITY_STATUS_EXCLUDE).append(URLEncoder.encode(ROUND_BRAC_OPEN + availabilityStatusSb.toString() 
					+ ROUND_BRAC_CLOSE, CONTENT_TYPE)).append(OR);
				query.append(AVAILABILITY_STATUS_EXCLUDE).append(URLEncoder.encode(ROUND_BRAC_OPEN + availabilityStatusSb.toString() 
					+ ROUND_BRAC_CLOSE, CONTENT_TYPE)).append(SUBBED_FLAG_YES)
					.append(SOLR_CONST.SUB_AVAILABILITY_STATUS).append(URLEncoder.encode(ROUND_BRAC_OPEN + availabilityStatusSb.toString() 
					+ ROUND_BRAC_CLOSE, CONTENT_TYPE)).append(OR);
				query.append("(").append(SOLR_CONST.AVAILABILITY_STATUS).append(URLEncoder.encode(ROUND_BRAC_OPEN + availabilityStatusSb.toString() 
					+ ROUND_BRAC_CLOSE, CONTENT_TYPE)).append(SUBBED_FLAG_YES)
					.append(SUB_AVAILABILITY_STATUS_EXCLUDE).append(URLEncoder.encode(ROUND_BRAC_OPEN + availabilityStatusSb.toString() 
					+ ROUND_BRAC_CLOSE, CONTENT_TYPE)).append("))");
			}
			else
				query.append(FILTER_EXCLUSION_AVAILABILITY_STATUS).append(URLEncoder.encode(ROUND_BRAC_OPEN + 
				availabilityStatusSb.toString() + ROUND_BRAC_CLOSE, CONTENT_TYPE));
		}
		return query;
	}catch (Exception e) {
		throw new SolrException(SOLR_LAYER_EXCEPTION, e);
	}
	}
}
