package com.choschi.memdroid.data.patient;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormSoapObjectParser;
import com.choschi.memdroid.interfaces.Serializable;

/**
 * 
 * is the data object for the patient field which can be sent to the web service
 * 
 * @author Christoph Isch
 * 
 */

public class PatientFieldData extends FormSoapObjectParser implements Serializable {

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
	
	/**
	 * init the object with a web service response input
	 * @param input
	 */
	
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
	
	/**
	 * get the field id
	 * @return id of the field
	 */
	
	public String getId(){
		return fieldId;
	}
	
	/**
	 * get the value of the field
	 * @return
	 */
	
	public String getValue(){
		return value;
	}
}
