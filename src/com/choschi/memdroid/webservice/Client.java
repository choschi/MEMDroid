package com.choschi.memdroid.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.choschi.memdroid.data.Form;
import com.choschi.memdroid.data.Study;
import com.choschi.memdroid.util.ClientListener;
import com.choschi.memdroid.util.SHA256;
import com.choschi.memdroid.webservice.parameters.ModuleRequestParams;
import com.choschi.memdroid.webservice.parameters.ServerRequestParams;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;
import com.choschi.memdroid.webservice.requests.BackgroundSoapRequest;
import com.choschi.memdroid.webservice.requests.ModuleLoginRequest;
import com.choschi.memdroid.webservice.requests.ModuleLoginResponse;
import com.choschi.memdroid.webservice.requests.ModuleUserDataRequest;
import com.choschi.memdroid.webservice.requests.ModuleUserDataResponse;
import com.choschi.memdroid.webservice.requests.ServerGetFormDefinitionRequest;
import com.choschi.memdroid.webservice.requests.ServerGetFormDefinitionResponse;
import com.choschi.memdroid.webservice.requests.ServerGetListOfFormsRequest;
import com.choschi.memdroid.webservice.requests.ServerGetListOfFormsResponse;
import com.choschi.memdroid.webservice.requests.ServerGetListOfStudiesRequest;
import com.choschi.memdroid.webservice.requests.ServerGetListOfStudiesResponse;
import com.choschi.memdroid.webservice.requests.ServerLoginRequest;
import com.choschi.memdroid.webservice.requests.ServerLoginResponse;
import com.choschi.memdroid.webservice.requests.ServerSessionIdRequest;
import com.choschi.memdroid.webservice.requests.ServerSessionIdResponse;
import com.choschi.memdroid.webservice.requests.SoapFaultResponse;

import android.util.Log;
import android.widget.TextView;

/**
 * 
 * @author choschi
 * 
 *         Handles all the requests to the webservices to prevent double login
 *         and other issuers of the same nature it is implemented as singleton
 * 
 */

public class Client {

	private static Client instance = new Client();
	private List<ClientListener> listeners = new ArrayList<ClientListener>();

	public static final int LOGIN_SUCCESS = 0;
	public static final int LOGIN_FAILED = 1;
	public static final int REQUEST_FAILED = 3;
	public static final int USER_DATA = 4;
	public static final int STUDIES_LIST = 6;

	public static final int LOGIN_DIALOG = 0;
	public static final int PROGRESS_DIALOG = 1;

	/**
	 * private constructor, only class methods can create an instance of the
	 * class
	 * 
	 */
	private Client() {
	}

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
	private String moduleId;
	
	private ModuleUserDataResponse userData;

	private Study actualStudy;

	/**
	 * data received from server and surely valid for the whole session
	 */

	private List<Study> studies;

	private Boolean loggedIn = false;
	private Boolean loggingIn = false;

	/**
	 * console, may be used while developing to show on screen info to the
	 * tablet user
	 */

	private TextView console;
	private List<Form> forms;

	public void setConsole(TextView target) {
		console = target;
	}

	/**
	 * initiates the login process cascade of requests
	 * 
	 * @param username
	 * @param password
	 */

	public void login(String username, String password) {
		if (!loggedIn) {
			loggingIn = true;
			log("trying to login to the servers");
			this.username = username;
			this.password = SHA256.getHash(password);
			SoapRequestParams params = new ServerRequestParams();
			params.setAction(BackgroundSoapRequest.ServerBaseAction
					+ "getServerSessionIdRequest");
			params.setMethod("getServerSessionIdRequest");
			logcat("init server session id request");
			BackgroundSoapRequest request = new ServerSessionIdRequest(params);
			request.execute(new SoapRequestParams[] {});
			logcat("issued server session id request");
		} else {
			log("already logged in");
		}
	}

	/**
	 * first request was successful -> received server session id , now login to
	 * module
	 * 
	 * @param response
	 */

	public void sessionIdReceived(ServerSessionIdResponse response) {
		this.sessionId = response.getSessionId();
		log(response.toString());
		SoapRequestParams params = new ModuleRequestParams();
		params.setAction("");
		params.setMethod("loginToModule");
		logcat("init module login request");
		BackgroundSoapRequest request = new ModuleLoginRequest(params,
				username, password, sessionId);
		request.execute(new SoapRequestParams[] {});
		logcat("issued module login request");
	}

	/**
	 * second step successful -> received module session id after user has been
	 * verified by module issue the first and last step of the login process
	 * 
	 * @param response
	 */

	public void moduleLoggedIn(ModuleLoginResponse response) {
		if (loggingIn) {
			this.userId = response.getUserId();
			this.moduleSessionId = response.getModuleSessionId();
			this.signature = response.getSignature();
			this.moduleId = response.getModuleId();
			log(response.toString());
			SoapRequestParams params = new ServerRequestParams();
			params.setAction(BackgroundSoapRequest.ServerBaseAction
					+ "loginToServerRequest");
			params.setMethod("loginToServer");
			logcat("init server login request");
			BackgroundSoapRequest request = new ServerLoginRequest(params,moduleId, sessionId, signature, userId);
			request.execute(new SoapRequestParams[] {});
			logcat("issued server login request");
		}
	}

	/**
	 * last step successful -> set the logged in flag tell the UI to display the
	 * after login screen
	 * 
	 * @param response
	 */

	public void serverLoggedIn(ServerLoginResponse response) {
		if (loggingIn) {
			this.loggedIn = response.getState();
			log(response.toString());
			loggingIn = false;
			for (ClientListener listener : listeners) {
				if (this.loggedIn) {
					listener.notify(Client.LOGIN_SUCCESS);
				} else {
					listener.notify(Client.LOGIN_FAILED);
				}
			}
		}
	}

	/**
	 * get the actual users data
	 */

	public void requestUserData() {
		if (this.loggedIn) {
			SoapRequestParams params = new ModuleRequestParams();
			params.setAction("");
			params.setMethod("getUserInformation");
			logcat("init user information request");
			BackgroundSoapRequest request = new ModuleUserDataRequest(params,moduleSessionId);
			request.execute(new SoapRequestParams[]{});
			logcat("issued user information request");
		}
	}

	/**
	 * second step successful -> received module session id after user has been
	 * verified by module issue the first and last step of the login process
	 * 
	 * @param response
	 */

	public void receivedUserData(ModuleUserDataResponse response) {
		if (loggedIn) {
			userData = response;
			log (response.toString());
			/*
			SoapRequestParams params = new ServerRequestParams();
			params.setAction (BackgroundSoapRequest.ServerBaseAction+"loginRequest");
			params.setMethod("login"); logcat ("init server login request");
			BackgroundSoapRequest request = new
			ServerLoginRequest(params,"memdoctest.memdoc.org",sessionId,signature,userId);
			request.execute(new SoapRequestParams[]{}); logcat("issued server login request");
			*/
			for (ClientListener listener : listeners) {
				listener.notify(Client.USER_DATA);
			}
		}
	}

	/**
	 * get a list of studies for the actual user
	 */

	public void requestListOfStudies() {
		if (this.loggedIn) {
			if (this.studies != null) {
				for (ClientListener listener : listeners) {
					listener.notify(Client.STUDIES_LIST);
				}
			} else {
				SoapRequestParams params = new ServerRequestParams();
				params.setAction(BackgroundSoapRequest.ServerBaseAction
						+ "getListOfStudiesRequest");
				params.setMethod("getListOfStudies");
				logcat("init server " + params.getMethod() + " request");
				logcat(Locale.getDefault().getDisplayLanguage());
				String language = "de";
				BackgroundSoapRequest request = new ServerGetListOfStudiesRequest(
						params, sessionId, language, "multicenter");
				request.execute(new SoapRequestParams[] {});
			}
		}
	}

	public void receivedListOfStudies(ServerGetListOfStudiesResponse response) {
		this.studies = response.getStudies();
		for (ClientListener listener : listeners) {
			listener.notify(Client.STUDIES_LIST);
		}
	}

	/**
	 * 
	 * @param study
	 */

	public void requestDataForStudy(Study study) {
		if (this.loggedIn) {
			
			Log.d("study loader","study loading is disabled at the moment");
			log("study loading disabled for instance");
			
			//TODO not working for instance, concentrate on patient...
			
			/*
			SoapRequestParams params = new ServerRequestParams();
			params.setAction(BackgroundSoapRequest.ServerBaseAction
					+ "getListOfFormsRequest");
			params.setMethod("getListOfForms");
			logcat("init server " + params.getMethod() + " request");
			logcat(Locale.getDefault().getDisplayLanguage());
			String language = "de";
			BackgroundSoapRequest request = new ServerGetListOfFormsRequest(
					params, sessionId, language, study.getId(), "multicenter");
			// BackgroundSoapRequest request = new
			// ServerGetListOfFormsRequest(params,sessionId,language,"27","multicenter");
			request.execute(new SoapRequestParams[] {});
			*/
		}
	}

	public void receivedListOfForms(ServerGetListOfFormsResponse result) {
		this.forms = result.getForms();
		for (Form form : this.forms) {
			Client.getInstance().requestFormDefinition(form);
			// TODO remove the moment it works
			break;
		}
		/*
		 * for (ClientListener listener:listeners){
		 * listener.notify(Client.STUDIES_LIST); }
		 */
	}

	public void requestFormDefinition(Form form) {
		if (this.loggedIn) {
			SoapRequestParams params = new ServerRequestParams();
			params.setAction(BackgroundSoapRequest.ServerBaseAction
					+ "downloadFormDefinitionRequest");
			params.setMethod("downloadFormDefinition");
			logcat("init server " + params.getMethod() + " request");
			logcat(Locale.getDefault().getDisplayLanguage());
			String language = "de";
			BackgroundSoapRequest request = new ServerGetFormDefinitionRequest(
					params, sessionId, language, form.getId());
			request.execute(new SoapRequestParams[] {});
		}
	}

	public void receivedFormDefinition(ServerGetFormDefinitionResponse result) {
		logcat("received the form definitions");
	}

	/**
	 * 
	 */
	
	public String getUserText(){
		return userData.getForDisplay();
	}
	
	/**
	 * register an OnLoginListener in this client
	 * 
	 * @param listener
	 */

	public void registerClientListener(ClientListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	/**
	 * terminal station for all soap faults, implement corresponding behaviour
	 * here
	 * 
	 * @param fault
	 */

	public void handleFault(SoapFaultResponse fault) {
		log("a soap fault occured");
		logcat("SOAPfault: " + fault.toString());
		if (loggingIn) {
			loggingIn = false;
			for (ClientListener listener : listeners) {
				listener.notify(Client.LOGIN_FAILED);
			}
		}
	}

	public List<Study> getListOfStudies() {
		return this.studies;
	}

	/**
	 * @return the actualStudy
	 */
	public Study getActualStudy() {
		return actualStudy;
	}

	/**
	 * @param actualStudy
	 *            the actualStudy to set
	 */
	public void setActualStudy(Study actualStudy) {
		this.actualStudy = actualStudy;
	}

	public Boolean isLoggedIn() {
		return this.loggedIn;
	}

	public void logOut() {
		// TODO really implement here the logout functionality
		this.loggedIn = false;
	}

	/**
	 * Appends a message text to the console view
	 * 
	 * @param text
	 */

	public void log(String text) {
		console.setText(console.getText() + "\n" + text);
	}

	/**
	 * logs a message to the log cat debug tool
	 * 
	 * @param text
	 */

	private void logcat(String text) {
		Log.d(TAG, text);
	}

}
