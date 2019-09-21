package org.smyld.app.pe.logging;

import org.smyld.resources.FileInfo;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class LogFile extends FileInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean showJarDuplicates;

	/**
	 * 
	 * @see
	 * @since
	 */
	public LogFile() {
	}

	public boolean isShowJarDuplicates() {
		return showJarDuplicates;
	}

	public void setShowJarDuplicates(boolean showJarDuplicates) {
		this.showJarDuplicates = showJarDuplicates;
	}
}
