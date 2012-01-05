package com.choschi.memdroid.data;

import org.ksoap2.serialization.SoapObject;


/**
 * 
 * @author Christoph Isch
 *
 * Represents a patient field returned by the getPatientFields requests to the module
 *
 */

public class PatientField extends FormSoapObjectParser{
	
	private String additionalCharacters;
	private boolean allowValidationDeactivation;
	private int columnspan;
	private int fieldId;
	private int fieldPosition;
	private String fieldType;
	private String fixedFieldType;
	private int groupNumber;
	private String label;
	private int lengthMax;
	private int lengthMin;
	private boolean patientlist;
	private boolean readOnly;
	private boolean required;
	private boolean useTime;
	private boolean useUnspecifiedGender;
	private String validationRegularExpression;
	
	public PatientField (SoapObject response){
		super(response);
	}


	@Override
	public void saveProperty(String property,Name name) {
		switch (name){
		case PATIENT_FIELD_ADDITIONAL_CHARACTERS:
			additionalCharacters = property;
			break;
		case PATIENT_FIELD_ALLOW_VALIDATION_DEACTIVATION:
			allowValidationDeactivation = parseBoolean(property);
			break;
		case PATIENT_FIELD_COLUMNSPAN:
			columnspan = parseInteger(property);
			break;
		case PATIENT_FIELD_FIELD_ID:
			fieldId = parseInteger(property);
			break;
		case PATIENT_FIELD_FIELDPOSITION:
			fieldPosition = parseInteger(property);
			break;
		case PATIENT_FIELD_FIELD_TYPE:
			fieldType = property;
			break;
		case PATIENT_FIELD_FIXED_FIELD_TYPE:
			fixedFieldType = property;
			break;
		case PATIENT_FIELD_GROUP_NUMBER:
			groupNumber = parseInteger(property);
			break;
		case PATIENT_FIELD_LABEL:
			label = property;
			break;
		case PATIENT_FIELD_LENGTH_MAX:
			lengthMax = parseInteger(property);
			break;
		case PATIENT_FIELD_LENGTH_MIN:
			lengthMin = parseInteger(property);
			break;
		case PATIENT_FIELD_PATIENT_LIST:
			patientlist = parseBoolean(property);
			break;
		case PATIENT_FIELD_READ_ONLY:
			readOnly = parseBoolean(property);
			break;
		case PATIENT_FIELD_REQUIRED:
			required = parseBoolean(property);
			break;
		case PATIENT_FIELD_USE_TIME:
			useTime = parseBoolean(property);
			break;
		case PATIENT_FIELD_USE_UNSPECIFIED_GENDER:
			useUnspecifiedGender = parseBoolean(property);
			break;
		case PATIENT_FIELD_VALIDATION_REGULAR_EXPRESSION: 
			validationRegularExpression = property;
			break;
		}
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

	public boolean getRequired(){
		return required;
	}
	
	/**
	 * @return the additionalCharacters
	 */
	public String getAdditionalCharacters() {
		return additionalCharacters;
	}

	/**
	 * @param additionalCharacters the additionalCharacters to set
	 */
	public void setAdditionalCharacters(String additionalCharacters) {
		this.additionalCharacters = additionalCharacters;
	}

	@Override
	protected void saveObject(SoapObject property, Name name) {
	}


	/**
	 * @return the lengthMax
	 */
	public int getLengthMax() {
		return lengthMax;
	}


	/**
	 * @param lengthMax the lengthMax to set
	 */
	public void setLengthMax(int lengthMax) {
		this.lengthMax = lengthMax;
	}


	/**
	 * @return the lengthMin
	 */
	public int getLengthMin() {
		return lengthMin;
	}


	/**
	 * @param lengthMin the lengthMin to set
	 */
	public void setLengthMin(int lengthMin) {
		this.lengthMin = lengthMin;
	}


	/**
	 * @return the patientlist
	 */
	public boolean isPatientlist() {
		return patientlist;
	}


	/**
	 * @param patientlist the patientlist to set
	 */
	public void setPatientlist(boolean patientlist) {
		this.patientlist = patientlist;
	}


	/**
	 * @return the readOnly
	 */
	public boolean isReadOnly() {
		return readOnly;
	}


	/**
	 * @param readOnly the readOnly to set
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}


	/**
	 * @return the useTime
	 */
	public boolean isUseTime() {
		return useTime;
	}


	/**
	 * @param useTime the useTime to set
	 */
	public void setUseTime(boolean useTime) {
		this.useTime = useTime;
	}


	/**
	 * @return the useUnspecifiedGender
	 */
	public boolean isUseUnspecifiedGender() {
		return useUnspecifiedGender;
	}


	/**
	 * @param useUnspecifiedGender the useUnspecifiedGender to set
	 */
	public void setUseUnspecifiedGender(boolean useUnspecifiedGender) {
		this.useUnspecifiedGender = useUnspecifiedGender;
	}


	/**
	 * @return the validationRegularExpression
	 */
	public String getValidationRegularExpression() {
		return validationRegularExpression;
	}


	/**
	 * @param validationRegularExpression the validationRegularExpression to set
	 */
	public void setValidationRegularExpression(
			String validationRegularExpression) {
		this.validationRegularExpression = validationRegularExpression;
	}
	
}
