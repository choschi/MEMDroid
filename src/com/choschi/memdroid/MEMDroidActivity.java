package com.choschi.memdroid;

import java.util.Calendar;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.AlertDialog;
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
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.choschi.memdroid.Client.ClientMessages;
import com.choschi.memdroid.data.Department;
import com.choschi.memdroid.interfaces.ClientListener;
import com.choschi.memdroid.ui.TabListener;
import com.choschi.memdroid.ui.fragment.PatientFragment;
import com.choschi.memdroid.ui.fragment.StudyFragment;
import com.choschi.memdroid.util.FixedLists;

/**
 * 
 * The activity is the main class of every android Application
 * 
 * @author Christoph Isch
 *
 */

public class MEMDroidActivity extends Activity implements OnClickListener,ClientListener, OnCancelListener, DialogInterface.OnClickListener {
    
	public static final int LOGIN_DIALOG = 0;
	public static final int PROGRESS_DIALOG = 1;
	public static final int DEPARTMENT_DIALOG = 2;
	public static final int DATE_DIALOG = 3;
	public static final int NO_PATIENT_DIALOG = 4;
	public static final int PATIENT_DIALOG = 5;
	private Dialog loginDialog;
	private ProgressDialog progressDialog;
	private Dialog departmentDialog;
	private DatePickerDialog dateDialog;
	private AlertDialog alrt;
	private AlertDialog alert;
	
	private MenuItem logoutButton;
	
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
        Button loginButton = (Button) this.findViewById(R.id.openLoginButton);
        loginButton.setOnClickListener(this);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
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
	    // logout is pressed, logout
	    case R.id.mainMenuLogin:
	    	logoutButton.setEnabled(false);
	    	Client.getInstance().logOut();
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
				loginDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
				departmentDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
			case NO_PATIENT_DIALOG:
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage(R.string.noPatientText);
				builder.setCancelable(false);
				builder.setPositiveButton(
					R.string.buttonOkay,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.dismiss();
						}
					}
				);
				alert = builder.create();
				return alert;
			case PATIENT_DIALOG:
				AlertDialog.Builder build = new AlertDialog.Builder(this);
				build.setMessage(R.string.patientSelected);
				build.setCancelable(false);
				build.setPositiveButton(R.string.buttonOkay,this);
				alrt = build.create();
				return alrt;
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
			// and the click on the department dismiss/select button
			case R.id.departmentDialogDismissButton:
				departmentDialog.dismiss();
				Spinner departmentSpinner = (Spinner)departmentDialog.findViewById(R.id.departmentDialogSpinner);
				Department department = (Department)departmentSpinner.getSelectedItem();
				Client.getInstance().setActualDepartment(department);
				Client.getInstance().requestPatientFieldsInsert();
				showDialog(PROGRESS_DIALOG);
			break;
			case R.id.openLoginButton:
				this.showLoginDialog();
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
				// if there's only one department chose it right away
				if (Client.getInstance().getDepartments().size() > 1){
					showDialog(DEPARTMENT_DIALOG);
				}else{
					Client.getInstance().setActualDepartment(Client.getInstance().getDepartments().get(0));
					Client.getInstance().requestPatientFieldsInsert();
					showDialog(PROGRESS_DIALOG);
				}
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
				View main = this.findViewById(R.id.mainContentStartup);
				main.setVisibility(View.INVISIBLE);
				progressDialog.dismiss();
				ActionBar actionBar = getActionBar();
			    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

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
			    
			    View frags = this.findViewById(R.id.fragmentContainer);
				frags.setVisibility(View.VISIBLE);
			    logoutButton.setEnabled(true);
				break;
			case SHOW_DATE_PICKER:
				showDialog(DATE_DIALOG);
				break;
			case PATIENT_SEARCH:
			case PATIENT_SAVE:
				progressDialog.dismiss();
				break;
			case NO_PATIENT:
				showDialog(NO_PATIENT_DIALOG);
				break;
			case PATIENT_SELECTED:
				showDialog(PATIENT_DIALOG);
				break;
			case RESTORE_BASE:
				View base = this.findViewById(R.id.mainContentStartup);
				base.setVisibility(View.VISIBLE);
				ActionBar actionB = getActionBar();
				actionB.removeAllTabs();
				View fragments = this.findViewById(R.id.fragmentContainer);
				fragments.setVisibility(View.INVISIBLE);
				break;
		}
	}

	@Override
	public void onCancel(DialogInterface arg0) {
		MEMDroidActivity.this.finish();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		dialog.dismiss();
	}
	
	@Override
	public boolean onPrepareOptionsMenu (Menu menu) {
	    logoutButton = menu.getItem(0);
	    return true;
	}
	
}
