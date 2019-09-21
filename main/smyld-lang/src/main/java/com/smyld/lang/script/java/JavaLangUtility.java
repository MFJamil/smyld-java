package com.smyld.lang.script.java;

import com.smyld.SMYLDObject;
import com.smyld.text.TextTokenizer;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class JavaLangUtility extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see
	 * @since
	 */
	public JavaLangUtility() {
	}

	public static String convertPackageToPath(String packageName)
			throws Exception {
		String[] folders = new TextTokenizer(packageName, ".").parseTokens();
		StringBuffer result = new StringBuffer();
		String systemPathSep = System.getProperty("file.separator");
		if (folders != null) {
			for (int i = 0; i < folders.length; i++) {
				result.append(folders[i]);
				if (i < folders.length - 1) {
					result.append(systemPathSep);
				}
			}
		}
		return result.toString();
	}

}
