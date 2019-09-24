package org.smyld.deploy.web.jnlp;

import org.smyld.deploy.web.WebDeploymentDescriptor;

public class JNLPDeploymentDescriptor extends WebDeploymentDescriptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ShortcutDescriptor shortcut;
	JNLPHTMLDocument htmlDocument;
	boolean offlineAllowed;
	String mainClass;

	public JNLPDeploymentDescriptor() {
	}

	public ShortcutDescriptor getShortcut() {
		return shortcut;
	}

	public void setShortcut(ShortcutDescriptor shortcut) {
		this.shortcut = shortcut;
	}

	public JNLPHTMLDocument getHtmlDocument() {
		return htmlDocument;
	}

	public void setHtmlDocument(JNLPHTMLDocument htmlDocument) {
		this.htmlDocument = htmlDocument;
	}

	public boolean isOfflineAllowed() {
		return offlineAllowed;
	}

	public void setOfflineAllowed(boolean offlineAllowed) {
		this.offlineAllowed = offlineAllowed;
	}

	public String getMainClass() {
		return mainClass;
	}

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}
}
