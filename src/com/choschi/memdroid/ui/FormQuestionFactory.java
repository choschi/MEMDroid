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
import com.choschi.memdroid.data.form.FormQuestion;
import com.choschi.memdroid.interfaces.AdapterItem;
import com.choschi.memdroid.util.FixedLists;


/**
 * 
 * @author Christoph Isch
 *
 * Creates the PatientFormElements all sorts of PatientFields
 */

public class FormQuestionFactory {
	
	// outer type
	//TODO get real values here
	public enum PresentationType{
		UNDEFINED ("undefined"),
		MULTIPLECHOICE ("multiplechoice"),
		DATE ("date"),
		ALPHANUM ("alphanumeric"),
		TEXT ("all"),
		NUMBER ("number"),
		;
		private String type;
		
		PresentationType (String type){
			this.type = type;
		}
		
		@Override
		public String toString(){
			return type;
		}
	}
	
	// inner type
	//TODO get real values here
	public enum DataType{
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
		
		DataType (String type){
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
	 * @param question
	 * @param context
	 * @return a patient form ui element
	 */
	
	//TODO change the factory so that it makes something useful with the FormQuestion Elements
	
	public static FormElement factory (FormQuestion question, Context context){
		PresentationType presentation = PresentationType.UNDEFINED;
		for (PresentationType candidate : PresentationType.values()){
			if (question.getPresentationType().compareToIgnoreCase(candidate.toString()) == 0){
				presentation = candidate;
			}
		}
		DataType data = DataType.UNDEFINED;
		for (DataType candidate : DataType.values()){
			if (question.getDataType().compareToIgnoreCase(candidate.toString()) == 0){
				data = candidate;
			}
		}
		FormElement form = new FormElement(context,question);
		
		TextView label = new TextView(context);
	    label.setText(question.getLabel());
	    form.fillLeft(label);
		if (data == DataType.UNDEFINED || presentation == PresentationType.UNDEFINED){
			Log.d ("PatientFieldFactory",question.getPresentationType()+":"+question.getDataType()+": einer der beiden typen ist noch nicht implementiert");
		}else{
			switch (presentation){
				case MULTIPLECHOICE:
					switch (data){
						case COUNTRY:
						case COUNTRY2:
							form.fillRight(createSpinnerForAdapter(context,question.getQuestionId(),FixedLists.getInstance().getCountries(null)), presentation);
						break;
						case DEPARTMENT:
							// Department can not be chosen anymore at this point 							
							return null;
						case LANGUAGE:
							form.fillRight(createSpinnerForAdapter(context,question.getQuestionId(),FixedLists.getInstance().getLanguages(null)), presentation);
						break;
						case GENDER:
							form.fillRight(createSpinnerForAdapter(context,question.getQuestionId(),FixedLists.getInstance().getGenders(null)), presentation);
						break;
					}
				break;
				case DATE:
				case ALPHANUM:
				case NUMBER:
				case TEXT:
					EditText inputView = new EditText(context);
					inputView.setWidth(200);
					form.fillRight(inputView,presentation);
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
