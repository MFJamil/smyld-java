package org.smyld.jms.mq;

import org.smyld.SMYLDObject;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class MQServerSettings extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String host;
	String port;
	String qManager;
	String channel;

	/**
	 * 
	 * @see
	 * @since
	 */
	public MQServerSettings() {
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getQManager() {
		return qManager;
	}

	public void setQManager(String qManager) {
		this.qManager = qManager;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String printServerInfo(){
		StringBuilder buffer = new StringBuilder();
		buffer.append("Address       : ");
		buffer.append(host);
		buffer.append('\n');
		buffer.append("Port          : ");
		buffer.append(port);
		buffer.append('\n');
		buffer.append("Queue Manager : ");
		buffer.append(qManager);
		buffer.append('\n');
		buffer.append("Channel       : ");
		buffer.append(channel);
		buffer.append('\n');
		return buffer.toString();
	}
}
