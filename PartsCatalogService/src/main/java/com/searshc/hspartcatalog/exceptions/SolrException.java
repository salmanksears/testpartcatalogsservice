/**
 * 
 */
package com.searshc.hspartcatalog.exceptions;

/**
* Title			:   SolrException
* 
* Description	:	custom exception class to for SOLR layer exceptions  
*
* @author		:	Abhishek Jain
*/

public class SolrException extends Exception{

	private static final long serialVersionUID = -7026736881682775567L;

	public SolrException() {
		super();
	}
	
	public SolrException(String msg){
		super(msg);
	}
	
	public SolrException(String msg, Exception e){
		super(msg, e);
	}
}
