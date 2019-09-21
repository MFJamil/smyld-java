package com.smyld.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;

import com.smyld.SMYLDObject;

/**
 * This class contains some utility methods to parse the xml document using the
 * jdom library
 */
public class XMLParser extends SMYLDObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Parses the xml file found at the given path and returns creates a XML
	 * document
	 * 
	 * @param filepath
	 *            the path of the file from which the XML document is to be
	 *            created
	 * @param validating
	 *            whether the file should be validated againist the DTD
	 * @return a <code>org.w3c.dom.Document</code>, created from the given
	 *         file
	 */
	public org.w3c.dom.Document parseXmlFile(String filepath, boolean validating) {
		try {
			// Create a builder factory
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setValidating(validating);

			// Create the builder and parse the file
			org.w3c.dom.Document doc = factory.newDocumentBuilder().parse(
					new File(filepath));
			return doc;
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Parses the xml file found at the given path and returns creates a XML
	 * document
	 * 
	 * @param filename
	 *            the path of the file from which the XML document is to be
	 *            created
	 * @return a <code>JDOM Document</code>, created from the given file
	 */
	public Document parseXmlFile(String filename) {
		try {
			SAXBuilder sxbuild = new SAXBuilder();
			Document doc = sxbuild.build(filename);
			return doc;
		} catch (JDOMException|IOException e) {
			e.printStackTrace();
		}
		// catch (IOException e) { e.printStackTrace(); }
		return null;
	}

	/**
	 * Writes the given JDOM document in the given xml path
	 * 
	 * @param xmlpath
	 *            the path of the xml file where it should be written
	 * @param doc
	 *            the JDom document that should be written
	 */
	public void writeXML(String xmlpath, Document doc) {
		try {
			FileWriter writer = new FileWriter(xmlpath);

			XMLOutputter outp = new XMLOutputter();
			outp.setFormat(Format.getPrettyFormat().setTextMode(Format.TextMode.NORMALIZE));
			outp.output(doc, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Replaces the symbols "~" and "!" with "<" and ">" respectively.
	 * 
	 * @param str
	 *            the string in which the symbols are to be replaced
	 * @retrun the string containing the replaced symbols
	 */
	public String recoverXMLSymbols(String str) {
		str = str.replace('~', '<');
		str = str.replace('!', '>');
		return str;
	}

	/**
	 * Replaces the symbols "<" and ">" with "~" and "!" respectively.
	 * 
	 * @param str
	 *            the string in which the symbols are to be replaced
	 * @retrun the string containing the replaced symbols
	 */
	public String replaceXMLSymbols(String str) {
		str = str.replace('<', '~');
		str = str.replace('>', '!');
		return str;
	}

}
