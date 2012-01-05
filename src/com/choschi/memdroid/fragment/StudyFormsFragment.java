package com.choschi.memdroid.fragment;

import java.util.List;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.Client.ClientMessages;
import com.choschi.memdroid.R;
import com.choschi.memdroid.data.ListOfFormsAdapter;
import com.choschi.memdroid.data.Study;
import com.choschi.memdroid.data.form.Form;
import com.choschi.memdroid.util.ClientListener;

public class StudyFormsFragment extends ListFragment implements ClientListener{

    int mCurCheckPosition = 0;
	Study study;
	
	/*
	public StudyFormsFragment (Study std){
		study = std;
	}
    */
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
        	Client.getInstance().registerClientListener(this);
        	List<Form> list = Client.getInstance().getActualStudy().getForms();
        	setListAdapter (new ListOfFormsAdapter(getActivity(),R.layout.study_row,list));
        }
       
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }

    /**
     * Helper function to show the details of a selected item, either by
     * displaying a fragment in-place in the current UI, or starting a
     * whole new activity in which it is displayed.
     */
    void showDetails(int index) {
    	if (index != mCurCheckPosition){
	        mCurCheckPosition = index;
	        /*
	        if (getListAdapter() != null){
	        	Study study = (Study)getListAdapter().getItem(mCurCheckPosition);
	        	Client.getInstance().requestListOfForms(study);
	        	Log.d ("fragment",""+mCurCheckPosition);
	        }
	        */
    	}
    }
    
	@Override
	public void notify(ClientMessages message) {
		switch (message){}
	}
}

