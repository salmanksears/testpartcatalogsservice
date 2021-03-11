package com.searshc.hs.psc.catalog.dao;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PCatalogDaoImpl implements PCatalogDao {

	private  NamedParameterJdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(PCatalogDaoImpl.class);

	@Override
	public int delete(String sql, Map<String, String> map) throws Exception {
		LOGGER.debug("Data values for DELETE >>> {}", StringUtils.join(map.values(), '|'));
		return jdbcTemplate.update(sql, map);
	}
	
	@Autowired
	@Qualifier("pCatalogDataSource")
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
}