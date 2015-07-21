package edu.nju.Homeworks.model;

public class HomeworkBean {
	
	private String course_name;
	private int id;
	private String description;
	private String sub_ddl;
	private String judge_ddl;
	private String type;
	private int score;
	private int difficulty;
	
	public HomeworkBean(){
		
	}
	
	public HomeworkBean(String a, int b, String c, String d, String e, String f, int g, int h){
		course_name=a;
		id=b;
		description=c;
		sub_ddl=d;
		judge_ddl=e;
		type=f;
		score=g;
		difficulty=h;
	}
	
	public void setCourse_name(String c){
		course_name=c;
	}
	
	public void setId(int i){
		id=i;
	}
	
	public void setDescription(String d){
		description=d;
	}
	
	public void setSub_dl(String t){
		sub_ddl=t;
	}
	
	public void setJudge_ddl(String t){
		judge_ddl=t;
	}
	
	public void setType(String t){
		type=t;
	}
	
	public void setScore(int s){
		score=s;
	}
	
	public void setDifficulty(int d){
		difficulty=d;
	}
	
	public String getCourse_name(){
		return course_name;
	}
	
	public int getId(){
		return id;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getSub_ddl(){
		return sub_ddl;
	}
	
	public String getJudge_ddl(){
		return judge_ddl;
	}
	
	public String getType(){
		return type;
	}
	
	public int getScore(){
		return score;
	}
	public int getDifficulty(){
		return difficulty;
	}
	

}
