package com.smyld.net.url;

/**
 * This interface contains the constants used for the communication between the
 * servlet and the applet
 */
public interface ProtocolConsts {
	// Constants for protocol header information
	public String REQ_INIT = "INIT_REQUEST";
	public String RESP_INIT = "INIT_RESPONSE";

	public String REQ_REGISTER = "CALLBACK_REGISTER_REQ";
	public String RESP_REGSITER = "CALLBACK_REGISTER_RESP";

	public String REQ_PROCESS_INFO = "PROCESS_INFO_REQ";
	public String RESP_PROCESS_INFO = "PROCESS_INFO_RESP";

	public String REQ_USER_VERIFY = "USER_VERIFY_REQUEST";
	public String RESP_USER_VERIFY = "USER_VERIFY_RESPONSE";

	public String REQ_RUN_PROCESS = "RUN_PROCESS_REQ";
	public String RESP_RUN_PROCESS = "RUN_PROCESS_RESP";

	public int PROCESS_SERVER_PORT = 8085;

}
