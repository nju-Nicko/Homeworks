package edu.nju.Homeworks.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ScoreSubmitInfoHandler extends SimpleTagSupport {
	
	private HttpServletRequest request;
	
	public void setRequest(HttpServletRequest r){
		request=r;
	}
	
	public void doTag(){
		JspWriter out=getJspContext().getOut();
		HttpSession session=request.getSession(true);
		Object info=session.getAttribute("stu_scores_submit_success");
		if(info==null){
			try {
				out.print("选择一门课程登记成绩");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				out.print("成绩登记成功");
				session.removeAttribute("stu_scores_submit_success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
