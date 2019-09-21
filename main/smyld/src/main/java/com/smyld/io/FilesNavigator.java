package com.smyld.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Vector;

import com.smyld.SMYLDObject;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class FilesNavigator extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Vector<FileNavigationListener> listeners;

	/**
	 * 
	 * @see
	 * @since
	 */
	public FilesNavigator() {
	}

	public void addNavigationListener(FileNavigationListener newListener) {
		if (listeners == null)
			listeners = new Vector<FileNavigationListener>();
		listeners.add(newListener);
	}

	public void navigate(File startingPath, FileFilter filter)
			throws IOException {
		File[] list = startingPath.listFiles(filter);
		File[] listAll = startingPath.listFiles();
		for (int i = 0; i < list.length; i++)
			informListeners(list[i]);
		for (int i = 0; i < listAll.length; i++) {
			if (listAll[i].isDirectory())
				navigate(listAll[i], filter);
		}
	}

	private void informListeners(File newFile) {
		for (FileNavigationListener currentListener : listeners) {
			currentListener.fileFound(newFile);
		}
	}
}
