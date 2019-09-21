package com.smyld.net.url;

import com.smyld.SMYLDObject;

/**
 * This represents the header in <code>ComProtocol</code>
 */
public class ProtocolHeader extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String headerStr;

	/**
	 * Default constructor, constructs a new protocol header with any parameters
	 */
	public ProtocolHeader() {
	}

	/**
	 * Constructs a new protocol header with the given string as parameter
	 * 
	 * @param param
	 *            the parameter to be added to this protocol header
	 */
	public ProtocolHeader(String param) {
		this.headerStr = param;

	}

	public String getHeader() {
		return headerStr;
	}

	/**
	 * Sets the new header
	 */
	public void setHeader(String header) {
		this.headerStr = header;
	}

}
