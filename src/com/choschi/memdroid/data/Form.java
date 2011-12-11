package com.choschi.memdroid.data;

public class Form {
	
	private String id;
	private String name;
	private String type;
	private String date;
	
	
	public Form(String data) {
		String[] values = this.parseData(data);
		for (int i=0;i<values.length;i++){
			if (values[i].length()>1){
				setValue(i,dataToValue(values[i]));
			}
		}
	}
	
	protected String[] parseData(String originalData){
		String rawData = originalData.substring(8, originalData.length()-1);
		return rawData.split(";");
	}
	
	protected String dataToValue (String data){
		data = data.trim();
		return data.substring(5,data.length());
	}
	
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
				this.setDate(value);
			break;
		}
	}

	/**
	 * @return the id
	 */
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
	
}
