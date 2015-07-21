package edu.nju.Homeworks.servlets;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.Homeworks.dao.FileDao;
import edu.nju.Homeworks.dao.FileDaoImpl;

/**
 * Servlet implementation class GetAssisJudges
 */
@WebServlet("/GetAssisJudges")
public class GetAssisJudges extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAssisJudges() {
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
		String c_name=request.getParameter("course_name");
		c_name=URLDecoder.decode(c_name, "UTF-8");
		String s_name=request.getParameter("student_name");
		s_name=URLDecoder.decode(s_name, "UTF-8");
		
		FileDao fd=new FileDaoImpl();
		String result=fd.getAssisJudges(c_name, s_name);
		response.getWriter().print(result);
	}

}
