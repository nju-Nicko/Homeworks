package edu.nju.Homeworks.util;

import java.util.Date;

import javax.servlet.ServletContextEvent;

import edu.nju.Homeworks.dao.CourseDao;
import edu.nju.Homeworks.dao.CourseDaoImpl;
import edu.nju.Homeworks.dao.FileDao;
import edu.nju.Homeworks.dao.FileDaoImpl;
import edu.nju.Homeworks.dao.HomeworkDao;
import edu.nju.Homeworks.dao.HomeworkDaoImpl;
import edu.nju.Homeworks.dao.PairDao;
import edu.nju.Homeworks.dao.PairDaoImpl;
import edu.nju.Homeworks.dao.ScoreDao;
import edu.nju.Homeworks.dao.ScoreDaoImpl;

public class TimeThread extends Thread {
	
	public ServletContextEvent e;
	
	public TimeThread(ServletContextEvent e){
		this.e=e;
	}
	
	@SuppressWarnings("deprecation")
	public void run(){
		while(true){
			Date date=new Date();
			int c_m=date.getMonth()+1;
			int c_d=date.getDate();
			if((c_m==2&&c_d==1)||(c_m==8&&c_d==1)){       //在2月1日和8月1日清空各种选课之类的数据并生成报表
				CourseDao cd=new CourseDaoImpl();
				cd.generateExcel(e.getServletContext(), c_m);
				FileDao fd=new FileDaoImpl();
				HomeworkDao hd=new HomeworkDaoImpl();
				PairDao pd=new PairDaoImpl();
				ScoreDao sd=new ScoreDaoImpl();
				fd.clear();
				hd.clear();
				pd.clear();
				sd.clear();
			}
			try{
				Thread.sleep(86400000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
