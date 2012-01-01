package com.choschi.memdroid.webservice.requests;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import android.util.Log;

import com.choschi.memdroid.data.Department;
import com.choschi.memdroid.webservice.interfaces.Result;

/**
 * Wrapper for the Module login request response
 * @author choschi
 *
 */

public class ModuleUserDataResponse implements Result{
	
	private List<Department> departments;
	
	/**
	 * render the SoapObject in a more meaningful entity
	 * @param response
	 */
	
	public ModuleUserDataResponse(SoapObject response){
		Log.d ("response",response.toString());
		departments = new ArrayList<Department>();
		for (int i=0; i<response.getPropertyCount();i++){
			departments.add(new Department((SoapObject)response.getProperty(i)));
		}
	}

	
	public List<Department> getDepartments (){
		return departments;
	}
	
	/**
	 * 
	 * @return String with the user information
	 */
	@Deprecated
	public String getForDisplay(){
		return "";
	}
}
