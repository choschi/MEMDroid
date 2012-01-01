package com.choschi.memdroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.DatePickerDialog.OnDateSetListener;
import android.util.Log;

import com.choschi.memdroid.data.Department;
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
 * Handles all the requests to the webservices. To prevent double login
 * and other issues of the same nature, it is implemented as singleton
 * 
 * @author Christoph Isch
 * 
 */

public class Client {

	private static Client instance = new Client();
	
	
	private List<ClientListener> listeners = new ArrayList<ClientListener>();
	
	
	/*
	 * messages for the listeners
	 * @TODO refactor them to an enum
	 */
	
	public enum ClientMessages{
		LOGIN_SUCCESS,
		LOGIN_FAILED,
		SHOW_PROGRESS_DIALOG, 
		USER_DATA,
		STUDIES_LIST,
		STUDY_DETAILS,
		PATIENT_FIELDS,
		SHOW_DATE_PICKER,
	}
	
	public static final int LOGIN_SUCCESS = 0;
	public static final int LOGIN_FAILED = 1;
	public static final int REQUEST_FAILED = 3;
	public static final int SHOW_PROGRESS_DIALOG = 4;
	public static final int USER_DATA = 10;
	public static final int STUDIES_LIST = 20;
	public static final int STUDY_DETAILS = 21;
	public static final int PATIENT_FIELDS = 30;


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
	private String language = "de";
	
	/**
	 * data received from server and surely valid for the whole session
	 */
	
	private ModuleUserDataResponse userData;

	private List<PatientField> patientFieldsInsert;
	private List<PatientField> patientFieldsSearch;

	private Study actualStudy;

	private List<Study> studies;

	private Boolean loggedIn = false;
	private Boolean loggingIn = false;

	private List<Form> forms;
	private boolean downloadingForms = false;
	private int formCount = 0;

	private Department actualDepartment;
	
	private PatientFormElement waitingForDate;
	
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
	 * initiates the login process cascade of requests
	 * 
	 * @param username
	 * @param password
	 */

	public void login(String username, String password) {
		if (!loggedIn) {
			loggingIn = true;
			logcat("trying to login to the servers");
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
			logcat("already logged in");
		}
	}

	/**
	 * first request was successful -> received server session id , now login to the
	 * module
	 * 
	 * @param response
	 */

	public void sessionIdReceived(ServerSessionIdResponse response) {
		logcat ("session id received");
		this.sessionId = response.getSessionId();
		SoapRequestParams params = new ModuleRequestParams();
		params.setAction("");
		params.setMethod("loginToModule");
		BackgroundSoapRequest request = new ModuleLoginRequest(params,
				username, password, sessionId);
		request.execute(new SoapRequestParams[] {});
	}

	/**
	 * second step successful -> received module session id after user has been
	 * verified by module issue the first and last step of the login process
	 * 
	 * @param response
	 */

	public void moduleLoggedIn(ModuleLoginResponse response) {
		if (loggingIn) {
			logcat ("logged in to module");
			this.userId = response.getUserId();
			this.moduleSessionId = response.getModuleSessionId();
			this.signature = response.getSignature();
			this.moduleId = response.getModuleId();
			SoapRequestParams params = new ServerRequestParams();
			params.setAction(BackgroundSoapRequest.ServerBaseAction
					+ "loginToServerRequest");
			params.setMethod("loginToServer");
			BackgroundSoapRequest request = new ServerLoginRequest(params,moduleId, sessionId, signature, userId);
			request.execute(new SoapRequestParams[] {});
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
			logcat ("logged in to server");
			this.loggedIn = response.getState();
			loggingIn = false;
			if (this.loggedIn) {
				notify(ClientMessages.LOGIN_SUCCESS);
				requestUserData();
			} else {
				notify(ClientMessages.LOGIN_FAILED);
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
			BackgroundSoapRequest request = new ModuleUserDataRequest(params,moduleSessionId);
			request.execute(new SoapRequestParams[]{});
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
			logcat ("user data received");
			notify(ClientMessages.USER_DATA);
		}
	}

	/**
	 * get a list of studies for the actual user
	 */

	public void requestListOfStudies() {
		if (this.loggedIn) {
			notify(ClientMessages.SHOW_PROGRESS_DIALOG);
			if (this.studies != null) {
				notify(ClientMessages.STUDIES_LIST);
			} else {
				SoapRequestParams params = new ServerRequestParams();
				params.setAction(BackgroundSoapRequest.ServerBaseAction
						+ "getListOfStudiesRequest");
				params.setMethod("getListOfStudies");
				BackgroundSoapRequest request = new ServerGetListOfStudiesRequest(
						params, sessionId, language, "multicenter");
				request.execute(new SoapRequestParams[] {});
			}
		}
	}

	/**
	 * handles the response for the get list of studies request
	 * 
	 * @param response
	 */
	
	public void receivedListOfStudies(ServerGetListOfStudiesResponse response) {
		this.studies = response.getStudies();
		logcat ("received studies");
		notify(ClientMessages.STUDIES_LIST);
	}

	/**
	 * request the data for a certain study
	 * @param study
	 */

	public void requestListOfForms(Study study) {
		if (this.loggedIn && !downloadingForms) {
			notify (ClientMessages.SHOW_PROGRESS_DIALOG);
			downloadingForms  = true;
			actualStudy = study;
			SoapRequestParams params = new ServerRequestParams();
			params.setAction(BackgroundSoapRequest.ServerBaseAction + "GetListOfFormsRequest");
			params.setMethod("getListOfForms");
			BackgroundSoapRequest request = new ServerGetListOfFormsRequest(
					params, sessionId, language,  "multicenter", study.getName());
			request.execute(new SoapRequestParams[] {});

		}
	}

	/**
	 * 
	 * the moment the forms are received, request the form definitions for each form
	 * 
	 * @param result
	 */
	
	public void receivedListOfForms(ServerGetListOfFormsResponse result) {
		forms = result.getForms();
		formCount = 0;
		Client.getInstance().requestFormDefinition(forms.get(formCount));
	}

	/**
	 * request the form definition of each form one after another
	 * @param form
	 */
	
	public void requestFormDefinition(Form form) {
		if (this.loggedIn) {
			SoapRequestParams params = new ServerRequestParams();
			params.setAction(BackgroundSoapRequest.ServerBaseAction
					+ "downloadFormDefinitionRequest");
			params.setMethod("downloadFormDefinition");
			logcat("init server " + params.getMethod() + " request");
			logcat(Locale.getDefault().getDisplayLanguage());
			BackgroundSoapRequest request = new ServerGetFormDefinitionRequest(
					params, sessionId, language, form.getStudyName(), form.getVersion());
			request.execute(new SoapRequestParams[] {});
		}
	}

	/**
	 * count if all form definitions are received, the notify the listeners
	 * @param result
	 */
	
	public void receivedFormDefinition(ServerGetFormDefinitionResponse result) {
		logcat ("form defintion received");
		forms.get(formCount).addDefinition(result);
		formCount++;
		if (formCount < forms.size()){
			Client.getInstance().requestFormDefinition(forms.get(formCount));
		}else{
			logcat ("all form definitions received");
			notify(ClientMessages.STUDY_DETAILS);
		}
	}


	/**
	 * Request all the fields for the patient insert
	 * !important! never call requestPatientFieldsInsert after requestPatientFieldsSearch 
	 */

	public void requestPatientFieldsInsert (){
		if (loggedIn) {
			if (patientFieldsInsert != null) {
				notify(ClientMessages.PATIENT_FIELDS);
			} else {
				if (userData != null){
					SoapRequestParams params = new ModuleRequestParams();
					params.setMethod("getPatientFields");
					BackgroundSoapRequestNew request = new ModuleGetPatientFieldsRequest(params, moduleSessionId, language, "insert", actualDepartment.getId());
					request.execute(new SoapRequestParams[] {});
				}else{
					requestUserData();
				}
			}
		}
	}
	
	/**
	 * Request all the fields for the patient search
	 * !important! never call requestPatientFieldsSearch before requestPatientFieldsInsert 
	 */
	
	public void requestPatientFieldsSearch (){
		if (loggedIn) {
			if (patientFieldsSearch != null) {
				notify(ClientMessages.PATIENT_FIELDS);
			} else {
				if (userData != null){
					SoapRequestParams params = new ModuleRequestParams();
					params.setMethod("getPatientFields");
					BackgroundSoapRequestNew request = new ModuleGetPatientFieldsRequest(params, moduleSessionId, language, "search", actualDepartment.getId());
					request.execute(new SoapRequestParams[] {});
				}else{
					requestUserData();
				}
			}
		}
	}
	
	/**
	 * 
	 * received a bunch of patient fields
	 * 
	 * @param fields
	 */
	
	public void receivedPatientFields (List<PatientField> fields){
		if (patientFieldsInsert == null){
			patientFieldsInsert = fields;
			requestPatientFieldsSearch();
		}else{
			patientFieldsSearch = fields;
			notify(ClientMessages.PATIENT_FIELDS);
		}
	}

	/**
	 * 
	 * save the patient to the server
	 * 
	 * @param data
	 */
	
	public void savePatient(PatientFieldData[] data){
		if (loggedIn) {
			SoapRequestParams params = new ModuleRequestParams();
			params.setMethod("createNewPatient");
			BackgroundSoapRequestNew request = new ModuleCreateNewPatientRequest(params, moduleSessionId, language, data, actualDepartment.getId());
			request.execute(new SoapRequestParams[] {});
		}
	}
	
	/**
	 * 
	 * the patient has been saved to the serve
	 * 
	 * @param response
	 */
	
	public void createdPatient(ModuleCreateNewPatientResponse response){
		
	}
	
	/**
	 * 
	 * initiate a patient search on the server
	 * 
	 * @param search
	 */
	
	public void searchPatient(PatientFieldData[] search){
		SoapRequestParams params = new ModuleRequestParams();
		params.setMethod("searchPatient");
		logcat("init module " + params.getMethod() + " request");
		String language = "de";
		BackgroundSoapRequestNew request = new ModuleSearchPatientRequest(params, moduleSessionId, language, search, actualDepartment.getId());
		request.execute(new SoapRequestParams[] {});
	}
	
	/**
	 * received a result for the patient search
	 */
	
	public void receivedSearchedPatients(){
		Log.d ("client","received patients");
	}
	
	/**
	 * 
	 * @return the user data
	 */
		
	public ModuleUserDataResponse getUserData(){
		return userData;
	}
	
	/**
	 * 
	 * @return a string representing the user data
	 */
	@Deprecated
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
	 * notify the listeners
	 * @param event
	 */
	
	private void notify (ClientMessages event){
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

	//TODO maybe deprecated if the new Background Request Class is used all over the place
	
	public void handleFault(SoapFaultResponse fault) {
		logcat("SOAPfault: " + fault.toString());
		if (loggingIn) {
			loggingIn = false;
			for (ClientListener listener : listeners) {
				listener.notify(ClientMessages.LOGIN_FAILED);
			}
		}
	}

	/**
	 * 
	 * @return the fields for the patient insert
	 */
	
	public List<PatientField> getPatientFieldsInsert() {
		return patientFieldsInsert;
	}
	
	/**
	 * 
	 * @return the fields for the patient search
	 */
	
	public List<PatientField> getPatientFieldsSearch() {
		return patientFieldsSearch;
	}
	
	/**
	 * 
	 * @return the list of all the studies for the actual user
	 */

	public List<Study> getListOfStudies() {
		return this.studies;
	}

	/**
	 * 
	 * @return the actualStudy
	 */
	
	public Study getActualStudy() {
		return actualStudy;
	}

	/**
	 * @param actualStudy to set
	 */
	public void setActualStudy(Study actualStudy) {
		this.actualStudy = actualStudy;
	}
	
	/**
	 * 
	 * @return the actual Department
	 */
	
	public Department getDepartment(){
		return actualDepartment;
	}
	
	/**
	 * 
	 * @param department to set
	 */
	
	public void setActualDepartment (Department department){
		actualDepartment = department;
	}
	
	
	public void showDatePicker (PatientFormElement caller){
		waitingForDate = caller;
		notify(ClientMessages.SHOW_DATE_PICKER);
	}
	

	public OnDateSetListener getDateWaiter() {
		return waitingForDate;
	}
	
	/**
	 * 
	 * @return true if still logged in
	 */
	
	public Boolean isLoggedIn() {
		return this.loggedIn;
	}

	/**
	 * log the user out
	 */
	
	public void logOut() {
		// TODO really implement here the logout functionality
		this.loggedIn = false;
	}

	/**
	 * logs a message to the log cat debug tool
	 *
	 * @param text
	 */

	private void logcat(String text) {
		Log.i("client", text);
	}



}