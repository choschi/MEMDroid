package com.choschi.memdroid.webservice.requests;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.util.Log;

import com.choschi.memdroid.data.Study;
import com.choschi.memdroid.webservice.Result;

/**
 * Wrapper for the Module login request response
 * @author choschi
 *
 */

public class ServerGetListOfStudiesResponse extends Result {
	
	private List<Study> studies;
	
	/**
	 * render the SoapObject in a more meaningful entity
	 * @param response
	 */
	
	public ServerGetListOfStudiesResponse(SoapObject response){
		studies = new ArrayList<Study>();
		for (int i=0;i<response.getPropertyCount();i++){
			//studies.add(new Study (response.getPropertyAsString(i)));
			studies.add(new Study ((SoapObject)response.getProperty(i)));
		}
	}
	
	
	public List<Study> getStudies(){
		return this.studies;
	}
}