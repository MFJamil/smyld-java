package org.smyld.jms;

import javax.jms.BytesMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;

import oracle.AQ.AQQueueTable;
import oracle.AQ.AQQueueTableProperty;
import oracle.jms.AQjmsDestination;
import oracle.jms.AQjmsDestinationProperty;
import oracle.jms.AQjmsFactory;
import oracle.jms.AQjmsSession;

import org.smyld.SMYLDObject;

public class JMSUtility extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JMSUtility() {
	}

	public static void createOracleQueue(String ora_sid, String host, int port,
			String driver, String userName, String userPass, String dataType,
			String tableName, String queueName) {
		QueueConnectionFactory qc_fact = null;
		QueueConnection q_conn = null;
		QueueSession q_sess = null;
		AQQueueTableProperty qt_prop = null;
		AQQueueTable q_table = null;
		AQjmsDestinationProperty dest_prop = null;
		Queue queue = null;
		@SuppressWarnings("unused")
		BytesMessage bytes_msg = null;

		try {
			/* get queue connection factory */
			qc_fact = AQjmsFactory.getQueueConnectionFactory(host, ora_sid,
					port, driver);
			/* create queue connection */
			q_conn = qc_fact.createQueueConnection(userName, userPass);
			/* create queue session */
			q_sess = q_conn
					.createQueueSession(true, Session.CLIENT_ACKNOWLEDGE);
			/* start the queue connection */
			q_conn.start();
			qt_prop = new AQQueueTableProperty(dataType);
			/* create a queue table */
			q_table = ((AQjmsSession) q_sess).createQueueTable(userName,
					tableName, qt_prop);
			dest_prop = new AQjmsDestinationProperty();
			/* create a queue */
			queue = ((AQjmsSession) q_sess).createQueue(q_table, queueName,
					dest_prop);
			/* start the queue */
			((AQjmsDestination) queue).start(q_sess, true, true);
			/* create a bytes message */
			bytes_msg = q_sess.createBytesMessage();
			/* close session */
			q_sess.close();
			/* close connection */
			q_conn.close();
		} catch (Exception ex) {
			System.out.println("Exception: " + ex);
		}
	}

	// Queue Data Types
	public static final String QUEUE_DATA_TYPE_TEXT = "SYS.AQ$_JMS_TEXT_MESSAGE";
	public static final String QUEUE_DATA_TYPE_OBJECT = "SYS.AQ$_JMS_OBJECT_MESSAGE";
	public static final String QUEUE_DATA_TYPE_BYTES = "SYS.AQ$_JMS_BYTES_MESSAGE";

}
