package com.searshc.hs.psc.catalog.vo;

import java.sql.Date;
import java.util.List;

public class Config {
	
	private int id;
	private String name;
	private String actions;
	private String file;
	private String table;
	private List<Column> columns;
	private String createdBy;
	private Date createdTs;
	private String updatedBy;
	private Date updatedTs;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Config [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", actions=");
		builder.append(actions);
		builder.append(", file=");
		builder.append(file);
		builder.append(", table=");
		builder.append(table);
		builder.append(", columns=");
		builder.append(columns);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", createdTs=");
		builder.append(createdTs);
		builder.append(", updatedBy=");
		builder.append(updatedBy);
		builder.append(", updatedTs=");
		builder.append(updatedTs);
		builder.append("]");
		return builder.toString();
	}

	public Config() {
	}
	
	public Config(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
	
	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}
	
}
