package com.choschi.memdroid.ui;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.choschi.memdroid.data.PatientField;
import com.choschi.memdroid.interfaces.AdapterItem;
import com.choschi.memdroid.util.FixedLists;


/**
 * 
 * @author Christoph Isch
 *
 * Creates the PatientFormElements all sorts of PatientFields
 */

public class PatientFieldFactory {
	
	// outer type
		
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
	
	// inner type
	
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
	
	/**
	 * 
	 * factory method for the PatientFormElements
	 * 
	 * @param field
	 * @param context
	 * @return a patient form ui element
	 */
	
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
							form.fillRight(createSpinnerForAdapter(context,field.getFieldId(),FixedLists.getInstance().getCountries(null)), type);
						break;
						case DEPARTMENT:
							// Department can not be chosen anymore at this point 							
							return null;
						case LANGUAGE:
							form.fillRight(createSpinnerForAdapter(context,field.getFieldId(),FixedLists.getInstance().getLanguages(null)), type);
						break;
						case GENDER:
							form.fillRight(createSpinnerForAdapter(context,field.getFieldId(),FixedLists.getInstance().getGenders(null)), type);
						break;
					}
				break;
				case DATE:
				case ALPHANUM:
				case NUMBER:
				case TEXT:
					EditText inputView = new EditText(context);
					inputView.setWidth(200);
					form.fillRight(inputView,type);
				break;
			}
		}
		return form;
	}
	
	/**
	 * 
	 * creates spinners for multiple choice fields
	 * 
	 * @param context
	 * @param id
	 * @param data
	 * @return a spinner for the related data
	 */
	
	private static View createSpinnerForAdapter (Context context, int id, List<AdapterItem> data){
		Spinner spinner = new Spinner(context);
		spinner.setId(id);
		ArrayAdapter<AdapterItem> adapter = new ArrayAdapter<AdapterItem>(context,
		          android.R.layout.simple_spinner_item, data);
		spinner.setAdapter(adapter);
	    return spinner;
	}
	


}
