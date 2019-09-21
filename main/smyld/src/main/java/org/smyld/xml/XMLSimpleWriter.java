package org.smyld.xml;

import java.io.FileOutputStream;
import java.io.IOException;

import org.smyld.SMYLDObject;

public class XMLSimpleWriter extends SMYLDObject implements XMLConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	FileOutputStream fout;

	public XMLSimpleWriter() {
	}

	public void createFile(String fileName) throws IOException {
		fout = new FileOutputStream(fileName);
	}

	public void AddTag(String tagName) {
		// fout.write("<" + tagName + );
	}

	public void addTag(String tagName, String[][] attributes, String tagValue)
			throws Exception {
		StringBuffer buffer = new StringBuffer(getTagOpen(tagName));
		addAttributes(buffer, attributes);
		buffer.append(" >");
		buffer.append(tagValue);
		buffer.append(getTagFullClose(tagName));
		addText(buffer.toString());
	}

	public void addTag(String tagName, String[][] attributes) throws Exception {
		StringBuffer buffer = new StringBuffer(getTagOpen(tagName));
		addAttributes(buffer, attributes);
		buffer.append(" />");
		addText(buffer.toString());
	}

	public void addTag(String tagName, String tagValue) throws Exception {
		StringBuffer buffer = new StringBuffer(getTagFullOpen(tagName));
		buffer.append(tagValue);
		buffer.append(getTagFullClose(tagName));
		addText(buffer.toString());
	}

	private String getTagOpen(String tagName) {
		return "<" + tagName;
	}

	private String getTagFullOpen(String tagName) {
		return "<" + tagName + ">";
	}

	private String getTagFullClose(String tagName) {
		return "</" + tagName + ">";
	}

	private void addAttributes(StringBuffer buffer, String[][] attributes) {
		for (int i = 0; i < attributes[0].length; i++) {
			buffer.append(" ");
			buffer.append(attributes[0][i]);
			buffer.append("=\"");
			buffer.append(attributes[1][i]);
			buffer.append("\"");
		}

	}

	public void addTagStart(String tagName) throws Exception {
		addText(getTagOpen(tagName));
	}

	public void addTagFullStart(String tagName) throws Exception {
		addText(getTagFullOpen(tagName));
	}

	public void addTagEnd(String tagName) throws Exception {
		addText(getTagFullClose(tagName));
	}

	public void addEncodingHeader(String encodingName) throws Exception {
		addText(XML_ENCODING_HEADER_START + encodingName
				+ XML_ENCODING_HEADER_END);
	}

	public void addXSLHeader(String xslFileName) throws Exception {
		addText(XML_XSL_HEADER_START + xslFileName + XML_XSL_HEADER_END);
	}

	private void addText(String newText) throws Exception {
		fout.write((newText + OS_NEW_LINE).getBytes());
	}

}
