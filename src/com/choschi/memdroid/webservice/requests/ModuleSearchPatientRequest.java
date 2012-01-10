package com.choschi.memdroid.webservice.requests;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.util.Log;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.data.PatientFieldData;
import com.choschi.memdroid.webservice.BackgroundSoapRequest;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ModuleSearchPatientRequest extends BackgroundSoapRequest {

	private List<String> patientIds;
	
	/**
	 * 
	 * @param params
	 * @param sessionId
	 * @param lang
	 * @param search
	 * @param deptId
	 */
	
	public ModuleSearchPatientRequest(SoapRequestParams params, String sessionId, String lang, PatientFieldData[] search, String deptId) {
		super(params);
		envelope.addMapping(params.getNamespace(), "PatientFieldData",PatientFieldData.class);
		request.addProperty("moduleSessionId",sessionId);
		request.addProperty("departmentId",deptId);
		//SoapObject patients = new SoapObject(params.getNamespace(), "patientFieldData");
		for (PatientFieldData data : search){
			request.addProperty("patientFieldData",data.toSoapObject(params.getNamespace()));
		}
		//request.addProperty("patientFieldData",patients);
	}
	
	
	@Override
	protected void parseResponse (SoapObject response){
		Log.d ("search patient",response.toString());
		patientIds = new ArrayList<String>();
		for (int i=0;i<response.getPropertyCount();i++){
			patientIds.add(response.getProperty(i).toString());
		}
	}


	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Object result){
		try{
			Client.getInstance().receivedSearchedPatients (patientIds);
			return;
		}catch (Exception ex){
			super.onPostExecute(ex);
		}
	}
	
}
