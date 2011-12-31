package com.choschi.memdroid.webservice.requests;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.PatientFieldData;
import com.choschi.memdroid.webservice.Client;
import com.choschi.memdroid.webservice.interfaces.Result;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ModuleCreateNewPatientRequest extends BackgroundSoapRequestNew {

	/**
	 * Constructor
	 * @param params, inherited from BackgroundSoapRequest
	 * @param username
	 * @param password
	 * @param sessionId, ServerSessionId obtained by call to the Server
	 */
	
	public ModuleCreateNewPatientRequest(SoapRequestParams params, String moduleSessionId, String language, PatientFieldData[] data, String departmentId) {
		super(params);
		request.addProperty("moduleSessionId",moduleSessionId);
		request.addProperty("language",language);
		request.addProperty("deptId",departmentId);
		SoapObject toSave = new SoapObject(params.getNamespace(), "patientFieldData");
		for (PatientFieldData item : data){
			toSave.addProperty("PatientFieldData",item.toSoapObject(params.getNamespace()));
		}
		request.addProperty("patientFieldData",toSave);
	}

	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Result result){
		try{
			Client.getInstance().createdPatient ((ModuleCreateNewPatientResponse)result);
			return;
		}catch (Exception ex){
			super.onPostExecute(result);
		}
	}
}
