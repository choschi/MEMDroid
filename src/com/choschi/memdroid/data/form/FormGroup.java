package com.choschi.memdroid.data.form;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormName;
import com.choschi.memdroid.data.FormSoapObjectParser;

public class FormGroup extends FormSoapObjectParser {
	
	
	private String defaultName;
	private String groupId;
	private FormName name;
	private String textGroupNo;
	
	private List<FormQuestion> questions;
	
	
	public FormGroup (SoapObject input){
		super(input);
	}
	
	@Override
	protected void saveProperty(String property, Name name) {
		switch (name){
			case DEFAULT_NAME:
				defaultName = property;
			break;
			case GROUP_ID:
				groupId = property;
			break;
			case TEXT_GROUP_NO:
				setTextGroupNo(property);
			break;
		}
		
	}

	@Override
	protected void saveObject(SoapObject property, Name name) {
		switch (name){
			case NAME:
				this.name = new FormName(property);
			break;
			case QUESTION:
				if (questions == null){
					questions = new ArrayList<FormQuestion>();
				}
				questions.add(new FormQuestion(property));
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
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the name
	 */
	public FormName getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(FormName name) {
		this.name = name;
	}

	/**
	 * @return the textGroupNo
	 */
	public String getTextGroupNo() {
		return textGroupNo;
	}

	/**
	 * @param textGroupNo the textGroupNo to set
	 */
	public void setTextGroupNo(String textGroupNo) {
		this.textGroupNo = textGroupNo;
	}
	
	/**
	 * 
	 * @return the list of form questions
	 */
	
	public List<FormQuestion> getQuestions(){
		return questions;
	}

}
