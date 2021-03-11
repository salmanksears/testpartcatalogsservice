/**
 * 
 */
package com.searshc.hspartcatalog.services;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

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
import org.junit.Ignore;
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
@Ignore
public class FullSearchTest {

	HttpGet httpGet = null;
	String baseURL = null;
	
	@Before
	public void setup(){
		baseURL = "http://trqahspsolrs1.vm.itg.corp.us.shldcorp.com:9180/PartsCatalogService/fullSearch?";
	}
	
//    @Test
	public void testHappyFlow(){

		httpGet = new HttpGet(baseURL+"key=*");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		NodeList resp = null;	
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "3427303");
		verifyMessages(doc, "Success");

		resp = doc.getElementsByTagName("model");
		assertTrue(resp.getLength()>=1);

		/*resp = doc.getElementsByTagName("item");
		assertTrue(resp.getLength()>=1);
		*/
		
			
	}

//    @Test
	public void testHappyFlowSearchModelNo(){

		httpGet = new HttpGet(baseURL+"key=10655202400");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList msg = null;	
		NodeList resp = null;
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "1");
		verifyMessages(doc, "Success");
		
		resp = doc.getElementsByTagName("model");
		
		assertEquals(1, resp.getLength());
		
		msg = (NodeList) resp.item(0);
		assertEquals("0165000058210655202400",((Element)msg.item(0)).getTextContent());
		assertEquals("10655202400",((Element)msg.item(1)).getTextContent());

		
			
	}

//    @Test
	public void testHappyFlowKeyWildcard(){

		httpGet = new HttpGet(baseURL+"key=1065*");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList msg = null;	
		NodeList resp = null;
		int i = 0;
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "2138");
		verifyMessages(doc, "Success");
		resp = doc.getElementsByTagName("model");
		
		int modelCount = resp.getLength();
		assertTrue(modelCount>=1);
		
		for(i=0;i<resp.getLength();i++){
			msg = (NodeList) resp.item(i);
			assertEquals("1065",((Element)msg.item(1)).getTextContent().substring(0, 4));
		}
		
		resp = doc.getElementsByTagName("item");
		int itemCount = resp.getLength();
		
//		assertTrue(itemCount>=1);
		
		for(i=0;i<resp.getLength();i++){
			msg = (NodeList) resp.item(i);
			assertEquals("1065",((Element)msg.item(1)).getTextContent().substring(0, 4));
		}

		assertEquals(100, itemCount+modelCount);
		
			
	}
	
//    @Test
	public void testHappyFlowPartNo(){

		httpGet = new HttpGet(baseURL+"key=10651102");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		NodeList msg = null;	
		NodeList resp = null;
		int i = 0;
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "1");
		verifyMessages(doc, "Success");
		resp = doc.getElementsByTagName("item");
		
		for(i=0;i<resp.getLength();i++){
			msg = (NodeList) resp.item(i);
			assertEquals("10651102",((Element)msg.item(1)).getTextContent().substring(0, 8));
		}

		
			
	}
	
//    @Test
	public void testHappyFlowWithPagination(){

		httpGet = new HttpGet(baseURL+"key=1065*&startingRow=101&maxRows=10");
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList resp = null;
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "2138");
		verifyMessages(doc, "Success");
		
		resp = doc.getElementsByTagName("model");
		int modelCount = resp.getLength();
		resp = doc.getElementsByTagName("item");
		int itemCount = resp.getLength();
		
		assertEquals(10, itemCount+modelCount);
		
		//changing the sorting criteria to : 
		
		httpGet = new HttpGet(baseURL+"key=1065*&startingRow=101&maxRows=10&sortBy=modelNo+desc,partNo+desc");
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		resp = doc.getElementsByTagName("item");
		
//		assertEquals(5, resp.getLength());
		
		resp = doc.getElementsByTagName("model");
//		assertEquals(5, resp.getLength());
		
		
			
	}

//    @Test
	public void testHappyFlowForFormattedModelNo(){

		httpGet = new HttpGet(baseURL+"key=UT-20697&formatted=Y");
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList msg = null;	
		NodeList resp = null;
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "1");
		verifyMessages(doc, "Success");
		
		resp = doc.getElementsByTagName("model");
		
		assertEquals(1, resp.getLength());
		
		msg = (NodeList) resp.item(0);
		assertEquals("UT-20697",((Element)msg.item(1)).getTextContent());
		
		
			
	}
	
	/*request validation testing */
	
//    @Test
	public void testRequiredFieldMissing(){

		httpGet = new HttpGet(baseURL);
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);

		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Required field missing : key");

		
			
	}

//    @Test
	public void testSpaceValidation(){

		httpGet = new HttpGet(baseURL+"key=J%20GSP48B0H3BB");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Spaces not allowed in between for field : key");

		

	}
	
	@Test
	public void testNumericNonNegativeFields(){

		httpGet = new HttpGet(baseURL+"key=JGSP48B0H3BB&startingRow=A");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Field : startingRow must be numeric");
		
		

		/* testing non negative maxRows*/
		
		httpGet = new HttpGet(baseURL+"key=JGSP48B0H3BB&startingRow=-1");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Field : startingRow should be non negative.");
		
		

		/* testing for maxRows*/
		
		httpGet = new HttpGet(baseURL+"key=JGSP48B0H3BB&maxRows=A");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Field : maxRows must be numeric");
		
		

		/* testing non negative maxRows*/
		
		httpGet = new HttpGet(baseURL+"key=JGSP48B0H3BB&maxRows=-1");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Field : maxRows should be non negative.");
		
		
	}

//    @Test
	public void testMaxRowsGreaterThanMaxValueValidation(){

		httpGet = new HttpGet(baseURL+"key=*&maxRows=101");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Invalid field value. maxRows must be less than or equals to 100");
		
		

	}

//    @Test
	public void testLengthValidation(){

		httpGet = new HttpGet(baseURL+"key=JGSP48B0H3BB0000000000000000000000000000000000000000000000000000000000000");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "key should be 50 characters long or less");
		
		

		/* exact length validation of formatted */
		httpGet = new HttpGet(baseURL+"key=*&formatted=YY");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);

		NodeList resp = doc.getElementsByTagName("message");
		assertEquals("formatted should be exactly 1 characters long", ((Element)resp.item(1)).getTextContent());
		
		
	}

//    @Test
	public void testDefaultValueValidation(){

		httpGet = new HttpGet(baseURL+"key=*");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "000");

		NodeList resp = doc.getElementsByTagName("model");
		
		/* maxLength default : 100*/
		assertEquals(100, resp.getLength());
		
		
	}
	
//    @Test
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