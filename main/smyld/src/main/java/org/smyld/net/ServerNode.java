package org.smyld.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNode extends ServerSocket implements Runnable {
	boolean closeServer = false;
	boolean connectionEstablished = false;
	long connectedClientsSleepTime;
	long serverSleepTime;
	NewClientNodeListener defaultListener;

	public ServerNode(int Port, long ConnectedClientsSleepTime,
			long ServerSleepTime) throws IOException {
		super(Port);
		serverSleepTime = ServerSleepTime;
		connectedClientsSleepTime = ConnectedClientsSleepTime;
	}

	public ServerNode(int Port, long ConnectedClientsSleepTime)
			throws IOException {
		super(Port);
		serverSleepTime = 50;
		connectedClientsSleepTime = ConnectedClientsSleepTime;
	}

	public void addNewClientListener(NewClientNodeListener clientListener) {
		defaultListener = clientListener;
	}

	public void run() {
		while (!closeServer) {
			try {
				Socket newConn = accept();
				if (defaultListener != null)
					defaultListener.newClientNodeFound(new ClientNode(newConn,
							connectedClientsSleepTime,
							connectedClientsSleepTime * 2));
				// TODO : Code for raising event of new connection established
				Thread.sleep(serverSleepTime);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void close() throws IOException {
		closeServer = true;
		super.close();
	}

	public void newClientNodeFound(ClientNode newClientNode) {
	}

}
