package edu.nju.Homeworks.dao;

import java.util.ArrayList;

import edu.nju.Homeworks.model.ScoreBean;

public interface ScoreDao {
	
	public ArrayList<ScoreBean> loadCourseScore(String c_name, String stu_name);
	
	public ArrayList<ScoreBean> loadStudentScoreOfOneHomework(String c_name, int h_id);
	
	public void updateScores(ArrayList<ScoreBean> list);
	
	public void clear();

}
