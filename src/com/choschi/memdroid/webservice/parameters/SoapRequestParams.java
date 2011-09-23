package com.choschi.memdroid.webservice.parameters;

/**
 * wrapper for the soap request parameters 
 * @author choschi
 *
 */


// TODO check if this class can be made superfluent by creating parameters directly in the request classes 
// and defining main parts as static values on the BackgroundSoapRequest class

public class SoapRequestParams {
	private String soapAction;
	private String soapMethod;
	protected String soapNamespace;
	protected String soapUrl;
	
	
	/**
	 * getter and setters
	 */
	
	public void setAction(String action){
		this.soapAction = action;
	}
	
	public String getAction(){
		return soapAction;
	}
	
	public void setMethod (String method){
		this.soapMethod = method;
	}

	public String getMethod() {
		return soapMethod;
	}

	public String getNamespace() {
		return soapNamespace;
	}
	@Deprecated
	public void setNamespace(String soapNamespace) {
		this.soapNamespace = soapNamespace;
	}

	public String getUrl() {
		return soapUrl;
	}
	@Deprecated
	public void setUrl(String soapUrl) {
		this.soapUrl = soapUrl;
	}
}
