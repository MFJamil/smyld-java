package org.smyld.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientNode implements Runnable {
	Socket clientSocket;
	PrintWriter out;
	// SMYLDBufferedReader in;
	// SMYLDInputStream in;
	BufferedReader in;
	boolean active = false;
	boolean close = false;
	long sleepTime;
	long activeSleepTime;
	long inActiveSleepTime;
	ClientNodeTextListener defaultListener;

	public ClientNode(Socket ClientSocket, long ActiveSleepTime,
			long InActiveSleepTime) {
		init(ClientSocket, ActiveSleepTime, InActiveSleepTime);
	}

	public int getPort() {
		return clientSocket.getPort();
	}

	public ClientNode(String destinationID, int destinationPortNo,
			long ActiveSleepTime, long InActiveSleepTime) throws Exception {
		Socket newClientSocket = new Socket(destinationID, destinationPortNo);
		init(newClientSocket, ActiveSleepTime, InActiveSleepTime);
	}

	private void init(Socket ClientSocket, long ActiveSleepTime,
			long InActiveSleepTime) {
		activeSleepTime = ActiveSleepTime;
		inActiveSleepTime = InActiveSleepTime;
		sleepTime = InActiveSleepTime;
		clientSocket = ClientSocket;
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket
					.getInputStream()));
			// in = new SMYLDBufferedReader(new
			// InputStreamReader(clientSocket.getInputStream()));
			/*
			 * in = new
			 * SMYLDInputStream(clientSocket.getInputStream(),265,activeSleepTime);
			 * in.addStreamListener(new StreamListener(){ public void
			 * textRecieved(String data){ textArrive(data); } public void
			 * dataReceived(byte[] data){ // nothing to do } public void
			 * textLineRecieved(String data){ //textArrive(data); }
			 * 
			 * });
			 */
			active = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			active = false;
		}
	}

	private void textArrive(String text) {
		defaultListener.incomingText(this, text);
	}

	public void start() {
		Thread listenerThread = new Thread(this);
		listenerThread.start();
	}

	public String getIPAddress() {
		return clientSocket.getInetAddress().getHostAddress();
	}

	public void run() {
		while (!close) {
			try {
				String incomingText = null;
				if ((incomingText = in.readLine()) != null)
					textArrive(incomingText);
			} catch (Exception ex) {
				// the error could be due to the disconnection of the socket
				ex.printStackTrace();
				close();
			}
			try {
				Thread.sleep(sleepTime);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void addClientTextListener(ClientNodeTextListener newClientText) {
		defaultListener = newClientText;
	}

	public boolean sendText(String dataSent) {
		out.print(dataSent);
		out.flush();
		sleepTime = activeSleepTime;
		return true;
	}

	public void sendTextLine(String dataSent) {
		sendText(dataSent + "\n");
	}

	public void close() {
		close = true;
		active = false;
		try {
			in.close();
			clientSocket.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean isActive() {
		return active;
	}

}
