package com.choschi.memdroid.data.patient;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormSoapObjectParser;
import com.choschi.memdroid.interfaces.Serializable;

/**
 * 
 * Contains a PatientPermission data set
 * 
 * @author Christoph Isch
 *
 */

public class PatientPermission extends FormSoapObjectParser implements Serializable {
	
	private String groupId;
	private String patientPermissionId;
	private String permission;
	
	/**
	 * constructor
	 * @param input
	 */
	
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
	
	/**
	 * @return get the permission character
	 */
	
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

	@Override
	public SoapObject toSoapObject(String namespace) {
		SoapObject output = new SoapObject (namespace,"perms");
		output.addProperty("item", groupId);
		output.addProperty("item", patientPermissionId);
		output.addProperty("item", permission);
		return output;
	}
}
