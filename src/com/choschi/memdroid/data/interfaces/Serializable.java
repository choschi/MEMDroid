package com.choschi.memdroid.data.interfaces;

import org.ksoap2.serialization.SoapObject;

public interface Serializable {
	public SoapObject toSoapObject(String namespace);
}
