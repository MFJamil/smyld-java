package com.smyld.lang.script.vb;

import java.io.File;
import java.io.IOException;

import com.smyld.lang.script.util.CodeReader;

public class VBCodeReader extends CodeReader {
	public VBCodeReader(String codeText) throws IOException {
		super(codeText, null);
	}

	public VBCodeReader(File codeFile) throws IOException {
		super(codeFile, null);
	}

	@Override
	public String getNextCodeLine() throws IOException {
		String currentLine = super.getNextCodeLine();
		boolean endOfLine = false;
		while ((!endOfLine) && (currentLine != null)) {
			if (!currentLine.endsWith("_")) {
				endOfLine = true;
			} else {
				currentLine = currentLine
						.substring(0, currentLine.length() - 1)
						+ super.getNextCodeLine();
			}
		}
		return currentLine;
	}

}
