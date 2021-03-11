package com.searshc.hspartcatalog.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.interceptor.JAXRSInInterceptor;
import org.apache.cxf.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.searshc.hs.psc.service.constants.Constants;
import com.searshc.hspartcatalog.services.bo.ClientConfigBO;
import com.searshc.hspartcatalog.services.domain.Client;
import com.searshc.hspartcatalog.util.ThreadLocalContainer;

public class RequestHeaderInterceptor extends JAXRSInInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestHeaderInterceptor.class);

	private static final String HEADER_API_KEY = "API-Key";
	private static final String QUERY_API_KEY = "apikey";

	public static final String WADL = "_wadl";

	@Autowired
	private ClientConfigBO clientConfigBO;

	public RequestHeaderInterceptor() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.cxf.jaxrs.interceptor.JAXRSInInterceptor#handleMessage(org.
	 * apache.cxf.message.Message)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void handleMessage(Message message) {

		final String query = (String) message.get(Message.QUERY_STRING);
		final String httpMethod = (String)message.get(Message.HTTP_REQUEST_METHOD);

		if(! httpMethod.equals("POST")){
			if (StringUtils.isBlank(query))
				throw new BadRequestException();
	
			if (query.endsWith(WADL))
				return;
		}
		Client client = null;

		try {
			Map<String, List<String>> headers = (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);
			if(headers == null)
				headers = new TreeMap<String, List<String>>();
			
			//handleAccept(headers);
			String apikey = "";
			if(! httpMethod.equals("POST")){
				apikey = getApiKeyFromQuery(query);
			}
			if (StringUtils.isNotBlank(apikey))
				headers.put(HEADER_API_KEY, Collections.singletonList(apikey));
			else
				apikey = getApiKeyFromHeader(headers);
			if (StringUtils.isNotBlank(apikey)) {
				client = clientConfigBO.getClientDetailsByKey(apikey);
				if (null != client && StringUtils.isNotBlank(client.getClientKey())) {
					ThreadLocalContainer.setClient(client);
					MDC.put(Constants.CLIENT, client.getClientName());
				}
			}
		} catch (Exception ex) {
			LOGGER.error("{}", ex);
			handleFault(message, Response.Status.BAD_REQUEST);
			return;
		}
	 
		if (client == null) {
			handleFault(message, Response.Status.UNAUTHORIZED);
			return;	
		}
	}

    private void handleFault(Message message, Status status) {
    	Response response = Response.status(status).build();
		message.getExchange().put(Response.class, response);
	}

    @SuppressWarnings("unused")
	private void handleAccept(Map<String, List<String>> headers) {
    	
    	boolean b = false;
		List<String> list = headers.get(Message.ACCEPT_CONTENT_TYPE);
		if (list == null || list.size() > 1)
			b = true;
		else {
			String val = list.get(0);
			if (!val.equalsIgnoreCase(MediaType.APPLICATION_JSON) && !val.equalsIgnoreCase(MediaType.APPLICATION_XML))
				b = true;
		}

		if (b)
			headers.put(Message.ACCEPT_CONTENT_TYPE, Collections.singletonList(MediaType.APPLICATION_XML));
		
    }
    
	private String getApiKeyFromQuery(String query) {

		String apikey = null;

		Map<String, String> queries = getQueries(query);
		if (queries != null) {
			apikey = queries.get(QUERY_API_KEY);
		}

		return apikey;
	}
	
	private String getApiKeyFromHeader(Map<String, List<String>> headers) {

		String apikey = null;

		// check header values FIRST
		List<String> list = headers.get(HEADER_API_KEY);
		if (!CollectionUtils.isEmpty(list))
			apikey = list.get(0);

		return apikey;
	}

	private Map<String, String> getQueries(String query) {
		Map<String, String> queries = new LinkedHashMap<String, String>();
		List<String> parts = Arrays.asList(query.split("&"));
		for (String part : parts) {
			String[] keyValue = part.split("=");
			try {
				queries.put(keyValue[0], URLDecoder.decode(keyValue[1], "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				queries.put(keyValue[0], keyValue[1]);
			}
		}
		return queries;
	}
}
