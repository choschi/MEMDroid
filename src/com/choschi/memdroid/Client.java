package com.choschi.memdroid;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import android.app.DatePickerDialog.OnDateSetListener;
import android.util.Log;

import com.choschi.memdroid.data.Department;
import com.choschi.memdroid.data.ModuleLoginData;
import com.choschi.memdroid.data.Study;
import com.choschi.memdroid.data.form.Form;
import com.choschi.memdroid.data.form.FormAnswer;
import com.choschi.memdroid.data.form.FormDefinition;
import com.choschi.memdroid.data.form.SubForm;
import com.choschi.memdroid.data.patient.NewPatient;
import com.choschi.memdroid.data.patient.Patient;
import com.choschi.memdroid.data.patient.PatientField;
import com.choschi.memdroid.data.patient.PatientFieldData;
import com.choschi.memdroid.interfaces.ClientListener;
import com.choschi.memdroid.util.SHA256;
import com.choschi.memdroid.webservice.BackgroundSoapRequest;
import com.choschi.memdroid.webservice.parameters.ModuleRequestParams;
import com.choschi.memdroid.webservice.parameters.ServerRequestParams;
import com.choschi.memdroid.webservice.parameters.SoapRequestParams;
import com.choschi.memdroid.webservice.requests.ModuleCreateNewPatientRequest;
import com.choschi.memdroid.webservice.requests.ModuleGetPatientByIdRequest;
import com.choschi.memdroid.webservice.requests.ModuleGetPatientFieldsRequest;
import com.choschi.memdroid.webservice.requests.ModuleLoginRequest;
import com.choschi.memdroid.webservice.requests.ModuleSearchPatientRequest;
import com.choschi.memdroid.webservice.requests.ModuleUserDataRequest;
import com.choschi.memdroid.webservice.requests.ServerGetFormDefinitionRequest;
import com.choschi.memdroid.webservice.requests.ServerGetListOfFormsRequest;
import com.choschi.memdroid.webservice.requests.ServerGetListOfStudiesRequest;
import com.choschi.memdroid.webservice.requests.ServerInsertFormRequest;
import com.choschi.memdroid.webservice.requests.ServerInsertPatientRequest;
import com.choschi.memdroid.webservice.requests.ServerLoginRequest;
import com.choschi.memdroid.webservice.requests.ServerSessionIdRequest;

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
	 * 
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
		PATIENT_SEARCH,
		PATIENT_SAVE,
		SHOW_FORM,
		SHOW_SUB_FORM,
		NO_PATIENT,
		PATIENT_SELECTED,
		RESTORE_BASE,
		ERROR_DIALOG,
	}
	
	/**
	 * enum for the languages
	 */
	
	public enum Language{
		UNDEFINED ("undefined","undefined"),
		EN ("en","english"),
		DE ("de","deutsch"),
		;
		private String name;
		private String iso;
		Language (String iso,String name){
			this.name = name;
			this.iso = iso;
		}
		
		public String toString(){
			return name;
		}
		
		public String getIso(){
			return iso;
		}
	}
	
	
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
	private Language language = Language.EN;
	
	/**
	 * data received from server and surely valid for the whole session
	 */

	private List<PatientField> patientFieldsInsert;
	private List<PatientField> patientFieldsSearch;

	private Study actualStudy;

	private List<Study> studies;

	private Boolean loggedIn = false;
	private Boolean loggingIn = false;
	
	private boolean downloadingForms = false;
	private int formCount = 0;

	private Department actualDepartment;
	
	private OnDateSetListener waitingForDate;
	
	private int patientsToGo;
	
	private List<Patient> patients;
	private Patient actualPatient; 

	private List<Department> departments;

	private Form actualForm;
	
	private SubForm actualSubForm;
	
	private String errorMessage;
	
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
	
	public void setLanguage(String lang){
		for (Language item : Language.values()){
			if (item.getIso().compareToIgnoreCase(lang)==0){
				language = item;
			}
		}
		logcat ("the language is: "+language);
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

	public void sessionIdReceived(String response) {
		logcat ("session id received");
		sessionId = response;
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

	public void moduleLoggedIn(ModuleLoginData response) {
		if (loggingIn) {
			logcat ("logged in to module");
			userId = response.getUserId();
			moduleSessionId = response.getModuleSessionId();
			signature = response.getSignature();
			moduleId = response.getModuleId();
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

	public void serverLoggedIn(Boolean response) {
		if (loggingIn) {
			logcat ("logged in to server");
			loggedIn = response;
			loggingIn = false;
			if (loggedIn) {
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
		if (loggedIn) {
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
	 * @param departments
	 */

	public void receivedUserData(List<Department> departments) {
		if (loggedIn) {
			this.departments = departments;
			logcat ("user data received");
			notify(ClientMessages.USER_DATA);
		}
	}

	/**
	 * get a list of studies for the actual user
	 */

	public void requestListOfStudies() {
		if (loggedIn) {
			notify(ClientMessages.SHOW_PROGRESS_DIALOG);
			if (studies != null) {
				notify(ClientMessages.STUDIES_LIST);
			} else {
				SoapRequestParams params = new ServerRequestParams();
				params.setAction(BackgroundSoapRequest.ServerBaseAction
						+ "getListOfStudiesRequest");
				params.setMethod("getListOfStudies");
				BackgroundSoapRequest request = new ServerGetListOfStudiesRequest(
						params, sessionId, language.getIso(), "multicenter");
				request.execute(new SoapRequestParams[] {});
			}
		}
	}

	/**
	 * handles the response for the get list of studies request
	 * 
	 * @param response
	 */
	
	public void receivedListOfStudies(List<Study> response) {
		studies = response;
		logcat ("received studies");
		notify(ClientMessages.STUDIES_LIST);
	}

	/**
	 * request the data for a certain study
	 * @param study
	 */

	public void requestListOfForms(Study study) {
		if (loggedIn && !downloadingForms) {
			notify (ClientMessages.SHOW_PROGRESS_DIALOG);
			logcat ("requesting list of forms");
			downloadingForms  = true;
			actualStudy = study;
			SoapRequestParams params = new ServerRequestParams();
			params.setAction(BackgroundSoapRequest.ServerBaseAction + "GetListOfFormsRequest");
			params.setMethod("getListOfForms");
			BackgroundSoapRequest request = new ServerGetListOfFormsRequest(
					params, sessionId, language.getIso(),  "multicenter", study.getName());
			request.execute(new SoapRequestParams[] {});

		}
	}

	/**
	 * 
	 * the moment the forms are received, request the form definitions for each form
	 * 
	 * @param result
	 */
	
	public void receivedListOfForms(List<Form> result) {
		logcat ("received a list of forms");
		//forms = result;
		formCount = 0;
		if (formCount < result.size()){
			actualStudy.addForm(result.get(formCount));
			Client.getInstance().requestFormDefinition(actualStudy.getForms().get(formCount));
		}else{
			logcat ("no form definitions available");
			downloadingForms = false;
			notify(ClientMessages.STUDY_DETAILS);
		}
	}

	/**
	 * request the form definition of each form one after another
	 * @param form
	 */
	
	public void requestFormDefinition(Form form) {
		if (loggedIn) {
			SoapRequestParams params = new ServerRequestParams();
			params.setAction(BackgroundSoapRequest.ServerBaseAction
					+ "downloadFormDefinitionRequest");
			params.setMethod("downloadFormDefinition");
			logcat("init server " + params.getMethod() + " request");
			BackgroundSoapRequest request = new ServerGetFormDefinitionRequest(
					params, sessionId, language.getIso(), form.getStudyName(), form.getVersion());
			request.execute(new SoapRequestParams[] {});
		}
	}

	/**
	 * count if all form definitions are received, then notify the listeners
	 * @param result
	 */
	
	public void receivedFormDefinition(FormDefinition result) {
		logcat ("form defintion received");
		actualStudy.getForms().get(formCount).addDefinition(result);
		formCount++;
		if (formCount < actualStudy.getForms().size()){
			Client.getInstance().requestFormDefinition(actualStudy.getForms().get(formCount));
		}else{
			logcat ("all form definitions received");
			downloadingForms = false;
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
				//notify(ClientMessages.PATIENT_FIELDS);
			} else {
				if (departments != null){
					SoapRequestParams params = new ModuleRequestParams();
					params.setMethod("getPatientFields");
					BackgroundSoapRequest request = new ModuleGetPatientFieldsRequest(params, moduleSessionId, language.getIso(), "insert", actualDepartment.getId());
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
				//notify(ClientMessages.PATIENT_FIELDS);
			} else {
				if (departments != null){
					SoapRequestParams params = new ModuleRequestParams();
					params.setMethod("getPatientFields");
					BackgroundSoapRequest request = new ModuleGetPatientFieldsRequest(params, moduleSessionId, language.getIso(), "search", actualDepartment.getId());
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
			notify(ClientMessages.SHOW_PROGRESS_DIALOG);
			SoapRequestParams params = new ModuleRequestParams();
			params.setMethod("createNewPatient");
			BackgroundSoapRequest request = new ModuleCreateNewPatientRequest(params, moduleSessionId, language.getIso(), data, actualDepartment.getId());
			request.execute(new SoapRequestParams[] {});
		}
	}
	
	/**
	 * 
	 * the patient has been saved to the serve
	 * 
	 * @param response
	 */
	
	public void createdPatient(NewPatient response){
		setActualPatient(response);
		SoapRequestParams params = new ServerRequestParams();
		params.setAction(BackgroundSoapRequest.ServerBaseAction
				+ "insertPatientRequest");
		params.setMethod("insertPatient");
		BackgroundSoapRequest request = new ServerInsertPatientRequest(params, sessionId, userId, actualDepartment.getId(), response);
		request.execute(new SoapRequestParams[] {});
	}
	
	/**
	 * call back for the ServerInsertPatientRequest
	 * @param state
	 */
	
	public void savedPatient(boolean state){
		notify (ClientMessages.PATIENT_SAVE);
	}
	
	/**
	 * 
	 * initiate a patient search on the server
	 * 
	 * @param search
	 */
	
	public void searchPatient(PatientFieldData[] search){
		if (loggedIn){
			notify(ClientMessages.SHOW_PROGRESS_DIALOG);
			SoapRequestParams params = new ModuleRequestParams();
			params.setMethod("searchPatient");
			logcat("init module " + params.getMethod() + " request");
			BackgroundSoapRequest request = new ModuleSearchPatientRequest(params, moduleSessionId, language.getIso(), search, actualDepartment.getId());
			request.execute(new SoapRequestParams[] {});
		}
	}
	
	/**
	 * received a result for the patient search
	 */
	
	public void receivedSearchedPatients(List<String> patientIds){
		patients = new ArrayList<Patient>();
		if (patientIds.size() > 0){
			patientsToGo = patientIds.size();
			for (String id : patientIds){
				SoapRequestParams params = new ModuleRequestParams();
				params.setMethod("getPatientById");
				logcat("init module " + params.getMethod() + " request");
				BackgroundSoapRequest request = new ModuleGetPatientByIdRequest(params, moduleSessionId, id);
				request.execute(new SoapRequestParams[] {});
			}
		}else{
			notify(ClientMessages.PATIENT_SEARCH);
		}
	}
	
	/**
	 * saves the received patient to a list of searched patients results
	 * @param patient
	 */
	
	public void receivedSearchedPatient(Patient patient){
		patientsToGo--;
		patients.add(patient);
		if (patientsToGo == 0){
			notify(ClientMessages.PATIENT_SEARCH);
		}
	}
	
	/**
	 * a patient has been selected, tell the user so
	 */
	
	public void showSelectPatientWarning(){
		notify (ClientMessages.NO_PATIENT);
	}
	
	/**
	 * insert data for a form in the database
	 * @param insert
	 */
	
	public void insertForm(FormAnswer[] insert){
		if (loggedIn){
			SoapRequestParams params = new ServerRequestParams();
			params.setAction(BackgroundSoapRequest.ServerBaseAction
					+ "insertFormRequest");
			params.setMethod("insertForm");
			BackgroundSoapRequest request = new ServerInsertFormRequest(params, sessionId, userId, actualDepartment.getId(), actualPatient.getId(), actualForm, actualSubForm, insert);
			request.execute(new SoapRequestParams[] {});
		}
	}
	
	/**
	 * call back being called after a form has been saved
	 * @param result
	 */
	
	public void savedForm (Object result){
		if (result != null){
			handleError((Exception)result);
		}
	}
	
	/**
	 * get a list of all the patient search results
	 * @return list of patients
	 */
	
	public List<Patient> getPatients(){
		return patients;
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
	 * terminal station for all errors in data handling 
	 * 
	 * @param result
	 */
	
	public void handleError(Exception result) {
		displayErrorMessage(result.toString());
		if (loggingIn) {
			loggingIn = false;
			notify(ClientMessages.LOGIN_FAILED);
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
		return studies;
	}
	
	/**
	 * get the application language
	 * @return the actual language
	 */
	
	public Language getLanguage(){
		return language;
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
	 * show the form
	 * @param form
	 */

	public void showForm(Form form) {
		actualForm = form;
		notify (ClientMessages.SHOW_FORM);
	}
	
	/**
	 * show the selectd sub form
	 * @param form
	 */
	
	public void showSubForm(SubForm form) {
		actualSubForm = form;
		notify (ClientMessages.SHOW_SUB_FORM);
	}
	
	/**
	 * what is the actually active form
	 * @return the form
	 */
	
	public Form getActualForm(){
		return actualForm;
	}
	
	/**
	 * what is the actually selected sub form
	 * @return subForm
	 */
	
	public SubForm getActualSubForm(){
		return actualSubForm;
	}
	
	/**
	 * get the actual department
	 * @return the actual Department
	 */
	
	public Department getDepartment(){
		return actualDepartment;
	}
	
	/**
	 * set the department
	 * @param department to set
	 */
	
	public void setActualDepartment (Department department){
		actualDepartment = department;
	}
	
	/**
	 * tell the ui to present a date picker dialog
	 * @param caller
	 */
	
	public void showDatePicker (OnDateSetListener caller){
		waitingForDate = caller;
		notify(ClientMessages.SHOW_DATE_PICKER);
	}
	
	/**
	 * a date has been selected send it to the waiting object
	 */

	public OnDateSetListener getDateWaiter() {
		return waitingForDate;
	}
	
	/**
	 * generates a date string according to the language
	 * @param dayOfMonth
	 * @param monthOfYear
	 * @param year
	 * @param lang
	 * @return date in string format
	 */
	
	public String getDateForLanguage(int dayOfMonth, int monthOfYear, int year, Language lang) {
		monthOfYear += 1;
		String day ="";
		String month = "";
		if (dayOfMonth < 10){
			day = "0"+dayOfMonth;
		}else{
			day = ""+dayOfMonth;
		}
		if (monthOfYear < 10){
			month = "0"+monthOfYear;
		}else{
			month = ""+monthOfYear;
		}
		String output = "";
		if (lang == null){
			// this is a workaround as long as the server only supports timestamps
			lang = Language.UNDEFINED;
		}
		switch (lang){
		case EN:
			output = month+"/"+day+"/"+year;
			break;
		case DE:
			output = day+"."+month+"."+year;
			break;
		case UNDEFINED:
		default:
			GregorianCalendar date = new GregorianCalendar (year,monthOfYear,dayOfMonth);
			Date time = date.getTime();
			output = ""+time.getTime();
			break;
		}
		return output;
	}
	
	/**
	 * @return true if still logged in
	 */
	
	public Boolean isLoggedIn() {
		return this.loggedIn;
	}

	/**
	 * log the user out
	 */
	
	public void logOut() {
		loggedIn = false;
		username = "";
		password = "";
		sessionId = "";
		userId = "";
		signature = "";
		moduleSessionId = "";
		moduleId = "";
		patientFieldsInsert = null;
		patientFieldsSearch = null;
		actualStudy = null;
		studies = null;
		loggingIn = false;
		downloadingForms = false;
		formCount = 0;
		actualDepartment = null;
		waitingForDate = null;
		patientsToGo = 0;
		patients = null;
		actualPatient = null; 
		departments = null;
		actualForm = null;
		actualSubForm = null;
		notify (ClientMessages.RESTORE_BASE);
	}

	/**
	 * set the error message and notifies the activity to show the error dialog
	 * @param msg
	 */
	
	public void displayErrorMessage(String msg){
		errorMessage = msg;
		notify(ClientMessages.ERROR_DIALOG);
	}
	
	/**
	 * get the actual error message
	 * @return the error message
	 */
	
	public String getErrorMessage(){
		return errorMessage;
	}
	
	/**
	 * logs a message to the log cat debug tool
	 *
	 * @param text
	 */

	private void logcat(String text) {
		Log.i("client", text);
	}

	/**
	 * get a list of all the possible departments for the user
	 * @return list of departments
	 */
	
	public List<Department> getDepartments() {
		return departments;
	}

	/**
	 * define which is the patient we are going to collect data for
	 * @param patient
	 */
	
	public void setActualPatient(Patient patient) {
		actualPatient = patient;
		notify(ClientMessages.PATIENT_SELECTED);
	}
	
	/**
	 * get the patient of interest
	 * @return patient
	 */
	
	public Patient getActualPatient(){
		return actualPatient;
	}
}
