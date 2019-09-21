package com.smyld.sys;

import java.util.HashMap;

import com.smyld.SMYLDObject;

public class SMYLDSystem extends SMYLDObject implements SystemConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static HashMap<String,String> OSMapper;

	public SMYLDSystem() {
	}

	public static OSGroup getOperatingSystemGroup() throws UnknownOS {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("windows")) {
			return OSGroup.Windows;
		}else if(os.contains("mac")){
				return OSGroup.MAC;
		}
		return OSGroup.Linux;
	}

	public static int getOperatingSystem() throws UnknownOS {
		String os = System.getProperty("os.name").toLowerCase();

		initOSMapper();
		String resultantOS = (String) OSMapper.get(os);
		if (resultantOS != null)
			return Integer.parseInt(resultantOS);
		throw new UnknownOS();
	}

	private static void initOSMapper() {
		OSMapper = new HashMap<String,String>();
		OSMapper.put(OS_NAME_AIX.toLowerCase(), Integer.toString(OS_AIX));
		OSMapper.put(OS_NAME_Digital_Unix.toLowerCase(), Integer
				.toString(OS_DIGITAL_UNIX));
		OSMapper.put(OS_NAME_FreeBSD.toLowerCase(), Integer
				.toString(OS_FREE_BSD));
		OSMapper.put(OS_NAME_HP_UX.toLowerCase(), Integer.toString(OS_HP_UX));
		OSMapper.put(OS_NAME_Irix.toLowerCase(), Integer.toString(OS_IRIX));
		OSMapper.put(OS_NAME_Linux.toLowerCase(), Integer.toString(OS_LINUX));
		OSMapper.put(OS_NAME_Mac_OS.toLowerCase(), Integer.toString(OS_MAC_OS));
		OSMapper.put(OS_NAME_MPE_iX.toLowerCase(), Integer.toString(OS_MPE_IX));
		OSMapper.put(OS_NAME_Netware_4_11.toLowerCase(), Integer
				.toString(OS_NETWARE_4_11));
		OSMapper.put(OS_NAME_OS_2.toLowerCase(), Integer.toString(OS_OS_2));
		OSMapper.put(OS_NAME_Solaris.toLowerCase(), Integer
				.toString(OS_SOLARIES));
		OSMapper.put(OS_NAME_Windows_2000.toLowerCase(), Integer
				.toString(OS_WINDOWS_2000));
		OSMapper.put(OS_NAME_Windows_95.toLowerCase(), Integer
				.toString(OS_WINDOWS_95));
		OSMapper.put(OS_NAME_Windows_98.toLowerCase(), Integer
				.toString(OS_WINDOWS_98));
		OSMapper.put(OS_NAME_Windows_NT.toLowerCase(), Integer
				.toString(OS_WINDOWS_NT));
		OSMapper.put(OS_NAME_Windows_XP.toLowerCase(), Integer
				.toString(OS_WINDOWS_XP));
		OSMapper.put(OS_NAME_Windows_7.toLowerCase(), Integer
				.toString(OS_WINDOWS_7));

		OSMapper.put(OS_NAME_Windows_10.toLowerCase(), Integer
				.toString(OS_WINDOWS_10));


	}

}
