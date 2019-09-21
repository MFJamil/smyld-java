package org.smyld.gui.portal.engine.sources;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import org.smyld.SMYLDObject;
import org.smyld.db.DBErrorHandler;
import org.smyld.db.DBSettings;
import org.smyld.db.oracle.SMYLDOracleConnection;
import org.smyld.io.FileSystem;

public class PortalResourceHandler extends SMYLDObject implements DBErrorHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * This class will handel the following : 1 - Conversion from any resource
	 * to data base resource. 2 - Conversion from any resource to XML resource.
	 */
	public PortalResourceHandler() {
	}

	public void convertToXML(PESwingApplicationReader res, String targetXMLFile)
			throws IOException {
		// In case the source is an XML file we can only copy the file directly
		if (res instanceof PortalAppXMLSetReaderPESwing) {
			File resFile = new File(((PortalAppXMLSetReaderPESwing) res)
					.getSourceXMLFileName());
			FileSystem.copyFile(resFile, new File(targetXMLFile));
		} else {
			PortalAppXMLSetWriter xmlWriter = new PortalAppXMLSetWriter();
			xmlWriter.generateXML(res, targetXMLFile);
		}
	}

	public void convertToDatabase(PESwingApplicationReader res,
                                  DBSettings targetDatabase, String targetAppID) throws Exception {
		// We need to review the free selection of any data base in the future
		SMYLDOracleConnection smyldOraConn = new SMYLDOracleConnection(
				SMYLDOracleConnection.DRIVER_THIN, targetDatabase);
		PortalDBWriter dbWriter = new PortalDBWriter(this, smyldOraConn);
		dbWriter.generateApplication(res, targetAppID);

	}

	public void convertToDatabase(String xmlResFile, DBSettings targetDatabase,
			String targetAppID) throws Exception {
		// We need to review the free selection of any data base in the future
		PortalAppXMLSetReaderPESwing resReader = new PortalAppXMLSetReaderPESwing(xmlResFile,null);
		convertToDatabase(resReader, targetDatabase, targetAppID);
	}

	public boolean addError(Exception e, Connection c) {
		e.printStackTrace();
		return false;
	}

}
