package org.smyld.deploy.web.jnlp;

import org.jdom2.Element;

import org.smyld.deploy.web.Descriptor;

public class JARDescriptor extends Descriptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ref;
	String downloadType;
	boolean containsMain;

	public JARDescriptor() {
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getDownloadType() {
		return downloadType;
	}

	public void setDownloadType(String downloadType) {
		this.downloadType = downloadType;
	}

	public boolean isContainsMain() {
		return containsMain;
	}

	public void setContainsMain(boolean containsMain) {
		this.containsMain = containsMain;
	}

	@Override
	public Element getXMLElement() {
		Element newJar = new Element(TAG_NAME_JAR);
		newJar.setAttribute(TAG_ATT_REF, getRef());
		newJar.setAttribute(TAG_ATT_DOWNLOAD, getDownloadType());
		String containsMain = isContainsMain() ? ATT_BOOLEAN_TRUE
				: ATT_BOOLEAN_FALSE;
		newJar.setAttribute(TAG_ATT_MAIN, containsMain);
		return newJar;

	}
}
