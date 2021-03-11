package com.searshc.hspartcatalog.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.searshc.hspartcatalog.services.bo.PartDiscountBO;
import com.searshc.hspartcatalog.services.vo.response.FullSearchResponse;
import com.searshc.hspartcatalog.services.vo.response.GetItemDetailsResponse;
import com.searshc.hspartcatalog.services.vo.response.GetItemListResponse;
import com.searshc.hspartcatalog.services.vo.response.GetModelDetailsResponse;
import com.searshc.hspartcatalog.services.vo.response.ItemSearchResponse;

@Component
public class ItemDiscountHelper {
	
	//private static final BigDecimal HUNDRED = new BigDecimal(100);
	//private static final Logger LOGR = LoggerFactory.getLogger(ItemDiscountHelper.class);
	//private static final String ACTIVE = "Y";
	
	@Autowired
	private PartDiscountBO partDiscountBO;
	
	//@Autowired
	//private ClientConfigBO clientBo;
	
	/**
	 * Apply discount to <code>FullSearchResponse</code>
	 * @param response
	 * @param clientKey 
	 */
	public void applyDiscount(FullSearchResponse response, String clientKey){
		
		partDiscountBO.calculateDiscountPrice(response.getItems());
		
		/**
		for(Item item : response.getItems()){
			LOGR.debug("Price of {} before discount: {}",item.getItemId(), item.getItemSellingPrice());
			item.setItemSellingPrice(getPriceAfterDiscount(item.getItemSellingPrice(), clientKey));
			LOGR.debug("Price After discount: {}", item.getItemSellingPrice());
		}
		**/
	}
	
	public void applyDiscount(GetModelDetailsResponse response, String clientKey){
		partDiscountBO.calculateDiscountPrice(response.getItems());
	}
	
	/**
	 * Apply discount to <code>ItemSearchResponse</code>
	 * @param response
	 * @param clientKey 
	 */
	public void applyDiscount(ItemSearchResponse response, String clientKey) {
		
		//price discount percentage > 0.00
		//price discout type is NOT BLAMK
		
		partDiscountBO.calculateDiscountPrice(response.getItems());
		
		/**
		for(Item item : response.getItems()){
			LOGR.debug("Price of {} before discount: {}",item.getItemId(), item.getItemSellingPrice());
			item.setItemSellingPrice(getPriceAfterDiscount(item.getItemSellingPrice(), clientKey));
			LOGR.debug("Price After discount: {}", item.getItemSellingPrice());
		}
		**/
	}
	
	/**
	 * Apply discount to <code>GetItemDetailsResponse</code>
	 * @param response
	 * @param clientKey 
	 */
	public void applyDiscount(GetItemDetailsResponse response, String clientKey){
		
		partDiscountBO.calculateDiscountPrice(response.getItem());
		
		/**
		Item item = response.getItem();
		LOGR.debug("Price of {} before discount: {}",item.getItemId(), item.getItemSellingPrice());
		item.setItemSellingPrice(getPriceAfterDiscount(response.getItem().getItemSellingPrice(), clientKey));
		LOGR.debug("Price After discount: {}", item.getItemSellingPrice());
		**/
	}
	
	/**
	 * Apply discount to <code>GetItemListResponse</code>
	 * @param response
	 * @param clientKey 
	 */
	public void applyDiscount(GetItemListResponse response, String clientKey) {
		
		partDiscountBO.calculateDiscountPriceWithoutSubstitute(response.getItems());
		
		/**
		for(ItemWithoutSubstitutes item : response.getItems()){
			LOGR.debug("Price of {} before discount: {}",item.getItemId(), item.getItemSellingPrice());
			item.setItemSellingPrice(getPriceAfterDiscount(item.getItemSellingPrice(), clientKey));
			LOGR.debug("Price After discount: {}", item.getItemSellingPrice());
		}
		**/
	}
	
	/**
	 * Apply discount to <code>ItemWithoutSubstitutes</code>
	 * 
	 * @param itemWOSub
	 * @param clientKey 
	 */
	/**
	private String getPriceAfterDiscount(String itemPrice, String clientKey){
		Client clientDetail = null;
		try {
			clientDetail = clientBo.getClientDetailsByKey(clientKey);
			// if client is active
			boolean isClientActive = clientDetail.getActiveFlag()!=null && ACTIVE.equalsIgnoreCase(clientDetail.getActiveFlag().toString());
			// if discount percent is nonzero
			boolean isNonZeroPercent = clientDetail.getPriceDiscountPercent()!=null && clientDetail.getPriceDiscountPercent()>0.0;
			// if discount type is 'List Minus' (sell price less a percentage)
			boolean isValidDiscountType = PriceDiscountType.LIST_MINUS.getType().equalsIgnoreCase(clientDetail.getPriceDiscountType());
			if(isClientActive && isNonZeroPercent && isValidDiscountType) { 
				return calculatePrice(itemPrice, clientDetail.getPriceDiscountPercent());	
			}
			LOGR.info("Returning original price because one of the follwing is false :: isClientActive=[{}], isNonZeroPercent=[{}], isValidDiscountType=[{}]. Client key was  {}",
					isClientActive, isNonZeroPercent, isValidDiscountType, clientKey);
		} catch (Exception e) {
			// If the client cannot be found, exception should be logged and price should be returned as 0.0
			LOGR.warn("Could not get client details by key {}. Discount could not be applied on item price. Reurning price 0.0", clientKey);
			LOGR.error("Exception while loading client details. ",e);
			return "0.0";
		}
		return itemPrice;
	}
	**/
	
	/**
	 * Performs the actual calculation newPrice = oldPrice - discountAmount and returns the newPrice
	 * @param price
	 * @param percentDiscount
	 * @return
	 */
	/**
	public String calculatePrice(String price, Double percentDiscount){
		
		BigDecimal bdPercent = new BigDecimal(percentDiscount);
		BigDecimal bdPrice = new BigDecimal(price);
		
		BigDecimal result = bdPrice.multiply(BigDecimal.ONE.subtract(bdPercent.divide(HUNDRED))).setScale(2,RoundingMode.HALF_UP);
		return result.toString();
	}
	**/
}
