package com.searshc.hs.psc.catalog.dao;

import com.searshc.hs.psc.catalog.vo.Config;

public interface ConfigDao {
	public Config selectByFile(String name) throws Exception;
	public int insert(Config config) throws Exception;
	public int delete(String name) throws Exception;
}
