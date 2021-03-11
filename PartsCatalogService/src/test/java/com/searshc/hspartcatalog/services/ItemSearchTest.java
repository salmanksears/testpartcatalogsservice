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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author ajain0
 *
 */

public class ItemSearchTest {

	HttpGet httpGet = null;
	String baseURL = null;
	
	@Before
	public void setup(){
		baseURL = "http://trqahspsolrs1.vm.itg.corp.us.shldcorp.com:9180/PartsCatalogService/itemSearch?";
	}
	
	//@Test
	public void testHappyFlow(){

		httpGet = new HttpGet(baseURL+"itemId=0046253241523501");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList resp = null;	
		NodeList msg = null;	
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "1");
		verifyMessages(doc, "Success");
		
		resp = doc.getElementsByTagName("item");
		
		assertEquals(1, resp.getLength());
		
		msg = (NodeList) resp.item(0);
		assertEquals("0046253241523501",((Element)msg.item(0)).getTextContent());
		assertEquals("241523501",((Element)msg.item(1)).getTextContent());
		assertEquals("0046",((Element)msg.item(2)).getTextContent());
		assertEquals("Refrigerators and Freezers",((Element)msg.item(3)).getTextContent());
		assertEquals("Water Tube",((Element)msg.item(4)).getTextContent());
		assertEquals("NLO",((Element)msg.item(5)).getTextContent());
		assertEquals("3.8",((Element)msg.item(6)).getTextContent());
		assertEquals("N",((Element)msg.item(7)).getTextContent());
		assertEquals("N",((Element)msg.item(8)).getTextContent());
		assertEquals("N",((Element)msg.item(9)).getTextContent());
		assertEquals("Y",((Element)msg.item(10)).getTextContent());
		assertEquals("N",((Element)msg.item(11)).getTextContent());
		assertEquals("0046253241796401",((Element)msg.item(12)).getTextContent());
		assertEquals("241796401",((Element)msg.item(13)).getTextContent());
		assertEquals("Water Tube",((Element)msg.item(14)).getTextContent());
		assertEquals("0046",((Element)msg.item(15)).getTextContent());
		assertEquals("Refrigerators and Freezers",((Element)msg.item(16)).getTextContent());
		assertEquals("http://s.sears.com/is/image/Sears/PD_0046_253_241796401",((Element)msg.item(17)).getTextContent());
		assertEquals("PIA",((Element)msg.item(18)).getTextContent());
		assertEquals("5.93",((Element)msg.item(19)).getTextContent());
		assertEquals("N",((Element)msg.item(20)).getTextContent());
		assertEquals("N",((Element)msg.item(21)).getTextContent());
		assertEquals("N",((Element)msg.item(22)).getTextContent());

		
			
	}

    //@Test
	public void testHappyFlowPartNo(){

		httpGet = new HttpGet(baseURL+"partNo=241523501");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList resp = null;	
		NodeList msg = null;	
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "1");
		verifyMessages(doc, "Success");

		resp = doc.getElementsByTagName("item");
		
		assertEquals(1, resp.getLength());
		
		msg = (NodeList) resp.item(0);
		assertEquals("0046253241523501",((Element)msg.item(0)).getTextContent());
		assertEquals("241523501",((Element)msg.item(1)).getTextContent());
		assertEquals("0046",((Element)msg.item(2)).getTextContent());
	/*	assertEquals("Refrigerators and Freezers",((Element)msg.item(3)).getTextContent());
		assertEquals("Water Tube",((Element)msg.item(4)).getTextContent());
		assertEquals("NLO",((Element)msg.item(5)).getTextContent());
		assertEquals("3.8",((Element)msg.item(6)).getTextContent());
		assertEquals("N",((Element)msg.item(7)).getTextContent());
		assertEquals("N",((Element)msg.item(8)).getTextContent());
		assertEquals("N",((Element)msg.item(9)).getTextContent());
		assertEquals("Y",((Element)msg.item(10)).getTextContent());
		assertEquals("0046253241796401",((Element)msg.item(11)).getTextContent());
		assertEquals("241796401",((Element)msg.item(12)).getTextContent());
		assertEquals("Water Tube",((Element)msg.item(13)).getTextContent());
		assertEquals("0046",((Element)msg.item(14)).getTextContent());
		assertEquals("Refrigerators and Freezers",((Element)msg.item(15)).getTextContent());
		assertEquals("http://s.sears.com/is/image/Sears/PD_0046_253_241796401",((Element)msg.item(16)).getTextContent());
		assertEquals("PIA",((Element)msg.item(17)).getTextContent());
		assertEquals("5.58",((Element)msg.item(18)).getTextContent());
		assertEquals("N",((Element)msg.item(19)).getTextContent());
		assertEquals("N",((Element)msg.item(20)).getTextContent());
		assertEquals("N",((Element)msg.item(21)).getTextContent());
*/
		
			
	}

    //@Test
	public void testHappyFlowPartNoWildcard(){

		httpGet = new HttpGet(baseURL+"partNo=00091*");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		int i = 0;
		NodeList resp = null;	
		NodeList msg = null;	
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "25");
		verifyMessages(doc, "Success");

		resp = doc.getElementsByTagName("item");
		
		assertEquals(25, resp.getLength());
		
		for(i=0;i<resp.getLength();i++){
			msg = (NodeList) resp.item(i);
			assertEquals("00091",((Element)msg.item(1)).getTextContent().substring(0, 5));
		}

		
			
	}
	
    //@Test
	public void testHappyFlowItemIdWildcard(){

		httpGet = new HttpGet(baseURL+"itemId=0071271*");
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		int i = 0;
		NodeList resp = null;	
		NodeList msg = null;	
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "2887");
		verifyMessages(doc, "Success");
		
		resp = doc.getElementsByTagName("item");
		
		assertEquals(100, resp.getLength());
		
		for(i=0;i<resp.getLength();i++){
			msg = (NodeList) resp.item(i);
			assertEquals("0071271",((Element)msg.item(0)).getTextContent().substring(0, 7));
		}

		
			
	}

    //@Test
	public void testHappyFlowWithPagination(){

		httpGet = new HttpGet(baseURL+"itemId=0071271100*&startingRow=101&maxRows=10");
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList resp = null;	
		NodeList msg = null;	
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "129");
		verifyMessages(doc, "Success");

		resp = doc.getElementsByTagName("item");
		
		assertEquals(10, resp.getLength());
		
		msg = (NodeList) resp.item(0);
		assertEquals("0071271100213-05530",((Element)msg.item(0)).getTextContent());
		
//		changing the sorting criteria to : 
		
		httpGet = new HttpGet(baseURL+"itemId=0071271100*&startingRow=101&maxRows=10&sortBy=partNo+desc");
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		resp = doc.getElementsByTagName("item");
		
		assertEquals(10, resp.getLength());
		
		msg = (NodeList) resp.item(0);
		assertEquals("007127110001133730",((Element)msg.item(0)).getTextContent());

		
			
	}

    //@Test
	public void testHappyFlowForFormattedItemId(){

		httpGet = new HttpGet(baseURL+"itemId=0064407.75GPH-70-A&formatted=Y");
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList resp = null;	
		NodeList msg = null;	
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "1");
		verifyMessages(doc, "Success");

		resp = doc.getElementsByTagName("item");
		
		assertEquals(1, resp.getLength());
		
		msg = (NodeList) resp.item(0);
		assertEquals("0064407.75GPH-70-A",((Element)msg.item(0)).getTextContent());
		
		
			
	}
	
    //@Test
	public void testHappyFlowFilterProductGroupId(){

		httpGet = new HttpGet(baseURL+"itemId=*&productGroupId=0064");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		int i=0;
		NodeList resp = null;	
		NodeList msg = null;	
		verifyResponseCode(doc, "000");
//		verifyNumFound(doc, "92398");
		verifyMessages(doc, "Success");

		resp = doc.getElementsByTagName("item");
		
		assertEquals(100, resp.getLength());
		
		for(i=0;i<resp.getLength();i++){
			msg = (NodeList) resp.item(i);
			assertEquals("0064",((Element)msg.item(2)).getTextContent());
		}

		
			
	}
	
    //@Test
	public void testHappyFlowFilterProductGroupIdWildCard(){

		httpGet = new HttpGet(baseURL+"itemId=*&productGroupId=006*");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Wildcard not supported for field : productGroupId");
		
		
			
	}

	/*request validation testing */
	
    //@Test
	public void testRequiredFieldMissing(){

		httpGet = new HttpGet(baseURL+"productGroupId=006*");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);

		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Required field missing : partNo or itemId");

		
			
	}

    //@Test
	public void testSpaceValidation(){

		httpGet = new HttpGet(baseURL+"itemId=0057564%20-W5T761");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Spaces not allowed in between for field : itemId");
		
		

		/* testing for partNo*/
		httpGet = new HttpGet(baseURL+"partNo=.60%20GPH-A-60");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Spaces not allowed in between for field : partNo");

		

		/* testing for productGroupId*/
		httpGet = new HttpGet(baseURL+"itemId=*&productGroupId=00%206");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Spaces not allowed in between for field : productGroupId");

		

	}
	
	//@Test
	public void testNumericNonNegativeFields(){

		httpGet = new HttpGet(baseURL+"itemId=0057564-W5T761&startingRow=A");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Field : startingRow must be numeric");
		
		

		/* testing non negative maxRows*/
		
		httpGet = new HttpGet(baseURL+"itemId=0057564-W5T761&startingRow=-1");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Field : startingRow should be non negative.");
		
		

		/* testing for maxRows*/
		
		httpGet = new HttpGet(baseURL+"itemId=0057564-W5T761&maxRows=A");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Field : maxRows must be numeric");
		
		

		/* testing non negative maxRows*/
		
		httpGet = new HttpGet(baseURL+"itemId=0057564-W5T761&maxRows=-1");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Field : maxRows should be non negative.");
		
		
	}

    //@Test
	public void testMaxRowsGreaterThanMaxValueValidation(){

		httpGet = new HttpGet(baseURL+"itemId=*&maxRows=101");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Invalid field value. maxRows must be less than or equals to 100");
		
		

	}

    //@Test
	public void testLengthValidation(){

		httpGet = new HttpGet(baseURL+"itemId=0057564-W5T7610000000000000000000000000000000000000000000000000000000000000");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "itemId should be 50 characters long or less");
		
		

		/* testing length of partNo*/
		
		httpGet = new HttpGet(baseURL+"partNo=0000000000000000000000000");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "partNo should be 24 characters long or less");

		

		/* exact length validation of formatted */
		httpGet = new HttpGet(baseURL+"itemId=*&formatted=YY");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		
		NodeList resp = doc.getElementsByTagName("message");
		assertEquals("formatted should be exactly 1 characters long", ((Element)resp.item(1)).getTextContent());

		
	}

    //@Test
	public void testDefaultValueValidation(){

		httpGet = new HttpGet(baseURL+"itemId=*");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "000");

		NodeList resp = doc.getElementsByTagName("item");
		
		/* maxLength default : 100*/
		assertEquals(100,resp.getLength());
		
		
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