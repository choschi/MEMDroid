package com.choschi.memdroid.data.form;

import java.util.List;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import com.choschi.memdroid.data.FormSoapObjectParser;
import com.choschi.memdroid.interfaces.AdapterItem;

/**
 * 
 * Form container class, strips all the form data together
 * 
 * @author Christoph Isch
 *
 */

public class Form extends FormSoapObjectParser implements AdapterItem{
	
	private String id;
	private String name;
	private String type;
	private String date;
	private String studyName;
	private String version;
	private FormDefinition definition;
	
	/**
	 * same problem here as it is with study, the data is just put plain in an array
	 * so no FormSoapObjectParser black magic
	 * @param input
	 */
	
	public Form(SoapObject input) {
		super(input);
		id = ((SoapPrimitive)input.getProperty(0)).toString();
		name = ((SoapPrimitive)input.getProperty(1)).toString();
		type = ((SoapPrimitive)input.getProperty(2)).toString();
		date = ((SoapPrimitive)input.getProperty(3)).toString();
		studyName = ((SoapPrimitive)input.getProperty(4)).toString();
		version = ((SoapPrimitive)input.getProperty(5)).toString();
	}
	

	@Override
	protected void saveProperty(String property, Name name) {
	}

	@Override
	protected void saveObject(SoapObject property, Name name) {		
	}
	
	/**
	 * set the definition fot this form
	 * @param def
	 * @return true on success, false on already defined
	 */
	
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
		return name;
	}
	
	/**
	 * 
	 * @return all the questions of the form
	 */
	
	public List<FormQuestion> getQuestions(){
		return definition.getQuestions();
	}
	/**
	 * get the sub forms
	 * @return list of SubForm
	 */
	
	public List<SubForm> getSubforms(){
		return definition.getSubforms();
	}
}
