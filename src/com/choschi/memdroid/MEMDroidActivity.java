package com.choschi.memdroid;

import java.util.Calendar;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.choschi.memdroid.Client.ClientMessages;
import com.choschi.memdroid.data.Department;
import com.choschi.memdroid.interfaces.ClientListener;
import com.choschi.memdroid.ui.fragment.PatientFragment;
import com.choschi.memdroid.ui.fragment.StudyFragment;
import com.choschi.memdroid.ui.fragment.TabListener;
import com.choschi.memdroid.util.FixedLists;

/**
 * 
 * The activity is the main class of every android Application
 * 
 * @author Christoph Isch
 *
 */

public class MEMDroidActivity extends Activity implements OnClickListener,ClientListener, OnCancelListener {
    
	public static final int LOGIN_DIALOG = 0;
	public static final int PROGRESS_DIALOG = 1;
	public static final int DEPARTMENT_DIALOG = 2;
	public static final int DATE_DIALOG = 3;    
	private Dialog loginDialog;
	private ProgressDialog progressDialog;
	private Dialog departmentDialog;
	private DatePickerDialog dateDialog;
	
	/**
	 * setting the main view layout from the resources directory
	 */
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // initialise the fixed lists
        FixedLists.getInstance().getCountries(getApplicationContext());
        FixedLists.getInstance().getLanguages(getApplicationContext());
        FixedLists.getInstance().getGenders(getApplicationContext());
        Client.getInstance().setLanguage(getApplicationContext().getString(R.string.languageISO));
    }
	
	@Override
	public void onResume (){
		super.onResume();
	}
	
	/**
	 * handling the clicks on the different options on the right side of the action bar
	 */
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // login is pressed, show the login dialog
	    case R.id.mainMenuLogin:
	    	this.showLoginDialog();
	      	return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	/**
	 * show the login Dialog if not logged in or log out
	 */
	
	public void showLoginDialog (){
		if (!Client.getInstance().isLoggedIn()){
			showDialog(LOGIN_DIALOG);
		}else{
			Client.getInstance().logOut();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_menu, menu);
	    return true;
	}
	
	/**
	 * create the different dialogs on the activity level
	 */
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id){
			case LOGIN_DIALOG:
				loginDialog = new Dialog(this);
				loginDialog.setContentView(R.layout.login_dialog);
				loginDialog.setTitle(R.string.loginDialogTitle);
				Button loginButton = (Button) loginDialog.findViewById(R.id.loginButton);
				loginButton.setOnClickListener(this);
				loginDialog.setOnCancelListener(this);
				return loginDialog;
			case PROGRESS_DIALOG:
				progressDialog = new ProgressDialog(this);
				progressDialog.setMessage("Loading...");
				progressDialog.setCancelable(false);
				return progressDialog;
			case DEPARTMENT_DIALOG:
				departmentDialog = new Dialog(this);
				departmentDialog.setContentView(R.layout.department_dialog);
				departmentDialog.setTitle(R.string.departmentDialogTitle);
				Spinner departmentSpinner = (Spinner)departmentDialog.findViewById(R.id.departmentDialogSpinner);
				ArrayAdapter <Department> departmentAdapter = new ArrayAdapter<Department>(departmentDialog.getContext(),android.R.layout.simple_spinner_item,Client.getInstance().getDepartments());
				departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				departmentSpinner.setAdapter(departmentAdapter);
				Button dismissButton = (Button) departmentDialog.findViewById(R.id.departmentDialogDismissButton);
				dismissButton.setOnClickListener(this);
				departmentDialog.setCancelable (false);
				return departmentDialog;
			case DATE_DIALOG:
				Calendar c = Calendar.getInstance();
				dateDialog = new DatePickerDialog(this,Client.getInstance().getDateWaiter(),c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
				return dateDialog;
		}
		return null;
	}
	
	/**
	 * handle the click on the login button in the login dialog
	 */
	
	public void onClick(View v){
		switch (v.getId()){
			case R.id.loginButton:
				EditText usernameText = (EditText)loginDialog.findViewById(R.id.username);
				EditText passwordText = (EditText)loginDialog.findViewById(R.id.password);
				((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(passwordText.getWindowToken(), 0);
				String username = usernameText.getText().toString();
				String password = passwordText.getText().toString();
				Log.i ("username:passwort", username+":"+password);
				loginDialog.dismiss();
				showDialog(PROGRESS_DIALOG);
				Client.getInstance().registerClientListener(this);
				Client.getInstance().login(username,password);
			break;
			case R.id.departmentDialogDismissButton:
				departmentDialog.dismiss();
				Spinner departmentSpinner = (Spinner)departmentDialog.findViewById(R.id.departmentDialogSpinner);
				Department department = (Department)departmentSpinner.getSelectedItem();
				Client.getInstance().setActualDepartment(department);
				Client.getInstance().requestPatientFieldsInsert();
				showDialog(PROGRESS_DIALOG);
			break;
		}
	}
	
	/**
	 * implementation of the listener method notify
	 */
	
	@Override 
	public void notify (ClientMessages message){
		switch (message){
			case USER_DATA:
				showDialog(DEPARTMENT_DIALOG);
			break;
			case LOGIN_FAILED:
				showDialog(LOGIN_DIALOG);
			break;
			case STUDIES_LIST:
				progressDialog.dismiss();
			break;
			case STUDY_DETAILS:
				progressDialog.dismiss();
			break;
			case SHOW_PROGRESS_DIALOG:
				showDialog(PROGRESS_DIALOG);
			break;
			case PATIENT_FIELDS:
				// all the necessary data is loaded, add the views to the action bar

				progressDialog.dismiss();
				ActionBar actionBar = getActionBar();
			    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			    actionBar.setDisplayShowTitleEnabled(false);
			    
			    Tab patient = actionBar.newTab()
			            .setText(R.string.menuPatient)
			            .setTabListener(new TabListener<PatientFragment>(
			                    this, "patient", PatientFragment.class));
			    actionBar.addTab(patient);
			    
			    Tab studies = actionBar.newTab()
			            .setText(R.string.menuStudies)
			            .setTabListener(new TabListener<StudyFragment>(
			                    this, "studies", StudyFragment.class));
			    actionBar.addTab(studies);
				break;
			case SHOW_DATE_PICKER:
				showDialog(DATE_DIALOG);
				break;
		}
	}

	@Override
	public void onCancel(DialogInterface arg0) {
		MEMDroidActivity.this.finish();
	}

}
