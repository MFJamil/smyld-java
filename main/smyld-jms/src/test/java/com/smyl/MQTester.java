package com.smyld;
import com.ibm.mq.MQC;
import com.ibm.mq.MQMessage;
import com.smyld.jms.mq.*;
public class MQTester implements MQListener {
	
	String requestQue = "QUE_TEST_REQ";
	public MQTester(){
		try {
			//testMQClientConnection();
			testMQServerConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
			new MQTester();
	}
	
	@SuppressWarnings("unused")
	private void testMQClientConnection() throws Exception{
		SMYLDMQClient client = new SMYLDMQClient("192.168.12.174","RS_CHNL_0001",1016,"SMYLDInterface");
		client.start();
		MQQueueRequest newRequest = new MQQueueRequest(requestQue,requestQue);
		newRequest.setOptions( MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_INQUIRE);
		client.openQueue(newRequest);
		client.addMQListener(this);
		client.activateListener();
		System.out.println("Connected ..");
	}
	private void testMQServerConnection() throws Exception{
		SMYLDMQServer client = new SMYLDMQServer("192.168.12.174",1016,"RS_CHNL_0001","SMYLDInterface","QUE_TEST_RES","QUE_TEST_REQ");
		client.activateListener();
		System.out.println("Connected ..");
		
		
	}
	
	public String getRequestQueueName() {
		return requestQue;
	}
	public void newMessage(String queueName, MQMessage message) {
		System.out.println("Received message .. ");
		
		
	}
	public void connectionClosed() {
		System.out.println("Connection Closed  :(  ....");
		
	}
	public void connectionResumed() {
		System.out.println("Connection Resumed :)  ....");
		
	}

}
