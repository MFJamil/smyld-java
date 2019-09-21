package org.smyld.text;

import javax.swing.text.BadLocationException;
import javax.swing.text.GapContent;
import javax.swing.text.Segment;

public class SMYLDCodedContent extends GapContent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String charSetName;

	public SMYLDCodedContent(String CharSetName) {
		charSetName = CharSetName;
	}

	public SMYLDCodedContent() {
	}

	@Override
	public String getString(int where, int len) throws BadLocationException {
		Segment s = new Segment();
		if (len == 0)
			len = length();
		getChars(where, len, s);
		String contentText = new String(s.array, s.offset, s.count);
		return contentText;
	}
}
