package com.choschi.memdroid.data;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import com.choschi.memdroid.webservice.requests.ServerGetFormDefinitionResponse.Name;

import android.util.Log;

public abstract class FormSoapObjectParser extends SoapObjectParser {
	public FormSoapObjectParser (SoapObject input){
		for (int i=0;i<input.getPropertyCount();i++){
			//Log.d ("rendering",input.getPropertyAsString(i));
			Object property = input.getProperty(i);
			PropertyInfo properties = new PropertyInfo();
			input.getPropertyInfo(i,properties);
			try{
				//Log.d ("handling primitive",properties.getName());
				this.handle((SoapPrimitive)property, properties.getName());
			}catch (Exception ex){
				//Log.d ("handling primitive",ex.getMessage());
				try{
					//Log.d ("handling object",properties.getName());
					this.handle((SoapObject)property, properties.getName());
				}catch (Exception nex){
					//Log.d ("handling object",nex.getMessage());
				}
			}
		}
	}
	
	protected void handle (SoapPrimitive property, String propertyName){
		boolean found = false;
		for (Name name : Name.values()){
			if (propertyName.equalsIgnoreCase(name.toString())){
				saveProperty (property.toString(), name);
				found = true;
			}
		}
		if (!found){
			Log.d("primitve renderer in "+this.getClass(),"no handle for "+propertyName);
		}
	}
	
	protected void handle (SoapObject property, String propertyName){
		boolean found = false;
		for (Name name : Name.values()){
			if (propertyName.equalsIgnoreCase(name.toString())){
				saveObject (property, name);
				found = true;
			}
		}
		if (!found){
			Log.d("object renderer in "+this.getClass(),"no handle for "+propertyName);
		}
	}
	
	protected abstract void saveProperty (String property,Name name);
	
	protected abstract void saveObject (SoapObject property, Name name);
}
