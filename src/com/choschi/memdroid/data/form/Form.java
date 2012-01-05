package com.choschi.memdroid.data.form;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.FormDefinition;
import com.choschi.memdroid.data.FormSoapObjectParser;
import com.choschi.memdroid.interfaces.AdapterItem;

public class Form extends FormSoapObjectParser implements AdapterItem{
	
	private String id;
	private String name;
	private String type;
	private String date;
	private String studyName;
	private String version;
	private FormDefinition definition;
	
	public Form(SoapObject input) {
		super(input);
	}
	

	@Override
	protected void saveProperty(String property, Name name) {
		switch (name){
		case ID:
			id = property;
			break;
		case NAME:
			this.name = property;
			break;
		case TYPE:
			type = property;
			break;
		}
	}

	@Override
	protected void saveObject(SoapObject property, Name name) {		
	}
	
	public boolean addDefinition (FormDefinition def){
		if (definition == null){
			definition = def;
			return true;
		}
		return false;
	}

	@Override
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the studyName
	 */
	public String getStudyName() {
		return studyName;
	}

	/**
	 * @param studyName the studyName to set
	 */
	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	@Override
	public String toString(){
		return studyName;
	}	
}
