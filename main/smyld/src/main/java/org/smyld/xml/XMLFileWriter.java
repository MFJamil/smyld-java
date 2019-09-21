package org.smyld.xml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import org.smyld.SMYLDObject;


public abstract class XMLFileWriter extends SMYLDObject {
	protected Element rootElement;
	protected String fileName;

	public XMLFileWriter() {
	}

	public void writeFileToPath(String filePath) throws IOException {
		String fileName = filePath + getFileName();
		writeFileTo(fileName);
	}

	public void writeFileTo(String fullFileName) throws IOException {
		FileOutputStream fout = new FileOutputStream(fullFileName);
		writeFileToStream(fout);
	}

	protected abstract void compose();

	protected void dumpFile() {
		compose();

	}

	public void writeFileToStream(OutputStream out) throws IOException {
		compose();
		XMLOutputter output = new XMLOutputter();
		output.setFormat(Format.getPrettyFormat().setTextMode(Format.TextMode.NORMALIZE));
		/*output.setNewlines(true);
		output.setIndent("   ");
		output.setTextNormalize(true);
		*/
		output.output(rootElement, out);
		out.close();
	}

	public Element getRootElement() {
		compose();
		return rootElement;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
