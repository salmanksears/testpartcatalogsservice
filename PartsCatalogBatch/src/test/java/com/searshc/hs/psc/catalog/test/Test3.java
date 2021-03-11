package com.searshc.hs.psc.catalog.test;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.searshc.hs.psc.catalog.util.Constants;
import com.searshc.hs.psc.catalog.vo.Column;

public class Test3  implements Constants {

	public static void main(String[] args) {
		
		try {
			
			List<Column> columns = new ArrayList<Column>();
			/**
			columns.add(new Column("rsr_id", 1, INDEX_FALSE, NO_CALC, DUP_FALSE));
			columns.add(new Column("icl_xcl_cd", 2, INDEX_FALSE, NO_CALC, DUP_FALSE));
			columns.add(new Column("itm_id", 0, INDEX_FALSE, "2+3+4", DUP_FALSE));
			columns.add(new Column("prd_gro_id", 3, INDEX_TRUE, NO_CALC, DUP_FALSE));
			columns.add(new Column("spp_id", 4, INDEX_TRUE, NO_CALC, DUP_FALSE));
			columns.add(new Column("orb_itm_id", 5, INDEX_TRUE, NO_CALC, DUP_FALSE));
			columns.add(new Column("rsr_id", 6, INDEX_TRUE, NO_CALC, DUP_TRUE));
			**/
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(columns);
			System.out.println(">>>>> " + jsonInString + " <<<<<");
			
			List<Column> columns2 = mapper.readValue(jsonInString, new TypeReference<List<Column>>(){});
			
			for (Column column : columns2) {
				System.out.println(column);
			}
			
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
