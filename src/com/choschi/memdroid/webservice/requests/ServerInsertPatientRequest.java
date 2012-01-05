package com.choschi.memdroid.webservice.requests;

import org.ksoap2.serialization.SoapObject;

import android.util.Log;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.data.NewPatient;
import com.choschi.memdroid.data.Patient;
import com.choschi.memdroid.data.PatientFieldData;
import com.choschi.memdroid.webservice.interfaces.Result;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ServerInsertPatientRequest extends BackgroundSoapRequestNew {

	/**
	 * 
	 * @param params
	 * @param moduleId
	 * @param sessionId
	 * @param signature
	 * @param userId
	 */
	
	// The PatientDataFields are available in the NewPatient parameter, but getting them directly by id to fill
	// the country, gender and language field feels very awkward...
	
	public ServerInsertPatientRequest(SoapRequestParams params, String sessionId, String userId, NewPatient patient) {
		super(params);
		request.addProperty ("serverSessionId",sessionId);
		request.addProperty ("userId",userId);
		String country = "ch";
		String gender = "u";
		String language = "de";
		for (PatientFieldData field : patient.getPatientFieldDatas()){
			if (field.getId().compareTo("15") == 0){
				country = field.getValue();
			}
			if (field.getId().compareTo("4") == 0){
				gender = field.getValue();
			}
			if (field.getId().compareTo("19") == 0){
				language = field.getValue();
			}
		}
		request.addProperty ("countryId",country);
		request.addProperty ("departmentId",Client.getInstance().getDepartment().getId());
		request.addProperty ("encryptedId",patient.getEncryptedId());
		request.addProperty ("gender",gender);
		request.addProperty ("hashCodes",patient.getHashCodes());
		request.addProperty ("hashedMrn",patient.getMrn());
		request.addProperty ("language",language);
		request.addProperty ("patientClinicId",patient.getPatientClinicId());
		request.addProperty ("patientId",patient.getId());
		// TODO resolve this issue after reply from mr. abt
		request.addProperty ("perms","");//patient.getPermissions());
		request.addProperty ("yearOfBirth",patient.getYearOfBirth());
		Log.d ("request",request.toString());
		envelope.headerOut = null;
	}
	
	@Override
	protected Result parseResponse (SoapObject response){
		Log.d ("patient created",response.toString());
		//patient = new NewPatient (response);
		return null;
	}
	
	
	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Result result){
		try{
			Client.getInstance().savedPatient ();
			return;
		}catch (Exception ex){
			super.onPostExecute(result);
		}
	}
}
