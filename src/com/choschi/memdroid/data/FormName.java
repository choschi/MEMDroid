package com.choschi.memdroid.data;

import org.ksoap2.serialization.SoapObject;

public class FormName extends FormSoapObjectParser {
	
	private String language;
	private String name;
	private String description;
	
	public FormName (SoapObject input){
		super(input);
	}
	
	
	@Override
	protected void saveProperty(String property, Name name) {
		switch (name){
			case LANGUAGE:
				language = property;
			break;
			case NAME:
				this.name = property;
			break;
			case DESCRIPTION:
				description = property;
			break;
		}
	}

	@Override
	protected void saveObject(SoapObject property, Name name) {
		
	}
	
	public String getLanguage(){
		return language;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDescription (){
		return description;
	}

}
