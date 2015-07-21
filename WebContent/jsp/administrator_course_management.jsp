<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix="course" uri="/WEB-INF/tlds/courses.tld" %>
 <%@taglib prefix="info" uri="/WEB-INF/tlds/info.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员-课程管理页面</title>
<link href="../css/administrator_style.css"  type="text/css"  rel="stylesheet"/>
 <script type="text/javascript" src="../js/administrator_course_management.js"></script>
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../scripts/jquery.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body onload="init()">
		     <jsp:include page="../nav.jsp"/>
		     <course:courses />
		     <info:adminSubmitCourseInfo request="${pageContext.request }"/>
		     <form method="post" action="/Homeworks/CourseChange">
		        <input type="text" name="tb_result" id="res" style="display: none;"/>
		        <div class="btn-group  btn-group-md" style="position: absolute; top: 60px; left: 5.5%;">
                  <button type="button" class="btn btn-primary"  onclick="submitData()">
                      <span class="glyphicon glyphicon-upload"></span>上传
                  </button>
                  <button type="button" class="btn btn-success" onclick="addOneColumn()">
                      <span class="glyphicon glyphicon-plus"></span>新增
                  </button>
                  </div>
		              <table class="table table-striped" id="um_tb">
		                   <thead>
		                   <tr>
		                   		<th style="font-family: 微软雅黑;">课程名称</th>
		                   		<th style="font-family: 微软雅黑;">授课教师</th>	  
		                   		<th style="font-family: 微软雅黑;">开设学期</th>	 
		                   		<th style="font-family: 微软雅黑;">更多操作</th>	     		
		                   </tr>
		                   </thead>
		                   <tbody id="um_tbb">
   </tbody>
		              </table>
		     </form>
</body>
</html>