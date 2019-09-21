package org.smyld.bw.util;

import java.io.File;

import org.smyld.bw.data.structurs.SystemJob;
import org.smyld.bw.db.BWDBAccess;
import org.smyld.bw.db.DBValue;
import org.smyld.text.TextUtil;
import org.smyld.util.SMYLDDate;
import org.smyld.util.Util;

public class BWUtility extends Util {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BWUtility() {

	}

	public static synchronized String createAuditTrial(
			SystemJob currentSystemJob, String trnID) {
		return createAuditTrial(currentSystemJob.getUserID(),currentSystemJob.getStationNo(), trnID);
	}
	public static synchronized String createAuditTrial(String userID,String stationNo, String trnID) {
		SMYLDDate currentDate = new SMYLDDate();
		if (trnID == null)
			trnID = AUDIT_TRIAL_TRN_NO;
		String auditTrial = currentDate.toString(SMYLDDate.FRM_yyDDD_HHmmss)
				+ "-" + userID + "-"+ stationNo + (trnID!=null? "-" + trnID:"");
		//+ "-"+ currentDate.getTimeSinceMidNight(); Old implementation for HVE, must be equal to 35 as maximum

		return auditTrial;
	}
	
	public static synchronized String getValidValueRange(String value,
			int from, int to) {
		if (TextUtil.isEmpty(value))
			return null;
		int intValue = -1;
		try {
			intValue = Integer.parseInt(value);
		} catch (Exception ex) {
		}
		if (((intValue < from) && (intValue > to)) || (intValue == -1))
			return null;
		return value;
	}

	public static synchronized String validateCityName(String cityOriginalName) {
		char[] cityName = cityOriginalName.toCharArray();
		if (cityName[0] != Character.SPACE_SEPARATOR) {
			if (!Character.isDigit(cityName[0])) {
				for (int i = 0; i < cityName.length; i++)
					if (!((Character.isLetterOrDigit(cityName[i])) || (cityName[i] == '*')))
						cityName[i] = ' ';
				if (cityName[0] != ' ')
					return new String(cityName);
			}
		}
		return COUNTRY_CODE_DEFAULT;
	}

	public static synchronized double getISOAmount(String amount,
			int CurrencyExp) {
		double result = 0;
		if (!TextUtil.isEmpty(amount)) {
			amount = amount.trim();
			if (CurrencyExp >= 0) {
				try {
					result = Integer.parseInt(amount)
							/ (Math.pow(10, CurrencyExp));
				} catch (Exception ex) {
					result = Integer.parseInt(amount.substring(1))
							/ (Math.pow(10, CurrencyExp));
					if (amount.startsWith("D"))
						result = -1 * result;
				}
			}
		}
		return result;
	}

	private static synchronized String addTrailingZeros(String value, int exp) {
		int dotLoc = value.indexOf('.');
		if (dotLoc != -1) {
			String decimalPart = value.substring(0, dotLoc);
			String partialPart = value.substring(dotLoc + 1);
			if (partialPart.length() < exp)
				partialPart = TextUtil.fillRightSide(partialPart, exp, '0');
			return decimalPart + '.' + partialPart;
		}
		return value;
	}

	public static synchronized double getISOAmount(String amount,
			String CurrCode) {
		int currExp = BWDBAccess.getCurrencyExponent(CurrCode);
		if (currExp != -1)
			return getISOAmount(amount, currExp);
		return 0;
	}

	public static synchronized String getISOAmountText(String amount,
			String CurrCode, boolean withTrailingZeros) {
		String result = Double.toString(getISOAmount(amount, CurrCode));
		if (withTrailingZeros)
			return addTrailingZeros(result, BWDBAccess
					.getCurrencyExponent(CurrCode));
		return result;

		// return Double.toString(getISOAmount(amount,CurrCode));
	}

	public static synchronized String getISOAmountText(String amount,
			String CurrCode) {
		return Double.toString(getISOAmount(amount, CurrCode));
	}

	public static synchronized String getISOAmountText(String amount,
			int CurrencyExp) {
		return Double.toString(getISOAmount(amount, CurrencyExp));
	}

	public static synchronized String getISOAmountText(String amount,
			int CurrencyExp, boolean withTrailingZeros) {
		String result = Double.toString(getISOAmount(amount, CurrencyExp));
		if (withTrailingZeros)
			return addTrailingZeros(result, CurrencyExp);
		return result;
	}

	public static synchronized String getCountryCode(String code) {
		if (!TextUtil.isEmpty(code)) {
			if (code.length() == 2)
				return (String) BWDBAccess.countryCodes2.get(code);
			if (code.length() == 3)
				return (String) BWDBAccess.countryCodes3.get(code);
		}
		return DBValue.DB_VALUE_NOT_EXIST;
	}

	public static synchronized String getCountryByStateCode(String code) {
		return (String) BWDBAccess.countryStateCodes.get(code);
	}

	public static synchronized boolean validateCountryState(String country,
			String state) {
		if (doHaveStateCode(country)) {
			String countryCode = (String) BWDBAccess.countryStateCodes
					.get(state);
			if (countryCode != null)
				return country.equals(countryCode);
		} else if (TextUtil.isEmpty(state)) {
			return true;
		}
		return false;
	}

	public static synchronized boolean doHaveStateCode(String CountryCode) {
		return ((CountryCode != null) && ((CountryCode
				.equals(DBValue.COUNTRY_UNITED_STATES))
				|| (CountryCode.equals(DBValue.COUNTRY_CANADA)) || (CountryCode
				.equals(DBValue.COUNTRY_AUSTRALIA))));
	}

	/**
	 * This method will validate the given address and will return the result,
	 * the validation contains whether the given country code exists and if so,
	 * whether the given state exists.
	 */
	public static synchronized boolean validateAddress(String countryCode,
			String cityName, String stateCode, String postalCode) {
		boolean result = false;
		if ((!TextUtil.isEmpty(countryCode)) && (!TextUtil.isEmpty(cityName))) {
			if (doHaveStateCode(countryCode)) {
				if (!TextUtil.isEmpty(stateCode)) {
					if ((!countryCode.equals(DBValue.COUNTRY_AUSTRALIA))
							&& (!countryCode
									.equals(getCountryByStateCode(stateCode))))
						stateCode = null;
					if ((stateCode != null) && (!TextUtil.isEmpty(postalCode)))
						result = true;
				} else if (countryCode.equals(DBValue.COUNTRY_AUSTRALIA)) {
					// CHG need to load it from sys_configuration table -
					// processing - AllowSpacesAsState
					// Australia allows spaces in state
					result = true;
				}
			} else if (TextUtil.isEmpty(stateCode)) {
				result = true;
			}
		}
		return result;
	}

	public static synchronized String generateMaskFileName(String origNAme)
			throws Exception {
		File testFile = new File(origNAme);
		String fileName = testFile.getName();
		String pathName = testFile.getParent();
		StringBuffer buffer = new StringBuffer(fileName.length());
		StringBuffer dateBuffer = new StringBuffer();
		int datePos = 0;
		boolean insideMask = false;
		for (int i = 0; i < fileName.length(); i++) {
			char curChar = fileName.charAt(i);
			if (curChar == '[') {
				insideMask = true;
				datePos = i;
			} else if (curChar == ']') {
				insideMask = false;
			} else if (insideMask) {
				dateBuffer.append(curChar);
			} else {
				buffer.append(curChar);
			}
		}
		SMYLDDate targetDate = new SMYLDDate(dateBuffer.toString());
		buffer.insert(datePos, targetDate.toString());
		String originalFile = pathName + File.separator + buffer.toString();
		return originalFile;

	}

	public static final String COUNTRY_CODE_DEFAULT = "";
	public static final String AUDIT_TRIAL_TRN_NO = "99998";
}
