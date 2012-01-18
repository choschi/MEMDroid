package com.choschi.memdroid.data.form;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormSoapObjectParser;
import com.choschi.memdroid.data.rule.FormQuestionConstantRule;
import com.choschi.memdroid.data.rule.FormQuestionQuestionRule;
import com.choschi.memdroid.data.rule.FormQuestionRule;

/**
 * 
 * class representing a single FormQuestion in a questionnaire
 * 
 * @author Christoph Isch
 *
 */

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

	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * @return the defaultName
	 */
	public String getDefaultName() {
		return defaultName;
	}

	/**
	 * @param defaultName the defaultName to set
	 */
	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	/**
	 * @return the maxanswer
	 */
	public String getMaxanswer() {
		return maxanswer;
	}

	/**
	 * @param maxanswer the maxanswer to set
	 */
	public void setMaxanswer(String maxanswer) {
		this.maxanswer = maxanswer;
	}

	/**
	 * @return the minanswer
	 */
	public String getMinanswer() {
		return minanswer;
	}

	/**
	 * @param minanswer the minanswer to set
	 */
	public void setMinanswer(String minanswer) {
		this.minanswer = minanswer;
	}

	/**
	 * @return the presentationType
	 */
	public String getPresentationType() {
		return presentationType;
	}

	/**
	 * @param presentationType the presentationType to set
	 */
	public void setPresentationType(String presentationType) {
		this.presentationType = presentationType;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the textQuestionNo
	 */
	public int getTextQuestionNo() {
		return textQuestionNo;
	}

	/**
	 * @param textQuestionNo the textQuestionNo to set
	 */
	public void setTextQuestionNo(int textQuestionNo) {
		this.textQuestionNo = textQuestionNo;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	/**
	 * get the label
	 * @return label string
	 */
	
	public String getLabel(){
		return defaultName;
	}
	
	/**
	 * get all the FormQuestionLabel objects 
	 * @return
	 */
	
	public List<FormQuestionLabel> getAllLabels(){
		return labels;
	}
	
}
