package com.choschi.memdroid.webservice;

public class ServerLoginRequest extends BackgroundSoapRequest {


	/**
	 * Constructor 
	 * @param client, inherited from BackgroundSoapRequest
	 * @param params, inherited from BackgroundSoapRequest
	 * @param server, domain name of the server
	 * @param sessionId, ServerSessionId obtained by call to the Server 
	 * @param signature, signature obtained by call to the Module
	 * @param userId, user id obtained by call to the Module
	 */
	
	public ServerLoginRequest(Client client, SoapRequestParams params, String server, String sessionId, String signature,String userId) {
		super(client, params);
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
		target.serverLoggedIn ((ServerLoginResponse)result);
	}
}
