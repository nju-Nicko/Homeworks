<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="err" uri="/WEB-INF/tlds/error.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/index_style.css" rel="stylesheet" type="text/css"/>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="scripts/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<title>课程管理系统</title>
<style type="text/css">
    #nav{
       position: absolute;
       left: 0px;
       top: 0px;
       width: 100%;
       background-color: #111111;
       height: 35px;
       z-index: 10;
    }
    
    #ico{
       color: white;
       font-family: 微软雅黑;
       font-size: 1em;
       position: relative;
       top: 5px;
       left: 20px;
    }
    
    .code{
font-family:Arial,宋体;
font-style:italic;
color:green;
border:0;
padding:2px 3px;
letter-spacing:3px;
font-weight:bolder;
position: absolute; left: 52%; top: 490px; width: 5%;
background-color: rgb(221, 221, 221);
}
</style>
<script language="javascript" type="text/javascript">
var code;//在全局 定义验证码
var checkCode;
var input;

function init(){
	checkCode = document.getElementById("checkCode");
	input=document.getElementById("input1");
	checkCode.style.height=input.offsetHeight+"px";
	createCode();
}

function createCode(){ 
code = new Array();

var codeLength = 4;//验证码的长度
var selectChar = new Array(2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z');

for(var i=0;i<codeLength;i++) {
   var charIndex = Math.floor(Math.random()*32);
   code +=selectChar[charIndex];
}
if(code.length != codeLength){
   createCode();
}
checkCode.value = code;
}

function login(){
	var f=document.getElementById("form0");
	if(input.value.toUpperCase()!=checkCode.value.toUpperCase()){
		var yzm=document.getElementById("yanzhengma");
		if(yzm!=null) document.body.removeChild(yzm);
		var fail=document.getElementById("fail");
		if(fail!=null) document.body.removeChild(fail);
		var div=document.createElement("div");
		div.className="alert alert-danger";
		div.style.cssText="text-align: center; position: absolute; left: 43%; width: 14%; top: 570px; background-color: rgba(0, 0, 0, 0); border-width: 0px";
	    div.innerText="验证码错误！";
	    div.id="yanzhengma";
	    document.body.appendChild(div);
	}
	else{
		f.submit();
	}
}
</script>
</head>
<body onload="init()">
<div id="nav">
      <label id="ico">课程管理系统</label>
      <span class="glyphicon glyphicon-user" style="color: white; position: absolute; left: 80%; top: 10px;">
        Please Login
      </span>
   </div>
      <img class="index_bg" src="image/index.jpg"/>
   	      <form id="form0" class="form-inline" role="form" method="post" action="/Homeworks/Login">
   			   <div class="form-group">
                    <input type="text" class="form-control"  placeholder="请输入名称" style="position: absolute; left: 40%; top: 410px; width: 20%;" name="username">
                    <input type="password" class="form-control"  placeholder="请输入密码" style="position: absolute; left: 40%; top: 450px; width: 20%;" name="password">
                    <input type="text" placeholder="请输入验证码" id="input1" class="form-control" style="position: absolute; left: 40%; top: 490px; width: 10%;"/>
                    <input type="button" id="checkCode" class="code" onClick="createCode()" />
                    <button type="button"  class="btn btn-primary" style="position: absolute; left: 43%; top: 540px; width: 14%;" onclick="login();">登陆</button>                           
                </div>
   	       </form>
   	       <err:logged session="<%=session %>"/>
</body>
</html>