package com.choschi.memdroid.data;

import java.util.Hashtable;
import java.util.List;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import com.choschi.memdroid.webservice.interfaces.Result;

public class PatientField implements KvmSerializable,Result {
	
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
	
	public PatientField (SoapObject input){
		additionalCharacters = input.getProperty("additionalCharacters").toString();
		allowValidationDeactivation = Boolean.parseBoolean(input.getProperty("allowValidationDeactivation").toString());
		columnspan = Integer.parseInt(input.getProperty("columnspan").toString());
		fieldId = Integer.parseInt(input.getProperty("fieldId").toString());
		fieldPosition = Integer.parseInt(input.getProperty("fieldPosition").toString());
		fieldType = input.getProperty("fieldType").toString();
		fixedFieldType = input.getProperty("fixedFieldType").toString();
		groupNumber = Integer.parseInt(input.getProperty("fieldId").toString());
		label = input.getProperty("label").toString();
		lengthMax = Integer.parseInt(input.getProperty("lengthMax").toString());
		lengthMin = Integer.parseInt(input.getProperty("lengthMin").toString());
		patientlist = Boolean.parseBoolean(input.getProperty("patientlist").toString());
		readOnly = Boolean.parseBoolean(input.getProperty("readOnly").toString());
		required = Boolean.parseBoolean(input.getProperty("required").toString());
		useTime = Boolean.parseBoolean(input.getProperty("useTime").toString());
		useUnspecifiedGender = Boolean.parseBoolean(input.getProperty("useUnspecifiedGender").toString());
		validationRegularExpression = input.getProperty("validationRegularExpression").toString();
	}
	
	@Override
	public Object getProperty(int arg0) {
		Object out = null;
		switch (arg0) {
		case 0: {
			out = additionalCharacters;
			break;
		}
		case 1: {
			out = allowValidationDeactivation;
			break;
		}
		case 2: {
			out = columnspan;
			break;
		}
		case 3: {
			out = fieldId;
			break;
		}
		case 4: {
			out = fieldPosition;
			break;
		}
		case 5: {
			out = fieldType;
			break;
		}
		case 6: {
			out = fixedFieldType;
			break;
		}
		case 7: {
			out = groupNumber;
			break;
		}
		case 8: {
			out = label;
			break;
		}
		case 9: {
			out = lengthMax;
			break;
		}
		case 10: {
			out = lengthMin;
			break;
		}
		case 11: {
			out = patientlist;
			break;
		}
		case 12: {
			out = readOnly;
			break;
		}
		case 13: {
			out = required;
			break;
		}
		case 14: {
			out = useTime;
			break;
		}
		case 15: {
			out = useUnspecifiedGender;
			break;
		}
		case 16: {
			out = validationRegularExpression;
			break;
		}
		}
		return out;
	}

	@Override
	public int getPropertyCount() {
		return 17;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		switch (arg0) {
		case 0:
			arg2.name = "additionalCharacters";
			arg2.type = PropertyInfo.STRING_CLASS;
			break;
		case 1:
			arg2.name = "allowValidationDeactivation";
			arg2.type = PropertyInfo.BOOLEAN_CLASS;
			break;
		case 2:
			arg2.name = "columnspan";
			arg2.type = PropertyInfo.INTEGER_CLASS;
			break;
		case 3:
			arg2.name = "fieldId";
			arg2.type = PropertyInfo.INTEGER_CLASS;
			break;
		case 4:
			arg2.name = "fieldPosition";
			arg2.type = PropertyInfo.INTEGER_CLASS;
			break;
		case 5:
			arg2.name = "fieldType";
			arg2.type = PropertyInfo.STRING_CLASS;
			break;
		case 6:
			arg2.name = "fixedFieldType";
			arg2.type = PropertyInfo.STRING_CLASS;
			break;
		case 7:
			arg2.name = "groupNumber";
			arg2.type = PropertyInfo.INTEGER_CLASS;
			break;
		case 8:
			arg2.name = "label";
			arg2.type = PropertyInfo.STRING_CLASS;
			break;
		case 9:
			arg2.name = "lengthMax";
			arg2.type = PropertyInfo.INTEGER_CLASS;
			break;
		case 10:
			arg2.name = "lengthMin";
			arg2.type = PropertyInfo.INTEGER_CLASS;
			break;
		case 11:
			arg2.name = "patientlist";
			arg2.type = PropertyInfo.BOOLEAN_CLASS;
			break;
		case 12:
			arg2.name = "readOnly";
			arg2.type = PropertyInfo.BOOLEAN_CLASS;
			break;
		case 13:
			arg2.name = "required";
			arg2.type = PropertyInfo.BOOLEAN_CLASS;
			break;
		case 14:
			arg2.name = "useTime";
			arg2.type = PropertyInfo.BOOLEAN_CLASS;
			break;
		case 15:
			arg2.name = "useUnspecifiedGender";
			arg2.type = PropertyInfo.BOOLEAN_CLASS;
			break;
		case 16:
			arg2.name = "validationRegularExpression";
			arg2.type = PropertyInfo.STRING_CLASS;
			break;
		}
	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		switch (arg0){
		case 0:
			additionalCharacters = arg1.toString();
			break;
		case 1:
			allowValidationDeactivation = Boolean.parseBoolean(arg1.toString());
			break;
		case 2:
			columnspan = Integer.parseInt(arg1.toString());
			break;
		case 3:
			fieldId = Integer.parseInt(arg1.toString());
			break;
		case 4:
			fieldPosition = Integer.parseInt(arg1.toString());
			break;
		case 5:
			fieldType = arg1.toString();
			break;
		case 6:
			fixedFieldType = arg1.toString();
			break;
		case 7:
			groupNumber = Integer.parseInt(arg1.toString());
			break;
		case 8:
			label = arg1.toString();
			break;
		case 9:
			lengthMax = Integer.parseInt(arg1.toString());
			break;
		case 10:
			lengthMin = Integer.parseInt(arg1.toString());
			break;
		case 11:
			patientlist = Boolean.parseBoolean(arg1.toString());
			break;
		case 12:
			readOnly = Boolean.parseBoolean(arg1.toString());
			break;
		case 13:
			required = Boolean.parseBoolean(arg1.toString());
			break;
		case 14:
			useTime = Boolean.parseBoolean(arg1.toString());
			break;
		case 15:
			useUnspecifiedGender = Boolean.parseBoolean(arg1.toString());
			break;
		case 16: 
			validationRegularExpression = arg1.toString();
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
	
}
