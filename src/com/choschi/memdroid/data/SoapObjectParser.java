package com.choschi.memdroid.data;

public abstract class SoapObjectParser {
	
	protected int parseInteger (String input){
		try {
			return Integer.parseInt(input);
		}catch (Exception ex){
			return 0;
		}
	}
	
	protected boolean parseBoolean(String input){
		return Boolean.parseBoolean(input); 
	}
}
