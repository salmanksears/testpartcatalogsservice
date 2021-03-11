package com.searshc.hs.psc.catalog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.searshc.hs.psc.catalog.util.Constants;
import com.searshc.hs.psc.catalog.vo.Audit;

@Repository
public class CatalogDaoImpl implements CatalogDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogDaoImpl.class);

	private static final String SELECT_AUDIT = "Select aud_old_val as realFileName , upd_by_id as uploadedBy, evnt_ts as uploadedTime From prtxtea_prt_evnt_aud Where aud_key = :audKey  and aud_new_val = :audNewVal";

	String UPDATE_AUDIT = "update prtxtea_prt_evnt_aud set process_flg = :processFlg  where aud_key = :audKey  and aud_new_val = :audNewVal";
	String FETCH_AUDIT = "Select evnt_aud_id from prtxtea_prt_evnt_aud where process_flg = :processFlg  and aud_key = :audKey  and aud_new_val = :audNewVal";
	String MAXID = "Select MAX(eml_det_id) as maxId from prtxted_prt_eml_det";
	String INSERT_EMAIL_DETAILS = "insert into prtxted_prt_eml_det (eml_det_id,tot_read_rec,tot_skp_rec,tot_prc_rec,aud_key,evnt_aud_id)values (:eml_det_id,:tot_read_rec,:tot_skp_rec,:tot_prc_rec,:aud_key,:evnt_aud_id)";

	@Override
	public int update(String sql, Map<String, String> map) throws Exception {
		LOGGER.debug("Data values for UPDATE >>> {}", StringUtils.join(map.values(), '|'));
		return jdbcTemplate.update(sql, map);
	}

	@Override
	public int insert(String sql, Map<String, String> map) throws Exception {
		LOGGER.debug("Data values for INSERT >>> {}", StringUtils.join(map.values(), '|'));
		return jdbcTemplate.update(sql, map);
	}

	@Override
	public long updatePartEvntAudit(String processFlg, String audKey, String audNewVal) {
		LOGGER.info("Processed Flag : {}", processFlg);
		LOGGER.info("Aud Key : {}", audKey);
		LOGGER.info("Aud New Value : {}", audNewVal);
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("processFlg", processFlg);
		namedParameters.addValue("audKey", audKey);
		namedParameters.addValue("audNewVal", audNewVal);
		jdbcTemplate.update(UPDATE_AUDIT, namedParameters);
		return jdbcTemplate.query(FETCH_AUDIT, namedParameters, new ResultSetExtractor<Long>() {

			@Override
			public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
				rs.next();
				return rs.getLong("evnt_aud_id");
			}
		});
	}

	@Autowired
	@Qualifier("catalogDataSource")
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Map<String, String>> getPartEvntAudit(String audKey, String audNewVal) {
		LOGGER.info("Aud Key : {}", audKey);
		LOGGER.info("Aud New Value : {}", audNewVal);
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("audKey", audKey);
		namedParameters.addValue("audNewVal", audNewVal);
		List<Map<String, String>> result = null;
		try {
			result = jdbcTemplate.query(SELECT_AUDIT, namedParameters,
					new ParameterizedRowMapper<Map<String, String>>() {
						@Override
						public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
							Map<String, String> result = new HashMap<String, String>();
							result.put("realFileName", rs.getString("realFileName"));
							result.put("uploadedBy", rs.getString("uploadedBy"));
							result.put("uploadedTime", rs.getString("uploadedTime"));
							return result;
						}
					});
		} catch (Exception e) {
			LOGGER.error("EXCEPTION-->" + e.getMessage());
			throw e;
		}
		return result;
	}

	@Override
	public void saveEmailDetails(long insertId, Audit audit) {
		LOGGER.info("Last evnt aud id : {}", insertId);
		LOGGER.info("Aud Key : {}", Constants.BULK_AUD_KEY);
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		Long maxId = jdbcTemplate.query(MAXID, namedParameters, new ResultSetExtractor<Long>() {

			@Override
			public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
				rs.next();
				return rs.getLong("maxId");
			}
		});
		namedParameters.addValue("eml_det_id", maxId == null ? (long) 1 : maxId + 1);
		namedParameters.addValue("tot_read_rec", audit.getRecordCnt());
		namedParameters.addValue("tot_skp_rec", audit.getErrorCnt());
		namedParameters.addValue("tot_prc_rec", audit.getInsertCnt() + audit.getUpdateCnt() + audit.getDeleteCnt());
		namedParameters.addValue("aud_key", Constants.BULK_AUD_KEY);
		namedParameters.addValue("evnt_aud_id", insertId);
		jdbcTemplate.update(INSERT_EMAIL_DETAILS, namedParameters);

	}
}
