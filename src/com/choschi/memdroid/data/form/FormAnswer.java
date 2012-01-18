package com.choschi.memdroid.data.form;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.interfaces.Serializable;

/**
 * 
 * FormAnswer built to wrap data for being sent back to the server
 * 
 * @author Christoph Isch
 *
 */

public class FormAnswer implements Serializable {
	
	private String questionName;
	private String value;
	
	/**
	 * constructor
	 * @param name
	 * @param val
	 */
	
	public FormAnswer (String name, String val){
		questionName = name;
		value = val;
	}
	
	@Override
	public SoapObject toSoapObject(String namespace) {
		SoapObject out = new SoapObject (namespace,"answers");
		out.addProperty ("questionName",questionName);
		out.addProperty ("value",value);
		return out;
	}

}
