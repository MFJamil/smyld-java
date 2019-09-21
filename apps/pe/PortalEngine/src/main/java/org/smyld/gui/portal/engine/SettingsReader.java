package org.smyld.gui.portal.engine;

import org.smyld.app.pe.logging.LogFile;

import java.util.HashMap;
import java.util.Vector;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public interface SettingsReader {
	public Vector<Application> loadApplications();
	public LogFile getLogFile();
	public HashMap<String,String> loadLibraries();
}
