package com.choschi.memdroid.data.rule;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormName;
import com.choschi.memdroid.data.FormSoapObjectParser;

public class RuleLabelsIf extends FormSoapObjectParser {
	
	private boolean defaultValue;
	private int labelId;
	private FormName name;
	private int value;
	private int valueRange;
	
	public RuleLabelsIf (SoapObject input){
		super(input);
	}
	
	@Override
	protected void saveProperty(String property, Name name) {
		switch (name){
			case DEFAULT_VALUE:
				defaultValue = parseBoolean(property);
			break;
			case LABEL_ID:
				labelId= parseInteger(property);
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
				this.name = new FormName (property);
			break;
		}
	}
	
	public boolean getDefaultValue(){
		return defaultValue;
	}
	
	public int getLabelId(){
		return labelId;
	}
	
	public FormName getName(){
		return name;
	}
	
	public int getValue(){
		return value;
	}
	
	public int getValueRange(){
		return valueRange;
	}
}
