package com.choschi.memdroid.data;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.interfaces.AdapterItem;

public class Patient extends FormSoapObjectParser implements AdapterItem{
	
	private String hashCodes; 
	private int patientClinicId; 
	private List<PatientFieldData> patientFieldDatas;  
	private String patientId; 
	
	
	public Patient (SoapObject response){
		super(response);
	}

	@Override
	protected void saveProperty(String property, Name name) {
		switch (name){
		case PATIENT_HASH_CODE:
			hashCodes = property;
			break;
		case PATIENT_CLINIC_ID:
			patientClinicId = parseInteger(property);
			break;
		case PATIENT_ID:
			patientId = property;
			break;
		}
	}

	@Override
	protected void saveObject(SoapObject property, Name name) {
		switch (name){
		case PATIENT_FIELD_DATA:
			if (patientFieldDatas == null){
				patientFieldDatas = new ArrayList<PatientFieldData>();
			}
			patientFieldDatas.add (new PatientFieldData(property));
		break;
		case PATIENT_ITEM:
			parseObject(property);
			break;
		}
	}

	@Override
	public String getId() {
		return patientId;
	}
	
	@Override
	public String toString(){
		return "Patient";
	}

	/**
	 * @return the hashCodes
	 */
	public String getHashCodes() {
		return hashCodes;
	}

	/**
	 * @param hashCodes the hashCodes to set
	 */
	public void setHashCodes(String hashCodes) {
		this.hashCodes = hashCodes;
	}

	/**
	 * @return the patientClinicId
	 */
	public int getPatientClinicId() {
		return patientClinicId;
	}

	/**
	 * @param patientClinicId the patientClinicId to set
	 */
	public void setPatientClinicId(int patientClinicId) {
		this.patientClinicId = patientClinicId;
	}
	
	public List<PatientFieldData> getPatientFieldDatas() {
		return patientFieldDatas;
	}

	public void setPatientFieldDatas(List<PatientFieldData> patientFieldDatas) {
		this.patientFieldDatas = patientFieldDatas;
	}

	
}
