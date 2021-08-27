package com.springboot.msbasico.util;

public class testHMAC {

	static HMAC hash = new HMAC();

	public static void main(String[] args) {

		
		String hmacSha256 = hash.calcHmacSha256("191222364559439dcc664f27.70714536","053406292Q");
	    System.out.println(hmacSha256);
	}
}
