package com.smyld.util;

import java.util.Calendar;

import com.smyld.SMYLDObject;

public class DateConverter extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected StringBuffer buffer;
	protected String referenceYear;
	protected String targetFormat;
	protected SMYLDDate targetDate;

	public DateConverter() {
		buffer = new StringBuffer(12);
		referenceYear = Integer.toString(Calendar.getInstance().get(
				Calendar.YEAR));
		targetDate = new SMYLDDate(SMYLDDate.FRM_yyyyMMdd);
	}

	public DateConverter(String activeYear) {
		this();
		referenceYear = activeYear;
	}

	public DateConverter(String activeYear, String TargetFormat) {
		this(activeYear);
		targetFormat = TargetFormat;
	}

	public void setTargetFormat(String newTargetFormat) {
		if (!newTargetFormat.equals(targetFormat)) {
			targetFormat = newTargetFormat;
			targetDate.setFormat(targetFormat);
		}
	}

	public void setActiveYear(String newActiveYear) {
		referenceYear = newActiveYear;
	}

	public SMYLDDate convertDate(SMYLDDate dateForConvert) {
		buffer.setLength(0);
		if (dateForConvert.getFormat().equals(SMYLDDate.FRM_MMdd)) {
			buffer.append(referenceYear);
			buffer.append(dateForConvert.toString());
			targetDate.setValue(buffer.toString());
		} else {
			targetDate.setTime(dateForConvert.getTime());
		}
		return targetDate;
	}

	public String convertDateToText(SMYLDDate dateForConvert) {
		convertDate(dateForConvert);
		if (targetDate != null)
			return targetDate.toString();
		return null;
	}

}
