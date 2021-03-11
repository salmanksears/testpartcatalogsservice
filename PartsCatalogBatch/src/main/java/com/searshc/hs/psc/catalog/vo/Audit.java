package com.searshc.hs.psc.catalog.vo;

import java.io.File;

public class Audit {

	private String name;
	private String fileOperation;
	private String fileTs;
	private int recordCnt = 0;
	private int deleteCnt = 0;
	private int insertCnt = 0;
	private int updateCnt = 0;
	private int errorCnt = 0;
	private File srcFile;
	private File errFile;
	private String orginalFileName;
	private String fileUploadedBy;
	private String fileUploadTime;
	private String message;
	private boolean fileUnprocessed;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOrginalFileName() {
		return orginalFileName;
	}

	public void setOrginalFileName(String orginalFileName) {
		this.orginalFileName = orginalFileName;
	}

	public String getFileUploadedBy() {
		return fileUploadedBy;
	}

	public void setFileUploadedBy(String fileUploadedBy) {
		this.fileUploadedBy = fileUploadedBy;
	}

	public String getFileUploadTime() {
		return fileUploadTime;
	}

	public void setFileUploadTime(String fileUploadTime) {
		this.fileUploadTime = fileUploadTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileOperation() {
		return fileOperation;
	}

	public void setFileOperation(String fileOperation) {
		this.fileOperation = fileOperation;
	}

	public String getFileTs() {
		return fileTs;
	}

	public void setFileTs(String fileTs) {
		this.fileTs = fileTs;
	}

	public File getSrcFile() {
		return srcFile;
	}

	public void setSrcFile(File srcFile) {
		this.srcFile = srcFile;
	}

	public int getRecordCnt() {
		return recordCnt;
	}

	public void setRecordCnt(int recordCnt) {
		this.recordCnt = recordCnt;
	}

	public int getInsertCnt() {
		return insertCnt;
	}

	public void setInsertCnt(int insertCnt) {
		this.insertCnt = insertCnt;
	}

	public int getUpdateCnt() {
		return updateCnt;
	}

	public void setUpdateCnt(int updateCnt) {
		this.updateCnt = updateCnt;
	}

	public int getSuccessCnt() {
		return insertCnt + updateCnt + deleteCnt;
	}

	public int getDeleteCnt() {
		return deleteCnt;
	}

	public void setDeleteCnt(int deleteCnt) {
		this.deleteCnt = deleteCnt;
	}

	public int getErrorCnt() {
		return errorCnt;
	}

	public void setErrorCnt(int errorCnt) {
		this.errorCnt = errorCnt;
	}

	public File getErrFile() {
		return errFile;
	}

	public void setErrFile(File errFile) {
		this.errFile = errFile;
	}

	public boolean isFileUnprocessed() {
		return fileUnprocessed;
	}

	public void setFileUnprocessed(boolean fileUnprocessed) {
		this.fileUnprocessed = fileUnprocessed;
	}

	@Override
	public String toString() {
		return "Audit [name=" + name + ", fileOperation=" + fileOperation + ", fileTs=" + fileTs + ", recordCnt="
				+ recordCnt + ", deleteCnt=" + deleteCnt + ", insertCnt=" + insertCnt + ", updateCnt=" + updateCnt
				+ ", errorCnt=" + errorCnt + ", srcFile=" + srcFile + ", errFile=" + errFile + ", orginalFileName="
				+ orginalFileName + ", fileUploadedBy=" + fileUploadedBy + ", fileUploadTime=" + fileUploadTime
				+ ", message=" + message + ", fileUnprocessed=" + fileUnprocessed + "]";
	}
}
