package org.smyld.app.pe.input.xml;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import org.smyld.SMYLDObject;
import org.smyld.resources.FileInfo;
import org.smyld.xml.XMLUtil;

import static org.smyld.app.pe.model.Constants.*;
/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class PEXmlFileReader extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Document xmlDocument;
	protected Element rootNode;
	String xmlFileName;

	public PEXmlFileReader()  {

	}

	/**
	 * 
	 * @see
	 * @since
	 */
	public PEXmlFileReader(String appXMLDoc) throws IOException, JDOMException {
		xmlFileName = appXMLDoc;
		try {
			rootNode = XMLUtil.getRootNode(appXMLDoc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new IOException(e.getMessage());
		}
	}

	public PEXmlFileReader(InputStream appXMLStream) throws IOException, JDOMException {
		try {
			rootNode = XMLUtil.getRootNode(appXMLStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new IOException(e.getMessage());
		}
	}


	public String getSourceXMLFileName() {
		return xmlFileName;
	}

	@SuppressWarnings("unchecked")
	protected HashMap<String, Element> loadElements(Element parentElement, String elementTag) {
		return XMLUtil.loadElements(parentElement,elementTag,TAG_COMP_ATT_ID);
	}


	@SuppressWarnings("unchecked")
	protected HashMap<String, String> loadElementsAsText(Element parentElement, String elementTag) {
		return XMLUtil.loadElementsAsText(parentElement,elementTag,TAG_COMP_ATT_ID);
	}

	protected void addFileInfo(FileInfo targetFile, Element inforElement) {
		if (inforElement != null) {
			targetFile.setFileName(inforElement.getChild(TAG_COMP_ATT_NAME)
					.getText());
			targetFile.setFilePath(inforElement.getChild(TAG_COMP_ATT_PATH)
					.getText());
		}
	}

}
