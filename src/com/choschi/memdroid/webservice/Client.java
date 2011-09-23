package com.choschi.memdroid.webservice;

import com.choschi.memdroid.webservice.parameters.ModuleRequestParams;
import com.choschi.memdroid.webservice.parameters.ServerRequestParams;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;
import com.choschi.memdroid.webservice.requests.BackgroundSoapRequest;
import com.choschi.memdroid.webservice.requests.ModuleLoginRequest;
import com.choschi.memdroid.webservice.requests.ModuleLoginResponse;
import com.choschi.memdroid.webservice.requests.ServerLoginRequest;
import com.choschi.memdroid.webservice.requests.ServerLoginResponse;
import com.choschi.memdroid.webservice.requests.ServerSessionIdRequest;
import com.choschi.memdroid.webservice.requests.ServerSessionIdResponse;

import android.util.Log;
import android.widget.TextView;

/**
 * 
 * @author choschi
 *
 * Handles all the requests to the webservices
 * to prevent double login and other issuers of the same nature it is implemented as singleton
 * 
 */


public class Client {
	
	private static Client instance = new Client();
	 
    /**
     * private constructor, only class methods can create an instance of the class
     * 
     */
    private Client() {}
 
    /**
     * static method: returns the sole instance of the class
     * 
     */
    public static Client getInstance() {
        return instance;
    }
	
    /**
     * Tag is used for the Log methods as origin name 
     */
    
	private static final String TAG = "Client";
	
	/**
	 * variables needed in "session" management
	 */
	
	private String username;
	private String password;
	private String sessionId;
	private String userId;
	private String signature;
	private String moduleSessionId;
	
	private Boolean loggedIn = false;
	
	/**
	 * console, may be used while developing to show on screen info to the tablet user
	 */
	
	private TextView console; 
	
	public void setConsole (TextView target){
		console = target;
	}
	
	/**
	 * initiates the login process cascade of requests
	 * @param username
	 * @param password
	 */
	
	public void login (String username, String password){
		if (!loggedIn){
			log ("trying to login to the servers");
			this.username = username;
			this.password = password;
			SoapRequestParams params = new ServerRequestParams();
			params.setAction(BackgroundSoapRequest.ServerBaseAction+"getServerSessionIdRequest");
			params.setMethod("getServerSessionIdRequest");
			logcat ("init server session id request");
			BackgroundSoapRequest request = new ServerSessionIdRequest(params);
			request.execute(new SoapRequestParams[]{});
			logcat ("issued server sesion id request");
		}else{
			log ("already logged in");
		}
	}
	
	/**
	 * first request was successful -> received server session id , now login to module
	 * @param response
	 */
	
	public void sessionIdReceived(ServerSessionIdResponse response){
		this.sessionId = response.getSessionId();
		log (response.toString());
		SoapRequestParams params = new ModuleRequestParams();
		params.setAction("");
		params.setMethod("login");
		logcat ("init module login request");
		BackgroundSoapRequest request = new ModuleLoginRequest(params,username,password,sessionId);
		request.execute(new SoapRequestParams[]{});
		logcat ("issued module login request");		
	}
	
	/**
	 * second step successful -> received module session id after user has been verified by module 
	 * issue the first and last step of the login process
	 * @param response
	 */
	
	public void moduleLoggedIn (ModuleLoginResponse response){
		this.userId = response.getUserId();
		this.moduleSessionId = response.getModuleSessionId();
		this.signature = response.getSignature();
		log (response.toString());
		SoapRequestParams params = new ServerRequestParams();
		params.setAction(BackgroundSoapRequest.ServerBaseAction+"loginRequest");
		params.setMethod("login");
		logcat ("init server login request");
		BackgroundSoapRequest request = new ServerLoginRequest(params,"memdoctest.memdoc.org",sessionId,signature,userId);
		request.execute(new SoapRequestParams[]{});
		logcat("issued server login request");
	}
	
	/**
	 * last step successful -> set the logged in flag
	 * tell the UI to display the after login screen
	 * @param response
	 */
	
	public void serverLoggedIn (ServerLoginResponse response){
		this.loggedIn = response.getState();
		log (response.toString());
		// TODO tell the UI to show after login screen
	}
	
	/**
	 * Appends a message text to the console view
	 * @param text
	 */
	
	private void log (String text){
		console.setText (console.getText()+"\n"+text);
	}
	
	private void logcat(String text){
		Log.d (TAG,text);
	}
}
