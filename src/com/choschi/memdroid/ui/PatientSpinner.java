package com.choschi.memdroid.ui;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PatientSpinner extends Spinner {

	public PatientSpinner(Context context) {
		super(context);
	}
	
	public PatientSpinner(Context context,ArrayAdapter<String> adapter){
		super (context);
		
	}

}
