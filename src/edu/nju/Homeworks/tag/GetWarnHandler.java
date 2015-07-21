package edu.nju.Homeworks.tag;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import edu.nju.Homeworks.dao.WarnDao;
import edu.nju.Homeworks.dao.WarnDaoImpl;
import edu.nju.Homeworks.model.UserBean;
import edu.nju.Homeworks.model.WarnBean;

public class GetWarnHandler extends SimpleTagSupport {
	
	private UserBean ub;
	
	public void setUb(UserBean ub){
		this.ub=ub;
	}
	
	public void doTag(){
		JspWriter out=getJspContext().getOut();
		WarnDao wd=new WarnDaoImpl();
		ArrayList<WarnBean> list=wd.getNewHomeworkWarn(ub.getName());
		ArrayList<WarnBean> list2=wd.getAssistantSubmitWarn(ub.getName());
		ArrayList<WarnBean> list3=wd.getNewTeacherJudgeWarn(ub.getName());
		list.addAll(list2);
		list.addAll(list3);    //获取所有可能的提醒信息
		String result=null;
		for(int i=0; i<=list.size()-1; i++){
			WarnBean wb=list.get(i);
			if(wb.getType()==WarnBean.WarnType.newHomework){
				int top=320+i*30;
				result="<span style=\"color: red;\"><b>【新的作业】</b></span>"+wb.getContext()+"布置了新作业"+" ";
				try {
					out.print("<span style=\"position: absolute; top: "+top+"px; left: 6%; font-size: 0.9em; font-family: 微软雅黑;  color: purple;\">"+result+"</span>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(wb.getType()==WarnBean.WarnType.newAssistantSubmit){
				int top=320+i*30;
				String[] split=wb.getContext().split(" ");
				String stu_name=split[1];
				String cou_name=split[0];
				int h_id=Integer.parseInt(split[2]);
				result="<span style=\"color: red;\"><b>【作业批改】</b></span>"+stu_name+"提交了选"+cou_name+"的同学的作业"+h_id+"的批改文件"+" ";
				try {
					out.print("<a href=\"/Homeworks/DownloadAssisJudgeFile?course_name="+cou_name+"&student_name="+stu_name+"&homework_id="+h_id+"\"  style=\"position: absolute; top: "+top+"px; left: 6%; font-size: 0.9em; font-family: 微软雅黑;  color: purple;\">"+result+"</a>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(wb.getType()==WarnBean.WarnType.teacherJudge){
				int top=320+i*30;
				String[] split=wb.getContext().split(" ");
				String c_name=split[0];
				String h_id=split[1];
				String ok=split[2];
				if(ok.equals("1")){
					result="<span style=\"color: red;\"><b>【作业点评】</b></span>"+"你的"+c_name+"课程的作业"+h_id+"提交的点评文件通过了教师审核";
				}
				else if(ok.equals("2")){
					result="<span style=\"color: red;\"><b>【作业点评】</b></span>"+"你的"+c_name+"课程的作业"+h_id+"提交的点评文件没有通过教师审核，请重新批改";
				}
				try {
					out.print("<span style=\"position: absolute; top: "+top+"px; left: 6%; font-size: 0.9em; font-family: 微软雅黑;  color: purple;\">"+result+"</span>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	}

}
