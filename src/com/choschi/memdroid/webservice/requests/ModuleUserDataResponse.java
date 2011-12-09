package com.choschi.memdroid.webservice.requests;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import android.util.Log;

import com.choschi.memdroid.webservice.Result;

/**
 * Wrapper for the Module login request response
 * @author choschi
 *
 */

public class ModuleUserDataResponse extends Result {
	
	private String clinicId;
	private String clinicName;
	private String countryId;
	private String departmentId;
	private String departmentName;
	
	/**
	 * render the SoapObject in a more meaningful entity
	 * @param response
	 */
	
	public ModuleUserDataResponse(SoapObject response){
		Log.d ("response",response.toString());
		SoapObject item = (SoapObject) response.getProperty("item");
		clinicId = (((SoapPrimitive)item.getProperty("clinicId")).toString());
		clinicName = (((SoapPrimitive)item.getProperty("clinicName")).toString()); 
		countryId = (((SoapPrimitive)item.getProperty("countryId")).toString());
		departmentId = (((SoapPrimitive)item.getProperty("departmentId")).toString());
		departmentName = (((SoapPrimitive)item.getProperty("departmentName")).toString());
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



	/**
	 * @return the departmentId
	 */
	public String getDepartmentId() {
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
		return "Clinic: "+clinicName;
	}
	
	/**
	 * 
	 * @return String with the user information
	 */
	
	public String getForDisplay(){
		return clinicName+"("+countryId+" - "+clinicId+")\n"+departmentName+"("+departmentId+")";
	}
}
