package com.smyld.jms.aq;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import com.smyld.SMYLDObject;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class AQTextMessage extends SMYLDObject implements SQLData {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see
	 * @since
	 */
	public AQTextMessage() {
	}

	public String getSQLTypeName() throws SQLException {
		return "SYS.AQ$_JMS_TEXT_MESSAGE";
	}

	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		// System.out.println("AQTextMessage class : " + stream.readString());
		System.out.println(stream.readObject().toString());

		System.out.println("hi there");
	}

	public void writeSQL(SQLOutput stream) throws SQLException {
	}
}
