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
 * Servlet implementation class UserChange
 */
@WebServlet("/UserChange")
public class UserChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserChange() {
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
		String result=request.getParameter("tb_result");
		String[] split=result.split(";");
		UserDao ud=new UserDaoImpl();
		ud.removeAll();
		ud.insert(UserDaoImpl.admin);
		for(int i=0; i<=split.length-1; i++){
			String[] split2=split[i].split(" ");
			String type="";	
			if(split2.length==4){
			switch(split2[2]){
			case "学生": type="s"; break;
			case "教师": if(split2[3].equals("授课教师")) type="t"; else if(split2[3].equals("教学负责人")) type="m"; else type="mt"; break;
			}
			UserBean ub=new UserBean(split2[0], split2[1], type);
			ud.insert(ub);
			}
		}
		HttpSession session=request.getSession(true);
		session.setAttribute("admin_submit_user_success", "true");
		response.sendRedirect("/Homeworks/jsp/administrator_user_management.jsp");
	}

}
