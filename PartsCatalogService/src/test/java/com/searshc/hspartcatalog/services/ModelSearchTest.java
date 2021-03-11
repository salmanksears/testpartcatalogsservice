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
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ModelSearchTest {

	HttpGet httpGet = null;
	String baseURL = null;
	
	@Before
	public void setup(){
		baseURL = "http://trqahspsolrs1.vm.itg.corp.us.shldcorp.com:9180/PartsCatalogService/modelSearch?";
	}
	
    @Test
	public void testHappyFlow(){

		httpGet = new HttpGet(baseURL+"modelNo=(RXHE-CE020CC)");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		NodeList resp = null;	
		NodeList msg = null;	
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, "1");
		verifyMessages(doc, "Success");

		resp = doc.getElementsByTagName("model");
		assertEquals(1, resp.getLength());
		msg = (NodeList) resp.item(0);
		
		assertEquals("01242500432JGSP48B0H3BB",((Element)msg.item(0)).getTextContent());
		assertEquals("JGSP48B0H3BB",((Element)msg.item(1)).getTextContent());
		assertEquals("GAS RANGE",((Element)msg.item(2)).getTextContent());
		assertEquals("0432",((Element)msg.item(3)).getTextContent());
		assertEquals("GE",((Element)msg.item(4)).getTextContent());
		assertEquals("0100000",((Element)msg.item(5)).getTextContent());
		assertEquals("Appliances - Major*^",((Element)msg.item(6)).getTextContent());
		assertEquals("0122600",((Element)msg.item(7)).getTextContent());
		assertEquals("Cooking*",((Element)msg.item(8)).getTextContent());
		assertEquals("0120750",((Element)msg.item(9)).getTextContent());
		assertEquals("Gas Ranges/Ovens*^",((Element)msg.item(10)).getTextContent());
		assertEquals("7",((Element)msg.item(11)).getTextContent());
		assertEquals("0",((Element)msg.item(12)).getTextContent());

		
			
	}

 	public void happyFlowModelId(){

		httpGet = new HttpGet(baseURL+"modelId=01242500432JGSP48B0H3BB");
		
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
		assertEquals("01242500432JGSP48B0H3BB",((Element)msg.item(0)).getTextContent());
		assertEquals("JGSP48B0H3BB",((Element)msg.item(1)).getTextContent());
		assertEquals("GAS RANGE",((Element)msg.item(2)).getTextContent());
		assertEquals("0432",((Element)msg.item(3)).getTextContent());
		assertEquals("GE",((Element)msg.item(4)).getTextContent());
		assertEquals("0100000",((Element)msg.item(5)).getTextContent());
		assertEquals("Appliances - Major*^",((Element)msg.item(6)).getTextContent());
		assertEquals("0122600",((Element)msg.item(7)).getTextContent());
		assertEquals("Cooking*",((Element)msg.item(8)).getTextContent());
		assertEquals("0120750",((Element)msg.item(9)).getTextContent());
		assertEquals("Gas Ranges/Ovens*^",((Element)msg.item(10)).getTextContent());
		assertEquals("7",((Element)msg.item(11)).getTextContent());
		assertEquals("0",((Element)msg.item(12)).getTextContent());

		
			
	}

  public void happyFlowModelNoWildcard(){

		httpGet = new HttpGet(baseURL+"modelNo=161*");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList msg = null;	
		NodeList resp = null;
		int i = 0;
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "88");
		verifyMessages(doc, "Success");
		resp = doc.getElementsByTagName("model");
		
		assertEquals(88, resp.getLength());
		
		for(i=0;i<resp.getLength();i++){
			msg = (NodeList) resp.item(i);
			assertEquals("161",((Element)msg.item(1)).getTextContent().substring(0, 3));
		}

		
			
	}
	
  public void happyFlowModelIdWildcard(){

		httpGet = new HttpGet(baseURL+"modelId=150382015*");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		NodeList msg = null;	
		NodeList resp = null;
		int i = 0;
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "138");

		resp = doc.getElementsByTagName("model");
		
		assertEquals(100, resp.getLength());
		
		for(i=0;i<resp.getLength();i++){
			msg = (NodeList) resp.item(i);
			assertEquals("150382015",((Element)msg.item(0)).getTextContent().substring(0, 9));
		}

		
			
	}
	
  public void happyFlowWithPagination(){

		httpGet = new HttpGet(baseURL+"modelId=150382015*&startingRow=101&maxRows=10");
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList msg = null;	
		NodeList resp = null;
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "138");
		verifyMessages(doc, "Success");
		
		resp = doc.getElementsByTagName("model");
		
		assertEquals(10, resp.getLength());
		
		msg = (NodeList) resp.item(0);
		assertEquals("15038201518UT20709",((Element)msg.item(0)).getTextContent());
		
		//changing the sorting criteria to : 
		
		httpGet = new HttpGet(baseURL+"modelId=150382015*&startingRow=101&maxRows=10&sortBy=modelNo+desc,modelId+asc");
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		resp = doc.getElementsByTagName("model");
		
		assertEquals(10, resp.getLength());
		
		msg = (NodeList) resp.item(0);
		assertEquals("15038201518UT-20697",((Element)msg.item(0)).getTextContent());

		
			
	}

   public void happyFlowForFormattedModelNo(){

		httpGet = new HttpGet(baseURL+"modelNo=UT-20697&formatted=Y");
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
	
	public void happyFlowFilterProductTypeId(){

		httpGet = new HttpGet(baseURL+"modelNo=*&productTypeId=1610360");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList msg = null;	
		NodeList resp = null;
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "627");
		verifyMessages(doc, "Success");
		
		resp = doc.getElementsByTagName("model");
		
		assertEquals(100, resp.getLength());
		
		msg = (NodeList) resp.item(0);
		assertEquals("1610360",((Element)msg.item(7)).getTextContent());

		
			
	}

  public void happyFlowFilterParentProductTypeId(){

    	
		httpGet = new HttpGet(baseURL+"modelId=*&parentProductTypeId=0729203");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		NodeList msg = null;	
		NodeList resp = null;
		int i = 0;
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "11030");
		verifyMessages(doc, "Success");
		
		resp = doc.getElementsByTagName("model");
		
		assertEquals(100, resp.getLength());
		
		for(i=0;i<resp.getLength();i++){
			msg = (NodeList) resp.item(i);
			assertEquals("0729203",((Element)msg.item(5)).getTextContent());
		}

		
			
	}

	public void happyFlowFilterSubProductTypeId(){

		httpGet = new HttpGet(baseURL+"modelNo=*&subProductTypeId=1307000");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		NodeList msg = null;	
		NodeList resp = null;
		int i = 0;
		verifyResponseCode(doc, "000");
		verifyNumFound(doc, "1955");
		verifyMessages(doc, "Success");
		
		resp = doc.getElementsByTagName("model");
		
		assertEquals(100, resp.getLength());
		
		for(i=0;i<resp.getLength();i++){
			msg = (NodeList) resp.item(i);
			assertEquals("1307000",((Element)msg.item(9)).getTextContent());
		}

		
			
	}

 	public void happyFlowFilterProductTypeIdWildCard(){

		httpGet = new HttpGet(baseURL+"modelNo=*&productTypeId=161036*");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Wildcard not supported for field : productTypeId");
		
		
			
	}

    
	public void requiredFieldMissing(){

		httpGet = new HttpGet(baseURL+"brand=KEN*");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);

		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Required field missing : modelId or modelNo");

		
			
	}

   public void spaceValidation(){

    	httpGet = new HttpGet(baseURL+"modelNo=*&productTypeId=1%20610360");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Spaces not allowed in between for field : productTypeId");

		/* testing for parentProductTypeId*/
		httpGet = new HttpGet(baseURL+"modelNo=*&parentProductTypeId=1%20610360");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Spaces not allowed in between for field : parentProductTypeId");
		
		

		/* testing for subProductTypeId*/
		httpGet = new HttpGet(baseURL+"modelNo=*&subProductTypeId=1%20307000");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Spaces not allowed in between for field : subProductTypeId");

		

	}
	
	public void numericNonNegativeFields(){

		httpGet = new HttpGet(baseURL+"modelNo=JGSP48B0H3BB&startingRow=A");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Field : startingRow must be numeric");
		
		

		/* testing non negative maxRows*/
		
		httpGet = new HttpGet(baseURL+"modelNo=JGSP48B0H3BB&startingRow=-1");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Field : startingRow should be non negative.");
		
		

		/* testing for maxRows*/
		
		httpGet = new HttpGet(baseURL+"modelNo=JGSP48B0H3BB&maxRows=A");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Field : maxRows must be numeric");
		
		

		/* testing non negative maxRows*/
		
		httpGet = new HttpGet(baseURL+"modelNo=JGSP48B0H3BB&maxRows=-1");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Field : maxRows should be non negative.");
		
		
	}

 	public void maxRowsGreaterThanMaxValueValidation(){

		httpGet = new HttpGet(baseURL+"modelNo=*&maxRows=101");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "Invalid field value. maxRows must be less than or equals to 100");
		
		

	}

  public void lengthValidation(){

		httpGet = new HttpGet(baseURL+"modelNo=JGSP48B0H3BB0000000000000000000000000000000000000000000000000000000000000");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "modelNo should be 35 characters long or less");
		
		

		/* testing length of modelId*/
		
		httpGet = new HttpGet(baseURL+"modelId=000000000000000000000000000000000000000000000000000000000000000000000000000");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);
		verifyMessages(doc, "modelId should be 50 characters long or less");

		

		/* exact length validation of formatted */
		httpGet = new HttpGet(baseURL+"modelNo=*&formatted=YY");
		
		response = getResponse(httpGet);
		doc = parseResponse(response);
		
		verifyResponseCode(doc, "200");
		verifyNumFound(doc, null);

		NodeList resp = doc.getElementsByTagName("message");
		assertEquals("formatted should be exactly 1 characters long", ((Element)resp.item(1)).getTextContent());
		
		
	}

 	public void defaultValueValidation(){

		httpGet = new HttpGet(baseURL+"modelNo=*");
		
		HttpResponse response = getResponse(httpGet);
		Document doc = parseResponse(response);
		
		verifyResponseCode(doc, "000");

		NodeList resp = doc.getElementsByTagName("model");
		
		/* maxLength default : 100*/
		assertEquals(100, resp.getLength());
		
		
	}
	
	
	private HttpResponse getResponse(HttpGet httpGet) {
		
		HttpClient httpClient = HttpClientBuilder.create().build();
	
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

	public void responseFormat(){

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
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		
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