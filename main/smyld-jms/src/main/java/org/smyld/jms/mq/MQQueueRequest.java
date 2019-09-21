package org.smyld.jms.mq;

public class MQQueueRequest {

	String id;
	String name;
	int options;
	
	public MQQueueRequest(String id,String name){
		this.id = id;
		this.name = name;
	}
	public int getOptions() {
		return options;
	}
	public void setOptions(int options) {
		this.options = options;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
