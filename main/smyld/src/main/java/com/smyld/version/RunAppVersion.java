package com.smyld.version;

import java.util.Date;

import com.smyld.SMYLDObject;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class RunAppVersion extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String applicationName;
	String version;

	/**
	 * 
	 * @see
	 * @since
	 */
	public RunAppVersion(String appName, String appVersion) {
		applicationName = appName;
		version = appVersion;
	}

	public boolean processVersion(String[] args) {
		if (args != null)
			if (args.length == 1)
				if (args[0].toLowerCase().equals(COMMAND_LINE_ARG_VERSION)) {
					System.out
							.println(getVersionText(applicationName, version));
					return true;
				}
		return false;
	}

	public static String getVersionText(String appName, String appVersion) {
		String versionResult = "SMYLD Software Group"
				+ System.getProperty("line.separator") + appName + " Version "
				+ appVersion;
		return versionResult;
	}

	public static String getVersionTextInFrame(String appName,
			String appVersion, String additionalText) {
		StringBuffer buff = new StringBuffer();
		buff.append(OS_NEW_LINE);
		buff.append("**************************************************");
		buff.append(OS_NEW_LINE);
		buff.append(getVersionText(appName, appVersion));
		buff.append(OS_NEW_LINE);
		if (additionalText != null) {
			buff.append(additionalText);
			buff.append(OS_NEW_LINE);
		}
		buff.append("JDK Version  - ");
		buff.append(System.getProperty("java.version"));
		buff.append(OS_NEW_LINE);
		buff.append(new Date().toString());
		buff.append(OS_NEW_LINE);

		buff.append("**************************************************");
		buff.append(OS_NEW_LINE);
		return buff.toString();
	}

	public static final String COMMAND_LINE_ARG_VERSION = "-version";
}
