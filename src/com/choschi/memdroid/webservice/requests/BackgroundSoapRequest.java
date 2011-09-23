package com.choschi.memdroid.webservice.requests;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.choschi.memdroid.webservice.MemdocSoapSerializationEnvelope;
import com.choschi.memdroid.webservice.Result;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

import android.os.AsyncTask;
import android.util.Log;

public class BackgroundSoapRequest extends AsyncTask<SoapRequestParams, Void, Result> {

	public static String ModuleEndpoint = "http://195.176.223.108/modulewsserver/MemdocModule.php?wsdl";
	public static String ServerEndpoint = "http://memdoctest.memdoc.org/memdocWsServer/MemdocServer";
	public static String ServerNamespace = "http://memdoc.webservices.yosemite.www.memdoc.org/";
	public static String ModuleNamespace= "http://webservice.module.yosemite.www.memdoc.org/";
	public static String ServerBaseAction = "http://memdoc.webservices.yosemite.www.memdoc.org/MemdocServer/";
	
	//protected Client target;
	protected SoapRequestParams parameters;
	protected SoapObject request;
	protected SoapSerializationEnvelope envelope;
	
	/**
	 * Constructor, does the initialisation of the soap envelope 
	 * @param client, reference to the calling instance
	 * @param params
	 */
	
	public BackgroundSoapRequest(SoapRequestParams params){
		this.parameters = params;
		this.request = new SoapObject(parameters.getNamespace(), parameters.getMethod());
		envelope = new MemdocSoapSerializationEnvelope(SoapEnvelope.VER11);
	    envelope.setOutputSoapObject(request);
	    envelope.setAddAdornments(false);
	    envelope.implicitTypes = true;
	}
	
	/**
	 * actually does the webservice call
	 * to really do all the work in the background thread the response rendering is done 
	 * in this thread as well.
	 */
	
	@Override
	protected Result doInBackground(SoapRequestParams... params) {
	    HttpTransportSE httpTransport = new HttpTransportSE(parameters.getUrl());
	    //this is optional, use it if you don't want to use a packet sniffer to check what the sent message was (httpTransport.requestDump)
	    httpTransport.debug = true;
	    Result response = null;
	    try {
	    	httpTransport.call(parameters.getAction(), envelope); //send request
	    	Log.d("Dump",httpTransport.requestDump); //display sent message	    	
	    	Log.d("BackgroundSoapRequestSuccess",envelope.getResponse().toString());
	    	response = Result.factory(envelope.getResponse());
	    }catch (Exception ex){
	    	Log.d("BackgroundSoapRequestFailure",ex.getMessage());
	    }
	    return response;
	}
	
	/**
	 * At this moment I am not sure if this override is used at all, so leave it
	 */
	
	// TODO remove if not necessary anymore 
	@Override
	protected void onPostExecute(Result result){
		Log.d ("onPostExecute", "was called");
	}

}
