package org.smyld.system;

public class SMYLDRuntime {
	public SMYLDRuntime() {
	}

	/**
	 * Executes a jar files under default java environment
	 * 
	 * @param filePath
	 *            the path of the jar file with the main class defined in its
	 *            manifest
	 * @return the process regarding this execution
	 */
	public Process executeJar(String filePath) {
		try {
			String cmd = "java -jar " + filePath;
			return Runtime.getRuntime().exec(cmd);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
