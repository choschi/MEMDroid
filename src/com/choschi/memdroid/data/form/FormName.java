package com.choschi.memdroid.data.form;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormSoapObjectParser;

/**
 * 
 * represents the name object in many form elements
 * 
 * @author Christoph Isch
 *
 */

public class FormName extends FormSoapObjectParser {
	
	private String language;
	private String name;
	private String description;
	
	/**
	 * constructor
	 * @param input
	 */
	
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
	
	/**
	 * get the language of the actual name object
	 * @return
	 */
	
	public String getLanguage(){
		return language;
	}
	
	/**
	 * this returns the display name
	 * @return
	 */
	
	public String getName(){
		return name;
	}
	
	/**
	 * get the description
	 * @return
	 */
	
	public String getDescription (){
		return description;
	}

}
