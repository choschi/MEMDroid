package com.choschi.memdroid.webservice.requests;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import android.util.Log;

import com.choschi.memdroid.data.SubForm;
import com.choschi.memdroid.webservice.Result;

/**
 * Wrapper for the Module login request response
 * @author choschi
 *
 */

public class ServerGetFormDefinitionResponse extends Result {
	
	private List<SubForm> subForms;
	
	/**
	 * render the SoapObject in a more meaningful entity
	 * @param response
	 */
	
	public ServerGetFormDefinitionResponse(SoapObject response){
		subForms = new ArrayList<SubForm>();
		for (int i=0;i<response.getPropertyCount();i++){
			Log.d ("rendering",response.getPropertyAsString(i));
			Object property = response.getProperty(i);
			try{
				this.handle((SoapPrimitive)property,i);
				break;
			}catch (Exception ex){
				Log.d ("handling primitive",ex.getMessage());
			}
			try{
				this.handle((SoapObject)property,i);
				break;
			}catch (Exception ex){
				Log.d ("handling object",ex.getMessage());
			}
			//subForms.add(new SubForm (response.getPropertyAsString(i)));
		}
	}
	
	private void handle (SoapPrimitive property, int index){
		Log.d ("primitive handler",property.toString());
	}
	
	private void handle (SoapObject property, int index){
		Log.d ("object handler",property.toString());
	}
	
	
	public List<SubForm> getSubForms(){
		return this.subForms;
	}
}