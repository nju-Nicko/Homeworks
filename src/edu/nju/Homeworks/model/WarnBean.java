package edu.nju.Homeworks.model;

public class WarnBean {
	
	private String context;
	private WarnType type;
	
	public enum WarnType{
		newHomework, newAssistantSubmit, teacherJudge;
	}
	
	public WarnBean(){
		
	}
	
	public WarnBean(String context, WarnType wt){
		this.context=context;
		type=wt;
	}
	
	public void setContext(String c){
		context=c;
	}
	
	public String getContext(){
		return context;
	}
	
	public void setType(WarnType wt){
		type=wt;
	}
	
	public WarnType getType(){
		return type;
	}

}
