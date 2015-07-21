package edu.nju.Homeworks.tag;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import edu.nju.Homeworks.dao.UserDao;
import edu.nju.Homeworks.dao.UserDaoImpl;
import edu.nju.Homeworks.model.UserBean;

public class GetTeachersHandler extends SimpleTagSupport {
	
	public void doTag(){
		UserDao ud=new UserDaoImpl();
		ArrayList<UserBean> list=ud.loadAllTeachers();
		
		String result="";
		
		for(int i=0; i<=list.size()-1; i++){
			result += list.get(i).getName()+" "+list.get(i).getPass()+" "+list.get(i).getType()+";";
		}
		JspWriter out=getJspContext().getOut();
		try {
			out.println("<input id=\"a_t\" type=\"text\" value=\""+result+"\" style=\"display: none;\"/>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
