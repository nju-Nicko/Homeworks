package edu.nju.Homeworks.dao;

import java.util.ArrayList;

import edu.nju.Homeworks.model.HomeworkBean;

public interface HomeworkDao {
	
	public void publishNewHomework(HomeworkBean hb);
	
	public ArrayList<HomeworkBean> loadTheHomework(String c_name);

	public ArrayList<HomeworkBean> getStuSubmitOfACourse(String c_name, String stu_name);
	
	public String downloadDemo(String c_name, int h_id);
	
	public void publishHomeworkDemo(String c_name, int h_id, String path);
	
	public void clear();

}
