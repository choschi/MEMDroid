package com.choschi.memdroid.webservice.requests;

import java.util.Arrays;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.data.patient.NewPatient;
import com.choschi.memdroid.data.patient.PatientFieldData;
import com.choschi.memdroid.webservice.BackgroundSoapRequest;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

/**
 * 
 * 
 * @author Christoph Isch
 *
 */

public class ModuleCreateNewPatientRequest extends BackgroundSoapRequest {

	private NewPatient patient;
	private PatientFieldData[] input;
	
	/**
	 *  
	 * @param params
	 * @param moduleSessionId
	 * @param language
	 * @param data
	 * @param departmentId
	 */
	
	public ModuleCreateNewPatientRequest(SoapRequestParams params, String moduleSessionId, String language, PatientFieldData[] data, String departmentId) {
		super(params);
		request.addProperty("moduleSessionId",moduleSessionId);
		request.addProperty("language",language);
		request.addProperty("departmentId",departmentId);
		for (PatientFieldData item : data){
			request.addProperty("patientFieldDatas",item.toSoapObject(params.getNamespace()));
		}
		input = data;
	}

	
	@Override
	protected void parseResponse (SoapObject response){
		patient = new NewPatient (response);
		patient.setPatientFieldDatas(Arrays.asList(input));
	}

	
	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Object result){
		try{
			Client.getInstance().createdPatient (patient);
			return;
		}catch (Exception ex){
			super.onPostExecute(ex);
		}
	}
}
