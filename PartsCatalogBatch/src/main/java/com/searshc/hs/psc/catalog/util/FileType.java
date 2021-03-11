package com.searshc.hs.psc.catalog.util;

public enum FileType {
	ARCHIVE("zip"), DATA("txt"), ERROR("err"), WORK("wrk");

	private String ext;

	FileType(String ext) {
		this.ext = ext;
	}

	public String ext() {
		return ext;
	}
}
