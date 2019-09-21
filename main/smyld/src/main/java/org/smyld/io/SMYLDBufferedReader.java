package org.smyld.io;

import java.io.BufferedReader;
import java.io.Reader;

public class SMYLDBufferedReader extends BufferedReader {
	StringBuffer buffer = new StringBuffer();

	public SMYLDBufferedReader(Reader reader) {
		super(reader);
	}

	/*
	 * public synchronized String readText() throws IOException{
	 * buffer.setLength(0); int dataSize = 0; char[] data = null; //new
	 * char[DATA_BLOCK_SIZE]; char[] realData = null; char[] messageLength = new
	 * char[4]; int dataLength = 0; // Reading the message length
	 * if((dataSize=read(messageLength,0,4))!=0){ try{ dataLength =
	 * Integer.parseInt(new String(messageLength));
	 * buffer.append(messageLength); } catch (Exception ex){ dataLength =
	 * DATA_BLOCK_SIZE;}//ex.printStackTrace();} }else{ dataLength =
	 * DATA_BLOCK_SIZE; } data = new char[dataLength];
	 * //System.out.println("Data length:" + dataLength);
	 * if((dataSize=read(data))!=0){ //while((dataSize=read(data))!=0){
	 * if(dataSize<dataLength){ realData = new char[dataSize];
	 * System.arraycopy(data,0,realData,0,dataSize); }else{ realData = data; }
	 * buffer.append(realData); } if (buffer.length()>0) return
	 * buffer.toString(); return null; }
	 */

	public static final int DATA_BLOCK_SIZE = 2048;
}
