package com.choschi.memdroid.data.rule;

import org.ksoap2.serialization.SoapObject;

import android.util.Log;

import com.choschi.memdroid.data.form.FormQuestion;
import com.choschi.memdroid.webservice.requests.ServerGetFormDefinitionResponse.Name;

@Deprecated
public class RuleFactory {
	
	@Deprecated
	public enum Type {
		
	}
	
	@Deprecated
	public static FormQuestion factory(SoapObject input){
		Log.d ("Rule factory",input.getProperty(Name.PRESENTATION_TYPE.toString()).toString());
		return null;
	}
}
