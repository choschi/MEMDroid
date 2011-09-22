package com.choschi.memdroid.webservice;

public class ModuleLoginRequest extends BackgroundSoapRequest {

	/**
	 * Constructor
	 * @param client, inherited from BackgroundSoapRequest
	 * @param params, inhertied from BackgroundSoapRequest
	 * @param username
	 * @param password
	 * @param sessionId, ServerSessionId obtained by call to the Server
	 */
	
	public ModuleLoginRequest(Client client, SoapRequestParams params, String username, String password, String sessionId) {
		super(client, params);
		request.addProperty ("passWord",password);
		request.addProperty ("serverSessionId",sessionId);
		request.addProperty ("userName",username);
	}
	
	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Result result){
		target.moduleLoggedIn ((ModuleLoginResponse)result);
	}
}
