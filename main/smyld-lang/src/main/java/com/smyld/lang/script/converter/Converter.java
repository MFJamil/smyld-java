package com.smyld.lang.script.converter;

import java.io.File;
import java.io.IOException;

import com.smyld.SMYLDObject;
import com.smyld.lang.script.util.CodeReader;
import com.smyld.lang.script.util.CodeWriter;
import com.smyld.lang.script.util.Scriptlet;

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
