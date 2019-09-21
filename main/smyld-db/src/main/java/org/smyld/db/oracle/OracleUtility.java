package org.smyld.db.oracle;

import java.io.File;
import java.util.HashMap;

import org.smyld.SMYLDObject;
import org.smyld.db.DBSettings;
import org.smyld.io.FileSystem;

public class OracleUtility extends SMYLDObject implements OraConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OracleUtility() {
	}

	public static HashMap<String,DBSettings> readOracleConnectionsSettings() {
		String fileName = getTNSNamesFile();
		if (fileName != null)
			return parseOracleConnectionsSettings(fileName);
		return null;
	}

	public static HashMap<String,DBSettings> parseOracleConnectionsSettings(String tnsNamesFile) {
		TNSNamesParser parser = new TNSNamesParser();
		return parser.parseConnections(tnsNamesFile);
	}

	public static String getTNSNamesFile() {
		String oraclePath = FileSystem.getEnvVariable(ORA_ENV_VAR_NAME);
		if (oraclePath != null)
			oraclePath += ORA_PATH_NAME_NET_ADMIN + File.separator
					+ ORA_FILE_NAME_TNS_NAMES;
		return oraclePath;
	}

}
