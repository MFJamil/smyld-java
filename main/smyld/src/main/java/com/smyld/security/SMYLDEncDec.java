package com.smyld.security;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/*
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
*/

// com.smyld.security.SMYLDEncDec
public class SMYLDEncDec {
	// Variable settings for the ecryption/decryption that can be changed to
	// test
	// the various possible parameters
	String algorithm = "DESede";
	String mode = "CFB";
	String padding = "NoPadding";
	String testKeyText = "qw21/RTE321bv@oPLKjwwq__h23109IKlsadVXYcfdert543aaqwlkmn7621II*a-qq2221333ewkkjhdisllmfjjsdfkier443$%772FsPiofrgewsnjdmmaayyweswwdpLMBNixxhsnwz33981723007ikjsdfhgaiuwerzsdfkljhpooqiweuhslkfjhaoiuer46234782346&&�/!$!)(!$/&IKFXyaSSND13iFDHvcb/nsdjwdfhwwruhwroz";
	String IVText = "zQ_1SdpO";
	byte[] IV = IVText.getBytes();

	// Line command constants that can be used for firing the process
	public static final String OP_DEC = "decrypt";
	public static final String OP_ENC = "encrypt";

	// Class instances
	SecretKey secretKey;
	Cipher desCipher;

	public SMYLDEncDec() {
	}

	private void processLineCommand(String[] args) {
		try {
			String operation = args[0];
			String text = args[1];
			if (operation.equals(OP_ENC))
				encrypt(text);
			else if (operation.equals(OP_DEC))
				decrypt(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doInitCipher(int ciMode) throws Exception {
		if (!mode.equals("ECB")) {
			IvParameterSpec pSpec = new IvParameterSpec(IV);
			desCipher.init(ciMode, secretKey, pSpec);
		} else {
			desCipher.init(ciMode, secretKey);
		}

	}

	private void initSecurity(int ciMode) throws Exception {
		DESedeKeySpec desKeySpec = new DESedeKeySpec(testKeyText.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
		secretKey = keyFactory.generateSecret(desKeySpec);
		desCipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
		doInitCipher(ciMode);
	}

	public String encrypt(String text) throws Exception {
		initSecurity(Cipher.ENCRYPT_MODE);
		byte[] data = text.getBytes();
		byte[] enData = desCipher.doFinal(data);
		//BASE64Encoder bEnc = new BASE64Encoder();
		//String result = bEnc.encode(enData);
		// System.out.println(result);
		//return result;
		throw new Exception("Sun's Base64 Encoder is no more exists in the JDK");
	}

	public String decrypt(String text) throws Exception {
		initSecurity(Cipher.DECRYPT_MODE);
		//BASE64Decoder bDec = new BASE64Decoder();
		//byte[] deData = desCipher.doFinal(bDec.decodeBuffer(text));
		//String result = new String(deData);
		// System.out.println(result);
		//return result;
		throw new Exception("Sun's Base64 Decoder is no more exists in the JDK");
	}

	public static void main(String[] args) {
		if ((args != null) && (args.length > 0)) {
			SMYLDEncDec encDec = new SMYLDEncDec();
			encDec.processLineCommand(args);
		} else {
			try {
				new SMYLDEncDec().showConsoleUtil();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// The following code is taked from Praveen code because I did not have time
	// to write it myself
	public void showConsoleUtil() throws Exception {
		System.out
				.println("\n########### SMYLD Internal Cryptographic Utility ################\n");
		System.out.println("'exit' allows to quit the utility ");
		System.out.println("'E' allows encryption of a string");
		// System.out.println("'D' allows decryption a string");
		System.out
				.println("'C' allows comparison a between an encrypted and plain string\n");

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		do {
			System.out.println("Do you want to encrypt (E), or compare (C)? ");
			str = in.readLine();
			BufferedReader in2 = new BufferedReader(new InputStreamReader(
					System.in));
			if (str.equals("E") || str.equals("e")) {
				System.out.println("Please enter the string to be encrypted: ");
				String pwd = in2.readLine();
				System.out.println("Result is: " + encrypt(pwd.trim()));
			} /*
				 * else if (str.equals("D") || str.equals("d")){
				 * System.out.println("Please enter the encrypted string: ");
				 * String encrypted = in2.readLine(); System.out.println("Result
				 * is : "+ decrypt(encrypted.trim())); }
				 */else if (str.equals("C") || str.equals("c")) {
				System.out.println("Please enter the encrypted string: ");
				String encrypted = in2.readLine().trim();
				System.out.println("Please enter the plain string: ");
				BufferedReader in3 = new BufferedReader(new InputStreamReader(
						System.in));
				String plain = in3.readLine().trim();
				String compared = encrypt(plain);
				if (compared.equals(encrypted))
					System.out.println("Result is: Value is matched");
				else if (!compared.equals(encrypted))
					System.out.println("Result is: Value is not matched");
			}

		} while (!str.equals("exit"));
	}

}
