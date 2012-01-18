package com.choschi.memdroid.webservice.requests;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.data.form.FormDefinition;
import com.choschi.memdroid.webservice.BackgroundSoapRequest;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ServerGetFormDefinitionRequest extends BackgroundSoapRequest {
	
	private FormDefinition definition;
	
	/**
	 * 
	 * @param params
	 * @param sessionId
	 * @param language
	 * @param formName
	 * @param version
	 */
	
	public ServerGetFormDefinitionRequest(SoapRequestParams params,String sessionId, String language, String formName, String version) {
		super(params);
		request.addProperty ("formName",formName);
		request.addProperty ("language",language);
		request.addProperty ("serverSessionId",sessionId);
		request.addProperty ("version", version);
	}

	@Override
	protected void parseResponse(SoapObject response) {
		definition = new FormDefinition(response);
	}
	
	@Override
	protected void onPostExecute(Object result){
		try{
			Client.getInstance().receivedFormDefinition (definition);
			return;
		}catch (Exception ex){
			super.onPostExecute(ex);
		}
	}

}
