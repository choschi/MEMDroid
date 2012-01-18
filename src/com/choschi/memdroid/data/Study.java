package com.choschi.memdroid.data;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import com.choschi.memdroid.data.form.Form;
import com.choschi.memdroid.interfaces.AdapterItem;

/**
 * 
 * represents a study
 * 
 * @author Christoph Isch
 *
 */

public class Study extends SoapObjectParser implements AdapterItem{
	
	private String id;
	private String name;
	private String title;
	private String type;
	private String startDate;
	private String endDate;
	
	private List<Form> forms;
	
	/**
	 * constructor looks totally wrong, because it is not done with FormSoapObjectParser
	 * unfortunately the whole study data is just put plain in an array, only the position
	 * of the elements makes it to be the id or name etc.  
	 * @param input
	 */
	
	public Study (SoapObject input){
		forms = new ArrayList<Form>();
		id = ((SoapPrimitive)input.getProperty(0)).toString();
		name = ((SoapPrimitive)input.getProperty(1)).toString();
		title = ((SoapPrimitive)input.getProperty(2)).toString();
		type = ((SoapPrimitive)input.getProperty(3)).toString();
		startDate = ((SoapPrimitive)input.getProperty(4)).toString();
		endDate = ((SoapPrimitive)input.getProperty(5)).toString();
	}
	
	/**
	 * get the studies id
	 */
	
	public String getId() {
		return id;
	}
	
	/**
	 * get the studies name
	 * @return name
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * set the studies name
	 * @param name
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
	
	/**
	 * add a form to this study
	 * @param form
	 */
	
	public void addForm(Form form){
		forms.add(form);
	}
	
	/**
	 * get the lis of forms for this sridy
	 * @return a list of forms
	 */
	
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
