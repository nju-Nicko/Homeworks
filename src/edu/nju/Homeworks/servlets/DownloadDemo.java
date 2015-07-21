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

import edu.nju.Homeworks.dao.HomeworkDao;
import edu.nju.Homeworks.dao.HomeworkDaoImpl;

/**
 * Servlet implementation class DownloadDemo
 */
@WebServlet("/DownloadDemo")
public class DownloadDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String c_name=request.getParameter("course_name");
		c_name=URLDecoder.decode(c_name, "UTF-8");
		int h_id=Integer.parseInt(request.getParameter("homework_id"));
		
		HomeworkDao hd=new HomeworkDaoImpl();
		String path=hd.downloadDemo(c_name, h_id);
		
		if(path.equals("暂无样例")){
			path="error";
		}
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
