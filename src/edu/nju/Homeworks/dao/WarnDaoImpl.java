package edu.nju.Homeworks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Locale;

import edu.nju.Homeworks.model.WarnBean;

public class WarnDaoImpl implements WarnDao {

	@Override
	public ArrayList<WarnBean> getNewHomeworkWarn(String username) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		ArrayList<WarnBean> list=new ArrayList<WarnBean>();
		Date cd=new Date();
		boolean flag=false;
		try {
			PreparedStatement stmt=c.prepareStatement("select * from homework");
			PreparedStatement stmt2=c.prepareStatement("select * from select_info where stu_name=\'"+username+"\'");
			ResultSet rs=stmt.executeQuery();      
			while(rs.next()){
				flag=false;
			   for(int i=0; i<=list.size()-1; i++){
				   if(list.get(i).getContext().equals(rs.getString("cou_name"))){
					   flag=true;
				   }
			   }
			   if(!flag){
	           ResultSet rs2=stmt2.executeQuery();
				while(rs2.next()){
					if(rs2.getString("cou_name").equals(rs.getString("cou_name"))){
				String time=rs.getString("deliver_time");
				time=time.replace("-", " ");
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
				try {
					Date od=sdf.parse(time);
					long interval=cd.getTime()-od.getTime();
					long days=interval/1000/60/60/24;
					if(days<=1){                 //当前时间距离作业的发布时间小于1天，需要警告学生
						WarnBean wb=new WarnBean(rs.getString("cou_name"), WarnBean.WarnType.newHomework);
						list.add(wb);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			    	}
		    	  }
			  	}
				rs2.close();
			   }
			}
			stmt.close();
			stmt2.close();
			rs.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<WarnBean> getNewTeacherJudgeWarn(String username) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		ArrayList<WarnBean> list=new ArrayList<WarnBean>();
		Date cd=new Date();
		
		try {
			PreparedStatement stmt=c.prepareStatement("select * from assis_file");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				if(username.equals(rs.getString("assis_name"))){
					String time=rs.getString("judge_time");
					if(time!=null){
					time=time.replace("-", " ");
					SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
					
					Date od;
					try {
						od = sdf.parse(time);	
						long interval=cd.getTime()-od.getTime();
					    long days=interval/1000/60/60/24;
					    if(days<=1){
					    	WarnBean wb=new WarnBean(rs.getString("cou_name")+" "+rs.getString("homework_id")+" "+rs.getString("ok"), WarnBean.WarnType.teacherJudge);
					    	list.add(wb);
					    }
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<WarnBean> getAssistantSubmitWarn(String username) { 
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		ArrayList<WarnBean> list=new ArrayList<WarnBean>();
		Date cd=new Date();
		
		try {
			PreparedStatement stmt=c.prepareStatement("select * from assis_file");
			PreparedStatement stmt2=c.prepareStatement("select course_name from course where teacher_name=\'"+username+"\'");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
					   ResultSet rs2=stmt2.executeQuery();
					   while(rs2.next()){
							if(rs2.getString("course_name").equals(rs.getString("cou_name"))){
								String time=rs.getString("upload_time");
								time=time.replace("-", " ");
								SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
								try {
									Date od=sdf.parse(time);
									long interval=cd.getTime()-od.getTime();
									long days=interval/1000/60/60/24;
									if(days<=1){                 
										WarnBean wb=new WarnBean(rs.getString("cou_name")+" "+rs.getString("assis_name")+" "+rs.getString("homework_id"), WarnBean.WarnType.newAssistantSubmit);
										list.add(wb);
									}
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
							    	}
							}
					   }
					   rs2.close();
				   }
			stmt.close();
			stmt2.close();
			rs.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
