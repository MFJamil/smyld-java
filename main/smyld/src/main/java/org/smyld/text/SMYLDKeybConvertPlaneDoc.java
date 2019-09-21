package org.smyld.text;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.smyld.util.keyboard.GermanToArabicKeybConverter;
import org.smyld.util.keyboard.KeyboardConverter;

public class SMYLDKeybConvertPlaneDoc extends PlainDocument {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	KeyboardConverter keybConv;

	public SMYLDKeybConvertPlaneDoc(int keyboardConvertionType) {
		super(new SMYLDCodedContent());
		init(keyboardConvertionType);
	}

	private void init(int keyboardConvertionType) {
		switch (keyboardConvertionType) {
		case KeyboardConverter.GERMAN_TO_ARABIC:
			keybConv = new GermanToArabicKeybConverter();
			break;
		default:

		}
	}

	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		if (str == null)
			return;
		try {
			super.insertString(offs, new String(keybConv
					.convertKeysToBytes(str), keybConv.getTargetCharSetName()),
					a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
