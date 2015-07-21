package edu.nju.Homeworks.tag;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class LoginErrorHandler extends SimpleTagSupport{
	
	HttpSession session;
	
	public void setSession(HttpSession s){
		this.session=s;
	}
	
	public void doTag(){
		Object o=session.getAttribute("error");
		if(o!=null){
			if(((String)o).equals("true")){
				JspWriter out=getJspContext().getOut();
				try {
					out.println("<div id=\"fail\" class=\"alert alert-danger\" style=\"text-align: center; position: absolute; left: 43%; width: 14%; top: 570px; background-color: rgba(0, 0, 0, 0); border-width: 0px;\">登录失败！</div>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
