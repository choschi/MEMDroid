package com.choschi.memdroid.webservice.parameters;

import com.choschi.memdroid.webservice.requests.BackgroundSoapRequest;

public class ModuleRequestParams extends SoapRequestParams {
	public ModuleRequestParams (){
		this.soapNamespace = BackgroundSoapRequest.ModuleNamespace;
		this.soapUrl = BackgroundSoapRequest.ModuleEndpoint;
	}
}
