package com.smyld.io;

import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import com.smyld.SMYLDObject;

/**
 * This class will serve the read operation for the given stream object and
 * encapsulate all the details for getting the data processed.
 */
public class StreamReader extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	InputStream inputStream;
	Reader reader;

	public StreamReader() {
	}

	/**
	 * This constructor will take java.io.File object as the source to read
	 * 
	 * @param in
	 *            the input stream object
	 * @exception IOException
	 *                if the passed file corrupted or not exist
	 */
	public StreamReader(String inputFileName) throws IOException {
		inputStream = new FileInputStream(inputFileName);
	}

	/**
	 * This constructor will take java.io.File object as the source to read
	 * 
	 * @param in
	 *            the input stream object
	 * @exception IOException
	 *                if the passed file corrupted or not exist
	 */
	public StreamReader(File inputFile) throws IOException {
		inputStream = new FileInputStream(inputFile);
	}

	/**
	 * This constructor will take java.io.InputStream object as the source to
	 * read
	 * 
	 * @param in
	 *            the input stream object
	 */
	public StreamReader(InputStream inputStreamObject) {
		inputStream = inputStreamObject;
	}

	/**
	 * This constructor will take java.io.InputStream object as the source to
	 * read
	 * 
	 * @param in
	 *            the input stream object
	 */
	public StreamReader(Reader readerObject) {
		reader = readerObject;
	}

	/**
	 * Returns the data stored in the given io object as string
	 * 
	 * @return the data of the io object or null if any error occured
	 */
	public String read() throws IOException {
		if (inputStream != null) {
			return readInputStream();
		} else if (reader != null) {
			return readReader();
		}
		return null;
	}

	/**
	 * Returns the data stored in the given io object as string
	 * 
	 * @return the data of the io object or null if any error occured
	 */
	public String read(int dataBlockAmount) {
		if (inputStream != null) {
			return readInputStream(dataBlockAmount);
		} else if (reader != null) {
			return readReader(dataBlockAmount);
		}
		return null;
	}

	/**
	 * Process the reading operation on the input stream io object, the target
	 * behind the seperation between the 2 objects is that they follow different
	 * approach cause the input stream object works on byte array while reader
	 * object works on char array
	 * 
	 * @return the resultant data passed as String
	 */
	private String readInputStream() throws IOException {
		StringBuffer readText = new StringBuffer();
		byte[] resultData = new byte[DATA_BLOCK];
		int realAmountRead = 0;
		while ((realAmountRead = inputStream.read(resultData)) != -1)
			readText.append(readRealInfo(realAmountRead, resultData));
		return readText.toString();
	}

	/**
	 * Process the reading operation on the input stream io object, the target
	 * behind the seperation between the 2 objects is that they follow different
	 * approach cause the input stream object works on byte array while reader
	 * object works on char array
	 * 
	 * @return the resultant data passed as String
	 */
	private String readInputStream(int dataBlock) {
		String readText = null;
		byte[] resultData = new byte[dataBlock];
		int realAmountRead = 0;
		try {
			realAmountRead = inputStream.read(resultData);
			if (realAmountRead != -1)
				readText = readRealInfo(realAmountRead, resultData);
			// Testing if the data read is less than the real data or not to
			// avoid additional data
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return readText;
	}

	/**
	 * This method will read the real data red by the stream according to the
	 * supplied result of read operation and will extract the real data
	 * accordingly
	 * 
	 * @param realAmountRead
	 *            the read operation result
	 * @param dataArray
	 *            the data array holding the information supplied in the read
	 *            operation
	 * @exception IOException
	 *                if the read operation failed
	 * @return the resultant data
	 */
	private String readRealInfo(int realAmountRead, byte[] dataArray)
			throws IOException {

		if (realAmountRead < dataArray.length) {
			ByteArrayInputStream byteArrayReader = new ByteArrayInputStream(
					dataArray, 0, realAmountRead);
			byte[] finalData = new byte[realAmountRead];
			byteArrayReader.read(finalData);
			return new String(finalData);
		} else {
			return new String(dataArray);
		}
	}

	/**
	 * This method will read the real data red by the stream according to the
	 * supplied result of read operation and will extract the real data
	 * accordingly
	 * 
	 * @param realAmountRead
	 *            the read operation result
	 * @param dataArray
	 *            the data array holding the information supplied in the read
	 *            operation
	 * @exception IOException
	 *                if the read operation failed
	 * @return the resultant data
	 */
	private String readRealInfo(int realAmountRead, char[] dataArray)
			throws IOException {
		if (realAmountRead < dataArray.length) {
			CharArrayReader charArrayReader = new CharArrayReader(dataArray, 0,
					realAmountRead);
			char[] finalData = new char[realAmountRead];
			charArrayReader.read(finalData);
			return new String(finalData);
		} else {
			return new String(dataArray);
		}
	}

	/**
	 * Process the reading operation on the input stream io object, the target
	 * behind the seperation between the 2 objects is that they follow different
	 * approach cause the input stream object works on byte array while reader
	 * object works on char array
	 * 
	 * @return the resultant data passed as String
	 */

	private String readReader() {
		StringBuffer readText = new StringBuffer();
		char[] resultData = new char[DATA_BLOCK];
		int realAmountRead = 0;
		try {
			while ((realAmountRead = reader.read(resultData)) != -1)
				readText.append(readRealInfo(realAmountRead, resultData));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return readText.toString();
	}

	/**
	 * Process the reading operation on the input stream io object, the target
	 * behind the seperation between the 2 objects is that they follow different
	 * approach cause the input stream object works on byte array while reader
	 * object works on char array
	 * 
	 * @return the resultant data passed as String
	 */
	private String readReader(int dataBlock) {
		StringBuffer readText = new StringBuffer();
		char[] resultData = new char[dataBlock];
		int realAmountRead = 0;
		try {
			realAmountRead = reader.read(resultData);
			readText.append(readRealInfo(realAmountRead, resultData));
			// Testing if the data read is less than the real data or not to
			// avoid additional data
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return readText.toString();
	}

	private static final int DATA_BLOCK = 256;
}
