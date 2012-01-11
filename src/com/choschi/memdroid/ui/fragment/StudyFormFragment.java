package com.choschi.memdroid.ui.fragment;


import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.Client.ClientMessages;
import com.choschi.memdroid.R;
import com.choschi.memdroid.data.ListOfAdapterItemAdapter;
import com.choschi.memdroid.data.Patient;
import com.choschi.memdroid.data.PatientField;
import com.choschi.memdroid.data.PatientFieldData;
import com.choschi.memdroid.data.form.Form;
import com.choschi.memdroid.data.form.FormQuestion;
import com.choschi.memdroid.interfaces.AdapterItem;
import com.choschi.memdroid.interfaces.ClientListener;
import com.choschi.memdroid.ui.FormElement;
import com.choschi.memdroid.ui.FormQuestionFactory;
import com.choschi.memdroid.ui.PatientFieldFactory;
import com.choschi.memdroid.ui.PatientFormElement;

/**
 * 
 * Fragment which displays the patient search relevant views and inits the corresponding actions
 * 
 * @author Christoph Isch
 *
 */

public class StudyFormFragment extends Fragment implements ClientListener,OnClickListener  {
	
	private List<FormElement> formChildren;
	
	private Form form;
	
	public StudyFormFragment(Form form){
		this.form = form;
	}
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null){
        	Client.getInstance().registerClientListener(this);
        	Button newButton = (Button)getActivity().findViewById(R.id.formSaveButton);
        	newButton.setOnClickListener(this);
        }
    }
    
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    	View view =  inflater.inflate(R.layout.study_form_fragment, container, false);
    	View scroller = view.findViewById(R.id.formScrollerContainer);
    	LinearLayout layout = (LinearLayout)scroller;
    	List<FormQuestion> questions = form.getQuestions();
    	formChildren = new ArrayList<FormElement>();
    	for (FormQuestion question : questions){
    		FormElement child = FormQuestionFactory.factory(question,getActivity().getBaseContext());
    		if (child != null){
    			if (formChildren.size() > 0){
    				//TODO uncomment this line later
    				//formChildren.get(formChildren.size()-1).setNextId(child.getViewId());
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
			/*
			case PATIENT_FIELDS:
				Log.i("received", "patientfields");
			break;
			case PATIENT_SEARCH:
				List<AdapterItem> patients = (List<AdapterItem>)Client.getInstance().getPatients();
				ListView list = (ListView) this.getActivity().findViewById(R.id.patientSearchResult);
				list.setOnItemClickListener(this);
				list.setAdapter(new ListOfAdapterItemAdapter(getActivity().getBaseContext(),R.layout.study_row,patients));
			break;
			*/
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.formSaveButton:
				Log.i("studyFormFragment","saveForm pressed");
				
				// First of all extract the data from the PatientFormElements
				/*
				PatientFieldData[] search = new PatientFieldData[formChildren.size()];
				int counter = 0;
				for (PatientFormElement item : formChildren){
					search[counter] = item.getPatientFieldData();
					counter++;
				}
				
				// then search for the patient
				
				Client.getInstance().searchPatient(search);
				*/
			break;
			
		}
	}
	/*
    void selectPatient(int index) {
        ListView list = (ListView) this.getActivity().findViewById(R.id.patientSearchResult);
        Patient patient = (Patient)list.getItemAtPosition(index);
        Client.getInstance().setActualPatient(patient);
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		selectPatient (arg2);
	}
	*/
}

