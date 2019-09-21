package org.smyld.jms;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;

import oracle.jms.AQjmsFactory;
import oracle.jms.AQjmsSession;

import org.smyld.db.DBSettings;

public class JMSOracleClient extends JMSClient {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected DBSettings connSettings;
	String driver = "oci";

	public JMSOracleClient(String Driver, DBSettings ConnSettings) {
		this(ConnSettings);
		setDriver(Driver);

	}

	public JMSOracleClient(DBSettings ConnSettings) {
		setConnSettings(ConnSettings);
	}

	public void init() {
		try {
			QueueConnectionFactory factory = AQjmsFactory
					.getQueueConnectionFactory(connSettings.getHost(),
							connSettings.getName(), Integer
									.parseInt(connSettings.getPort()),
							getDriver());
			connection = factory.createQueueConnection(connSettings
					.getUserName(), connSettings.getUserPassword());
			connection.start();
			queueSession = activateSession(queueSession);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public QueueConnection getQueueConnection() {
		return connection;
	}

	// public void messageReceived(Message newMessage){}

	/*
	 * public Queue createQueue(String queueName)throws JMSException{ return
	 * createQueue(queueName,se); }
	 */
	/*
	 * public void activateReceiver() throws AQException{ AQDequeueOption
	 * aqDeqOpt = new AQDequeueOption();
	 * aqDeqOpt.setDequeueMode(aqDeqOpt.DEQUEUE_REMOVE);
	 *  }
	 */

	@Override
	public Queue createQueue(String queueName, QueueSession queueSession)
			throws JMSException {
		Queue targetQueue = ((AQjmsSession) queueSession).getQueue(connSettings
				.getUserName(), queueName);
		// AQQueueProperty prop = new AQQueueProperty();

		return targetQueue;
	}

	public void setConnSettings(DBSettings newConnSettings) {
		connSettings = newConnSettings;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String newDriver) {
		driver = newDriver;
	}

}
