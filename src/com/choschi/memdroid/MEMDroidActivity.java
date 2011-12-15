package com.choschi.memdroid;

import com.choschi.memdroid.ui.StudyListFragment;
import com.choschi.memdroid.util.ClientListener;
import com.choschi.memdroid.webservice.Client;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MEMDroidActivity extends Activity implements OnClickListener,ClientListener, OnCancelListener {
    /** Called when the activity is first created. */
    
	private Button loginButton;
	private Dialog loginDialog;
	private ProgressDialog progressDialog;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
        //setContentView(R.layout.main);
        //TextView view = (TextView) findViewById(R.id.console);
        //Client.getInstance().setConsole (view);
    }
	
	@Override
	
	public void onResume (){
		super.onResume();
		/*
		if (!Client.getInstance().isLoggedIn()){
			showDialog(Client.LOGIN_DIALOG);
		}
		*/
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    /*
	    case R.id.mainMenuPatient:
	        
	    	return true;
	    */
	    case R.id.mainMenuStudies:
	        this.showDialog(Client.PROGRESS_DIALOG);
	        Client.getInstance().requestListOfStudies();
	        return true;
	    
	    case R.id.mainMenuLogin:
	    	this.showLoginDialog();
	    	return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	
	public void showLoginDialog (){
		if (!Client.getInstance().isLoggedIn()){
			showDialog(Client.LOGIN_DIALOG);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_menu, menu);
	    return true;
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id){
			case Client.LOGIN_DIALOG:
				//Context mContext = this.getApplicationContext();
				//Dialog dialog = new Dialog(mContext);
				loginDialog = new Dialog(this);
				loginDialog.setContentView(R.layout.login_box);
				loginDialog.setTitle("Login please");
				//loginDialog.setCancelable(false);
				loginButton = (Button) loginDialog.findViewById(R.id.loginButton);
				loginButton.setOnClickListener(this);
				loginDialog.setOnCancelListener(this);
				return loginDialog;
			case Client.PROGRESS_DIALOG:
				progressDialog = new ProgressDialog(this);
				progressDialog.setMessage("Loading...");
				progressDialog.setCancelable(false);
				return progressDialog;
		}
		return null;
	}
	
	public void onClick(View v){
		if(v.getId() == loginDialog.findViewById(R.id.loginButton).getId()){
				EditText usernameText = (EditText)loginDialog.findViewById(R.id.username);
				EditText passwordText = (EditText)loginDialog.findViewById(R.id.password);
				// TODO find out if this line of code hides the keyboard forever!
				((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(passwordText.getWindowToken(), 0);
				String username = usernameText.getText().toString();
				String password = passwordText.getText().toString();
				Log.d ("username:passwort", username+":"+password);
				loginDialog.dismiss();
				showDialog(Client.PROGRESS_DIALOG);
				Client.getInstance().registerClientListener(this);
				Client.getInstance().login(username,password);
		}
		/*
		if (v.getId() ==  findViewById(R.id.mainListButton).getId()){
			Intent intent = new Intent(this, DisplayStudiesActivity.class);
			startActivity(intent);
		}
		if (v.getId() ==  findViewById(R.id.mainSearchPatientButton).getId()){
			Intent intent = new Intent(this, DisplaySearchPatientActivity.class);
			startActivity(intent);
			//Log.d ("searchbutton","pressed");
		}
		*/
	}
	
	@Override 
	public void notify (int message){
		switch (message){
			case Client.LOGIN_SUCCESS:
				progressDialog.dismiss();
				//Client.getInstance().requestUserData();
			break;
			case Client.LOGIN_FAILED:
				showDialog(Client.LOGIN_DIALOG);
			break;
			case Client.USER_DATA:
				showUserData();
			break;
			case Client.STUDIES_LIST:
				progressDialog.dismiss();
				FragmentManager manager = getFragmentManager();
				FragmentTransaction transaction = manager.beginTransaction();
				Fragment fragment = new StudyListFragment();
				
				transaction.replace(R.id.list_fragment_container, fragment);
				transaction.addToBackStack(null);
				
				transaction.commit();
			break;
			default:
				
			break;
		}
	}
	
	private void showUserData(){
		Log.d("MEMDroid", "showUserData");
		TextView dataText = (TextView)findViewById(R.id.mainUserData);
		dataText.setText(Client.getInstance().getUserText());
		dataText.setVisibility(View.VISIBLE);
		Button listButton = (Button) findViewById(R.id.mainListButton);
		listButton.setVisibility(View.VISIBLE);
		listButton.setOnClickListener(this);
		Button searchButton = (Button) findViewById(R.id.mainSearchPatientButton);
		searchButton.setVisibility(View.VISIBLE);
		searchButton.setOnClickListener(this);
	}

	@Override
	public void onCancel(DialogInterface arg0) {
		MEMDroidActivity.this.finish();
	}

}
