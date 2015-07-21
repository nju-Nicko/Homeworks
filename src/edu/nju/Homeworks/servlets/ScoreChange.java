package edu.nju.Homeworks.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.Homeworks.dao.ScoreDao;
import edu.nju.Homeworks.dao.ScoreDaoImpl;
import edu.nju.Homeworks.model.ScoreBean;

/**
 * Servlet implementation class ScoreChange
 */
@WebServlet("/ScoreChange")
public class ScoreChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreChange() {
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
		ArrayList<ScoreBean> list=new ArrayList<ScoreBean>();
		String scores=request.getParameter("scores");
		if(!scores.equals("")){
		String split[]=scores.split(";");
		String[] split2=split[0].split(" ");
		String c_name=split2[0];
		list.add(new ScoreBean(c_name, split2[1], Integer.parseInt(split2[2]), "", Integer.parseInt(split2[3]), split2[4]));
		for(int i=1; i<=split.length-1; i++){
			String[] split3=split[i].split(" ");
			list.add(new ScoreBean(c_name, split3[0], Integer.parseInt(split3[1]), "", Integer.parseInt(split3[2]), split3[3]));
		    }
		ScoreDao sd=new ScoreDaoImpl();
		sd.updateScores(list);
		HttpSession session=request.getSession(true);
		session.setAttribute("stu_scores_submit_success", "true");
		response.sendRedirect("/Homeworks/jsp/teacher_record_score.jsp");
		}
	}

}
