package edu.nju.Homeworks.servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.Homeworks.dao.ScoreDao;
import edu.nju.Homeworks.dao.ScoreDaoImpl;
import edu.nju.Homeworks.model.ScoreBean;

/**
 * Servlet implementation class GetCourseStudentScore
 */
@WebServlet("/GetCourseStudentScore")
public class GetCourseStudentScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCourseStudentScore() {
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
		String course_name=request.getParameter("course_name");
		course_name=URLDecoder.decode(course_name, "UTF-8");
		
		int h_id=Integer.parseInt(request.getParameter("homework_id"));
		
		ScoreDao sd=new ScoreDaoImpl();
		ArrayList<ScoreBean> list=sd.loadStudentScoreOfOneHomework(course_name, h_id);
		
		String result="";
		for(int i=0; i<=list.size()-1; i++){
			ScoreBean tmp=list.get(i);
			result=result+tmp.getS_name()+" "+tmp.getScore()+" "+tmp.getRemark()+";";
		}
		response.getWriter().print(result);
	}

}
