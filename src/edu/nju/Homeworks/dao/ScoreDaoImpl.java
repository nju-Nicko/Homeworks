package edu.nju.Homeworks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.nju.Homeworks.model.ScoreBean;

public class ScoreDaoImpl implements ScoreDao {

	@Override
	public ArrayList<ScoreBean> loadCourseScore(String c_name, String stu_name) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		ArrayList<ScoreBean> list=new ArrayList<ScoreBean>();
		
		try {
			PreparedStatement stmt=c.prepareStatement("select * from score where cou_name=\'"+c_name+"\' and stu_name=\'"+stu_name+"\'"+" order by hom_id desc ");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				int id=Integer.parseInt(rs.getString("hom_id"));
				int score=Integer.parseInt(rs.getString("score"));
				String remark=rs.getString("remark");
				PreparedStatement stmt2=c.prepareStatement("select description from homework where cou_name=\'"+c_name+"\' and homework_id="+id);
				ResultSet rs2=stmt2.executeQuery();
				String desc=null;
				while(rs2.next()){
					desc=rs2.getString("description");
				}
				ScoreBean sb=new ScoreBean(c_name, stu_name, id, desc, score, remark);
				list.add(sb);
				stmt2.close();
				rs2.close();
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
	public ArrayList<ScoreBean> loadStudentScoreOfOneHomework(String c_name,
			int h_id) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		ArrayList<ScoreBean> list=new ArrayList<ScoreBean>();
		
		try {
			PreparedStatement stmt=c.prepareStatement("select * from score where cou_name=\'"+c_name+"\' and hom_id="+h_id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				String cou_name=c_name;
				String stu_name=rs.getString("stu_name");
				int id=h_id;
				String remark=rs.getString("remark");
				int score=Integer.parseInt(rs.getString("score"));
				ScoreBean sb=new ScoreBean(cou_name, stu_name, id, "", score, remark);
				list.add(sb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void updateScores(ArrayList<ScoreBean> list) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		
		try {
			for(int i=0; i<=list.size()-1; i++){
				ScoreBean sb=list.get(i);
				PreparedStatement stmt2=c.prepareStatement("update score set score="+sb.getScore()+", remark=\'"+sb.getRemark()+"\' where cou_name=\'"+
				sb.getC_name()+"\' and stu_name=\'"+sb.getS_name()+"\' and hom_id="+sb.getH_id());
				stmt2.executeUpdate();
				stmt2.close();
			}
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
			PreparedStatement stmt=c.prepareStatement("truncate table score");
			stmt.executeUpdate();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
