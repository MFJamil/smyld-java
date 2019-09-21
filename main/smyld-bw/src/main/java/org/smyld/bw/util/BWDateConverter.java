package org.smyld.bw.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.smyld.util.DateConverter;
import org.smyld.util.SMYLDDate;

public class BWDateConverter extends DateConverter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SMYLDDate postingDate;
	int acceptedDiffinDays;
	int postingYear;
	long acceptedDiffinMS, negativeDiff;
	GregorianCalendar calendar = new GregorianCalendar();

	public BWDateConverter() {
		super();
		setInterval(Date_Diff);
	}

	public BWDateConverter(String postingDateYear) {
		super(postingDateYear);
		setInterval(Date_Diff);
	}

	public BWDateConverter(String postingDateYear, String targetFormat) {
		super(postingDateYear, targetFormat);
		setInterval(Date_Diff);

	}

	public BWDateConverter(SMYLDDate PostingDate) {
		this(PostingDate, SMYLDDate.FRM_yyyyMMdd, Date_Diff);
	}

	public BWDateConverter(SMYLDDate PostingDate, String TargetFormat) {
		this(PostingDate, TargetFormat, Date_Diff);
	}

	public BWDateConverter(SMYLDDate PostingDate, String TargetFormat,
			int DateDifferenceInterval) {
		setPostingDate(PostingDate);
		setTargetFormat(TargetFormat);
		setInterval(DateDifferenceInterval);
	}

	/*
	 * public SMYLDDate convertOnPostingDate(SMYLDDate currentDate){
	 * convertDate(currentDate); long diff = targetDate.getTime() -
	 * postingDate.getTime(); //System.out.println("The difference is : " +
	 * diff); int changeYear = postingYear; if (diff > acceptedDiffinMS)
	 * changeYear--; else if (diff < negativeDiff) changeYear++; if
	 * (changeYear!= postingYear)
	 * targetDate.setValue(Integer.toString(changeYear) +
	 * currentDate.toString(SMYLDDate.FRM_MMdd)); return targetDate; }
	 */
	public SMYLDDate convertOnPostingDate(SMYLDDate currentDate) {
		convertDate(currentDate);
		long diff = targetDate.getTime() - postingDate.getTime();
		// System.out.println("The difference is : " + diff);
		//int changeYear = postingYear;
		int changeValue = 0;
		if (diff > acceptedDiffinMS)
			changeValue = -1;
		else if (diff < negativeDiff)
			changeValue = 1;
		if (changeValue == -1)
			changeYear(changeValue);
		// targetDate.setValue(Integer.toString(changeYear) +
		// currentDate.toString(SMYLDDate.FRM_MMdd));
		return targetDate;
	}

	private void changeYear(int value) {
		calendar.setTime(targetDate);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + value);
		targetDate.setTime(calendar.getTimeInMillis());
	}

	public String convertOnPostingDatetoText(SMYLDDate currentDate) {
		convertOnPostingDate(currentDate);
		// System.out.println("currentDate :" + currentDate.toString() + ",
		// while posting date :" + postingDate.toString() );
		if (targetDate != null) {
			return targetDate.toString();
		}
		return null;
	}

	public void setInterval(int days) {
		acceptedDiffinDays = days;
		acceptedDiffinMS = SMYLDDate.getDaysInMilliseconds(acceptedDiffinDays);
		negativeDiff = SMYLDDate.getDaysInMilliseconds(acceptedDiffinDays - 365);
	}

	public void setPostingDate(SMYLDDate newPostingDate) {
		postingDate = newPostingDate;
		setActiveYear(postingDate.toString(SMYLDDate.FRM_yyyy));
		postingYear = Integer.parseInt(referenceYear);

	}

	private static final int Date_Diff = 28;
}
