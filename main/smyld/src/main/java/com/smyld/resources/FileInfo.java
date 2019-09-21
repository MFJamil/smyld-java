package com.smyld.resources;

import java.io.File;

import com.smyld.SMYLDObject;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class FileInfo extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String fileName;
	String filePath;

	/**
	 * 
	 * @see
	 * @since
	 */
	public FileInfo() {
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFullFileName() {
		return filePath + File.separator + fileName;
	}

}
