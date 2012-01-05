package com.choschi.memdroid.webservice.requests;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.util.Log;

import com.choschi.memdroid.data.FormName;
import com.choschi.memdroid.data.FormSoapObjectParser;
import com.choschi.memdroid.data.form.SubFormContainer;
import com.choschi.memdroid.data.rule.MultipleChoiceRulesContainer;
import com.choschi.memdroid.webservice.interfaces.Result;

/**
 * Wrapper for the Module login request response
 * @author choschi
 *
 */

public class ServerGetFormDefinitionResponse extends FormSoapObjectParser implements Result {
	
	public enum Name {
		UNDEFINED ("undefined"),
		CREATION_DATE ("creationDate"),
		DEFAULT_NAME ("defaultName"),
		FORM_ID ("formId"), 
		MULTI_CHOICE_RULES ("multipleChoiceRules"),
		ERROR_TEXT ("errorText"),
		LANGUAGE ("language"), 
		MESSAGE ("message"),
		LABELS_IF ("labelsIf"),
		DEFAULT_VALUE ("defaultValue"), 
		LABEL_ID ("labelId"),
		NAME ("name"),
		VALUE ("value"),
		VALUE_RANGE ("valueRange"),
		MANDATORY ("mandatory"),
		MC_RULE_ID ("mcRuleId"),
		OPERATOR ("operator"),
		QUESTIONS_DEACTIVATE ("questionsThenDeactivate"),
		PRESENTATION_TYPE ("presentationType"),
		DESCRIPTION ("description"),
		SUBFORM ("subforms"),
		HIDDEN ("hidden"),
		OPTIONAL ("optional"),
		SCORE_TYPE ("scoreType"),
		SECTION ("sections"),
		GROUP ("groups"),
		QUESTION ("questions"),
		GROUP_ID ("groupId"),
		QUESTION_ID ("questionId"),
		DATA_TYPE ("dataType"),
		MAXANSWER ("maxanswer"),
		MINANSWER ("minanswer"),
		SCORE ("score"),
		TEXT_QUESTION_NO ("textQuestionNo"),
		UNIT ("unit"),
		RULE_ID ("ruleId"),
		RULE_TEXT ("ruleText"),
		LABEL ("labels"),
		CONSTANT_RULE ("ccRules"),
		QUESTION_RULE ("cqRules"),
		SECTION_ID ("sectionId"),
		TEXT_SECTION_NO ("textSectionNo"),
		TEXT_GROUP_NO ("textGroupNo"),
		SUBFORM_ID ("subformId"),
		VERSION ("version"),
		SUM_VALUE ("sumvalue"),
		LINKED_QUESTION ("linkedQuestion"),
		LABEL_DEACTIVATE ("labelsThenDeactivate"),
		SUBFORM_ACTIVATE ("subformsThenActivate"),
		PATIENT_FIELD_DATA ("patientFieldDatas"),
		PATIENT_ID ("patientId"),
		HASH_CODE ("hashCodes"),
		PATIENT_CLINIC_ID ("patientClinicId"),
		PATIENT_ITEM ("item"),
		PATIENT_ENCRYPTED_ID ("encryptedId"),
		PATIENT_MRN ("hashedMrn"),
		PATIENT_PERMISSION ("permissions"),
		PATIENT_YEAR_OF_BIRTH ("yearOfBirth"),
		PATIENT_PERMISSION_GROUP_ID ("groupId"),
		PATIENT_PERMISSION_ID ("patientPermissionId"),
		PATIENT_PERMISSION_NAME ("permission"),
		;
		
		private String name;
		
		private Name(String name){
			this.name = name;
		}
		
		public String toString(){
			return name;
		}
	}
	
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
	
	public ServerGetFormDefinitionResponse(SoapObject response){
		super(response);
		Log.d ("input",response.toString());
		Log.d ("should","be initalised");
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
}