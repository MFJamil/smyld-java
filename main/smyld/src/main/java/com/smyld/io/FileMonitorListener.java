package com.smyld.io;

import java.util.HashMap;

public interface FileMonitorListener {
	// public void filesRecieved(String filePath, File[] files);
	public void filesRecieved(String filePath, HashMap<String,String> files);
}
