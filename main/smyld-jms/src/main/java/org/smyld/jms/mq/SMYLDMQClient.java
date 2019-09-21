package org.smyld.jms.mq;


import com.ibm.mq.*;
import org.smyld.jms.MessageID;

import java.util.HashMap;
import java.util.Vector;

/**
 * 
 * @author 
 * @version 
 * @see 
 * @since 
 */
public class SMYLDMQClient implements Runnable
{
            String                          Host;
            String                          Channel;
            String                          QueueManager;
            MQQueueManager                  queueManager;
            MQQueue                         activeQueue;
            HashMap<String,MQQueue>         activeQueues;
            HashMap<String,String>          listeningQueues;
            HashMap<String, MQQueueRequest> origQueueOpenRequests = new HashMap<String, MQQueueRequest>();
            Vector<MQListener>              listeners;
            boolean                         connected     = true;
  protected boolean                         active        = false;
  protected int                             msgCounter    = 0;
  protected long                            sleepInterval = 100;
            int                             OpenOptions   = -1;
            int                             port;

  /**
   *  The class is a client implementation for the MQ client APIs 
   * @see 
   * @since 
   */
  public SMYLDMQClient()
  {
  }
  public SMYLDMQClient(String queueHost,String serverChannel,String queueManager)
  throws MQException{
    setHost(queueHost);
    setChannel(serverChannel);
    setQueueManager(queueManager);
    //init();
  }
  public SMYLDMQClient(String queueHost,String serverChannel,int serverPort,String queueManager)
  throws MQException{
    this(queueHost,serverChannel,queueManager);
    setPort(serverPort);
  }
  public void start()throws MQException{
	  listeningQueues        = new HashMap<String, String>();
	  init();
    
  }
  @SuppressWarnings("unchecked")
  private void init() throws MQException{
    activeQueues           = new HashMap<String, MQQueue>();
    
    MQEnvironment.hostname = getHost();
    MQEnvironment.channel  = getChannel();
    MQEnvironment.port     = getPort();
    
    MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY,MQC.TRANSPORT_MQSERIES);
    queueManager = new MQQueueManager(getQueueManager());
    MQException.log = null;
    // Setting the default values for opening the queue
    //*
    if (OpenOptions==-1)
      setOpenOptions(MQC.MQOO_INPUT_AS_Q_DEF|MQC.MQOO_OUTPUT|MQC.MQOO_INQUIRE|MQC.MQGMO_CONVERT); //*/
    
    
  }
  public void openQueue(MQQueueRequest queReq) throws MQException{
	  if (queReq.getOptions()==-1)
		  queReq.setOptions(getOpenOptions());
	  origQueueOpenRequests.put(queReq.getId(), queReq);
	  openQueue(queReq.getName(),queReq.getOptions(),queReq.getId());
  }
  public void openQueue(String queName,int options, String queID) throws MQException{
    //if (OpenOptions!=-1) setOpenOptions(openOptions);
    MQQueue newQueue = queueManager.accessQueue(queName,options);
    if (newQueue!=null)
      activeQueues.put(queID,newQueue);
  }
  public void openQueue(String queName, String queID)throws MQException{
    openQueue(queName,getOpenOptions(),queID);
  }
  public MessageID sendMessage (String queID,String messageText)throws Exception{
    return sendMessage(queID,messageText,null);
  }
  public MessageID sendMessage (String queID,String messageText, MessageID correlationID)throws Exception{
	  return sendMessage(queID, messageText, correlationID, null);
  }
  public MessageID sendMessage (String queID,String messageText, MessageID correlationID,String replyToQueue)throws Exception{
    MQQueue targetQue = getQueue(queID);
    if (targetQue!=null){
      MQMessage newMessage = new MQMessage();
      newMessage.write(messageText.getBytes());
      if (correlationID!=null)
        newMessage.correlationId = correlationID.getOrigID();
      newMessage.expiry = MQC.MQEI_UNLIMITED;
      //newMessage.replyToQueueName =   
      newMessage.format = MQC.MQFMT_STRING;
      if(replyToQueue!=null)
    	  newMessage.replyToQueueName = replyToQueue;
      targetQue.put(newMessage, new MQPutMessageOptions());
      return new MQMessageID(newMessage.messageId);
    }
    return null;
  }
  public MQMessage receiveMessage(String queID,MessageID correlationID)throws MQException,InterruptedException{
    return receiveMessage(queID,correlationID,0);
  }
  public MQMessage receiveMessage(String queID)throws MQException,InterruptedException{
    return receiveMessage(queID,null,0);
  }
  public MQMessage receiveMessage(String queID, int wait)throws MQException,InterruptedException{
    return receiveMessage(queID,null,wait);
  }
  public MQMessage receiveMessage(String queID,MessageID correlationID, int wait)throws MQException,InterruptedException{
    MQQueue targetQue = getQueue(queID);
    if ((targetQue!=null)&&(targetQue.isOpen())){
    	
      MQMessage fetchedMessage = new MQMessage();
      MQGetMessageOptions mgo = new MQGetMessageOptions();
      if (correlationID!=null){
        mgo.matchOptions = MQC.MQMO_MATCH_CORREL_ID;
        fetchedMessage.correlationId = correlationID.getOrigID();
      }
      mgo.options += MQC.MQGMO_CONVERT;
      int msgCount = targetQue.getCurrentDepth();
      //System.out.println("inputCount : " + msgCount);
      if (msgCount>0){
        if (wait>0){
          Thread.sleep(wait);
          targetQue.get(fetchedMessage, mgo);
        }else{
          targetQue.get(fetchedMessage, mgo);
        }
        if (fetchedMessage.correlationId!=null)
          return fetchedMessage;
      }
    }
    return null;
  }
  private MQQueue getQueue(String ID){
    return activeQueues.get(ID);  
    
  }  
// Setters and getters 
  public String getHost()
  {
    return Host;
  }
  public void setHost(String Host)
  {
    this.Host = Host;
  }
  public String getChannel()
  {
    return Channel;
  }
  public void setChannel(String Channel)
  {
    this.Channel = Channel;
  }
  public String getQueueManager()
  {
    return QueueManager;
  }
  public void setQueueManager(String QueueManager)
  {
    this.QueueManager = QueueManager;
  }
  public int getOpenOptions()
  {
    return OpenOptions;
  }
  public void setOpenOptions(int OpenOptions)
  {
    this.OpenOptions = OpenOptions;
  }
  public int getPort()
  {
    return port;
  }
  public void setPort(int port)
  {
    this.port = port;
  }
  public void run(){
    while(active){
      try
      {
    	if (connected){
    		
    		if (listeningQueues.size()>0){
	          for (String curQueue : listeningQueues.keySet()) {
	            MQMessage newMessage = receiveMessage(curQueue);
	            //if (!connected) handleConnectionChange();
	            if ((newMessage!=null)&&(listeners!=null)&&(listeners.size()>0)){
	              msgCounter++;
	              for (MQListener curList : listeners) 
	            	  if (curList.getRequestQueueName().equals(curQueue))
	            		  curList.newMessage(curQueue,newMessage);
	            }
	          }
	        }
	        Thread.sleep(sleepInterval);
    	}
	     /* 
      	  }else{
	    	  // Connection is closed by the server
	    	  if (connected)
	    		  handleConnectionChange();
	      }
	      */
      }
     
      // Detecting the sepcial exceptions coming from the MQ server
      catch (MQException e){
    	  switch(e.reasonCode){
    	   case 2162:case 2009:
    	    	  if (connected){
    	    		  handleConnectionChange();
    	    		  new Thread(new ConnectionDetector()).start();
    	    	  }
    			  
    			  break;
    		  default:
    			  //System.out.println(e.getMessage());
    	  }
      }
      //catch (Exception e){e.printStackTrace();}
      catch (InterruptedException e) {
		e.printStackTrace();
      }
    }
  } 
  private void handleConnectionChange(){
	  if (connected){
		  // inform listeners that the connection is closed now
		  for (MQListener listener : listeners) 
			listener.connectionClosed();
		  connected = false;
	  }else{
		  // inform listeners that the connection is closed now
		  for (MQListener listener : listeners) 
			listener.connectionResumed();
		  connected = true;
	  }

	  
  }
  public void addMQListener(MQListener newListener){
    listeningQueues.put(newListener.getRequestQueueName(),newListener.getRequestQueueName());
    if (listeners==null)
      listeners = new Vector<MQListener>();
    listeners.add(newListener);
  }
  public void activateListener() throws MQException{
    if (!active){
        new Thread(this).start();
        active = true;
      }
  }
  public void stop(){
    active = false;
  }
  protected String readMessageByteText(MQMessage targetMessage) throws Exception{
    int msgLength = targetMessage.getMessageLength();
    byte[] data = new byte[msgLength];
    targetMessage.readFully(data);
    return  new String(data);
  }
  public void setSleepingTimeInMilliseconds(long sleepTime){
    sleepInterval = sleepTime;
  }
  class ConnectionDetector implements Runnable{
	  boolean keepChecking = true;
	  
	public void run() {
		while(keepChecking){
		try {
			//queueManager.accessQueue("QUE_TEST_REQ", MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_INQUIRE);
			init();
			keepChecking = false;
			for (MQQueueRequest curQueue : origQueueOpenRequests.values()) {
				openQueue(curQueue);
			}
			handleConnectionChange();
		} catch (MQException e) {
			//System.out.println("Reconnecting error - " + e.reasonCode);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
  }
}
