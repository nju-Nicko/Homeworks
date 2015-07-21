package edu.nju.Homeworks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.nju.Homeworks.model.UserBean;
import edu.nju.Homeworks.util.Administrator;

public class UserDaoImpl implements UserDao {
	
	public static Administrator admin=new Administrator("administrator", "admin", "ad");

	@Override
	public void insert(UserBean ub) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		try {
			PreparedStatement stmt=c.prepareStatement("insert into user values (\'"+ub.getName()+"\', \'"+ub.getPass()+"\', \'"+ub.getType()+"\');");
			stmt.executeUpdate();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public UserBean find(String name, String pass) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		try {
			PreparedStatement stmt=c.prepareStatement("select pass, type from user where name=\'"+name+"\'");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				String password=rs.getString("pass");
				String kind=rs.getString("type");
				if(password.equals(pass)){
					UserBean ub=new UserBean(name, password, kind);
					return ub;
				}
			}
			stmt.close();
			rs.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<UserBean> loadAll(){
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		ArrayList<UserBean> list=new ArrayList<UserBean>();
		try {
			PreparedStatement stmt=c.prepareStatement("select * from user");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				String name=rs.getString("name");
				String pass=rs.getString("pass");
				String type=rs.getString("type");
				UserBean ub=new UserBean(name, pass, type);
				if(!type.equals("ad"))
				list.add(ub);
			}
			stmt.close();
			rs.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		try {
			PreparedStatement stmt1=c.prepareStatement("select name, pass from user where type=\"ad\"");
			ResultSet rs=stmt1.executeQuery();
			while(rs.next()){
				admin.setName(rs.getString("name"));
				admin.setPass(rs.getString("pass"));
				break;
			}
			stmt1.close();
			rs.close();
			PreparedStatement stmt=c.prepareStatement("TRUNCATE  TABLE  user");
			stmt.executeUpdate();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<UserBean> loadAllStudents() {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		ArrayList<UserBean> list=new ArrayList<UserBean>();
		try {
			PreparedStatement stmt=c.prepareStatement("select name from user where type='s'");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				list.add(new UserBean(rs.getString("name"), "", ""));
			}
			stmt.close();
			rs.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<UserBean> loadAllTeachers() {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		ArrayList<UserBean> list=new ArrayList<UserBean>();
		try {
			PreparedStatement stmt=c.prepareStatement("select name from user where type='t' or type='mt'");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				list.add(new UserBean(rs.getString("name"), "", ""));
			}
			stmt.close();
			rs.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
