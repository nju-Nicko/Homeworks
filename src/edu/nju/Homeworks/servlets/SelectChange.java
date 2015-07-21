package edu.nju.Homeworks.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.Homeworks.dao.PairDao;
import edu.nju.Homeworks.dao.PairDaoImpl;

/**
 * Servlet implementation class SelectChange
 */
@WebServlet("/SelectChange")
public class SelectChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectChange() {
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
		String info=request.getParameter("new_stu");
		String info2=request.getParameter("new_assis");
		PairDao pd=new PairDaoImpl();
		pd.updateStudentSelect(info);
		pd.updateAssistantAssignment(info2);
		HttpSession session=request.getSession(true);
		session.setAttribute("submit_success", "true");
		PrintWriter out=response.getWriter();
		out.println("<script type=\"text/javascript\">window.location.href=\"/Homeworks/jsp/teacher_course_management.jsp\";</script>");
	}

}
