package com.choschi.memdroid.webservice.requests;

import org.ksoap2.SoapFault;

import com.choschi.memdroid.webservice.Result;

public class SoapFaultResponse extends Result{
	
	private String message;
	
	public SoapFaultResponse (SoapFault original){
		message = original.getMessage();
	}
	
	public SoapFaultResponse(Exception original){
		message = original.getMessage();
	}
	
	@Override
	public String toString(){
		return message;		
	}
}
