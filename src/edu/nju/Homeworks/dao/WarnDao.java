package edu.nju.Homeworks.dao;

import java.util.ArrayList;

import edu.nju.Homeworks.model.WarnBean;

public interface WarnDao {
	
	public ArrayList<WarnBean> getNewHomeworkWarn(String username);
	
	public ArrayList<WarnBean> getNewTeacherJudgeWarn(String username);
	
	public ArrayList<WarnBean> getAssistantSubmitWarn(String username);

}
