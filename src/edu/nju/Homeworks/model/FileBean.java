package edu.nju.Homeworks.model;

public class FileBean {
	
	private String cou_name;
	private int id;
	private String stu_name;
	private String path;
	
	public FileBean(){
		
	}
	
	public FileBean(String c, int i, String s, String p){
		cou_name=c;
		id=i;
		stu_name=s;
		path=p;
	}
	
	public void setCou_name(String cou_name){
		this.cou_name=cou_name;
	}
	
	public void setId(int i){
		id=i;
	}
	
	public void setStu_name(String s){
		stu_name=s;
	}
	
	public void setPath(String p){
		path=p;
	}
	
	public String getCou_name(){
		return cou_name;
	}
	
	public int getId(){
		return id;
	}
	
	public String getStu_name(){
		return stu_name;
	}
	
	public String getPath(){
		return path;
	}

}
