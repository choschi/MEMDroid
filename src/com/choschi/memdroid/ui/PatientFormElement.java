package com.choschi.memdroid.ui;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.choschi.memdroid.data.PatientField;
import com.choschi.memdroid.data.PatientFieldData;
import com.choschi.memdroid.ui.PatientFieldFactory.FieldType;

public class PatientFormElement extends LinearLayout implements OnItemSelectedListener {

	private LinearLayout left;
	private LinearLayout right;
	private PatientField field;
	private String value;
	
	public PatientFormElement(Context context, PatientField field) {
		super(context);
		this.field = field;
		this.setOrientation(HORIZONTAL);
		left = new LinearLayout(context);
		right = new LinearLayout(context);
		ViewGroup.LayoutParams params = new LayoutParams(300, 30);
		left.setLayoutParams(params);
		this.addView(left);
		this.addView(right);
	}

	public void fillLeft (View insert){
		left.addView(insert);
	}
	
	public void fillRight(View insert,FieldType type){
		switch (type){
			case MULTIPLECHOICE:
				((Spinner)insert).setOnItemSelectedListener(this);
			break;
			case DATE:
				Log.d ("FormElement","added date thing");
			break;
		}
		right.addView(insert);
	}
	
	public void setNextId (int id){
		View child = right.getChildAt(0);
		child.setNextFocusForwardId(id);
	}
	
	public int getViewId (){
		View child = right.getChildAt(0);
		return child.getId();
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Log.d("Spinner", "selected"+arg2);
		value = arg0.getItemAtPosition(arg2).toString();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		Log.d ("Spinner", "nothing selected");
		value = "";
	}
	
	public String getValue(){
		return value;
	}
	
	public PatientFieldData getPatientFieldData(){
		if (value == null){
			View child = right.getChildAt(0);
			value = ((EditText)child).getText().toString();
		}
		return new PatientFieldData (""+field.getFieldId(),value);
	}
}