package org.smyld.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;

import org.smyld.SMYLDObject;

public class SMYLDFileFilter extends SMYLDObject implements FilenameFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String FilePattern;
	String[] FilePatterns;
	protected HashMap<String,String> acceptedFiles;
	protected HashMap<String,String> informedFiles;
	boolean informOnce;

	public SMYLDFileFilter(String smyldFilePattern, boolean InformOnce) {
		FilePattern = smyldFilePattern;
		informOnce = InformOnce;
	}

	public SMYLDFileFilter(String[] smyldFilePatterns, boolean InformOnce) {
		FilePatterns = smyldFilePatterns;
		informOnce = InformOnce;
	}

	public HashMap<String,String> getAcceptedFiles() {
		return acceptedFiles;
	}

	public void clearAcceptedFiles() {
		if (acceptedFiles != null)
			acceptedFiles.clear();
	}

	protected void addAcceptedFile(String name, String pattern) {
		if (acceptedFiles == null)
			acceptedFiles = new HashMap<String,String>();
		if (informOnce) {
			if (informedFiles == null)
				informedFiles = new HashMap<String,String>();
			if (informedFiles.get(name) == null) {
				informedFiles.put(name, pattern);
				acceptedFiles.put(name, pattern);
			}
		} else {
			acceptedFiles.put(name, pattern);
		}
	}

	public String getFilePattern() {
		return FilePattern;
	}

	public String[] getFilePatterns() {
		return FilePatterns;
	}

	public boolean accept(File dir, String name) {
		// A primitive compare facility
		return (name.length() == FilePattern.length());
	}

}
