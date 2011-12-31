package com.choschi.memdroid.webservice.requests;

import org.ksoap2.SoapFault;

import com.choschi.memdroid.webservice.interfaces.Result;

public class SoapFaultResponse implements Result{
	
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
