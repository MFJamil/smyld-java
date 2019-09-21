package org.smyld.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SMYLDDate extends Date {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String originalFormat = FRM_yyyyMMdd;
	private String textValue;
	private final SimpleDateFormat dateFormatter = new SimpleDateFormat(
			originalFormat);
	private boolean correct = true;

	public SMYLDDate() {
		super();
	}

	public SMYLDDate(String dateFormat) {
		super();
		setFormat(dateFormat);
		dateFormatter.setLenient(false);
	}

	public SMYLDDate(String dateFormat, String dateValue) {
		setValue(dateValue, dateFormat);
		dateFormatter.setLenient(false);
	}

	public void setLenient(boolean value) {
		dateFormatter.setLenient(value);
	}

	public void setFormat(String newFormat) {
		dateFormatter.applyPattern(newFormat);
		originalFormat = newFormat;
	}

	@Override
	public void setTime(long newTime) {
		super.setTime(newTime);
		correct = true;
	}

	public void setValue(String newValue) {
		textValue = newValue;
		if (isAcceptableValue(newValue)) {
			try {
				setTime(dateFormatter.parse(newValue).getTime());
			} catch (Exception ex) {
				correct = false;
			}
		} else {
			correct = false;
		}
	}

	public void setValue(String newValue, String valueFormat) {
		setFormat(valueFormat);
		setValue(newValue);
	}

	public boolean isCorrect() {
		return correct;
	}

	public static boolean isAcceptableValue(String value) {
		if (value != null) {
			if (value.trim().length() != 0) {
				try {
					if (Integer.parseInt(value) == 0)
						return false;
				} catch (Exception ex) {
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		if (isCorrect())
			return dateFormatter.format(this);
		return null;
	}

	public String toString(String DateFormat) {
		if (isCorrect()) {
			dateFormatter.applyPattern(DateFormat);
			String resultantDate = dateFormatter.format(this);
			dateFormatter.applyPattern(originalFormat);
			return resultantDate;
		}
		return null;
	}

	public SMYLDDate getDateAs(String DateFormat) {
		SMYLDDate targetDate = null;
		try {
			targetDate = new SMYLDDate(DateFormat, toString(DateFormat));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return targetDate;
	}

	public long getTimeSinceMidNight() {
		long resultedMilliSeconds = -1;
		GregorianCalendar currentCalendar = (GregorianCalendar) Calendar
				.getInstance();
		currentCalendar.setTime(this);
		resultedMilliSeconds = ((currentCalendar.get(Calendar.HOUR_OF_DAY) * 60) + currentCalendar
				.get(Calendar.MINUTE))
				* 60 + currentCalendar.get(Calendar.SECOND);
		return resultedMilliSeconds;
	}

	public String getFormat() {
		return originalFormat;
	}

	public static long getDayInMilliseconds() {
		return 24 * 60 * 60 * 1000;
	}

	public static long getDaysInMilliseconds(int days) {
		return days * getDayInMilliseconds();
	}

	// Date Formats going to be used
	public static final String FRM_yyyyMMdd = "yyyyMMdd";
	public static final String FRM_yyyyMM = "yyyyMM";
	public static final String FRM_yyMMddHHmmss = "yyMMddHHmmss";
	public static final String FRM_yyDDD = "yyDDD";
	public static final String FRM_yyDDD_HHmmss = "yyDDD-HHmmss";
	public static final String FRM_yyyyMMdd_SEP_2points = "yyyy:MM:dd";
	public static final String FRM_MMddyyyy_SEP_slash = "MM/dd/yyyy";
	public static final String FRM_HHmmss = "HHmmss";
	public static final String FRM_HHmmss_SEP_2points = "HH:mm:ss";
	public static final String FRM_yyMM = "yyMM";
	public static final String FRM_MMdd = "MMdd";
	public static final String FRM_MMddyy = "MMddyy";
	public static final String FRM_ddMMyy = "ddMMyy";
	public static final String FRM_yyMMdd = "yyMMdd";
	public static final String FRM_yyyy = "yyyy";
	public static final String FRM_DDD = "DDD";
	public static final String FRM_HH = "HH";
	public static final String FRM_mm = "mm";
	public static final String FRM_ss = "ss";
	public static final String FRM_MM = "MM";
	public static final String FRM_ddMMMyy_SEP_MINUS = "dd-MMM-yy";
	public static final String FRM_MMMyyyy_SEP_MINUS = "MMM-yyyy";
	public static final String FRM_MMM = "MMM";
	public static final String FRM_ddMMMyy_yyHHmmss_SEP_MINUS = "dd-MMM-yyHHmmss";
	public static final String FRM_yyyyMMddHHmmss_SEP_2points = "yyyyMMddHH:mm:ss";

	public String getTextValue() {
		return textValue;
	}
}
