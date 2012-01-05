package com.choschi.memdroid.webservice.requests;

import java.util.Arrays;

import org.ksoap2.serialization.SoapObject;

import android.util.Log;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.data.NewPatient;
import com.choschi.memdroid.data.PatientFieldData;
import com.choschi.memdroid.webservice.interfaces.Result;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

/**
 * 
 * 
 * @author Christoph Isch
 *
 */

public class ModuleCreateNewPatientRequest extends BackgroundSoapRequestNew {

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
	protected Result parseResponse (SoapObject response){
		Log.d ("patient created",response.toString());
		patient = new NewPatient (response);
		patient.setPatientFieldDatas(Arrays.asList(input));
		return null;
	}

	
	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Result result){
		try{
			Client.getInstance().createdPatient (patient);
			return;
		}catch (Exception ex){
			super.onPostExecute(result);
		}
	}
}
