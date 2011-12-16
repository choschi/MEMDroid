package com.choschi.memdroid.data;

import java.util.List;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

public class PatientField extends SoapObjectParser{
	
	private List<String> additionalCharacters;
	private boolean allowValidationDeactivation;
	private int columnspan;
	private int fieldId;
	private int fieldPosition;
	private String fieldType;
	private String fixedFieldType;
	private int groupNumber;
	private String label;
	private PatientFieldRule rule;

	
	public PatientField (SoapObject input){
		
		//TODO check if this really can be set to null
		
		additionalCharacters = null;
		allowValidationDeactivation = parseBoolean((((SoapPrimitive)input.getProperty("allowValidationDeactivation")).toString()));
		columnspan = parseInteger((((SoapPrimitive)input.getProperty("columnspan")).toString()));
		fieldId = parseInteger((((SoapPrimitive)input.getProperty("fieldId")).toString()));
		fieldPosition = parseInteger((((SoapPrimitive)input.getProperty("fieldPosition")).toString()));
		fieldType = ((SoapPrimitive)input.getProperty("fieldType")).toString();
		fixedFieldType = ((SoapPrimitive)input.getProperty("fixedFieldType")).toString();
		groupNumber = parseInteger((((SoapPrimitive)input.getProperty("fieldId")).toString()));
		label = ((SoapPrimitive)input.getProperty("fixedFieldType")).toString();
		
		rule = extractRule(input);
	}
	
	private PatientFieldRule extractRule (SoapObject input){
		return new PatientFieldRule (
			parseInteger((((SoapPrimitive)input.getProperty("lengthMax")).toString())),
			parseInteger((((SoapPrimitive)input.getProperty("lengthMin")).toString())),
			parseBoolean((((SoapPrimitive)input.getProperty("patientlist")).toString())),
			parseBoolean((((SoapPrimitive)input.getProperty("readOnly")).toString())),
			parseBoolean((((SoapPrimitive)input.getProperty("required")).toString())),
			parseBoolean((((SoapPrimitive)input.getProperty("useTime")).toString())),
			parseBoolean((((SoapPrimitive)input.getProperty("useUnspecifiedGender")).toString())),
			
			//TODO check if this really can always be set to empty
			""
			//((SoapPrimitive)input.getProperty("validationRegularExpression")).toString()
		);
	}

	/**
	 * @return the allowValidationDeactivation
	 */
	public boolean isAllowValidationDeactivation() {
		return allowValidationDeactivation;
	}

	/**
	 * @param allowValidationDeactivation the allowValidationDeactivation to set
	 */
	public void setAllowValidationDeactivation(boolean allowValidationDeactivation) {
		this.allowValidationDeactivation = allowValidationDeactivation;
	}

	/**
	 * @return the columnspan
	 */
	public int getColumnspan() {
		return columnspan;
	}

	/**
	 * @param columnspan the columnspan to set
	 */
	public void setColumnspan(int columnspan) {
		this.columnspan = columnspan;
	}

	/**
	 * @return the fieldId
	 */
	public int getFieldId() {
		return fieldId;
	}

	/**
	 * @param fieldId the fieldId to set
	 */
	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}

	/**
	 * @return the fieldPosition
	 */
	public int getFieldPosition() {
		return fieldPosition;
	}

	/**
	 * @param fieldPosition the fieldPosition to set
	 */
	public void setFieldPosition(int fieldPosition) {
		this.fieldPosition = fieldPosition;
	}

	/**
	 * @return the fieldType
	 */
	public String getFieldType() {
		return fieldType;
	}

	/**
	 * @param fieldType the fieldType to set
	 */
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	/**
	 * @return the fixedFieldType
	 */
	public String getFixedFieldType() {
		return fixedFieldType;
	}

	/**
	 * @param fixedFieldType the fixedFieldType to set
	 */
	public void setFixedFieldType(String fixedFieldType) {
		this.fixedFieldType = fixedFieldType;
	}

	/**
	 * @return the groupNumber
	 */
	public int getGroupNumber() {
		return groupNumber;
	}

	/**
	 * @param groupNumber the groupNumber to set
	 */
	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the rule
	 */
	public PatientFieldRule getRule() {
		return rule;
	}

	/**
	 * @param rule the rule to set
	 */
	public void setRule(PatientFieldRule rule) {
		this.rule = rule;
	}

	/**
	 * @return the additionalCharacters
	 */
	public List<String> getAdditionalCharacters() {
		return additionalCharacters;
	}

	/**
	 * @param additionalCharacters the additionalCharacters to set
	 */
	public void setAdditionalCharacters(List<String> additionalCharacters) {
		this.additionalCharacters = additionalCharacters;
	}
	
}
