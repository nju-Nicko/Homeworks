<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>教学负责人个人信息页面</title>
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../scripts/jquery.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
		  <jsp:include page="../nav.jsp"/>
		         <table class="table table-bordered" style="position: absolute; left: 10%; width: 40%; top: 80px;">
   <caption>基本信息</caption>
   <thead>
      <tr>
         <th>用户名</th>
         <th>密码</th>
         <th>用户类型</th>
      </tr>
   </thead>
   <tbody>
      <tr>
         <td>${user.name }</td>
         <td>${user.pass }</td>
         <td>${user.tt }</td>
      </tr>
   </tbody>
</table>
</body>
</html>