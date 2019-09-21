package com.smyld.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;

import com.smyld.SMYLDObject;

public abstract class JMSClient extends SMYLDObject {
	protected QueueBrowser browser;
	protected QueueReceiver receiver;
	QueueSender sender;
	QueueSession receiverSession;
	QueueSession senderSession;
	QueueSession browserSession;
	QueueSession queueSession;
	Queue senderQueue;
	Queue receiverQueue;
	Queue browserQueue;
	String senderQueueName;
	String receiverQueueName;
	String browserQueueName;
	QueueConnection connection;

	public JMSClient() {

	}

	// receiver activation process
	public void activateReceiver() throws Exception {
		// receiverSession = activateSession(receiverSession);
		if (receiverQueue == null)
			receiverQueue = createQueue(receiverQueueName, queueSession); // receiverSession);
		// receiver = receiverSession.createReceiver(receiverQueue);
		receiver = queueSession.createReceiver(receiverQueue);
		// queueSession.recover();
		queueSession.commit();
		// queueSession.run();
		// receiverSession.run();

	}

	public void addMessageListener(MessageListener newMessageListener)
			throws JMSException {
		if (receiver != null)
			receiver.setMessageListener(newMessageListener);
	}

	public void addBrowserMessageListener(MessageListener newMessageListener)
			throws JMSException {
		// if (browser!=null)

	}

	public void receiverCommit() throws JMSException {
		queueSession.commit();
	}

	// Sender activation process
	public void activateSender() throws JMSException {
		// senderSession = activateSession(queueSession);
		sender = queueSession.createSender(createQueue(getSenderQueueName(),
				queueSession));
	}

	public void closeQueueConnection() throws JMSException {
		connection.close();
	}

	// Browser activation process
	public void activateBrowser() throws JMSException {
		browserSession = activateSession(browserSession);
		browser = browserSession.createBrowser(createQueue(
				getBrowserQueueName(), browserSession));
	}

	protected QueueSession activateSession(QueueSession targetSession)
			throws JMSException {
		if (targetSession == null)
			targetSession = connection.createQueueSession(true, 0);
		return targetSession;
	}

	public void sendMessage(Message newMessage) throws JMSException {
		sender.send(newMessage);
		queueSession.commit();
	}

	private void closeSession(QueueSession targetSession) {
		try {
			targetSession.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void closeSenderSession() {
		closeSession(senderSession);
	}

	public void closeReceiverSession() {
		closeSession(receiverSession);
	}

	public void closeBrowserSession() {
		closeSession(browserSession);
	}

	public void sendTextMessage(String messageText, String correlationID)
			throws JMSException {
		TextMessage newMessage = queueSession.createTextMessage(messageText);
		newMessage.setText(messageText);
		newMessage.setJMSCorrelationID(correlationID);
		sendMessage(newMessage);
	}

	public void sendObjectMessage(SMYLDObject messageBody, String correlationID)
			throws JMSException {
		ObjectMessage newMessage = queueSession
				.createObjectMessage(messageBody);
		newMessage.setJMSCorrelationID(correlationID);
		sendMessage(newMessage);
	}

	// public abstract void messageReceived(Message newMessage);
	// public abstract Queue createQueue(String queueName)throws JMSException;
	public abstract Queue createQueue(String queueName, QueueSession session)
			throws JMSException;

	// Setters and getters methods
	public QueueBrowser getBrowser() {
		return browser;
	}

	public void setBrowser(QueueBrowser newBrowser) {
		browser = newBrowser;
	}

	public QueueReceiver getReceiver() {
		return receiver;
	}

	public void setReceiver(QueueReceiver newReceiver) {
		receiver = newReceiver;
	}

	public QueueSender getSender() {
		return sender;
	}

	public void setSender(QueueSender newSender) {
		sender = newSender;
	}

	public QueueSession getReceiverSession() {
		return receiverSession;
	}

	public QueueSession getSenderSession() {
		return senderSession;
	}

	public QueueSession getBrowserSession() {
		return browserSession;
	}

	public void setReceiverSession(QueueSession newSession) {
		receiverSession = newSession;
	}

	public void setSenderSession(QueueSession newSession) {
		senderSession = newSession;
	}

	public void setBrowserSession(QueueSession newSession) {
		browserSession = newSession;
	}

	public Queue getSenderQueue() {
		return senderQueue;
	}

	public void setSenderQueue(Queue newSenderQueue) {
		senderQueue = newSenderQueue;
	}

	public Queue getReceiverQueue() {
		return receiverQueue;
	}

	public void setReceiverQueue(Queue newReceiverQueue) {
		receiverQueue = newReceiverQueue;
	}

	public Queue getBrowserQueue() {
		return browserQueue;
	}

	public void setBrowserQueue(Queue newBrowserQueue) {
		browserQueue = newBrowserQueue;
	}

	public String getSenderQueueName() {
		return senderQueueName;
	}

	public void setSenderQueueName(String newSenderQueueName) {
		senderQueueName = newSenderQueueName;
	}

	public String getReceiverQueueName() {
		return receiverQueueName;
	}

	public void setReceiverQueueName(String newReceiverQueueName) {
		receiverQueueName = newReceiverQueueName;
	}

	public String getBrowserQueueName() {
		return browserQueueName;
	}

	public void setBrowserQueueName(String newBrowserQueueName) {
		browserQueueName = newBrowserQueueName;
	}

	public void closeConnection() throws JMSException {
		connection.close();
	}

}
