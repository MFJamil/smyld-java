package org.smyld.net;

public interface NetProtocol {
	public String getWelcomeMessage();

	public String getConnectionNoOver();

	public void processMessage(ClientNode sourceNode, String incomingMessage);
}
