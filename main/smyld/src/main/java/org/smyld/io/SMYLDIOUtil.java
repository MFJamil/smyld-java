package org.smyld.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.util.Vector;

import org.smyld.SMYLDObject;

public class SMYLDIOUtil extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SMYLDIOUtil() {
	}

	/**
	 * This method will open a file which have line separated file and return
	 * the data read in vector
	 * 
	 * @param filePath
	 *            the name of the file to read from
	 * 
	 */
	public static Vector<String> readFileLines(String filePath) {
		Vector<String> result = new Vector<String>();
		try {
			LineNumberReader reader = new LineNumberReader(new FileReader(
					filePath));
			String currentMessage = null;
			while ((currentMessage = reader.readLine()) != null)
				result.add(currentMessage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static int getFilesCount(File startPath, int initialCount) {
		File[] list = startPath.listFiles();
		for (int i = 0; i < list.length; i++) {
			if (list[i].isDirectory())
				initialCount += getFilesCount(list[i], 0);
			else
				initialCount++;
		}
		return initialCount;
	}

	public static String readStream(InputStream sreader) {
		try {
			StringBuffer errWriter = new StringBuffer();
			byte[] err = new byte[2048];
			int realSize = 0;
			while ((realSize = sreader.read(err)) != -1) {
				errWriter.append(new String(err, 0, realSize));
			}
			if (errWriter.toString().length() > 0)
				return errWriter.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
