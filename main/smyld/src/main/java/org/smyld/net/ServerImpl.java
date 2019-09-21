package org.smyld.net;

import java.io.IOException;
import java.util.HashMap;


import org.smyld.SMYLDObject;

public class ServerImpl extends SMYLDObject implements NewClientNodeListener,
		ClientNodeTextListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected NetProtocol serverNetProtocol;
	ServerNode serverSock;
	HashMap<String,ClientNode> connectedClients;
	int maxClientNo;
	int currentConnectingClients;

	public ServerImpl(int port, long textListeningSleepTime,
			int MaximumConnectingClient, NetProtocol ServerNetProtocol)
			throws IOException {
		serverNetProtocol = ServerNetProtocol;
		maxClientNo = MaximumConnectingClient;
		connectedClients = new HashMap<String,ClientNode>();
		serverSock = new ServerNode(port, textListeningSleepTime);
		serverSock.addNewClientListener(this);
		Thread serverThread = new Thread(serverSock);
		serverThread.start();
	}

	public void newClientNodeFound(ClientNode newClientNode) {
		// System.out.println("New Client Connected : " +
		// newClientNode.getIPAddress());
		currentConnectingClients++;
		if (currentConnectingClients <= maxClientNo) {
			newClientNode.addClientTextListener(this);
			newClientNode.start();
			newClientNode.sendText(serverNetProtocol.getWelcomeMessage());
			connectedClients.put(newClientNode.getIPAddress(), newClientNode);
		} else {
			newClientNode.sendText(serverNetProtocol.getConnectionNoOver());
			newClientNode.close();
		}
	}

	public void incomingText(ClientNode sourceNode, String newText) {
		// System.out.println("New Text : " + newText );
		serverNetProtocol.processMessage(sourceNode, newText);
	}

	public void sendText(String clientIPAddress, String sentText) {
		ClientNode targetClient = null;
		if ((targetClient = (ClientNode) connectedClients.get(clientIPAddress)) != null)
			targetClient.sendText(sentText);
	}

	public void sendTextLineToALL(String sentText) {
		for (ClientNode targetClient : connectedClients.values()) {
			targetClient.sendTextLine(sentText);
		}
	}

	public void close() throws IOException {
		serverSock.close();
		connectedClients.clear();
		currentConnectingClients = 0;
	}

}
