package org.smyld.lang.script.converter;

import java.io.File;
import java.io.IOException;

import org.smyld.SMYLDObject;
import org.smyld.lang.script.util.CodeReader;
import org.smyld.lang.script.util.CodeWriter;
import org.smyld.lang.script.core.Scriptlet;

public abstract class Converter extends SMYLDObject {
	Scriptlet sourceScriptlet;
	Scriptlet destinationScriptlet;

	CodeReader currentCodeReader;
	CodeWriter currentCodeWriter;

	public Converter() {
	}

	public void loadCodeFile(String fileName) throws IOException {
		currentCodeReader = new CodeReader(new File(fileName),
				getSourceLangDelimiter());
	}

	public void loadCodeText(String codeText) throws IOException {
		currentCodeReader = new CodeReader(codeText, getSourceLangDelimiter());
	}

	public void startConversion() {
		try {
			String currentLine = null;
			while ((currentLine = currentCodeReader.getNextCodeLine()) != null) {
				doConvert(currentLine.trim().toLowerCase());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public abstract void doConvert(String currentLine);

	public abstract String getSourceLangDelimiter();

}
