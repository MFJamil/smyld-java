package org.smyld.test;

import org.smyld.SMYLDObject;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class BWMessage extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String process_number;
	String response_code;

	/**
	 * 
	 * @see
	 * @since
	 */
	public BWMessage() {
	}

	public String getProcess_number() {
		return process_number;
	}

	public void setProcess_number(String process_number) {
		this.process_number = process_number;
	}

	public String getResponse_code() {
		return response_code;
	}

	public void setResponse_code(String response_code) {
		this.response_code = response_code;
	}
}
