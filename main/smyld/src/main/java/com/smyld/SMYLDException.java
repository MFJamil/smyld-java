package com.smyld;

/**
 * This class will inherits Exception class and will be inherited by all the
 * exception classes in the project
 */

public class SMYLDException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int exceptionID = 0;
	protected String exceptionReasonText; // This variable is the same as
											// expection message but created to
											// be able to access it through
											// children classes
	protected boolean isPrinted;

	public SMYLDException() {
		super();
	}

	public SMYLDException(int ExceptionID) {
		super();
		exceptionID = ExceptionID;
	}

	public String getExceptionReason() {
		return exceptionReasonText;
	}

	public SMYLDException(String ExceptionReason, int ExceptionID) {
		super(ExceptionReason);
		exceptionID = ExceptionID;
		exceptionReasonText = ExceptionReason;
	}

	public SMYLDException(String exceptionReason) {
		super(exceptionReason);
		exceptionReasonText = exceptionReason;
	}

	public int getExceptionID() {
		return exceptionID;
	}

	public boolean isPrintedToStream() {
		return isPrinted;
	}

	public void exceptionPrintedToStream(boolean value) {
		isPrinted = value;
	}

}
