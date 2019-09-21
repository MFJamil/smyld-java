package com.smyld.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import com.smyld.db.oracle.SMYLDOracleConnection;

public class Tester {
	static Connection oracle;

	public Tester() {
	}
	
	/*
	public static void initCon() throws Exception {
		oracle = new SMYLDOracleConnection(
				"d:/cvs_home/Resources/oracledb.properties").getConnection();
	}
	*/

	public static void testPwd() {
		// try{
		String pwd = getPwd();
		System.out.println("Password is:" + pwd);
		HashEncoder enc = new HashEncoder();
		// byte[] encbytes = enc.encodePwd("enigma");
		// System.out.println("Password is:"+ encbytes.toString());
		if (enc.matchPwd(pwd, "enigma"))
			System.out.println("Everything ok");
		else
			System.out.println("Doesn't match");
		// }catch(UnsupportedEncodingException e){e.printStackTrace();}
	}

	public static String getPwd() {
		try {
			String sql = "Select * from SYS_USER_INFORMATION where USERID='900005'";
			PreparedStatement ps = oracle.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String pwd = rs.getString("PASS_WORD");
			return pwd;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			//initCon();
			testPwd();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
