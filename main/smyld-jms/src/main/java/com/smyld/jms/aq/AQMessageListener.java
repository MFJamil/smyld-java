package com.smyld.jms.aq;

import oracle.AQ.AQMessage;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public interface AQMessageListener {
	public void newMessage(AQMessage newMessage);

}
