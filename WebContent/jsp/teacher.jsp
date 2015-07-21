<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="warn"  uri="/WEB-INF/tlds/warn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>教师页面</title>
<link href="../css/teacher_style.css"  type="text/css"  rel="stylesheet"/>
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../scripts/jquery.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
      <jsp:include page="../nav.jsp"/>
      <img src="../image/course.png" class="management_ico2" onclick="window.location.href='teacher_course_management.jsp'"/>
      <img src="../image/homework.png" class="management_ico3" onclick="window.location.href='teacher_homework_management.jsp'"/>
      <img src="../image/userinfo.png" class="userinfo_ico" onclick="window.location.href='teacher_personal_info.jsp';"/>
      <img src="../image/document_save.png" class="download_ico" onclick="window.location.href='teacher_record_score.jsp';"/>
      <img src="../image/download_file.png" class="d_ico" onclick="window.location.href='teacher_download_judge.jsp'"/>
      <a href="teacher_course_management.jsp" id="course_management" class="instruction_text" style="font-family: 微软雅黑;">课程管理</a>
      <a href="teacher_homework_management.jsp" id="homework_management" class="instruction_text" style="font-family: 微软雅黑;">作业管理</a>
      <a href="teacher_personal_info.jsp" id="selfinfo" class="instruction_text" style="font-family: 微软雅黑;">个人信息</a>    
      <a href="teacher_record_score.jsp" id="download" class="instruction_text" style="font-family: 微软雅黑;">成绩登记</a>
      <a href="teacher_download_judge.jsp" id="d" class="instruction_text" style="font-family: 微软雅黑;">点评下载</a> 
      <jsp:include page="../horizontal.jsp" />
      <warn:warns ub="${user }"/>
</body>
</html>