package com.choschi.memdroid.webservice;

import java.io.IOException;

import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlSerializer;


/**
 * 
 * @author choschi
 * The SoapSerializationEnvelope class implements a few changes to the standard behaviour
 * read more in the methods doc section
 */

public class MemdocSoapSerializationEnvelope extends SoapSerializationEnvelope {
	
	public MemdocSoapSerializationEnvelope(int version) {
		super(version);
	}
	
	/**
	 * any empty header object in a call to the server results in a http 500 error, thus this modification to the original envelope was necessary
	 */

	@Override
	public void write(XmlSerializer writer) throws IOException {
		writer.setPrefix("i", xsi);
		writer.setPrefix("d", xsd);
		writer.setPrefix("c", enc);
		writer.setPrefix("v", env);
		writer.startTag(env, "Envelope");
		if (headerOut != null){
			writer.startTag(env, "Header");
			writeHeader(writer);
			writer.endTag(env, "Header");
		}
		writer.startTag(env, "Body");
		if (bodyOut != null){
			writeBody(writer);
		}
		writer.endTag(env, "Body");
		writer.endTag(env, "Envelope");
	}
	
	/**
	 * directly returns the servers response without further ado
	 */
	
	@Override
	public Object getResponse(){
		return this.bodyIn;
	}

}
