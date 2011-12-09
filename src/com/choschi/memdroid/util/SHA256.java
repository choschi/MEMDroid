package com.choschi.memdroid.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public  class SHA256 {
	private MessageDigest messageDigest;
	private byte[] digest;
    private String algorithm = "SHA-256";
	
	public SHA256() {
		try {
			messageDigest = MessageDigest.getInstance(algorithm);
		} catch(NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		}
	}
			
  	public String toHexString(byte b){
   		int value = (b & 0x7F) + (b < 0 ? 128 : 0);
	   
    		String ret = (value < 16 ? "0" : "");
    		ret += Integer.toHexString(value).toLowerCase();
	    
		return ret;
  	}
	  
	public String do_checksum(String data){

		StringBuffer strbuf = new StringBuffer();
		
      		messageDigest.update(data.getBytes(), 0, data.length());      
      		digest = messageDigest.digest();      
    
      		for (int i = 0; i < digest.length; i++) {
      			strbuf.append(toHexString(digest[i]));
		}
		   
		return strbuf.toString();      
 	}
	
	public static String getHash(String input){

	  	SHA256 messageDigest = new SHA256();	 			
	    return messageDigest.do_checksum(input);
   }
}

