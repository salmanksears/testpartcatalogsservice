package com.searshc.hspartcatalog.services.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.searshc.hs.psc.logging.annotation.ExtCallAuditLogger;
import com.searshc.hspartcatalog.pojo.Brand;
import com.searshc.hspartcatalog.pojo.Category;
import com.searshc.hspartcatalog.pojo.ModelPart;
import com.searshc.hspartcatalog.services.domain.Accessory;
import com.searshc.hspartcatalog.services.domain.Maintenance;
import com.searshc.hspartcatalog.services.domain.PartDetail;
import com.searshc.hspartcatalog.services.domain.PartDetailReturn;
import com.searshc.hspartcatalog.services.domain.Trans;
import com.searshc.hspartcatalog.services.vo.request.GetCoreAndEnvChargeAmountRequest;

public class PartsCatalogDao {

	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final String CARET_CHAR = "^";
	private static final String ASTERICK_CHAR = "*";
	
	private static final Logger logger = LoggerFactory.getLogger(PartsCatalogDao.class);

	static final String SELECT_MODEL_PARTS = "SELECT distinct trim(pr.doc_pg_id) as docPageId, trim(pp.prd_gro_id)||trim(pp.spp_id)||trim(pp.orb_itm_id) as itemId, "
			+ "case when pp.key_suf_id = 'NA' then trim(pp.key_id) else trim(pp.key_id)||trim(pp.key_suf_id) end as itemKeyId "
			+ "FROM prtxtpr_page_sum pr, mxsvtpp pp " + "WHERE pr.doc_pg_id IN (:schematics) "
			+ "AND pr.pg_id != '00000' " + "AND pr.doc_id = pp.doc_id " + "AND pr.pg_id = pp.pg_id";

	static final String SELECT_MODEL_PARTS_BY_DOCPAGE = "SELECT DISTINCT TRIM(pp.doc_id)||TRIM(pp.pg_id)as docPageId, TRIM(pp.prd_gro_id)||TRIM(pp.spp_id)||TRIM(pp.orb_itm_id) as itemId, "
			+ "CASE WHEN pp.key_suf_id = 'NA' THEN TRIM(pp.key_id) ELSE TRIM(pp.key_id)||TRIM(pp.key_suf_id) END AS itemKeyId "
			+ "FROM mxsvtvd vd, mxsvtpp pp " + "WHERE  vd.prd_typ_id = :productTypeId " + "AND  vd.bnd_id = :brandId "
			+ "AND vd.mdl_nm = :modelNo " + "AND (vd.mdl_nm = pp.mdl_nm OR pp.mdl_nm='x')" +"AND vd.pg_id = pp.pg_id " + "AND vd.doc_id = pp.doc_id and (";

	static final String SELECT_MODEL_PARTS_BY_DOCPAGE2 = "SELECT DISTINCT TRIM(pp.doc_id)||TRIM(pp.pg_id)as docPageId, TRIM(pp.prd_gro_id)||TRIM(pp.spp_id)||TRIM(pp.orb_itm_id) as itemId, "
			+ "CASE WHEN pp.key_suf_id = 'NA' THEN TRIM(pp.key_id) ELSE TRIM(pp.key_id)||TRIM(pp.key_suf_id) END AS itemKeyId "
			+ "FROM mxsvtvd vd, mxsvtpp pp " + "WHERE  vd.prd_typ_id = :productTypeId " + "AND  vd.bnd_id = :brandId "
			+ "AND vd.mdl_nm = :modelNo " + "AND vd.doc_id = :docId " + "AND vd.pg_id = pp.pg_id "
			+ "AND vd.doc_id = pp.doc_id " + "AND vd.pg_id IN (";

	private String SELECT_ACCESSORY_PARTS_BY_MODEL = "SELECT FIRST %s DISTINCT pp.prd_gro_id as productGroupId, pp.spp_id as supplierId, TRIM(pp.orb_itm_id) as partNo, " 
			+ "CASE WHEN ms.orb_itm_id IS NOT NULL THEN TRIM(ms.mst_prt_ds) ELSE TRIM(sm.ds_tx) END as partDs, img.prt_img_url as imageUrl, sm.rnk_no as rank "
			+ "FROM mxsvtvd vd, mxsvtpp pp, prtxtpm_prt_mast ms, prtxtps_prt_summ sm left outer join prtxtpi_prt_img img on (img.orb_itm_id=sm.orb_itm_id and img.prd_gro_id = sm.prd_gro_id and img.spp_id = sm.spp_id) "
			+ "WHERE  vd.prd_typ_id = :productTypeId AND  vd.bnd_id = :brandId AND vd.mdl_nm = :modelNo "
			+ "AND vd.pg_id = pp.pg_id AND vd.doc_id = pp.doc_id " + "AND pp.prd_gro_id = sm.prd_gro_id "
			+ "AND pp.spp_id = sm.spp_id " + "AND pp.orb_itm_id = sm.orb_itm_id " + "AND ms.prd_gro_id = sm.prd_gro_id "
			+ "AND ms.spp_id = sm.spp_id " + "AND ms.orb_itm_id = sm.orb_itm_id " + "AND ms.acy_prt_fl = 'Y'  AND sm.avail != 'NLA' "
			+ "ORDER BY sm.rnk_no desc";
	 
	private String SELECT_MAINTENANCE_PARTS_BY_MODEL = "SELECT FIRST %s DISTINCT pp.prd_gro_id as productGroupId, pp.spp_id as supplierId, TRIM(pp.orb_itm_id) as partNo, pt.prt_typ_nm as partTypeTermName, pt.prt_lbl as label, " 
                       + "CASE WHEN ms.orb_itm_id IS NOT NULL THEN TRIM(ms.mst_prt_ds) ELSE TRIM(sm.ds_tx) END as partDs, img.prt_img_url as imageUrl, sm.rnk_no as rank "
                       + "FROM mxsvtvd vd, mxsvtpp pp, prtxtps_prt_summ sm left outer join prtxtpi_prt_img img on (img.orb_itm_id=sm.orb_itm_id and img.prd_gro_id = sm.prd_gro_id and img.spp_id = sm.spp_id), prtxtpm_prt_mast ms, prtxtpa_att_val pa, prtxtpt_prt_typ pt "
                       + "WHERE  vd.prd_typ_id = :productTypeId AND  vd.bnd_id = :brandId AND vd.mdl_nm = :modelNo "
                       + "AND vd.pg_id = pp.pg_id AND vd.doc_id = pp.doc_id " + "AND pp.prd_gro_id = sm.prd_gro_id "
                       + "AND pp.spp_id = sm.spp_id " + "AND pp.orb_itm_id = sm.orb_itm_id " + "AND ms.prd_gro_id = sm.prd_gro_id "
                       + "AND ms.spp_id = sm.spp_id " + "AND ms.orb_itm_id = sm.orb_itm_id " + "AND sm.avail != 'NLA' "
                       + "AND ms.orb_itm_id = pa.orb_itm_id AND ms.prd_gro_id = pa.prd_gro_id AND ms.spp_id = pa.spp_id AND pa.att_id = 152 "
                       + "AND pa.att_val_hi = pt.prt_typ_id "   
                       + "ORDER BY sm.rnk_no desc";
	
	static final String SELECT_ALL_BRAND = "select trim(bnd_id), ds_tx from mxsvtbr";
	
	static final String SELECT_ALL_CATEGORY = "select trim(prd_typ_id), ds_tx  from mxsvtpt where par_prd_typ_id is not null and  par_prd_typ_id !=  '' and  ds_tx != 'Unassigned'  and ds_tx NOT LIKE 'Sears Service Flashes%' and " 
			+ "ds_tx NOT LIKE 'Sears Training Bulletin%' and ds_tx NOT LIKE 'Manufacturer Service Bulletin%' and ds_tx NOT LIKE 'Sears Training Bulletin%' and " 
			+ "ds_tx NOT LIKE 'Reference Information%' and ds_tx NOT LIKE 'Rapid Communications%' and ds_tx NOT LIKE 'Div%' and "
			+ "ds_tx NOT LIKE 'Sears Service Mgmt Flash%' and ds_tx NOT LIKE 'Major Brand PLS Source%' and ds_tx NOT LIKE 'NPA%'";
		
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@ExtCallAuditLogger(logInput = false, logOutput = false)
	public Map<String, ModelPart> getModelParts(List<String> schematicIds) {

		ParameterizedRowMapper<ModelPart> mapper = new ParameterizedRowMapper<ModelPart>() {
			public ModelPart mapRow(ResultSet rs, int rowNum) throws SQLException {

				ModelPart o = new ModelPart();
				o.setDocPageId(rs.getString("docPageId"));
				o.setItemId(rs.getString("itemId"));
				o.setItemKeyId(rs.getString("itemKeyId"));

				return o;
			}
		};

		Map<String, List<String>> namedParameters = Collections.singletonMap("schematics", schematicIds);

		List<ModelPart> modelParts = this.jdbcTemplate.query(SELECT_MODEL_PARTS, namedParameters, mapper);

		logger.debug("selectModelParts - There were a total of {} Part(s) selected for Schematics:{}...",
				modelParts.size(), StringUtils.join(schematicIds, "|"));

		Map<String, ModelPart> map = new HashMap<String, ModelPart>();

		// turn into MAP using itemId as the key
		for (ModelPart modelPart : modelParts) {
			map.put(modelPart.getItemId(), modelPart);
		}

		return map;
	}

	@ExtCallAuditLogger(logInput = false, logOutput = false)
	public List<ModelPart> getModelPartsByDocPage(List<String> schematicIds, String productTypeId,
			String brandId, String modelNo) {

		ParameterizedRowMapper<ModelPart> mapper = new ParameterizedRowMapper<ModelPart>() {
			public ModelPart mapRow(ResultSet rs, int rowNum) throws SQLException {

				ModelPart o = new ModelPart();
				o.setDocPageId(rs.getString("docPageId"));
				o.setItemId(rs.getString("itemId"));
				o.setItemKeyId(rs.getString("itemKeyId"));

				return o;
			}
		};

		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("productTypeId", productTypeId);
		namedParameters.put("brandId", brandId);
		namedParameters.put("modelNo", modelNo);

		// create doc and page AND clause (and ( (pp.doc_id = “WA001701” and
		// pp.pg_id = “00001”) OR (....)
		StringBuilder sb = new StringBuilder();
		boolean first = true;

		String docId = "";
		String pageId = "";

		for (String schematicId : schematicIds) {
			// last 5 bytes are page ID
			docId = schematicId.substring(0, schematicId.length() - 5);
			pageId = schematicId.substring(schematicId.length() - 5);
			if (first) {
				sb.append(" (pp.doc_id = '").append(docId).append("' AND pp.pg_id = '").append(pageId).append("')");
				first = false;
			} else {
				sb.append(" OR (pp.doc_id = '").append(docId).append("' AND pp.pg_id = '").append(pageId).append("')");
			}
		}
		sb.append(")");

		String sql = SELECT_MODEL_PARTS_BY_DOCPAGE + sb.toString();

		logger.debug("selectModelParts - generated SQL >>> {}", sql);

		List<ModelPart> modelParts = this.jdbcTemplate.query(sql, namedParameters, mapper);
		
		if(null != modelParts){
			logger.debug(
					"selectModelParts - There were a total of {} Part(s) selected for ProductTypeId:{} BrandId:{} ModelNo:{}  Schematics:{}...",
					modelParts.size(), productTypeId, brandId, modelNo, StringUtils.join(schematicIds, "|"));
		}

		return modelParts;
	}

	@ExtCallAuditLogger(logInput = false, logOutput = false)
	public Map<String, ModelPart> getModelPartsByDocPage2(List<String> schematicIds, String productTypeId,
			String brandId, String modelNo) {

		ParameterizedRowMapper<ModelPart> mapper = new ParameterizedRowMapper<ModelPart>() {
			public ModelPart mapRow(ResultSet rs, int rowNum) throws SQLException {

				ModelPart o = new ModelPart();
				o.setDocPageId(rs.getString("docPageId"));
				o.setItemId(rs.getString("itemId"));
				o.setItemKeyId(rs.getString("itemKeyId"));

				return o;
			}
		};

		// document should be same across pull from 1st one...
		String schematic = schematicIds.get(0);
		String docId = schematic.substring(0, schematic.length() - 5);

		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("productTypeId", productTypeId);
		namedParameters.put("brandId", brandId);
		namedParameters.put("modelNo", modelNo);
		namedParameters.put("docId", docId);

		StringBuilder sb = new StringBuilder();

		String pageId = "";

		boolean first = true;

		for (String schematicId : schematicIds) {
			// last 5 bytes are page ID
			pageId = schematicId.substring(schematicId.length() - 5);
			if (first)
				first = false;
			else
				sb.append(", ");

			sb.append("'").append(pageId).append("'");
		}

		sb.append(")");

		String sql = SELECT_MODEL_PARTS_BY_DOCPAGE2 + sb.toString();

		logger.debug("selectModelParts - generated SQL >>> {}", sql);

		List<ModelPart> modelParts = this.jdbcTemplate.query(sql, namedParameters, mapper);

		logger.debug(
				"selectModelParts - There were a total of {} Part(s) selected for ProductTypeId:{} BrandId:{} ModelNo:{}  Schematics:{}...",
				modelParts.size(), productTypeId, brandId, modelNo, StringUtils.join(schematicIds, "|"));

		Map<String, ModelPart> map = new HashMap<String, ModelPart>();

		// turn into MAP using itemId as the key
		for (ModelPart modelPart : modelParts) {
			map.put(modelPart.getItemId(), modelPart);
		}

		return map;
	}

	@ExtCallAuditLogger(logInput = false, logOutput = false)
	public List<Accessory> getAccessoryByModel(String productTypeId, String brandId, String modelNo, String limit) {

		String accessoryPartsSql = String.format(SELECT_ACCESSORY_PARTS_BY_MODEL, limit);
		
		ParameterizedRowMapper<Accessory> accessoryPartsMapper = new ParameterizedRowMapper<Accessory>() {
			public Accessory mapRow(ResultSet rs, int rowNum) throws SQLException {

				Accessory o = new Accessory();
				o.setProductGroupId(rs.getString("productGroupId"));
				o.setSupplierId(rs.getString("supplierId"));
				o.setPartNo(rs.getString("partNo"));
				o.setPartDs(rs.getString("partDs"));
				o.setImageUrl(rs.getString("imageUrl"));
				o.setRank(rs.getInt("rank"));
				return o;
			}
		};
		
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("productTypeId", productTypeId);
		namedParameters.put("brandId", brandId);
		namedParameters.put("modelNo", modelNo);
		
		List<Accessory> accessoryParts = this.jdbcTemplate.query(accessoryPartsSql, namedParameters, accessoryPartsMapper);
		
		logger.debug(
				"getAccessoryByModel - There were a total of {} AccessoryPart(s) selected for ProductTypeId:{} BrandId:{} ModelNo:{}...",
				accessoryParts.size(), productTypeId, brandId, modelNo);
		
		return accessoryParts;
	}
	
	@ExtCallAuditLogger(logInput = false, logOutput = false)
	public List<Maintenance> getMaintenancePartsByModel(String productTypeId, String brandId, String modelNo, String limit) {

		String maintenancePartsSql = String.format(SELECT_MAINTENANCE_PARTS_BY_MODEL, limit);

		ParameterizedRowMapper<Maintenance> maintenancePartsMapper = new ParameterizedRowMapper<Maintenance>() {
			public Maintenance mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Maintenance o = new Maintenance();
				o.setProductGroupId(rs.getString("productGroupId"));
				o.setSupplierId(rs.getString("supplierId"));
				o.setPartNo(rs.getString("partNo"));
				o.setPartDs(rs.getString("partDs"));
				o.setImageUrl(rs.getString("imageUrl"));
				o.setRank(rs.getInt("rank"));
				o.setLabel(rs.getString("label"));
				o.setPartTypeName(rs.getString("partTypeTermName"));
				return o;
			}
		};

		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("productTypeId", productTypeId);
		namedParameters.put("brandId", brandId);
		namedParameters.put("modelNo", modelNo);
		
		List<Maintenance> maintenanceParts = this.jdbcTemplate.query(maintenancePartsSql, namedParameters, maintenancePartsMapper);

		logger.debug(
				"getMaintenancePartsByModel - There were a total of {} MaintenancePart(s) selected for ProductTypeId:{} BrandId:{} ModelNo:{}...",
				maintenanceParts.size(), productTypeId, brandId, modelNo);
		
		return maintenanceParts;
	}
	
	
	@ExtCallAuditLogger(logInput = false, logOutput = false)
	public List<PartDetailReturn> getCoreAndEnvChargeAmount(GetCoreAndEnvChargeAmountRequest coreAndEnvChargeAmountRequest) {

		ParameterizedRowMapper<PartDetailReturn> mapper = new ParameterizedRowMapper<PartDetailReturn>() {
			public PartDetailReturn mapRow(ResultSet rs, int rowNum) throws SQLException {

				PartDetailReturn o = new PartDetailReturn();
				o.setCoreChargeAmt(rs.getDouble("coreChargeAmt"));
				o.setDivNo(rs.getString("divNo"));
				o.setEnvironmentChargeAmt(rs.getDouble("environmentChargeAmt"));
				o.setPartNo(rs.getString("partNo"));
				o.setPlsNo(rs.getString("plsNo"));
				o.setStateCode(rs.getString("stateCode"));

				return o;
			}
		};
		
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		
		Trans trans = coreAndEnvChargeAmountRequest.getTrans();
		String transCode = trans.getTransCode();
		String stateCode = trans.getStateCode();
		List<PartDetail> partDetails = coreAndEnvChargeAmountRequest.getPartDetails();
		String query = null;
		List<String> itemIds = new ArrayList<String>();
		List<PartDetailReturn> parts = null;
		
		if(transCode.equals("S") || transCode.equals("B")){
			if(StringUtils.isNotBlank(stateCode)&& stateCode.length() == 2){
				if(transCode.equals("B")){
					for(PartDetail part :partDetails){
						itemIds.add(StringUtils.leftPad(part.getDivNo(), 4, '0') + StringUtils.leftPad(part.getPlsNo(), 3, '0') + part.getPartNo());
					}
					query = "SELECT core_am as coreChargeAmt, envir_am as environmentChargeAmt, orb_itm_id as partNo, prd_gro_id as divNo, spp_id as plsNo, ste_cd as stateCode FROM prtxtcp_core_prt WHERE itm_id IN (:itemIds) and ste_cd = :stateCd";
					namedParameters.put("stateCd",stateCode);
					namedParameters.put("itemIds",itemIds);
					parts = this.jdbcTemplate.query(query, namedParameters, mapper);
				}
				else{
					query = "SELECT core_am as coreChargeAmt, envir_am as environmentChargeAmt, orb_itm_id as partNo, prd_gro_id as divNo, spp_id as plsNo, ste_cd as stateCode FROM prtxtcp_core_prt WHERE ste_cd = :stateCd";
					namedParameters.put("stateCd",stateCode);
					parts = this.jdbcTemplate.query(query, namedParameters, mapper);
				}
			}
		}
		else if(transCode.equals("P")){
			for(PartDetail part :partDetails){
				itemIds.add(StringUtils.leftPad(part.getDivNo(), 4, '0') + part.getPlsNo() + part.getPartNo());
			}
			query = "SELECT core_am as coreChargeAmt, envir_am as environmentChargeAmt, orb_itm_id as partNo, prd_gro_id as divNo, spp_id as plsNo, ste_cd as stateCode FROM prtxtcp_core_prt WHERE itm_id IN (:itemIds)";
			namedParameters.put("itemIds",itemIds);
			parts = this.jdbcTemplate.query(query, namedParameters, mapper);
		}
		//transCode is A
		else{
			query = "SELECT core_am as coreChargeAmt, envir_am as environmentChargeAmt, orb_itm_id as partNo, prd_gro_id as divNo, spp_id as plsNo, ste_cd as stateCode FROM prtxtcp_core_prt";
			parts = this.jdbcTemplate.query(query, namedParameters, mapper);
		}
		
		logger.debug("getCoreAndEnvChargeAmount - generated SQL >>> {}", query);
		return parts;
	}
	
	@ExtCallAuditLogger(logInput = false, logOutput = false)
	public List<Brand> getBrands() {

		ParameterizedRowMapper<Brand> mapper = new ParameterizedRowMapper<Brand>() {
			public Brand mapRow(ResultSet rs, int rowNum) throws SQLException {
				Brand o = new Brand();
				o.setId(rs.getString(1));
				o.setName(rs.getString(2));
				
				return o;
			}
		};
	
		List<Brand> list = this.jdbcTemplate.query(SELECT_ALL_BRAND, mapper);

		logger.debug(
				"getBrands - There were a total of {} Brands selected...", 	list.size());
		
		return list;
	}
	
	@ExtCallAuditLogger(logInput = false, logOutput = false)
	public List<Category> getCategories() {

		ParameterizedRowMapper<Category> mapper = new ParameterizedRowMapper<Category>() {
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				Category o = new Category();
				o.setId(rs.getString(1));
				String val  = rs.getString(2);
				if(StringUtils.endsWith(val, CARET_CHAR))
					val = StringUtils.stripEnd(val, CARET_CHAR); 
				
				if(StringUtils.endsWith(val,  ASTERICK_CHAR))
					val = StringUtils.stripEnd(val, ASTERICK_CHAR); 
				
				o.setName(val);
				
				return o;
			}
		};
	
		List<Category> list = this.jdbcTemplate.query(SELECT_ALL_CATEGORY, mapper);

		logger.debug(
				"getCategories - There were a total of {} Categories selected...", 	list.size());
		
		return list;
	}
	
}
