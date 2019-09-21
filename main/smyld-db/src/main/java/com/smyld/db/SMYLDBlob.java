package com.smyld.db;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class SMYLDBlob implements Blob {
	byte[] bytesData;

	public SMYLDBlob(byte[] BytesData) {
		bytesData = BytesData;
	}

	public long length() throws SQLException {
		return bytesData.length;
	}

	public byte[] getBytes(long pos, int length) throws SQLException {
		if ((pos + length) < length()) {
			byte[] part = new byte[length];
			String longValue = Long.toString(pos);
			int intPos = Integer.parseInt(longValue);
			System.arraycopy(bytesData, intPos, part, 0, length);
			return part;
		}
		throw new SQLException("Array out of bound");
	}

	public InputStream getBinaryStream() throws SQLException {
		// return new ByteArrayInputStream(bytesData);
		return null;
	}

	public OutputStream setBinaryStream(long size) throws SQLException {
		return null;
	}

	public long position(byte[] pattern, long start) throws SQLException {
		return 0;
	}

	public long position(Blob pattern, long start) throws SQLException {
		return 0;
	}

	public int setBytes(long pos, byte[] bytes) throws SQLException {
		return 0;
	}

	public int setBytes(long pos, byte[] bytes, int offset, int len)
			throws SQLException {
		return 0;
	}

	public void truncate(long len) throws SQLException {
	}

	public void free() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public InputStream getBinaryStream(long pos, long length)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
