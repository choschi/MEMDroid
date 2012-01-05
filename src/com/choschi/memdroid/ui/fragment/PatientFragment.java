package com.choschi.memdroid.ui.fragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.R;
import com.choschi.memdroid.Client.ClientMessages;
import com.choschi.memdroid.interfaces.ClientListener;

/**
 * 
 * @author Christoph Isch
 * 
 * Master Fragment for the patient views
 * 
 */


public class PatientFragment extends Fragment implements ClientListener,OnClickListener  {
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState == null){
			Client.getInstance().registerClientListener(this);
			Button newButton = (Button)getActivity().findViewById(R.id.patientNewButton);
			newButton.setOnClickListener(this);
			Button searchButton = (Button)getActivity().findViewById(R.id.patientSearchButton);
			searchButton.setOnClickListener(this);
		}

		if (savedInstanceState != null) {
			// Restore last state for checked position.
			
		}
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.patient_fragment, container, false);
		//return null;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		//outState.putInt("curChoice", mCurCheckPosition);
	}


	@Override
	public void notify(ClientMessages message) {
		switch (message){
		}
	}

	@Override
	public void onClick(View v) {
		// as long as there are no pateintFields these two fragment display nothing, so omit any action
		try{
			Client.getInstance().getPatientFieldsInsert();
			switch (v.getId()){
			case R.id.patientNewButton:
					View newView = getActivity().findViewById(R.id.patientDetailOutlet);
					newView.setVisibility(1);
					Fragment newFragment = new PatientNewFragment();
					FragmentTransaction newTransaction = getFragmentManager().beginTransaction();

					newTransaction.replace(R.id.patientDetailOutlet, newFragment);

					newTransaction.commit();
					Log.d("patientFragment","newPatient pressed");
				break;
			case R.id.patientSearchButton:
					View searchView = getActivity().findViewById(R.id.patientDetailOutlet);
					searchView.setVisibility(1);
					Fragment searchFragment = new PatientSearchFragment();
					FragmentTransaction searchTransaction = getFragmentManager().beginTransaction();

					searchTransaction.replace(R.id.patientDetailOutlet, searchFragment);

					searchTransaction.commit();
					Log.d("patientFragment","searchPatient pressed");
				break;
			}
		}catch (Exception ex){
			
		}
	}
}

