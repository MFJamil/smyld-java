package org.smyld.io;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.JComponent;

import org.smyld.SMYLDObject;
import org.smyld.gui.SMYLDTextArea;

public class ComponentPrintStream extends PrintStream {
	JComponent comp;
	ByteArrayOutputStream outTxt = new ByteArrayOutputStream();

	public ComponentPrintStream(JComponent comp) {
		super(new ByteArrayOutputStream());
		this.comp = comp;
		super.out = outTxt;
	}

	@Override
	public void print(Object newData) {
		doWrite(newData.toString());
	}

	@Override
	public void print(String newData) {
		doWrite(newData);
	}

	@Override
	public void println(Object newData) {
		doWriteln(newData.toString());
	}

	@Override
	public void println(String newData) {
		doWriteln(newData);
	}

	private void doWriteln(String data) {
		doWrite(data + SMYLDObject.OS_NEW_LINE);
	}

	private void doWrite(String data) {
		if (comp instanceof SMYLDTextArea) {
			((SMYLDTextArea) comp).append(data);
		}
	}
}
