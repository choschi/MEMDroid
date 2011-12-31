package com.choschi.memdroid.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.util.Log;

import com.choschi.memdroid.data.PatientField;
import com.choschi.memdroid.data.PatientFieldData;
import com.choschi.memdroid.data.Study;
import com.choschi.memdroid.data.form.Form;
import com.choschi.memdroid.ui.PatientFormElement;
import com.choschi.memdroid.util.ClientListener;
import com.choschi.memdroid.util.SHA256;
import com.choschi.memdroid.webservice.parameters.ModuleRequestParams;
import com.choschi.memdroid.webservice.parameters.ServerRequestParams;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;
import com.choschi.memdroid.webservice.requests.BackgroundSoapRequest;
import com.choschi.memdroid.webservice.requests.BackgroundSoapRequestNew;
import com.choschi.memdroid.webservice.requests.ModuleCreateNewPatientRequest;
import com.choschi.memdroid.webservice.requests.ModuleCreateNewPatientResponse;
import com.choschi.memdroid.webservice.requests.ModuleGetPatientFieldsRequest;
import com.choschi.memdroid.webservice.requests.ModuleLoginRequest;
import com.choschi.memdroid.webservice.requests.ModuleLoginResponse;
import com.choschi.memdroid.webservice.requests.ModuleSearchPatientRequest;
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

/**
 * 
 * @author choschi
 * 
 *         Handles all the requests to the webservices. To prevent double login
 *         and other issues of the same nature, it is implemented as singleton
 * 
 */

public class Client {

	private static Client instance = new Client();
	private List<ClientListener> listeners = new ArrayList<ClientListener>();

	public static final int LOGIN_SUCCESS = 0;
	public static final int LOGIN_FAILED = 1;
	public static final int REQUEST_FAILED = 3;
	public static final int SHOW_PROGRESS_DIALOG = 4;
	public static final int USER_DATA = 10;
	public static final int STUDIES_LIST = 20;
	public static final int STUDY_DETAILS = 21;
	public static final int PATIENT_FIELDS = 30;

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
	//private ModuleGetPatientFieldsResponse patientFields;
	private List<PatientField> patientFieldsInsert;

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

	//private TextView console;
	private List<Form> forms;
	private boolean downloadingForms = false;
	private int formCount = 0;
	private List<PatientField> patientFieldsSearch;
	/*
	public void setConsole(TextView target) {
		console = target;
	}
	 */
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
			if (this.loggedIn) {
				notify(Client.LOGIN_SUCCESS);
				// although the userData is not necessary to do most stuff with MEMDroid it is necessary to obtain the patientFields, which it will automatically
				requestUserData();
			} else {
				notify(Client.LOGIN_FAILED);
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
			requestPatientFieldsInsert();
			notify(Client.USER_DATA);
		}
	}

	/**
	 * get a list of studies for the actual user
	 */

	public void requestListOfStudies() {
		if (this.loggedIn) {
			notify(Client.SHOW_PROGRESS_DIALOG);
			if (this.studies != null) {
				notify(Client.STUDIES_LIST);
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
		Log.d ("client", "notifying listeners");
		notify(Client.STUDIES_LIST);
	}

	/**
	 * 
	 * @param study
	 */

	public void requestDataForStudy(Study study) {
		if (this.loggedIn && !downloadingForms) {
			downloadingForms  = true;
			//Log.d("study loader","study loading is disabled at the moment");
			//log("study loading disabled for instance");
			actualStudy = study;
			notify (SHOW_PROGRESS_DIALOG);
			SoapRequestParams params = new ServerRequestParams();
			params.setAction(BackgroundSoapRequest.ServerBaseAction + "GetListOfFormsRequest");
			params.setMethod("getListOfForms");
			logcat("init server " + params.getMethod() + " request");
			logcat(Locale.getDefault().getDisplayLanguage());
			String language = "de";
			BackgroundSoapRequest request = new ServerGetListOfFormsRequest(
					params, sessionId, language,  "multicenter", study.getName());
			request.execute(new SoapRequestParams[] {});

		}
	}

	public void receivedListOfForms(ServerGetListOfFormsResponse result) {
		forms = result.getForms();
		formCount = 0;
		Client.getInstance().requestFormDefinition(forms.get(formCount));
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
					params, sessionId, language, form.getStudyName(), form.getVersion());
			request.execute(new SoapRequestParams[] {});
		}
	}

	public void receivedFormDefinition(ServerGetFormDefinitionResponse result) {
		forms.get(formCount).addDefinition(result);
		formCount++;
		if (formCount < forms.size()){
			Client.getInstance().requestFormDefinition(forms.get(formCount));
		}else{
			notify(STUDY_DETAILS);
		}
	}


	/**
	 * Request all the fields for the patient search
	 */

	public void requestPatientFieldsInsert (){
		if (loggedIn) {
			if (patientFieldsInsert != null) {
				notify(Client.PATIENT_FIELDS);
			} else {
				if (userData != null){
					SoapRequestParams params = new ModuleRequestParams();
					params.setMethod("getPatientFields");
					logcat("init module " + params.getMethod() + " request");
					String language = "de";
					BackgroundSoapRequestNew request = new ModuleGetPatientFieldsRequest(params, moduleSessionId, language, "insert", userData.getDepartmentId());
					request.execute(new SoapRequestParams[] {});
				}else{
					requestUserData();
				}
			}
		}
	}
	
	public void requestPatientFieldsSearch (){
		if (loggedIn) {
			if (patientFieldsSearch != null) {
				notify(Client.PATIENT_FIELDS);
			} else {
				if (userData != null){
					SoapRequestParams params = new ModuleRequestParams();
					params.setMethod("getPatientFields");
					logcat("init module " + params.getMethod() + " request");
					String language = "de";
					BackgroundSoapRequestNew request = new ModuleGetPatientFieldsRequest(params, moduleSessionId, language, "search", userData.getDepartmentId());
					request.execute(new SoapRequestParams[] {});
				}else{
					requestUserData();
				}
			}
		}
	}
	
	public void receivedPatientFields (List<PatientField> fields){
		if (patientFieldsInsert == null){
			patientFieldsInsert = fields;
			requestPatientFieldsSearch();
		}else{
			patientFieldsSearch = fields;
			notify(Client.PATIENT_FIELDS);
		}
	}

	public void savePatient(PatientFieldData[] data){
		if (loggedIn) {
			SoapRequestParams params = new ModuleRequestParams();
			params.setMethod("createNewPatient");
			logcat("init module " + params.getMethod() + " request");
			String language = "de";
			BackgroundSoapRequestNew request = new ModuleCreateNewPatientRequest(params, moduleSessionId, language, data, userData.getDepartmentId());
			request.execute(new SoapRequestParams[] {});
		}
	}
	
	public void createdPatient(ModuleCreateNewPatientResponse response){
		SoapRequestParams params = new ModuleRequestParams();
		params.setMethod("getPatientFields");
		logcat("init module " + params.getMethod() + " request");
		String language = "de";
		//BackgroundSoapRequestNew request = new ModuleSearchPatientRequest(params, moduleSessionId, language, ,userData.getDepartmentId());
		//request.execute(new SoapRequestParams[] {});
	}
	
	public void searchPatient(PatientFieldData[] search){
		SoapRequestParams params = new ModuleRequestParams();
		params.setMethod("searchPatient");
		logcat("init module " + params.getMethod() + " request");
		String language = "de";
		BackgroundSoapRequestNew request = new ModuleSearchPatientRequest(params, moduleSessionId, language, search, userData.getDepartmentId());
		request.execute(new SoapRequestParams[] {});
	}
	
	public void receivedSearchedPatients(){
		Log.d ("client","received patients");
	}
	
	/**
	 * 
	 */
	
	
	public ModuleUserDataResponse getUserData(){
		return userData;
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


	private void notify (int event){
		for (ClientListener listener:listeners){
			listener.notify(event);
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


	public List<PatientField> getPatientFieldsInsert() {
		return patientFieldsInsert;
	}
	
	public List<PatientField> getPatientFieldsSearch() {
		return patientFieldsSearch;
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
		// console.setText(console.getText() + "\n" + text);
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
