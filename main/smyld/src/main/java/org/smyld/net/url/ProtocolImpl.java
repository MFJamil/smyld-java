package org.smyld.net.url;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import org.smyld.SMYLDObject;

public class ProtocolImpl extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private Serializable body;
	// private String header;
	private ComProtocol prot;

	// private ProtocolHeader protHeader;
	// private ProtocolBody protBody;

	public ProtocolImpl(Serializable pBody, String pHeader) {
		this();
		prot.setBody(new ProtocolBody(pBody));
		prot.setHeader(new ProtocolHeader(pHeader));
	}

	public ProtocolImpl() {
		prot = new ComProtocol();
		// protHeader = new ProtocolHeader();
		// protBody = new ProtocolBody();
	}

	public void send(OutputStream output) {
		try {
			ObjectOutputStream objout = new ObjectOutputStream(output);
			objout.writeObject(prot);
			objout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Call getHeader() and getBody() to receive the contents of this input
	 * stream
	 */
	public void receive(InputStream input) {
		try {
			System.out.println("Trying to read the request..");
			ObjectInputStream objIn = new ObjectInputStream(input);

			ComProtocol protocol = (ComProtocol) objIn.readObject();
			System.out.println("Using the modifided class to read..");
			this.prot = protocol;

			// prot.setHeader(protocol.getHeader());
			// System.out.println("Read the header..");
			// System.out.println("Header read is
			// :"+protocol.getHeader().getHeader());
			// prot.setBody(protocol.getBody());
			System.out.println("Read the protocol body..");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Call receive before calling this method incase of receiving
	 */
	public String getHeader() {
		return prot.getHeader().getHeader();
	}

	public ComProtocol getProt() {
		return prot;
	}

	/**
	 * CAll receive before calling this method incase of receiving
	 */
	public Serializable getBody() {
		return prot.getBody().getBody();
	}

	public void setHeader(String newHeader) {
		prot.setHeader(new ProtocolHeader(newHeader));
	}

	public void setBody(Serializable newBody) {
		prot.setBody(new ProtocolBody(newBody));
	}

}
