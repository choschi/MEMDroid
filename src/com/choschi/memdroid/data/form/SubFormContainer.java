package com.choschi.memdroid.data.form;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormName;
import com.choschi.memdroid.data.FormSoapObjectParser;
import com.choschi.memdroid.webservice.requests.ServerGetFormDefinitionResponse.Name;

public class SubFormContainer extends FormSoapObjectParser {
	
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
				scoreType = parseInteger(property);
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
	
	@Deprecated
	public List<SubForm> getSubForms(){
		return null;
	}
	
}
