<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="course"  uri="/WEB-INF/tlds/courses.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生查看成绩页面</title>
<link href="../css/student_style.css" rel="stylesheet" type="text/css"/>
<script src="../js/student_my_score.js"></script>
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../scripts/jquery.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body onload="init()">
        <jsp:include page="../nav.jsp"/>
        <course:student_course stu_name="${user.name }"/>
        <ul class="list-group" id="my_course" style="font-family: 微软雅黑;"></ul>
     <span id="warn">选择一门课程查看成绩</span>
</body>
</html>