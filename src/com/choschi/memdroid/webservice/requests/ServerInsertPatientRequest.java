package com.choschi.memdroid.webservice.requests;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.data.patient.NewPatient;
import com.choschi.memdroid.data.patient.PatientFieldData;
import com.choschi.memdroid.data.patient.PatientPermission;
import com.choschi.memdroid.webservice.BackgroundSoapRequest;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ServerInsertPatientRequest extends BackgroundSoapRequest {
	
	private boolean state;
	
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
	
	public ServerInsertPatientRequest(SoapRequestParams params, String sessionId, String userId, String departmentId, NewPatient patient) {
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
		request.addProperty ("departmentId",departmentId);
		request.addProperty ("encryptedId",patient.getEncryptedId());
		request.addProperty ("gender",gender);
		request.addProperty ("hashCodes",patient.getHashCodes());
		request.addProperty ("hashedMrn",patient.getMrn());
		request.addProperty ("language",language);
		request.addProperty ("patientClinicId",patient.getPatientClinicId());
		request.addProperty ("patientId",patient.getId());
		for (PatientPermission perm : patient.getPermissions()){
			request.addProperty("perms",perm.toSoapObject(params.getNamespace()));
		}
		request.addProperty ("perms","");//patient.getPermissions());
		request.addProperty ("yearOfBirth",patient.getYearOfBirth());
		envelope.headerOut = null;
	}
	
	@Override
	protected void parseResponse (SoapObject response){
		state = Boolean.parseBoolean(response.toString());
	}
	
	
	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Object result){
		try{
			Client.getInstance().savedPatient (state);
			return;
		}catch (Exception ex){
			super.onPostExecute(ex);
		}
	}
}
