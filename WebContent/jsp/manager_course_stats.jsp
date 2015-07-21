<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="course" uri="/WEB-INF/tlds/courses.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>教学负责人数据统计页面</title>
<script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../js/manager_course_stats.js"></script>
<script src="../js/highcharts.js"></script>
<script src="../js/modules/exporting.js"></script>
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../css/manager_style.css" rel="stylesheet"/>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body onload="init()">
    <jsp:include page="../nav.jsp"/>
    <ul class="nav nav-tabs" style="position: absolute; left: 0px; top: 35px; font-family: 微软雅黑; width: 100%;">
   <li class="active"><a href="#">课程</a></li>
   <li><a href="manager_student_stats.jsp">学生</a></li>
   <li><a href="manager_assistant_stats.jsp">助教</a></li>
   <li><a href="manager_teacher_stats.jsp">教师</a></li>
</ul>
        <course:courses />
        <ul class="list-group" id="students" style="font-family: 微软雅黑;"></ul>
        <span id="warn">选择一门课程进行查看</span>
</body>
</html>