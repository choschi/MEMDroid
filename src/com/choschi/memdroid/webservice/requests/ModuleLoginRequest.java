package com.choschi.memdroid.webservice.requests;

import com.choschi.memdroid.webservice.Client;
import com.choschi.memdroid.webservice.Result;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ModuleLoginRequest extends BackgroundSoapRequest {

	/**
	 * Constructor
	 * @param params, inherited from BackgroundSoapRequest
	 * @param username
	 * @param password
	 * @param sessionId, ServerSessionId obtained by call to the Server
	 */
	
	public ModuleLoginRequest(SoapRequestParams params, String username, String password, String sessionId) {
		super(params);
		request.addProperty ("passWord",password);
		request.addProperty ("serverSessionId",sessionId);
		request.addProperty ("userName",username);
	}
	
	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Result result){
		Client.getInstance().moduleLoggedIn ((ModuleLoginResponse)result);
	}
}
