package com.choschi.memdroid.data.form;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormSoapObjectParser;

public class FormSection extends FormSoapObjectParser {
	
	
	private String defaultName;
	private int sectionId;
	private int textSectionNo;
	
	private List<FormGroup> groups;
	
	public FormSection (SoapObject input){
		super(input);
	}
	
	@Override
	protected void saveProperty(String property, Name name) {
		switch (name){
			case DEFAULT_NAME:
				defaultName = property;
			break;
			case SECTION_ID:
				sectionId = parseInteger(property);
			break;
			case TEXT_SECTION_NO:
				textSectionNo = parseInteger(property);
			break;
		}
	}

	@Override
	protected void saveObject(SoapObject property, Name name) {
		switch (name){
			case GROUP:
				if (groups == null){
					groups = new ArrayList<FormGroup>();
				}
				groups.add(new FormGroup (property));
			break;
		}
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
	 * @return the sectionId
	 */
	public int getSectionId() {
		return sectionId;
	}

	/**
	 * @param sectionId the sectionId to set
	 */
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	/**
	 * @return the textSectionNo
	 */
	public int getTextSectionNo() {
		return textSectionNo;
	}

	/**
	 * @param textSectionNo the textSectionNo to set
	 */
	public void setTextSectionNo(int textSectionNo) {
		this.textSectionNo = textSectionNo;
	}
	
	/**
	 * @return the list of form groups for this section
	 */
	
	public List<FormGroup> getGroups(){
		return groups;
	}
	
	/**
	 * 
	 * @return all the questions in this sections groups
	 */
	
	public List<FormQuestion> getQuestions(){
		List<FormQuestion> questions = new ArrayList<FormQuestion>();
		for (FormGroup group : groups){
			questions.addAll(group.getQuestions());
		}
		return questions;
	}
}
