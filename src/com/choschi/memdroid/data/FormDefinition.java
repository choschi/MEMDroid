package com.choschi.memdroid.data;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.form.SubFormContainer;
import com.choschi.memdroid.data.rule.MultipleChoiceRulesContainer;

/**
 * Wrapper for the Module login request response
 * @author choschi
 *
 */

public class FormDefinition extends FormSoapObjectParser {
	
	private List<SubFormContainer> subForms;
	private String creationDate;
	private String defaultName;
	private String formId;
	private MultipleChoiceRulesContainer rules;
	private FormName name;
	
	/**
	 * render the SoapObject in a more meaningful entity
	 * @param response
	 */
	
	public FormDefinition(SoapObject response){
		super(response);
	}
		
	@Override
	protected void saveProperty (String property, Name name){
		switch (name){
			case CREATION_DATE:
				creationDate = property;
			break;
			case DEFAULT_NAME:
				defaultName = property;
			break;
			case FORM_ID:
				formId = property;
			break;
		}
	}
	
	@Override
	protected void saveObject(SoapObject property, Name name){
		switch (name){
			case MULTI_CHOICE_RULES:
				rules = new MultipleChoiceRulesContainer(property);
			break;
			case NAME:
				this.name = new FormName(property);
			break;
			case SUBFORM:
				if (subForms == null){
					subForms = new ArrayList<SubFormContainer>();
				}
				subForms.add(new SubFormContainer(property));
			break;
		}
	}

	public List<SubFormContainer> getSubForms(){
		return subForms;
	}

	/**
	 * @return the creationDate
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
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
	 * @return the formId
	 */
	public String getFormId() {
		return formId;
	}

	/**
	 * @param formId the formId to set
	 */
	public void setFormId(String formId) {
		this.formId = formId;
	}

	/**
	 * @return the rules
	 */
	public MultipleChoiceRulesContainer getRules() {
		return rules;
	}

	/**
	 * @param rules the rules to set
	 */
	public void setRules(MultipleChoiceRulesContainer rules) {
		this.rules = rules;
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
}