 package com.searshc.hspartcatalog.services.service.impl;

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
import com.searshc.hspartcatalog.services.domain.BrandCcdb;
import com.searshc.hspartcatalog.services.domain.ProductTypeCcdb;
import com.searshc.hspartcatalog.services.service.PartsCatalogService;
import com.searshc.hspartcatalog.services.vo.base.BaseResponse;
import com.searshc.hspartcatalog.services.vo.request.ModelSearchRequest;
import com.searshc.hspartcatalog.services.vo.response.ModelSearchResponse;
import com.searshc.hspartcatalog.util.ClientUtils;
import com.searshc.hspartcatalog.validator.ModelSearchRequestValidator;
import com.searshc.hspartcatalog.validator.Validator;
import com.searshc.hspartcatalog.validator.ValidatorContainer;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ClientUtils.class)
public class PartsCatalogServiceImpl_modelSearch_Test {
	private PartsCatalogService partsCatalogService;
	private BaseResponse baseResponse;
	private ValidatorContainer validatorContainer;
	@SuppressWarnings("rawtypes")
	private Map<String, Validator> validatorMap;
	private ModelSearchRequestValidator modelSearchRequestValidator;
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
		modelSearchRequestValidator = new ModelSearchRequestValidator();
		ReflectionTestUtils.setField(modelSearchRequestValidator, "maxRowsDefault", "10");
		ReflectionTestUtils.setField(modelSearchRequestValidator, "maxRowsCacheDefault", "10");
		
		validatorMap.put("ModelSearchRequestValidator", modelSearchRequestValidator);
		validatorContainer.setValidatorMap(validatorMap);
		ReflectionTestUtils.setField(partsCatalogService, "validatorContainer", validatorContainer);
		ReflectionTestUtils.setField(partsCatalogService, "clientConfigBO", clientConfigBO);
		ReflectionTestUtils.setField(partsCatalogService, "solrServiceInvokerBO", solrServiceInvokerBO);
		
		ReflectionTestUtils.setField(partsCatalogService, "maxRowsCacheDefault", "10");
		ReflectionTestUtils.setField(partsCatalogService, "maxRowsDefault", "10");
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testModelSearchSuccesWithNumberFoundZero() throws CcdbException, SolrException {
		MultivaluedMap<String, String> params = new MultivaluedHashMap<String, String>();		
		params.add(REQUEST_FIELDS.MODEL_NO, "098765");
		params.add(REQUEST_FIELDS.MODEL_ID, "1234566");
		params.add(REQUEST_FIELDS.BRAND, "1234");
		params.add(REQUEST_FIELDS.PARENT_PRODUCT_TYPE_ID, "23456");	
		params.add(REQUEST_FIELDS.PRODUCT_TYPE_ID, "345678");
		params.add(REQUEST_FIELDS.SUB_PRODUCT_TYPE_ID, "10102");
		params.add(REQUEST_FIELDS.FORMATTED, "Y");
		params.add(REQUEST_FIELDS.STARTING_ROW, "itemId asc");
		params.add(REQUEST_FIELDS.MAX_ROWS, "itemId asc");
		params.add(REQUEST_FIELDS.SORT_BY, "itemId asc");
		
		ModelSearchResponse modelSearchResponse = new ModelSearchResponse();
		modelSearchResponse.setNumFound("0");
		
		PowerMock.mockStatic(ClientUtils.class);
		EasyMock.expect(uriInfo.getQueryParameters()).andReturn(params);
		EasyMock.expect(ClientUtils.isClientActive()).andReturn(true);
		EasyMock.expect(ClientUtils.getClientId()).andReturn(new Integer(12345));
		EasyMock.expect(clientConfigBO.getBrandIds(new Integer(12345))).andReturn(null);
		EasyMock.expect(clientConfigBO.getProductType(new Integer(12345))).andReturn(null);		
		EasyMock.expect(solrServiceInvokerBO.modelSearch((ModelSearchRequest) EasyMock.anyObject(),
						(List<BrandCcdb>) EasyMock.anyObject(),(List<ProductTypeCcdb>) EasyMock.anyObject())).andReturn(modelSearchResponse);
		PowerMock.replayAll();	
		
		baseResponse=partsCatalogService.modelSearch("clientKey", "098765", "1234566", "1234", "23456", "345678", "10102", "application/xml", "Y", "1", "10", "modelId asc", uriInfo);
		Assert.assertEquals("000", baseResponse.getResponseCode());	
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testModelSearchSuccesWithNumberNotFoundZero() throws CcdbException, SolrException {
		MultivaluedMap<String, String> params = new MultivaluedHashMap<String, String>();		
		params.add(REQUEST_FIELDS.MODEL_NO, "098765");
		params.add(REQUEST_FIELDS.MODEL_ID, "1234566");
		params.add(REQUEST_FIELDS.BRAND, "1234");
		params.add(REQUEST_FIELDS.PARENT_PRODUCT_TYPE_ID, "23456");	
		params.add(REQUEST_FIELDS.PRODUCT_TYPE_ID, "345678");
		params.add(REQUEST_FIELDS.SUB_PRODUCT_TYPE_ID, "10102");
		params.add(REQUEST_FIELDS.FORMATTED, "Y");
		params.add(REQUEST_FIELDS.STARTING_ROW, "itemId asc");
		params.add(REQUEST_FIELDS.MAX_ROWS, "itemId asc");
		params.add(REQUEST_FIELDS.SORT_BY, "itemId asc");
		
		ModelSearchResponse modelSearchResponse = new ModelSearchResponse();
		modelSearchResponse.setNumFound("1");
		
		PowerMock.mockStatic(ClientUtils.class);
		EasyMock.expect(uriInfo.getQueryParameters()).andReturn(params);
		EasyMock.expect(ClientUtils.isClientActive()).andReturn(true);
		EasyMock.expect(ClientUtils.getClientId()).andReturn(new Integer(12345));
		EasyMock.expect(clientConfigBO.getBrandIds(new Integer(12345))).andReturn(null);
		EasyMock.expect(clientConfigBO.getProductType(new Integer(12345))).andReturn(null);		
		EasyMock.expect(solrServiceInvokerBO.modelSearch((ModelSearchRequest) EasyMock.anyObject(),
				(List<BrandCcdb>) EasyMock.anyObject(),(List<ProductTypeCcdb>) EasyMock.anyObject())).andReturn(modelSearchResponse);
		PowerMock.replayAll();	
		
		baseResponse=partsCatalogService.modelSearch("clientKey", "098765", "1234566", "1234", "23456", "345678", "10102", "application/xml", "Y", "1", "10", "modelId asc", uriInfo);
		Assert.assertEquals("000", baseResponse.getResponseCode());	
		
	}
	
	@Test
	public void testModelSearchFailure() {
		PowerMock.mockStatic(ClientUtils.class);
		EasyMock.expect(ClientUtils.isClientActive()).andReturn(false);
		PowerMock.replayAll();		
		BaseResponse response = partsCatalogService.modelSearch("clientKey", "098765", "1234566", "1234", "23456", "345678", "10102", "10101", "Y", "itemId asc", "itemId asc", "itemId asc", uriInfo);
		Assert.assertEquals("200", response.getResponseCode()); 
		Assert.assertTrue(response.getMessages().size()>0);
			
	}
}
