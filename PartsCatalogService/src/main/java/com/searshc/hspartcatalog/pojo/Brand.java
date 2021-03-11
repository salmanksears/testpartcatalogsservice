package com.searshc.hspartcatalog.pojo;

public class Brand {
	
	private String id;
	private String name;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Brand [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
