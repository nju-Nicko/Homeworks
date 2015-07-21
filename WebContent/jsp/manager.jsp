<%@ page language="java" contentType="text/html; charset=uTf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Utf-8">
<title>教学负责人页面</title>
<link href="../css/manager_style.css" rel="stylesheet" type="text/css"/>
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../scripts/jquery.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
			<jsp:include page="../nav.jsp"/>
			<img src="../image/stats.png" class="stats_ico" onclick="window.location.href='manager_course_stats.jsp';"/>
			<img src="../image/userinfo.png" class="userinfo_ico" onclick="window.location.href='manager_personal_info.jsp';"/>
			<a href="manager_course_stats.jsp" id="stats" class="instruction_text" style="font-family: 微软雅黑;">数据统计</a>
		    <a href="manager_personal_info.jsp" id="selfinfo" class="instruction_text" style="font-family: 微软雅黑;">个人信息</a>
		    <jsp:include page="../horizontal.jsp"/> 
</body>
</html>