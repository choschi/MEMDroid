package com.choschi.memdroid.data.form;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormName;
import com.choschi.memdroid.data.FormSoapObjectParser;
import com.choschi.memdroid.webservice.requests.ServerGetFormDefinitionResponse.Name;

public class FormGroup extends FormSoapObjectParser {
	
	
	private String defaultName;
	private int groupId;
	private FormName name;
	private int textGroupNo;
	
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
				groupId = parseInteger(property);
			break;
			case TEXT_GROUP_NO:
				textGroupNo = parseInteger(property);
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

}
