package com.searshc.hs.psc.catalog.vo;

public class Erorr {
	
	private int lineNo;
	private String record;
	private String error;
	
	public Erorr() {
	}
	
	public Erorr(int lineNo, String record, String error) {
		this.lineNo = lineNo;
		this.record = record;
		this.error = error;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Error [lineNo=");
		builder.append(lineNo);
		builder.append(", record=");
		builder.append(record);
		builder.append(", error=");
		builder.append(error);
		builder.append("]");
		return builder.toString();
	}

	public int getLineNo() {
		return lineNo;
	}

	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
