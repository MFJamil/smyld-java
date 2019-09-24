package org.smyld.deploy.web;

import org.jdom2.Element;

public class MIMEDescriptor extends Descriptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String type;
	String extension;

	public MIMEDescriptor() {
	}

	public MIMEDescriptor(String Type, String Extension) {
		setType(Type);
		setExtension(Extension);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public Element getXMLElement() {
		Element newMap = new Element(TAG_NAME_MIME_MAP);
		Element newExt = new Element(TAG_NAME_EXTENSION);
		Element newType = new Element(TAG_NAME_MIME_TYPE);
		newExt.setText(getExtension());
		newType.setText(getType());
		newMap.addContent(newExt);
		newMap.addContent(newType);
		return newMap;
	}

}
