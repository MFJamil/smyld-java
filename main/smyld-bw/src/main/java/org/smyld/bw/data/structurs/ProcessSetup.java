package org.smyld.bw.data.structurs;

import org.smyld.SMYLDObject;

public class ProcessSetup extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String FileMask;
	String FilePath;
	String FileRenameMask;
	String FileMovePath;
	String ProcessName;

	public ProcessSetup() {
	}

	public String getFileMask() {
		return FileMask;
	}

	public void setFileMask(String newFileMask) {
		FileMask = newFileMask;
	}

	public String getFilePath() {
		return FilePath;
	}

	public void setFilePath(String newFilePath) {
		FilePath = newFilePath;
	}

	public String getFileRenameMask() {
		return FileRenameMask;
	}

	public void setFileRenameMask(String newFileRenameMask) {
		FileRenameMask = newFileRenameMask;
	}

	public String getFileMovePath() {
		return FileMovePath;
	}

	public void setFileMovePath(String newFileMovePath) {
		FileMovePath = newFileMovePath;
	}

	public String getProcessName() {
		return ProcessName;
	}

	public void setProcessName(String newProcessName) {
		ProcessName = newProcessName;
	}
}
