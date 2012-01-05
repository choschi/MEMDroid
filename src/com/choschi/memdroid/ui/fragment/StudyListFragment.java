package com.choschi.memdroid.ui.fragment;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.R;
import com.choschi.memdroid.Client.ClientMessages;
import com.choschi.memdroid.data.ListOfStudiesAdapter;
import com.choschi.memdroid.data.Study;
import com.choschi.memdroid.interfaces.ClientListener;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class StudyListFragment extends ListFragment implements ClientListener  {
	
    int mCurCheckPosition = Integer.MIN_VALUE;
	
    
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
        	Client.getInstance().registerClientListener(this);
        	//Client.getInstance().requestListOfStudies();
        	setListAdapter (new ListOfStudiesAdapter(getActivity(),R.layout.study_row,Client.getInstance().getListOfStudies()));
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
	        if (getListAdapter() != null){
	        	Study study = (Study)getListAdapter().getItem(mCurCheckPosition);
	        	Client.getInstance().requestListOfForms(study);
	        	Log.d ("fragment",""+mCurCheckPosition);
	        }
    	}
    }
    
	@Override
	public void notify(ClientMessages message) {
		switch (message){
		}
	}
	
}
