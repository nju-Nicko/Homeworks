package edu.nju.Homeworks.servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.Homeworks.dao.FileDao;
import edu.nju.Homeworks.dao.FileDaoImpl;

/**
 * Servlet implementation class Download
 */
@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String stu_name=request.getParameter("student_name");
		stu_name=URLDecoder.decode(stu_name, "UTF-8");
		String cou_name=request.getParameter("course_name");
		cou_name=URLDecoder.decode(cou_name, "UTF-8");
		int h_id=Integer.parseInt(request.getParameter("homework_id"));
	
		FileDao hd=new FileDaoImpl();
		String path=hd.downloadStuHomework(cou_name, stu_name, h_id);
		if(path.equals(""))          //学生没有提交这门课的这次作业
			path="error";
		String realpath=request.getSession().getServletContext().getRealPath("/"+path);
		
		String filename=realpath.substring(realpath.lastIndexOf("\\")+1);
		response.setHeader("Content-disposition", "attachment;filename="+filename); //服务器通过这个头，告诉浏览器以下载方式打开数据
		FileInputStream in = new FileInputStream(realpath);
		int len = 0;
        byte buffer[]=new byte[1024];
        OutputStream out = response.getOutputStream();
        while((len = in.read(buffer))>0){
            out.write(buffer, 0, len);
            }
        in.close();	
	}

}
