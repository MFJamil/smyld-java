package com.smyld.net;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.smyld.SMYLDObject;

public class InternetUtil extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InternetUtil() {
	}

	public static byte[] savePageItem(String urlPage, String targetFile) {
		byte[] itemContents = null;
		try {
			FileOutputStream fout = new FileOutputStream(targetFile);
			itemContents = getPageContentsAsBytes(urlPage);
			if (itemContents != null) {
				fout.write(itemContents);
				fout.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return itemContents;

	}

	public static String savePageContents(String urlPage, String targetFile) {
		String pageContents = null;
		try {
			FileOutputStream fout = new FileOutputStream(targetFile);
			pageContents = getPageContents(urlPage);
			fout.write(pageContents.getBytes());
			fout.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return pageContents;

	}

	public static String getPageContents(String urlPage) {
		byte[] contents = getPageContentsAsBytes(urlPage);
		if (contents != null)
			return new String(contents);
		return null;
	}

	public static byte[] getPageContentsAsBytes(String urlPage) {
		try {
			URL test = new URL(urlPage);
			URLConnection conn = test.openConnection();
			conn.connect();
			InputStream stream = conn.getInputStream();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] data = new byte[256];
			int dataSize = 0;
			while ((dataSize = stream.read(data)) != -1) {
				out.write(data, 0, dataSize);
			}
			out.flush();
			out.close();
			return out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
