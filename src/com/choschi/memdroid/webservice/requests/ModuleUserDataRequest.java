package com.choschi.memdroid.webservice.requests;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.data.Department;
import com.choschi.memdroid.webservice.BackgroundSoapRequest;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class ModuleUserDataRequest extends BackgroundSoapRequest {

	private List<Department> departments;
	
	/**
	 * 
	 * @param params
	 * @param userId
	 */
	
	public ModuleUserDataRequest(SoapRequestParams params, String userId) {
		super(params);
		request.addProperty("moduleSessionId",userId);
	}
	
	@Override
	protected void parseResponse(SoapObject response) {
		departments = new ArrayList<Department>();
		for (int i=0; i<response.getPropertyCount();i++){
			departments.add(new Department((SoapObject)response.getProperty(i)));
		}
	}
	
	@Override
	protected void onPostExecute(Object result){
		try{
			Client.getInstance().receivedUserData (departments);
			return;
		}catch (Exception ex){
			super.onPostExecute(ex);
		}
	}

}
