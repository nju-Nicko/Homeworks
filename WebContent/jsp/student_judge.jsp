<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="identity" uri="/WEB-INF/tlds/identity_judge.tld" %>
<%@ taglib prefix="course" uri="/WEB-INF/tlds/courses.tld" %>
<%@ taglib prefix="info" uri="/WEB-INF/tlds/info.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>助教评分页面</title>
<link href="../css/student_style.css" rel="stylesheet" type="text/css"/>
<script src="../js/student_homework_judge.js"></script>
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../scripts/jquery.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body onload="init()">
      <jsp:include page="../nav.jsp"/>
      <course:assistant_course stu_name="${user.name }"/>
      <ul class="list-group" id="my_course" style="font-family: 微软雅黑;"></ul>
      <span id="warn"><identity:judge session="${ pageContext.session}" /><info:judgeFile_submit_info request="${pageContext.request }" /></span>
</body>
</html>