package org.smyld.net;

import org.smyld.SMYLDObject;

public class NetCommand extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String command;
	String answer;
	String description;

	public NetCommand(String Command, String Description) {
		command = Command;
		description = Description;
	}

	public NetCommand(String Command, String Answer, String Description) {
		this(Command, Description);
		answer = Answer;
	}

	public static String extractCommand(String incomingText) {
		int pracetIndex = incomingText.indexOf("(");
		if (pracetIndex == -1)
			pracetIndex = incomingText.length();
		return incomingText.substring(0, pracetIndex);
	}

	public static String getParameters(String message) {
		String parameters = null;
		int paramStart = message.indexOf("(");
		int paramEnd = message.indexOf(")");
		if ((paramStart != -1) && (paramEnd != -1)) {
			parameters = message.substring(paramStart + 1, paramEnd);
		}
		return parameters;
	}

	public static boolean containsMultiParameters(String incomingText) {
		return (incomingText.indexOf(",") != -1);
	}

	public String getCommand() {
		return command;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String newAnswer) {
		answer = newAnswer;
	}

	public String getDescription() {
		return description;
	}

}
