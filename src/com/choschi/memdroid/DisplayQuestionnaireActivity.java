package com.choschi.memdroid;

import com.choschi.memdroid.util.ClientListener;
import com.choschi.memdroid.webservice.Client;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayQuestionnaireActivity extends Activity implements ClientListener {

	private ProgressDialog progressDialog; 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questionnaire_view);
		TextView view = (TextView) findViewById(R.id.consoleQuestionnaire);
		Client.getInstance().registerClientListener(this);
		Client.getInstance().setConsole (view);
		showDialog(Client.PROGRESS_DIALOG);
		Client.getInstance().requestDataForStudy(Client.getInstance().getActualStudy());
	}

	@Override
	public void notify(int message) {
		switch (message){
		case Client.STUDIES_LIST:
			this.displayQuestionnaireList();
			break;
		default:

			break;
		}
	}

	private void displayQuestionnaireList (){
		progressDialog.dismiss();
		/*
			
			studies = Client.getInstance().getListOfStudies();
		    ListView listOfStudiesView = (ListView) findViewById(R.id.listOfStudies);
		    ArrayAdapter<Study> studiesAdapter = new ListOfStudiesAdapter(this,R.layout.study_row,studies);
		    listOfStudiesView.setOnItemClickListener(this);
		    listOfStudiesView.setAdapter(studiesAdapter);
		 */
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
}

