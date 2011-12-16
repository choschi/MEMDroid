package com.choschi.memdroid;

import java.util.List;

import com.choschi.memdroid.data.ListOfStudiesAdapter;
import com.choschi.memdroid.data.Study;
import com.choschi.memdroid.util.ClientListener;
import com.choschi.memdroid.webservice.Client;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

//TODO remove this class after the new UI is implemented thoroughly

@Deprecated
public class DisplayStudiesActivity extends Activity implements ClientListener,OnItemClickListener {
	
	private List<Study> studies;
	private ProgressDialog progressDialog;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_view);
        TextView view = (TextView) findViewById(R.id.consoleStudies);
        Client.getInstance().registerClientListener(this);
        //Client.getInstance().setConsole (view);
        showDialog(Client.PROGRESS_DIALOG);
        Client.getInstance().requestListOfStudies();
    }

	@Override
	public void notify(int message) {
		switch (message){
			case Client.STUDIES_LIST:
				this.displayStudyList();
			break;
			default:
			
			break;
		}
	}
	
	private void displayStudyList (){
		progressDialog.dismiss();
		studies = Client.getInstance().getListOfStudies();
	    ListView listOfStudiesView = (ListView) findViewById(R.id.listOfStudies);
	    ArrayAdapter<Study> studiesAdapter = new ListOfStudiesAdapter(this,R.layout.study_row,studies);
	    listOfStudiesView.setOnItemClickListener(this);
	    listOfStudiesView.setAdapter(studiesAdapter);
	    
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
		Client.getInstance().log("Clicked on study: " + studies.get(arg2).getName());
		Client.getInstance().setActualStudy(studies.get(arg2));
		Intent intent = new Intent(this, DisplayQuestionnaireActivity.class);
		startActivity(intent);
	}
}
