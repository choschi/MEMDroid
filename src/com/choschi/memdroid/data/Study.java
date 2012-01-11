package com.choschi.memdroid.data;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import com.choschi.memdroid.data.form.Form;
import com.choschi.memdroid.interfaces.AdapterItem;


public class Study extends SoapObjectParser implements AdapterItem{
	
	private String id;
	private String name;
	private String title;
	private String type;
	private String startDate;
	private String endDate;
	
	private List<Form> forms;
	
	public Study (SoapObject input){
		forms = new ArrayList<Form>();
		id = ((SoapPrimitive)input.getProperty(0)).toString();
		name = ((SoapPrimitive)input.getProperty(1)).toString();
		title = ((SoapPrimitive)input.getProperty(2)).toString();
		type = ((SoapPrimitive)input.getProperty(3)).toString();
		startDate = ((SoapPrimitive)input.getProperty(4)).toString();
		endDate = ((SoapPrimitive)input.getProperty(5)).toString();
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

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
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	@Override
	public String toString(){
		return title;
	}
	
	public void addForm(Form form){
		forms.add(form);
	}
	
	public List<Form> getForms(){
		return forms;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}
