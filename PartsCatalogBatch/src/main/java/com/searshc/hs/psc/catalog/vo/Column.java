package com.searshc.hs.psc.catalog.vo;

public class Column {
	
	private String name;
	private int pos = 0;
	private boolean idx = false;
	private boolean dup = false; //used for index and data values
	private boolean calc = false;
	
	public Column() {};
	
	public Column(String name, int pos,  boolean idx, boolean calc, boolean dup) {
		this.name = name;
		this.pos = pos;
		this.idx = idx;
		this.calc = calc;
		this.dup = dup;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Column [name=");
		builder.append(name);
		builder.append(", pos=");
		builder.append(pos);
		builder.append(", idx=");
		builder.append(idx);
		builder.append(", dup=");
		builder.append(dup);
		builder.append(", calc=");
		builder.append(calc);
		builder.append("]");
		return builder.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public void setIdx(boolean idx) {
		this.idx = idx;
	}

	public boolean isIdx() {
		return idx;
	}
	
	public boolean isDup() {
		return dup;
	}

	public void setDup(boolean dup) {
		this.dup = dup;
	}

	public void setCalc(boolean calc) {
		this.calc = calc;
	}
	
	public boolean isCalc() {
		return calc;
	}
}
