package com.searshc.hs.psc.catalog.test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.FileFileFilter;

public class FileList {

	public static void main(String[] args) {
		File directory = new File("C:\\Temp\\data");

		File[] files = directory.listFiles((FileFilter) FileFileFilter.FILE);
		System.out.println("Default order");
		display(files);

		Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_COMPARATOR);
		System.out.println("\nLast Modified Descending Order (LASTMODIFIED)");
		display(files);

	}

	public static void display(File[] files) {
		for (File file : files) {
			System.out.println(file.getName() + " >>> " + file.lastModified());
		}
	}
}
