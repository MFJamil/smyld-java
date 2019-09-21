package com.smyld.jms.mq;
import com.ibm.mq.MQException;

public class MQTester 
{
  public MQTester()
  {
    test();
    
  }

  /**
   * 
   * @param args
   */
  public static void main(String[] args)
  {
    new MQTester();
  }
  
  private void test(){
    MQServerSettings newSettings = new MQServerSettings();
    newSettings.setHost(MQ_HOST);
    newSettings.setChannel(MQ_CHNL);
    newSettings.setQManager(MQ_QMGR);
    newSettings.setPort(MQ_PORT);
    try{
      SMYLDMQServer server = new SMYLDMQServer(newSettings,MQ_QUE_SND,MQ_QUE_RCV);
      server.setSleepingTimeInMilliseconds(1000);
    }catch (MQException e){e.printStackTrace();}
    
  }
  
  public static final String MQ_HOST    = "192.168.12.174";
  public static final String MQ_CHNL    = "S_mjameel";
  public static final String MQ_QMGR    = "QM_mjameel";
  public static final String MQ_PORT    = "1414";
  public static final String MQ_QUE_SND = "postcard";
  public static final String MQ_QUE_RCV = "default";
  
  
}
