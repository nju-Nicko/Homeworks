package edu.nju.Homeworks.servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.Homeworks.dao.CourseDao;
import edu.nju.Homeworks.dao.CourseDaoImpl;
import edu.nju.Homeworks.model.CourseBean;

/**
 * Servlet implementation class GetAssisCourse
 */
@WebServlet("/GetAssisCourse")
public class GetAssisCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAssisCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String stu_name=request.getParameter("stu_name");
		stu_name=URLDecoder.decode(stu_name, "UTF-8");
		
		CourseDao cd=new CourseDaoImpl();
		ArrayList<CourseBean> list=cd.getAssistantCourse(stu_name);
		String result="";
	    for(int i=0; i<=list.size()-1; i++){
	    	result += list.get(i).getCn() +";";
	    }
	    
	    response.getWriter().print(result);
	}

}
