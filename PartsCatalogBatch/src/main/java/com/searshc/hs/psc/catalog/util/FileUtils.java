package com.searshc.hs.psc.catalog.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils extends org.apache.commons.io.FileUtils implements Constants {

	private static Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

	public static File[] getFile(String inputDir, Comparator<File> lastmodifiedComparator) throws FileNotFoundException {
		
		File dir = new File(inputDir);
		FileFilter fileFilter = new WildcardFileFilter(DATA_FILE_PREFIX + '*');
		File[] files = dir.listFiles(fileFilter);
		
		if (files == null || files.length == 0)
			return null;
		
		LOGGER.debug("Input directory {} contains {} files to process...", inputDir, files.length);
		
		// sort process oldest first if needed...
		if (files.length > 1)
			Arrays.sort(files, lastmodifiedComparator);
				
		return files;
	}

	public static void renameFile(File oldFile, File newFile) throws Exception {
		
		LOGGER.debug("Renaming source file:{} to work file:{}", oldFile.getPath(), newFile.getPath());
		
		if (oldFile.renameTo(newFile))
			LOGGER.debug("SUCCESSFULLY moved file to:{}", newFile.getPath());
		else {
			LOGGER.error("Failed to RENAME file:{}", oldFile.getPath());
			throw new Exception("Failed to rename file:" + oldFile.getPath());
		}
	}

	public static File newFile(String name, String dir, String ext, String ts) throws Exception {

		String fileName = FilenameUtils.getBaseName(name);
		File newFile = new File(dir + File.separator + fileName + "-" + ts + "." + ext);
		LOGGER.debug("Created new file >>> {}", newFile);
		return newFile;
	}

	public static void archive(String srcDir, String arcDir, String filter, String ts) throws Exception {

		int bufferSize = 1024;

		File dir = new File(srcDir);
		FileFilter fileFilter = new WildcardFileFilter(filter + '-' + ts + '*');
		File[] files = dir.listFiles(fileFilter);

		if (files == null || files.length == 0) {
			LOGGER.debug("There is NOTHING to archive in dir {} using filter >>> {}", srcDir, filter);
			return;
		}

		String zipFile = arcDir + File.separator + filter + "-" + ts + "." + FileType.ARCHIVE.ext();
		LOGGER.debug("Files will be archived into {}", zipFile);
		byte[] buffer = new byte[bufferSize];

		try {
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));
			for (File srcFile : files) {

				LOGGER.debug("Adding file {} to Archive...", srcFile.getName());
				FileInputStream fis = new FileInputStream(srcFile);
				zos.putNextEntry(new ZipEntry(srcFile.getName()));
				int length;
				while ((length = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, length);
				}

				zos.closeEntry();
				fis.close();

				// delete source file
				if (!srcFile.delete()) {
					LOGGER.error("Error deleting file {}...", srcFile.getCanonicalFile());
					throw new Exception("Error deleting file " + srcFile.getCanonicalFile());
				}

				LOGGER.debug("Data file:{} was SUCCESSFULLY compressed into ZIP file:{}", srcFile.getPath(), zipFile);
			}

			zos.close();
		} catch (IOException ioe) {
			LOGGER.error("Error compressing file message:{}", ioe.getMessage());
			throw new Exception("Error compressing file(s) >>> {}" + filter);
		}
	}
}
