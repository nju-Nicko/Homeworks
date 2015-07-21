package edu.nju.Homeworks.util;

import edu.nju.Homeworks.model.UserBean;

public class Administrator extends UserBean {
	
	private String name;
	private String pass;
	private String type;
	
	public Administrator(){
	}
	
	public Administrator(String name, String pass, String type){
		this.name=name;
		this.pass=pass;
		this.type=type;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public void setPass(String pass){
		this.pass=pass;
	}
	
	public void setType(String type){
		this.type=type;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPass(){
		return pass;
	}
	
	
	public String getType(){
		return type;
	}

}
