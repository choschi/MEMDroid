package com.choschi.memdroid.webservice.requests;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.webservice.BackgroundSoapRequest;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ServerLoginRequest extends BackgroundSoapRequest {

	private Boolean state;

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
		envelope.headerOut = null;
	}
	

	@Override
	protected void parseResponse(SoapObject response) {
		String rawState = response.getProperty("response").toString();
		if (rawState.compareTo("true")== 0){
			state = true;
		}else{
			state = false;
		}
	}
	
	@Override
	protected void onPostExecute(Object result){
		try{
			Client.getInstance().serverLoggedIn (state);
			return;
		}catch (Exception ex){
			super.onPostExecute(ex);
		}
	}

}
