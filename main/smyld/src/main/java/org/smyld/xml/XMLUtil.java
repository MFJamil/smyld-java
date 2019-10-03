package org.smyld.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;


import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import org.smyld.SMYLDObject;

import javax.annotation.Nullable;

public class XMLUtil extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public XMLUtil() {
	}

	public static void addChildElement(Element parent, String childName,
			String childValue) {
		if (childValue != null) {
			Element childElement = new Element(childName);
			childElement.setText(childValue);
			parent.addContent(childElement);
		}
	}
	
	public static boolean getBooleanAttribute(Element target,String attName) {
		if (target!=null){
			String value = target.getAttributeValue(attName);
			if (value!=null)
				return Boolean.valueOf(value).booleanValue();
		}
		return false;
	}

	public static int getIntAttribute(Element target,String attName) {
		if (target!=null){
			String value = target.getAttributeValue(attName);
			if (value!=null)
				return Integer.valueOf(value);
		}
		return 0;
	}

	public static String getElementValue(Element target) {
		if (target != null)
			return target.getText();
		return null;
	}

	public static String getChildValue(Element target, String childName) {
		if ((target != null) && (target.getChild(childName) != null))
			return target.getChild(childName).getText();
		return null;
	}

	public static String getChildValue(Element target, String childName, String  defaultVal) {
		String val = getChildValue(target,childName);
		return val!=null?val:defaultVal;
	}

	public static void setAttribute(Element targetElement, String attName,
			String attValue) {
		if (attValue != null)
			targetElement.setAttribute(attName, attValue);
	}

	public static void setChildValue(Element target, String childName,
			String value) {
		if ((target != null) && (target.getChild(childName) != null)
				&& (value != null))
			target.getChild(childName).setText(value);
	}

	public static void setChildValue(Element target, String childName,
			Element value) {
		if ((target != null) && (target.getChild(childName) != null)
				&& (value != null)) {
			target.removeChild(value.getName());
			target.addContent((Element) value.clone());
		}
	}

	public static String getChildAttributeValue(Element target,
			String childName, String childAttribute) {
		if ((target != null) && (target.getChild(childName) != null))
			return target.getChild(childName).getAttributeValue(childAttribute);
		return null;
	}

	public static String getElementStringValue(Element target)
			throws IOException {
		XMLOutputter outputter = new XMLOutputter();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		outputter.output(target, out);
		return out.toString();
	}

	public static Element getRootNode(URL url) throws IOException, JDOMException {
		// 360t JDOM version compatible
		return  new SAXBuilder().build(url).getRootElement();
		//return (new DOMBuilder()).build(new File(fileName)).getRootElement();
	}
	public static Element getRootNode(File file) throws IOException,JDOMException {
		// 360t JDOM version compatible
		return  new SAXBuilder().build(file).getRootElement();
		//return (new DOMBuilder()).build(new File(fileName)).getRootElement();
	}

	public static Element getRootNode(String fileName) throws IOException,JDOMException {
		// 360t JDOM version compatible
		return  new SAXBuilder().build(new File(fileName)).getRootElement();
		//return (new DOMBuilder()).build(new File(fileName)).getRootElement();
	}

	public static Element getRootNode(InputStream stream) throws IOException,JDOMException {
		return  new SAXBuilder().build(stream).getRootElement();
		//return (new DOMBuilder()).build(stream).getRootElement();
	}

	public static Element getRootNode(byte[] bytes) throws IOException,JDOMException {
		//return getRootNode(new ByteArrayInputStream(bytes));
		return  new SAXBuilder().build(new ByteArrayInputStream(bytes)).getRootElement();
	}

	public static void writeXMLFile(String targetFileName, Element xmlContents)
			throws Exception {
		XMLOutputter output = new XMLOutputter();
		output.setFormat(Format.getPrettyFormat().setTextMode(Format.TextMode.NORMALIZE));
		//output.setNewlines(true);
		//output.setIndent("   ");
		//output.setTextNormalize(true);
		FileOutputStream fout = new FileOutputStream(targetFileName);
		output.output(xmlContents, fout);
		fout.close();
	}

	public static boolean hasChildren(Element curEl){
		return ((curEl.getChildren()!=null)&&(curEl.getChildren().size()>0));
	}


	public static HashMap<String, String> loadElementsAsText(Element parentElement, String elementTag, String keyAtt) {
		HashMap<String, String> loadedElements = new HashMap<String, String>();
		Element elementNode = parentElement.getChild(elementTag);
		if ((elementNode != null)&&(hasChildren(elementNode)))
			elementNode.getChildren().forEach(currentElement -> loadedElements.put(currentElement.getAttributeValue(keyAtt), currentElement.getText()));
		return loadedElements;
	}
	public static HashMap<String, Element> loadElements(Element parentElement, String elementTag, String keyAtt) {
		HashMap<String, Element> loadedElements = new HashMap<>();
		Element elementNode = parentElement.getChild(elementTag);
		if ((elementNode != null)&&(hasChildren(elementNode)))
			elementNode.getChildren().forEach(currentElement -> loadedElements.put(currentElement.getAttributeValue(keyAtt), currentElement));
		return loadedElements;
	}


}
