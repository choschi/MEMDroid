package com.choschi.memdroid.webservice.parameters;

/**
 * wrapper for the soap request parameters, abstract so that it can not be instantiated
 *  
 * @author Christoph Isch
 *
 */


public abstract class SoapRequestParams {
	private String soapAction;
	private String soapMethod;
	protected String soapNamespace;
	protected String soapUrl;
	
	/**
	 * 
	 * @param action
	 */
		
	public void setAction(String action){
		this.soapAction = action;
	}
	
	/**
	 * 
	 * @return soap action 
	 */
	
	public String getAction(){
		return soapAction;
	}
	
	/**
	 * 
	 * @param method
	 */
	
	public void setMethod (String method){
		this.soapMethod = method;
	}
	
	/**
	 * 
	 * @return soap method
	 */
	
	public String getMethod() {
		return soapMethod;
	}
	
	/**
	 * 
	 * @return the namespace
	 */
	
	public String getNamespace() {
		return soapNamespace;
	}

	/**
	 * 
	 * @return url as string
	 */
	
	public String getUrl() {
		return soapUrl;
	}
}
