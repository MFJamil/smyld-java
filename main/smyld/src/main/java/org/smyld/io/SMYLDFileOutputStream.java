package org.smyld.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.smyld.SMYLDObject;
import org.smyld.text.TextUtil;

public class SMYLDFileOutputStream extends FileOutputStream {
	boolean withIndent;
	int indentWidth = 4;
	int indentLevel;

	public SMYLDFileOutputStream(String fileName) throws IOException {
		super(fileName);
	}

	public SMYLDFileOutputStream(String fileName, boolean append)
			throws IOException {
		super(fileName, append);
	}

	public SMYLDFileOutputStream(File file) throws IOException {
		super(file);
	}

	public SMYLDFileOutputStream(File file, boolean append) throws IOException {
		super(file, append);
	}

	public void writeln(String text) throws IOException {
		String ind = "";
		if (isWithIndent())
			ind = TextUtil.createWord(' ', indentLevel * indentWidth);
		write((ind + text + SMYLDObject.OS_NEW_LINE).getBytes());
	}

	public void writeln(String text, int indLevel) throws IOException {
		if (indLevel >= 0) {
			String ind = TextUtil.createWord(' ', indLevel * indentWidth);
			write((ind + text + SMYLDObject.OS_NEW_LINE).getBytes());
		} else {
			writeln(text);
		}
	}

	public boolean isWithIndent() {
		return withIndent;
	}

	public void setWithIndent(boolean withIndent) {
		this.withIndent = withIndent;
	}

	public int getIndentWidth() {
		return indentWidth;
	}

	public void setIndentWidth(int indentWidth) {
		this.indentWidth = indentWidth;
	}

	public int getIndentLevel() {
		return indentLevel;
	}

	public void setIndentLevel(int indentLevel) {
		this.indentLevel = indentLevel;
	}

	public static final int IND_DIR_FORWARD = 1;
	public static final int IND_DIR_BACKWARD = 2;
	public static final int IND_DIR_SAME = 3;

}
