package com.smyld.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class provides utilities to hash a password for storing in db and to
 * compare passwords stored in db against the plain text
 */
public class HashEncoder {
	public HashEncoder() {
	}

	/**
	 * Returns the hash value of the string using "SHA-1" algorithm
	 * 
	 * @param pwd
	 *            the string to be hashed
	 * @return the byte array containing the hash value of the password NOTE:
	 *         This method currently uses UTF-16 as charset to encode the
	 *         password, this might be supposed to change
	 */
	public byte[] encodePwd(String pwd) {
		// try{
		byte[] buf = pwd.getBytes();

		MessageDigest algorithm = null;
		try {
			algorithm = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		algorithm.reset();
		algorithm.update(buf);
		byte[] digest1 = algorithm.digest();
		return digest1;
		// } catch(UnsupportedEncodingException e){e.printStackTrace();}
		// return null;
	}

	/**
	 * Hashes the given plain text using "SHA-1 " and compares it to the given
	 * encoded value
	 * 
	 * @param encoded
	 *            the string representation of the encoded byte array (should be
	 *            UTF-16)
	 * @param the
	 *            plain text to be compared
	 * @return true both the values are same, else returns false NOTE: It is
	 *         supposed that these encoded bytes are in UTF-16 format. IF not
	 *         they are to be converted to UTF-16 before making a string out of
	 *         it.
	 */
	public boolean matchPwd(String encoded, String plain) {
		/*
		try {
			byte[] encBytes = new sun.misc.BASE64Decoder()
					.decodeBuffer(encoded);
			// byte[] encBytes = encoded.getBytes("ASCII");
			byte[] plainBytes = encodePwd(plain);

			return MessageDigest.isEqual(encBytes, plainBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		throw new RuntimeException("Sun's Base64 Decoder is no more exists in the JDK");
	}

	public static void testEncoding(String encStr)
			throws UnsupportedEncodingException {
		String encoding = encStr;
		byte[] b = new byte[256];
		for (int i = 0; i < 256; i++)
			b[i] = (byte) i;
		String x = new String(b, encoding);
		for (int i = 0; i < x.length(); i++) {
			if (x.charAt(i) != i)
				System.out.println(i + " -> " + (int) x.charAt(i));
		}

	}

	public static void main(String[] args) {
		// try{
		// testEncoding("UTf-8");
		HashEncoder enc = new HashEncoder();
		byte[] encoded = enc.encodePwd("enigma");
		// String sr = new String(encoded);

		// System.out.println(new String(sr.getBytes("UTF-16LE")));
		//String sr = new sun.misc.BASE64Encoder().encode(encoded);
		// System.out.println(sr);
		//System.out.println("Match returns:" + enc.matchPwd(sr, "enigma"));
		// } catch(UnsupportedEncodingException e){e.printStackTrace();}
	}

}
