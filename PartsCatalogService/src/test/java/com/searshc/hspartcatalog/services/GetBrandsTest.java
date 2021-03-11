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

public class GetBrandsTest {

	HttpGet httpGet = null;
	String baseURL = null;
	
	@Before
	public void setup(){
		baseURL = "http://trqahspsolrs1.vm.itg.corp.us.shldcorp.com:9180/PartsCatalogService/getBrands?";
	}
	
	//@Test
	public void testHappyFlow(){

		httpGet = new HttpGet(baseURL+"brand=kent");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList resp = null;	
		NodeList msg = null;	
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "10");
		verifyMessages(doc, "Success");
		
		resp = doc.getElementsByTagName("brand");
		
		assertEquals(1, resp.getLength());
		
		msg = (NodeList) resp.item(0);
		assertEquals("kent",((Element)msg.item(0)).getTextContent());
		assertEquals("10",((Element)msg.item(1)).getTextContent());

		
			
	}

    //@Test
	public void testHappyFlowBrandWildcard(){

		httpGet = new HttpGet(baseURL+"brand=ken*");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		int i = 0;
		NodeList resp = null;	
		NodeList msg = null;	
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "347");
		verifyMessages(doc, "Success");
		int modelCount = 0;

		resp = doc.getElementsByTagName("brand");
		
		for(i=0;i<resp.getLength();i++){
			msg = (NodeList) resp.item(i);
			assertEquals("ken",((Element)msg.item(0)).getTextContent().substring(0, 3));
			modelCount = modelCount + Integer.parseInt((String)((Element)msg.item(1)).getTextContent());
		}
		assertTrue(modelCount == 347);
		
			
	}

    //@Test
	public void testHappyFlowWithPagination(){

		httpGet = new HttpGet(baseURL+"brand=KEN*&sortBy=count");

		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList resp = null;	
		NodeList msg = null;	

		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		resp = doc.getElementsByTagName("brand");
		
		assertEquals(3, resp.getLength());
		
		msg = (NodeList) resp.item(0);
		assertEquals("kenwood",((Element)msg.item(0)).getTextContent());
		assertEquals("335",((Element)msg.item(1)).getTextContent());

		
			
//		changing the sorting criteria to : 
		
		httpGet = new HttpGet(baseURL+"brand=KEN*&sortBy=brand");
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		resp = doc.getElementsByTagName("brand");
		
		assertEquals(3, resp.getLength());
		
		msg = (NodeList) resp.item(0);
		assertEquals("ken-ran",((Element)msg.item(0)).getTextContent());
		assertEquals("2",((Element)msg.item(1)).getTextContent());

		

	}

	/*request validation testing */
	
    //@Test
	public void testRequiredFieldMissing(){

		httpGet = new HttpGet(baseURL);
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);

		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Required field missing : brand");

		
			
	}

    //@Test
	public void testLengthValidation(){

		httpGet = new HttpGet(baseURL+"brand=00000000000000000000000000000000000000000000" +
				"0000000000000000000000000000000000000000000000000000000000000000000000000" +
				"000000000000000000000000000000000000000000000000000000000000000000000000000" +
				"0000000000000000000000000000000000000000000000000000000000000000000000000000");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "brand should be 254 characters long or less");
		
		
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

		httpGet = new HttpGet(baseURL+"brand=*");
		
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