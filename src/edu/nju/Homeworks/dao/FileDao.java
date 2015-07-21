package edu.nju.Homeworks.dao;

import edu.nju.Homeworks.model.FileBean;

public interface FileDao {
	
	public void updateHomeworkFile(FileBean f);
	
	public void updateAssistantJudgeFile(FileBean f);
	
	public String downloadStuHomework(String c_name, String s_name, int h_id);
	
	public String downloadAssisJudgeFile(String c_name, String s_name, int h_id);
	
	public void changeAssisJudgeFileOKState(String c_name, String s_name, int h_id, int f);
	
	public String getAssisJudges(String cou_name, String stu_name);
	
	public void clear();

}
