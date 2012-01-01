package com.choschi.memdroid.data;

/**
 * 
 * @author Christoph Isch
 *
 * implements basic parsing abilites to be able to parse the soap objects easier
 *
 */

public abstract class SoapObjectParser {
	
	/**
	 * 
	 * if instead of a parseable string something else is provided the method returns 0
	 * 
	 * @param input
	 * @return an integer representation of the input
	 */
	
	protected int parseInteger (String input){
		try {
			return Integer.parseInt(input);
		}catch (Exception ex){
			return 0;
		}
	}
	
	/**
	 * 
	 * @param input
	 * @return a boolean representation of the input string
	 */
	
	protected boolean parseBoolean(String input){
		return Boolean.parseBoolean(input); 
	}
}
