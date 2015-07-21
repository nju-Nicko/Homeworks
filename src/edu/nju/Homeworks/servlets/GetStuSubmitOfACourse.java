package edu.nju.Homeworks.servlets;

import java.io.IOException;
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
 * Servlet implementation class GetStuSubmitOfACourse
 */
@WebServlet("/GetStuSubmitOfACourse")
public class GetStuSubmitOfACourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStuSubmitOfACourse() {
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
		String stu_name=request.getParameter("student_name");
		stu_name=URLDecoder.decode(stu_name, "UTF-8");
		String cou_name=request.getParameter("course_name");
		cou_name=URLDecoder.decode(cou_name, "UTF-8");
		HomeworkDao hd=new HomeworkDaoImpl();
		ArrayList<HomeworkBean> list=hd.getStuSubmitOfACourse(cou_name, stu_name);
		String result="";
		for(int i=0; i<=list.size()-1; i++){
			result=result+list.get(i).getId()+" ";
		}
		response.getWriter().print(result);
	}

}
