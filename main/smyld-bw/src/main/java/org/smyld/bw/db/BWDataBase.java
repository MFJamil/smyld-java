package org.smyld.bw.db;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Vector;

import org.smyld.bw.data.structurs.BWUser;
import org.smyld.db.DBConnection;
import org.smyld.db.DBErrorHandler;
import org.smyld.db.SMYLDDBUtility;
import org.smyld.db.SMYLDDataBaseHandler;
import org.smyld.db.Utility;
import org.smyld.math.HexaDecimal;
import org.smyld.text.TextUtil;

/**
 * This class will handle the whole connection with the data base holding the
 * connection object together with the utility object as static declared and
 * accordingly all the child classes will use the very same connection object
 * and utility.
 * 
 */
public class BWDataBase extends SMYLDDataBaseHandler implements JavaDBValue,
		DBName {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * In this constructor, the class will open connection to the data base, and
	 * will load the required tables according to method calls
	 */
	public BWDataBase(DBErrorHandler e) {
		super(e);
	}

	public BWDataBase(DBErrorHandler e, DBConnection connection) {
		super(e, connection);
	}

	public BWDataBase(DBErrorHandler e, String userName, String userPassword,
			String host, String port, String dbname) {
		super(e, userName, userPassword, host, port, dbname);
	}

	public BWDataBase(int driver, DBErrorHandler e, String userName,
			String userPassword, String host, String port, String dbname) {
		super(driver, e, userName, userPassword, host, port, dbname);
	}

	public synchronized String createSequentialNumber(String tableName,
			String columnName, String institutionNo, int digitWidth) {
		long result = dbUtility.getSequentialID(tableName, columnName,
				COL_SYS_INST_INST_NUM, institutionNo);
		return TextUtil.fillLeftSide(result, digitWidth, '0');
	}

	public synchronized String createSequentialNumber(String tableName,
			String columnName, String institutionNo) {
		return createSequentialNumber(tableName, columnName, institutionNo,
				DB_SEQUENTIAL_NO_WIDTH);
	}

	public synchronized String createSequentialNumber(String tableName,
			String columnName, int digitWidth) {
		long result = dbUtility.getSequentialID(tableName, columnName);
		return TextUtil.fillLeftSide(result, digitWidth, '0');
	}

	public String getAbsoluteSequenceValue(String seqName) {
		return TextUtil.fillLeftSide(seqName, DB_SEQUENTIAL_NO_WIDTH, '0');
	}

	public String getSequenceValue(String seqName) {
		return TextUtil.fillLeftSide(dbUtility.getSequenceValue(seqName),
				DB_SEQUENTIAL_NO_WIDTH, '0');
	}

	public String getSequenceValue(String seqName, int digitWidth) {
		return TextUtil.fillLeftSide(dbUtility.getSequenceValue(seqName),
				digitWidth, '0');
	}

	public synchronized String createSequentialNumber(String tableName,
			String columnName) {
		return createSequentialNumber(tableName, columnName,
				DB_SEQUENTIAL_NO_WIDTH);
	}

	public HashMap<String,String> loadSysConfigSection(String sectionName) {
		ResultSet rsSection = dbUtility.getSingleParamSQL(sectionName,
				SQLStatements.SEL_sys_configuration_SECTION_RECORDS);
		return getSectionValues(rsSection);
	}

	public HashMap<String,String> loadSysConfigSection(String sectionName, String institutionNo) {
		ResultSet rsSection = dbUtility.getDoubleParamSQL(sectionName,
				institutionNo,
				SQLStatements.SEL_sys_configuration_INST_SECTION_RECORDS);
		return getSectionValues(rsSection);
	}

	public HashMap<String,String> getOracleTablesContainingFieldName(String fieldName) {
		return dbUtility.getTablesContainingFieldName(fieldName);
	}

	public String getOracleDirectoryPath(String directoryName) {
		return dbUtility.getOracleDirectory(directoryName);
	}

	private HashMap<String,String> getSectionValues(ResultSet rsSection) {
		HashMap<String,String> result = null;
		if (rsSection != null) {
			try {
				while (rsSection.next()) {
					if (result == null)
						result = new HashMap<String,String>();
					String configKey = rsSection
							.getString(COL_SYS_CONFIG_KEYWORD);
					String keyValue = rsSection.getString(COL_SYS_CONFIG_VALUE);
					result.put(configKey, keyValue);
				}
			} catch (Exception ex) {
				handleDBError(ex);
			} finally {
				dbUtility.closeCursor(rsSection);
			}
		}
		return result;

	}

	public SMYLDDBUtility getSMYLDDBUtility() {
		return dbUtility;
	}

	public Vector<String> getInstitutionsAvailableForConfigSection(String sectionName) {
		ResultSet rsInsts = dbUtility.getSingleParamSQL(sectionName,
				SQLStatements.SEL_sys_configuration_INST_IN_SECTION_RECORDS);
		Vector<String> result = null;
		try {
			while (rsInsts.next()) {
				if (result == null)
					result = new Vector<String>();
				String instNo = rsInsts.getString(COL_SYS_CONFIG_INSTNR);
				result.add(instNo);
			}
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsInsts);
		}

		return result;
	}

	public int callInsertExternal(String tableName, String procName) {
		CallableStatement st = null;
		try {
			String funcText = Utility
					.constructTrippleParamFunctionCall(FUNC_INS_EXT_TABLE);
			st = dbConnection.prepareCall(funcText);
			st.registerOutParameter(1, Types.NUMERIC);
			st.setString(2, tableName);
			st.setString(3, ""); // File name is not implemented need to be
									// replaced with folder name
			st.setString(4, procName);
			st.execute();
			return st.getInt(1);
		} catch (SQLException e) {
			handleDBError(e);
		} finally {
			dbUtility.closeCursor(st);
		}
		return 0;
	}

	public int getTotalTablesSize(HashMap<String,String> tables) {
		int totalRecNo = 0;
		for (String curTableName : tables.keySet()) {
			totalRecNo += getTableTotalRecordsNumber(curTableName);
		}
		return totalRecNo;
	}

	public HashMap<String,BWUser> loadUsers() {
		PreparedStatement pstUsers = null;
		ResultSet rsUsers = null;
		HashMap<String,BWUser>  users = null;
		try {
			pstUsers = dbConnection
					.prepareStatement(SQLStatements.SEL_sys_user_info_ALL_RECORDS);
			rsUsers = pstUsers.executeQuery();
			while (rsUsers.next()) {
				if (users == null)
					users = new HashMap<String,BWUser> ();
				BWUser newUser = createUser(rsUsers);
				users.put(newUser.getID(), newUser);
			}
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(pstUsers);
		}
		return users;

	}

	// Oracle specific function for detecting the session existence
	public boolean doClientSessionExists(String clientID) {
		String sql = "select * from SYS.V_$SESSION where client_identifier='"
				+ clientID + "'";
		return dbUtility.isExist(sql);
	}

	public BWUser loadUser(String usrID) {
		PreparedStatement pstUsers = null;
		ResultSet rsUsers = null;
		BWUser newUser = null;
		try {
			pstUsers = dbConnection
					.prepareStatement(SQLStatements.SEL_sys_user_info_SINGLE_RECORD);
			pstUsers.setString(1, usrID);
			rsUsers = pstUsers.executeQuery();
			if (rsUsers.next())
				newUser = createUser(rsUsers);
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(pstUsers);
		}
		return newUser;

	}

	private BWUser createUser(ResultSet rsUser) throws Exception {
		BWUser newUser = new BWUser();
		newUser.setID(rsUser.getString(COL_SYS_USR_INFO_ID));
		newUser.setName(rsUser.getString(COL_SYS_USR_INFO_NAME));
		newUser.setShortName(rsUser.getString(COL_SYS_USR_INFO_SHORT_NAME));
		newUser.setLanguage(rsUser.getString(COL_SYS_USR_INFO_LANG));
		newUser.setBankCode(rsUser.getString(COL_SYS_USR_INFO_BANK_CODE));
		newUser.setBranchCode(rsUser.getString(COL_SYS_USR_INFO_BRANCH_CODE));
		newUser.setDeptCode(rsUser.getString(COL_SYS_USR_INFO_DEPT_CODE));
		newUser.setAdmin("-1".equals(rsUser.getString(COL_SYS_USR_INFO_ADMIN)));
		newUser.setDeveloper("-1".equals(rsUser
				.getString(COL_SYS_USR_INFO_DEVELOPER)));
		newUser.setRs2Internal("-1".equals(rsUser
				.getString(COL_SYS_USR_INFO_SMYLD_INTERN)));
		newUser.setStatus(rsUser.getInt(COL_SYS_USR_INFO_STATUS));
		newUser.setDefInst(rsUser.getString(COL_SYS_USR_INFO_DEF_INST));
		newUser.setPassword(rsUser.getString(COL_SYS_USR_INFO_PWD));
		// Those fields need further processing
		newUser.setAccessGroup(rsUser.getString(COL_SYS_USR_INFO_ACCESS_GRP));
		// Reading the valid institutions
		String validInsts = rsUser.getString(COL_SYS_USR_INFO_VALID_INST);
		String validBin = HexaDecimal.convertToBinary(validInsts);
		//HashMap<String,String> validInstsGroup = new HashMap<String,String>();
		for (int i = 0; i < validBin.length(); i++) {

		}

		// validInstsGroup.put()

		// System.out.println("Hexa value (" + validInsts + ") bin (" + validBin
		// + ")");
		newUser.setAccessModule(rsUser
				.getString(COL_SYS_USR_INFO_MODULE_ACCESS));
		return newUser;
	}

}
