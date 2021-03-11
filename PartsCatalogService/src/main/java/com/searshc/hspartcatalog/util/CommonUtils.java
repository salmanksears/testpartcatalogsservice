package com.searshc.hspartcatalog.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST;
import com.searshc.hspartcatalog.pojo.ValidatorOutput;

public class CommonUtils {
	
	private static final Pattern patternWildCard = Pattern.compile("%|-|\\+");
	private static final Pattern patternNoWildCard = Pattern.compile("%");
	
	/**
	 * This is generic method which is used to build the output message whenever there is validation failure
	 * @param returnCode
	 * @param List of message
	 * @return ValidatorOutput
	 */
	public static ValidatorOutput buildValidatorOutput(String returnCode, List<String> messages) {
		ValidatorOutput validatorOutput = new ValidatorOutput();
		validatorOutput.setReturnCode(returnCode);
		if(messages!=null && !messages.isEmpty())
			validatorOutput.setMessages(messages);
		
		return validatorOutput;
	}
	
	/**
	 * This is returns processId for a requests (thread). This is thread safe
	 * @return String
	 */
	public static synchronized String getProcessId(){
		return (System.nanoTime() + "-" + Thread.currentThread().getId());
	}
	
	/**
	 * This is generic method that returns the difference of start time and end time
	 * @param long startTime
	 * @param long endTime
	 * @return long
	 */
	public static long getServiceResponseTime(long startTime,long endTime) {
		long responseTime=0;
		if(endTime>startTime){
			responseTime=endTime-startTime;
		}
		return responseTime;
	}
	
	/**
	 * This is generic method which is used to validate date format specified in constants interface
	 * @param String date
	 * @return boolean
	 */
	public static boolean validateDate(String input) throws ParseException {
	    Date date = null;
	    SimpleDateFormat format = new SimpleDateFormat(GEN_CONST.DATE_FORMAT);
   		format.setLenient(false);
        date = format.parse(input);
        if(date != null)
        	return true;
        else
        	return false;
	 }
		
	/**
	 * This method escapes the special chars of SOLR query with wildCard
	 * @param String str
	 * @return String
	 */
	public static String escapeSOLRSpecialCharsWildCard(String str){
			
		str = str.replaceAll("%5C", "\\\\\\\\");
		return str.replaceAll("-", "\\\\-").replaceAll("%21", "\\\\!").replaceAll("%28", "\\\\(").
			replaceAll("%29", "\\\\)").replaceAll("%7B", "\\\\{").replaceAll("%7D", "\\\\}").replaceAll("%5B", "\\\\[").
			replaceAll("%5D", "\\\\]").replaceAll("%5E", "\\\\^").replaceAll("%22", "\\\\\"").replaceAll("%7E", "\\\\~").
			replaceAll("%3F", "\\\\?").replaceAll("%3A", "\\\\:").replaceAll("%26%26", "\\\\&&").replaceAll("%7C%7C", "\\\\||").replaceAll("\\+", "\\\\+");
	}
	
	/**
	 * This method escapes the special chars of SOLR query
	 * @param String str
	 * @return String
	 */
	public static String escapeSOLRSpecialChars(String str){
		
		return str.replaceAll("%5C", "\\\\\\\\").replaceAll("%22", "\\\\\"");
	}
}
