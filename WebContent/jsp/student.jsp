<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="warn"  uri="/WEB-INF/tlds/warn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生页面</title>
<link href="../css/student_style.css"  type="text/css"  rel="stylesheet"/>
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../scripts/jquery.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
		<jsp:include page="../nav.jsp"/>
		<img src="../image/homework_upload.png" class="upload_ico" onclick="window.location.href='student_homework_submit.jsp'"/>
        <img src="../image/score.png" class="score_ico" onclick="window.location.href='student_my_score.jsp'"/>
        <img src="../image/judge.png" class="judge_ico" onclick="window.location.href='student_judge.jsp'"/>
        <img src="../image/userinfo.png" class="userinfo_ico" onclick="window.location.href='student_personal_info.jsp';"/>
        <a href="student_homework_submit.jsp" id="homework_upload" class="instruction_text" style="font-family: 微软雅黑;">作业提交</a>
        <a href="student_my_score.jsp" id="score_show" class="instruction_text" style="font-family: 微软雅黑;">我的成绩</a>
       <a href="student_judge.jsp" id="score_judge" class="instruction_text" style="font-family: 微软雅黑;">作业批改</a>
       <a href="student_personal_info.jsp" id="selfinfo" class="instruction_text" style="font-family: 微软雅黑;">个人信息</a> 
       <jsp:include page="../horizontal.jsp" />
       <warn:warns ub="${user }"/>
</body>
</html>