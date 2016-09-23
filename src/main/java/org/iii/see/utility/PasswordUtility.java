package org.iii.see.utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordUtility {
	
	public static String generate() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(80, random).toString(32);		
	}
	
	public static String md5(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");		
		byte[] md5Bytes = md.digest(password.getBytes());
		return bytesToHex(md5Bytes, 0, md5Bytes.length);
	}
	
	public static String bytesToHex(byte[] bytes, int offset, int count) {
		char[] hexArray = "0123456789ABCDEF".toCharArray();
	    char[] hexChars = new char[count * 2];
	    for ( int j = 0; j < count; j++ ) {
	        int v = bytes[j+offset] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}		
}
