package edu.nju.Homeworks.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HomeworkSubmitInfoHandler extends SimpleTagSupport{
	
private HttpServletRequest request;
	
	public void setRequest(HttpServletRequest r){
		request=r;
	}
	
	public void doTag(){
		JspWriter out=getJspContext().getOut();
		HttpSession session=request.getSession(true);
		Object inf=session.getAttribute("hmk_submit");
		if(inf!=null){
			String info=(String)inf;
		if(info.equals("success")){
			try {
				out.print("作业成功上传！");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(info.equals("fail")){
			try {
				out.print("文件类型错误！");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		session.removeAttribute("hmk_submit");
		}
		else{
			try {
				out.print("选择一门课程提交作业");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
