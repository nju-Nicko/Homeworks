package edu.nju.Homeworks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.nju.Homeworks.model.PairBean;

public class PairDaoImpl implements PairDao{

	@Override
	public void updateStudentSelect(String info) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		String split[]=info.split(" ");
		String c_name=split[0];
		try {
			PreparedStatement stmt=c.prepareStatement("delete from select_info where cou_name=\'"+c_name+"\'");
			stmt.executeUpdate();
			stmt.close();
			for(int i=1; i<=split.length-1; i++){
			   PreparedStatement stmt1=c.prepareStatement("insert into select_info values(\'"+split[i]+"\', \'"+c_name+"\')");
			   stmt1.executeUpdate();
			   stmt1.close();	
			}
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateAssistantAssignment(String info) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		String split[]=info.split(" ");
		String c_name=split[0];
		try {
			PreparedStatement stmt=c.prepareStatement("delete from assistant where cou_name=\'"+c_name+"\'");
			stmt.executeUpdate();
			stmt.close();
			for(int i=1; i<=split.length-1; i++){
			   PreparedStatement stmt1=c.prepareStatement("insert into assistant values(\'"+split[i]+"\', \'"+c_name+"\')");
			   stmt1.executeUpdate();
			   stmt1.close();	
			}
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<PairBean> loadSelectInfo(String c_name) {
		// TODO Auto-generated method stub
		ArrayList<PairBean> list=new ArrayList<PairBean>();
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		try {
			PreparedStatement stmt=c.prepareStatement("select stu_name, cou_name from select_info where cou_name=\'"+c_name+"\'");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				PairBean pb=new PairBean(rs.getString("stu_name"), rs.getString("cou_name"));
				list.add(pb);
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
	public ArrayList<PairBean> loadAssistantInfo(String c_name) {
		// TODO Auto-generated method stub
		ArrayList<PairBean> list=new ArrayList<PairBean>();
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		try {
			PreparedStatement stmt=c.prepareStatement("select stu_name, cou_name from assistant where cou_name=\'"+c_name+"\'");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				PairBean pb=new PairBean(rs.getString("stu_name"), rs.getString("cou_name"));
				list.add(pb);
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
	public boolean isAssistant(String s_name) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		try {
			PreparedStatement stmt=c.prepareStatement("select * from assistant where stu_name=\'"+s_name+"\'");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				return true;
			}
			stmt.close();
			rs.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		
		try {
			PreparedStatement stmt=c.prepareStatement("truncate table select_info");
			PreparedStatement stmt2=c.prepareStatement("truncate table assistant");
			stmt.executeUpdate();stmt2.executeUpdate();
			stmt.close(); stmt2.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
