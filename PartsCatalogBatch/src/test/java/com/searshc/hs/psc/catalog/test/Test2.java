package com.searshc.hs.psc.catalog.test;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParser;
import com.searshc.hs.psc.catalog.vo.Column;

public class Test2 {

	public static void main(String[] args) {
		
			
		try {
			String json = "[{\"name\":\"att_id\"}]";
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			List<Column> columns = mapper.readValue(json, List.class);
			for (Column column : columns) {
				System.out.println(column.toString());
				
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
