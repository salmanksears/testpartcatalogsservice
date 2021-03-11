package com.searshc.hspartcatalog.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;

public class LengthAttributes {
	
	String fieldName;
	String fieldValue;
	int length;
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("fieldName", fieldName);
		builder.append("fieldValue", fieldValue);
		builder.append("length", length);
		return builder.toString();
	}
}
