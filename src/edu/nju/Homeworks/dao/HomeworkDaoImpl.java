package edu.nju.Homeworks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import edu.nju.Homeworks.model.HomeworkBean;

public class HomeworkDaoImpl implements HomeworkDao{

	@Override
	public void publishNewHomework(HomeworkBean hb) {   //在发布新作业的同时更新选了这门课的同学的成绩表，各项全部插入0值
		// TODO Auto-generated method stub
		Date date=new Date();
		String c_t=date.toString().replace(" ", "-");
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		try{
			String c_name=hb.getCourse_name();
			int id=hb.getId();
			String desc=hb.getDescription();
			String sub_ddl=hb.getSub_ddl();
			String judge_ddl=hb.getJudge_ddl();
			String type=hb.getType();
			int score=hb.getScore();
			int difficulty=hb.getDifficulty();
			PreparedStatement stmt=c.prepareStatement("insert into homework values(\'"+c_name+"\', "+id+", \'"+desc+"\', \'"+sub_ddl+"\', \'"+judge_ddl+"\', \'"+type+"\', "+score+", "+difficulty+", \'"+c_t+"\', '暂无样例')");
			stmt.executeUpdate();
			stmt.close();
			PreparedStatement stmt2=c.prepareStatement("select stu_name from select_info where cou_name=\'"+c_name+"\'");
			ResultSet rs=stmt2.executeQuery();
			while(rs.next()){
				String stu_name=rs.getString("stu_name");
				PreparedStatement stmt3=c.prepareStatement("insert into score values(\'"+c_name+"\', \'"+stu_name+"\', "+id+", 0, \'暂无点评\')");
				stmt3.executeUpdate();
				stmt3.close();
			}
			stmt2.close();rs.close();
			c.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<HomeworkBean> loadTheHomework(String c_name) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		ArrayList<HomeworkBean> list=new ArrayList<HomeworkBean>();
		try {
			PreparedStatement stmt=c.prepareStatement("select * from homework where cou_name=\'"+c_name+"\' order by homework_id desc");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				int id=Integer.parseInt(rs.getString("homework_id"));
				String desc=rs.getString("description");
				String ddl1=rs.getString("sub_ddl");
				String ddl2=rs.getString("judge_ddl");
				String type=rs.getString("type");
				int score=Integer.parseInt(rs.getString("score"));
				int difficulty=Integer.parseInt(rs.getString("difficulty"));
				HomeworkBean hb=new HomeworkBean(c_name, id, desc, ddl1, ddl2, type, score, difficulty);
				list.add(hb);
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
	public ArrayList<HomeworkBean> getStuSubmitOfACourse(String c_name,
			String stu_name) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		ArrayList<HomeworkBean> list=new ArrayList<HomeworkBean>();
		try {
			PreparedStatement stmt=c.prepareStatement("select homework_id from file where cou_name=\'"+c_name+"\' and stu_name=\'"+stu_name+"\'");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				int id=Integer.parseInt(rs.getString("homework_id"));
				HomeworkBean hb=new HomeworkBean("", id, "", "", "", "",0, 0);
				list.add(hb);
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
	public String downloadDemo(String c_name, int h_id) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		try {
			PreparedStatement stmt=c.prepareStatement("select demo_path from homework where cou_name=\'"+c_name+"\'  and homework_id="+h_id);
			ResultSet rs=stmt.executeQuery();
			String path="";
			while(rs.next())
				path=rs.getString("demo_path");
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
	public void publishHomeworkDemo(String c_name, int h_id, String path) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		
		try {
			PreparedStatement stmt=c.prepareStatement("update homework set demo_path=\'"+path+"\' where cou_name=\'"+c_name+"\'  and homework_id="+h_id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void clear(){
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		
		try {
			PreparedStatement stmt=c.prepareStatement("truncate table homework");
			stmt.executeUpdate();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
