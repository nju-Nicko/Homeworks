package edu.nju.Homeworks.servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.Homeworks.dao.PairDao;
import edu.nju.Homeworks.dao.PairDaoImpl;
import edu.nju.Homeworks.model.PairBean;

/**
 * Servlet implementation class GetCourseStuAssis
 */
@WebServlet("/GetCourseStuAssis")
public class GetCourseStuAssis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCourseStuAssis() {
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
		String cou_name=request.getParameter("course_name");
		cou_name=URLDecoder.decode(cou_name, "UTF-8");
		PairDao pd=new PairDaoImpl();
		ArrayList<PairBean> l1=pd.loadSelectInfo(cou_name);
		ArrayList<PairBean> l2=pd.loadAssistantInfo(cou_name);
		String result="";
		for(int i=0; i<=l1.size()-1; i++){
			result=result+l1.get(i).getStu_name()+" ";
		}
		result+=";";
		for(int i=0; i<=l2.size()-1; i++){
			result=result+l2.get(i).getStu_name()+" ";
		}
		response.getWriter().print(result);
	}

}
