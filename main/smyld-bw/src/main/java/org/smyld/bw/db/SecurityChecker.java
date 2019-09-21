package org.smyld.bw.db;

/**
 * This class will handle all the requests that checks for security related
 *  topics.
 */
import java.sql.ResultSet;

import org.smyld.bw.sys.SecurityException;
import org.smyld.db.DBConnection;
import org.smyld.db.DBErrorHandler;

public class SecurityChecker extends BWDataBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecurityChecker(DBErrorHandler e, DBConnection connection) {
		super(e, connection);
	}

	public SecurityChecker(DBErrorHandler e) {
		super(e);
	}

	/**
	 * This method will check for the installation number in the data base and
	 * if it is more than one value a security exception must be thrown
	 */
	public String getInstallationNo() throws SecurityException {
		String installationNo = null;
		ResultSet rsInstallationMethode = null;
		try {
			rsInstallationMethode = dbUtility
					.executeQuery(SQLStatements.SEL_sys_inst_license_INSTALLATION_CHECK);
			if (rsInstallationMethode.next()) {
				installationNo = rsInstallationMethode
						.getString(COL_SYS_INST_INSTALLATION_NUM);
				if (rsInstallationMethode.next()) {
					installationNo = null;
					throw new SecurityException(
							SecurityException.Seq_Ex_txt_Multi_Installation_no,
							SecurityException.Seq_Ex_Multi_Installation_no);
				}
			}
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsInstallationMethode);
		}
		return installationNo;
	}

}
