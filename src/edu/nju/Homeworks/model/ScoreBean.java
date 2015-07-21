package edu.nju.Homeworks.model;

public class ScoreBean {
	
	private String c_name;
	private String s_name;
	private int h_id;
	private String desc;
	private int score;
	private String remark;
	
	public ScoreBean(){
		
	}
	
	public ScoreBean(String c, String s, int i, String d, int sc, String r){
		c_name=c;
		s_name=s;
		h_id=i;
		desc=d;
		remark=r;
		score=sc;
	}
	
	public void setC_name(String c){
		c_name=c;
	}
	
	public void setS_name(String s){
		s_name=s;
	}
	
	public void setH_id(int id){
		h_id=id;
	}
	
	public void setDesc(String d){
		desc=d;
	}
	
	public void setRemark(String r){
		remark=r;
	}
	
	public void setScore(int s){
		score=s;
	}
	
	public String getC_name(){
		return c_name;
	}
	
	public String getS_name(){
		return s_name;
	}
	
	public int getH_id(){
		return h_id;
	}
	
	public String getDesc(){
		return desc;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public int getScore(){
		return score;
	}

}
