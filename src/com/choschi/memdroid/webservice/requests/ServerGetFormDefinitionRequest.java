package com.choschi.memdroid.webservice.requests;

import com.choschi.memdroid.webservice.Client;
import com.choschi.memdroid.webservice.Result;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ServerGetFormDefinitionRequest extends BackgroundSoapRequest {
	
	public ServerGetFormDefinitionRequest(SoapRequestParams params,String sessionId, String language, String formId) {
		super(params);
		request.addProperty ("formId",formId);
		request.addProperty ("language",language);
		request.addProperty ("sessionId",sessionId);
	}
	
	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Result result){
		try{
			Client.getInstance().receivedFormDefinition ((ServerGetFormDefinitionResponse)result);
			return;
		}catch (Exception ex){
			super.onPostExecute(result);
		}
	}

}
