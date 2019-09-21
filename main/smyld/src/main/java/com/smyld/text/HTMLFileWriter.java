package com.smyld.text;


import java.util.Vector;

import javax.swing.text.html.HTML;

import org.jdom2.Element;

import com.smyld.xml.XMLFileWriter;
import com.smyld.xml.XMLUtil;

public class HTMLFileWriter extends XMLFileWriter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Element bodyEl;
	Element headEl;
	String title;
	String headline;
	Vector<Element> elements;

	public HTMLFileWriter() {
		super();
		init();
	}

	private void init() {
		rootElement = new Element(HTML.Tag.HTML.toString());
		bodyEl = new Element(HTML.Tag.BODY.toString());
		headEl = new Element(HTML.Tag.HEAD.toString());
		elements = new Vector<Element>();
	}

	@Override
	public void compose() {
		XMLUtil.addChildElement(headEl, HTML.Tag.TITLE.toString(), getTitle());
		XMLUtil.addChildElement(bodyEl, HTML.Tag.H1.toString(), getHeadline());
		// Adding body elements
		for (Element curEl : elements) {
			bodyEl.addContent(curEl);
		}

		rootElement.addContent(headEl);
		rootElement.addContent(bodyEl);
	}

	public void addParagraph(String contents) {
		Element newPara = new Element(HTML.Tag.P.toString());
		newPara.setText(contents);
		elements.add(newPara);
	}

	public void addLink(String linkTarget, String linkText) {
		Element newLink = new Element(HTML.Tag.A.toString());
		newLink.setAttribute("href", linkTarget);
		newLink.setText(linkText);
		elements.add(newLink);
	}

	public void addImage(String imageSource) {
		Element newImage = new Element(HTML.Tag.IMG.toString());
		newImage.setAttribute("src", imageSource);
		elements.add(newImage);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

}
