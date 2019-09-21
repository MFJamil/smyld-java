package org.smyld.bw.sys;

import org.smyld.SMYLDException;

public class SecurityException extends SMYLDException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecurityException(String ExceptionReason, int ExceptionID) {
		super(ExceptionReason, ExceptionID);
	}

	public static final int Seq_Ex_Multi_Installation_no = 1;

	public static final String Seq_Ex_txt_Multi_Installation_no = "Multiple installation number exists";
}
