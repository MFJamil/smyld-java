package com.smyld;

import java.util.Enumeration;

import javax.swing.text.Style;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

public class Tester_01 extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2595613478085471303L;

	public Tester_01() {
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		HTMLEditorKit kit = new HTMLEditorKit();
		HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();
		StyleSheet styles = doc.getStyleSheet();

		StyleSheet[] sheets = styles.getStyleSheets();
		for (StyleSheet element : sheets) {
			element.toString();

		}

		// Enumeration rules = styles.getStyleNames();
		for (Enumeration rules = styles.getStyleNames(); rules
				.hasMoreElements();) {
			Style rule = styles.getStyle((String) rules.nextElement());
			System.out.println(rule.toString());
		}
		System.exit(0);
	}

}
