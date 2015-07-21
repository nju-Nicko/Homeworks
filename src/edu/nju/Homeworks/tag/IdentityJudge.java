package edu.nju.Homeworks.tag;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import edu.nju.Homeworks.dao.PairDao;
import edu.nju.Homeworks.dao.PairDaoImpl;
import edu.nju.Homeworks.model.UserBean;

public class IdentityJudge extends SimpleTagSupport {
	
	private HttpSession session;
	
	public void setSession(HttpSession session){
		this.session=session;
	}
	
	public void doTag(){
		UserBean ub=(UserBean)(session.getAttribute("user"));
		PairDao pd=new PairDaoImpl();
		boolean flag=pd.isAssistant(ub.getName());
		JspWriter out=getJspContext().getOut();
		if(flag){
			session.setAttribute("not_assistant", "false");
		}
		else{
			try {
				out.print("您不是任何课程的助教！");
				session.setAttribute("not_assistant", "true");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
