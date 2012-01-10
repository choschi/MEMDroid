package com.choschi.memdroid.webservice.requests;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.data.form.Form;
import com.choschi.memdroid.webservice.BackgroundSoapRequest;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ServerGetListOfFormsRequest extends BackgroundSoapRequest {
	
	private List<Form> forms;
	
	public ServerGetListOfFormsRequest(SoapRequestParams params,String sessionId, String language, String studyTypeId, String studyName) {
		super(params);
		request.addProperty ("language",language);
		request.addProperty ("serverSessionId",sessionId);
		request.addProperty ("studyName", studyName);
	}
	
	@Override
	protected void parseResponse(SoapObject response) {
		forms = new ArrayList<Form>();
		if (response.getPropertyCount()>0){
			for (int i=0;i<response.getPropertyCount();i++){
				forms.add(new Form ((SoapObject)response.getProperty(i)));
			}
		}
	}
	
	@Override
	protected void onPostExecute(Object result){
		try{
			Client.getInstance().receivedListOfForms (forms);
			return;
		}catch (Exception ex){
			super.onPostExecute(ex);
		}
	}


}
