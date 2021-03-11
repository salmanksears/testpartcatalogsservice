package com.searshc.hspartcatalog.pojo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;

/**
* Title			:   ValidatorOutput
* Description	:	pojo which holds messages of validation layer inf any 
* @author		:	Abhishek Jain
*/

public class ValidatorOutput implements Serializable {

	private static final long serialVersionUID = 2097958290993573951L;

	private String returnCode;
	private List<String> messages;
	
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("returnCode", returnCode);
		builder.append("messages", messages);
		return builder.toString();
	}
}
  