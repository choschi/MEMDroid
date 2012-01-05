package com.choschi.memdroid.ui.fragment;


import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.R;
import com.choschi.memdroid.Client.ClientMessages;
import com.choschi.memdroid.data.PatientField;
import com.choschi.memdroid.data.PatientFieldData;
import com.choschi.memdroid.interfaces.ClientListener;
import com.choschi.memdroid.ui.PatientFieldFactory;
import com.choschi.memdroid.ui.PatientFormElement;

public class PatientNewFragment extends Fragment implements ClientListener,OnClickListener  {
		
	private List<PatientFormElement> formChildren;
	
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
    	View view =  inflater.inflate(R.layout.patient_new_fragment, container, false);
    	View scroller = view.findViewById(R.id.patientNewScrollerContainer);
    	LinearLayout layout = (LinearLayout)scroller;
    	List<PatientField> fields = Client.getInstance().getPatientFieldsInsert();
    	formChildren = new ArrayList<PatientFormElement>();
    	for (PatientField field : fields){
    		PatientFormElement child = PatientFieldFactory.factory(field,getActivity().getBaseContext());
    		if (child != null){
    			if (formChildren.size() > 0){
    				formChildren.get(formChildren.size()-1).setNextId(child.getViewId());
    			}
    			formChildren.add(child);
    			layout.addView(child, layout.getChildCount());
    		}
    	}
    	return view;
    }
    
    
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


	@Override
	public void notify(ClientMessages message) {
		switch (message){
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			
			
			
			case R.id.patientNewSubmitButton:
				
				// First of all extract the data from the PatientFormElements
				
				PatientFieldData[] data = new PatientFieldData[formChildren.size()+1];
				
				// OMG, dirty hack due to the need of the departementId in the fields too, not only the request header
				
				data[0] = new PatientFieldData ("1",Client.getInstance().getDepartment().getId());
				int counter = 1;
				for (PatientFormElement item : formChildren){
					data[counter] = item.getPatientFieldData();
					counter++;
				}
				
				// Send the new patient data to the server
				
				Client.getInstance().savePatient(data);
			break;
		}
	}
}

