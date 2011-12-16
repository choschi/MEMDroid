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
	 * allows to set an empty body tag in envelope
	 */
	/*
	@Override
	public void writeBody(XmlSerializer writer) throws IOException{
		if (bodyOut != null){
			super.writeBody(writer);
			Log.d ("MemdocSoapErializationEnvelope","body is not empty");
		}else{
			Log.d ("MemdocSoapErializationEnvelope","body is empty");			
		}
	}
	*/
	/**
	 * try to suppress the header tag as I think this might be a source for error
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
