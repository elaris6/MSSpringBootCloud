package com.springboot.msbasico.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;


public class testHMAC {
	
	public static void main(String[] args) {

		try {
			  String hmacSha256 = HMAC.calcHmacSha256("053406292Q".getBytes("UTF-8"));
		      System.out.println(hmacSha256);
		    } catch (UnsupportedEncodingException e) {
		      e.printStackTrace();
		    }
	}
}
