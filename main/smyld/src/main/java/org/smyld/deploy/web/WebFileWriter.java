package org.smyld.deploy.web;

import java.util.HashMap;


import org.jdom2.Element;

import org.smyld.xml.XMLFileWriter;

/**
 * This class will handel the creation of web.xml file that contains all the
 * necessary settings that could be needed for the deployment of a web service
 */

public class WebFileWriter extends XMLFileWriter implements WebDescConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String description;
	int sessionTimeOut;
	HashMap<String,MIMEDescriptor> mimeMappings;

	public WebFileWriter() {
		init();
	}

	private void init() {
		rootElement = new Element(TAG_NAME_ROOT);
		addMIMEMapping(new MIMEDescriptor(TAG_MIME_TYP_HTML, TAG_MIME_EXT_HTML));
		addMIMEMapping(new MIMEDescriptor(TAG_MIME_TYP_TEXT, TAG_MIME_EXT_TEXT));

	}

	@Override
	protected void compose() {
		// Adding description node
		Element desc = new Element(TAG_NAME_DESC);
		if (getDescription() != null)
			desc.setText(getDescription());
		else
			desc.setText(TAG_DESC_DEFAULT);
		rootElement.addContent(desc);
		// Adding session config node
		if (getSessionTimeOut() != 0) {
			Element sesConfig = new Element(TAG_NAME_SES_CFG);
			Element sesTimeOut = new Element(TAG_NAME_SES_TOUT);
			sesTimeOut.setText(Integer.toString(getSessionTimeOut()));
			sesConfig.addContent(sesTimeOut);
			rootElement.addContent(sesConfig);
		}
		// adding mime mappings
		for (MIMEDescriptor curMapping : mimeMappings.values()) {
			rootElement.addContent(curMapping.getXMLElement());
		}
	}

	public void addJNLPMappings() {
		addMIMEMapping(new MIMEDescriptor(TAG_MIME_TYP_JNLP, TAG_MIME_EXT_JNLP));
	}

	public void addMIMEMapping(MIMEDescriptor newMapping) {
		if (mimeMappings == null)
			mimeMappings = new HashMap<String,MIMEDescriptor>();
		mimeMappings.put(newMapping.getExtension(), newMapping);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSessionTimeOut() {
		return sessionTimeOut;
	}

	public void setSessionTimeOut(int sessionTimeOut) {
		this.sessionTimeOut = sessionTimeOut;
	}

	public HashMap<String,MIMEDescriptor> getMimeMappings() {
		return mimeMappings;
	}

	public void setMimeMappings(HashMap<String,MIMEDescriptor> mimeMappings) {
		this.mimeMappings = mimeMappings;
	}

	@Override
	public String getFileName() {
		if (fileName == null)
			return FILE_NAME_DEFAULT;
		return fileName;
	}

}
