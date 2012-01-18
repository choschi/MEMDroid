package com.choschi.memdroid.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.choschi.memdroid.data.form.FormQuestion;
import com.choschi.memdroid.data.form.FormQuestionLabel;
import com.choschi.memdroid.interfaces.AdapterItem;


/**
 *
 * Creates the FormElements all the FormQuestions
 * 
 * @author Christoph Isch
 *
 */

public class FormQuestionFactory {
	
	// outer type
	public enum PresentationType{
		UNDEFINED ("undefined"),
		REAL ("real"),
		DATE ("date"),
		RADIO_BUTTON ("radiobutton"),
		DROPDOWN ("dropdown"),
		STRING ("string"),
		CHECKBOX ("checkbox"),
		SLIDER_1 ("slider1"),
		SLIDER_2 ("slider2"),
		INTEGER ("integer"),
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
	public enum DataType{
		UNDEFINED ("undefined"),
		NULLVALUE ("NULLVALUE"),
		INTEGER ("integer"),
		STRING ("string"),
		REAL ("real"),
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
	 * factory method for the FormElements
	 * 
	 * @param question
	 * @param context
	 * @return a form ui element
	 */
	
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
			Log.i ("FormElementFactory",question.getPresentationType()+":"+question.getDataType()+": one of the two types is not yet implemented");
		}else{
			switch (presentation){
				case RADIO_BUTTON:
					RadioGroup radios = new RadioGroup(context);
					if(question.getAllLabels() != null){
						for (FormQuestionLabel item : question.getAllLabels()){
							RadioButton radio = new RadioButton(context);
							radio.setText(item.toString());
							radio.setId(item.getValue());
							radio.setActivated(item.isDefault());
							radios.addView(radio);
						}
					}
					form.fillRight(radios, presentation);
				break;
				case DROPDOWN:
					List<AdapterItem> items = new ArrayList<AdapterItem>();
					items.addAll(question.getAllLabels());
					form.fillRight(createSpinnerForAdapter(context,question.getQuestionId(),items), presentation);
				break;
				case CHECKBOX:
					if(question.getAllLabels() != null){
						if (question.getAllLabels().size()> 1){
							Log.i ("FormQuestionFactory","who the heck had the idea use more than one checkbox!");
						}
						FormQuestionLabel item = question.getAllLabels().get(0);
						CheckBox box = new CheckBox(context);
						box.setText(item.toString());
						box.setId(item.getValue());
						box.setChecked(item.isDefault());
						form.fillRight(box,presentation);
					}
				break;
				case SLIDER_1:
				case SLIDER_2:
					SeekBar slider = new SeekBar(context);
					LayoutParams params = new LinearLayout.LayoutParams(200, LayoutParams.WRAP_CONTENT);
					slider.setLayoutParams(params);
					slider.setId(question.getQuestionId());
					form.fillRight(slider, presentation);
				break;
				case INTEGER:
				case STRING:
				case REAL:
				case DATE:
				case UNDEFINED:
				default:
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
	 * creates spinners for multiple choice and dropdown fields
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
