package com.choschi.memdroid.data;

import org.ksoap2.serialization.SoapObject;

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

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the patientPermissionId
	 */
	public String getPatientPermissionId() {
		return patientPermissionId;
	}

	/**
	 * @param patientPermissionId the patientPermissionId to set
	 */
	public void setPatientPermissionId(String patientPermissionId) {
		this.patientPermissionId = patientPermissionId;
	}

}
