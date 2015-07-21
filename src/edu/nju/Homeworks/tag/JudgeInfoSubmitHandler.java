package edu.nju.Homeworks.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class JudgeInfoSubmitHandler extends SimpleTagSupport {
	
private HttpServletRequest request;
	
	public void setRequest(HttpServletRequest r){
		request=r;
	}
	
	public void doTag(){
		JspWriter out=getJspContext().getOut();
		HttpSession session=request.getSession(true);
		Object inf=session.getAttribute("jf_submit");
		if(inf!=null){
			String info=(String)inf;
		if(info.equals("success")){
			try {
				out.print("作业点评成功上传！");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		session.removeAttribute("jf_submit");
		}
		else if(!((String)session.getAttribute("not_assistant")).equals("true")){
			try {
				out.print("选择一门课程批改作业");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}				
		session.removeAttribute("not_assistant");
	}

}
