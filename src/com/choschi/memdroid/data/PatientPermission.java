package com.choschi.memdroid.data;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.webservice.requests.ServerGetFormDefinitionResponse.Name;

public class PatientPermission extends FormSoapObjectParser {
	
	private String groupId;
	private String patientPermissionId;
	private String permission;
	
	
	public PatientPermission(SoapObject input) {
		super(input);
	}

	@Override
	protected void saveProperty(String property, Name name) {
		switch (name){
		case PATIENT_PERMISSION_GROUP_ID:
			groupId = property;
			break;
		case PATIENT_PERMISSION_NAME:
			permission = property;
			break;
		case PATIENT_PERMISSION_ID:
			patientPermissionId = property;
			break;
		}
	}

	@Override
	protected void saveObject(SoapObject property, Name name) {

	}
	
	public String getCharacter(){
		return permission;
	}

}
