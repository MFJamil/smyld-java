package org.smyld.util.multilang;

import org.smyld.SMYLDObject;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class LangSource extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String sourceFileName;
	String targetFileName;

	/**
	 * 
	 * @see
	 * @since
	 */
	public LangSource() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	public String getTargetFileName() {
		return targetFileName;
	}

	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}
}
