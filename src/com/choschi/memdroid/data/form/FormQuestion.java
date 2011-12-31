package com.choschi.memdroid.data.form;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormSoapObjectParser;
import com.choschi.memdroid.webservice.requests.ServerGetFormDefinitionResponse.Name;

public class FormQuestion extends FormSoapObjectParser {

	private String dataType;
	private String defaultName;
	private String maxanswer;
	private String minanswer;
	private String presentationType;
	private int questionId;
	private int score;
	private int textQuestionNo;
	private String unit;
	
	private List<FormQuestionRule> constantRules;
	private List<FormQuestionRule> questionRules;
	private List<FormQuestionLabel> labels;
	
	public FormQuestion (SoapObject input){
		super(input);
	}
	
	@Override
	protected void saveProperty(String property, Name name) {
		switch (name){
			case DATA_TYPE:
				dataType = property;
			break;
			case DEFAULT_NAME:
				defaultName = property;
			break;
			case MAXANSWER:
				maxanswer = property;
			break;
			case MINANSWER:
				minanswer = property;
			break;
			case PRESENTATION_TYPE:
				presentationType = property;
			break;
			case QUESTION_ID:
				questionId = parseInteger(property);
			break;
			case SCORE:
				score = parseInteger(property);
			break;
			case TEXT_QUESTION_NO:
				textQuestionNo = parseInteger(property);
			break;
			case UNIT:
				unit = property;
			break;
		}
	}

	@Override
	protected void saveObject(SoapObject property, Name name) {
		switch (name){
			case LABEL:
				if (labels == null){
					labels = new ArrayList<FormQuestionLabel>();
				}
				labels.add (new FormQuestionLabel(property));
			break;
			case QUESTION_RULE:
				if (questionRules == null){
					questionRules = new ArrayList<FormQuestionRule>();
				}
				questionRules.add(new FormQuestionQuestionRule(property));
			break;
			case CONSTANT_RULE:
				if (constantRules == null){
					constantRules = new ArrayList<FormQuestionRule>();
				}
				constantRules.add(new FormQuestionConstantRule(property));
			break;
		}
	}
	
	public int getQuestionId (){
		return questionId;
	}
	
}
