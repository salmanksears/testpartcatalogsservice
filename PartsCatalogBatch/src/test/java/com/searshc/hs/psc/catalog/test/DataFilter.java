package com.searshc.hs.psc.catalog.test;

import java.io.File;
import java.io.FileFilter;

public class DataFilter implements FileFilter {

	private final String[] extensions = new String[] {"properties"};

	  public boolean accept(File file)
	  {
	    for (String extension : extensions)
	    {
	      if (file.getName().toLowerCase().endsWith(extension))
	      {
	        return true;
	      }
	    }
	    return false;
	  }
}
