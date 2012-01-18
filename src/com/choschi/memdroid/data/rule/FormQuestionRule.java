package com.choschi.memdroid.data.rule;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormSoapObjectParser;

/**
 * 
 * base class for all the form question rules, which are ignored for instance
 * 
 * @author Christoph Isch
 *
 */

public abstract class FormQuestionRule extends FormSoapObjectParser {

	private boolean mandatory;
	private String operator;
	private int ruleId;
	private RuleText ruleText;
	
	/**
	 * constructor
	 * @param input
	 */
	
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
	
	/**
	 * is the rule mandatory
	 * @return
	 */
	
	public boolean isMandatory(){
		return mandatory;
	}
	
	/**
	 * what is the operator
	 * @return
	 */
	
	public String getOperator(){
		return operator;
	}
	
	/**
	 * get the id
	 * @return
	 */
	
	public int getId (){
		return ruleId;
	}
	
	/**
	 * get the message if the rule is not fulfilled
	 * @return
	 */
	
	public String getMessage(){
		return ruleText.getMessage();
	}

}
