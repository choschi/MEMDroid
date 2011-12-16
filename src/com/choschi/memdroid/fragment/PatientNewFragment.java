package com.choschi.memdroid.fragment;


import android.app.Fragment;
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

public class PatientNewFragment extends Fragment implements ClientListener,OnClickListener  {
		
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null){
        	Client.getInstance().registerClientListener(this);
        	Button newButton = (Button)getActivity().findViewById(R.id.patientNewSubmitButton);
        	newButton.setOnClickListener(this);
        }
   }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    	return inflater.inflate(R.layout.patient_new_fragment, container, false);
    	//return null;
    }
    
    
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


	@Override
	public void notify(int message) {
		switch (message){
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.patientSearchSearchButton:
				Log.d("patienFragment","searchPatient pressed");
			break;
		}
	}
}

