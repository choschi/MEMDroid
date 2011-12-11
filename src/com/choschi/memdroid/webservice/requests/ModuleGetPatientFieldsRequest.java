package com.choschi.memdroid.webservice.requests;

import com.choschi.memdroid.webservice.Client;
import com.choschi.memdroid.webservice.Result;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ModuleGetPatientFieldsRequest extends BackgroundSoapRequest {

	/**
	 * Constructor
	 * @param params, inherited from BackgroundSoapRequest
	 * @param username
	 * @param password
	 * @param sessionId, ServerSessionId obtained by call to the Server
	 */
	
	public ModuleGetPatientFieldsRequest(SoapRequestParams params, String sessionId, String lang, String mode, String deptId) {
		super(params);
		request.addProperty("moduleSessionId",sessionId);
		request.addProperty("language",lang);
		request.addProperty("mode",mode);
		request.addProperty("deptId",deptId);
	}
	
	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Result result){
		try{
			Client.getInstance().receivedPatientFields ((ModuleGetPatientFieldsResponse)result);
			return;
		}catch (Exception ex){
			super.onPostExecute(result);
		}
	}
}
