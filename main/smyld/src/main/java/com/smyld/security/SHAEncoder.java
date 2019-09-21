/*
 * SHAEncoder.java
 *
 * Created on November 23, 2005, 9:18 AM
 *
 */

package com.smyld.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author johanf
 */
public class SHAEncoder {

	public static String encode(String s) {
		int iValue;
		MessageDigest md;
		String sEncrypted;
		String sTemp;

		// initialise variables
		md = null;
		sEncrypted = "";
		sTemp = "";
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		}
		try {
			md.update(s.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
		byte abOriginal[] = md.digest();
		for (int i = 0; i < abOriginal.length; i++) {
			iValue = abOriginal[i] & 0xff;
			sTemp = Integer.toHexString(iValue);
			if (sTemp.length() == 1)
				sTemp = "0" + sTemp;
			sEncrypted += sTemp;
		}
		return sEncrypted;
	}

}
