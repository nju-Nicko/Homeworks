package edu.nju.Homeworks.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class UserSubmitInfoHandler extends SimpleTagSupport{
	
	private HttpServletRequest request;
	
	public void setRequest(HttpServletRequest r){
		request=r;
	}
	
	public void doTag(){
		JspWriter out=getJspContext().getOut();
		HttpSession session=request.getSession(true);
		Object info=session.getAttribute("admin_submit_user_success");
		if(info!=null){
			try {
				out.print("<div class=\"alert alert-success\" style=\"position: absolute; top: 50px; left: 18%; width: 12%; background-color: rgba(0, 0, 0, 0); border-width: 0px;\">用户信息上传成功！</div>");
				session.removeAttribute("admin_submit_user_success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
