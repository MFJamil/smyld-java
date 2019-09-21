package org.smyld.security;

import java.util.HashMap;

import org.smyld.text.TextTokenizer;

public class SMYLDIPFilter {
	HashMap<String,String> allowedIPs;
	String subNetMask;

	public SMYLDIPFilter(HashMap<String,String> AllowedIPs) {
		allowedIPs = AllowedIPs;
	}

	public SMYLDIPFilter(String[] AllowedIPs) {
		setAllowedIPs(AllowedIPs);
	}

	public SMYLDIPFilter(String[] AllowedIPs, String SubNetMask) {
		this(AllowedIPs);
		setSubNetMask(SubNetMask);
	}

	public boolean isAllowed(String IP) {
		if (!allowedIPs.containsKey(IP)) {
			// Checking for the internal subnet mask
			if (subNetMask != null) {
				return IP.startsWith(subNetMask);
			} else {
				return false;
			}
		}
		return true;
	}

	public String getSubNetMask() {
		return subNetMask;
	}

	public void setAllowedIPs(HashMap<String,String> newIPRange) {
		allowedIPs = newIPRange;
	}

	public void setAllowedIPs(String[] ipArray) {
		if (allowedIPs == null)
			allowedIPs = new HashMap<String,String>();
		else
			allowedIPs.clear();
		for (int i = 0; i < ipArray.length; i++)
			allowedIPs.put(ipArray[i], ipArray[i]);
	}

	public HashMap<String,String> getAllowedIPs() {
		return allowedIPs;
	}

	public void setSubNetMask(String newSubNetMask) {
		if (newSubNetMask != null) {
			newSubNetMask = newSubNetMask.toLowerCase().replace('x', '0');
			if (isValidIP(newSubNetMask)) {
				subNetMask = newSubNetMask.substring(0, newSubNetMask
						.lastIndexOf("."));
				// System.out.println("Sub Net Mask : " + subNetMask);
			}
		}
	}

	public static boolean isValidIP(String IPAddress) {
		if (IPAddress != null) {
			if (IPAddress.length() <= IP_VALID_WIDTH) {
				TextTokenizer stringTockens = new TextTokenizer(IPAddress, ".");
				String[] IPParts = stringTockens.parseTokens();
				if (IPParts.length == 4) {
					for (int i = 0; i < IPParts.length; i++) {
						try {
							if (Integer.parseInt(IPParts[i]) > 255)
								return false;
						} catch (Exception ex) {
							return false;
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	private static final int IP_VALID_WIDTH = 15;
}
