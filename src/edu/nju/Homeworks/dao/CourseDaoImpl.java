package edu.nju.Homeworks.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import edu.nju.Homeworks.model.CourseBean;
import edu.nju.Homeworks.model.PairBean;

public class CourseDaoImpl implements CourseDao{

	@Override
	public ArrayList<CourseBean> loadAll() {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		ArrayList<CourseBean> list=new ArrayList<CourseBean>();
		try {
			PreparedStatement stmt=c.prepareStatement("select * from course");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				CourseBean cb=new CourseBean(rs.getString("course_name"), rs.getString("teacher_name"), Integer.parseInt(rs.getString("term")));
				list.add(cb);
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
			PreparedStatement stmt=c.prepareStatement("truncate table course");
			stmt.executeUpdate();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void insert(CourseBean cb) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		try {
			PreparedStatement stmt=c.prepareStatement("insert into course values (\'"+cb.getCn()+"\', \'"+cb.getTn()+"\', "+cb.getTerm()+");");
			stmt.executeUpdate();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<CourseBean> getTeacherCourse(String teacher_name) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		ArrayList<CourseBean> list=new ArrayList<CourseBean>();
		try {
			PreparedStatement stmt=c.prepareStatement("select * from course where teacher_name=\'"+teacher_name+"\'");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				CourseBean cb=new CourseBean(rs.getString("course_name"), teacher_name, Integer.parseInt(rs.getString("term")));
				list.add(cb);
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
	public ArrayList<CourseBean> getStudentCourse(String stu_name) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		ArrayList<CourseBean> list=new ArrayList<CourseBean>();
		try {
			PreparedStatement stmt=c.prepareStatement("select cou_name from select_info where stu_name=\'"+stu_name+"\'");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				String name=rs.getString("cou_name");
				CourseBean cb=new CourseBean(name,"", 0);
				list.add(cb);
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
	public ArrayList<CourseBean> getAssistantCourse(String stu_name) {
		// TODO Auto-generated method stub
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		ArrayList<CourseBean> list=new ArrayList<CourseBean>();
		try {
			PreparedStatement stmt=c.prepareStatement("select cou_name from assistant where stu_name=\'"+stu_name+"\'");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				String name=rs.getString("cou_name");
				CourseBean cb=new CourseBean(name,"", 0);
				list.add(cb);
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
	public void generateExcel(ServletContext sc, int c_m) {
		// TODO Auto-generated method stub
		String realpath=sc.getRealPath("/");
		DaoHelperImpl dhi=new DaoHelperImpl();
		Connection c=dhi.getConnection();
		if(c_m==2){
			try {
				PreparedStatement stmt=c.prepareStatement("select * from course where term="+1);
				ResultSet rs=stmt.executeQuery();
				while(rs.next()){
					File file=new File(realpath+rs.getString("course_name")+"统计报表.xls");
					if(file.exists())
						file.delete();
					try {
						OutputStream os=new FileOutputStream(file);
						try {
							WritableWorkbook wwb = Workbook.createWorkbook(os);
							WritableSheet ws = wwb.createSheet("Sheet 1", 0); 
							Label label = new Label(0, 0, rs.getString("course_name")); 
							Label label2 = new Label(1, 0, rs.getString("teacher_name")); 
							PairDao pd=new PairDaoImpl();
							ArrayList<PairBean> list1=pd.loadSelectInfo(rs.getString("course_name"));
							ArrayList<PairBean> list2=pd.loadAssistantInfo(rs.getString("course_name"));
							String r1=""; 
							String r2="";
							for(int i1=0; i1<=list1.size()-1; i1++)
								r1=r1+list1.get(i1).getStu_name()+" ";
							for(int i2=0; i2<=list2.size()-1; i2++)
								r2=r2+list2.get(i2).getStu_name()+" ";
							Label label3= new Label(2, 0, r1); 
							Label label4 = new Label(3, 0, r2); 
							try {
								ws.addCell(label);
								ws.addCell(label2);
								ws.addCell(label3);
								ws.addCell(label4);
								wwb.write();
						        wwb.close();
							} catch (RowsExceededException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (WriteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(c_m==8){
			try {
				PreparedStatement stmt=c.prepareStatement("select * from course where term="+2);
				ResultSet rs=stmt.executeQuery();
				while(rs.next()){
					File file=new File(realpath+rs.getString("course_name")+"统计报表.xls");
					if(file.exists())
						file.delete();
					try {
						OutputStream os=new FileOutputStream(file);
						try {
							WritableWorkbook wwb = Workbook.createWorkbook(os);
							WritableSheet ws = wwb.createSheet("Sheet 1", 0); 
							Label label = new Label(0, 0, rs.getString("course_name")); 
							Label label2 = new Label(1, 0, rs.getString("teacher_name")); 
							PairDao pd=new PairDaoImpl();
							ArrayList<PairBean> list1=pd.loadSelectInfo(rs.getString("course_name"));
							ArrayList<PairBean> list2=pd.loadAssistantInfo(rs.getString("course_name"));
							String r1=""; 
							String r2="";
							for(int i1=0; i1<=list1.size()-1; i1++)
								r1=r1+list1.get(i1).getStu_name()+" ";
							for(int i2=0; i2<=list2.size()-1; i2++)
								r2=r2+list2.get(i2).getStu_name()+" ";
							Label label3= new Label(2, 0, r1); 
							Label label4 = new Label(3, 0, r2); 
							try {
								ws.addCell(label);
								ws.addCell(label2);
								ws.addCell(label3);
								ws.addCell(label4);
								wwb.write();
						        wwb.close();
							} catch (RowsExceededException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (WriteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
