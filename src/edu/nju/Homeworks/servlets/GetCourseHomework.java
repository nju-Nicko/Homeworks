package edu.nju.Homeworks.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.Homeworks.dao.HomeworkDao;
import edu.nju.Homeworks.dao.HomeworkDaoImpl;
import edu.nju.Homeworks.model.HomeworkBean;

/**
 * Servlet implementation class GetCourseHomework
 */
@WebServlet("/GetCourseHomework")
public class GetCourseHomework extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCourseHomework() {
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
		HomeworkDao hd=new HomeworkDaoImpl();
		ArrayList<HomeworkBean> list=hd.loadTheHomework(c_name);
		String result="";
		for(int i=0; i<=list.size()-1; i++){
			HomeworkBean tmp=list.get(i);
			result=result+tmp.getId()+" "+tmp.getDescription()+" "+tmp.getSub_ddl()+" "+tmp.getJudge_ddl()+" "+tmp.getType()+" "+tmp.getScore()+" "+tmp.getDifficulty()+";";
		}
		PrintWriter pw=response.getWriter();
		pw.print(result);
	}

}
