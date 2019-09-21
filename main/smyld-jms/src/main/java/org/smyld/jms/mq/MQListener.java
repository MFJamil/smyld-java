package org.smyld.jms.mq;

import com.ibm.mq.MQMessage;

public interface MQListener {
	public String getRequestQueueName();
	public void newMessage(String queueName, MQMessage message);
	public void connectionClosed();
	public void connectionResumed();
}
