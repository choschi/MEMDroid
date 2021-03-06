package com.choschi.memdroid.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.Client.ClientMessages;
import com.choschi.memdroid.R;
import com.choschi.memdroid.data.ListOfAdapterItemAdapter;
import com.choschi.memdroid.data.form.Form;
import com.choschi.memdroid.interfaces.AdapterItem;
import com.choschi.memdroid.interfaces.ClientListener;

/**
 * 
 * displays the list of the forms on the study fragment view
 * 
 * @author Christoph Isch
 *
 */

public class StudyFormsFragment extends ListFragment implements ClientListener{

    int mCurCheckPosition = 0;
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
        	Client.getInstance().registerClientListener(this);
        	List<AdapterItem> list = new ArrayList<AdapterItem>();
        	list.addAll(Client.getInstance().getActualStudy().getForms());
        	setListAdapter (new ListOfAdapterItemAdapter(getActivity(),R.layout.study_row,list));
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
        mCurCheckPosition = index;
        if (getListAdapter() != null){
        	Form form = (Form)getListAdapter().getItem(mCurCheckPosition);
        	Client.getInstance().showForm(form);
        }
    }
    
	@Override
	public void notify(ClientMessages message) {
		switch (message){}
	}
}

