package org.smyld.net.url;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;

import org.smyld.SMYLDObject;

/**
 * This class acts a connectot or sending and receiving data through a
 * <code>URLConnection</code>
 */
public class URLConnector extends SMYLDObject implements ProtocolConsts {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private URLConnection connection;
	protected URL serverURL;
	@SuppressWarnings("unused")
	private String urlSuffix;
	private ProtocolImpl protParser;
	// private ComProtocol prot;
	public static final String COMPROT_PROP_KEY = "COMPROT_KEY";
	public static final String COMPROT_PROP_VAL = "COMPROT_VAL";

	/**
	 * Constructs a URL connector encapsulation the given url
	 * 
	 * @param connectURL
	 *            the url to which the connection is to be made to
	 */
	public URLConnector(URL connectURL) {
		this.serverURL = connectURL;
		protParser = new ProtocolImpl();
	}

	/*
	 * @TODO : (14) Creating a new class in org.smyld.net.url.ProtocolImpl which
	 * will hold the sending the receiving for the com protocol objects and will
	 * be used by any class which deals with the com protocol.
	 */
	/**
	 * Constructs a URLConnector with the given protocol, host, port number and
	 * the url suffix of the file on the server
	 * 
	 * @param protocol
	 *            the protocol for this url
	 * @param host
	 *            th host address
	 * @param port
	 *            the port number of the host communicated with
	 * @param urlSuffix
	 *            the name of the file to be communicated to
	 */
	public URLConnector(String protocol, String host, int port, String urlSuffix) {
		initCon(protocol, host, port, urlSuffix);
		protParser = new ProtocolImpl();
	}

	public void setRequestProperty(String propertyName, String propertyValue) {
		if (connection == null)
			startConnection();
		// System.out.println("Connection is null");
		if (propertyName == null)
			System.out.println("Property name is null");
		if (propertyValue == null)
			System.out.println("Property value is null");

		connection.setRequestProperty(propertyName, propertyValue);
	}

	/**
	 * Sends a request to through a URLConnection as a <code>ComProtocol</code>
	 * 
	 * @param header
	 *            the header determining the protocol
	 * @param body
	 *            the body to be included in the protocol
	 * @return the reply from
	 */
	public ComProtocol send(String header, Serializable body) {
		try {

			startConnection(); // Get the connection.
			/* @TODO: Change the implementation from here */
			setRequestProperty(COMPROT_PROP_KEY, COMPROT_PROP_VAL);
			// if(prot == null) prot = new ComProtocol();
			// ProtocolHeader prot_header = new ProtocolHeader(header);
			// ProtocolBody prot_body = new ProtocolBody(body);
			protParser.setHeader(header);
			protParser.setBody(body);
			protParser.send(connection.getOutputStream());
			// Write the protocol to the server
			// ObjectOutputStream output = new
			// ObjectOutputStream(connection.getOutputStream());
			// output.writeObject(prot);
			// output.close();

			InputStream inStr = connection.getInputStream();

			if (inStr == null) // If there is no reply return a null
				return null;

			ObjectInputStream in = new ObjectInputStream(inStr);
			Object o = in.readObject();

			org.smyld.net.url.ComProtocol myProt = (org.smyld.net.url.ComProtocol) o;

			return myProt;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Creates the <code>URLConnection</code> to the servlet to send the
	 * request
	 */
	private void initCon(String prot, String host, int port, String file) {
		try {
			serverURL = new URL(prot, host, port, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens an url connection with the url contained in this connector NOTE:
	 * This needs to be done for every request because an URLConnection is valid
	 * for only one request and response
	 */
	private void startConnection() {
		try {
			connection = serverURL.openConnection();
			connection.setDoOutput(true);
			connection.setUseCaches(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
