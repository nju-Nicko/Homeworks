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

import edu.nju.Homeworks.dao.FileDao;
import edu.nju.Homeworks.dao.FileDaoImpl;
import edu.nju.Homeworks.model.FileBean;
import edu.nju.Homeworks.model.UserBean;

/**
 * Servlet implementation class AssistantJudgeFileUpload
 */
@WebServlet("/AssistantJudgeFileUpload")
public class AssistantJudgeFileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssistantJudgeFileUpload() {
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
		String course_name=request.getParameter("course_name");
		course_name=URLDecoder.decode(course_name, "UTF-8");
		FileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload upload=new ServletFileUpload(factory);
		String realDirectory=request.getSession().getServletContext().getRealPath("/");
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> list=upload.parseRequest(request);
			for(FileItem item: list){
				if(item.isFormField()){
					
				}
				else{
					String lastpath=item.getName();
					 lastpath = lastpath.substring(lastpath.lastIndexOf(".")); 
					 String filename = UUID.randomUUID().toString().replace("-", "") + lastpath;
					 try {
						item.write(new File(realDirectory+filename));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 HttpSession session=request.getSession();
					 session.setAttribute("jf_submit", "success");
					 UserBean ub=(UserBean)session.getAttribute("user");
					 FileDao fd=new FileDaoImpl();
					 fd.updateAssistantJudgeFile(new FileBean(course_name, 0, ub.getName(), filename));
					 response.sendRedirect("/Homeworks/jsp/student_judge.jsp");
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
