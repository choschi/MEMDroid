package com.choschi.memdroid.data.rule;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormSoapObjectParser;
import com.choschi.memdroid.webservice.requests.ServerGetFormDefinitionResponse.Name;

public class RuleErrorText extends FormSoapObjectParser {
	
	private String language;
	private String message;
	
	public RuleErrorText(SoapObject input){
		super(input);
	}
	
	@Override
	protected void saveProperty (String property, Name name){
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
	protected void saveObject (SoapObject object, Name name){
		
	}
	
	public String getLanguage (){
		return language;
	}
	
	public String getMessage(){
		return message;
	}
}