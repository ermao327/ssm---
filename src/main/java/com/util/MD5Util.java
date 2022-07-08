package com.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//需要导入的包
import java.util.Base64;
import java.util.Base64.Encoder;

public class MD5Util {

	public static byte[] md5(String s){
		try {
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] b = md.digest(s.getBytes());
//			BASE64Encoder encoder = new BASE64Encoder();
			Encoder encoder = Base64.getEncoder();
			return encoder.encode(b);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
