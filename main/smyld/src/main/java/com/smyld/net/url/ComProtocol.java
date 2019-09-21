package com.smyld.net.url;

import com.smyld.SMYLDObject;

/**
 * This class is used as the communication protocol between the servlet and the
 * applet. This protocol is exchanged in eacg request and response between the
 * servlet and the applet
 */
public class ComProtocol extends SMYLDObject implements ProtocolConsts {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProtocolBody body;
	private ProtocolHeader header;

	public ComProtocol() {
	}

	/**
	 * Constructs a new <code>ComProtocol</code> using the given protocol
	 * header and the protocol body
	 * 
	 * @param header
	 *            the header of this protocol
	 * @param body
	 *            the body of this protocol
	 */
	public ComProtocol(ProtocolHeader header, ProtocolBody body) {
		this.header = header;
		this.body = body;
	}

	/**
	 * @return the body contained in this protocol
	 */
	public ProtocolBody getBody() {
		return body;
	}

	/**
	 * @return the header contained in this protocol
	 */
	public ProtocolHeader getHeader() {
		return header;
	}

	/**
	 * Sets the given <code>ProtocolHeader</code> as the new header of this
	 * <code>ComProtocol</code>
	 * 
	 * @param newHeader
	 *            the new header of this protocol
	 */
	public void setHeader(ProtocolHeader newHeader) {
		this.header = newHeader;
	}

	/**
	 * Sets the given <code>ProtocolBody</code> as the new body of this
	 * <code>ComProtocol</code>
	 * 
	 * @param newBody
	 *            the new body of this protocol
	 */
	public void setBody(ProtocolBody newBody) {
		this.body = newBody;
	}

}
