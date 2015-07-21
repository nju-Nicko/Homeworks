<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="course" uri="/WEB-INF/tlds/teacher_course.tld" %>
<%@taglib prefix="student" uri="/WEB-INF/tlds/students.tld" %>
<%@ taglib prefix="info" uri="/WEB-INF/tlds/info.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>教师课程管理页面</title>
<link href="../css/teacher_style.css" type="text/css" rel="stylesheet"/>
<script src="../js/teacher_course_management.js"></script>
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../scripts/jquery.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body onload="init()">
       <jsp:include page="../nav.jsp"/>
       <course:teacher_courses teacher_name="${user.name }" />
       <student:students />
       <ul class="list-group" id="my_course" style="font-family: 微软雅黑;"></ul>
<span id="warn"><info:selectSubmitInfo request="${pageContext.request }"/></span>
<form action="/Homeworks/SelectChange" method="post">
	<input type="text" name="new_stu" id="new_stu" style="display: none;"/>
	<input type="text" name="new_assis" id="new_assis" style="display: none;"/>
	<button id="submit-btn" type="button" class="btn btn-success" style="position: absolute; left: 20%; top: 200px; display: none;" onclick="submitData()">提交结果</button>
	 <div class="col-lg-6" style="position: absolute; left: 18.5%; top: 250px; width: 20%; display: none;" id="go_search">
            <div class="input-group">
               <input type="text" class="form-control" placeholder="输入学生ID快速查找......" id="key">
               <span class="input-group-btn">
                  <button class="btn btn-default" type="button" onclick="filter()">
                     Go!
                  </button>
               </span>
            </div><!-- /input-group -->
         </div><!-- /.col-lg-6 -->
</form>
</body>
</html>