package com.choschi.memdroid.webservice.parameters;

import com.choschi.memdroid.webservice.BackgroundSoapRequest;


/**
 * 
 * container for parameters for a request to the server
 * 
 * @author Christoph Isch
 *
 */

public class ServerRequestParams extends SoapRequestParams {
	
	/**
	 * initiate the right parameters for the server
	 */
	
	public ServerRequestParams (){
		this.soapNamespace = BackgroundSoapRequest.ServerNamespace;
		this.soapUrl = BackgroundSoapRequest.ServerEndpoint;
	}
}
