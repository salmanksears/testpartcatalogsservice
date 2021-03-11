package com.searshc.hspartcatalog.services.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS;
import com.searshc.hspartcatalog.exceptions.CcdbException;
import com.searshc.hspartcatalog.exceptions.SolrException;
import com.searshc.hspartcatalog.services.bo.ClientConfigBO;
import com.searshc.hspartcatalog.services.bo.SolrServiceInvokerBO;
import com.searshc.hspartcatalog.services.service.PartsCatalogService;
import com.searshc.hspartcatalog.services.vo.base.BaseResponse;
import com.searshc.hspartcatalog.services.vo.request.ItemSearchRequest;
import com.searshc.hspartcatalog.services.vo.response.ItemSearchResponse;
import com.searshc.hspartcatalog.util.ClientUtils;
import com.searshc.hspartcatalog.validator.ItemSearchRequestValidator;
import com.searshc.hspartcatalog.validator.Validator;
import com.searshc.hspartcatalog.validator.ValidatorContainer;
@RunWith(PowerMockRunner.class)
@PrepareForTest( ClientUtils.class )
public class PartsCatalogServiceImpl_itemSearch_Test {
	private PartsCatalogService partsCatalogService;
	private BaseResponse baseResponse;
	private ValidatorContainer validatorContainer;
	@SuppressWarnings("rawtypes")
	private Map<String, Validator> validatorMap;
	private ItemSearchRequestValidator itemSearchRequestValidator;
	
	private ClientConfigBO clientConfigBO;
	private SolrServiceInvokerBO solrServiceInvokerBO;
	
	UriInfo uriInfo;

	@SuppressWarnings("rawtypes")
	@Before
	public void setUp() {
		partsCatalogService = new PartsCatalogServiceImpl();
		validatorContainer = new ValidatorContainer();
		uriInfo = PowerMock.createMock(UriInfo.class);
		clientConfigBO = PowerMock.createMock(ClientConfigBO.class);
		solrServiceInvokerBO = PowerMock.createMock(SolrServiceInvokerBO.class);
		
		validatorMap = new HashMap<String, Validator>();
		itemSearchRequestValidator = new ItemSearchRequestValidator();
		ReflectionTestUtils.setField(itemSearchRequestValidator, "maxRowsDefault", "10");
		ReflectionTestUtils.setField(itemSearchRequestValidator, "maxRowsCacheDefault", "10");
		
		validatorMap.put("ItemSearchRequestValidator", itemSearchRequestValidator);
		validatorContainer.setValidatorMap(validatorMap);
		ReflectionTestUtils.setField(partsCatalogService, "validatorContainer", validatorContainer);
		ReflectionTestUtils.setField(partsCatalogService, "clientConfigBO", clientConfigBO);
		ReflectionTestUtils.setField(partsCatalogService, "solrServiceInvokerBO", solrServiceInvokerBO);
		
		ReflectionTestUtils.setField(partsCatalogService, "maxRowsCacheDefault", "10");
		ReflectionTestUtils.setField(partsCatalogService, "maxRowsDefault", "10");
		
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testItemSearchSuccesWithNumberFoundZero() throws CcdbException, SolrException {
		MultivaluedMap<String, String> params = new MultivaluedHashMap<String, String>();
		//params.add(REQUEST_FIELDS.CLIENT_KEY, "12345");
		params.add(REQUEST_FIELDS.ITEM_ID, "1234566");
		params.add(REQUEST_FIELDS.PART_NO, "098765");
		params.add(REQUEST_FIELDS.PRODUCT_GROUP_ID, "1234");
		//params.add(REQUEST_FIELDS.RESPONSE_FORMAT, "application/xml");
		params.add(REQUEST_FIELDS.FORMATTED, "N");
		params.add(REQUEST_FIELDS.STARTING_ROW, "345678");
		params.add(REQUEST_FIELDS.MAX_ROWS, "10");
		params.add(REQUEST_FIELDS.SORT_BY, "itemId asc");
		
		
		ItemSearchResponse itemSearchResponse = new ItemSearchResponse();
		itemSearchResponse.setNumFound("0");
		
		PowerMock.mockStatic(ClientUtils.class);
		EasyMock.expect(uriInfo.getQueryParameters()).andReturn(params);
		EasyMock.expect(ClientUtils.isClientActive()).andReturn(true);
		EasyMock.expect(ClientUtils.getClientId()).andReturn(new Integer(12345));
		EasyMock.expect(clientConfigBO.getProductGroup(new Integer(12345))).andReturn(null);
		EasyMock.expect(clientConfigBO.getRestrictions(new Integer(12345))).andReturn(null);
		EasyMock.expect(clientConfigBO.getItemAvailabilityStatus(new Integer(12345))).andReturn(null);
		EasyMock.expect(clientConfigBO.getItemSellingPriceLimit(new Integer(12345))).andReturn(null);
		EasyMock.expect(clientConfigBO.getItemSellingPriceLimit(new Integer(12345))).andReturn(null);
		EasyMock.expect(solrServiceInvokerBO.itemSearch(EasyMock.anyObject(ItemSearchRequest.class), 
				EasyMock.anyObject(List.class), EasyMock.anyObject(List.class), EasyMock.anyObject(List.class), 
				EasyMock.anyObject(BigDecimal.class))).andReturn(itemSearchResponse);
		PowerMock.replayAll();	
		
		baseResponse=partsCatalogService.itemSearch("12345", "1234566", "098765", "1234", "application/xml", "N", "345678", "10", "itemId asc", uriInfo);
		Assert.assertEquals("000", baseResponse.getResponseCode());	 // TODO review comment: need to do object assertion
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testItemSearchSuccesWithNumberNotFoundZero() throws CcdbException, SolrException {
		MultivaluedMap<String, String> params = new MultivaluedHashMap<String, String>();
		//params.add(REQUEST_FIELDS.CLIENT_KEY, "12345");
		params.add(REQUEST_FIELDS.ITEM_ID, "1234566");
		params.add(REQUEST_FIELDS.PART_NO, "098765");
		params.add(REQUEST_FIELDS.PRODUCT_GROUP_ID, "1234");
		//params.add(REQUEST_FIELDS.RESPONSE_FORMAT, "application/xml");
		params.add(REQUEST_FIELDS.FORMATTED, "N");
		params.add(REQUEST_FIELDS.STARTING_ROW, "345678");
		params.add(REQUEST_FIELDS.MAX_ROWS, "10");
		params.add(REQUEST_FIELDS.SORT_BY, "itemId asc");
		
		
		ItemSearchResponse itemSearchResponse = new ItemSearchResponse();
		itemSearchResponse.setNumFound("1");
		
		PowerMock.mockStatic(ClientUtils.class);
		EasyMock.expect(uriInfo.getQueryParameters()).andReturn(params);
		EasyMock.expect(ClientUtils.isClientActive()).andReturn(true);
		EasyMock.expect(ClientUtils.getClientId()).andReturn(new Integer(12345));
		EasyMock.expect(clientConfigBO.getProductGroup(new Integer(12345))).andReturn(null);
		EasyMock.expect(clientConfigBO.getRestrictions(new Integer(12345))).andReturn(null);
		EasyMock.expect(clientConfigBO.getItemAvailabilityStatus(new Integer(12345))).andReturn(null);
		EasyMock.expect(clientConfigBO.getItemSellingPriceLimit(new Integer(12345))).andReturn(null);
		EasyMock.expect(clientConfigBO.getItemSellingPriceLimit(new Integer(12345))).andReturn(null);
		EasyMock.expect(solrServiceInvokerBO.itemSearch(EasyMock.anyObject(ItemSearchRequest.class), 
				EasyMock.anyObject(List.class), EasyMock.anyObject(List.class), EasyMock.anyObject(List.class), 
				EasyMock.anyObject(BigDecimal.class))).andReturn(itemSearchResponse);
		PowerMock.replayAll();	
		
		baseResponse=partsCatalogService.itemSearch("12345", "1234566", "098765", "1234", "application/xml", "N", "345678", "10", "itemId asc", uriInfo);
		Assert.assertEquals("000", baseResponse.getResponseCode());	// TODO review comment: need to do object assertion
	}
	
	@Test
	public void testItemSearchFailure() {
		PowerMock.mockStatic(ClientUtils.class);
		EasyMock.expect(ClientUtils.isClientActive()).andReturn(false);
		PowerMock.replayAll();		
		BaseResponse response = partsCatalogService.itemSearch("12345", "1234566", "098765", "1234", "application/xml", "N", "345678", "10", "itemId asc", uriInfo);
		Assert.assertEquals("200", response.getResponseCode()); // TODO review comment: need to do object assertion
		Assert.assertTrue(response.getMessages().size()>0);
			
	}
}
