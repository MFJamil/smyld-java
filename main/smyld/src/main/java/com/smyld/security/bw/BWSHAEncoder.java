/*
 * BWSHAEncoder.java
 *
 * Created on December 9, 2005, 9:50 AM
 *
 */

package com.smyld.security.bw;

import com.smyld.security.SHAEncoder;

/**
 * 
 * @author johanf
 */
public class BWSHAEncoder extends SHAEncoder {

	public static String encode(String s) {
		return SHAEncoder.encode(s).replaceAll("0", "");
	}

}
