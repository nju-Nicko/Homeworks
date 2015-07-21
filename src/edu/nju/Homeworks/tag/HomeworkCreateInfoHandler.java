package edu.nju.Homeworks.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HomeworkCreateInfoHandler extends SimpleTagSupport{
	
private HttpServletRequest request;
	
	public void setRequest(HttpServletRequest r){
		request=r;
	}
	
	public void doTag(){
		JspWriter out=getJspContext().getOut();
		HttpSession session=request.getSession(true);
		Object info=session.getAttribute("hm_submit_success");
		Object info2=session.getAttribute("demo_submit");
		if(info==null&&info2==null){
			try {
				out.print("选择一门课程布置作业");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(info!=null&&info2==null){
			try {
				out.print("作业布置成功");
				session.removeAttribute("hm_submit_success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(info2!=null && info==null){
			try {
				out.print("样例上传成功");	
				session.removeAttribute("demo_submit");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
