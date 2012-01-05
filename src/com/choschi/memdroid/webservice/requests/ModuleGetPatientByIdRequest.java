package com.choschi.memdroid.webservice.requests;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.util.Log;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.data.Patient;
import com.choschi.memdroid.data.PatientFieldData;
import com.choschi.memdroid.webservice.interfaces.Result;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ModuleGetPatientByIdRequest extends BackgroundSoapRequestNew {

	private Patient patient;
	
	/**
	 * 
	 * @param params
	 * @param sessionId
	 * @param lang
	 * @param search
	 * @param deptId
	 */
	
	public ModuleGetPatientByIdRequest(SoapRequestParams params, String sessionId, String id) {
		super(params);
		envelope.addMapping(params.getNamespace(), "PatientFieldData",PatientFieldData.class);
		request.addProperty("moduleSessionId",sessionId);
		request.addProperty("patientId",id);
	}
	
	
	@Override
	protected Result parseResponse (SoapObject response){
		Log.d ("patient received",response.toString());
		patient = new Patient (response);
		return null;
	}


	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Result result){
		try{
			Client.getInstance().receivedSearchedPatient (patient);
			return;
		}catch (Exception ex){
			super.onPostExecute(result);
		}
	}
	
}
