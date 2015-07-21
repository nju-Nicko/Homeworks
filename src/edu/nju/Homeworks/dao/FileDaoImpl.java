package edu.nju.Homeworks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import edu.nju.Homeworks.model.FileBean;

public class FileDaoImpl implements FileDao {

	@Override
	public void updateHomeworkFile(FileBean f) {
		// TODO Auto-generated method stub
		Date date=new Date();
		String c_t=date.toString().replace(" ", "-");
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		String c_name=f.getCou_name();
		String stu_name=f.getStu_name();
		int id=f.getId();
		String path=f.getPath();
		try {
			PreparedStatement stmt=c.prepareStatement("select * from file where cou_name=\'"+c_name+"\' and homework_id="+id+" and stu_name=\'"+stu_name+"\'");
			ResultSet rs=stmt.executeQuery();
			boolean flag=false;
			while(rs.next()){
				flag=true;
			}
			if(flag){
				PreparedStatement stmt2=c.prepareStatement("update file set path=\'"+path+"\' where cou_name=\'"+c_name+"\' and homework_id="+id+" and stu_name=\'"+stu_name+"\' and upload_time=\'"+c_t+"\'");
				stmt2.executeUpdate();
				stmt2.close();
			}
			else{
				PreparedStatement stmt2=c.prepareStatement("insert into file values(\'"+c_name+"\', "+id+", \'"+stu_name+"\', \'"+path+"\', \'"+c_t+"\')");
				stmt2.executeUpdate();
				stmt2.close();
			}
			rs.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateAssistantJudgeFile(FileBean f) {
		// TODO Auto-generated method stub
		Date date=new Date();
		String c_t=date.toString().replace(" ", "-");
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		String c_name=f.getCou_name();
		String stu_name=f.getStu_name();
		int id=f.getId();
		String path=f.getPath();
		try {
			PreparedStatement stmt2=c.prepareStatement("select * from assis_file where cou_name=\'"+c_name+"\' and homework_id="+id+" and assis_name=\'"+stu_name+"\'");
			ResultSet rs2=stmt2.executeQuery();
			boolean flag=false;
			while(rs2.next()){
				flag=true;
			}
			if(flag){
				PreparedStatement stmt3=c.prepareStatement("update assis_file set path=\'"+path+"\' where cou_name=\'"+c_name+"\' and homework_id="+id+" and assis_name=\'"+stu_name+"\' and upload_time=\'"+c_t+"\'");
				stmt3.executeUpdate();
				stmt3.close();
			}
			else{
			PreparedStatement stmt=c.prepareStatement("insert into assis_file values(\'"+c_name+"\', "+id+", \'"+stu_name+"\', \'"+path+"\', \'"+c_t+"\', 0, NULL)");
			stmt.executeUpdate();
			stmt.close();
			}
			stmt2.close();
			rs2.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String downloadStuHomework(String c_name, String s_name,
			int h_id) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		try {
			PreparedStatement stmt=c.prepareStatement("select path from file where cou_name=\'"+c_name+"\' and stu_name=\'"+
			s_name+"\' and homework_id="+h_id);
			ResultSet rs=stmt.executeQuery();
			String path="";
			while(rs.next())
				path=rs.getString("path");
			stmt.close();
			rs.close();
			c.close();
			return path;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String downloadAssisJudgeFile(String c_name, String s_name, int h_id) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		try {
			PreparedStatement stmt=c.prepareStatement("select path from assis_file where cou_name=\'"+c_name+"\' and assis_name=\'"+
			s_name+"\' and homework_id="+h_id);
			ResultSet rs=stmt.executeQuery();
			String path="";
			while(rs.next())
				path=rs.getString("path");
			stmt.close();
			rs.close();
			c.close();
			return path;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void changeAssisJudgeFileOKState(String c_name, String s_name,
			int h_id, int f) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		
		Date date=new Date();
		String c_t=date.toString().replace(" ", "-");
		
		try {
			PreparedStatement stmt=c.prepareStatement("update assis_file set ok="+f+", judge_time=\'"+c_t+"\'  where cou_name=\'"+c_name+"\' and assis_name=\'"+
			s_name+"\' and homework_id="+h_id);
			stmt.executeUpdate();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		
		try {
			PreparedStatement stmt=c.prepareStatement("truncate table file");
			PreparedStatement stmt2=c.prepareStatement("truncate table assis_file");
			stmt.executeUpdate();stmt2.executeUpdate();
			stmt.close(); stmt2.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getAssisJudges(String cou_name, String stu_name) {
		// TODO Auto-generated method stub
		String result="";
		
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		
		try {
			PreparedStatement stmt=c.prepareStatement("select * from assis_file where cou_name=\'"+cou_name+"\' and assis_name=\'"+
		stu_name+"\'");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				result=result+rs.getString("homework_id")+" "+rs.getString("ok")+";";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
