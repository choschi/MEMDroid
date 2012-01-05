package com.choschi.memdroid.data;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import com.choschi.memdroid.data.form.Form;


public class Study extends SoapObjectParser {
	
	private String id;
	private String name;
	private String type;
	private String startDate;
	private String endDate;
	
	private List<Form> forms;
	
	@Deprecated
	public Study (String data){
		String[] values = this.parseData(data);
		for (int i=0;i<values.length;i++){
			if (values[i].length()>1){
				setValue(i,dataToValue(values[i]));
			}
		}
	}
	
	public Study (SoapObject input){
		forms = new ArrayList<Form>();
		id = ((SoapPrimitive)input.getProperty(0)).toString();
		name = ((SoapPrimitive)input.getProperty(1)).toString();
		type = ((SoapPrimitive)input.getProperty(2)).toString();
		startDate = ((SoapPrimitive)input.getProperty(3)).toString();
		endDate = ((SoapPrimitive)input.getProperty(4)).toString();
	}
	
	
	@Deprecated
	protected String[] parseData(String originalData){
		String rawData = originalData.substring(8, originalData.length()-1);
		return rawData.split(";");
	}
	@Deprecated
	protected String dataToValue (String data){
		data = data.trim();
		return data.substring(5,data.length());
	}
	@Deprecated
	protected void setValue(int index,String value){
		switch (index){
			case 0:
				this.setId(value);
			break;
			case 1:
				this.setName(value);
			break;
			case 2:
				this.setType(value);
			break;
			case 3:
				this.setStartDate(value);
			break;
			case 4:
				this.setEndDate(value);
			break;
		}
	}


	public String getId() {
		return id;
	}

	
	@Deprecated
	public void setId(String id) {
		this.id = id;
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
		return "Study: "+this.getName()+"("+this.getId()+") from "+this.getStartDate()+" till "+this.getEndDate();
	}
	
	public void addForm(Form form){
		forms.add(form);
	}
	
	public List<Form> getForms(){
		return forms;
	}
}
