package edu.nju.Homeworks.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.Homeworks.dao.UserDao;
import edu.nju.Homeworks.dao.UserDaoImpl;
import edu.nju.Homeworks.model.UserBean;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
              String username=request.getParameter("username");
              String password=request.getParameter("password");
              UserDao ud=new UserDaoImpl();
              UserBean ub=ud.find(username, password);
              HttpSession session=request.getSession(true);
              if(ub==null){
            	  HttpSession hs=request.getSession(true);
            	  hs.setAttribute("error", "true");
            	  response.sendRedirect("/Homeworks/index.jsp");
              }
              else{
            	  session.setAttribute("user", ub);
            	  switch(ub.getType()){
            	  case "s": response.sendRedirect("/Homeworks/jsp/student.jsp"); break;
            	  case "ad": response.sendRedirect("/Homeworks/jsp/administrator.jsp"); break;
            	  case "t": response.sendRedirect("/Homeworks/jsp/teacher.jsp"); break;
            	  case "m":  response.sendRedirect("/Homeworks/jsp/manager.jsp"); break;
            	  case "mt": response.sendRedirect("/Homeworks/jsp/mt.jsp"); break;
            	  }
              }
	}

}
