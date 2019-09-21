package com.smyld.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.smyld.SMYLDObject;

public class Util extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Util() {
	}

	public static String extractExceptionStackTrace(Exception exp) {
		StringWriter strWr = new StringWriter();
		exp.printStackTrace(new PrintWriter(strWr));
		return strWr.getBuffer().toString();
	}

}
