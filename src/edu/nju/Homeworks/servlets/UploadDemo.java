package edu.nju.Homeworks.servlets;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import edu.nju.Homeworks.dao.HomeworkDao;
import edu.nju.Homeworks.dao.HomeworkDaoImpl;

/**
 * Servlet implementation class UploadDemo
 */
@WebServlet("/UploadDemo")
public class UploadDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String c_name=request.getParameter("course_name");
		c_name=URLDecoder.decode(c_name, "UTF-8");
	    int h_id=Integer.parseInt(request.getParameter("homework_id"));
	    
		FileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload upload=new ServletFileUpload(factory);
		String realDirectory = request.getSession().getServletContext().getRealPath("/"); 
		
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> list=upload.parseRequest(request);
			for(FileItem item: list){
				if(item.isFormField()){   //判断FileItem是一个文件上传对象还是普通表单对象
				}
				else{
					 String lastpath = item.getName();//获取上传文件的名称  
					 lastpath = lastpath.substring(lastpath.lastIndexOf(".")); 
					 String filename = UUID.randomUUID().toString().replace("-", "") + lastpath;  
					 try {
						item.write(new File(realDirectory+filename));
						 HttpSession session=request.getSession();
						 session.setAttribute("demo_submit", "success");
						 HomeworkDao hd=new HomeworkDaoImpl();
						 hd.publishHomeworkDemo(c_name, h_id, filename);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/Homeworks/jsp/teacher_homework_management.jsp");
	}

}
