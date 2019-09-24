package org.smyld.deploy.web.jnlp;

import org.jdom2.Element;

import org.smyld.deploy.web.Descriptor;

public class AppDescriptor extends Descriptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String mainClass;

	public AppDescriptor() {
	}

	public String getMainClass() {
		return mainClass;
	}

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}

	@Override
	public Element getXMLElement() {
		Element app = new Element(TAG_NAME_APP_DESC);
		app.setAttribute(TAG_ATT_MAIN_CLS, getMainClass());
		return app;
	}
}
