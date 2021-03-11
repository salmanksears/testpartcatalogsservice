package com.searshc.hs.psc.catalog.dao;

import java.util.Map;

public interface PCatalogDao {
	public int delete(String sql, Map<String, String> map)  throws Exception;
}