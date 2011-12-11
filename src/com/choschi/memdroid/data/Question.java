package com.choschi.memdroid.data;

import java.util.List;

import com.choschi.memdroid.data.interfaces.Answer;
import com.choschi.memdroid.data.interfaces.Rule;

public class Question {
	
	protected String name;
	protected List<Rule> rules;
	protected String id;
	protected Answer answer;
	

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Rule> getRules() {
		return rules;
	}
	
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Answer getAnswer() {
		return answer;
	}
	
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	
}
