package com.choschi.memdroid.data;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import com.choschi.memdroid.data.interfaces.SpinnerItem;

/**
 * 
 * @author Christoph Isch
 * 
 * Represents a department in a clinic
 * 
 */

public class Department extends SoapObjectParser implements SpinnerItem {

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
		clinicId = (((SoapPrimitive)response.getProperty("clinicId")).toString());
		clinicName = (((SoapPrimitive)response.getProperty("clinicName")).toString()); 
		countryId = (((SoapPrimitive)response.getProperty("countryId")).toString());
		departmentId = (((SoapPrimitive)response.getProperty("departmentId")).toString());
		departmentName = (((SoapPrimitive)response.getProperty("departmentName")).toString());
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
