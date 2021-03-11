package com.searshc.hs.psc.catalog.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.searshc.hs.psc.catalog.util.Constants;
import com.searshc.hs.psc.catalog.util.SQLUtils;
import com.searshc.hs.psc.catalog.vo.Config;
import com.searshc.hs.psc.catalog.vo.Column;

public class Test1 implements Constants {

	private static final String DB_DRIVER = "com.informix.jdbc.IfxDriver";
	private static final String DB_CONNECTION = "jdbc:informix-sqli://hawaii.sears.com:1525/lis0d000d:informixserver=ifmx00289_tcp";
	private static final String DB_USER = "pdrp";
	private static final String DB_PASSWORD = "lis1partserv";

	private static Logger LOGGER = LoggerFactory.getLogger(SQLUtils.class);

	public static void main(String[] args) {

		Connection dbConn = null;
		PreparedStatement psmt = null;
				
		try {
			String process = "ATTRIBUTES";

			Config config = new Config(process);
			config.setFile("attributes.txt");
			config.setTable("prtxtpa_att_val");

			List<Column> columns = new ArrayList<Column>();
			
			/**
			columns.add(new Column("rsr_id", 1, INDEX_FALSE, NO_CALC, DUP_FALSE));
			columns.add(new Column("icl_xcl_cd", 2, INDEX_FALSE, NO_CALC, DUP_FALSE));
			columns.add(new Column("itm_id", 0, INDEX_FALSE, "2+3+4", DUP_FALSE));
			columns.add(new Column("prd_gro_id", 3, INDEX_TRUE, NO_CALC, DUP_FALSE));
			columns.add(new Column("spp_id", 4, INDEX_TRUE, NO_CALC, DUP_FALSE));
			columns.add(new Column("orb_itm_id", 5, INDEX_TRUE, NO_CALC, DUP_FALSE));
			columns.add(new Column("rsr_id", 6, INDEX_TRUE, NO_CALC, DUP_TRUE));
			config.setColumns(columns);
			**/
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(config);
			LOGGER.debug("JSON >>> {}", jsonInString);			
			
			/**
			List<String> values = new ArrayList<String>();
			values.add("152|999999|WJ14X111|0000|363");

			StringBuffer sb = new StringBuffer();
			sb.append(SQLUtils.getTableClause(config.getTable()));
			sb.append(SQLUtils.getSetClause(columns));
			sb.append(SQLUtils.getWhereClause(columns));
			String sql = sb.toString();
			LOGGER.debug("SQL generated >>> {}", sql);

			dbConn = getDBConnection();
			LOGGER.debug("Connection created...");

			psmt = dbConn.prepareStatement(sql);
			LOGGER.debug("PreparedStatement created...");

			String data = values.get(0);
			String[] arr = data.split(FILE_COLUMN_SEPERATOR);

			int paramIdx = 1;
			for (String string : arr) {
				psmt.setString(paramIdx++, string);
			}

			psmt.executeUpdate();
			LOGGER.debug("ExecuteUpdate successfull...");
			**/
			
		} catch (Exception e) {
			LOGGER.error("{}", e.getMessage());
		} finally {
			try {
				psmt.close();
			} catch (SQLException sqle) {
				LOGGER.error("Failed to close PSMT >>> {}", sqle.getMessage());
			}

			try {
				dbConn.close();
			} catch (SQLException sqle) {
				LOGGER.error("Failed to close CONN >>> {}", sqle.getMessage());
			}

		}
	}

	private static Connection getDBConnection() {

		Connection conn = null;

		try {
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}
}
