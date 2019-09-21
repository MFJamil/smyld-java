package org.smyld.test;

import java.sql.Connection;

import org.smyld.SMYLDObject;
import org.smyld.db.DBConnection;
import org.smyld.db.DBErrorHandler;
import org.smyld.db.DBSettings;
import org.smyld.db.oracle.SMYLDOracleConnection;
import org.smyld.gui.portal.engine.sources.PortalDBReaderPESwing;
import org.smyld.gui.portal.engine.sources.PortalResourceHandler;
import org.smyld.gui.portal.engine.sources.SQLStatements;

public class Tester extends SMYLDObject implements DBErrorHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tester() {

		try {
			testDBToXML();
			// testDBReader();
			// testResHandler();
			// printSQL();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Tester();
	}

	@SuppressWarnings("unused")
	private void printSQL() {
		System.out.println(SQLStatements.SEL_APP_LANGS);
	}

	@SuppressWarnings("unused")
	private void testDBReader() throws Exception {
		DBSettings settings = new DBSettings("localhost", "smyldbw", "1521",
				"bw3", "bw3data");
		DBConnection smyldConn = new SMYLDOracleConnection(
				SMYLDOracleConnection.DRIVER_THIN, settings);
		PortalDBReaderPESwing reader = new PortalDBReaderPESwing(this, smyldConn);
		reader.readApplication("BWMGUI");
		System.out.println(reader.getSourcePath());
	}

	@SuppressWarnings("unused")
	private void testResHandler() throws Exception {
		DBSettings settings = new DBSettings("localhost", "smyldbw", "1521",
				"bw3", "bw3data");
		PortalResourceHandler handler = new PortalResourceHandler();
		// handler.convertToDatabase("D:/portal/Projects/sources/arabickitchen/arabickitchen.xml",settings,"ARKICH");
		handler
				.convertToDatabase(
						"D:/Projects/TestApplications/PortalSiteUpdator/sources/siteupdator.xml",
						settings, "SITE");

	}

	private void testDBToXML() throws Exception {
		/*
		DBSettings settings = new DBSettings("localhost", "smyldbw", "1521",
				"bw3", "bw3data");
		DBConnection smyldConn = new SMYLDOracleConnection(
				SMYLDOracleConnection.DRIVER_THIN, settings);
		PortalDBReader reader = new PortalDBReader(this, smyldConn);
		reader.readApplication("SITE");
		PortalAppXMLSetWriter xmlWriter = new PortalAppXMLSetWriter();
		xmlWriter.generateXML(reader, "c:/SMYLD_SITE.xml");

		System.out.println(reader.getSourcePath());
		*/
	}

	public boolean addError(Exception e, Connection c) {
		e.printStackTrace();
		return false;
	}
}
