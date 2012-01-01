package com.choschi.memdroid.webservice.requests;

import android.util.Log;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.webservice.interfaces.Result;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ModuleLoginRequest extends BackgroundSoapRequest {

	/**
	 * 
	 * @param params
	 * @param username
	 * @param password
	 * @param sessionId
	 */
	
	public ModuleLoginRequest(SoapRequestParams params, String username, String password, String sessionId) {
		super(params);
		request.addProperty ("passWord",password);
		request.addProperty ("serverSessionId",sessionId);
		request.addProperty ("userName",username);
		Log.d ("request", request.toString());
	}
	
	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Result result){
		try{
			Client.getInstance().moduleLoggedIn ((ModuleLoginResponse)result);
			return;
		}catch (Exception ex){
			super.onPostExecute(result);
		}
	}
}
