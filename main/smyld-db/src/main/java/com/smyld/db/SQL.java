package com.smyld.db;

/**
 * This class will hold all the string constants used in SQL statements which
 * will facilitate the text typing for the programmer.
 * 
 * 
 * Example "Select * from " + tableName1 can be written as SQL.SELA + tableName1
 * 
 * Please note that the constants ends with space to spare the programmer adding
 * it
 */
public interface SQL {
	/**
	 * SEL (SELect) Holds the value of "Select "
	 */
	public static final String SEL = "Select ";
	/**
	 * FRM (FRoM) Holds the value of " from "
	 */

	public static final String FRM = " from ";
	/**
	 * Holds the value of "* "
	 */
	public static final String ALL = "* ";
	/**
	 * Holds the value of " "
	 */
	public static final String SP = " ";
	/**
	 * SELA (SELect All from) Holds the value of "Select * from "
	 */
	public static final String SELA = "Select * from ";
	/**
	 * COUNT_COLUMN Holds the value of "RECORD_COUNT"
	 */
	public static final String COUNT_COLUMN = "RECORD_COUNT";

	/**
	 * SELC (SELect Count of all from) Holds the value of "Select Count(*) from "
	 */
	public static final String SELCA = "Select Count(*) as " + COUNT_COLUMN
			+ " from ";

	/**
	 * SELC (SELect Count of all from) Holds the value of "select count(rownum)
	 * from "
	 */
	public static final String SELCRN = "select count(rownum) from ";

	/**
	 * DEL (DELete from) Holds the value of "Delete from "
	 */
	public static final String DEL = "Delete from ";
	/**
	 * INS (INSert into) Holds the value of "Insert into "
	 */
	public static final String INS = "Insert into ";
	/**
	 * UPD (UPDate) Holds the value of "Update "
	 */
	public static final String UPD = "Update ";
	/**
	 * Holds the value of "Set "
	 */
	public static final String SET = " Set ";

	/**
	 * VAL (VALues) Holds the value of " Values "
	 */
	public static final String VAL = " Values ";
	/**
	 * VAO (VAlues Of) Holds the value of " Values ("
	 */
	public static final String VAO = " Values (";
	/**
	 * WHR (WHeRe ) Holds the value of " Where "
	 */
	public static final String WHR = " Where ";
	/**
	 * ORD (ORDer by) Holds the value of " Order By "
	 */
	public static final String ORD = " Order By ";
	/**
	 * GRP (GRouP by) Holds the value of " Group By "
	 */
	public static final String GRP = " Group By ";
	/**
	 * EQ (EQual) Holds the value of "="
	 */
	public static final String EQ = "=";
	/**
	 * NEQ (Not EQual) Holds the value of "<>"
	 */
	public static final String NEQ = "<>";
	/**
	 * AND (AND) Holds the value of " AND "
	 */
	public static final String AND = " AND ";
	/**
	 * OR (OR) Holds the value of " OR "
	 */
	public static final String OR = " OR ";
	/**
	 * GR (GReater than ) Holds the value of "> "
	 */
	public static final String GR = "> ";
	/**
	 * GRE (GReater than or Equal ) Holds the value of ">= "
	 */
	public static final String GRE = ">= ";
	/**
	 * SM (SMaller than) Holds the value of "< "
	 */
	public static final String SM = "< ";
	/**
	 * SME (SMaller than or Equal) Holds the value of "<= "
	 */
	public static final String SME = "<= ";
	/**
	 * COM (COMma)Holds the value of ", "
	 */
	public static final String COM = ", ";
	/**
	 * DOT (DOT) Holds the value of "."
	 */
	public static final String DOT = ".";
	/**
	 * QUM (QUestion Mark) Holds the value of "?"
	 */
	public static final String QUM = "?";

	/**
	 * EQM (EQual question Mark) Holds the value of "=?"
	 */
	public static final String EQM = "=?";
	/**
	 * UPR (UPpeR) Holds the value of " UPPER "
	 */
	public static final String UPR = " UPPER ";
	/**
	 * UPO (UPper Of) Holds the value of " UPPER("
	 */
	public static final String UPO = " UPPER(";

	/**
	 * MAXO (Maximum of) Holds the value of "MAX("
	 */

	public static final String MAXO = " MAX(";

	/**
	 * PC (Praket Close) Holds the value of ") "
	 */
	public static final String PC = ") ";

	/**
	 * PO (Praket Close) Holds the value of " ("
	 */
	public static final String PO = " (";

	/**
	 * PO (Praket Close) Holds the value of " ("
	 */
	public static final String DIST = " distinct ";

	/**
	 * LIKE Holds the value of " LIKE "
	 */
	public static final String LIKE = " LIKE ";

	/**
	 * PR (Percent) Holds the value of "%"
	 */
	public static final String PR = "%";
	/**
	 * PR (Percent) Holds the value of "%"
	 */
	public static final String RightPR = "'?%'";

	/**
	 * SQ (Single Qutation) Holds the value of "'"
	 */
	public static final String SQ = "'";

	/**
	 * COL_MAX_NO Holds the name of the column which will hold the maximum
	 * value"MAX_NO"
	 */
	public static final String COL_MAX_NO = "MAX_NO";

	/**
	 * COUNT_MAX Holds the sql statement for fetching the maximum number of a
	 * given col in a table
	 */
	public static final String COUNT_MAX = "select count(*) as " + COL_MAX_NO
			+ " from ";

	/**
	 * TO_CHAR convert the given number to char
	 */
	public static final String TO_CHAR = " TO_CHAR";

	/**
	 * TO_NUMBER convert the given char to number
	 */
	public static final String TO_NUMBER = " TO_NUMBER";

	/**
	 * TRIM trims the given char from spaces
	 */
	public static final String TRIM = " TRIM";

	/**
	 * IN checks for the existence of the the field value
	 */
	public static final String IN = " IN";

	public static final String ISNULL = " IS NULL";
	/**
	 * CONCAT holds the concatination symbol ||
	 */
	public static final String CONCAT = "||";

	public static final String INST_1 = "inst_1";
	public static final String INST_2 = "inst_2";
	public static final String INST_3 = "inst_3";
	public static final String INST_4 = "inst_4";
	public static final String INST_5 = "inst_5";
	public static final String INST_6 = "inst_6";
	public static final String INST_7 = "inst_7";

	public static final String OUTJ = " (+)";

}
