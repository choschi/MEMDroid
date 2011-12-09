package com.choschi.memdroid.webservice.requests;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.util.Log;

import com.choschi.memdroid.data.Form;
import com.choschi.memdroid.webservice.Result;

/**
 * Wrapper for the Module login request response
 * @author choschi
 *
 */

public class ServerGetListOfFormsResponse extends Result {
	
	private List<Form> forms;
	
	/**
	 * render the SoapObject in a more meaningful entity
	 * @param response
	 */
	
	public ServerGetListOfFormsResponse(SoapObject response){
		forms = new ArrayList<Form>();
		for (int i=0;i<response.getPropertyCount();i++){
			forms.add(new Form (response.getPropertyAsString(i)));
		}
	}
	
	
	public List<Form> getForms(){
		return this.forms;
	}
}