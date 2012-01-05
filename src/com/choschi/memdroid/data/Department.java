package com.choschi.memdroid.data;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.interfaces.AdapterItem;

/**
 * 
 * @author Christoph Isch
 * 
 * Represents a department in a clinic
 * 
 */

public class Department extends FormSoapObjectParser implements AdapterItem {

	private String clinicId;
	private String clinicName;
	private String countryId;
	private String departmentId;
	private String departmentName;
	
	/**
	 * constructor
	 * @param response
	 */
	
	
	public Department (SoapObject response){
		super(response);
	}
	

	@Override
	protected void saveProperty(String property, Name name) {
		switch (name){
		case DEPARTMENT_CLINIC_ID:
			clinicId = property;
			break;
		case DEPARTMENT_CLINIC_NAME:
			clinicName = property;
			break;
		case DEPARTMENT_COUNTRY_ID:
			countryId = property;
			break;
		case DEPARTMENT_ID:
			departmentId = property;
			break;
		case DEPARTMENT_NAME:
			departmentName = property;
			break;
		}
	}


	@Override
	protected void saveObject(SoapObject property, Name name) {
	}
	

	/**
	 * @return the clinicId
	 */
	public String getClinicId() {
		return clinicId;
	}



	/**
	 * @return the clinicName
	 */
	public String getClinicName() {
		return clinicName;
	}



	/**
	 * @return the countryId
	 */
	public String getCountryId() {
		return countryId;
	}

	@Override
	public String getId() {
		return departmentId;
	}


	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	
	/**
	 * convenience override for the log method
	 */
	
	@Override
	public String toString(){
		return departmentName;
	}
}
