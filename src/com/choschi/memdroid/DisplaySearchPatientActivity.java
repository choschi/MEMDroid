package com.choschi.memdroid;

import java.util.List;

import com.choschi.memdroid.data.ListOfStudiesAdapter;
import com.choschi.memdroid.data.PatientField;
import com.choschi.memdroid.data.Study;
import com.choschi.memdroid.util.ClientListener;
import com.choschi.memdroid.webservice.Client;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DisplaySearchPatientActivity extends Activity implements ClientListener,OnItemClickListener {
	
	private List<Study> studies;
	private ProgressDialog progressDialog;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_view);
        TextView view = (TextView) findViewById(R.id.consoleStudies);
        Client.getInstance().registerClientListener(this);
        Client.getInstance().setConsole (view);
        showDialog(Client.PROGRESS_DIALOG);
        Client.getInstance().requestPatientFields();
    }

	@Override
	public void notify(int message) {
		switch (message){
			case Client.PATIENT_FIELDS:
				this.displayFields();
			break;
			default:
			
			break;
		}
	}
	
	private void displayFields (){
		List<PatientField> fields = Client.getInstance().getPatientFields();
		for (PatientField field : fields){
			Log.d ("fieldtype",field.getLabel()+":"+field.getFieldType());
		}
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id){
			case Client.PROGRESS_DIALOG:
				progressDialog = new ProgressDialog(this);
				progressDialog.setMessage("Loading...");
				progressDialog.setCancelable(false);
				return progressDialog;
		}
		return null;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		/*
		Client.getInstance().log("Clicked on study: " + studies.get(arg2).getName());
		Client.getInstance().setActualStudy(studies.get(arg2));
		Intent intent = new Intent(this, DisplayQuestionnaireActivity.class);
		startActivity(intent);
		*/
	}

}
