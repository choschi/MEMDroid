package com.choschi.memdroid.data;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import android.util.Log;

/**
 * 
 * Is a base class for all the data received from the server, that has actually been packed in a object like structur
 * 
 * @author Christoph Isch
 *
 */

public abstract class FormSoapObjectParser extends SoapObjectParser {
	
	/**
	 * defines the names of elements that are recognised in the parsing process
	 */
	
	public enum Name{
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
		ID ("id"),
		NAME ("name"),
		TYPE ("type"),
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
		PATIENT_HASH_CODE ("hashCodes"),
		PATIENT_CLINIC_ID ("patientClinicId"),
		PATIENT_ITEM ("item"),
		PATIENT_ENCRYPTED_ID ("encryptedId"),
		PATIENT_MRN ("hashedMrn"),
		PATIENT_PERMISSION ("permissions"),
		PATIENT_YEAR_OF_BIRTH ("yearOfBirth"),
		PATIENT_PERMISSION_GROUP_ID ("groupId"),
		PATIENT_PERMISSION_ID ("patientPermissionId"),
		PATIENT_PERMISSION_NAME ("permission"),
		DEPARTMENT_CLINIC_ID ("clinicId"),
		DEPARTMENT_CLINIC_NAME ("clinicName"),
		DEPARTMENT_COUNTRY_ID ("countryId"),
		DEPARTMENT_ID ("departmentId"),
		DEPARTMENT_NAME ("departmentName"),
		PATIENT_FIELD_ADDITIONAL_CHARACTERS ("additionalCharacters"),
		PATIENT_FIELD_ALLOW_VALIDATION_DEACTIVATION ("allowValidationDeactivation"),
		PATIENT_FIELD_COLUMNSPAN ("columnspan"),
		PATIENT_FIELD_FIELD_ID ("fieldId"),
		PATIENT_FIELD_FIELDPOSITION ("fieldPosition"),
		PATIENT_FIELD_FIELD_TYPE ("fieldType"),
		PATIENT_FIELD_FIXED_FIELD_TYPE ("fixedFieldType"),
		PATIENT_FIELD_GROUP_NUMBER ("groupNumber"),
		PATIENT_FIELD_LABEL ("label"),
		PATIENT_FIELD_LENGTH_MAX ("lengthMax"),
		PATIENT_FIELD_LENGTH_MIN ("lengthMin"),
		PATIENT_FIELD_PATIENT_LIST ("patientlist"),
		PATIENT_FIELD_READ_ONLY ("readOnly"),
		PATIENT_FIELD_REQUIRED ("required"),
		PATIENT_FIELD_USE_TIME ("useTime"),
		PATIENT_FIELD_USE_UNSPECIFIED_GENDER ("useUnspecifiedGender"),
		PATIENT_FIELD_VALIDATION_REGULAR_EXPRESSION ("validationRegularExpression"),
		;
		
		private String name;
		
		private Name(String name){
			this.name = name;
		}
		
		public String toString(){
			return name;
		}
	}
	
	/**
	 * constructor, only parses not null SoapObjects
	 * @param input
	 */
	
	public FormSoapObjectParser (SoapObject input){
		if (input != null){
			parseObject(input);
		}
	}
	
	/**
	 * this is the basic parser
	 * @param input
	 */
	
	protected void parseObject (SoapObject input){
		for (int i=0;i<input.getPropertyCount();i++){
			Object property = input.getProperty(i);
			PropertyInfo properties = new PropertyInfo();
			input.getPropertyInfo(i,properties);
			// try to parse an input as primitive, if it fails try to parse it as object
			try{
				this.handle((SoapPrimitive)property, properties.getName());
			}catch (Exception ex){
				try{
					this.handle((SoapObject)property, properties.getName());
				}catch (Exception nex){
				}
			}
		}
	}
	
	/**
	 * handles the SoapPrimitive input types
	 * @param property
	 * @param propertyName
	 */
	
	protected void handle (SoapPrimitive property, String propertyName){
		boolean found = false;
		for (Name name : Name.values()){
			if (propertyName.equalsIgnoreCase(name.toString())){
				saveProperty (property.toString(), name);
				found = true;
			}
		}
		// if an input type is not found, then tell the programmer
		if (!found){
			Log.i("primitve renderer in "+this.getClass(),"no handle for "+propertyName);
		}
	}
	
	/**
	 * handles the SoapObject input types
	 * @param property
	 * @param propertyName
	 */
	
	protected void handle (SoapObject property, String propertyName){
		boolean found = false;
		for (Name name : Name.values()){
			if (propertyName.equalsIgnoreCase(name.toString())){
				saveObject (property, name);
				found = true;
			}
		}
		// if an input type is not found, then tell the programmer
		if (!found){
			Log.i("object renderer in "+this.getClass(),"no handle for "+propertyName);
		}
	}
	
	/**
	 * the real projection on the object data is done in subclasses
	 * @param property
	 * @param name
	 */
	
	protected abstract void saveProperty (String property,Name name);
	
	/**
	 * the real projection on the object type is done in subclasses
	 * @param property
	 * @param name
	 */
	
	protected abstract void saveObject (SoapObject property, Name name);
}
