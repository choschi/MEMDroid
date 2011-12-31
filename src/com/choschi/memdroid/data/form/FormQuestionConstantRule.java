package com.choschi.memdroid.data.form;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.webservice.requests.ServerGetFormDefinitionResponse.Name;


public class FormQuestionConstantRule extends FormQuestionRule {

	private String value;
	
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
	
	public String getValue(){
		return value;
	}
	
}
