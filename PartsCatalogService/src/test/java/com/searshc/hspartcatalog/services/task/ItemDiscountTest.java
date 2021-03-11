package com.searshc.hspartcatalog.services.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.searshc.hspartcatalog.helper.ItemDiscountHelper;
import com.searshc.hspartcatalog.services.domain.Item;
import com.searshc.hspartcatalog.services.vo.response.GetItemDetailsResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextTest-beans.xml")
public class ItemDiscountTest {

	@Autowired
	private ItemDiscountHelper helper;
	
	private final String clientKey = "DcXeSK4QEvA3tblQdqEhoJCzvXgW5CeW";
	private final String sellingPrice = "99.99";
	
	@Test
	public void	testDiscountApplied(){
		GetItemDetailsResponse response = buildGetItemDetailResponse();
		helper.applyDiscount(response, clientKey);
	}

	private GetItemDetailsResponse buildGetItemDetailResponse() {
		// only setting required values
		GetItemDetailsResponse response = new GetItemDetailsResponse();
		Item item = new Item();
		response.setItem(item);
		item.setItemId("0460469030");
		item.setItemSellingPrice(sellingPrice);
		return response;
	}
}
