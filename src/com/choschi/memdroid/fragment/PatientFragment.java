package com.choschi.memdroid.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.choschi.memdroid.R;
import com.choschi.memdroid.util.ClientListener;
import com.choschi.memdroid.webservice.Client;

public class PatientFragment extends Fragment implements ClientListener,OnClickListener  {

	boolean mDualPane;
	int mCurCheckPosition = 0;

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


		// Check to see if we have a frame in which to embed the details
		// fragment directly in the containing UI.
		View detailsFrame = getActivity().findViewById(R.id.details);
		mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

		if (savedInstanceState != null) {
			// Restore last state for checked position.
			mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
		}

		if (mDualPane) {
			Log.d ("patientFragment","omg it's dual pane");
			// In dual-pane mode, the list view highlights the selected item.
			//getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			// Make sure our UI is in the correct state.
			//showDetails(mCurCheckPosition);
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
		outState.putInt("curChoice", mCurCheckPosition);
	}


	@Override
	public void notify(int message) {
		switch (message){
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.patientNewButton:
			if (mDualPane){
				View view = getActivity().findViewById(R.id.details);
				view.setVisibility(1);
				Fragment newFragment = new PatientNewFragment();
				FragmentTransaction transaction = getFragmentManager().beginTransaction();

				// Replace whatever is in the fragment_container view with this fragment,
				// and add the transaction to the back stack
				transaction.replace(R.id.details, newFragment);
				//transaction.addToBackStack(null);

				// Commit the transaction
				transaction.commit();
			}
			Log.d("patienFragment","newPatient pressed");
			break;
		case R.id.patientSearchButton:
			if (mDualPane){
				View view = getActivity().findViewById(R.id.details);
				view.setVisibility(1);
				Fragment newFragment = new PatientSearchFragment();
				FragmentTransaction transaction = getFragmentManager().beginTransaction();

				// Replace whatever is in the fragment_container view with this fragment,
				// and add the transaction to the back stack
				transaction.replace(R.id.details, newFragment);
				//transaction.addToBackStack(null);

				// Commit the transaction
				transaction.commit();
			}
			Log.d("patienFragment","searchPatient pressed");
			break;
		}
	}
}

