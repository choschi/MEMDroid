package com.choschi.memdroid.data.form;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormSoapObjectParser;
import com.choschi.memdroid.interfaces.AdapterItem;

/**
 * 
 * a label for a FormQuestion that has more than one possible answer
 * 
 * @author Christoph Isch
 *
 */

public class FormQuestionLabel extends FormSoapObjectParser implements AdapterItem {

	private boolean defaultValue;
	private String labelId;
	private int value;
	private int valueRange;
	private FormName name;
	
	/**
	 * constructor
	 * @param input
	 */
	
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
				labelId = property;
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
	
	/**
	 * get the label id
	 */
	
	public String getId (){
		return labelId;
	}
	
	/**
	 * see if this should be selected by default
	 * @return state
	 */
	
	public boolean isDefault(){
		return defaultValue;
	}
	
	/**
	 * label means in this case the String representation of the label as a whole
	 * @return label as String
	 */
	
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
	
	@Override
	public String toString(){
		return name.getName();
	}
}
