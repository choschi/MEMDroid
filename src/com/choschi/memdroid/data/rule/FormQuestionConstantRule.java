package com.choschi.memdroid.data.rule;

import org.ksoap2.serialization.SoapObject;


/**
 * 
 * class for FormQuestionRule, these are ignored for instance but nonetheless parsed through data retrieval
 * 
 * @author Christoph Isch
 *
 */

public class FormQuestionConstantRule extends FormQuestionRule {

	private String value;
	
	/**
	 * constructor
	 * @param input
	 */
	
	public FormQuestionConstantRule(SoapObject input) {
		super(input);
	}
	
	@Override
	protected void saveProperty (String property, Name name){
		switch (name){
			case VALUE:
				value  = property;
			break;
			default:
				super.saveProperty(property, name);
			break;
		}
	}
	
	/**
	 * get the value of the constant rule
	 * @return value
	 */
	
	public String getValue(){
		return value;
	}
	
}
