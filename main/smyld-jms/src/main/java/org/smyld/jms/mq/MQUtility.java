package org.smyld.jms.mq;

import com.ibm.mq.MQMessage;
import org.smyld.SMYLDObject;

public class MQUtility extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MQUtility() {
	}

	public static String readMessageByteText(MQMessage targetMessage)
			throws Exception {
		return new String(readMessageBytes(targetMessage));
	}

	public static byte[] readMessageBytes(MQMessage targetMessage)
			throws Exception {
		int msgLength = targetMessage.getMessageLength();
		byte[] data = new byte[msgLength];
		targetMessage.readFully(data);
		return data;
	}

}
