package com.smyld.io;

import java.io.File;

import com.smyld.SMYLDObject;

public class FileListenSource extends SMYLDObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	File SourcePath;
	SMYLDFileFilter Filter;
	String key;

	public FileListenSource(String filePath, SMYLDFileFilter fileFilter) {
		setFilter(fileFilter);
		setSourcePath(new File(filePath));
		key = filePath + fileFilter.getFilePattern();
	}

	public File getSourcePath() {
		return SourcePath;
	}

	public void setSourcePath(File newSourcePath) {
		SourcePath = newSourcePath;
	}

	public SMYLDFileFilter getFilter() {
		return Filter;
	}

	public void setFilter(SMYLDFileFilter newFilter) {
		Filter = newFilter;
	}

	public String getKey() {
		return key;
	}
}
