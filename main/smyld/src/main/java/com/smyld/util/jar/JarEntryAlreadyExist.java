package com.smyld.util.jar;

import java.util.jar.JarEntry;

import com.smyld.SMYLDException;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class JarEntryAlreadyExist extends SMYLDException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JarEntry jarEntry;

	/**
	 * 
	 * @see
	 * @since
	 */
	public JarEntryAlreadyExist(JarEntry exJarEntry) {
		jarEntry = exJarEntry;
	}

	public JarEntry getJarEntry() {
		return jarEntry;
	}

}
