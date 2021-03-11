package com.searshc.hspartcatalog.validator;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.ASC;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.CACHE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.DATE_FORMAT;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.DESC;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.EQUALS_TO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.FORMAT;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.JSON;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.LESS_THAN;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.NO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.OR;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.SPACE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.STARTING_ROW_DEFAULT;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.WILDCARD;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.XML;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.YES;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST.ZERO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.FACET_BY;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.FACET_BY_FIELD;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.FORMATTED;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.MAX_ROWS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.RESPONSE_FORMAT;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.SORT_BY;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.SORT_BY_FIELD;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.SORT_BY_ORDER;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.REQUEST_FIELDS.STARTING_ROW;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.FIELD_MUST_BE_NUMERIC;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.INVALID_DATE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.INVALID_DATE_FORMAT;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.INVALID_EXACT_FIELD_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.INVALID_FIELD_LENGTH;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.INVALID_FIELD_VALUE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.NON_NEGATIVE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.NON_ZERO;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.REQUIRED_FIELD_MISSING;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.SORT_EXAMPLE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.SPACES_NOT_ALLOWED_IN_BETWEEN;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.WILDCARD_NOT_SUPPORTED_FOR_FIELD;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.FACET_EXAMPLE;
import static com.searshc.hspartcatalog.util.CommonUtils.validateDate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import com.searshc.hspartcatalog.pojo.LengthAttributes;

/**
* Title			:   CommonValidator
* Description	:	this class provides implementation for few common validation, which can be extended by all the validator classes  
* @author		:	Abhishek Jain
*/
public abstract class CommonValidator {
	
	@Value("${maxRowsForCacheAPI}")
	private String maxRowsCacheDefault;
	 
	@Value("${maxRowsForCatalogAPI}")
	private String maxRowsDefault;
	
	/**
     * Method 		 	: lengthValidation
     * Description		: This method validates length of request params   
     * @param			: List<LengthAttributes>
     * @return			: List<String>
     */
	
	public List<String> lengthValidation(List<LengthAttributes> validationParams){
		List<String> messages = new ArrayList<String>();
		for(LengthAttributes param : validationParams){
			if (param.getFieldValue().length() > param.getLength()){
				messages.add(String.format(INVALID_FIELD_LENGTH, param.getFieldName(), param.getLength()));
			}
		}
		return messages;
	}

	/**
     * Method 		 	: lengthExactValidation
     * Description		: This method validates exact length of request params   
     * @param			: List<LengthAttributes>
     * @return			: List<String>
     */
	public List<String> lengthExactValidation(List<LengthAttributes> validationParams){
		List<String> messages = new ArrayList<String>();
		for(LengthAttributes param : validationParams){
			if (param.getFieldValue().length() != param.getLength()){
				messages.add(String.format(INVALID_EXACT_FIELD_LENGTH, param.getFieldName(), param.getLength()));
			}
		}
		return messages;
	}
	
	/**
     * Method 		 	: mandatoryFieldValidation
     * Description		: This method validates mandatory request params   
     * @param			: Map<String, String> 
     * @return			: String
     */
	public String mandatoryFieldValidation(Map<String, String> fieldMap){
		String msg = "";
		int i = 0;
		int size = fieldMap.size();
		for(Map.Entry<String, String> entry: fieldMap.entrySet()){
			if(StringUtils.isEmpty(entry.getValue())){
				msg = msg + entry.getKey() + OR;
				i++;
			}
		}
		if(size==i)		
			return String.format(REQUIRED_FIELD_MISSING, msg.substring(0, msg.length()-OR.length()));
		
		return null;
	}

	/**
     * Method 		 	: formattedValidation
     * Description		: This method validates the value of formatted field
     * @param			: String formatted 
     * @return			: String
     */
	public String formattedValidation(String formatted){
		if(!(formatted.equalsIgnoreCase(YES) || formatted.equalsIgnoreCase(NO)))
			return String.format(INVALID_FIELD_VALUE, FORMATTED, YES + OR + NO);
		return null;
	}

	/**
     * Method 		 	: sortByValidation
     * Description		: This method validates the value of sortBy field
     * @param			: String sortBy, String varags for properties 
     * @return			: String
     */
	public List<String> sortByValidation(String sortBy, String... props){
		String sortByField = null;
		String sortByOrder = null;
		StringTokenizer tokenizer1 = null;
		StringTokenizer tokenizer = null;
		String[] sortByKeyValuePair = null;
		List<String> propList = new ArrayList<String>();
		List<String> messages = new ArrayList<String>();
		int i =0;
		String msg = "";
		
		for(String prop : props)
			propList.add(prop);
		
		tokenizer = new StringTokenizer(sortBy, ",");
		sortByKeyValuePair = new String[tokenizer.countTokens()];
		
		while(tokenizer.hasMoreElements())
			sortByKeyValuePair[i++] = (String) tokenizer.nextElement();
		
		for(String pair : sortByKeyValuePair){
			tokenizer1 = new StringTokenizer(pair, " ");
			if(tokenizer1.countTokens()!=2)
				messages.add(String.format(INVALID_FIELD_VALUE, SORT_BY, FORMAT +SORT_BY_FIELD + SPACE + SORT_BY_ORDER+"."
					+ SORT_EXAMPLE));
			else{
				sortByField = (String) tokenizer1.nextElement();
				sortByOrder = (String) tokenizer1.nextElement();
				if(!propList.contains(sortByField)){
					for(String prop : propList){
						msg = msg + prop + OR;
					}
					msg = msg.substring(0, msg.length()-OR.length());
					messages.add(String.format(INVALID_FIELD_VALUE, SORT_BY_FIELD, msg));
				}
				if(!(sortByOrder.equalsIgnoreCase(ASC) || sortByOrder.equalsIgnoreCase(DESC)))
					messages.add(String.format(INVALID_FIELD_VALUE, SORT_BY_ORDER, ASC  +  OR  +  DESC));
				}
		}
		
		return messages;
	}
	
	
	/**
     * Method 		 	: facetByValidation
     * Description		: This method validates the value of facetBy field
     * @param			: String facetBy, String varags for properties 
     * @return			: String
     */
	public List<String> facetByValidation(String facetBy, String... allowedFieldsForFaceting){
		
		if(facetBy == null){
			return null;
		}
		
		String[] facetByFields = facetBy.split(",");
		List<String> allowedFieldsList = new ArrayList<String>();
		List<String> messages = new ArrayList<String>();
		
		for(String fieldName : allowedFieldsForFaceting)
			allowedFieldsList.add(fieldName);
		
		StringBuilder messageBuilder = new StringBuilder();
		for (Iterator<String> iterator = allowedFieldsList.iterator(); iterator
				.hasNext();) {
			String fieldName =  iterator.next();
			messageBuilder.append(fieldName.trim());
			if(iterator.hasNext()){
				messageBuilder.append(OR);
			}
			
		}
		
		for (String fieldName : facetByFields) {
			
			if(!allowedFieldsList.contains(fieldName)){
				 messages.add(String.format(INVALID_FIELD_VALUE, FACET_BY_FIELD, messageBuilder.toString()));
				}
		}
	
		return messages;
	}
	
	/**
     * Method 		 	: responseFormatValidation
     * Description		: This method validates the value of responseFormat field
     * @param			: String responseFormat 
     * @return			: String
     */
	public String responseFormatValidation(String responseFormat){
		String format = null;
		boolean flag = false;
		StringTokenizer tokenizer2 = new StringTokenizer(responseFormat, ",");
		while(tokenizer2.hasMoreTokens()){
			while(tokenizer2.hasMoreTokens()){
				format = (String) tokenizer2.nextToken();
				if(format.equalsIgnoreCase(JSON) || format.equalsIgnoreCase(XML))
					flag = true;
			}
		}
		if(!flag)
			return String.format(INVALID_FIELD_VALUE, RESPONSE_FORMAT, JSON+OR+XML);
		return null;
		
	}
	
	/**
     * Method 		 	: startingRowValidation
     * Description		: This method validates the value of startingRow field
     * @param			: String startingRow 
     * @return			: String
     */
	public String startingRowValidation(String startingRow){
		try{
			if(Integer.parseInt(startingRow)< STARTING_ROW_DEFAULT-1)
				return String.format(NON_NEGATIVE, STARTING_ROW);
			if(Integer.parseInt(startingRow) == STARTING_ROW_DEFAULT-1)
				return String.format(NON_ZERO, STARTING_ROW);
		}catch(NumberFormatException nfe){
			return String.format(FIELD_MUST_BE_NUMERIC, STARTING_ROW);
		}
		return null;
	}

	/**
     * Method 		 	: maxRowsValidation
     * Description		: This method validates the value of maxRows field
     * @param			: String maxRows 
     * @return			: String
     */
	public String maxRowsValidation(String maxRows){
		try{
			if(Integer.parseInt(maxRows)< ZERO)
				return String.format(NON_NEGATIVE, MAX_ROWS);
		}catch(NumberFormatException nfe){
			return String.format(FIELD_MUST_BE_NUMERIC, MAX_ROWS);
		}
		return null;
	}
	
	/**
     * Method 		 	: maxRowsGreaterThanDefaultValidation
     * Description		: This method validates if maxRows value is greater than the default 
     * @param			: String maxRows, String apiType 
     * @return			: String
     */
	public String maxRowsGreaterThanDefaultValidation(String maxRows, String apiType){
			if(apiType.equals(CACHE)){
				if(Integer.parseInt(maxRows)>Integer.parseInt(getMaxRowsCacheDefault()))
					return String.format(INVALID_FIELD_VALUE, MAX_ROWS, LESS_THAN +  OR + EQUALS_TO + getMaxRowsCacheDefault());
			}
			else{
				if(Integer.parseInt(maxRows)>Integer.parseInt(getMaxRowsDefault()))
					return String.format(INVALID_FIELD_VALUE, MAX_ROWS, LESS_THAN +  OR + EQUALS_TO + getMaxRowsDefault());
			}
			return null;
	}
	
	/**
     * Method 		 	: wildCardValidation
     * Description		: This method validates what are the fields for which wildcard can be applied 
     * @param			: Map<String, String> fieldMap 
     * @return			: String
     */
	public String wildCardValidation(Map<String, String> fieldMap){
		String msg = "";
		boolean flag = false;
		for(Map.Entry<String, String> entry: fieldMap.entrySet()){
			if(entry.getValue()!=null && entry.getValue().endsWith(WILDCARD)){
				msg = msg + entry.getKey() + OR;
				flag = true;
			}
		}
		if(flag)
			return String.format(WILDCARD_NOT_SUPPORTED_FOR_FIELD, msg.substring(0, msg.length()-OR.length()));
		
		return null;
	}
	
	/**
     * Method 		 	: spaceValidation
     * Description		: This method validates what are the fields for which spaces are allowed can be applied 
     * @param			: Map<String, String> fieldMap 
     * @return			: String
     */
	public String spaceValidation(Map<String, String> fieldMap){
		String msg = "";
		boolean flag = false;
		for(Map.Entry<String, String> entry: fieldMap.entrySet()){
			if(StringUtils.contains(entry.getValue(), " ")){
				msg = msg + entry.getKey() + OR;
				flag = true;
			}
		}
		if(flag)
			return String.format(SPACES_NOT_ALLOWED_IN_BETWEEN, msg.substring(0, msg.length()-OR.length()));
		return null;
	}
	
	/**
     * Method 		 	: spaceValidation
     * Description		: This method validates what are the fields for which spaces are allowed can be applied 
     * @param			: String versionDate 
     * @return			: String
     */
	public String versionDateValidation(String versionDate){
		if(versionDate!= null){
			try{
				if(!validateDate(versionDate))
					return String.format(INVALID_DATE_FORMAT, DATE_FORMAT);
			}catch(ParseException pe){
				return INVALID_DATE;
			}
		}
		return null;
	}
	
	private String getMaxRowsDefault(){
		return maxRowsDefault.trim();
	}
	private String getMaxRowsCacheDefault(){
		return maxRowsCacheDefault.trim();
	}
}
