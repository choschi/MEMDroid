package com.choschi.memdroid.data.rule;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormSoapObjectParser;
import com.choschi.memdroid.webservice.requests.ServerGetFormDefinitionResponse.Name;

public class RuleSubformActivator extends FormSoapObjectParser {

	private boolean hidden;
	private boolean optional;
	private String scoreType;
	private int subformId;
	
	public RuleSubformActivator(SoapObject input) {
		super(input);
	}

	@Override
	protected void saveProperty(String property, Name name) {
		switch (name){
			case HIDDEN:
				hidden = parseBoolean(property);
			break;
			case OPTIONAL:
				optional = parseBoolean(property);
			break;
			case SCORE_TYPE:
				scoreType = property;
			break;
			case SUBFORM_ID:
				subformId = parseInteger(property);
			break;
		}
	}

	@Override
	protected void saveObject(SoapObject property, Name name) {
		
	}

}
