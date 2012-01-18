package com.choschi.memdroid.webservice.requests;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.data.Study;
import com.choschi.memdroid.webservice.BackgroundSoapRequest;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ServerGetListOfStudiesRequest extends BackgroundSoapRequest {
	
	List<Study> studies;
	
	/**
	 * 
	 * @param params
	 * @param sessionId
	 * @param language
	 * @param studyType
	 */
	
	public ServerGetListOfStudiesRequest(SoapRequestParams params,String sessionId, String language, String studyType) {
		super(params);
		request.addProperty ("language",language);
		request.addProperty ("serverSessionId",sessionId);
	}
	

	@Override
	protected void parseResponse(SoapObject response) {
		studies = new ArrayList<Study>();
		for (int i=0;i<response.getPropertyCount();i++){
			studies.add(new Study ((SoapObject)response.getProperty(i)));
		}		
	}
	
	@Override
	protected void onPostExecute(Object result){
		try{
			Client.getInstance().receivedListOfStudies (studies);
			return;
		}catch (Exception ex){
			super.onPostExecute(ex);
		}
	}


}
