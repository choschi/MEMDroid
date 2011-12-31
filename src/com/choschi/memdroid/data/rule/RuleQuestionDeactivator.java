package com.choschi.memdroid.data.rule;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormSoapObjectParser;
import com.choschi.memdroid.data.form.FormQuestion;
import com.choschi.memdroid.webservice.requests.ServerGetFormDefinitionResponse.Name;

public class RuleQuestionDeactivator extends FormSoapObjectParser {
	
	private String dataType;
	private String defaultName;
	private String maxanswer;
	private String minanswer;
	private String presentationType;
	private int questionId;
	private int score;
	private int textQuestionNo;
	private String unit;
	
	private List<FormQuestion> linkedQuestions;
	
	public RuleQuestionDeactivator(SoapObject input) {
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
			case LINKED_QUESTION:
				if (linkedQuestions == null){
					linkedQuestions = new ArrayList<FormQuestion>();
				}
				linkedQuestions.add(new FormQuestion(property));
			break;
		}
	}

}
