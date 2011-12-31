package com.choschi.memdroid.data.rule;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormSoapObjectParser;
import com.choschi.memdroid.data.form.FormQuestionLabel;
import com.choschi.memdroid.webservice.requests.ServerGetFormDefinitionResponse.Name;

public class MultipleChoiceRulesContainer extends FormSoapObjectParser {
	
	private RuleErrorText errorText;
	private RuleLabelsIf labelsIf;
	private boolean mandatory;
	private int mcRuleId;
	private String operator;
	private List<RuleQuestionDeactivator> questionDeactivators;
	private List<FormQuestionLabel> labelDeactivators;
	private List<RuleSubformActivator> subformActivators;
	private int sumValue;
	
	
	public MultipleChoiceRulesContainer (SoapObject input){
		super(input);
	}
	
	@Override
	protected void saveProperty (String property, Name name){
		switch (name){
			case MANDATORY:
				mandatory = parseBoolean(property);
			break;
			case MC_RULE_ID:
				mcRuleId = parseInteger(property);
			break;
			case OPERATOR:
				operator = property;
			break;
			case SUM_VALUE:
				sumValue = parseInteger(property);
			break;
		}
	}
	
	@Override
	protected void saveObject(SoapObject property, Name name){
		switch (name){
			case ERROR_TEXT:
				errorText = new RuleErrorText(property);
			break;
			case LABELS_IF:
				labelsIf = new RuleLabelsIf(property);
			break;
			case QUESTIONS_DEACTIVATE:
				if (questionDeactivators == null){
					questionDeactivators = new ArrayList<RuleQuestionDeactivator>();
				}
				questionDeactivators.add(new RuleQuestionDeactivator(property));
			break;
			case LABEL_DEACTIVATE:
				if (labelDeactivators == null){
					labelDeactivators = new ArrayList<FormQuestionLabel>();
				}
				labelDeactivators.add(new FormQuestionLabel(property));
			break;
			case SUBFORM_ACTIVATE:
				if (subformActivators == null){
					subformActivators = new ArrayList<RuleSubformActivator>();
				}
				subformActivators.add(new RuleSubformActivator(property));
			break;
		}
	}
}
