package edu.nju.Homeworks.tag;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import edu.nju.Homeworks.dao.CourseDao;
import edu.nju.Homeworks.dao.CourseDaoImpl;
import edu.nju.Homeworks.model.CourseBean;

public class GetStudentCourseHandler extends SimpleTagSupport{
	
	private String stu_name;
	
	public void setStu_name(String name){
		stu_name=name;
	}
	
	public void doTag(){
		CourseDao cd=new CourseDaoImpl();
		ArrayList<CourseBean> list=cd.getStudentCourse(stu_name);
		String result="";
	    for(int i=0; i<=list.size()-1; i++){
		    	result += list.get(i).getCn() +";";
		    }
	    JspWriter out=getJspContext().getOut();
	    try {
			out.println("<input id=\"a_sc\" type=\"text\" value=\""+result+"\" style=\"display: none;\"/>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
