package com.smyld.jms.mq;

import com.ibm.mq.MQC;
import com.ibm.mq.MQException;
import com.ibm.mq.MQMessage;
import com.smyld.jms.MessageID;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class SMYLDMQServer extends SMYLDMQClient implements MQListener // implements
																	// Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String recQ = "receivingQueue";
	String sndQ = "sendingQueue";
	
	MQServerSettings serverSettings;

	/**
	 * 
	 * @see
	 * @since
	 */
	public SMYLDMQServer(String host, String channel, String queManager,
			String queSend, String queRecv) throws MQException {
		super(host, channel, queManager);
		start();
		recQ = queRecv;
		sndQ = queSend;
		MQQueueRequest recQueReq = new MQQueueRequest(recQ,recQ);
		recQueReq.setOptions(MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_INQUIRE|MQC.MQGMO_CONVERT);
		MQQueueRequest sndQueReq = new MQQueueRequest(sndQ,sndQ);
		sndQueReq.setOptions(MQC.MQOO_OUTPUT);
		
		openQueue(recQueReq);
		openQueue(sndQueReq);

		// active = true;
		// new Thread(this).start();
	}
	

	public SMYLDMQServer(MQServerSettings serverSettings, String queSend,
			String queRecv) throws MQException {
		this(serverSettings.getHost(), Integer.parseInt(serverSettings
				.getPort()), serverSettings.getChannel(), serverSettings
				.getQManager(), queSend, queRecv);
		this.serverSettings = serverSettings; 
	}

	public SMYLDMQServer(String host, int port, String channel,
			String queManager, String queSend, String queRecv)
			throws MQException {
		super(host, channel, port, queManager);
		start();
		recQ = queRecv;
		sndQ = queSend;
		MQQueueRequest recQueReq = new MQQueueRequest(recQ,recQ);
		recQueReq.setOptions(MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_INQUIRE);
		MQQueueRequest sndQueReq = new MQQueueRequest(sndQ,sndQ);
		sndQueReq.setOptions(MQC.MQOO_OUTPUT);
		
		openQueue(recQueReq);
		openQueue(sndQueReq);
		
		addMQListener(this);
		// active = true;
		// start();
		// new Thread(this).start();
	}

	public String getRequestQueueName() {
		return recQ;
	}

	public String getResponseQueueName() {
		return sndQ;
	}

	public void newMessage(String queueName, MQMessage message) {
		if (queueName.equals(recQ)) {
			processMessage(message);
		}
	}

	protected void processMessage(MQMessage message) {
		try {
			sendMessage(sndQ, getReply(message), new MessageID(message.messageId),recQ);
		} catch (Exception e) {
			handleError(e, message);
		}
	}

	protected String getReply(MQMessage message) {
		String msgText = null;
		try {
			msgText = readMessageByteText(message);
		} catch (Exception e) {
			handleError(e, message);
		}
		return "AutoReply To :" + msgText;
	}

	protected void handleError(Exception ex, MQMessage message) {
		// Need to be reviewed in the future
		ex.printStackTrace();
	}
	
	public String printServerInfo(){
		StringBuilder buffer = new StringBuilder();
		buffer.append(serverSettings.printServerInfo());
		buffer.append(" Queue   :  ");
		buffer.append(recQ);
		buffer.append('\n');
		buffer.append(" Queue   :  ");
		buffer.append(sndQ);
		buffer.append('\n');
		return  buffer.toString();
		
	}

	public void connectionClosed() {
		System.out.println("Connection Closed ....");
		
	}

	public void connectionResumed() {
		
		System.out.println("Connection Resumed ....");
	}

}

/*
 * public void run(){ while(active){ try { MQMessage newMessage =
 * receiveMessage(recQ); if (newMessage!=null){ while
 * (newMessage.getDataLength()!=0){ msgCounter++; //System.out.println("Messages
 * Received (" + msgCounter + ")"); String messageID = new
 * String(newMessage.messageId);
 * System.out.println("************************************************************");
 * System.out.println("New Message received on Queue : " + recQ);
 * System.out.println("Message ID : " + messageID); String msgText =
 * readMessageByteText(newMessage); System.out.println("Message text : " +
 * msgText );
 * System.out.println("************************************************************");
 * //sendMessage(sndQ,"This is the reply for your message : \"" + msgText + "\" "
 * ,messageID); System.out.println("Reply : " + getDummyReply());
 * sendMessage(sndQ,getDummyReply() ,messageID);
 * System.out.println("=============================================================");
 * //newMessage = receiveMessage(recQ); } Thread.sleep(10); } } catch (Exception
 * e) { e.printStackTrace(); } } }
 */

// test.sendMessage(sndQ,"This is the first test","myCorrel");
// test.sendMessage("sendingQueue","This is the second test");
/*
 * MQMessage newMessage = test.receiveMessage("sendingQueue","myCorrel"); if
 * (newMessage!=null){ System.out.println("New Message received : " );
 * System.out.println("Message ID : " + new String(newMessage.messageId) );
 * System.out.println("Message Text : " + newMessage.readUTF() ); }//
 */
// public static final String REPLY = "This is the message format 1.00SMYLD
// AcctLiMOH DSECURITYINFO
// rtrn1234560002ASI100000000000000000001AASI200000000000000000002B";
