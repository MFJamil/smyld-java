package com.smyld.io;

public interface StreamListener {
	public void dataReceived(byte[] data);

	public void textRecieved(String data);

	public void textLineRecieved(String data);
}
