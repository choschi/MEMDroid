package com.choschi.memdroid.data.rule;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormSoapObjectParser;

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

	/**
	 * @return the hidden
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * @param hidden the hidden to set
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * @return the optional
	 */
	public boolean isOptional() {
		return optional;
	}

	/**
	 * @param optional the optional to set
	 */
	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	/**
	 * @return the scoreType
	 */
	public String getScoreType() {
		return scoreType;
	}

	/**
	 * @param scoreType the scoreType to set
	 */
	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}

	/**
	 * @return the subformId
	 */
	public int getSubformId() {
		return subformId;
	}

	/**
	 * @param subformId the subformId to set
	 */
	public void setSubformId(int subformId) {
		this.subformId = subformId;
	}

}
