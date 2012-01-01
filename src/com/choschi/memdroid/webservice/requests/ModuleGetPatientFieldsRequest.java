package com.choschi.memdroid.webservice.requests;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.data.PatientField;
import com.choschi.memdroid.webservice.interfaces.Result;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ModuleGetPatientFieldsRequest extends BackgroundSoapRequestNew {

	private List<PatientField> fields;
	
	/**
	 * 
	 * @param params
	 * @param sessionId
	 * @param lang
	 * @param mode
	 * @param deptId
	 */
	
	public ModuleGetPatientFieldsRequest(SoapRequestParams params, String sessionId, String lang, String mode, String deptId) {
		super(params);
		request.addProperty("moduleSessionId",sessionId);
		request.addProperty("language",lang);
		request.addProperty("mode",mode);
		request.addProperty("deptId",deptId);
	}
	
	
	@Override
	protected Result parseResponse (SoapObject response){
		fields = new ArrayList<PatientField>();
		for (int i=0;i<response.getPropertyCount();i++){
			fields.add(new PatientField((SoapObject) response.getProperty(i)));
		}
		return null;
	}


	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Result result){
		try{
			Client.getInstance().receivedPatientFields (fields);
			return;
		}catch (Exception ex){
			super.onPostExecute(result);
		}
	}
}
