package edu.nju.Homeworks.model;

public class UserBean {
	
	private String name;
	private String pass;
	private String type;
	private String tt;
	
	public UserBean(){
		
	}
	
	public UserBean(String n, String p, String t){
		this.name=n;
		this.pass=p;
		this.type=t;
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
	
	public String getTt(){
		if(type!=null)
		switch(type){
		case "ad": return "管理员";
		case "s": return "学生";
		case "t": return "授课教师";
		case "m": return "教学负责人";
		case "mt": return "授课教师兼教学负责人";
		}
		return null;
	}

}
