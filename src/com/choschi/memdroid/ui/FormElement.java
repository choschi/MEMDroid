package com.choschi.memdroid.ui;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.choschi.memdroid.Client;
import com.choschi.memdroid.data.PatientField;
import com.choschi.memdroid.data.PatientFieldData;
import com.choschi.memdroid.data.form.FormQuestion;
import com.choschi.memdroid.interfaces.AdapterItem;
import com.choschi.memdroid.ui.FormQuestionFactory.PresentationType;

/**
 * 
 * Provides a view container with two rows for the PatientFields
 * the first row is considered to contain a label, the second one to contain the form element
 * 
 * @author Christoph Isch
 * 
 */

public class FormElement extends LinearLayout implements OnTouchListener,OnDateSetListener {

	private LinearLayout left;
	private LinearLayout right;
	private FormQuestion question;
	private PresentationType type;

	
	/**
	 * 
	 * @param context
	 * @param question
	 */
	
	public FormElement(Context context, FormQuestion question) {
		super(context);
		this.question = question;
		this.setOrientation(HORIZONTAL);
		left = new LinearLayout(context);
		right = new LinearLayout(context);
		ViewGroup.LayoutParams params = new LayoutParams(300, 30);
		left.setLayoutParams(params);
		this.addView(left);
		this.addView(right);
	}

	/**
	 * 
	 * adds a view to the first row
	 * 
	 * @param insert
	 */
	
	public void fillLeft (View insert){
		left.addView(insert);
	}
	
	/**
	 * 
	 * adds a view to the second row, by defining the type the listener for the corresponding object is set on this
	 * 
	 * @param insert
	 * @param type
	 */
	
	public void fillRight(View insert,PresentationType type){
		this.type = type;
		switch (this.type){
			case DATE:
				EditText child = (EditText)insert;
				child.setOnTouchListener(this);
			break;
		}
		right.addView(insert);
	}
	
	/**
	 * set next id for the "tabIndex" property on the form object
	 * @param id
	 */
	
	public void setNextId (int id){
		View child = right.getChildAt(0);
		child.setNextFocusForwardId(id);
	}
	
	/**
	 * 
	 * @return id of the view in the second row
	 */
	
	public int getViewId (){
		View child = right.getChildAt(0);
		return child.getId();
	}

	/**
	 * 
	 * creates a PatientFieldData Object from the actual state
	 * 
	 * @return actual patient field data
	 */
	//TODO change whenever you know what the type of the field is needed to send back the form data
	public PatientFieldData getPatientFieldData(){
		String val = "";
		View child = right.getChildAt(0);
		switch (type){
			case MULTIPLECHOICE:
				val = ((AdapterItem)((Spinner)child).getSelectedItem()).getId();
			break;
			default:
				val = ((EditText)child).getText().toString();
			break;
		}
		return new PatientFieldData (""+question.getQuestionId(),val);
	}


	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		EditText child = (EditText)right.getChildAt(0);
		child.setText(dayOfMonth+"."+(monthOfYear+1)+"."+year);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		View child = right.getChildAt(0);
		if (v.getId()==child.getId()){
			Client.getInstance().showDatePicker(this);
		}
		return true;
	}
}