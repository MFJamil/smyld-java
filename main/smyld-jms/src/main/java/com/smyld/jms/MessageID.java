package com.smyld.jms;

public class MessageID {
	byte[] origID;
	String textID;
	public MessageID(byte[] origID){
		this.origID = origID;
	}
	public byte[] getOrigID() {
		return origID;
	}
	public void setOrigID(byte[] origID) {
		this.origID = origID;
	}
	public String getTextID() {
		return textID;
	}
	public void setTextID(String textID) {
		this.textID = textID;
	}
}
