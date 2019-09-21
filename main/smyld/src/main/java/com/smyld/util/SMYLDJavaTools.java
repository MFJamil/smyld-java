package com.smyld.util;

import com.smyld.SMYLDObject;
import com.smyld.run.SMYLDRunProcess;
import com.smyld.run.RunProcessListener;
import com.smyld.security.SMYLDKey;

public class SMYLDJavaTools extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SMYLDRunProcess curProcess;

	public SMYLDJavaTools() {
	}

	public void compile(String sourcePath, String targetClass,
			String targetPath, String libraryRef) throws Exception {
		if (targetClass == null)
			throw new Exception("Class name to compile is not specified");
		sourcePath = sourcePath != null ? " -sourcepath " + sourcePath : "";
		targetPath = targetPath != null ? " -d " + targetPath : "";
		String classPath = libraryRef != null ? " -classpath " + libraryRef
				: "";
		String command = "javac " + sourcePath + classPath + targetPath
				+ targetClass;
		System.out.println("Executing Command : " + command + "\n\n\n");
		executeCommand(command, true);
	}

	public void createKey(SMYLDKey newKey) throws Exception {
		createKey(newKey.getName(), newKey.getPassword(), newKey
				.getDistinguishName(), newKey.getKeyStore());
	}

	public void createKey(String keyName, String keyPass,
			String distinguishName, String keyStore) throws Exception {
		if (keyName == null)
			throw new Exception("Key name is mandatory ...");
		StringBuffer command = new StringBuffer("keytool -genkey -alias ");
		command.append(keyName);
		final String curPass = keyPass;
		if (keyPass != null)
			command.append(" -keypass " + keyPass);
		if (keyStore != null)
			command.append(" -keystore " + keyStore);
		if (distinguishName != null)
			command.append(" -dname \"" + distinguishName + "\"");
		curProcess = executeCommand(command.toString(),
				new RunProcessListener() {
					public void onError(String incError) {
						// System.out.println("Incoming Error : " + incError);
						if (incError.startsWith("Enter keystore password")) {
							try {
								curProcess.send(curPass);
								curProcess.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}

					public void onResponse(String response) {
						System.out.println("Incoming Reponse : " + response);
					}
				}, false);

	}

	public boolean signJarFile(String jarPath, SMYLDKey key) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("jarsigner");
		if (key.getKeyStore() != null)
			buffer.append(" -keystore " + key.getKeyStore());
		if (key.getPassword() != null)
			buffer.append(" -storepass " + key.getPassword());
		buffer.append(" " + jarPath);
		buffer.append(" " + key.getName());
		try {
			SMYLDRunProcess result = executeCommand(buffer.toString(), true);
			result.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public SMYLDRunProcess executeCommand(String command, boolean wait)
			throws Exception {
		System.out.println("Executing command : " + command);
		Runtime rt = Runtime.getRuntime();
		Process result = rt.exec(command);
		SMYLDRunProcess runningProcess = new SMYLDRunProcess(result);
		if (wait)
			result.waitFor();
		return runningProcess;
	}

	public SMYLDRunProcess executeCommand(String command,
			RunProcessListener prcListener, boolean wait) throws Exception {
		System.out.println("Executing command : " + command);
		Runtime rt = Runtime.getRuntime();
		Process result = rt.exec(command);
		SMYLDRunProcess runningProcess = new SMYLDRunProcess(result, prcListener);
		if (wait)
			result.waitFor();
		return runningProcess;
	}

}
