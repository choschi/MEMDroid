package com.choschi.memdroid.webservice.requests;

import org.ksoap2.serialization.SoapPrimitive;

import com.choschi.memdroid.webservice.interfaces.Result;

/**
 * wrapper for the Server session id request response
 * @author choschi
 *
 */

public class ServerSessionIdResponse implements Result{
	
	private String sessionId;
	
	/**
	 * get the data from the SoapPrimitve
	 * @param response
	 */
	
	public ServerSessionIdResponse (SoapPrimitive response){
		sessionId = response.toString();
	}
	
	/**
	 * get the Server session id
	 * @return String
	 */
	
	public String getSessionId(){
		return this.sessionId;
	}
	
	/**
	 * convenience override for the log method
	 */
	
	@Override
	public String toString(){
		return "ServerSessionId: "+sessionId;
	}
}
