package edu.nju.Homeworks.dao;

import java.util.ArrayList;

import edu.nju.Homeworks.model.PairBean;

public interface PairDao {
	
	public void updateStudentSelect(String info);
	
	public void updateAssistantAssignment(String info);
	
	public ArrayList<PairBean> loadSelectInfo(String c_name);
	
	public ArrayList<PairBean> loadAssistantInfo(String c_name);
	
	public boolean isAssistant(String s_name);
	
	public void clear();

}
