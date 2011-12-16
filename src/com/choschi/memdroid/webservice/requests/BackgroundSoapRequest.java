package com.choschi.memdroid.webservice.requests;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.util.Log;

import com.choschi.memdroid.webservice.Client;
import com.choschi.memdroid.webservice.MemdocSoapSerializationEnvelope;
import com.choschi.memdroid.webservice.Result;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

public class BackgroundSoapRequest extends AsyncTask<SoapRequestParams, Void, Result> {

/**
 * Final endpoints to use for developing use the test server ones...
 */


//	public static String ModuleEndpoint = "https://demomodule.memdoc.org/moduleWsServer/MemdocModule.php?wsdl";
//	public static String ServerEndpoint = "https://memdocdemo.memdoc.org/memdocWsServer/MemdocServer?wsdl";

	
	public static String ModuleEndpoint = "http://195.176.223.108/moduleWsServer/MemdocModule.php?wsdl";
	public static String ServerEndpoint = "http://memdoctest.memdoc.org/memdocWsServer/MemdocServer";
	public static String ServerNamespace = "http://memdoc.webservices.yosemite.www.memdoc.org/";
	public static String ModuleNamespace= "http://webservice.module.yosemite.www.memdoc.org/";
	public static String ServerBaseAction = "http://memdoc.webservices.yosemite.www.memdoc.org/MemdocServer/";
	
	protected SoapRequestParams parameters;
	protected SoapObject request;
	protected MemdocSoapSerializationEnvelope envelope;
	
	
	/**
	 * Constructor, does the initialisation of the soap envelope 
	 * @param client, reference to the calling instance
	 * @param params
	 */
	
	public BackgroundSoapRequest(SoapRequestParams params){
		this.parameters = params;
		this.request = new SoapObject(parameters.getNamespace(), parameters.getMethod());
		this.envelope = new MemdocSoapSerializationEnvelope(SoapEnvelope.VER11);
	    this.envelope.setOutputSoapObject(request);
	    this.envelope.setAddAdornments(false);
	    this.envelope.implicitTypes = true;
	    this.envelope.dotNet = true;
	}
	
	/**
	 * actually does the webservice call
	 * 
	 * to really do all the work in the background thread the response rendering is done 
	 * in this thread as well.
	 */
	
	@Override
	protected Result doInBackground(SoapRequestParams... params) {
	    HttpTransportSE httpTransport = new HttpTransportSE(parameters.getUrl());
	    httpTransport.debug = true;
	    Result response = null;
	    try {
	    	httpTransport.call(parameters.getAction(), envelope);
	    	Log.d ("request: ", httpTransport.requestDump);
	    	Log.d ("response: ", httpTransport.responseDump);
	    	response = Result.factory(envelope.getResponse());	
	    }catch (Exception ex){
	    	response = Result.factory(ex);
	    }
	    return response;
	}
	
	/**
	 * if an error in the soap request itself occurred inform the client about the error. 
	 */
	
	@Override
	protected void onPostExecute(Result result){
		try{
			Log.d ("erronous result", result.toString());
			Client.getInstance().handleFault((SoapFaultResponse)result);
		}catch (Exception ex){
			Log.d ("BackgroundSoapRequest",ex.getMessage());
		}
	}

}
