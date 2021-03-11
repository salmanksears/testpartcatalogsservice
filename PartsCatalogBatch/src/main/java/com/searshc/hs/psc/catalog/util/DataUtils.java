package com.searshc.hs.psc.catalog.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searshc.hs.psc.catalog.vo.Column;
import com.searshc.hs.psc.catalog.vo.Config;

public class DataUtils implements Constants {

	private static Logger LOGGER = LoggerFactory.getLogger(DataUtils.class);
	
	public static int getDataCnt(Config config) {
		
		int cnt = 0;
		
		List<Column> columns = config.getColumns();
		for (Column column : columns) {
			if (!column.isCalc())
				cnt++;
		}
		
		return cnt;
	}
	
	public static int getItemIdPos(Config config) {
		
		int pos = -1;
		int cnt = 0;
		
		List<Column> columns = config.getColumns();
		for (Column column : columns) {
			if(StringUtils.equals(column.getName(), ITEM_ID_COLUMN_NAME)) {
				pos = cnt;
				break;
			}	
			cnt++;
		}	
		
		return pos;
	}	
	
	public static int getIdxCnt(Config config) {
		
		int cnt = 0;
		
		List<Column> columns = config.getColumns();
		for (Column column : columns) {
			if (column.isIdx())
				cnt++;
		}
		
		return cnt;
	}
	
	public static String[] trim(String[] vals) {
		for (int i = 0; i < vals.length; i++) {
			if (vals[i] == null)
				vals[i] = "";
			else
				vals[i] = vals[i].trim();
		}

		return vals;
	}
	
	public static Map<String, String> getValMap(Config config, final String[] vals) {
		Map<String, String> map = new HashMap<String, String>();
		
		List<Column> columns = config.getColumns();
		for (Column column : columns) {
			int idx = column.getPos() - 1;
			map.put(column.getName(), vals[idx]);
		}
		
		return map;
	}
	
	public static Map<String, String> getIdxMap(Config config, final String[] vals) {
		Map<String, String> map = new HashMap<String, String>();
		
		List<Column> columns = config.getColumns();
		for (Column column : columns) {
			if(column.isIdx()) {
				int idx = column.getPos() - 1;
				map.put(column.getName(), vals[idx]);
			}
		}
		
		return map;
	}
	
	public static String[] addItemIdData(final String[] vals, int pos) {
		
		if (pos == -1)
			return vals;
		
		int idx = 0;
		
		String newVals[] = new String[vals.length + 3];
		for (int i = 0; i < vals.length; i++) {
			newVals[idx++] = vals[i];
			if(i == pos) {
				newVals[idx++] = vals[i].substring(0, 4); 
				newVals[idx++] = vals[i].substring(4, 7);
				newVals[idx++] = vals[i].substring(7);
			}
		}
	
		return newVals;
	}
}
