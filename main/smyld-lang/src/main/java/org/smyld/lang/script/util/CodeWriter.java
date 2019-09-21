package org.smyld.lang.script.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class CodeWriter extends BufferedWriter {
	public CodeWriter(String fileName) throws IOException {
		super(new FileWriter(fileName));
	}

	public CodeWriter() throws IOException {
		super(new StringWriter());
	}

}
