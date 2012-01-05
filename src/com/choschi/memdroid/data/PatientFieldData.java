package com.choschi.memdroid.data;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.interfaces.Serializable;

/**
 * 
 * @author Christoph Isch
 * 
 * is the data object for the patient field which can be sent to the web service
 * 
 */

public class PatientFieldData extends FormSoapObjectParser implements Serializable {

	//public static Class PATIENT_FIELD_DATA_CLASS = PatientFieldData.class;
	private String fieldId;
	private String value;
	
	/**
	 * init the object with the init values
	 * @param id
	 * @param val
	 */
	
	public PatientFieldData (String id, String val){
		super(null);
		fieldId = id;
		value = val;
	}
	
	public PatientFieldData (SoapObject input){
		super (input);
	}
	
	@Override
	protected void saveProperty(String property, Name name) {
		switch (name){
		case PATIENT_FIELD_FIELD_ID:
			fieldId = property;
			break;
		case VALUE:
			value = property;
			break;
		}
	}
	@Override
	protected void saveObject(SoapObject object,Name name){}
	
	@Override
	public SoapObject toSoapObject(String namespace){
		SoapObject out = new SoapObject (namespace,"PatientFieldData");
		out.addProperty ("fieldId",fieldId);
		out.addProperty ("value",value);
		return out;
	}
	
	public String getId(){
		return fieldId;
	}
	
	public String getValue(){
		return value;
	}
}
