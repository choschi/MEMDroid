package com.choschi.memdroid;

import com.choschi.memdroid.util.OnLoginListener;
import com.choschi.memdroid.webservice.Client;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MEMDroidActivity extends Activity implements OnClickListener,OnLoginListener, OnCancelListener {
    /** Called when the activity is first created. */
    
	static final int LOGIN_DIALOG = 0;
	static final int PROGRESS_DIALOG = 1;
	
	private Button loginButton;
	private Dialog loginDialog;
	private ProgressDialog progressDialog;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView view = (TextView) findViewById(R.id.textview);
        Client.getInstance().setConsole (view);
    }
	
	@Override
	
	public void onResume (){
		super.onResume();
        showDialog(LOGIN_DIALOG);
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id){
			case LOGIN_DIALOG:
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
			case PROGRESS_DIALOG:
				progressDialog = new ProgressDialog(this);
				progressDialog.setMessage("Loading...");
				progressDialog.setCancelable(false);
				return progressDialog;
		}
		return null;
	}
	
	public void onClick(View v){
		EditText usernameText = (EditText)loginDialog.findViewById(R.id.username);
		EditText passwordText = (EditText)loginDialog.findViewById(R.id.password);
		// TODO find out if this line of code hides the keyboard forever!
		((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(passwordText.getWindowToken(), 0);
		String username = usernameText.getText().toString();
		String password = passwordText.getText().toString();
		Log.d ("username:passwort", username+":"+password);
		loginDialog.dismiss();
		showDialog(PROGRESS_DIALOG);
        //Client.getInstance().login("webservice", "webService1");
		Client.getInstance().registerOnLoginListener(this);
		Client.getInstance().login(username,password);
	}
	
	
	public void onLoginComplete(Boolean state){
		progressDialog.dismiss();
		if (!state){
			showDialog(LOGIN_DIALOG);
		}else{
			Client.getInstance().getListOfStudies();
		}
	}


	@Override
	public void onCancel(DialogInterface arg0) {
		MEMDroidActivity.this.finish();
	}
}
