package com.springboot.msbasico.util;

import java.math.BigInteger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HMAC {

	@Autowired
	static ConfigProperties conf;
	
	static public String calcHmacSha256(byte[] message) {
		
	    byte[] hmacSha256 = null;
	    try {
	    	
	    	byte[] key = conf.getConfigValue("hash.key").getBytes("UTF-8");
	    	Mac mac = Mac.getInstance("HmacSHA256");
	    	SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA256");
	    	mac.init(secretKeySpec);
	    	hmacSha256 = mac.doFinal(message);
	    } catch (Exception e) {
	      throw new RuntimeException("Failed to calculate hmac-sha256", e);
	    }
	    return String.format("%064x", new BigInteger(1, hmacSha256));
	  }
}
