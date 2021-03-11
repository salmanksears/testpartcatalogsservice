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

public class GetSchematicsTest {

	HttpGet httpGet = null;
	String baseURL = null;
	
	@Before
	public void setup(){
		baseURL = "http://trqahspsolrs1.vm.itg.corp.us.shldcorp.com:9180/PartsCatalogService/getSchematics?";
	}
	
	//@Test
	public void testHappyFlowWithModelNo(){

		httpGet = new HttpGet(baseURL+"modelNo=10");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList resp = null;	
		NodeList msg = null;	
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "1");
		verifyMessages(doc, "Success");
		
		resp = doc.getElementsByTagName("schematic");
		
		assertEquals(1, resp.getLength());
		
		msg = (NodeList) resp.item(0);
		assertEquals("0005647100001",((Element)msg.item(0)).getTextContent());
		assertEquals("MODEL NOTES",((Element)msg.item(1)).getTextContent());
		assertEquals("http://c.searspartsdirect.com/lis_png/PLDM/00056471-00001.png",((Element)msg.item(2)).getTextContent());
		
		

	}

	//@Test
	public void testHappyFlowWithModelId(){

		httpGet = new HttpGet(baseURL+"modelId=13071503266100088");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList resp = null;	
		NodeList msg = null;	
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "2");
		verifyMessages(doc, "Success");
		
		resp = doc.getElementsByTagName("schematic");
		
		assertEquals(2, resp.getLength());
		
		msg = (NodeList) resp.item(0);
		assertEquals("5003409700001",((Element)msg.item(0)).getTextContent());
		assertEquals("CABINET PARTS",((Element)msg.item(1)).getTextContent());
		assertEquals("http://c.searspartsdirect.com/lis_png/PLDM/50034097-00001.png",((Element)msg.item(2)).getTextContent());
		
		
			
	}
	
	//@Test
	public void testHappyFlowWithItemId(){

		httpGet = new HttpGet(baseURL+"itemId=000335246-18685-3");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList resp = null;	
		NodeList msg = null;	
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "7");
		verifyMessages(doc, "Success");
		
		resp = doc.getElementsByTagName("schematic");
		
		assertTrue(resp.getLength() >= 1);
		
		msg = (NodeList) resp.item(0);
		assertEquals("1003642500006",((Element)msg.item(0)).getTextContent());
		assertEquals("POWER AND CONTROL CIRCUIT BOARD",((Element)msg.item(1)).getTextContent());
		assertEquals("http://c.searspartsdirect.com/lis_png/PLDM/10036425-00006.png",((Element)msg.item(2)).getTextContent());
		
		
			
	}
	
	//@Test
	public void testHappyFlowWithPartNo(){

		httpGet = new HttpGet(baseURL+"partNo=46-18685-3");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList resp = null;	
		NodeList msg = null;	
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "7");
		verifyMessages(doc, "Success");
		
		resp = doc.getElementsByTagName("schematic");
		
		assertTrue(resp.getLength() >= 1);
		
		msg = (NodeList) resp.item(0);
		assertEquals("1003642500006",((Element)msg.item(0)).getTextContent());
		assertEquals("POWER AND CONTROL CIRCUIT BOARD",((Element)msg.item(1)).getTextContent());
		assertEquals("http://c.searspartsdirect.com/lis_png/PLDM/10036425-00006.png",((Element)msg.item(2)).getTextContent());
		
		
			
	}

	//@Test
	public void testForRestrictedModelNo(){

		httpGet = new HttpGet(baseURL+"modelNo=38519606");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "100");
		verifyNumFound(doc, "0");
		verifyMessages(doc, "Searched model is restricted to the client. or No matching record found");
		
		

	}

	//@Test
	public void testForRestrictedModelId(){

		httpGet = new HttpGet(baseURL+"modelId=1210000058238519606");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "100");
		verifyNumFound(doc, "0");
		verifyMessages(doc, "Searched model is restricted to the client. or No matching record found");
		
		
			
	}
	
	//@Test
	public void testForRestrictedItemId(){

		httpGet = new HttpGet(baseURL+"itemId=0003003070087R");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "100");
		verifyNumFound(doc, "0");
		verifyMessages(doc, "No matching record found");
		
		
			
	}
	
	//@Test
	public void testForRestrictedPartNo(){

		httpGet = new HttpGet(baseURL+"partNo=0003003");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "100");
		verifyNumFound(doc, "0");
		verifyMessages(doc, "No matching record found");
		
		
			
	}

	/*request validation testing */
	
    //@Test
	public void testRequiredFieldMissing(){

		httpGet = new HttpGet(baseURL);
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);

		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Required field missing : modelId or modelNo or partNo or itemId");

		
			
	}

    //@Test
	public void testSpaceValidation(){

		httpGet = new HttpGet(baseURL+"itemId=000335246%20-18685-3");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Spaces not allowed in between for field : itemId");
		
		

		httpGet = new HttpGet(baseURL+"partNo=1%200");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Spaces not allowed in between for field : partNo");
		
		
		
	}
	
    //@Test
    public void testSorting(){
	    httpGet = new HttpGet(baseURL+"itemId=000335246-18685-3&sortBy=subComponentName+desc");
	    
	    HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		NodeList resp = null;
		NodeList msg = null;
		verifyResponseCode(doc, "000");
		
		resp = doc.getElementsByTagName("schematic");
		
		assertTrue(resp.getLength()>=1);
		
		msg = (NodeList) resp.item(0);
		
		assertEquals("1003647100006",((Element)msg.item(0)).getTextContent());
		assertEquals("POWER CONTROL CIRCUIT BOARD",((Element)msg.item(1)).getTextContent());
	
		

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