package com.choschi.memdroid.data.form;

import org.ksoap2.serialization.SoapObject;

import com.choschi.memdroid.data.SoapObjectParser;
import com.choschi.memdroid.data.interfaces.AdapterItem;

//TODO alter this class to comply with the newly introduced scheme for the properties handling and generation

public class Form extends SoapObjectParser implements AdapterItem{
	
	private int id;
	private String name;
	private String type;
	private String date;
	private String studyName;
	private String version;
	private Object definition;
	
	public Form(SoapObject data) {
		for (int i=0;i<data.getPropertyCount();i++){
			setValue(i,data.getPropertyAsString(i));
		}
	}
	
	protected void setValue(int index,String value){
		switch (index){
			case 0:
				this.setId(parseInteger(value));
			break;
			case 1:
				this.setName(value);
			break;
			case 2:
				this.setType(value);
			break;
			case 3:
				this.setDate(value);
			break;
			case 4:
				this.setStudyName(value);
			break;
			case 5:
				this.setVersion(value);
			break;
		}
	}
	
	public boolean addDefinition (Object def){
		if (definition == null){
			definition = def;
			return true;
		}
		return false;
	}

	@Override
	public String getId() {
		return ""+id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
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
