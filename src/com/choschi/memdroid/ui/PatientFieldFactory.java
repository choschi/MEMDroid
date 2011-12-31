package com.choschi.memdroid.ui;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.choschi.memdroid.R;
import com.choschi.memdroid.data.PatientField;

public class PatientFieldFactory {
	
	public enum FieldType{
		UNDEFINED ("undefined"),
		MULTIPLECHOICE ("multiplechoice"),
		DATE ("date"),
		ALPHANUM ("alphanumeric"),
		TEXT ("all"),
		NUMBER ("number"),
		;
		private String type;
		
		FieldType (String type){
			this.type = type;
		}
		
		@Override
		public String toString(){
			return type;
		}
	}
	
	public enum FixedFieldType{
		UNDEFINED ("undefined"),
		COUNTRY ("country"),
		COUNTRY2 ("country2"),
		DEPARTMENT ("department"),
		NONE ("none"),
		MRN ("mrn"),
		SSN ("ssn"),
		FIRSTNAME ("firstname"),
		LASTNAME ("lastname"),
		DATE_OF_BIRTH ("dob"),
		LANGUAGE ("lang"),
		GENDER ("gender"),
		;
		private String type;
		
		FixedFieldType (String type){
			this.type = type;
		}
		
		@Override
		public String toString(){
			return type;
		}
	}
	
	
	public static PatientFormElement factory (PatientField field, Context context){
		FieldType type = FieldType.UNDEFINED;
		for (FieldType candidate : FieldType.values()){
			if (field.getFieldType().compareToIgnoreCase(candidate.toString()) == 0){
				type = candidate;
			}
		}
		FixedFieldType fixedType = FixedFieldType.UNDEFINED;
		for (FixedFieldType candidate : FixedFieldType.values()){
			if (field.getFixedFieldType().compareToIgnoreCase(candidate.toString()) == 0){
				fixedType = candidate;
			}
		}
		PatientFormElement form = new PatientFormElement(context,field);
		TextView label = new TextView(context);
	    label.setText(field.getLabel());
	    form.fillLeft(label);
		if (fixedType == FixedFieldType.UNDEFINED || type == FieldType.UNDEFINED){
			Log.d ("PatientFieldFactory",field.getFieldType()+":"+field.getFixedFieldType()+": einer der beiden typen ist noch nicht implementiert");
		}else{
			switch (type){
				case MULTIPLECHOICE:
					switch (fixedType){
						case COUNTRY:
						case COUNTRY2:
						    form.fillRight(createNewSpinner(context,R.array.countries,field.getFieldId()),type);
						break;
						case DEPARTMENT:
							
							// ModuleUserDataResponse data = Client.getInstance().getUserData();
							/*
							PatientSpinner departmentSpinner = new PatientSpinner(context);
							departmentSpinner.setId(field.getFieldId());
							//TODO put list of departments here
							ArrayAdapter<CharSequence> departmentAdapter = ArrayAdapter.createFromResource(
						            context, , android.R.layout.simple_spinner_item);
						    departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						    departmentSpinner.setAdapter(departmentAdapter);
						    
						    */
							// TextView department = new TextView(context);
							// department.setText(data.getDepartmentId());
							// form.fillRight(department,type);
							return null;
						//break;
						case LANGUAGE:
							form.fillRight(createNewSpinner(context,R.array.languages,field.getFieldId()),type);
						break;
						case GENDER:
							form.fillRight(createNewSpinner(context,R.array.genders,field.getFieldId()),type);
						break;
					}
				break;
				case ALPHANUM:
				case NUMBER:
				case TEXT:
					EditText inputView = new EditText(context);
					inputView.setWidth(200);
					form.fillRight(inputView,type);
				break;
				case DATE:
					return null;
					/*
					DatePicker picker = new DatePicker(context);
					picker.setCalendarViewShown(false);
					form.fillRight(picker,type);
					*/
				//break;
			}
		}
		return form;
	}
	
	private static Spinner createNewSpinner(Context context, int array,int id){
		PatientSpinner spinner = new PatientSpinner(context);
		spinner.setId(id);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            context, array, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(adapter);
	    return spinner;
	}
}
