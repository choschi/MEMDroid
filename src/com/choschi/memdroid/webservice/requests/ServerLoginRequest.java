package com.choschi.memdroid.webservice.requests;

import com.choschi.memdroid.webservice.Client;
import com.choschi.memdroid.webservice.Result;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ServerLoginRequest extends BackgroundSoapRequest {


	/**
	 * Constructor 
	 * @param params, inherited from BackgroundSoapRequest
	 * @param server, domain name of the server
	 * @param sessionId, ServerSessionId obtained by call to the Server 
	 * @param signature, signature obtained by call to the Module
	 * @param userId, user id obtained by call to the Module
	 */
	
	public ServerLoginRequest(SoapRequestParams params, String server, String sessionId, String signature,String userId) {
		super(params);
		request.addProperty ("serverAddress",server);
		request.addProperty ("serverSessionId",sessionId);
		request.addProperty ("signature",signature);
		request.addProperty ("userId",userId);
		envelope.headerOut = null;
	}
	
	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Result result){
		Client.getInstance().serverLoggedIn ((ServerLoginResponse)result);
	}
}
