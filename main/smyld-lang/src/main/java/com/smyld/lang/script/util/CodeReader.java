package com.smyld.lang.script.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.StringReader;

public class CodeReader extends LineNumberReader {
	String codeLineDelimiter;

	public CodeReader(File inputFile, String CodeLineDelimiter)
			throws IOException {
		this(new FileReader(inputFile));
		codeLineDelimiter = CodeLineDelimiter;
	}

	public CodeReader(String codeText, String CodeLineDelimiter)
			throws IOException {
		this(new StringReader(codeText));
		codeLineDelimiter = CodeLineDelimiter;
	}

	public CodeReader(Reader reader) throws IOException {
		super(reader);
	}

	public String getNextCodeLine() throws IOException {
		String newLine = readLine();
		if (newLine != null) {
			newLine = newLine.trim();
		}
		return newLine;
	}

}
