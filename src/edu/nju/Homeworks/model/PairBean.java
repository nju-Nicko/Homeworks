package edu.nju.Homeworks.model;

public class PairBean {
	
	private String stu_name;
	
	private String cou_name;
	
	public PairBean(){
		
	}
	
	public PairBean(String stu_name, String cou_name){
		this.stu_name=stu_name;
		this.cou_name=cou_name;
	}
	
	public void setStu_name(String s){
		stu_name=s;
	}
	
	public void setCou_name(String c){
		cou_name=c;
	}
	
	public String getStu_name(){
		return stu_name;
	}
	
	public String getCou_name(){
		return cou_name;
	}

}
