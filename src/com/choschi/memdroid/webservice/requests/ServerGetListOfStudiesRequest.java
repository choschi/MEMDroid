package com.choschi.memdroid.webservice.requests;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.webservice.interfaces.Result;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ServerGetListOfStudiesRequest extends BackgroundSoapRequest {
	
	public ServerGetListOfStudiesRequest(SoapRequestParams params,String sessionId, String language, String studyType) {
		super(params);
		request.addProperty ("language",language);
		request.addProperty ("serverSessionId",sessionId);
		request.addProperty ("studyTypeId",studyType);
	}
	
	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Result result){
		try{
			Client.getInstance().receivedListOfStudies ((ServerGetListOfStudiesResponse)result);
			return;
		}catch (Exception ex){
			super.onPostExecute(result);
		}
	}

}
