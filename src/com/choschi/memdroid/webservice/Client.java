package com.choschi.memdroid.webservice;

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
		this.username = username;
		this.password = password;
		SoapRequestParams params = new SoapRequestParams();
		params.setAction("http://memdoc.webservices.yosemite.www.memdoc.org/MemdocServer/getServerSessionIdRequest");
		params.setMethod("getServerSessionIdRequest");
		params.setNamespace("http://memdoc.webservices.yosemite.www.memdoc.org");
		params.setUrl("http://memdoctest.memdoc.org/memdocWsServer/MemdocServer");
		Log.d (TAG,"init server session id request");
		BackgroundSoapRequest request = new SessionIdRequest(Client.getInstance(),params);
		request.execute(new SoapRequestParams[]{});
		Log.d (TAG,"issued server sesion id request");		
	}
	
	/**
	 * first request was successful -> received server session id , now login to module
	 * @param response
	 */
	
	public void sessionIdReceived(ServerSessionIdResponse response){
		this.sessionId = response.getSessionId();
		log (response.toString());
		SoapRequestParams params = new SoapRequestParams();
		params.setAction("");
		params.setMethod("login");
		params.setNamespace("http://webservice.module.yosemite.www.memdoc.org/");
		params.setUrl("http://195.176.223.108/modulewsserver/MemdocModule.php?wsdl");
		Log.d (TAG,"init module login request");
		BackgroundSoapRequest request = new ModuleLoginRequest(Client.getInstance(),params,username,password,sessionId);
		request.execute(new SoapRequestParams[]{});
		Log.d (TAG,"issued module login request");		
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
		SoapRequestParams params = new SoapRequestParams();
		params.setAction("http://memdoc.webservices.yosemite.www.memdoc.org/MemdocServer/loginRequest");
		params.setMethod("login");
		params.setNamespace("http://memdoc.webservices.yosemite.www.memdoc.org");
		params.setUrl("http://memdoctest.memdoc.org/memdocWsServer/MemdocServer");
		Log.d (TAG,"init server login request");
		BackgroundSoapRequest request = new ServerLoginRequest(Client.getInstance(),params,"memdoctest.memdoc.org",sessionId,signature,userId);
		request.execute(new SoapRequestParams[]{});
		Log.d (TAG,"issued server login request");
	}
	
	public void serverLoggedIn (ServerLoginResponse response){
		
	}
	
	/**
	 * Appens a message text to the console view
	 * @param text
	 */
	
	private void log (String text){
		console.setText (console.getText()+"\n"+text);
	}
}
