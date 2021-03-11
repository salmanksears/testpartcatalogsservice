package com.searshc.hs.psc.catalog.dao;

import java.util.List;
import java.util.Map;

import com.searshc.hs.psc.catalog.vo.Audit;

public interface CatalogDao {
	public int update(String sql, Map<String, String> map)  throws Exception;
	public int insert(String sql, Map<String, String> map)  throws Exception;
	public long updatePartEvntAudit(String flag, String audKey, String audNewVal);
	public List<Map<String, String>> getPartEvntAudit(String audKey, String audNewVal);
	public void saveEmailDetails(long insertId, Audit audit);
}
