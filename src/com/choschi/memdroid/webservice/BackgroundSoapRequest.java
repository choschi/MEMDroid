package com.choschi.memdroid.webservice;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.os.AsyncTask;
import android.util.Log;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;

/**
 * 
 * Base class for web service calls that extends AsyncTask to be able to run in a background thread
 * 
 * @author Christoph Isch
 *
 */

public abstract class BackgroundSoapRequest extends AsyncTask<SoapRequestParams, Void, Object> {

	/**
	 * Final endpoints to use in production environment, for developing use the test server ones...
	 */


//	public static String ModuleEndpoint = "https://demomodule.memdoc.org/moduleWsServer/MemdocModule.php?wsdl";
//	public static String ServerEndpoint = "https://memdocdemo.memdoc.org/memdocWsServer/MemdocServer?wsdl";

	/**
	 * test server endpoints
	 */
	
	public static String ModuleEndpoint = "http://195.176.223.108/moduleWsServer/MemdocModule.php?wsdl";
	public static String ServerEndpoint = "http://memdoctest.memdoc.org/memdocWsServer/MemdocServer";
	public static String ServerNamespace = "http://memdoc.webservices.yosemite.www.memdoc.org/";
	public static String ModuleNamespace= "http://webservice.module.yosemite.www.memdoc.org/";
	public static String ServerBaseAction = "http://memdoc.webservices.yosemite.www.memdoc.org/MemdocServer/";
	
	protected SoapRequestParams parameters;
	protected SoapObject request;
	protected MemdocSoapSerializationEnvelope envelope;
	protected boolean isResultVector = false;
	
	/**
	 * Constructor, does the initialisation of the soap envelope 
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
	 * actually does the web-service call
	 * 
	 * to really do all the work in the background thread the response rendering is done 
	 * in this thread as well.
	 * @return 
	 */
	
	@Override
	protected Object doInBackground(SoapRequestParams... params) {
		 HttpTransportSE httpTransport = new HttpTransportSE(parameters.getUrl());
		 httpTransport.debug = true;
		 
		 Object result = null;
		 
        // call and Parse Result.
        try
        {
            httpTransport.call(parameters.getAction(), envelope);
            if (!isResultVector)
            {
                result = envelope.getResponse();
            } else
            {
                result = envelope.bodyIn;
            }
        } catch (final IOException e)
        {
            logcat(e.getMessage());
        } catch (final XmlPullParserException e)
        {
        	logcat(e.getMessage());
        } catch (final Exception e)
        {
        	logcat(e.getMessage());
        }
        
        if (result != null)
        {
        	// this is only necessary because the server in 2 cases only sends a primitive answer rather than a meaningful object...
        	if (result instanceof SoapPrimitive){
        		SoapObject newResult = new SoapObject (parameters.getNamespace(),"response");
        		newResult.addProperty("response",result.toString());
        		parseResponse(newResult);
        	}else if (result instanceof SoapFault){
        		logcat (((SoapFault)result).toString());
        		parseResponse (null);
        	}else{
        		parseResponse((SoapObject) result);
        	}
        }
        return null;
	}
	
	protected abstract void parseResponse (SoapObject response);
	
	/**
	 * if an error in the soap request itself occurred inform the client about the error. 
	 */
	
	@Override
	protected void onPostExecute(Object result){
		Client.getInstance().handleError((Exception)result);
	}
	
	private void logcat(String message){
		Log.i ("BackgroundSoapRequest",message);
	}

}
