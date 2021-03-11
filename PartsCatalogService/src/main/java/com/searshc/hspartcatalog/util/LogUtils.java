package com.searshc.hspartcatalog.util;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.EQUALS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.SPACE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.INVALID_REQUEST;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Title			:   LogUtils
* Description	:	this class provides utility methods for custom logging  
* @author		:	Abhishek Jain
*/

public final class LogUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);
	
	private LogUtils() {}

	/**
     * Method 		 	: buildStartLog
     * Description		: This method logs the request   
     * @param			: String clientKey, String processId, String method, Map<String, String> request params
     */
	public static void buildStartLog(final String clientKey, final String processId, final String method, final Map<String, String> request) {

		StringBuilder requestSb = new StringBuilder();
		
		for (Map.Entry<String, String> entry : request.entrySet()) {
			if(entry.getValue()!=null && !entry.getValue().isEmpty())
				requestSb.append(entry.getKey()).append(EQUALS).append(entry.getValue()).append(SPACE);
		}
		logger.info("PID[{}] CLIENT[{}] METHOD[{}] started. REQUEST[{}]", processId, clientKey, method, requestSb.toString());

	}

	/**
     * Method 		 	: buildFinishLog
     * Description		: This method logs the responseCode, validation messages (if any) and numFound   
     * @param			: String clientKey, String processId, String method, String responseCode, List<String> messagesList, Integer numFound
     */
	public static void buildFinishLog(final String clientKey, final String processId, final String method, String responseCode, 
			List<String> messagesList, Integer numFound) {
		
		StringBuilder requestSb = new StringBuilder();
		
		for(String messages : messagesList)
			requestSb.append(messages);
		
		logger.info("PID[{}] CLIENT[{}] METHOD[{}] ended. RESPCODE[{}] RESPMSG[{}] NUMFOUND[{}]", processId, clientKey, method, 
			responseCode, requestSb.toString(), numFound);

	}

	/**
     * Method 		 	: buildInvalidRequestLog
     * Description		: This method logs the invalid requests   
     * @param			: String clientKey, String processId, String method, String responseCode, List<String> messagesList
     */
	public static void buildInvalidRequestLog(final String clientKey, final String processId, final String method, String responseCode, 
			List<String> messagesList ) {

		StringBuilder requestSb = new StringBuilder();
		
		for(String messages : messagesList)
			requestSb.append(messages);
		
		logger.info("PID[{}] CLIENT[{}] METHOD[{}]"+ INVALID_REQUEST +" RESPCODE[{}] RESPMSG[{}]", processId, clientKey, 
			method,	responseCode, requestSb.toString());

	}

	/**
     * Method 		 	: buildExceptionLog
     * Description		: This method logs the exceptions   
     * @param			: String clientKey, String processId, String method, Exception ex
     */
	public static void buildExceptionLog(final String clientKey, final String processId, final String method, final Exception ex) {

		logger.error("PID[{}] CLIENT[{}] METHOD[{}] exception occurred. EXCEPTION MSG[{}]", processId, clientKey, method, 
			ex.getMessage());
		logger.error("Exception : ",ex);
	
	}
	
	/**
     * Method 		 	: buildExceptionLog
     * Description		: This method logs the finally block, basically the response time   
     * @param			: String clientKey, String processId, String method, long responseTime
     */
	public static void buildFinallyLog(final String clientKey, final String processId, final String method, long responseTime) {
		
		logger.info("PID[{}] CLIENT[{}] METHOD[{}] RESPONSE_TIME[{}]", processId, clientKey, method, responseTime);

	}

}
