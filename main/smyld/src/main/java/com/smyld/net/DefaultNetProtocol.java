package com.smyld.net;

import java.util.HashMap;


import com.smyld.SMYLDObject;

public class DefaultNetProtocol extends SMYLDObject implements NetProtocol {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected HashMap<String,NetCommand> commands = new HashMap<String,NetCommand>();

	public DefaultNetProtocol() {
		init();
	}

	protected void init() {
		NetCommand helpCommand = new NetCommand(MSG_CMD_HELP, MSG_CMD_HELP_ANS,
				MSG_CMD_HELP_DESC);
		NetCommand closeCommand = new NetCommand(MSG_CMD_CLOSE,
				MSG_CMD_CLOSE_DESC);
		addCommand(helpCommand);
		addCommand(closeCommand);
	}

	protected void addCommand(NetCommand newCommand) {
		commands.put(newCommand.getCommand().toLowerCase(), newCommand);

	}

	public String getHelp() {
		StringBuffer helpText = new StringBuffer("Please Press ..."
				+ MSG_NEW_LINE);
		for (NetCommand curComm : commands.values()) {
			helpText.append("\t" + curComm.getCommand() + " : "
					+ curComm.getDescription() + MSG_NEW_LINE);
		}
		return helpText.toString();
	}

	public String getWelcomeMessage() {
		return MSG_WELCOME;
	}

	public String getConnectionNoOver() {
		return MSG_OVER_CONNECTION;
	}

	public void processMessage(ClientNode sourceNode, String incomingMessage) {
		if (incomingMessage.equals(MSG_NEW_LINE)) {
			sourceNode.sendText(MSG_NEW_LINE);
		} else if (incomingMessage.equals(MSG_CMD_HELP)) {
			sourceNode.sendText(getHelp());
		} else if (incomingMessage.equals(MSG_CMD_CLOSE)) {
			sourceNode.close();
		} else {
			String incomingCommand = getCommand(incomingMessage);
			if (commands.containsKey(incomingCommand)) {
				NetCommand curCommand = (NetCommand) commands
						.get(incomingCommand);
				sourceNode.sendText(curCommand.getAnswer());
			} else {
				sourceNode.sendText(MSG_CMD_UNKNOWN_ANS);
			}
		}
	}

	protected String getCommand(String incomingText) {
		return NetCommand.extractCommand(incomingText).toLowerCase();
	}

	public void answerToClient(String clientKey, String answerText,
			HashMap<String,ClientNode> clients) {
		ClientNode targetClient = null;
		if (clients.containsKey(clientKey))
			targetClient.sendText(answerText + MSG_NEW_LINE);
	}

	public static final String MSG_NEW_LINE = System
			.getProperty("line.separator");
	public static final String MSG_WELCOME = "Welcome to SMYLD network Layer...."
			+ MSG_NEW_LINE;
	public static final String MSG_OVER_CONNECTION = "Sorry maximum concurrent connectors reached...."
			+ MSG_NEW_LINE;
	public static final String MSG_CMD_UNKNOWN_ANS = "Your command is not recognized .. please press ? for help"
			+ MSG_NEW_LINE;

	// Commands
	public static final String MSG_CMD_HELP = "?";
	public static final String MSG_CMD_HELP_DESC = "for this help";
	public static final String MSG_CMD_HELP_ANS = "Please Press ..."
			+ MSG_NEW_LINE;

	public static final String MSG_CMD_CLOSE = "close";
	public static final String MSG_CMD_CLOSE_DESC = "for closing the console";

}
