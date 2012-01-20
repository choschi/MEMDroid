package com.choschi.memdroid.webservice.requests;

import org.ksoap2.serialization.SoapObject;

import android.util.Log;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.data.form.Form;
import com.choschi.memdroid.data.form.FormAnswer;
import com.choschi.memdroid.data.form.SubForm;
import com.choschi.memdroid.webservice.BackgroundSoapRequest;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ServerInsertFormRequest extends BackgroundSoapRequest {
	
	//private boolean state;
	
	/**
	 * 
	 * @param params
	 * @param sessionId
	 * @param userId
	 * @param departmentId
	 * @param patientId
	 * @param form
	 * @param subform
	 * @param answers
	 */
	
	public ServerInsertFormRequest(SoapRequestParams params, String sessionId, String userId, String departmentId, String patientId, Form form, SubForm subform, FormAnswer[] answers) {
		super(params);
		request.addProperty ("serverSessionId",sessionId);
		request.addProperty ("creatorId",userId);
		request.addProperty ("deptId",departmentId);
		SoapObject formData = new SoapObject(params.getNamespace(), "formData");
		formData.addProperty ("formId",form.getId());
		formData.addProperty ("patientId",patientId);
		SoapObject subformData = new SoapObject (params.getNamespace(),"subformData");
		subformData.addProperty ("subformId",subform.getId());
		for (FormAnswer item : answers){
			if (item != null){
				subformData.addProperty("answers",item.toSoapObject(params.getNamespace()));
			}
		}
		formData.addProperty("subformData",subformData);
		request.addProperty("formData",formData);
		envelope.headerOut = null;
	}
	
	@Override
	protected void parseResponse (SoapObject response){
		Log.d ("form inserted",response.toString());
	}
	
	
	/**
	 * go back to the UI thread and tell it what to do
	 */
	
	@Override
	protected void onPostExecute(Object result){
		try{
			Client.getInstance().savedForm (result);
			return;
		}catch (Exception ex){
			super.onPostExecute(ex);
		}
	}
}
