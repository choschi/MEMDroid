package com.choschi.memdroid.webservice.requests;

import com.choschi.memdroid.webservice.Client;
import com.choschi.memdroid.webservice.Result;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ModuleUserDataRequest extends BackgroundSoapRequest {

	/**
	 * Constructor
	 * @param params, inherited from BackgroundSoapRequest
	 * @param username
	 * @param password
	 * @param sessionId, ServerSessionId obtained by call to the Server
	 */
	
	public ModuleUserDataRequest(SoapRequestParams params, String userId) {
		super(params);
		request.addProperty("moduleSessionId",userId);
	}
	
	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Result result){
		try{
			Client.getInstance().receivedUserData ((ModuleUserDataResponse)result);
			return;
		}catch (Exception ex){
			super.onPostExecute(result);
		}
	}
}
