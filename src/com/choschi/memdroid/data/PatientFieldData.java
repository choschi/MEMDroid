package com.choschi.memdroid.data;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.interfaces.Serializable;

public class PatientFieldData implements KvmSerializable,Serializable {

	//public static Class PATIENT_FIELD_DATA_CLASS = PatientFieldData.class;
	private String fieldId;
	private String value;
	
	public PatientFieldData (String id, String val){
		fieldId = id;
		value = val;
	}
	
	
	@Override
	public Object getProperty(int arg0) {
		Object out = null;
		switch (arg0){
		case 0:
			out = fieldId;
			break;
		case 1:
			out = value;
			break;
		}
		return out;
	}

	@Override
	public int getPropertyCount() {
		return 2;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		switch (arg0){
		case 0:
			arg2.name = "fieldId";
			arg2.type = PropertyInfo.STRING_CLASS;
			break;
		case 1:
			arg2.name = "value";
			arg2.type = PropertyInfo.STRING_CLASS;
			break;
		}
	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		switch (arg0){
		case 0:
			fieldId = arg1.toString();
			break;
		case 1:
			value = arg1.toString();
			break;
		}
	}
	
	@Override
	public SoapObject toSoapObject(String namespace){
		SoapObject out = new SoapObject (namespace,"PatientFieldData");
		out.addProperty ("fieldId",fieldId);
		out.addProperty ("value",value);
		return out;
	}

}
