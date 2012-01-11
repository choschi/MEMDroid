package com.choschi.memdroid.data.form;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormName;
import com.choschi.memdroid.data.FormSoapObjectParser;
import com.choschi.memdroid.interfaces.AdapterItem;

public class SubFormContainer extends FormSoapObjectParser implements AdapterItem {
	
	private String date;
	private String defaultName;
	private boolean hidden;
	private boolean optional;
	private int scoreType;
	private FormName name;
	private String version;
	private int subformId;
	
	
	private List<FormSection> sections;
	
	public SubFormContainer (SoapObject input){
		super (input);
	}
	
	
	@Override
	protected void saveProperty(String property, Name name) {
		switch (name){
			case CREATION_DATE:
				date = property;
			break;
			case DEFAULT_NAME:
				defaultName = property;
			break;
			case HIDDEN:
				hidden = parseBoolean (property);
			break;
			case OPTIONAL:
				optional = parseBoolean(property);
			break;
			case SCORE_TYPE:
				setScoreType(parseInteger(property));
			break;
			case VERSION:
				version = property;
			break;
			case SUBFORM_ID:
				subformId = parseInteger(property);
			break;
		}
	}

	@Override
	protected void saveObject(SoapObject property, Name name) {
		switch (name){
			case SECTION:
				if (sections == null){
					 sections = new ArrayList<FormSection>();
				}
				sections.add(new FormSection(property));
			break;
			case NAME:
				this.name = new FormName(property);
		}
	}

	@Override
	public String getId() {
		return ""+subformId;
	}
	
	@Override
	public String toString(){
		return name.getName();
	}


	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
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
	public int getScoreType() {
		return scoreType;
	}


	/**
	 * @param scoreType the scoreType to set
	 */
	public void setScoreType(int scoreType) {
		this.scoreType = scoreType;
	}


	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}


	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * 
	 * @return all the questions in the sections
	 */
	
	public List<FormQuestion> getQuestions(){
		List<FormQuestion> questions = new ArrayList<FormQuestion>();
		for (FormSection section : sections){
			questions.addAll(section.getQuestions());
		}
		return questions;
	}
}
