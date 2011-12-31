package com.choschi.memdroid.webservice.requests;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.util.Log;

import com.choschi.memdroid.data.PatientField;
import com.choschi.memdroid.data.PatientFieldData;
import com.choschi.memdroid.webservice.Client;
import com.choschi.memdroid.webservice.interfaces.Result;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ModuleSearchPatientRequest extends BackgroundSoapRequestNew {

	private List<PatientField> fields;
	
	/**
	 * Constructor
	 * @param params, inherited from BackgroundSoapRequest
	 * @param username
	 * @param password
	 * @param sessionId, ServerSessionId obtained by call to the Server
	 */
	
	public ModuleSearchPatientRequest(SoapRequestParams params, String sessionId, String lang, PatientFieldData[] search, String deptId) {
		super(params);
		envelope.addMapping(params.getNamespace(), "PatientFieldData",PatientFieldData.class);
		request.addProperty("moduleSessionId",sessionId);
		request.addProperty("departmentId",deptId);
		SoapObject patients = new SoapObject(params.getNamespace(), "patientFieldData");
		for (PatientFieldData data : search){
			patients.addProperty("PatientFieldData",data.toSoapObject(params.getNamespace()));
		}
		request.addProperty("patientFieldData",patients);
	}
	
	
	@Override
	protected Result parseResponse (SoapObject response){
		Log.d ("search patient",response.toString());
		/*
		fields = new ArrayList<PatientField>();
		for (int i=0;i<response.getPropertyCount();i++){
			fields.add(new PatientField((SoapObject) response.getProperty(i)));
		}
		*/
		return null;
	}


	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Result result){
		try{
			Client.getInstance().receivedSearchedPatients ();
			return;
		}catch (Exception ex){
			super.onPostExecute(result);
		}
	}
}
