package org.smyld.log;

import java.util.Vector;

public class DefaultLogRecordStrcut implements LogStructure {
	public DefaultLogRecordStrcut() {
	}

	public Vector<String> getFieldsSeq() {
		Vector<String> fieldSeq = new Vector<String>();
		fieldSeq.add("date");
		fieldSeq.add("className");
		fieldSeq.add("modName");
		fieldSeq.add("message");
		fieldSeq.add("startTime");
		fieldSeq.add("endTime");

		return fieldSeq;
	}

	public Vector<String> getFieldsHeader() {
		Vector<String> fieldheader = new Vector<String>();
		fieldheader.add("Date");
		fieldheader.add("Class");
		fieldheader.add("Module");
		fieldheader.add("Message");
		fieldheader.add("Start Time");
		fieldheader.add("End Time");

		return fieldheader;
	}

}
