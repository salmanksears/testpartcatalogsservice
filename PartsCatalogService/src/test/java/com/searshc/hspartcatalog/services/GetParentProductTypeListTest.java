/**
 * 
 */
package com.searshc.hspartcatalog.services;

import static junit.framework.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author ajain0
 *
 */

public class GetParentProductTypeListTest {

	HttpGet httpGet = null;
	String baseURL = null;
	
	@Before
	public void setup(){
		baseURL = "http://trqahspsolrs1.vm.itg.corp.us.shldcorp.com:9180/PartsCatalogService/getParentProductTypeList?";
	}
	
	//@Test
	public void testHappyFlow(){

		httpGet = new HttpGet(baseURL);
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList resp = null;	
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "11728009");
		verifyMessages(doc, "Success");
		
		resp = doc.getElementsByTagName("parentProductType");
		
		assertEquals(20, resp.getLength());
		
		
			
	}
	
    //@Test
	public void testHappyFlowFilterBrandId(){

		httpGet = new HttpGet(baseURL+"brandId=0430");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList resp = null;	
		NodeList msg = null;	
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "203");
		verifyMessages(doc, "Success");
		
		resp = doc.getElementsByTagName("parentProductType");
		
		assertEquals(3, resp.getLength());
		
		
		msg = (NodeList) resp.item(0);
		assertEquals("0708500",((Element)msg.item(0)).getTextContent());
		assertEquals("Hardware/Tools/Painting*",((Element)msg.item(1)).getTextContent());
		Integer count = Integer.parseInt(((Element)msg.item(2)).getTextContent());
		Integer newCount = count;
		
		assertEquals(new Integer(152), count);

		msg = (NodeList) resp.item(1);
		count = Integer.parseInt(((Element)msg.item(2)).getTextContent());
		newCount = newCount + count;
		assertEquals(new Integer(2), count);
		
		msg = (NodeList) resp.item(2);
		count = Integer.parseInt(((Element)msg.item(2)).getTextContent());
		newCount = newCount + count;
		assertEquals(new Integer(49), count);
		
		assertEquals(newCount, new Integer(203));
		
		
			
	}

    //@Test
	public void testHappyFlowVersionDate(){

		httpGet = new HttpGet(baseURL+"brandId=0430&versionDate=02-06-2014");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList resp = null;	
		NodeList msg = null;	
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "203");
		verifyMessages(doc, "Success");
		
		resp = doc.getElementsByTagName("parentProductType");
		
		assertEquals(3, resp.getLength());
		
		msg = (NodeList) resp.item(0);
		
		assertEquals("0708500",((Element)msg.item(0)).getTextContent());
		assertEquals("Hardware/Tools/Painting*",((Element)msg.item(1)).getTextContent());
		Integer count = Integer.parseInt(((Element)msg.item(2)).getTextContent());
		
		assertEquals(new Integer(152), count);

		
		
		/* validating records for the date for which no data exists in solr */

		httpGet = new HttpGet(baseURL+"brandId=0430&versionDate=03-01-2014");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "100");
		verifyNumFound(doc, "0");
		verifyMessages(doc, "No matching record found");
		
		

	}

    
    //@Test
	public void testWildcardValidation(){

    	httpGet = new HttpGet(baseURL+"brandId=043*");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null );
		verifyMessages(doc, "Wildcard not supported for field : brandId");

		
			
	}
	

    //@Test
	public void testHappyFlowWithPagination(){

    	httpGet = new HttpGet(baseURL+"brandId=0430&startingRow=101&maxRows=10");
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList resp = null;	
		NodeList msg = null;	
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "203");
		verifyMessages(doc, "Success");

		resp = doc.getElementsByTagName("parentProductType");
		
		assertEquals(3, resp.getLength());
		
		msg = (NodeList) resp.item(0);
		assertEquals("0708500",((Element)msg.item(0)).getTextContent());
		assertEquals("Hardware/Tools/Painting*",((Element)msg.item(1)).getTextContent());
		
		
			
	}

	/*request validation testing */

    //@Test
	public void testSpaceValidation(){

		httpGet = new HttpGet(baseURL+"brandId=0%201");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Spaces not allowed in between for field : brandId");
		
		

	}
	
    //@Test
	public void testNumericNonNegativeFields(){

		httpGet = new HttpGet(baseURL+"brandId=0430&startingRow=A");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Field : startingRow must be numeric");
		
		

		/* testing non negative maxRows*/
		
		httpGet = new HttpGet(baseURL+"brandId=0430&startingRow=-1");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Field : startingRow should be non negative.");
		
		

		/* testing for maxRows*/
		
		httpGet = new HttpGet(baseURL+"brandId=0430&maxRows=A");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Field : maxRows must be numeric");
		
		

		/* testing non negative maxRows*/
		
		httpGet = new HttpGet(baseURL+"brandId=0430&maxRows=-1");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Field : maxRows should be non negative.");
		
		
	}

    //@Test
	public void testMaxRowsGreaterThanMaxValueValidation(){

		httpGet = new HttpGet(baseURL+"brandId=*&maxRows=15001");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Invalid field value. maxRows must be less than or equals to 15000");
		
		

	}

    //@Test
	public void testLengthValidation(){

		httpGet = new HttpGet(baseURL+"brandId=04300000");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "brandId should be 6 characters long or less");
		
		

		/* testing length of versionDate*/
		
		httpGet = new HttpGet(baseURL+"brandId=0430&versionDate=01-30-14");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "versionDate should be exactly 10 characters long");

		

	}

    //@Test
	public void testDefaultValueValidation(){

    	httpGet = new HttpGet(baseURL+"brandId=0430");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "000");

		NodeList resp = doc.getElementsByTagName("parentProductType");
		
		/* maxLength default : 15000*/
		assertEquals(3,resp.getLength());
		
		
	}

    //@Test
	public void testDateFormat(){

    	httpGet = new HttpGet(baseURL+"brandId=0430&versionDate=01-30-14");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "versionDate should be exactly 10 characters long");
		
		
	}

	private HttpResponse getResponse(HttpGet httpGet){
		DefaultHttpClient httpClient = new DefaultHttpClient();
	
		httpGet.addHeader("Accept", "application/xml");
		httpGet.setHeader("clientId", "1");
		httpGet.setHeader("clientKey", "1234567890");
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			System.out.println("RESPOSE" + response);
			
		}catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	private Document parseResponse(HttpResponse response){
		Document doc = null;
		try{	
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output;
			StringBuffer sb = new StringBuffer();
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			
			System.out.println(sb.toString());
			System.out.println("Validating response");
		
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			
			is.setCharacterStream(new StringReader(sb.toString()));
			doc = db.parse(is);
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	private void verifyResponseCode(Document doc, String respCode){
		NodeList resp = doc.getElementsByTagName("responseCode");
		assertEquals(respCode,((Element)resp.item(0)).getTextContent());
	}
	
	private void verifyNumFound(Document doc, String numFound){
		NodeList resp = doc.getElementsByTagName("numFound");
		if(numFound == null)
			assertEquals(null,(Element)resp.item(0));
		else
			assertEquals(numFound,((Element)resp.item(0)).getTextContent());
	}
	
	private void verifyMessages(Document doc, String message){
		NodeList resp = doc.getElementsByTagName("messages");
		int i = 0;
		NodeList msg = null;
		for(i=0;i<resp.getLength();i++){
			msg = (NodeList) resp.item(i);
			assertEquals(message,	((Element)msg.item(0)).getTextContent());
		}
	}
	
	//@Test
	public void testResponseFormat(){

		httpGet = new HttpGet(baseURL+"key=*");
		
		/*no Accept header set, default value will be set to : application/xml */
		httpGet.setHeader("clientId", "1");
		httpGet.setHeader("clientKey", "1234567890");
		
		HttpResponse response = getResponseWithoutHeaderValues(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "000");
		verifyMessages(doc, "Success");

		
		
		/* Testing for JSON response*/
		httpGet.setHeader("clientId", "1");
		httpGet.setHeader("clientKey", "1234567890");
		httpGet.setHeader("Accept", "application/json");
		
		response = getResponseWithoutHeaderValues(httpGet);

		try{	
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output;
			StringBuffer sb = new StringBuffer();
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			
			System.out.println(sb.toString());
			
			junit.framework.Assert.assertNotNull(sb.toString());
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

	private HttpResponse getResponseWithoutHeaderValues(HttpGet httpGet){
		DefaultHttpClient httpClient = new DefaultHttpClient();
	
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			System.out.println("RESPOSE" + response);
			
		}catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}