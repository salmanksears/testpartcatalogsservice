package com.searshc.hspartcatalog.services.bo;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.searshc.hspartcatalog.domain.solr.SpinItem;
import com.searshc.hspartcatalog.exceptions.SolrException;
import com.searshc.hspartcatalog.services.domain.BrandCcdb;
import com.searshc.hspartcatalog.services.domain.ProductTypeCcdb;
import com.searshc.hspartcatalog.services.vo.request.ModelSearchRequest;

public class ModelQueryBuilderTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testModelNoAndBrandSearch() {
		ModelSearchRequest request = new ModelSearchRequest();
		request.setMaxRows("100");
		request.setModelNo("ABCDEFGHIJKLMNO");
		request.setBrand("KENMORE");
		
		
		ModelQueryBuilder builder = new ModelQueryBuilder(request,new StringBuilder());
		try {
			assertEquals(getExpectedForModelNoAndBrand(), builder.buildSolrQuery());
		} catch (SolrException e) {
			fail("failed with exception");
		}
	}


	
	
	@Test
	public void testModelNoAndProductTypeAndBrandSearch() {
		ModelSearchRequest request = new ModelSearchRequest();
		request.setMaxRows("100");
		request.setModelNo("ABCDEFGHIJKLMNO");
		request.setProductTypeId("PRODUCTTYPE");
		request.setBrand("KENMORE");
		
	ModelQueryBuilder builder = new ModelQueryBuilder(request,new StringBuilder());
		try {
			assertEquals(getExpectedForModelNoAndProductTypeAndBrandSearch(), builder.buildSolrQuery());
		} catch (SolrException e) {
			fail("failed with exception");
		}
	}
	
	@Test
	public void testModelIdAndProductTypeAndBrandSearch() {
		ModelSearchRequest request = new ModelSearchRequest();
		request.setMaxRows("100");
		request.setModelId("MODELID");
		request.setBrand("BRAND");
		request.setProductTypeId("PRODUCTTYPE");
		
		ModelQueryBuilder builder = new ModelQueryBuilder(request,new StringBuilder());
	
		try {
			assertEquals(getExpectedForModelIdAndProductTypeAndBrandSearch(), builder.buildSolrQuery());
		} catch (SolrException e) {
			fail("failed with exception");
		}
	}
	
	@Test
	public void tesRowsReturned() {
		ModelSearchRequest request = new ModelSearchRequest();
		request.setMaxRows("123");
		
		ModelQueryBuilder builder = new ModelQueryBuilder(request,new StringBuilder());
		
		try {
			
			assertEquals("&rows=123", builder.appendRowsToReturnQuery().getQueryBuffer().toString());
		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fail("failed with exception");
			
		}
		
	}
	
	@Test
	public void testStartRows() {
		ModelSearchRequest request = new ModelSearchRequest();
		request.setStartingRow("131");
		
		ModelQueryBuilder builder = new ModelQueryBuilder(request,new StringBuilder());
		
		try {
			
			assertEquals("&start=130", builder.appendStartRowQuery().getQueryBuffer().toString());
		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fail("failed with exception");
			
		}
		
	}

	 private String getExpectedForModelNoAndBrand() {
		return "formattedModelNo:\"ABCDEFGHIJKLMNO\"+OR+modelNoEnhancedSearch:ABCDEFGHIJKLMNO*&fq=brand:\"KENMORE\"&rows=100&wt=json&fl=*,score";
	}
	
	
	private String getExpectedForModelIdAndProductTypeAndBrandSearch(){
		return "modelId:\"MODELID\"+OR+formattedModelId:\"MODELID\"&fq=brand:\"BRAND\"&fq=productTypeId:%28PRODUCTTYPE%29&rows=100&wt=json&fl=*,score";
				
	}
	
	private String getExpectedForModelNoAndProductTypeAndBrandSearch(){
		return "formattedModelNo:\"ABCDEFGHIJKLMNO\"+OR+modelNoEnhancedSearch:ABCDEFGHIJKLMNO*&fq=brand:\"KENMORE\"&fq=productTypeId:%28PRODUCTTYPE%29&rows=100&wt=json&fl=*,score";
	}
	
   public static void main(String args[]) throws SolrException{
		
		ModelSearchRequest request = new ModelSearchRequest();
		request.setModelNo("ABCDE");
		request.setMaxRows("10");
		
		ModelQueryBuilder builder = new ModelQueryBuilder(request,new StringBuilder());
		
		String query = builder.buildSolrQuery();
		System.out.println("QUERY 1==="+query);
		
		
		builder = new ModelQueryBuilder(request,new StringBuilder());
		query = builder.buildSolrQuery();
		System.out.println("QUERY 2==="+query);
		
		request = new ModelSearchRequest();
		request.setModelNo("XRSS287BBPXRS");
		request.setBrand("AMANA");
		request.setFormatted("Y");
		request.setParentProductTypeId("0100000");
		request.setProductTypeId("0165000");
		request.setSortBy("modelNo ASC");
		request.setMaxRows("100");
		request.setStartingRow("1");
		request.setSubProductTypeId("0165000");
		request.setMaxRows("10");
			
		builder = new ModelQueryBuilder(request,new StringBuilder());
		query = builder.buildSolrQuery();
		System.out.println("QUERY 3==="+query);
		
		request = new ModelSearchRequest();
		request.setModelNo("XRSS287BBPXRS*");
		request.setBrand("AMANA");
		request.setFormatted("Y");
		request.setParentProductTypeId("0100000");
		request.setProductTypeId("0165000");
		request.setSortBy("modelNo ASC");
		request.setMaxRows("100");
		request.setStartingRow("1");
		request.setSubProductTypeId("0165000");
		request.setMaxRows("10");
			
		builder = new ModelQueryBuilder(request,new StringBuilder());
		query = builder.buildSolrQuery();
		System.out.println("QUERY 4==="+query);
		
		
		
	
   
		 request = new ModelSearchRequest();
		request.setModelNo("XRS-S287-BB-PXRS");
		request.setBrand("AMANA");
		//request.setFormatted("Y");
		request.setParentProductTypeId("0100000");
		request.setProductTypeId("0165000");
		request.setSortBy("modelNo asc");
		request.setMaxRows("100");
		request.setStartingRow("1");
		request.setSubProductTypeId("0165000");
		request.setMaxRows("10");
		
		builder = new ModelQueryBuilder(request,new StringBuilder());
		query = builder.buildSolrQuery();
		System.out.println("QUERY 5==="+query);
		
       

		request = new ModelSearchRequest();
		request.setModelNo("XRS-S287-BB-PXRS*");
		request.setBrand("AMANA");
		//request.setFormatted("Y");
		request.setParentProductTypeId("0100000");
		request.setProductTypeId("0165000");
		request.setSortBy("modelNo asc");
		request.setMaxRows("100");
		request.setStartingRow("1");
		request.setSubProductTypeId("0165000");
		request.setMaxRows("10");
		
		builder = new ModelQueryBuilder(request,new StringBuilder());
		query = builder.buildSolrQuery();
		System.out.println("QUERY 6==="+query);
		
		
		request = new ModelSearchRequest();
		request.setModelNo("XRS-S287-BB-PXRS*");
		request.setBrand("AMANA");
		//request.setFormatted("Y");
		request.setParentProductTypeId("0100000");
		request.setProductTypeId("0165000");
		request.setSortBy("modelNo asc");
		request.setMaxRows("100");
		request.setStartingRow("1");
		request.setSubProductTypeId("0165000");
		request.setMaxRows("10");
		
		List<BrandCcdb> brandList = new ArrayList<BrandCcdb>();
		
		BrandCcdb b1 = new BrandCcdb();
		b1.setBrandId("1268");
		BrandCcdb b2 = new BrandCcdb();
		b2.setBrandId("0432");
		brandList.add(b1);
		brandList.add(b2);
		
		List<ProductTypeCcdb> productList = new ArrayList<ProductTypeCcdb>();
		
		ProductTypeCcdb p1 = new ProductTypeCcdb();
		p1.setProductTypeCd("0110000");
		ProductTypeCcdb p2 = new ProductTypeCcdb();
		p2.setProductTypeCd("0165000");
		productList.add(p1);
		productList.add(p2);
/*			
		
		builder = new ModelQueryBuilder(request,builder.getCommonCodeGetModel(brandList , productList));
		query = builder.buildSolrQuery();
		System.out.println("QUERY 7 with valid exclusion filter==="+query);
		
		
		List<BrandCcdb> brandList2 = new ArrayList<BrandCcdb>();
		
		BrandCcdb b3 = new BrandCcdb();
		b3.setBrandId("1");
		BrandCcdb b4 = new BrandCcdb();
		b4.setBrandId("0");
		brandList2.add(b3);
		brandList2.add(b4);
		
		List<ProductTypeCcdb> productList2 = new ArrayList<ProductTypeCcdb>();
		
		ProductTypeCcdb p3 = new ProductTypeCcdb();
		p3.setProductTypeCd("0");
		ProductTypeCcdb p4 = new ProductTypeCcdb();
		p4.setProductTypeCd("0");
		productList2.add(p3);
		productList2.add(p4);
		
			
		
		builder = new ModelQueryBuilder(request,builder.getCommonCodeGetModel(brandList2 , productList2));
		query = builder.buildSolrQuery();
		System.out.println("QUERY 8 with invalid exclusion filter==="+query);*/

		
	}
	
	

}
