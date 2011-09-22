package com.choschi.memdroid.webservice;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import android.util.Log;

/**
 * Base class and factory for the webservice response to class renderers 
 * @author choschi
 * 
 */


public class Result {
	
	/**
	 * allowed primitive responses
	 */
	
	private enum Primitive {
		getServerSessionIdResponse
	};
	
	/**
	 * allowed complex responses
	 */
	
	private enum Complex {
		loginResponse
	}
	
	public static final String TAG = "Result";
	
	/**
	 * factory method for the renderers
	 * @param soapResponse
	 * @return
	 */
	
	public static Result factory (Object soapResponse){
		Result result = null;
		try{
			result = Result.handlePrimitive((SoapPrimitive) soapResponse);
		}catch (Exception ex){Log.d(TAG,"not a primitive: "+ex.getMessage());}
		try{
			result = Result.handleObject((SoapObject) soapResponse);
		}catch (Exception ex){Log.d(TAG,"not an object "+ex.getMessage());}
		return result;
	}
	
	/**
	 * private factory for primitive types
	 * @param soapResponse
	 * @return an appropriate Result object
	 */
	
	private static Result handlePrimitive (SoapPrimitive soapResponse){
		Primitive type = null;
		for (Primitive possible : Primitive.values()){
			if (soapResponse.getName().equalsIgnoreCase(possible.name())){
				type = possible;
			}
		}
		switch (type){
			case getServerSessionIdResponse:
				return new ServerSessionIdResponse (soapResponse);
		}
		return null;
	}
	
	/**
	 * private factory for complex types
	 * @param soapResponse
	 * @return an appropriate Result object
	 */
	
	private static Result handleObject (SoapObject soapResponse){
		Complex type = null;
		for (Complex possible : Complex.values()){
			if (soapResponse.getName().equalsIgnoreCase(possible.name())){
				type = possible;
			}
		}
		switch (type){
			case loginResponse:
				return new ModuleLoginResponse (soapResponse);
		}
		return null;
	}
}
