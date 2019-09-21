package com.smyld.net.url;

import java.io.Serializable;

import com.smyld.SMYLDObject;

public class ProtocolBody extends SMYLDObject implements ProtocolConsts {
	private Serializable content;

	static final long serialVersionUID = -5806419437860113512L;

	/**
	 * Constructs a new protocol body using the given serializable object
	 */
	public ProtocolBody(Serializable body) {
		setBody(body);
	}

	public ProtocolBody() {
	}

	/**
	 * Sets the given serializable object as the body
	 * 
	 * @param body
	 *            the new body in this protocol body
	 */
	public void setBody(Serializable body) {
		content = body;
	}

	/**
	 * Returns the serializable object contained as body in this protocol body
	 * 
	 * @return the serializable object contained as body in this protocol body
	 */
	public Serializable getBody() {
		return content;
	}

}
