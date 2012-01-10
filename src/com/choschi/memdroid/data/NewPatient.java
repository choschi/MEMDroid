package com.choschi.memdroid.data;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

public class NewPatient extends Patient{
	
	
	private String encryptedId;
	private String mrn;
	private List<PatientPermission> permissions;
	private String yearOfBirth;
	private String[] stringPermissions;
	
	
	public NewPatient (SoapObject response){
		super(response);
	}

	@Override
	protected void saveProperty(String property, Name name) {
		super.saveProperty(property,name);
		switch (name){
		case PATIENT_ENCRYPTED_ID:
			encryptedId = property;
		break;
		case PATIENT_MRN:
			mrn = property;
			break;
		case PATIENT_YEAR_OF_BIRTH:
			yearOfBirth = property;
		}
	}

	@Override
	protected void saveObject(SoapObject property, Name name) {
		super.saveObject(property, name);
		switch (name){
		case PATIENT_PERMISSION:
			if (permissions == null){
				permissions = new ArrayList<PatientPermission>();
			}
			permissions.add(new PatientPermission(property));
		}
	}

	/**
	 * @return the encryptedId
	 */
	public String getEncryptedId() {
		return encryptedId;
	}

	/**
	 * @param encryptedId the encryptedId to set
	 */
	public void setEncryptedId(String encryptedId) {
		this.encryptedId = encryptedId;
	}

	/**
	 * @return the mrn
	 */
	public String getMrn() {
		return mrn;
	}

	/**
	 * @param mrn the mrn to set
	 */
	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	/**
	 * @return the yearOfBirth
	 */
	public String getYearOfBirth() {
		return yearOfBirth;
	}

	/**
	 * @param yearOfBirth the yearOfBirth to set
	 */
	public void setYearOfBirth(String yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	@Deprecated
	public String[] getPermissionsOld(){
		if (stringPermissions == null){
			stringPermissions = new String[permissions.size()];
			int counter = 0;
			for (PatientPermission permission : permissions){
				stringPermissions[counter] = permission.getCharacter();
				counter++;
			}
		}
		return stringPermissions;
	}
	
	public List<PatientPermission> getPermissions(){
		return permissions;
	}
	
}
