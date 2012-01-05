package com.choschi.memdroid.data.form;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormName;
import com.choschi.memdroid.data.FormSoapObjectParser;

public class FormQuestionLabel extends FormSoapObjectParser {

	private boolean defaultValue;
	private int labelId;
	private int value;
	private int valueRange;
	private FormName name;
	
	public FormQuestionLabel(SoapObject input) {
		super(input);
	}

	@Override
	protected void saveProperty(String property, Name name) {
		switch (name){
			case DEFAULT_VALUE:
				defaultValue = parseBoolean(property);
			break;
			case LABEL_ID:
				labelId = parseInteger(property);
			break;
			case VALUE:
				value = parseInteger(property);
			break;
			case VALUE_RANGE:
				valueRange = parseInteger(property);
			break;
		}
	}

	@Override
	protected void saveObject(SoapObject property, Name name) {
		switch (name){
			case NAME:
				this.name = new FormName(property);
			break;
		}
	}
	
	public int getId (){
		return labelId;
	}
	
	public boolean isDefault(){
		return defaultValue;
	}
	
	public String getLabel(){
		return name.getName();
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the valueRange
	 */
	public int getValueRange() {
		return valueRange;
	}

	/**
	 * @param valueRange the valueRange to set
	 */
	public void setValueRange(int valueRange) {
		this.valueRange = valueRange;
	}
}
