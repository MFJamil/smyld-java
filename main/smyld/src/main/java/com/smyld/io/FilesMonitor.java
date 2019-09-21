package com.smyld.io;

import java.io.File;
import java.util.HashMap;


import com.smyld.SMYLDObject;

public class FilesMonitor extends SMYLDObject implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long sleepTime;
	boolean monitoringActive;
	FileMonitorListener listener;
	HashMap<String,FileListenSource> registry;
	HashMap<String,String> informedFiles = new HashMap<String,String>();

	public FilesMonitor() {

	}

	public void setSleepTime(long newSleepTime) {
		sleepTime = newSleepTime;
	}

	public long getSleepTime() {
		return sleepTime;
	}

	public void registerMonitoring(FileListenSource newSource) {
		if (registry == null)
			registry = new HashMap<String,FileListenSource>();
		registry.put(newSource.getKey(), newSource);
	}

	public void addFileMonitorListener(FileMonitorListener fileListener) {
		listener = fileListener;
	}

	public void startMonitoring() {
		monitoringActive = true;
		Thread newThread = new Thread(this);
		newThread.start();
	}

	public void stopMonitoring() {
		monitoringActive = false;
	}

	public void run() {
		while (monitoringActive) {
			for (FileListenSource currentSource : registry.values()) {
				File[] resultantFiles = currentSource.getSourcePath()
						.listFiles(currentSource.getFilter());
				if (resultantFiles != null)
					if (resultantFiles.length > 0) {
						listener.filesRecieved(currentSource.getSourcePath()
								.getPath(), currentSource.getFilter()
								.getAcceptedFiles());
						informedFiles.putAll(currentSource.getFilter()
								.getAcceptedFiles());
						currentSource.getFilter().clearAcceptedFiles();
					}
			}
			// Putting the thread to sleep
			try {
				Thread.sleep(sleepTime);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
