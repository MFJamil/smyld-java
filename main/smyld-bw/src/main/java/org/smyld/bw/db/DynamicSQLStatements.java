package org.smyld.bw.db;

import org.smyld.db.schema.SchemaObject;
import org.smyld.db.schema.Table;
import org.smyld.db.schema.TableColumn;

public class DynamicSQLStatements extends SQLStatements {
	public DynamicSQLStatements() {
	}

	public static String getContractDetails(boolean useGroup,
			boolean getGroupClt) {
		StringBuffer buffer = new StringBuffer();
		// Select Part
		buffer.append(SEL_cbr_service_contract_CLEINT_LINKs_PART_1);
		if (getGroupClt)
			buffer.append("," + INST_5 + DOT + COL_CIS_CLNT_LNK_CLNT_NUM + SP
					+ CNTR_DETAIL_INST_GRP_CLNT_NUM);
		// From part
		buffer.append(SEL_cbr_service_contract_CLEINT_LINKs_PART_2);
		if (getGroupClt)
			buffer.append(COM + TABLE_CIS_CLNT_LNK + SP + INST_5);
		// Where section
		buffer.append(SEL_cbr_service_contract_CLEINT_LINKs_PART_3);
		// Converting the instances to make it more direct in use
		if (useGroup) {
			buffer.append(AND + INST_4 + DOT + COL_CIS_CLNT_LNK_GRP_NUM + EQM);
			buffer.append(PC);
			buffer.append(AND + INST_1 + DOT + COL_CIS_CLNT_LNK_GRP_NUM + EQM);
		} else {
			buffer.append(PC);
		}
		if (getGroupClt)
			buffer.append(SEL_cbr_service_contract_CLEINT_LINKs_PART_4);
		return buffer.toString();
	}

	public static String getCardUpdate(String targetTable) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(UPD);
		buffer.append(targetTable);
		buffer.append(SET);
		buffer.append(COL_SVC_CLNT_CARDS_CARD_NUM);
		buffer.append(EQM);
		buffer.append(WHR);
		buffer.append(COL_ROW_ID);
		buffer.append(EQM);
		return buffer.toString();
	}

	public static String getCardSelect(String targetTable) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(SEL);
		buffer.append(COL_ROW_ID);
		buffer.append(COM);
		buffer.append(COL_SVC_CLNT_CARDS_CARD_NUM);
		buffer.append(FRM);
		buffer.append(targetTable);

		return buffer.toString();
	}
	public static String getTableSelect(Table table){
		StringBuffer buffer = new StringBuffer();
		buffer.append(SEL);
		buffer.append(COL_ROW_ID);
		for(SchemaObject curCol:table.getCols().values()){
			buffer.append(COM);
			buffer.append(curCol.getName());
			
		}
		buffer.append(FRM);
		buffer.append(table.getName());

		return buffer.toString();
		
	}
	public static String getTableUpdate(Table table) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(UPD);
		buffer.append(table.getName());
		buffer.append(SET);
		boolean firstItem = true;
		for(SchemaObject curCol:table.getCols().values()){
			if (!firstItem)
				buffer.append(COM);
			buffer.append(curCol.getName());
			buffer.append(EQM);
			firstItem = false;
		}
		buffer.append(WHR);
		buffer.append(COL_ROW_ID);
		buffer.append(EQM);
		return buffer.toString();
	}
	
	public static String getInsertFile(String tableName) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(INS);
		buffer.append(tableName);
		buffer.append(INS_part_file_log_NEW_RECORD);
		return buffer.toString();
	}

	public static String getInsertProcessFile(String tableName) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(INS);
		buffer.append(tableName);
		buffer.append(INS_part_proc_file_log_NEW_RECORD);
		return buffer.toString();
	}

	public static String getCheckFile(String tableName) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(SELA);
		buffer.append(tableName);
		buffer.append(SEL_part_file_log_details_FILE_CHECK);
		return buffer.toString();
	}
}
