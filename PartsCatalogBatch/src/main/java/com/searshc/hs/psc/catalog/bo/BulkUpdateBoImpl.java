package com.searshc.hs.psc.catalog.bo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.searshc.hs.psc.catalog.dao.CatalogDao;
import com.searshc.hs.psc.catalog.dao.ConfigDao;
import com.searshc.hs.psc.catalog.dao.PCatalogDao;
import com.searshc.hs.psc.catalog.service.EmailService;
import com.searshc.hs.psc.catalog.util.Constants;
import com.searshc.hs.psc.catalog.util.DataUtils;
import com.searshc.hs.psc.catalog.util.FileType;
import com.searshc.hs.psc.catalog.util.FileUtils;
import com.searshc.hs.psc.catalog.util.SQLUtils;
import com.searshc.hs.psc.catalog.vo.Audit;
import com.searshc.hs.psc.catalog.vo.Config;
import com.searshc.hs.psc.catalog.vo.Erorr;

@Component
public class BulkUpdateBoImpl implements BulkUpdateBo, Constants {

	@Autowired
	ConfigDao configDao;

	@Autowired
	CatalogDao catalogDao;

	@Autowired
	PCatalogDao pcatalogDao;

	@Autowired
	EmailService emailService;

	@Value("${input.dir}")
	private String inputDir;

	@Value("${archive.dir}")
	private String archiveDir;

	@Value("${work.dir}")
	private String workDir;

	private static final Logger LOGGER = LoggerFactory.getLogger(BulkUpdateBoImpl.class);

	@Override
	public void process() throws Exception {

		Audit audit = null;

		try {
			// Cleaning work directory for previous failed attempts of job
			File folder = new File(workDir);
			File[] workDirFiles = folder.listFiles();
			
			if (!ArrayUtils.isEmpty(workDirFiles)){
				for(File file:workDirFiles){
					if(file.delete()){
						LOGGER.info("Existing work file:[{}] was successfully deleted", file);
					}
				}
			}
			
			// Look for file to process...
			File[] dataFiles = FileUtils.getFile(inputDir, LastModifiedFileComparator.LASTMODIFIED_COMPARATOR);
			if (dataFiles == null) {
				LOGGER.info("ZERO files found to process exiting gracefully....");
				return;
			}

			for (File file : dataFiles) {
				// LOGGER.debug("Processing INPUT data file:{}",
				// dataFile.getPath());
				audit = new Audit();
				File errFile = null;
				String audNewVal = inputDir.concat(file.getName());
				List<Map<String, String>> partEvntAudit = catalogDao.getPartEvntAudit(Constants.BULK_AUD_KEY,
						audNewVal);
				for (Map<String, String> map : partEvntAudit) {
					audit.setFileUploadedBy(map.get("uploadedBy"));
					audit.setOrginalFileName(map.get("realFileName"));
					audit.setFileUploadTime(map.get("uploadedTime"));
				}
				audit.setFileTs(getFileTs());
				try {
					readHeader(file, audit, audNewVal);
				} catch (Exception e) {
					continue;
				}

				// Lookup how to parse the file...
				Config config = configDao.selectByFile(audit.getName());
				if (config == null) {
					LOGGER.error("Could NOT find a Config for filename:{} exiting gracefully BUT NEEDS TO BE ADDED...",
							audit.getName());
					long insertId = catalogDao.updatePartEvntAudit(Constants.PROCESSES_FLAG_F, Constants.BULK_AUD_KEY,
							audNewVal);
					audit.setFileUnprocessed(true);
					audit.setErrFile(file);
					audit.setMessage("Attached file Not Proccessed as Could NOT find a Config for filename: " + audit.getName());
					catalogDao.saveEmailDetails(insertId, audit);
					emailService.send(audit);
					if(file.delete()){
						LOGGER.info("Unprocessed file:[{}] was successfully deleted", file);
					}
					continue;
				}

				File workFile = FileUtils.newFile(config.getName(), workDir, FileType.WORK.ext(), audit.getFileTs());

				FileUtils.renameFile(file, workFile);

				if (audit.getFileOperation().equals(FILE_UPDATE))
					audit = doUpdate(config, workFile, errFile, audit);
				else
					audit = doDelete(config, workFile, errFile, audit);

				emailService.send(audit);
				FileUtils.archive(workDir, archiveDir, config.getName(), audit.getFileTs());
				if (audit.getSuccessCnt() == audit.getRecordCnt()) {
					long insertId = catalogDao.updatePartEvntAudit(Constants.PROCESSES_FLAG_P, Constants.BULK_AUD_KEY, audNewVal);
					catalogDao.saveEmailDetails(insertId, audit);
				} else if (audit.getSuccessCnt() == 0) {
					long insertId = catalogDao.updatePartEvntAudit(Constants.PROCESSES_FLAG_F, Constants.BULK_AUD_KEY, audNewVal);
					catalogDao.saveEmailDetails(insertId, audit);
				} else {
					long insertId = catalogDao.updatePartEvntAudit(Constants.PROCESSES_FLAG_R, Constants.BULK_AUD_KEY, audNewVal);
					catalogDao.saveEmailDetails(insertId, audit);
				}

			}

		} catch (Exception e) {
			LOGGER.error("Exception:{} was caught...", e.getMessage(), e);
			throw e;
		} finally {
			LOGGER.info("Updates have completed >>> {}", audit);
		}
	}

	private Audit doUpdate(Config config, File srcFile, File errFile, Audit audit) throws Exception {

		boolean first = true;
		int recordCnt = 0;
		int insertCnt = 0;
		int updateCnt = 0;
		int errorCnt = 0;
		BufferedReader br = null;
		Erorr error = null;
		FileWriter errWriter = null;

		try {

			int dataCnt = DataUtils.getDataCnt(config);
			int itemIdPos = DataUtils.getItemIdPos(config);

			br = new BufferedReader(new FileReader(srcFile));
			String line = "";

			String updSql = SQLUtils.getUpdateSQL(config);
			String insSql = SQLUtils.getInsertSQL(config);

			while ((line = br.readLine()) != null) {
				error = null;

				// 1st record is HEADER...
				if (first) {
					first = false;
					continue;
				}

				try {
					recordCnt++;
					String[] vals = line.split(DATA_FILE_COLUMN_SEPERATOR);
					if (vals == null || vals.length != dataCnt)
						throw new Exception(INVALID_COL_CNT_ERR);

					vals = DataUtils.trim(vals);
					if (itemIdPos > -1)
						vals = DataUtils.addItemIdData(vals, itemIdPos);

					Map<String, String> valMap = DataUtils.getValMap(config, vals);

					int i = 0;
					if(isValidAction(config, UPDATE_ACTION)) 
						i = catalogDao.update(updSql, valMap);
					
					if (i == 1) {
						updateCnt++;
						LOGGER.debug("{}. SUCCESSFULLY UPDATED {} record from line#{}", recordCnt, i, line);
					} else {
						if(isValidAction(config, INSERT_ACTION)) {
							catalogDao.insert(insSql, valMap);
							insertCnt++;
							LOGGER.debug("{}. SUCCESSFULLY INSERTED record from line:{}", recordCnt, line);
						} else {
							error = new Erorr(recordCnt, line, ORDER_UPDATE_ERR + audit.getName());
						}
					}
				} catch (DuplicateKeyException dupe) {
					error = new Erorr(recordCnt, line, DUPLICATE_KEY_ERR);
				} catch (Exception e) {
					if(e.getMessage().contains("Missing key in referenced table for referential constraint")){
						error = new Erorr(recordCnt, line, MISSING_MASTER_TABLE_ERR);
					} else {
						error = new Erorr(recordCnt, line, e.getMessage());
					}
				}

				if (error != null) {

					LOGGER.error("ERROR processing line#{} {} >>> {}", recordCnt, line, error.getError());

					if (errorCnt == 0) {
						errFile = FileUtils.newFile(config.getName(), workDir, FileType.ERROR.ext(), audit.getFileTs());
						errWriter = new FileWriter(errFile);
					}
					errorCnt++;
					writeError(error, errWriter);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception on doUpdates for Config:{} File:{} >>> {}", config.getName(), srcFile.getName(),
					e.getMessage());
			throw e;
		} finally {
			if (br != null)
				br.close();
			if (errWriter != null)
				errWriter.close();
		}

		audit.setSrcFile(srcFile);
		audit.setRecordCnt(recordCnt);
		// delete need to set=0 otherwise previous values are picked
		audit.setDeleteCnt(0);
		audit.setInsertCnt(insertCnt);
		audit.setUpdateCnt(updateCnt);
		audit.setErrorCnt(errorCnt);
		audit.setMessage("File Completely Proccessed");
		if (errorCnt > 0) {
			audit.setErrFile(errFile);
			audit.setMessage("File Partially Proccessed");
		}
		if (errorCnt == recordCnt) {
			audit.setErrFile(errFile);
			audit.setMessage("File Not Proccessed");
		}

		LOGGER.debug("Audit records from doUpdate for {} >>> {}", config.getName(), audit);

		return audit;
	}

	private Audit doDelete(Config config, File srcFile, File errFile, Audit audit) throws Exception {

		boolean first = true;
		int recordCnt = 0;
		int deleteCnt = 0;
		int errorCnt = 0;
		BufferedReader br = null;
		Erorr error = null;
		FileWriter errWriter = null;

		if (!isValidAction(config, DELETE_ACTION)) {
			LOGGER.error("DELETE not currently configured {} no actions will be performed", config.getName());
			audit.setSrcFile(srcFile);
			return audit;
		}

		try {

			int itemIdPos = DataUtils.getItemIdPos(config);
			int idxCnt = DataUtils.getIdxCnt(config);

			br = new BufferedReader(new FileReader(srcFile));
			String line = "";

			String sql = SQLUtils.getDeleteSQL(config);

			while ((line = br.readLine()) != null) {
				error = null;

				// 1st record is HEADER...
				if (first) {
					first = false;
					continue;
				}

				try {
					recordCnt++;
					String[] vals = line.split(DATA_FILE_COLUMN_SEPERATOR);
					if (vals == null || vals.length != idxCnt - 3)
						throw new Exception(INVALID_COL_CNT_ERR);

					vals = DataUtils.trim(vals);
					if (itemIdPos > -1)
						vals = DataUtils.addItemIdData(vals, itemIdPos);

					Map<String, String> valMap = DataUtils.getIdxMap(config, vals);

					int i = catalogDao.update(sql, valMap);
					if (i == 1) {
						deleteCnt++;
						LOGGER.debug("STAGE >>> {}. SUCCESSFULLY DELETED {} record from line:{}", recordCnt, i, line);
					} else if (audit.getName().equalsIgnoreCase("RESTRICTIONS") && audit.getFileOperation().equalsIgnoreCase("DEL")){
						LOGGER.debug("STAGE >>> Record not found on DELETE from restrictions table... for {} >>> {}", recordCnt, line);
						error = new Erorr(recordCnt, line, RECORD_NOT_FOUND_ERR_RESTRICTION_TABLE);
					} else {
						LOGGER.debug("STAGE >>> Record not found on DELETE.. for {} >>> {}", recordCnt, line);
						error = new Erorr(recordCnt, line, RECORD_NOT_FOUND_ERR);
					}

					// update against prod as there is NO real replication :(
					i = pcatalogDao.delete(sql, valMap);
					if (i == 1) {
						LOGGER.debug("PROD >>> {}. SUCCESSFULLY DELETED {} record from line:{}", recordCnt, i, line);
					} else if (audit.getName().equalsIgnoreCase("RESTRICTIONS") && audit.getFileOperation().equalsIgnoreCase("DEL")){
						LOGGER.debug("PROD >>> Record not found on DELETE from restrictions table... for {} >>> {}", recordCnt, line);
						error = new Erorr(recordCnt, line, PROD_RECORD_NOT_FOUND_ERR_RESTRICTION_TABLE);
					} else {
						LOGGER.debug("PROD >>> Record not found on DELETE.. for {} >>> {}", recordCnt, line);
						error = new Erorr(recordCnt, line, PROD_RECORD_NOT_FOUND_ERR);
					}
				} catch (Exception e) {
					LOGGER.error("ERROR deleting using line:{} >>> {}", line, e.getMessage());
					error = new Erorr(recordCnt, line, e.getMessage());
				}

				if (error != null) {
					if (errorCnt == 0) {
						errFile = FileUtils.newFile(config.getName(), workDir, FileType.ERROR.ext(), audit.getFileTs());
						errWriter = new FileWriter(errFile);
					}
					errorCnt++;
					writeError(error, errWriter);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception on doDeletes for Config:{} File:{} >>> {}", config.getName(), srcFile.getName(),
					e.getMessage());
			throw e;
		} finally {
			if (br != null)
				br.close();
			if (errWriter != null)
				errWriter.close();
		}

		audit.setSrcFile(srcFile);
		audit.setRecordCnt(recordCnt);
		audit.setDeleteCnt(deleteCnt);
		// insert & update need to set=0 otherwise previous values are picked
		audit.setInsertCnt(0);
		audit.setUpdateCnt(0);
		audit.setErrorCnt(errorCnt);
		audit.setMessage("File Completely Proccessed");
		if (errorCnt > 0) {
			audit.setErrFile(errFile);
			audit.setMessage("File Partially Proccessed");
		}
		if (errorCnt == recordCnt) {
			audit.setErrFile(errFile);
			audit.setMessage("File Not Proccessed");
		}

		LOGGER.debug("Audit records from doDeletes for {} >>> {}", config.getName(), audit);

		return audit;
	}

	private void writeError(Erorr error, FileWriter errWriter) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(error.getLineNo()).append(ERROR_FILE_COLUMN_SEPERATOR);
		sb.append(error.getRecord()).append(ERROR_FILE_COLUMN_SEPERATOR);
		sb.append("ERROR=").append(error.getError()).append(System.lineSeparator());

		errWriter.write(sb.toString());
	}

	private void readHeader(File file, Audit audit, String audNewVal) throws Exception {

		String arr[] = null;
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			LOGGER.debug("Header line read >>> {}", line);

			arr = line.split("\\|");
			arr[0] = arr[0].toUpperCase();
			arr[1] = arr[1].trim();

			audit.setName(arr[0]);
			audit.setFileOperation(arr[1]);

			if (!arr[1].equals(FILE_DELETE) && !arr[1].equals(FILE_UPDATE)) {
				LOGGER.error("Invalid file operation read Header from File:{} >>> {}", arr[1]);
				throw new Exception("Invalid Operation on File Header");
			}

		} catch (Exception e) {
			LOGGER.error("Could NOT read Header from File:{} >>> {}", file.getName(), e.getMessage());
			long insertId = catalogDao.updatePartEvntAudit(Constants.PROCESSES_FLAG_F, Constants.BULK_AUD_KEY,
					audNewVal);
			audit.setFileUnprocessed(true);
			audit.setErrFile(file);
			audit.setMessage("Attached file Not Proccessed. Either Header or file operation is invalid");
			catalogDao.saveEmailDetails(insertId, audit);
			emailService.send(audit);
			if(file.delete()){
				LOGGER.info("Unprocessed file:[{}] was successfully deleted", file);
			}
			throw new Exception("Invalid Operation or Header on file");
		} finally {
			if (br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException ioe) {
					LOGGER.error("Failed to close reader >>> {}", ioe.getMessage());
				}
			}
		}
	}

	private String getFileTs() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		return sdf.format(new Date());
	}

	private boolean isValidAction(Config config, String action) {
		return config.getActions().contains(action);
	}
}
