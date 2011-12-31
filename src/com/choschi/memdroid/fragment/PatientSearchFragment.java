package com.choschi.memdroid.fragment;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.choschi.memdroid.R;
import com.choschi.memdroid.data.PatientField;
import com.choschi.memdroid.data.PatientFieldData;
import com.choschi.memdroid.ui.PatientFieldFactory;
import com.choschi.memdroid.ui.PatientFormElement;
import com.choschi.memdroid.util.ClientListener;
import com.choschi.memdroid.webservice.Client;

public class PatientSearchFragment extends Fragment implements ClientListener,OnClickListener  {
	
	private List<PatientFormElement> formChildren;
	//private Integer[] idsToDisplay = new Integer[]{2,5,6,8};
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null){
        	Client.getInstance().registerClientListener(this);
        	Button newButton = (Button)getActivity().findViewById(R.id.patientSearchSearchButton);
        	newButton.setOnClickListener(this);
        }
    }
    
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    	View view =  inflater.inflate(R.layout.patient_search_fragment, container, false);
    	View scroller = view.findViewById(R.id.patientSearchScrollerContainer);
    	LinearLayout layout = (LinearLayout)scroller;
    	List<PatientField> fields = Client.getInstance().getPatientFieldsSearch();
    	formChildren = new ArrayList<PatientFormElement>();
    	//List<Integer> toDisplay = Arrays.asList(idsToDisplay);
    	for (PatientField field : fields){
    		//if (toDisplay.contains(field.getFieldId())){
	    		PatientFormElement child = PatientFieldFactory.factory(field,getActivity().getBaseContext());
	    		if (child != null){
	    			if (formChildren.size() > 0){
	    				formChildren.get(formChildren.size()-1).setNextId(child.getViewId());
	    			}
	    			formChildren.add(child);
	    			layout.addView(child, layout.getChildCount());
	    		}
    		//}
    	}
    	return view;
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


	@Override
	public void notify(int message) {
		switch (message){
			case Client.PATIENT_FIELDS:
				Log.d("received", "patientfields");
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.patientSearchSearchButton:
				Log.d("patientFragment","searchPatient pressed");
				PatientFieldData[] search = new PatientFieldData[formChildren.size()];
				int counter = 0;
				for (PatientFormElement item : formChildren){
					search[counter] = item.getPatientFieldData();
					counter++;
				}
				Client.getInstance().searchPatient(search);
			break;
			
		}
	}
}

