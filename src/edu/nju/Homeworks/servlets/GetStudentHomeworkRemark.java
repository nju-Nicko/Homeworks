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
 * Servlet implementation class GetStudentHomeworkRemark
 */
@WebServlet("/GetStudentHomeworkRemark")
public class GetStudentHomeworkRemark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStudentHomeworkRemark() {
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
		
		ScoreDao sd=new ScoreDaoImpl();
		ArrayList<ScoreBean> list=sd.loadCourseScore(c_name, s_name);
		
		String result="";
		for(int i=0; i<=list.size()-1; i++){
			result=result+list.get(i).getC_name()+" "+list.get(i).getS_name()+" "+list.get(i).getH_id()+" "+list.get(i).getDesc()+" "+list.get(i).getScore()+" "+list.get(i).getRemark()+";";
		}
		
		response.getWriter().print(result);
	}

}
