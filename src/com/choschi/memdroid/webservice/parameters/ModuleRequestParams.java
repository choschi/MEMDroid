package com.choschi.memdroid.webservice.parameters;

import com.choschi.memdroid.webservice.requests.BackgroundSoapRequest;

/**
 * 
 * container for parameters for a request to the module server
 * 
 * @author Christoph Isch
 *
 */

public class ModuleRequestParams extends SoapRequestParams {
	
	/**
	 * initiate the right parameters for the module
	 */
	
	public ModuleRequestParams (){
		this.soapNamespace = BackgroundSoapRequest.ModuleNamespace;
		this.soapUrl = BackgroundSoapRequest.ModuleEndpoint;
	}
}
