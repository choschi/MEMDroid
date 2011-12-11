package com.choschi.memdroid.webservice.requests;

import com.choschi.memdroid.webservice.Client;
import com.choschi.memdroid.webservice.Result;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ServerGetListOfFormsRequest extends BackgroundSoapRequest {
	
	public ServerGetListOfFormsRequest(SoapRequestParams params,String sessionId, String language, String studyTypeId, String studyName) {
		super(params);
		request.addProperty ("language",language);
		request.addProperty ("sessionId",sessionId);
		//request.addProperty ("studyId",studyId);
		request.addProperty ("studyTypeId",studyTypeId);
		request.addProperty ("studyName", studyName);
	}
	
	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Result result){
		try{
			Client.getInstance().receivedListOfForms ((ServerGetListOfFormsResponse)result);
			return;
		}catch (Exception ex){
			super.onPostExecute(result);
		}
	}

}
