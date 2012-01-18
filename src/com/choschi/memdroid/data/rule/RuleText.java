package com.choschi.memdroid.data.rule;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormSoapObjectParser;

/**
 * 
 * RuleText, not commented on purpose
 * 
 * @author Christoph Isch
 *
 */

public class RuleText extends FormSoapObjectParser {

	private String language;
	private String message;
	
	public RuleText(SoapObject input) {
		super(input);
	}

	@Override
	protected void saveProperty(String property, Name name) {
		switch (name){
			case LANGUAGE:
				language = property;
			break;
			case MESSAGE:
				message = property;
			break;
		}
	}

	@Override
	protected void saveObject(SoapObject property, Name name) {
		
	}
	
	public String getMessage(){
		return message;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

}
