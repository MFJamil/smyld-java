package org.smyld;

public class SMYLDSystem extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static boolean trace_enable = true;
	private static boolean debug_enable = true;

	public SMYLDSystem() {
	}

	public static void enableTrace(boolean traceOn) {
		trace_enable = traceOn;
	}

	public static void enableDebug(boolean debugOn) {
		debug_enable = debugOn;
	}

	public static void printTrace(String message) {
		if (trace_enable)
			printLn(message);
	}

	public static void printDebug(String message) {
		if (debug_enable)
			printLn(message);
	}

	public static void printLn(String message) {
		System.out.println(message);
	}

}
