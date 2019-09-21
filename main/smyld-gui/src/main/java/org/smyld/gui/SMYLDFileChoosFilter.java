package org.smyld.gui;

import java.io.File;
import java.util.HashMap;

import javax.swing.filechooser.FileFilter;

import org.smyld.io.FileSystem;

public class SMYLDFileChoosFilter extends FileFilter {

	String fileDesc;
	HashMap<String, String> fileExts;

	public SMYLDFileChoosFilter(String fileDescription) {
		fileDesc = fileDescription;
		fileExts = new HashMap<String, String>();

	}

	public SMYLDFileChoosFilter(String fileExtention, String fileDescription) {
		this(fileDescription);
		addExtention(fileExtention);
	}

	public void addExtention(String newExt) {
		if (newExt != null) {
			fileExts.put(newExt.toLowerCase(), newExt);
		}
	}

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String fileExt = FileSystem.getExtention(f);
		if ((fileExt != null) && (fileExts.containsKey(fileExt.toLowerCase()))) {
			return true;
		}
		return false;

	}

	@Override
	public String getDescription() {
		return fileDesc;
	}

	public void setDescription(String newDescription) {
		fileDesc = newDescription;
	}

}
