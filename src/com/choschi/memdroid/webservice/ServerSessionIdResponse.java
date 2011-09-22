package com.choschi.memdroid.webservice;

import org.ksoap2.serialization.SoapPrimitive;

/**
 * wrapper for the Server session id request response
 * @author choschi
 *
 */

public class ServerSessionIdResponse extends Result {
	
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
	 * @return
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
