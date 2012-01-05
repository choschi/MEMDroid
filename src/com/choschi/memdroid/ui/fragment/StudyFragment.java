package com.choschi.memdroid.ui.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.R;
import com.choschi.memdroid.Client.ClientMessages;
import com.choschi.memdroid.interfaces.ClientListener;
public class StudyFragment extends Fragment implements ClientListener {

	int mCurCheckPosition = 0;
	boolean mAlreadyInitiated = false;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (!mAlreadyInitiated) {
        	Client.getInstance().registerClientListener(this);
        	Client.getInstance().requestListOfStudies();
        	mAlreadyInitiated = true;
        }
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.studies_fragment, container, false);
		//return null;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("curChoice", mCurCheckPosition);
	}


	@Override
	public void notify(ClientMessages message) {
			switch (message){
			case STUDIES_LIST:
				Log.d ("fragment", "the listener for STUDIES_LIST has been called!");
				if (getActivity() != null){
					View listView = getActivity().findViewById(R.id.studiesListOutlet);
					listView.setVisibility(1);
					Fragment listFragment = new StudyListFragment();
					FragmentTransaction listTransaction = getFragmentManager().beginTransaction();
					// Replace whatever is in the fragment_container view with this fragment,
					// and add the transaction to the back stack
					listTransaction.replace(R.id.studiesListOutlet, listFragment);
					//transaction.addToBackStack(null);
					// Commit the transaction
					listTransaction.commit();
				}
			break;
			case STUDY_DETAILS:
				Log.d ("fragment", "the listener for STUDIES_LIST has been called!");
				if (getActivity() != null){
					View listView = getActivity().findViewById(R.id.studiesListOutlet);
					listView.setVisibility(1);
					//Fragment formsFragment = new StudyFormsFragment(Client.getInstance().getActualStudy());
					Fragment formsFragment = new StudyFormsFragment();
					FragmentTransaction listTransaction = getFragmentManager().beginTransaction();
					// Replace whatever is in the fragment_container view with this fragment,
					// and add the transaction to the back stack
					listTransaction.replace(R.id.studiesSubListOutlet, formsFragment);
					//transaction.addToBackStack(null);
					// Commit the transaction
					listTransaction.commit();
				}
		}
	}
}