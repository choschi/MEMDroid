package com.choschi.memdroid.interfaces;

import org.ksoap2.serialization.SoapObject;

/**
 * 
 * @author Christoph Isch
 *
 * Serializable is an object that can put itself in an SoapObject to be sent to the webservice
 *
 */

public interface Serializable {
	
	/**
	 * render the object into a SoapObject
	 * @param namespace
	 * @return SoapObject representation of the object
	 */
	
	public SoapObject toSoapObject(String namespace);
}
