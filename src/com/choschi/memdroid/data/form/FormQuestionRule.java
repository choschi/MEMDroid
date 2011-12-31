package com.choschi.memdroid.data.form;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormSoapObjectParser;
import com.choschi.memdroid.data.rule.RuleText;
import com.choschi.memdroid.webservice.requests.ServerGetFormDefinitionResponse.Name;

public abstract class FormQuestionRule extends FormSoapObjectParser {

	private boolean mandatory;
	private String operator;
	private int ruleId;
	private RuleText ruleText;
	
	public FormQuestionRule(SoapObject input) {
		super(input);
	}

	@Override
	protected void saveProperty(String property, Name name) {
		switch (name){
			case MANDATORY:
				mandatory = parseBoolean(property);
			break;
			case OPERATOR:
				operator = property;
			break;
			case RULE_ID:
				ruleId = parseInteger(property);
			break;
		}
	}

	@Override
	protected void saveObject(SoapObject property, Name name) {
		switch (name){
			case RULE_TEXT:
				ruleText = new RuleText(property);
			break;
		}
	}
	
	public boolean isMandatory(){
		return mandatory;
	}
	
	public String getOperator(){
		return operator;
	}
	
	public int getId (){
		return ruleId;
	}
	
	public String getMessage(){
		return ruleText.getMessage();
	}

}
