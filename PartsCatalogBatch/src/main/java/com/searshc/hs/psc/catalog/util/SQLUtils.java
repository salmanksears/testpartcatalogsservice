package com.searshc.hs.psc.catalog.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searshc.hs.psc.catalog.vo.Column;
import com.searshc.hs.psc.catalog.vo.Config;

public class SQLUtils implements Constants {

	private static Logger LOGGER = LoggerFactory.getLogger(SQLUtils.class);

	public static String getDeleteSQL(Config config) {

		StringBuffer sb = new StringBuffer();
		sb.append("DELETE FROM " + config.getTable());
		sb.append(getUpdWhereClause(config.getColumns()));
		String sql = sb.toString();

		LOGGER.debug("Dynamic DELETE SQL generated for {} >>> {}", config.getName(), sql);

		return sql;
	}
	
	public static String getUpdateSQL(Config config) {

		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE " + config.getTable());
		sb.append(getUpdSetClause(config.getColumns()));
		sb.append(getUpdWhereClause(config.getColumns()));
		String sql = sb.toString();

		LOGGER.debug("Dynamic UPDATE SQL generated for {} >>> {}", config.getName(), sql);

		return sql;
	}

	private static String getUpdWhereClause(List<Column> columns) {

		boolean first = true;
		StringBuffer sb = new StringBuffer();

		for (Column column : columns) {
			if (column.isIdx()) {
				if (first) {
					sb.append(" WHERE ").append(column.getName()).append(" = :").append(column.getName());
					first = false;
				} else {
					sb.append(" AND ").append(column.getName()).append(" = :").append(column.getName());
				}
			}
		}

		LOGGER.debug("WHERE clause generated >>> {}", sb.toString());

		return sb.toString();
	}

	private static String getUpdSetClause(List<Column> columns) {

		boolean first = true;
		StringBuffer sb = new StringBuffer();

		for (Column column : columns) {
			if (!column.isIdx()) {
				if (first) {
					sb.append(" SET ").append(column.getName()).append(" = :").append(column.getName());
					first = false;
				} else {
					sb.append(", ").append(column.getName()).append(" = :").append(column.getName());
				}
			}
		}

		// add defaults
		sb.append(DEFAULT_UPDATE_COLUMNS);

		LOGGER.debug("UPDATE SET clause generated >>> {}", sb.toString());

		return sb.toString();
	}

	public static String getInsertSQL(Config config) {

		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO " + config.getTable());

		sb.append(getInsIntoClause(config.getColumns()));
		sb.append(getInsValuesClause(config.getColumns()));

		String sql = sb.toString();

		LOGGER.debug("Dynamic INSERT SQL generated for {} >>> {}", config.getName(), sql);

		return sql;
	}

	private static String getInsIntoClause(List<Column> columns) {

		boolean first = true;
		StringBuffer sb = new StringBuffer();

		for (Column column : columns) {

			if (column.isDup())
				continue;

			if (first) {
				sb.append(" (").append(column.getName());
				first = false;
			} else {
				sb.append(", ").append(column.getName());
			}
		}

		sb.append(DEFAULT_INSERT_COLUMN_NAMES).append(")");

		LOGGER.debug("INSERT INTO clause generated >>> {}", sb.toString());

		return sb.toString();
	}

	private static String getInsValuesClause(List<Column> columns) {

		boolean first = true;
		StringBuffer sb = new StringBuffer();

		for (Column column : columns) {
			
			if(column.isDup())
				continue;
			
			if (first) {
				sb.append(" VALUES(").append(" :").append(column.getName());
				first = false;
			} else {
				sb.append(",  ").append(":").append(column.getName());;
			}
		}
	
		sb.append(DEFAULT_INSERT_COLUMN_VALUES).append(")");

		LOGGER.debug("INSERT VALUES clause generated >>> {}", sb.toString());

		return sb.toString();
	}
}
