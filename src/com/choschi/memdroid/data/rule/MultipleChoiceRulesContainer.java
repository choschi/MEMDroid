package com.choschi.memdroid.data.rule;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormSoapObjectParser;
import com.choschi.memdroid.data.form.FormQuestionLabel;

/**
 * 
 * contains all the rules for a form
 * 
 * @author Christoph Isch
 *
 */

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

	/**
	 * @return the errorText
	 */
	public RuleErrorText getErrorText() {
		return errorText;
	}

	/**
	 * @param errorText the errorText to set
	 */
	public void setErrorText(RuleErrorText errorText) {
		this.errorText = errorText;
	}

	/**
	 * @return the labelsIf
	 */
	public RuleLabelsIf getLabelsIf() {
		return labelsIf;
	}

	/**
	 * @param labelsIf the labelsIf to set
	 */
	public void setLabelsIf(RuleLabelsIf labelsIf) {
		this.labelsIf = labelsIf;
	}

	/**
	 * @return the mandatory
	 */
	public boolean isMandatory() {
		return mandatory;
	}

	/**
	 * @param mandatory the mandatory to set
	 */
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	/**
	 * @return the mcRuleId
	 */
	public int getMcRuleId() {
		return mcRuleId;
	}

	/**
	 * @param mcRuleId the mcRuleId to set
	 */
	public void setMcRuleId(int mcRuleId) {
		this.mcRuleId = mcRuleId;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @return the sumValue
	 */
	public int getSumValue() {
		return sumValue;
	}

	/**
	 * @param sumValue the sumValue to set
	 */
	public void setSumValue(int sumValue) {
		this.sumValue = sumValue;
	}
}
