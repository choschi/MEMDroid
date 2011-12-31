package com.choschi.memdroid.data;

public class PatientFieldRule {

	private int lengthMax;
	private int lengthMin;
	private boolean patientlist;
	private boolean readOnly;
	private boolean required;
	private boolean useTime;
	private boolean useUnspecifiedGender;
	private String validationRegularExpression;
	
	public PatientFieldRule (int maxlen, int minlen, boolean list, boolean only, boolean req, boolean time, boolean gender, String regex){
		lengthMax = maxlen;
		lengthMin = minlen;
		patientlist = list;
		readOnly = only;
		required = req;
		useTime = time;
		useUnspecifiedGender = gender;
		validationRegularExpression = regex;
	}
	
	public boolean isRequired(){
		return required;
	}
	
}
