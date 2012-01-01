package com.choschi.memdroid.webservice.requests;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.webservice.interfaces.Result;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;


public class ServerSessionIdRequest extends BackgroundSoapRequest {
	/**
	 * 
	 * Constructor
	 * @param params
	 */
	public ServerSessionIdRequest (SoapRequestParams params){
		super (params);
		this.envelope.bodyOut = null;
	}
	
	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Result result){
		try{
			Client.getInstance().sessionIdReceived ((ServerSessionIdResponse)result);
			return;
		}catch (Exception ex){
			super.onPostExecute(result);
		}
	}
}
