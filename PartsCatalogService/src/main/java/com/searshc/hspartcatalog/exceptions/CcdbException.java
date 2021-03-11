/**
 * 
 */
package com.searshc.hspartcatalog.exceptions;

/**
* Title			:   CcdbException
* 
* Description	:	custom exception class for client config related exceptions  
*
* @author		:	Abhishek Jain
*/

public class CcdbException extends Exception{

	public CcdbException() {
		super();
	}
	
	public CcdbException(String msg){
		super(msg);
	}
	
	public CcdbException(String msg, Exception e){
		super(msg, e);
	}
}
