package edu.nju.Homeworks.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SelectInfoHandler extends SimpleTagSupport{
	
	private HttpServletRequest request;
	
	public void setRequest(HttpServletRequest r){
		request=r;
	}
	
	public void doTag(){
		JspWriter out=getJspContext().getOut();
		HttpSession session=request.getSession(true);
		Object info=session.getAttribute("submit_success");
		if(info==null){
			try {
				out.print("选择一门课程进行设置");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				out.print("选课和助教信息提交成功");
				session.removeAttribute("submit_success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
