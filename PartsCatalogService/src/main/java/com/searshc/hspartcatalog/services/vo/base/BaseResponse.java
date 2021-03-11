/**
 * 
 */
package com.searshc.hspartcatalog.services.vo.base;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;

/**
* Title			:   BaseResponse
* Description	:	vo object for BaseResponse 
* @author		:	Abhishek Jain
*/

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseResponse", propOrder = {
 "responseCode",
 "messages",
 "processId"
}, namespace="http://vo.services.hspartcatalog.searshc.com/base/")

public abstract class BaseResponse {

	 @XmlElement(nillable=false, required=true)
	 protected String responseCode;
	 
	 @XmlElementWrapper(name="messages")
	 @XmlElement(name = "message", nillable=false, required=true)
	 protected List<String> messages;
	 
	 @XmlElement(nillable=false, required=true)
	 protected String processId;
	
	 /**
	  * Gets the value of the responseCode property.
	  * 
	  * @return
	  *     possible object is
	  *     {@link String }
	  *     
	  */
	 public String getResponseCode() {
	     return responseCode;
	 }
	
	 /**
	  * Sets the value of the responseCode property.
	  * 
	  * @param value
	  *     allowed object is
	  *     {@link String }
	  *     
	  */
	 public void setResponseCode(String value) {
	     this.responseCode = value;
	 }
	
	 /**
	  * Gets the value of the messages property.
	  * 
	  * <p>
	  * This accessor method returns a reference to the live list,
	  * not a snapshot. Therefore any modification you make to the
	  * returned list will be present inside the JAXB object.
	  * This is why there is not a <CODE>set</CODE> method for the messages property.
	  * 
	  * <p>
	  * For example, to add a new item, do as follows:
	  * <pre>
	  *    getMessages().add(newItem);
	  * </pre>
	  * 
	  * 
	  * <p>
	  * Objects of the following type(s) are allowed in the list
	  * {@link String }
	  * 
	  * 
	  */
	 public List<String> getMessages() {
	     if (messages == null) {
	         messages = new ArrayList<String>();
	     }
	     return this.messages;
	 }
	
	 public void setMessages(List<String> messages) {
	
		 this.messages = messages;
	 }
	 /**
	  * Gets the value of the processId property.
	  * 
	  */
	 public String getProcessId() {
	     return processId;
	 }
	
	 /**
	  * Sets the value of the processId property.
	  * 
	  */
	 public void setProcessId(String value) {
	     this.processId = value;
	 }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("responseCode", responseCode);
		builder.append("messages", messages);
		builder.append("processId", processId);
		return builder.toString();
	}

}
