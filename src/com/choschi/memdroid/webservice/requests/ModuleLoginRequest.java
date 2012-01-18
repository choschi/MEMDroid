package com.choschi.memdroid.webservice.requests;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.data.ModuleLoginData;
import com.choschi.memdroid.webservice.BackgroundSoapRequest;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ModuleLoginRequest extends BackgroundSoapRequest {

	private ModuleLoginData data;
	
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
	}
	
	@Override
	protected void parseResponse(SoapObject response) {
		data = new ModuleLoginData (response);
	}
	
	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Object result){
		try{
			Client.getInstance().moduleLoggedIn (data);
			return;
		}catch (Exception ex){
			super.onPostExecute(ex);
		}
	}

}
