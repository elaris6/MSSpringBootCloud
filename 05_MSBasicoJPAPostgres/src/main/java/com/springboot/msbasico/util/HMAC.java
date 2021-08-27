package com.springboot.msbasico.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


public class HMAC {
	
	public String calcHmacSha256(String keyString, String message) {
		
	    byte[] hmacSha256 = null;
	    try {
	    	
	    	//byte[] key = "191222364559439dcc664f27.70714536".getBytes("UTF-8");
	    	byte[] key = keyString.getBytes("UTF-8");
	    	byte[] inputString = message.getBytes("UTF-8");
	    	Mac mac = Mac.getInstance("HmacSHA256");
	    	SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA256");
	    	mac.init(secretKeySpec);
	    	hmacSha256 = mac.doFinal(inputString);
	    }catch (UnsupportedEncodingException e) {
		      e.printStackTrace();
	    } catch (Exception e) {
	      throw new RuntimeException("Failed to calculate hmac-sha256", e);
	    }
	    return String.format("%064x", new BigInteger(1, hmacSha256));
	  }
}
