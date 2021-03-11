package com.searshc.hspartcatalog.services.bo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.searshc.hspartcatalog.cache.PriceOverrideCache;
import com.searshc.hspartcatalog.domain.discount.ClientDiscount;
import com.searshc.hspartcatalog.services.domain.Client;
import com.searshc.hspartcatalog.services.domain.Item;
import com.searshc.hspartcatalog.services.domain.ItemWithoutSubstitutes;
import com.searshc.hspartcatalog.util.ClientUtils;
import com.searshc.hspartcatalog.util.DiscountConstants;
import com.searshc.hspartcatalog.util.DiscountUtils;

@Component
public class PartDiscountBO implements DiscountConstants {
	
	@Autowired
	PriceOverrideCache priceOverrideCache;
	
	private static final boolean ITEM = false;
	private static final boolean SUB_ITEM = true;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PartDiscountBO.class);

	public void calculateDiscountPrice(Item item) {
		List<Item> items = new ArrayList<Item>();
		items.add(item);
		calculateDiscountPrice(items);
	}
	
	public void calculateDiscountPriceWithoutSubstitute(List<ItemWithoutSubstitutes> items) {
		
		// find the CostPlus and ListMinus percentages from database
		Client client = ClientUtils.getClient();
		List<ClientDiscount> discounts = client.getDiscounts();
		if(discounts == null || discounts.size() == 0)
			return;
		
		// Get minimum price from database
		BigDecimal minPrice = priceOverrideCache.getMinimumPrice();

		boolean isItemPriceOverride = false;
		boolean isCostPlus = false;
		boolean isListMinus = false;

		String costPlusPercentStr = null;
		String listMinusPercentStr = null;
		String listMinus2PercentStr = null;
	
		for (ClientDiscount discount : discounts) {
			if (discount.isCostPlus()) {
				costPlusPercentStr = discount.getDiscountPercent();
				isCostPlus = true;
			} else if (discount.isListMinus()) {
				listMinusPercentStr = discount.getDiscountPercent();
				isListMinus = true;
			} else if (discount.isListMinus2()) {
				listMinus2PercentStr = discount.getDiscountPercent();
			}
		}
        
		if(isCostPlus == false && isListMinus == false)
			return;
		
		for (ItemWithoutSubstitutes item : items) {
			
			isItemPriceOverride = calculateByPriceOverride(item, minPrice);
			
			if (isItemPriceOverride) {
				continue;
			}	
			else if (isCostPlus) {
				calculateByItemCostPlus(item, costPlusPercentStr, listMinusPercentStr, listMinus2PercentStr, minPrice);
			} else if (isListMinus) {
				calculateByListMinus(item, listMinusPercentStr);
			}
		}
	}
	
	public void calculateDiscountPrice(List<Item> items) {

		// find the CostPlus and ListMinus percentages from database
		Client client = ClientUtils.getClient();
		List<ClientDiscount> discounts = client.getDiscounts();
		if(discounts == null || discounts.size() == 0)
			return;
		
		// Get minimum price from database
		BigDecimal minPrice = priceOverrideCache.getMinimumPrice();

		boolean isItemPriceOverride = false;
		boolean isSubItemPriceOverride = false;
		boolean isCostPlus = false;
		boolean isListMinus = false;

		String costPlusPercentStr = null;
		String listMinusPercentStr = null;
		String listMinus2PercentStr = null;
	
		for (ClientDiscount discount : discounts) {
			if (discount.isCostPlus()) {
				costPlusPercentStr = discount.getDiscountPercent();
				isCostPlus = true;
			} else if (discount.isListMinus()) {
				listMinusPercentStr = discount.getDiscountPercent();
				isListMinus = true;
			} else if (discount.isListMinus2()) {
				listMinus2PercentStr = discount.getDiscountPercent();
			}
		}
        
		if(isCostPlus == false && isListMinus == false)
			return;
		
		boolean isSub = false;
		
		for (Item item : items) {
			
			isSub = isSubbed(item);
			
			isItemPriceOverride = calculateByPriceOverride(item, minPrice, ITEM);
			if(isSub)
				isSubItemPriceOverride = calculateByPriceOverride(item, minPrice, SUB_ITEM);
			
			if (isItemPriceOverride || isSubItemPriceOverride) {
				isSubItemPriceOverride = false;	// not checked every time...
				continue;
			}	
			else if (isCostPlus) {
				calculateByItemCostPlus(item, costPlusPercentStr, listMinusPercentStr, listMinus2PercentStr, minPrice, ITEM);
				if(isSub)
					calculateByItemCostPlus(item, costPlusPercentStr, listMinusPercentStr, listMinus2PercentStr,
						minPrice, SUB_ITEM);
			} else if (isListMinus) {
				calculateByListMinus(item, listMinusPercentStr, ITEM);
				if(isSub)
					calculateByListMinus(item, listMinusPercentStr, SUB_ITEM);
			}
		}
	}

	private boolean calculateByPriceOverride(Item item, BigDecimal minPrice, boolean isSub) {

		String itemId = getItemId(item, isSub);
		
		BigDecimal overridePrice = priceOverrideCache.getPrice(itemId);
		if(overridePrice == null)
			return false;
		
		if (DiscountUtils.isValidOverride(overridePrice, minPrice)) {
			setItemSellingPrice(item, overridePrice, isSub);
			return true;
		}

		return false;
	}
	
	private boolean calculateByPriceOverride(ItemWithoutSubstitutes item, BigDecimal minPrice) {

		String itemId = item.getItemId();
		
		BigDecimal overridePrice = priceOverrideCache.getPrice(itemId);
		if(overridePrice == null)
			return false;
		
		if (DiscountUtils.isValidOverride(overridePrice, minPrice)) {
			String str = DiscountUtils.bigDecimalToString(overridePrice);
			item.setItemSellingPrice(str);
			return true;
		}

		return false;
	}
	
	private void calculateByItemCostPlus(Item item, String cppPercentString, String lmpPercentString,
			String lmp2PercentString, BigDecimal minPrice, boolean isSub) {

		BigDecimal itemCost = null;
		BigDecimal itemSellPrice = null;
		BigDecimal itemCostPlusPrice = null;
		BigDecimal itemListMinusPrice = null;
		BigDecimal itemListMinus2Price = null;

		itemCost = getItemCost(item, isSub);
		if(itemCost == null)
			return;
		
		itemCostPlusPrice = itemCost.divide(
				(new BigDecimal(1).subtract(new BigDecimal(cppPercentString).divide(ONE_HUNDRED))), 2,
				RoundingMode.CEILING);

		// 2. >>> If Min price > CPP use it....
		if (calculateByMinPrice(item, itemCostPlusPrice, minPrice, isSub))
			return;
		
		itemSellPrice = getItemSellingPrice(item, isSub);
		if(itemSellPrice == null)
			return;
		
		itemListMinusPrice = itemSellPrice
				.subtract(itemSellPrice.multiply(new BigDecimal(lmpPercentString).divide((ONE_HUNDRED))));

		itemListMinus2Price = itemSellPrice
				.subtract(itemSellPrice.multiply(new BigDecimal(lmp2PercentString).divide((ONE_HUNDRED))));

		if (itemListMinusPrice != null && itemCostPlusPrice.compareTo(itemListMinusPrice) == 1) {
			if (itemListMinusPrice.compareTo(itemCost) == 1) {
				setItemSellingPrice(item, itemListMinusPrice, isSub);
			}
		}
		else {
			if (itemListMinus2Price != null && itemCostPlusPrice.compareTo(itemListMinus2Price) == 1)
				setItemSellingPrice(item, itemCostPlusPrice, isSub);
			else
				setItemSellingPrice(item, itemListMinus2Price, isSub);
		}
	}
	
	
	private void calculateByItemCostPlus(ItemWithoutSubstitutes item, String cppPercentString, String lmpPercentString,
			String lmp2PercentString, BigDecimal minPrice) {

		BigDecimal itemCost = null;
		BigDecimal itemSellPrice = null;
		BigDecimal itemCostPlusPrice = null;
		BigDecimal itemListMinusPrice = null;
		BigDecimal itemListMinus2Price = null;

		itemCost = DiscountUtils.stringToBigDecimal(item.getItemCost());
		if(itemCost == null)
			return;
		
		itemCostPlusPrice = itemCost.divide(
				(new BigDecimal(1).subtract(new BigDecimal(cppPercentString).divide(ONE_HUNDRED))), 2,
				RoundingMode.CEILING);

		// 2. >>> If Min price > CPP use it....
		if (calculateByMinPrice(item, itemCostPlusPrice, minPrice))
			return;
		
		itemSellPrice = DiscountUtils.stringToBigDecimal(item.getItemSellingPrice());
		if(itemSellPrice == null)
			return;
		
		itemListMinusPrice = itemSellPrice
				.subtract(itemSellPrice.multiply(new BigDecimal(lmpPercentString).divide((ONE_HUNDRED))));

		itemListMinus2Price = itemSellPrice
				.subtract(itemSellPrice.multiply(new BigDecimal(lmp2PercentString).divide((ONE_HUNDRED))));

		if (itemListMinusPrice != null && itemCostPlusPrice.compareTo(itemListMinusPrice) == 1) {
			if (itemListMinusPrice.compareTo(itemCost) == 1) {
				item.setItemSellingPrice(DiscountUtils.bigDecimalToString(itemListMinusPrice));
			}
		}
		else {
			if (itemListMinus2Price != null && itemCostPlusPrice.compareTo(itemListMinus2Price) == 1)
				item.setItemSellingPrice(DiscountUtils.bigDecimalToString(itemCostPlusPrice));
			else
				item.setItemSellingPrice(DiscountUtils.bigDecimalToString(itemListMinus2Price));
		}
	}
	
	private boolean calculateByMinPrice(Item item, BigDecimal itemCostPlusPrice, BigDecimal minPrice, boolean isSub) {

		if (minPrice.compareTo(itemCostPlusPrice) >= 0) {
			setItemSellingPrice(item, minPrice, isSub);
			return true;
		}

		return false;
	}
	
	private boolean calculateByMinPrice(ItemWithoutSubstitutes item, BigDecimal itemCostPlusPrice, BigDecimal minPrice) {

		if (minPrice.compareTo(itemCostPlusPrice) >= 0) {
			item.setItemSellingPrice(DiscountUtils.bigDecimalToString(minPrice));
			return true;
		}

		return false;
	}

	private void calculateByListMinus(Item item, String lmpPercentString, boolean isSub) {
		
		BigDecimal sellingPrice = getItemSellingPrice(item, isSub);
		if(sellingPrice == null)
			return;
		
		BigDecimal discount = new BigDecimal(lmpPercentString).divide(ONE_HUNDRED, 2, RoundingMode.CEILING);
		
		BigDecimal listMinusPrice = sellingPrice.subtract(sellingPrice.multiply(discount));
		setItemSellingPrice(item, listMinusPrice, isSub);
	}
	
	private void calculateByListMinus(ItemWithoutSubstitutes item, String lmpPercentString) {
		
		BigDecimal sellingPrice = DiscountUtils.stringToBigDecimal(item.getItemSellingPrice());
		if(sellingPrice == null)
			return;
		
		BigDecimal discount = new BigDecimal(lmpPercentString).divide(ONE_HUNDRED, 2, RoundingMode.CEILING);
		
		BigDecimal listMinusPrice = sellingPrice.subtract(sellingPrice.multiply(discount));
		item.setItemSellingPrice(DiscountUtils.bigDecimalToString(listMinusPrice));
	}
	
	private BigDecimal getItemSellingPrice(Item item, boolean isSub) {
		
		BigDecimal bd = null;
		
		if(isSub)
			bd = DiscountUtils.stringToBigDecimal(item.getSubItemSellingPrice());
		else
			bd= DiscountUtils.stringToBigDecimal(item.getItemSellingPrice());
	
		return bd;
	}
	
	private void setItemSellingPrice(Item item, BigDecimal price, boolean isSub) {
		
		String str = DiscountUtils.bigDecimalToString(price);
		if(str == null)
			return;
		
		if(isSub)
			item.setSubItemSellingPrice(str);
		else
			item.setItemSellingPrice(str);
	}

	private BigDecimal getItemCost(Item item, boolean isSub) {
		
		BigDecimal bd = null;
		
		if(isSub)
			bd = DiscountUtils.stringToBigDecimal(item.getSubItemCost());
		else
			bd= DiscountUtils.stringToBigDecimal(item.getItemCost());
	
		return bd;
	}
	
	private String getItemId(Item item, boolean isSub) {
		
		String itemId = null;
		
		if(isSub)
			itemId = item.getSubItemId();
		else
			itemId = item.getItemId();
	
		return itemId;
	}
	
	private boolean isSubbed(Item item) {
		return StringUtils.isNotBlank(item.getSubItemId());
	}
}
