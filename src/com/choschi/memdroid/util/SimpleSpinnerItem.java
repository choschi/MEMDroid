package com.choschi.memdroid.util;

import com.choschi.memdroid.data.interfaces.SpinnerItem;

public class SimpleSpinnerItem implements SpinnerItem {

	private String label;
	private String id;
	
	public SimpleSpinnerItem (String id, String label){
		this.id = id;
		this.label = label;
	}
	
	@Override
	public String getId() {
		return this.id;
	}
	
	@Override
	public String toString(){
		return label;
	}

}
