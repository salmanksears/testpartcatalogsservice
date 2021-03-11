package com.searshc.hspartcatalog.services.bo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.searshc.hspartcatalog.domain.solr.SpinItem;
import com.searshc.hspartcatalog.exceptions.SolrException;
import com.searshc.hspartcatalog.services.vo.request.ModelSearchRequest;

public class ModelQueryBuilderWithSpinDataTest {

	@Test
	public void testBuildSorQueryWithAllParameters() {
			
			ModelSearchRequest request = new ModelSearchRequest();
			request.setModelNo("123456");
			request.setMaxRows("100");
			
			ModelQueryBuilderWithSpinData builder = new ModelQueryBuilderWithSpinData(request,new StringBuilder());
			
			String expected = getExpected();
			
			try {
				assertEquals(expected, builder.buildSolrQuery());
				
			} catch (SolrException e) {
			    fail("Failed with exception");
				
			}
				
			
   }
	
	@Test
	public void testBuildSorQueryWithSpecialCharactersInModelNo() {
			
			ModelSearchRequest request = new ModelSearchRequest();
			request.setModelNo("12-$#{}-3456");
			request.setMaxRows("100");
			
			ModelQueryBuilderWithSpinData builder = new ModelQueryBuilderWithSpinData(request,new StringBuilder());
			
			String expected = getExpected();
			
			try {
				assertEquals(expected, builder.buildSolrQuery());
				
			} catch (SolrException e) {
			    fail("Failed with exception");
				
			}
				
			
   }
	
	
  private String getExpected() {
		return "({!join+from=spinModelPlsItem+to=modelNoEnhancedSearch}catchAllSpin:123456)+or+({!join+from=spinModelManufacturerModelNumber+to=modelNoEnhancedSearch}catchAllSpin:123456)+or+({!join+from=spinModelVendorStockNumber+to=modelNoEnhancedSearch}catchAllSpin:123456)&fq={!cache=false}!formattedModelNo:123456*&rows=100&wt=json&fl=*,score";  
	}
	
		

}
