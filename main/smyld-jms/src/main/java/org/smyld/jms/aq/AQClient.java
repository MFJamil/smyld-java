package org.smyld.jms.aq;

import java.sql.SQLData;
import java.sql.SQLException;

import oracle.AQ.AQDequeueOption;
import oracle.AQ.AQDriverManager;
import oracle.AQ.AQEnqueueOption;
import oracle.AQ.AQException;
import oracle.AQ.AQMessage;
import oracle.AQ.AQObjectPayload;
import oracle.AQ.AQOracleQueue;
import oracle.AQ.AQOracleSession;

import org.smyld.SMYLDObject;
import org.smyld.db.DBConnection;
import org.smyld.db.DBConnectionListener;
import org.smyld.db.DBSettings;
import org.smyld.db.oracle.SMYLDOracleConnection;
import org.smyld.jms.MessageListener;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class AQClient extends SMYLDObject implements Runnable,
		DBConnectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AQOracleSession recSession;
	AQOracleSession sendSession;
	DBSettings connSettings;
	SMYLDOracleConnection dbConnection;
	AQOracleQueue receiverQueue;
	AQOracleQueue senderQueue;
	AQDequeueOption dequeueOptions;
	AQEnqueueOption enqueueOptions;
	boolean stopReceiver;
	MessageListener activeMessageListener;

	/**
	 * 
	 * @see
	 * @since
	 */
	public AQClient(DBSettings newSettings) throws Exception {
		connSettings = newSettings;
		dbConnection = new SMYLDOracleConnection(connSettings);
		init();
	}

	public AQClient(SMYLDOracleConnection newDBConnection) throws AQException {
		dbConnection = newDBConnection;
		init();
	}

	public DBConnection getDBConnection() {
		return dbConnection;
	}

	private void init() throws AQException {
		try {
			Class.forName("oracle.AQ.AQOracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Setting the default settings
		dequeueOptions = new AQDequeueOption();
		dequeueOptions.setDequeueMode(AQDequeueOption.DEQUEUE_REMOVE_NODATA);
	}

	public void setAutoReconnect() {
		dbConnection.addDBConnectionListener(this);
	}

	public void activateReceiver(String queueOwner, String queueName)
			throws AQException {
		recSession = (AQOracleSession) AQDriverManager
				.createAQSession(dbConnection.getConnection());
		receiverQueue = (AQOracleQueue) recSession.getQueue(queueOwner,
				queueName);
		receiverQueue.startDequeue();
		stopReceiver = false;
	}

	public String getSessionID() {
		// ((OracleConnection)activeSession.getDBConnection()).
		return null;
	}

	public void start() {
		new Thread(this).start();
	}

	public boolean isActive() {
		return dbConnection.isConnected();
	}

	public void setDequeueOptions(AQDequeueOption newDequeueOption) {
		dequeueOptions = newDequeueOption;
	}

	public void setEnqueueOptions(AQEnqueueOption newEnqueueOption) {
		enqueueOptions = newEnqueueOption;
	}

	public void addMessageListener(MessageListener newListener) {
		activeMessageListener = newListener;
	}

	public void run() {
		if (receiverQueue != null) {
			while (!stopReceiver) {
				try {
					// there is a possibility of loosing some messages during
					// the read
					// to be reviewed in the future
					AQMessage newMessage = receiverQueue.dequeue(
							dequeueOptions, Class
									.forName("org.smyld.jms.aq.AQTextMessage"));
					if (newMessage != null) {
						if (activeMessageListener != null) {
							activeMessageListener.newMessage(newMessage);
						} else {
							System.out
									.println("Received message but listener is null, message is lost with the ID : "
											+ newMessage.getMessageProperty()
													.getCorrelation());
						}
					}
					// 
					/*
					 * System.out.println("New message received : " +
					 * newMessage.getMessageProperty().getCorrelation()); if
					 * (newMessage.getObjectPayload()!=null){ if
					 * (newMessage.getObjectPayload().getPayloadData()!=null){
					 * System.out.println(newMessage.getObjectPayload().getPayloadData().toString()); } } }
					 */

					Thread.sleep(500);

				} catch (Exception ex) {
					// We need to printout the exception here to detect all the
					// connection related exceptions
					dbConnection.detectConnectionError(ex);
					if (dbConnection.isConnected()) {
						System.out
								.println("Kindly inform SMYLD About the text shown below : ");
						ex.printStackTrace();
					}
					stopReceiver = true;
				}
			}
		}
	}

	public void sendObjectMessage(SQLData messageObject, String correlationID)
			throws AQException, SQLException {
		AQMessage sendMessage = senderQueue.createMessage();
		AQObjectPayload payload = sendMessage.getObjectPayload();
		payload.setPayloadData(messageObject);
		if (correlationID != null)
			sendMessage.getMessageProperty().setCorrelation(correlationID);
		senderQueue.enqueue(new AQEnqueueOption(), sendMessage);
		sendSession.getDBConnection().commit();
	}

	public void activateSender(String queueOwner, String queueName)
			throws AQException {
		sendSession = (AQOracleSession) AQDriverManager
				.createAQSession(dbConnection.getConnection());
		senderQueue = (AQOracleQueue) sendSession.getQueue(queueOwner,
				queueName);
		senderQueue.start();
	}

	public void close() {
		// System.out.println("receiverQueue.close();");
		// receiverQueue.close();
		// System.out.println("activeSession.close();");
		if (sendSession != null)
			sendSession.close();
		if (recSession != null)
			recSession.close();
		// System.out.println("stopReceiver = true;");
		stopReceiver = true;
		try {
			dbConnection.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static final int DEQUEUE_WAIT_TIME_DEFAULT = 100;

	public void connectionClosed() {
		System.out.println("Connection closed on AQ client listener ... ");
		stopReceiver = true;
	}

	public void connectionResumed(DBConnection newConnection) {
		System.out.println("Connection resumed on AQ client listener ... ");
		try {
			if (receiverQueue != null) {
				activateReceiver(receiverQueue.getOwner(), receiverQueue
						.getName());
				start();
			}
			if (senderQueue != null)
				activateSender(senderQueue.getOwner(), senderQueue.getName());

		} catch (AQException e) {
			e.printStackTrace();
		}
		stopReceiver = false;
	}
}
