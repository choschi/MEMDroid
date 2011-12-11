package com.choschi.memdroid.data;

public abstract class SoapObjectParser {
	
	protected int parseInteger (String input){
		return Integer.parseInt(input);
	}
	
	protected boolean parseBoolean(String input){
		return Boolean.parseBoolean(input); 
	}
}
