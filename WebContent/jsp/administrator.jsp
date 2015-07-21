<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员页面</title>
<link href="../css/administrator_style.css"  type="text/css"  rel="stylesheet"/>
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../scripts/jquery.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
     <jsp:include page="../nav.jsp"/>
     <img src="../image/user.png" class="management_ico1" onclick="window.location.href='administrator_user_management.jsp';"/>
     <img src="../image/course.png" class="management_ico2" onclick="window.location.href='administrator_course_management.jsp';"/>
     <img src="../image/userinfo.png" class="userinfo_ico" onclick="window.location.href='administrator_personal_info.jsp';"/>
     <a href="administrator_user_management.jsp" id="user_management" class="instruction_text" style="font-family: 微软雅黑;">用户管理</a>
     <a href="administrator_course_management.jsp" id="course_management" class="instruction_text" style="font-family: 微软雅黑;">教学计划</a>
     <a href="administrator_personal_info.jsp" id="selfinfo" class="instruction_text" style="font-family: 微软雅黑;">个人信息</a> 
     <jsp:include page="../horizontal.jsp" />
</body>
</html>