package edu.nju.Homeworks.model;

public class CourseBean {
	
	private String cn;
	private String tn;
	private int term;
	
	public CourseBean(){
		
	}
	
	public CourseBean(String cn, String tn, int t){
		this.cn=cn;
		this.tn=tn;
		this.term=t;
	}
	
	public void setCn(String cn){
		this.cn=cn;
	}
	
	public void setTn(String tn){
		this.tn=tn;
	}
	
	public String getCn(){
		return cn;
	}
	
	public String getTn(){
		return tn;
	}
	
	public void setTerm(int t){
		this.term=t;
	}

	public int getTerm(){
		return term;
	}
}
