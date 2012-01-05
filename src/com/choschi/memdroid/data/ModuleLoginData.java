package com.choschi.memdroid.data;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import android.util.Log;

/**
 * Wrapper for the Module login request response
 * @author choschi
 *
 */

public class ModuleLoginData{
	
	private String moduleSessionId;
	private String signature;
	private String userId;
	private String moduleId;
	
	/**
	 * render the SoapObject in a more meaningful entity
	 * @param response
	 */
	
	public ModuleLoginData(SoapObject response){
		Log.d ("reponse",response.toString());
		moduleSessionId = ((SoapPrimitive)response.getProperty("moduleSessionId")).toString();
		signature = ((SoapPrimitive)response.getProperty("signature")).toString();
		userId = ((SoapPrimitive)response.getProperty("userId")).toString();
		moduleId = (((SoapPrimitive)response.getProperty("moduleId")).toString());
	}
	
	/**
	 * get the module session id
	 * @return module session id
	 */
	
	public String getModuleSessionId (){
		return this.moduleSessionId;
	}
	
	/**
	 * get the signature returned by the module
	 * @return signature
	 */
	
	public String getSignature (){
		return this.signature;
	}
	
	/**
	 * get the user id returned by the module 
	 * @return user id
	 */
	
	public String getUserId (){
		return this.userId;
	}
	
	/**
	 * @return the moduleId
	 */
	public String getModuleId() {
		return moduleId;
	}
	
	/**
	 * convenience override for the log method
	 */
	
	@Override
	public String toString(){
		return "ModuleSessionId: "+moduleSessionId;
	}


}
