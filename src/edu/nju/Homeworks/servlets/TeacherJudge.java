package edu.nju.Homeworks.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.Homeworks.dao.FileDao;
import edu.nju.Homeworks.dao.FileDaoImpl;

/**
 * Servlet implementation class TeacherJudge
 */
@WebServlet("/TeacherJudge")
public class TeacherJudge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherJudge() {
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
		String info=request.getParameter("flag");
		String[] split=info.split(" ");
		String c_name=split[0];
		int h_id=Integer.parseInt(split[1]);
		String s_name=split[2];
		int j=Integer.parseInt(split[3]);
		
		FileDao fd=new FileDaoImpl();
		fd.changeAssisJudgeFileOKState(c_name, s_name, h_id, j);
		
		HttpSession session=request.getSession(true);
		session.setAttribute("d_j", "success");
		
		response.sendRedirect("/Homeworks/jsp/teacher_download_judge.jsp");
	}

}
