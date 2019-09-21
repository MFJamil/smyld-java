package org.smyld.text;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.smyld.io.StreamReader;

/**
 * This class will navigate through given text in serial order and will provide
 * all the necessary methodes for reading the given text in parts
 * 
 * @author Mohammed Jameel
 * @version 1.0
 */

public class StringNavigator implements StringReader {

	FileInputStream fin = null;
	/**
	 * Holds the flag telling that the end of the text reached
	 */
	private boolean endOfText = false;
	/**
	 * Holds the stream reader for the file
	 */
	private StreamReader fileReader;

	@SuppressWarnings("unused")
	private InputStream inpStream;
	/**
	 * Holds the amount of the data block to be read from the file
	 */
	@SuppressWarnings("unused")
	private boolean readIsSegmented = false;

	/**
	 * Holds the amount of the data block to be read from the file
	 */
	private int readBlockSize = DEFAULT_DATA_BLOCK_SIZE;
	private int blockDelimSize = DEFAULT_DATA_BLOCK_DELIM_SIZE;
	private boolean withDelimiter = false;
	/**
	 * The text for processing
	 */
	private String text;

	private String residualText;

	private String lastText;
	/**
	 * Pointer which points at the position of reading the text, this pointer is
	 * advanced according to the methods called
	 */
	private int pointer = 0;
	private long filePointer = 0;

	/**
	 * Show if the class have to read new data in case of the end of available
	 * data finished or not
	 */
	boolean fixedBlockRead = false;

	/**
	 * Constructs the class with the given text
	 * 
	 * @param Text
	 *            the text for navigation
	 */

	public StringNavigator(String Text) {
		setText(Text);
		fixedBlockRead = true;
	}

	/**
	 * Constructs the class with the given file
	 * 
	 * @param textFile
	 *            the file that contains the text for navigation
	 * @exception IOException
	 *                if the file does not exist or any error occured while
	 *                reading it
	 */

	public StringNavigator(File textFile) throws IOException {
		try {
			fileReader = new StreamReader(textFile);
			setText(fileReader.read());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new IOException("Error extracting the text from the file");
		}
	}

	/**
	 * Constructs the class with the given file and will read the information
	 * according to the provided data block size
	 * 
	 * @param textFile
	 *            the file that contains the text for navigation
	 * @exception IOException
	 *                if the file does not exist or any error occured while
	 *                reading it
	 */

	public StringNavigator(File textFile, int dataBlockSize) throws IOException {
		this(new FileInputStream(textFile), dataBlockSize);
	}

	/**
	 * Constructs the class with the given file and will read the information
	 * according to the provided data block size
	 * 
	 * @param textFile
	 *            the file that contains the text for navigation
	 * @exception IOException
	 *                if the file does not exist or any error occured while
	 *                reading it
	 */

	public StringNavigator(InputStream dataStream, int dataBlockSize)
			throws IOException {
		try {
			if (dataBlockSize > 0) {
				readBlockSize = dataBlockSize;

				readIsSegmented = true;
				fileReader = new StreamReader(dataStream);
				doReadData();
			} else {
				throw new IOException("Not Suitable data size");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new IOException("Error extracting the text from the file");
		}
	}

	public void close() throws Exception {
		// if (fileReader!=null) fileReader.
	}

	public String getText(int charNo, int code) {
		return getText(charNo);
	}

	/**
	 * Reads the text and fetchs the characters according to the given
	 * charachters no also the pointer is advanced accordingly
	 * 
	 * @param charNo
	 *            Number of characters to be read
	 * @return The resulted string and null if any error occured
	 */
	public String getText(int charNo) {
		lastText = "";
		if (charNo > 0) {
			filePointer += charNo; // Advancing the total file pointer
			// Detecting the end of the available text
			if ((pointer + charNo) >= text.length()) {
				lastText = residualText = text
						.substring(pointer, text.length());
				// The case of variable amount of text to be read
				if (!fixedBlockRead) {
					int newBlockPart = charNo - residualText.length();
					if ((doReadData()) && (newBlockPart != 0)) {
						filePointer -= newBlockPart;
						lastText += getText(newBlockPart);
					}
				} else {
					endOfText = true;
				}
			} else if (pointer < 0) {
				int rsidualLength = residualText.length();
				String leftPart = residualText.substring(rsidualLength
						+ pointer);
				charNo += pointer;
				// pointer = charNo;
				pointer = 0;
				lastText = leftPart + text.substring(pointer, pointer + charNo);
				pointer = charNo;
			} else {
				lastText = text.substring(pointer, pointer + charNo);
				pointer += charNo;
			}
		}
		return lastText;
	}

	/**
	 * Reads the next part of code from the file
	 * 
	 * @see #aaa
	 * @param paramName
	 *            comments
	 * @exception XxxxxxException
	 *                if ...
	 * @return comments
	 */
	private boolean doReadData() {
		boolean readResult = true;
		if (fixedBlockRead) {
			endOfText = true;
		} else {
			if (isWithDelimiter()) {
				fileReader.read(blockDelimSize);
				filePointer += blockDelimSize;
			}
			text = fileReader.read(readBlockSize);
			if (text == null) {
				endOfText = true;
				readResult = false;
			} else {
				pointer = 0;
			}
		}
		return readResult;
	}

	/**
	 * The pointer is advanced with the given position number
	 * 
	 * @param stepsNo
	 *            Number of positions to jumb it can be negative to make the
	 *            pointer move in the reverese direction
	 */

	public void jump(int stepNo) {
		filePointer += stepNo;
		if ((pointer + stepNo) > text.length()) {
			int diff = readBlockSize - pointer;
			int newBlockPart = stepNo - diff;
			pointer = newBlockPart;
			doReadData();
		} else {
			pointer += stepNo;
		}

	}

	public boolean EndOfValue() {
		return endOfText;
	}

	/**
	 * Returns the number of available characters in the buffer
	 * 
	 * @return the left available characters
	 */
	public int availableCharacters() {
		int restLength = getText().length() - pointer - 1;
		return restLength;
	}

	/**
	 * Returns the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text
	 * 
	 * @param newText
	 *            the new text to be processed
	 */
	public void setText(String newText) {
		reset();
		text = newText;
		fixedBlockRead = true;
	}

	public void reset() {
		endOfText = false;
		readIsSegmented = false;
		fixedBlockRead = false;
		pointer = 0;
		filePointer = 0;
	}

	public long getPosition() {
		return filePointer;
	}

	public void setDataBlockSize(int newBlockSize) {
		readBlockSize = newBlockSize;
	}

	public void setDataBlockDelimiterSize(int newDelimiterSize) {
		blockDelimSize = newDelimiterSize;
	}

	public void setWithDelimiter(boolean WithDelimiter) {
		withDelimiter = WithDelimiter;
	}

	public int getDataBlockSize() {
		return readBlockSize;
	}

	public int getDataBlockDelimiterSize() {
		return blockDelimSize;
	}

	public boolean isWithDelimiter() {
		return withDelimiter;
	}

	public String getLastReadText() {
		return lastText;
	}

	/***************************************************************************
	 * / Reads the text and fetchs the characters according to the given
	 * charachters no also the pointer is advanced accordingly @param charNo
	 * Number of characters to be read @return The resulted string and null if
	 * any error occured
	 * 
	 * public String getText(int charNo){
	 * 
	 * if (charNo>0){ filePointer += charNo; String part = null; // Detecting
	 * the end of the available text if ((pointer+charNo) >=
	 * getText().length()){
	 * 
	 * residualText = getText().substring(pointer,getText().length()); // The
	 * case of variable amount of text to be read if (!FixedBlockRead){ int
	 * newBlockPart = charNo - residualText.length(); pointer = newBlockPart; if
	 * ((doReadData())&&(pointer==0)){ filePointer -= newBlockPart; return
	 * residualText + getText(newBlockPart); } }else{ EndOfText = true; } return
	 * residualText; } if (pointer<0) { int rsidualLength =
	 * residualText.length(); String leftPart =
	 * residualText.substring(rsidualLength+pointer); charNo += pointer;
	 * pointer=0; part = leftPart + getText().substring(pointer,pointer+charNo) ;
	 * }else{ part = getText().substring(pointer,pointer+charNo); }
	 * 
	 * 
	 * if ((pointer+charNo)== getText().length()) { doReadData(); }else{
	 * updatePointer(charNo); } return part; } return ""; }
	 */

	public static final int DEFAULT_DATA_BLOCK_SIZE = 1024;
	public static final int DEFAULT_DATA_BLOCK_DELIM_SIZE = 2;

}
