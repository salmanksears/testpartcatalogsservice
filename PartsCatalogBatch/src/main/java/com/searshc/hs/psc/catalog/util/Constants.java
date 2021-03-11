package com.searshc.hs.psc.catalog.util;

public interface Constants {
	
	public static String DATA_FILE_COLUMN_SEPERATOR = "\\|";
	public static String ERROR_FILE_COLUMN_SEPERATOR = ",";
	public static String FILE_DELETE = "DEL";
	public static String FILE_UPDATE = "UPD";
	public static String DATA_FILE_PREFIX = "bulk";

	public final static String DELETE_ACTION = "D";
	public final static String INSERT_ACTION = "I";
	public final static String UPDATE_ACTION = "U";

	public static String DEFAULT_UPDATE_COLUMNS = ", upd_by_id = 'BULKY', upd_by_ts = CURRENT YEAR TO SECOND ";
	public static String DEFAULT_INSERT_COLUMN_NAMES = ", upd_by_id, upd_by_ts ";
	public static String DEFAULT_INSERT_COLUMN_VALUES = ", 'BULKY', CURRENT YEAR TO SECOND ";
	public static String ITEM_ID_COLUMN_NAME = "itm_id";
	public static String PRODUCT_GROUP_ID_COLUMN_NAME = "prd_gro_id";
	public static String SUPPLIER_ID_COLUMN_NAME = "spp_id";
	public static String PART_NO_COLUMN_NAME = "orb_itm_id";

	// messages
	public static String RECORD_NOT_FOUND_ERR = "STAGE Record NOT found in database";
	public static String PROD_RECORD_NOT_FOUND_ERR = "PROD Record NOT found in database";
	public static String RECORD_NOT_FOUND_ERR_RESTRICTION_TABLE = "STAGE Record NOT found in restriction table, part did not have this restriction";
	public static String PROD_RECORD_NOT_FOUND_ERR_RESTRICTION_TABLE = "PROD Record NOT found in restriction table, part did not have this restriction";
	public static String ORDER_UPDATE_ERR = "Record not updated successfully and insert is invalid operation for:";
	public static String DUPLICATE_KEY_ERR = "Duplication Key Error";
	public static String MISSING_MASTER_TABLE_ERR = "SKU not in master table: prtxtpm_prt_mast";
	public static String INVALID_COL_CNT_ERR = "Invalid column count on record";

	public static String ERROR_FILE_NAME = "errors.txt";
	public static String EMAIL_DOMAIN = "@searshc.com";

	public static String PROCESSES_FLAG_F = "F";
	public static String PROCESSES_FLAG_P = "P";
	public static String PROCESSES_FLAG_R = "R";
	public static String BULK_AUD_KEY = "BULK-FILE-NAME";

	public static boolean INDEX_TRUE = true;
	public static boolean INDEX_FALSE = false;
	public static boolean CALC_TRUE = true;
	public static boolean CALC_FALSE = false;
	public static boolean DUP_FALSE = false;
	public static boolean DUP_TRUE = true;
}
