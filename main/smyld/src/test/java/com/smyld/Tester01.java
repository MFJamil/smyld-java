package com.smyld;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.security.MessageDigest;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.smyld.io.FileNavigationListener;
import com.smyld.io.FilesNavigator;
import com.smyld.io.SMYLDFileOutputStream;
import com.smyld.run.RunProcessListener;
import com.smyld.run.SMYLDRunProcess;
import com.smyld.util.SMYLDJavaTools;

public class Tester01 {
	String curVersion;
	SMYLDFileOutputStream res;
	String error;
	String response;
	SMYLDFileOutputStream fout;
	int navigateFunc;
	String[] args;
	KeyGenerator keyGen;
	SecretKey secKey;
	SecretKey secretKey;
	// Creating the Cipher
	Cipher desCipher;

	String algorithm = "DESede";
	String mode = "CFB";
	// String mode = "ECB";
	String padding = "NoPadding";// "PKCS5Padding";
	// String padding = "PKCS5Padding";
	String testKeyText = "qw21/RTE321bv@oPLKjwwq__h23109IKlsadVXYcfdert543aaqwlkmn7621II*a-qq2221333ewkkjhdisllmfjjsdfkier443$%772FsPiofrgewsnjdmmaayyweswwdpLMBNixxhsnwz33981723007ikjsdfhgaiuwerzsdfkljhpooqiweuhslkfjhaoiuer46234782346&&�/!$!)(!$/&IKFXyaSSND13iFDHvcb/nsdjwdfhwwruhwroz";
	String testedText = "bw3data";
	// byte[] IV =
	// {(byte)0x1,(byte)0x2,(byte)0x3,(byte)0x4,(byte)0x5,(byte)0x5,(byte)0x5,(byte)0x5};
	String IVText = "zQ_1SdpO";
	byte[] IV = IVText.getBytes();
	boolean exceptionHappened = false;

	public Tester01(String[] comArgs) {
		args = comArgs;
		if ((args != null) && (args.length > 0)) {
			processLineCommand();
		} else {
			startTest();
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Tester01(args);
	}

	private void processLineCommand() {
		try {
			String operation = args[0];
			String text = args[1];
			if (operation.equals(OP_ENC)) {
				encrypt(text);
			} else if (operation.equals(OP_DEC)) {
				decrypt(text);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void startTest() {
		try {
			// testSecurity();
			// navigate();
			// readJDKs();
			startSecurityTest();
			// testSHA();
			// decrypt("l?���}o");

			// System.out.println(encrypt(testedText));
			// String dec = decrypt("��쑓z");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void testSHA() throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");

		String name = "enigma";

		digest.update(name.getBytes());
		byte[] firstPass = digest.digest();
		digest.reset();
		name = "enigma";
		digest.update(name.getBytes("ASCII"));
		byte[] secondPass = digest.digest();
		System.out.println(MessageDigest.isEqual(firstPass, secondPass));

	}
	@SuppressWarnings("unused")
	private void testDES() throws Exception {
		secKey = new SecretKeySpec("MyOnlyKe".getBytes(), "DESede");
		desCipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		desCipher.init(Cipher.ENCRYPT_MODE, secKey);
	}

	private void startSecurityTest() throws Exception {
		res = new SMYLDFileOutputStream(RESULTS_FILE, true);
		res.writeln("======================================================");
		res.writeln(new Date().toString());
		res.writeln("Algorithm  :" + algorithm);
		res.writeln("Mode       :" + mode);
		res.writeln("Padding    :" + padding);
		res.writeln("Key        :" + testKeyText);
		res.writeln("IV         : ASCII bytes of (" + IVText + ")");

		res.writeln("======================================================");
		//String curOp = OP_DEC;
		// String testText = "bw3d";
		// String testText = "#Xk��";
		/*
		 * for (int i = 1; i < 15; i++) {
		 * 
		 * String encrpPass = execJDKCommand(OP_ENC,testedText,i); if
		 * (exceptionHappened) res.writeln("Exception : " + encrpPass); else
		 * res.writeln("JDK - No. ("+ i +") encryption for text ("+ testedText
		 * +") resulted in - "+ encrpPass); if
		 * ((!exceptionHappened)&&(encrpPass!=null)){ String response =
		 * execJDKCommand(OP_DEC,encrpPass,-1); if (exceptionHappened)
		 * res.writeln("Exception : " + response); } }
		 */
		String encrpPass = execJDKCommand(OP_ENC, testedText, 1);
		if (exceptionHappened) {
			res.writeln("Exception : " + encrpPass);
		} else {
			res.writeln("JDK - No. (" + 1 + ") encryption for text ("
					+ testedText + ") resulted in - " + encrpPass);
		}
		if ((!exceptionHappened) && (encrpPass != null)) {
			String response = execJDKCommand(OP_DEC, encrpPass, -1);
			if (exceptionHappened) {
				res.writeln("Exception : " + response);
			}
		}

	}

	private String execJDKCommand(String op, String text, int jdkSeq)
			throws Exception {

		LineNumberReader reader = new LineNumberReader(new FileReader(
				LOCAL_JDK_FILE));
		String curJDK = null;
		int jdkCounter = 1;
		while ((curJDK = reader.readLine()) != null) {
			curJDK = curJDK.substring(4);
			if ((jdkSeq != -1) && (jdkCounter == jdkSeq)) {
				String answer = executePrcCommand(curJDK + " -jar " + TESTER
						+ " " + op + " " + text);
				return answer;
			} else if (jdkSeq == -1) {
				String answer = executePrcCommand(curJDK + " -jar " + TESTER
						+ " " + op + " " + text);
				// System.out.println(response);
				res.writeln(jdkCounter + "- " + answer);

			}
			jdkCounter++;
			// System.out.println(response);

		}
		return null;
	}

	private void initSecurity() throws Exception {
		// Generating the key as mentioned in the documents

		// System.out.println("Test Key Length : " + testKeyText.length());
		DESedeKeySpec desKeySpec = new DESedeKeySpec(testKeyText.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
		secretKey = keyFactory.generateSecret(desKeySpec);

		// secKey = new SecretKeySpec(keyValue.getBytes(),algorithm);
		// secKey = new SecretKeySpec("whelloth".getBytes(),algorithm);
		// Creating the key
		// keyGen = KeyGenerator.getInstance("DES");
		// secKey = keyGen.generateKey();
		// Creating the Cipher
		desCipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);

		// desCipher = Cipher.getInstance("SHA-1/ECB");

	}

	private String encrypt(String text) throws Exception {
		initSecurity();

		doInitCipher(Cipher.ENCRYPT_MODE);

		byte[] data = text.getBytes();
		byte[] enData = desCipher.doFinal(data);
		String result = new String(enData);
		// System.out.println(result);
		//BASE64Encoder bEnc = new BASE64Encoder();
		//System.out.println(bEnc.encode(result.getBytes()));
		//return bEnc.encode(result.getBytes());
		return null;
		// return result;
	}

	private void doInitCipher(int ciMode) throws Exception {

		if (!mode.equals("ECB")) {
			IvParameterSpec pSpec = new IvParameterSpec(IV);
			desCipher.init(ciMode, secretKey, pSpec);
		} else {
			desCipher.init(ciMode, secretKey);
		}

	}

	private String decrypt(String text) throws Exception {
		/*BASE64Decoder bDec = new BASE64Decoder();
		initSecurity();
		doInitCipher(Cipher.DECRYPT_MODE);
		byte[] deData = desCipher.doFinal(bDec.decodeBuffer(text));
		String result = new String(deData);
		System.out.println(result);
		return result;
		*/
		 return null;

	}
	@SuppressWarnings("unused")
	private void testSecurity() throws Exception {
		desCipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] data = "Trying to encrypt".getBytes();
		System.out.println(new String(data));
		byte[] enData = desCipher.doFinal(data);
		System.out.println(new String(enData));
		desCipher.init(Cipher.DECRYPT_MODE, secKey);
		byte[] deData = desCipher.doFinal(enData);
		System.out.println(new String(deData));
	}
	@SuppressWarnings("unused")
	private void readJDKs() throws Exception {
		fout = new SMYLDFileOutputStream(LOCAL_JDK_FILE);
		navigateFunc = 1;
		navigate();
	}

	private void doReadSingleJDK(File jdkFile) throws Exception {
		fout.writeln(jdkFile.getPath());
	}

	private void navigate() throws Exception {
		FilesNavigator navigator = new FilesNavigator();
		navigator.addNavigationListener(new FileNavigationListener() {
			public void fileFound(File foundFile) {
				try {
					if (navigateFunc == 1) {
						doReadSingleJDK(foundFile);
					} else {
						processJavaCheck(foundFile);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		navigator.navigate(new File("c:/"), new FileFilter() {
			public boolean accept(File testFile) {
				return (testFile.getName().equals("java.exe"));
			}
		});
	}

	private void processJavaCheck(File javaExe) throws Exception {
		error = null;
		curVersion = null;
		String comVer = javaExe.getPath() + " -version";
		SMYLDRunProcess rp = new SMYLDJavaTools().executeCommand(comVer, true);
		rp.addProcessListener(new RunProcessListener() {
			public void onResponse(String incomingResponse) {
				curVersion = incomingResponse;
			}

			public void onError(String incomingError) {
				error = incomingError;
			}

		});
		if (error != null) {
			System.out.println("Err: " + error);
		} else {
			System.out.println("Testing JDK version " + curVersion + " On : ("
					+ javaExe.getPath() + ")");
		}
	}

	private String executePrcCommand(String command) throws Exception {
		String errorText = null;
		System.out.println("Command :" + command);
		Process prc = Runtime.getRuntime().exec(command);
		prc.waitFor();
		// Thread.sleep(2000);
		String result = readStream(prc.getInputStream());
		if (result == null) {
			errorText = readStream(prc.getErrorStream());
		}
		if (errorText != null) {
			exceptionHappened = true;
			result = errorText;
		} else {
			exceptionHappened = false;
		}
		return result;
	}


	private String readStream(InputStream inp) throws Exception {
		byte[] data = new byte[200];

		int resSize = inp.read(data);
		if (resSize > 0) {
			byte[] fdata = new byte[resSize];
			System.arraycopy(data, 0, fdata, 0, resSize);
			return new String(fdata);
		}
		return null;

	}
	@SuppressWarnings("unused")
	private String executeCommand(String command) throws Exception {

		SMYLDRunProcess rp = new SMYLDJavaTools().executeCommand(command, true);
		rp.addProcessListener(new RunProcessListener() {
			public void onResponse(String incomingResponse) {
				response = incomingResponse;
			}

			public void onError(String incomingError) {
				error = incomingError;
			}
		});
		if (response != null) {
			return response;
		}
		if (error != null) {
			return error;
		}
		return null;
	}

	public static final String LOCAL_JDK_FILE = "LocalJDK.txt";
	public static final String RESULTS_FILE = "Results.txt";
	public static final String OP_DEC = "decrypt";
	public static final String OP_ENC = "encrypt";
	public static final String TESTER = "D:/Projects/sharedProjects/SMYLD/deploy/tester.jar";

}
