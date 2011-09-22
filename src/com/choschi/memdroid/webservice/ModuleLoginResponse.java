package com.choschi.memdroid.webservice;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import android.util.Log;

import com.choschi.memdroid.webservice.Result;

/**
 * Wrapper for the Module login request response
 * @author choschi
 *
 */

public class ModuleLoginResponse extends Result {
	
	private String moduleSessionId;
	private String signature;
	private String userId;
	
	/**
	 * render the SoapObject in a more meaningful entity
	 * @param response
	 */
	
	public ModuleLoginResponse(SoapObject response){
		moduleSessionId = ((SoapPrimitive)response.getProperty("moduleSessionId")).toString();
		signature = ((SoapPrimitive)response.getProperty("signature")).toString();
		userId = ((SoapPrimitive)response.getProperty("userId")).toString();
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
	 * convenience override for the log method
	 */
	
	@Override
	public String toString(){
		return "moduleSessionId: "+moduleSessionId;
	}
}
