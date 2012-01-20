package com.choschi.memdroid.webservice.requests;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.data.patient.Patient;
import com.choschi.memdroid.data.patient.PatientFieldData;
import com.choschi.memdroid.webservice.BackgroundSoapRequest;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ModuleGetPatientByIdRequest extends BackgroundSoapRequest {

	private Patient patient;
	
	/**
	 * 
	 * @param params
	 * @param sessionId
	 * @param id
	 */
	
	public ModuleGetPatientByIdRequest(SoapRequestParams params, String sessionId, String id) {
		super(params);
		envelope.addMapping(params.getNamespace(), "PatientFieldData",PatientFieldData.class);
		request.addProperty("moduleSessionId",sessionId);
		request.addProperty("patientId",id);
	}
	
	
	@Override
	protected void parseResponse (SoapObject response){
		if (response != null){
			patient = new Patient (response);
		}
	}


	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Object result){
		try{
			Client.getInstance().receivedSearchedPatient (patient);
			return;
		}catch (Exception ex){
			super.onPostExecute(ex);
		}
	}
	
}
