package org.smyld.util;

import java.util.Date;
import java.util.GregorianCalendar;

public class JulianDate extends GregorianCalendar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JulianDate() {
		this.setGregorianChange(new Date(Long.MAX_VALUE));
		this.setTime(new Date());
	}

}
