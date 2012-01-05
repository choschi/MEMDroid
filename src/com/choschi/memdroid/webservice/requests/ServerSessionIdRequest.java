package com.choschi.memdroid.webservice.requests;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.webservice.BackgroundSoapRequest;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;


public class ServerSessionIdRequest extends BackgroundSoapRequest {
	
	private String sessionId;
	
	/**
	 * 
	 * Constructor
	 * @param params
	 */
	public ServerSessionIdRequest (SoapRequestParams params){
		super (params);
		this.envelope.bodyOut = null;
	}


	@Override
	protected void parseResponse(SoapObject response) {
		sessionId = response.getProperty("response").toString();
	}
		
	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Object result){
		try{
			Client.getInstance().sessionIdReceived (sessionId);
			return;
		}catch (Exception ex){
			super.onPostExecute(ex);
		}
	}

}
