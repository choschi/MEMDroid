package com.choschi.memdroid.webservice.requests;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

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
		//studies = new ArrayList<Study>();
		//Object[] elementData = (Object[]) response.getProperty("elementData");
		//Object elementData = response.getPropertySafely("elementData");
		//elementData.toString();
		/*
		for (int i=0;i<elementData.length;i++){
			if (elementData[i]!= null){
				Study study = new Study(elementData[i]);
				studies.add(study);
			}
		}
		*/
	}
	
	
	
	@Override
	public String toString(){
		return "ServerGetListOfStudiesResponse.toString() not implemented yet";
	}
}