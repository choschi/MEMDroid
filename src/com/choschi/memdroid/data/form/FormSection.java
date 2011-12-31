package com.choschi.memdroid.data.form;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormSoapObjectParser;
import com.choschi.memdroid.webservice.requests.ServerGetFormDefinitionResponse.Name;

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

}
