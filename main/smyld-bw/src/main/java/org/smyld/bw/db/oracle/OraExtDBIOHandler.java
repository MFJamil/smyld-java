package org.smyld.bw.db.oracle;

import org.smyld.bw.db.BWDataBase;
import org.smyld.db.DBErrorHandler;
import org.smyld.db.oracle.OraDBIOHandler;

public class OraExtDBIOHandler extends OraDBIOHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SMYLDOraExtTableSaver extrnalSaver;
	BWDataBase bwDataBase;
	OraImpRecord curRecord;
	String extFolder;

	public OraExtDBIOHandler(DBErrorHandler e, BWDataBase bwDataBase,
			String folder) {
		super(e, bwDataBase.getSMYLDConnection());
		this.bwDataBase = bwDataBase;
		setExtFolder(folder);
	}

	@Override
	public void importFile(String dumpKey, String targetTable, String fileName,
			String separator) throws Exception {
		super.importFile(dumpKey, targetTable, fileName, separator);
	}

	@Override
	protected void doInitializeProcess(String targetTable) throws Exception {
		extrnalSaver = new SMYLDOraExtTableSaver(getExtFolder(), targetTable,
				bwDataBase);
		curRecord = new OraImpRecord();
		extrnalSaver.init();

	}

	@Override
	protected void doClearProcess() throws Exception {
		extrnalSaver.close();
	}

	public String getExtFolder() {
		return extFolder;
	}

	public void setExtFolder(String extFolder) {
		this.extFolder = extFolder;
	}

	@Override
	protected void insertLine(String[] data) throws Exception {
		curRecord.setData(data);
		extrnalSaver.saveRecord(curRecord);
	}

}
