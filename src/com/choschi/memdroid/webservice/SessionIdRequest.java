package com.choschi.memdroid.webservice;


public class SessionIdRequest extends BackgroundSoapRequest {
	/**
	 * 
	 * @inherit
	 * @param client
	 * @param params
	 */
	public SessionIdRequest (Client client, SoapRequestParams params){
		super (client, params);
		this.envelope.bodyOut = null;
	}
	
	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Result result){
		target.sessionIdReceived ((ServerSessionIdResponse)result);
	}
}
