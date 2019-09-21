package com.smyld.db.oracle;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.HashMap;

import com.smyld.SMYLDObject;
import com.smyld.db.DBSettings;
import com.smyld.io.FileSystem;

public class TNSNamesParser extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TNSNamesParser() {
	}

	public HashMap<String,DBSettings> parseConnections(String tnsNamesFile) {
		HashMap<String,DBSettings> conns = null;
		String contents = FileSystem.readStringFile(tnsNamesFile);
		if (contents != null) {
			conns = new HashMap<String,DBSettings>();
			doParse(contents, conns);
		}
		return conns;
	}

	private void doParse(String contents, HashMap<String,DBSettings> conns) {
		LineNumberReader reader = new LineNumberReader(new StringReader(
				contents));
		String curLine = null;
		StringBuffer buffer = new StringBuffer();
		//boolean insideCon = false;
		try {
			while ((curLine = reader.readLine()) != null) {
				// System.out.println(curLine);
				if ((curLine.trim().length() == 0)) {
					if (!buffer.toString().startsWith("#")) {
						parseConnection(buffer.toString(), conns);
					}
					buffer.setLength(0);
				}
				buffer.append(curLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void parseConnection(String conTxt, HashMap<String,DBSettings> conns) {
		DBSettings newConn = new DBSettings();
		String connName = conTxt.substring(0, conTxt.indexOf("="));
		int startConnection = conTxt.indexOf("=", 0) + 1;
		String host = getParam(conTxt, "HOST", startConnection);
		String sid = getParam(conTxt, "SID", startConnection);
		if (sid == null)
			sid = getParam(conTxt, "SERVICE_NAME", startConnection);
		String port = getParam(conTxt, "PORT", startConnection);
		newConn.setHost(host);
		newConn.setName(sid);
		newConn.setPort(port);
		conns.put(connName, newConn);
	}

	private String getParam(String txt, String name, int fromIndex) {
		String paramName = name + " = ";
		if (fromIndex < 0)
			fromIndex = 0;
		int startIndex = txt.indexOf(paramName, fromIndex);
		if (startIndex != -1) {
			startIndex += paramName.length();
			int endIndex = txt.indexOf(")", startIndex);
			if (endIndex != -1) {
				return txt.substring(startIndex, endIndex);
			}
		}
		return null;
	}
}
