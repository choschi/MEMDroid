package com.choschi.memdroid.webservice.requests;

import org.ksoap2.serialization.SoapPrimitive;


import com.choschi.memdroid.webservice.Result;

/**
 * Wrapper for the Module login request response
 * @author choschi
 *
 */

public class ServerLoginResponse extends Result {
	
	private Boolean state;
	
	/**
	 * render the SoapObject in a more meaningful entity
	 * @param soapResponse
	 */
	
	public ServerLoginResponse(SoapPrimitive soapResponse){
		if (soapResponse.toString().compareTo("true")== 0){
			state = true;
		}else{
			state = false;
		}
	}
	
	public Boolean getState(){
		return state;
	}
	
	@Override
	public String toString(){
		if(this.state){
			return "successfully logged in";
		}else{
			return "login failed";
		}
	}
}
