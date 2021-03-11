package com.searshc.hs.psc.catalog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.searshc.hs.psc.catalog.vo.Column;
import com.searshc.hs.psc.catalog.vo.Config;

@Repository
public class ConfigDaoImpl implements ConfigDao {

	private JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDaoImpl.class);

	private static final String SELECT_BY_NAME_SQL = "select id, name, file_name, atable, actions, mapping from CATALOG_BULK_CONFIG where name = ?";

	private static final String INSERT_SQL = "insert into CATALOG_BULK_CONFIG(name, file_name, atable, actions, mapping, created_by, created_ts) values(?, ?, ?, ?, ?, ?, now())";

	private static final String DELETE_SQL = "delete from CATALOG_BULK_CONFIG where name = ?";

	@Override
	public Config selectByFile(final String name) throws Exception {

		Config config = null;

		LOGGER.debug("Selecting Config for name:{}...", name);
		
		try {
			config = jdbcTemplate.queryForObject(SELECT_BY_NAME_SQL, new RowMapper<Config>() {
				
				public Config mapRow(ResultSet rs, int rowNum) throws SQLException {

					Config item = new Config();

					item.setId(rs.getInt(1));
					item.setName(rs.getString(2));
					item.setFile(rs.getString(3));
					item.setTable(rs.getString(4));
					item.setActions(rs.getString(5));
					String jsonString = rs.getString(6);

					ObjectMapper mapper = new ObjectMapper();
					try {
						List<Column> columns = mapper.readValue(jsonString, new TypeReference<List<Column>>() {
						});
						item.setColumns(columns);
					} catch (Exception e) {
						LOGGER.error("Unable to parse JSON value for {} >>> {}", name, e.getMessage());
					}

					return item;
				}
			}, name);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Config NOT found for name:{} returning NULL...", name);
			return null;
		} catch (Exception e) {
			LOGGER.error("Failed to select Config for name:{} >>> {}", name, e.getMessage());
			throw e;
		}

		LOGGER.debug("{} SELECTED for name:{}", config, name);

		return config;
	}

	@Override
	public int insert(Config config) throws Exception {

		LOGGER.debug("Inserting Config:{}...", config);

		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(config.getColumns());

			jdbcTemplate.update(INSERT_SQL, new Object[] { config.getName(), config.getFile(), config.getTable(),
					config.getActions(), jsonString, config.getCreatedBy() });
		} catch (Exception e) {
			LOGGER.error("Failed to Insert Config:{} >>> {}... ", config, e.getMessage());
			throw e;
		}

		return 1;
	}

	@Override
	public int delete(String name) throws Exception {

		LOGGER.debug("Deleting Config:{}...", name);

		try {

			jdbcTemplate.update(DELETE_SQL, name);
		} catch (Exception e) {
			LOGGER.error("Failed to DELETE Config:{} >>> {}... ", name, e.getMessage());
			throw e;
		}

		return 1;
	}

	@Autowired
	@Qualifier("clientDataSource")
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
