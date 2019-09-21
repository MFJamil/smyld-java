package org.smyld.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.smyld.math.HexaHandler;

public class HashHandler {
	MessageDigest dig   = null;
	String        type = null;
	HexaHandler   hexHandler = new HexaHandler();
	public HashHandler(String type){
		this.type = type;
		init();
	}
	private void init(){
	  try {
		    // Choose between MD5 and SHA1
		  dig = MessageDigest.getInstance(type);
		  } catch(NoSuchAlgorithmException e){
		      System.out.println("NoSuchAlgorithmException caught!");
		      System.exit( -1);
		  }
	}
	public String encode(String value){
		  byte[] byteHash = null;
		  dig.reset();
		  dig.update(value.getBytes());
		  byteHash = dig.digest();
		  StringBuilder resultString = new StringBuilder();
		  String hexConverted = null;
		  for(byte curByte:byteHash){
			  //hexConverted = Integer.toHexString(0xFF & curByte);
		    //resultString.append(Integer.toHexString(0xFF & curByte));
			  resultString.append(hexHandler.toHexString(curByte)); 
		  }
		  return(resultString.toString());
	}
	
	public boolean match(String encoded,String plainText){
		return encoded.equals(encode(plainText));
		
	}
	
	public static final String HASH_TYPE_MD5="MD5";
	public static final String HASH_TYPE_SHA1="SHA-1";
}
