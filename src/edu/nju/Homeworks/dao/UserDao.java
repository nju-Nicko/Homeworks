package edu.nju.Homeworks.dao;

import java.util.ArrayList;

import edu.nju.Homeworks.model.UserBean;

public interface UserDao {
	
	public void insert(UserBean ub);
	
	public UserBean find(String name, String pass);
	
	public void removeAll();
	
	public ArrayList<UserBean> loadAll();
	
	public ArrayList<UserBean> loadAllStudents();
	
	public ArrayList<UserBean> loadAllTeachers();

}
