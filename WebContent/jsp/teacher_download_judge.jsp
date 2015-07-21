<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="course" uri="/WEB-INF/tlds/teacher_course.tld" %>
 <%@taglib prefix="info" uri="/WEB-INF/tlds/info.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/teacher_style.css" type="text/css" rel="stylesheet"/>
<script src="../js/teacher_download_judge.js"></script>
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../scripts/jquery.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<title>教师点评下载页面</title>
</head>
<body onload="init()">
    <jsp:include page="../nav.jsp"/>
    <course:teacher_courses teacher_name="${user.name }" />
    <ul class="list-group" id="my_course" style="font-family: 微软雅黑;"></ul>
    <span id="warn"><info:TeacherJudgeJudgeSubmitInfo request="${pageContext.request }"/></span>
</body>
</html>