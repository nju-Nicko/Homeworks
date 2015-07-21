package edu.nju.Homeworks.dao;

import java.util.ArrayList;

import javax.servlet.ServletContext;

import edu.nju.Homeworks.model.CourseBean;

public interface CourseDao {
	
	public ArrayList<CourseBean> loadAll();
	
	public void removeAll();
	
	public void insert(CourseBean cb);
	
	public ArrayList<CourseBean> getTeacherCourse(String teacher_name);
	
	public ArrayList<CourseBean> getStudentCourse(String stu_name);
	
	public ArrayList<CourseBean> getAssistantCourse(String stu_name);
	
	public void generateExcel(ServletContext sc, int month);

}
