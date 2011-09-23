package com.choschi.memdroid.webservice.parameters;

import com.choschi.memdroid.webservice.requests.BackgroundSoapRequest;

public class ServerRequestParams extends SoapRequestParams {
	public ServerRequestParams (){
		this.soapNamespace = BackgroundSoapRequest.ServerNamespace;
		this.soapUrl = BackgroundSoapRequest.ServerEndpoint;
	}
}
