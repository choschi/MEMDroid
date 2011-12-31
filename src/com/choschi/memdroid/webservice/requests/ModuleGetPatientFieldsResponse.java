package com.choschi.memdroid.webservice.requests;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.util.Log;

import com.choschi.memdroid.data.PatientField;
import com.choschi.memdroid.webservice.interfaces.Result;

/**
 * Wrapper for the Module login request response
 * @author choschi
 *
 */

public class ModuleGetPatientFieldsResponse implements Result {
	
	private List<PatientField> fields;
	
	/**
	 * render the SoapObject in a more meaningful entity
	 * @param response
	 */
	
	public ModuleGetPatientFieldsResponse(SoapObject response){
		this.fields = new ArrayList<PatientField>();
		Log.d ("response",response.toString());
		for (int i = 0; i< response.getPropertyCount();i++){
			SoapObject item = (SoapObject) response.getProperty(i);
			try {
				fields.add(new PatientField (item));
			}catch (Exception ex){
				Log.d ("parser","darn exception"+ex.getMessage());
			}
		}
	}
	
	public List<PatientField> getFields(){
		return fields;
	}
}
