package edu.nju.Homeworks.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.Homeworks.dao.CourseDao;
import edu.nju.Homeworks.dao.CourseDaoImpl;
import edu.nju.Homeworks.model.CourseBean;

/**
 * Servlet implementation class CourseChange
 */
@WebServlet("/CourseChange")
public class CourseChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseChange() {
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
		String result=request.getParameter("tb_result");
		String[] split=result.split(";");
		CourseDao cd=new CourseDaoImpl();
		cd.removeAll();
		if(!result.equals(""))
		for(int i=0; i<=split.length-1; i++){
			CourseBean cb;
			String[] split2=split[i].split(" ");
			if(split2[2].equals("第一学期"))
			   cb=new CourseBean(split2[0], split2[1], 1);
			else
			  cb=new CourseBean(split2[0], split2[1], 2);
			cd.insert(cb);
		}
		HttpSession session=request.getSession(true);
		session.setAttribute("admin_submit_course_success", "true");
		response.sendRedirect("/Homeworks/jsp/administrator_course_management.jsp");
	}

}
