<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="course" uri="/WEB-INF/tlds/teacher_course.tld" %>
    <%@taglib prefix="info" uri="/WEB-INF/tlds/info.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>教师作业管理页面</title>
   <script type="text/javascript" src="../js/teacher_homework_management.js"></script>
   <link href="../css/teacher_style.css" type="text/css" rel="stylesheet"/>
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
  </head>
  <body onload="init()">
 <jsp:include page="../nav.jsp"/>
        <course:teacher_courses teacher_name="${user.name }" />
       <ul class="list-group" id="my_course" style="font-family: 微软雅黑; list-style: none;"></ul>
       <span id="warn"><info:newHomeworkCreateInfo request="${pageContext.request }"/></span>
       <form method="post" action="/Homeworks/CreateNewHomework"  id="form0">
       		<input type="text" id="assi_detail" name="assi_detail" style="display: none;"/>
       		<input type="text" id="hm_cou_name" style="display: none;"/>
       </form>
       <table id="homework_detail" class="table table-bordered" style="position: absolute; left: 20%; top: 100px; width: 50%; font-family: 微软雅黑; display:none;">
    <tr><th>作业编号</th><td><input type="text" id="assi_number" class="form-control" disabled></td><th>作业描述</th><td><input type="text" id="assi_info" class="form-control" ></td></tr>
    <tr><th>提交期限</th><td> <div id="datetimepicker2" class="input-append date">
      <input id="assi_sub_ddl" type="text"></input>
      <span class="add-on">
        <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
      </span>
    </div></td><th>批改期限</th><td> <div id="datetimepicker" class="input-append date">
      <input type="text" id="assi_judge_ddl"></input>
      <span class="add-on">
        <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
      </span>
    </div></td></tr>
    <tr><th>文件格式</th><td> <select id="type" class="form-control">
         <option id="docx">.docx</option>
         <option>.txt</option>
         <option>.zip</option>
      </select></td><th>分数</th><td> <select id="score" class="form-control">
         <option id="s1">1</option>
         <option>2</option>
         <option>3</option>
         <option>4</option>
         <option>5</option>
      </select></td></tr>
    <tr><th>难度</th><td> <select id="difficulty" class="form-control">
         <option id="d1">1</option>
         <option>2</option>
         <option>3</option>
         <option>4</option>
         <option>5</option>
      </select></td><th>操作</th><td><button type="button" class="btn btn-success "  onclick="submitData()">发布作业</button></td></tr>
</table>

    <script type="text/javascript"
     src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js">
    </script>
    <script type="text/javascript"
     src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js">
    </script>
    <script type="text/javascript" src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript">
      $('#datetimepicker').datetimepicker({
        format: 'MM/dd/yyyy-hh:mm',
        language: 'en',
        pickDate: true,
        pickTime: true,
        hourStep: 1,
        minuteStep: 15,
        secondStep: 30,
        inputMask: true
      });
      $('#datetimepicker2').datetimepicker({
          format: 'MM/dd/yyyy-hh:mm',
          language: 'en',
          pickDate: true,
          pickTime: true,
          hourStep: 1,
          minuteStep: 15,
          secondStep: 30,
          inputMask: true
        });
    </script>
  </body>
  </html>