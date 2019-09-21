package org.smyld.run;

import org.smyld.SMYLDObject;
import org.smyld.security.EnAndDecryptor;
import org.smyld.version.RunAppVersion;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class RunApp extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see
	 * @since
	 */
	public RunApp() {

	}

	public static void main(String[] args) {
		if (!new RunAppVersion(cryptoAppName, cryptoVersion)
				.processVersion(args))
			EnAndDecryptor.main(args);
	}

	public static final String cryptoAppName = "Crypto Console";
	public static final String cryptoVersion = "1.00.001";

}
