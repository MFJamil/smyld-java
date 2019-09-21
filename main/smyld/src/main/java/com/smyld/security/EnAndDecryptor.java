package com.smyld.security;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EnAndDecryptor {
	/**
	 * WARNING: Don't change this key, unless all the encrypted data can't be
	 * decrypted back !!!!!
	 */
	@SuppressWarnings("unused")
	private static final String KEY = "MyKey123";
	@SuppressWarnings("unused")
	private static final String version = "-version";
	private SecretKeySpec keySpec;
	private Cipher ecipher;
	private Cipher dcipher;

	// Takes a 7-byte quantity and returns a valid 8-byte DES key.
	// The input and output bytes are big-endian, where the most significant
	// byte is in element 0.
	public static byte[] addParity(byte[] in) {
		byte[] result = new byte[8];
		// Keeps track of the bit position in the result
		int resultIx = 1;
		// Used to keep track of the number of 1 bits in each 7-bit chunk
		int bitCount = 0;
		// Process each of the 56 bits
		for (int i = 0; i < 56; i++) {
			// Get the bit at bit position i
			boolean bit = (in[6 - i / 8] & (1 << (i % 8))) > 0;
			// If set, set the corresponding bit in the result
			if (bit) {
				result[7 - resultIx / 8] |= (1 << (resultIx % 8)) & 0xFF;
				bitCount++;
			}
			// Set the parity bit after every 7 bits
			if ((i + 1) % 7 == 0) {
				if (bitCount % 2 == 0) {
					// Set low-order bit (parity bit) if bit count is even
					result[7 - resultIx / 8] |= 1;
				}
				resultIx++;
				bitCount = 0;
			}
			resultIx++;
		}
		return result;
	}

	// Get the 56-bit value
	byte[] raw = new byte[] { 0x01, 0x72, 0x43, 0x3E, 0x1C, 0x7A, 0x55 };
	// byte[] raw = "MyKey123".getBytes();
	byte[] keyBytes = addParity(raw);

	public EnAndDecryptor() {
		try {

			// keySpec = new SecretKeySpec(KEY.getBytes(),"DES");
			keySpec = new SecretKeySpec(keyBytes, "DES");
			// ecipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			// dcipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			ecipher = Cipher.getInstance("DES");
			dcipher = Cipher.getInstance("DES");
			ecipher.init(Cipher.ENCRYPT_MODE, keySpec);
			dcipher.init(Cipher.DECRYPT_MODE, keySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String encrypt(String str) {
		/*
		try {
			// Encode the string into bytes using utf-8
			byte[] utf8 = str.getBytes("UTF8");
			// Encrypt
			byte[] enc = ecipher.doFinal(utf8);
			// Encode bytes to base64 to get a string
			return new sun.misc.BASE64Encoder().encode(enc);
			// return new MyOwn64Encoder().encode(enc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		*/
		throw new RuntimeException("Sun's Base64 Encoder is no more exists in the JDK");
	}

	public String decrypt(String str) {
		/*
		try {
			// Decode base64 to get bytes
			// byte[] dec = new MyOwn64Decoder().decodeBuffer(str.trim());
			byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str.trim());
			// Decrypt
			byte[] utf8 = dcipher.doFinal(dec);
			// Decode using utf-8
			return new String(utf8, "UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		*/
		throw new RuntimeException("Sun's Base64 Decoder is no more exists in the JDK");
	}

	public void showNewConsoleUtil() throws Exception {
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

	/*
	 * public void test(){ String myPwd = "Password";
	 * 
	 * String encrypted = encrypt(myPwd); System.out.println("Encrypted pwd: "+
	 * encrypted); FileSystem.writeFile("d:/encrypted.txt",encrypted,false);
	 * String read = FileSystem.readStringFile("d:/encrypted.txt");
	 * System.out.println("String read from the file is:" + read); String
	 * decrypted = decrypt(read); System.out.println("Decrypted pwd: "+
	 * decrypted); }
	 */

	@SuppressWarnings("unused")
	private void showConsoleUtil() throws Exception {
		System.out
				.println("\n########### SMYLD Internal Cryptographic Utility ################\n");
		System.out.println("'exit' allows to quit the utility ");
		System.out.println("'E' allows encryption of a string");
		System.out.println("'D' allows decryption a string\n");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		do {
			System.out.println("Do you want to encrypt (E) or decrpyt (D) ? ");
			str = in.readLine();
			BufferedReader in2 = new BufferedReader(new InputStreamReader(
					System.in));
			if (str.equals("E")) {
				System.out.println("Please enter the string to be encrypted: ");
				String pwd = in2.readLine();
				System.out.println("Encrypted String: " + encrypt(pwd.trim()));
			} else if (str.equals("D")) {
				System.out.println("Please enter the encrypted string: ");
				String encrypted = in2.readLine();
				System.out.println("Decrypted String: "
						+ decrypt(encrypted.trim()));
			}
		} while (!str.equals("exit"));
	}

	public static void main(String[] args) {
		try {
			// if(args != null && (args[0].equalsIgnoreCase(version))){
			// System.out.println("\n####### SMYLD Software Group ######\n");
			// System.out.println("Application:\t BankWORKS Crypto
			// Utility\nVersion:\t 1.00.01");
			// System.out.println("Major Changes:\t IBM Crypto Library
			// Compatiblity");

			EnAndDecryptor crypto = new EnAndDecryptor();
			crypto.showNewConsoleUtil();
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
