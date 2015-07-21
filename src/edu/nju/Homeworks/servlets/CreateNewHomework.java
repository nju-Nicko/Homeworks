package edu.nju.Homeworks.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.Homeworks.dao.HomeworkDao;
import edu.nju.Homeworks.dao.HomeworkDaoImpl;
import edu.nju.Homeworks.model.HomeworkBean;

/**
 * Servlet implementation class CreateNewHomework
 */
@WebServlet("/CreateNewHomework")
public class CreateNewHomework extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNewHomework() {
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
		String result=request.getParameter("assi_detail");
		String[] split=result.split(" ");
		HomeworkBean hb=new HomeworkBean(split[0], Integer.parseInt(split[1]), split[2], split[3], split[4], split[5], Integer.parseInt(split[6]), Integer.parseInt(split[7]));
		HomeworkDao hd=new HomeworkDaoImpl();
		hd.publishNewHomework(hb);
		HttpSession session=request.getSession(true);
		session.setAttribute("hm_submit_success", "true");
		PrintWriter out=response.getWriter();
		out.println("<script type=\"text/javascript\">window.location.href=\"/Homeworks/jsp/teacher_homework_management.jsp\";</script>");
	}

}
