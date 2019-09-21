package com.smyld.apps.launch;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class looks up for the Basic service required to initiate the Java web
 * start application
 */
public class JNLPLauncher {
	public static final String BASIC_SERVICE_CLASS = "javax.jnlp.BasicService";

	/**
	 * Default constructor, does currently nothing
	 */
	public JNLPLauncher() {
	}

	/**
	 * Looks up the class <code>javax.jnlp.BasicService</code> and returns the
	 * URL of the web server on which the JNLP application is runnning
	 * 
	 * @return a the URL of the web server on which the webstart application is
	 *         residing
	 
	public URL launchJNLP() throws UnavailableServiceException {
		BasicService bs = (BasicService) ServiceManager
				.lookup(BASIC_SERVICE_CLASS);
		return bs.getCodeBase();
	}*/

	/**
	 * REmoves the "/" character created by the webstart and retrieves a URL
	 * that can be used to make a URL connection
	 * 
	 * @param codebase
	 *            the code base that needs to be formatted
	 * @return a URL that can be used to create a <code>URLConnection</code>
	public static URL getExactURL(URL codebase)
			throws UnavailableServiceException, MalformedURLException {
		String fn = codebase.getFile();
		int pos = fn.indexOf("/", fn.length() - 2);
		String newFName = fn.substring(0, pos);
		codebase = new URL(codebase.getProtocol(), codebase.getHost(), codebase
				.getPort(), newFName);
		return codebase;
	}*/

}
