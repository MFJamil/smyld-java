package org.smyld.bw.data.structurs;

import org.smyld.SMYLDObject;

/**
 * This class will hold all channel information
 */
public class Channel extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String institutionNo;
	private String ID;
	private String tranCurrency;
	private String settlementCurrency;
	private String clientNo;
	private String clientBussType;
	private String interchangeInstitution;
	private String auditTrail;
	private String recordDate;
	private String recordType;
	private String cardOrganization;
	private String areaOfEvent;
	private String serviceType;
	private String chargeType;
	private String trnFormat;

	public Channel(String InstitutionNo, String ID, String TranCurrency,
			String SettlementCurrency, String ClientNo, String ClientBussType,
			String InterchangeInstitution, String AuditTrail,
			String RecordDate, String RecordType, String CardOrganization,
			String AreaOfEvent, String ServiceType, String ChargeType,
			String TrnFormat) {

		this(ID, TranCurrency, SettlementCurrency, ClientNo, ClientBussType,
				InterchangeInstitution, AuditTrail, RecordDate, RecordType,
				CardOrganization, AreaOfEvent, ServiceType, ChargeType,
				TrnFormat);
		this.trnFormat = TrnFormat;
	}

	public Channel(String InstitutionNo, String ID, String TranCurrency,
			String SettlementCurrency, String ClientNo, String ClientBussType,
			String InterchangeInstitution, String AuditTrail,
			String RecordDate, String RecordType, String CardOrganization,
			String AreaOfEvent, String ServiceType, String ChargeType) {
		this.institutionNo = InstitutionNo;
		this.ID = ID;
		this.tranCurrency = TranCurrency;
		this.settlementCurrency = SettlementCurrency;
		this.clientNo = ClientNo;
		this.clientBussType = ClientBussType;
		this.interchangeInstitution = InterchangeInstitution;
		this.auditTrail = AuditTrail;
		this.recordDate = RecordDate;
		this.recordType = RecordType;
		this.cardOrganization = CardOrganization;
		this.areaOfEvent = AreaOfEvent;
		this.serviceType = ServiceType;
		this.chargeType = ChargeType;
	}

	public String getInstitutionNo() {
		return this.institutionNo;
	}

	public String getID() {
		return this.ID;
	}

	public String getTranCurrency() {
		return this.tranCurrency;
	}

	public String getSettlCurrency() {
		return this.settlementCurrency;
	}

	public String getClientNo() {
		return this.clientNo;
	}

	public String getClientBussType() {
		return this.clientBussType;
	}

	public String getInterchangeInstitution() {
		return this.interchangeInstitution;
	}

	public String getAuditTrail() {
		return this.auditTrail;
	}

	public String getRecordDate() {
		return this.recordDate;
	}

	public String getRecordType() {
		return this.recordType;
	}

	public String getCardOrganization() {
		return this.cardOrganization;
	}

	public String getAreaOfEvent() {
		return this.areaOfEvent;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public String getChargeType() {
		return this.chargeType;
	}

	public String getTrnFormat() {
		return this.trnFormat;
	}

	public String getKey() {
		if ((institutionNo != null) && (ID != null))
			return institutionNo + ID;
		return null;
	}

	@Override
	public void printInstanceValues() {
		insertInstanceValue(" Institution No          :" + institutionNo);
		insertInstanceValue(" ID                      :" + ID);
		insertInstanceValue(" Tran Currency           :" + tranCurrency);
		insertInstanceValue(" Settlement Currency     :" + settlementCurrency);
		insertInstanceValue(" Client No               :" + clientNo);
		insertInstanceValue(" Client Buss Type        :" + clientBussType);
		insertInstanceValue(" Interchange Institution :"
				+ interchangeInstitution);
		insertInstanceValue(" Audit Trail             :" + auditTrail);
		insertInstanceValue(" Record Date             :" + recordDate);
		insertInstanceValue(" Record Type             :" + recordType);
		insertInstanceValue(" Card Organization       :" + cardOrganization);
		insertInstanceValue(" Area Of Event           :" + areaOfEvent);
		insertInstanceValue(" Service Type            :" + serviceType);
		insertInstanceValue(" Charge Type             :" + chargeType);
		insertInstanceValue(" Trn Format              :" + trnFormat);
	}
}
