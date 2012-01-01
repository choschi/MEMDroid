package com.choschi.memdroid.webservice.requests;

import android.util.Log;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.webservice.interfaces.Result;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ServerLoginRequest extends BackgroundSoapRequest {


	/**
	 * 
	 * @param params
	 * @param moduleId
	 * @param sessionId
	 * @param signature
	 * @param userId
	 */
	
	public ServerLoginRequest(SoapRequestParams params, String moduleId, String sessionId, String signature,String userId) {
		super(params);
		request.addProperty ("moduleId",moduleId);
		request.addProperty ("serverSessionId",sessionId);
		request.addProperty ("signature",signature);
		request.addProperty ("userId",userId);
		Log.d ("request",request.toString());
		envelope.headerOut = null;
	}
	
	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Result result){
		try{
			Client.getInstance().serverLoggedIn ((ServerLoginResponse)result);
			return;
		}catch (Exception ex){
			super.onPostExecute(result);
		}
	}
}
