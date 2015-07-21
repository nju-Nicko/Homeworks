<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="course" uri="/WEB-INF/tlds/teacher_course.tld" %>
<%@ taglib prefix="info" uri="/WEB-INF/tlds/info.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>教师登记成绩页面</title>
<script type="text/javascript" src="../js/teacher_record_score.js"></script>
<link type="text/css" href="../css/teacher_style.css" rel="stylesheet"/>
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../scripts/jquery.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body onload="init()">
       <jsp:include page="../nav.jsp"/>
       <course:teacher_courses teacher_name="${user.name }" />
       <ul class="list-group" id="my_course" style="font-family: 微软雅黑;"></ul>
       <span id="warn"><info:scoreSubmitInfo request="${pageContext.request }"/></span>
       <form id="form0" class="form-inline" method="post" action="/Homeworks/ScoreChange" style="position: absolute; top: 100px; left: 20%; display: none;">
          <div class="form-group">
       		 <div  class="btn-group  btn-group-md" >
                  <button type="button" class="btn btn-primary" onclick="submitData()">
                      <span class="glyphicon glyphicon-upload"></span>上传
                  </button>
                  <button type="button" class="btn btn-success" >
                      <span class="glyphicon glyphicon-repeat"></span>重置
                  </button>
                  </div>
                  </div>
                  <div class="form-group">
                  <label for="name" >作业选择</label>
                  <input type="text" name="scores" id="scores" style="display: none;"/>
            </div>
       </form>
</body>
</html>