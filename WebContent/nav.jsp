<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title></title>
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
    
    #ico:hover{
    cursor: pointer;
    }
    
    #username{
    color: white; position: absolute; left: 80%; top: 0px; height: 35px; text-align: center; line-height: 35px;
    }
</style>
<script type="text/javascript">
        var odd=false;
        
		function jump(type){
			switch(type){
			case "s": window.location.href="student.jsp"; break;
			case "t":  window.location.href="teacher.jsp"; break;
			case "mt": window.location.href="mt.jsp"; break;
			case "ad": window.location.href="administrator.jsp"; break;
			case "m": window.location.href="manager.jsp"; break;
			}
		}
		
		function show(){
			var u=document.getElementById("username");
			var uw=u.offsetWidth;   //获取username元素的宽度
			var uh=u.offsetHeight;
			var ut=u.offsetTop;
			var ul=u.offsetLeft;
			if(!odd){
				odd=true;
				var div=document.createElement("div");
				div.id="info";
				div.style.textAlign="center";
				div.style.position="absolute";
				div.style.width=uw+2+"px";
				div.style.height="50px";
				div.style.top=ut+uh+"px";
				div.style.left=ul+"px";
				div.style.borderLeft="thin solid black";
				div.style.borderRight="thin solid black";
				div.style.borderBottom="thin solid black";
				document.body.appendChild(div);
				
				var a=document.createElement("a");
				a.innerText="注销";
				a.style.cssText="width: "+uw+"px; font-family: 微软雅黑;";
				a.href="/Homeworks/Signout";
				div.appendChild(a);
				
			    u.style.backgroundColor="white";
				u.style.color="black";
				u.style.borderLeft="thin solid black";
				u.style.borderRight="thin solid black";
				u.style.borderTop="thin solid black";
				}
				else{
					var div=document.getElementById("info");
					if(div!=null)
						 document.body.removeChild(div);
					u.style.backgroundColor="rgba(0, 0, 0, 0)";
					u.style.borderTop="0px";
					u.style.borderLeft="0px";
					u.style.borderRight="0px";
					odd=false;
					u.style.color="white";
				}
		}
		
		function mouseIn(){
			var u=document.getElementById("username");
			var div=document.getElementById("info");
			if(div==null){
			   u.style.cssText="background-color: #777777; cursor: pointer;";
			}
			
		}
		
		function recover(){
			var u=document.getElementById("username");
			var div=document.getElementById("info");
			if(div==null){
				   u.style.cssText="background-color: rgba(0, 0, 0, 0); cursor: default;";
				}
		}
</script>
</head>
<body>
   <jsp:useBean id="user" class="edu.nju.Homeworks.model.UserBean"  scope="session"></jsp:useBean>
   <div id="nav">
      <label id="ico" onclick="jump(this.className)" class="${user.type }">课程管理系统</label>
      <span id="username" class="glyphicon glyphicon-user"  onclick="show()" onmouseover="mouseIn()" onmouseout="recover()" >
               <jsp:getProperty name="user" property="name"></jsp:getProperty>
      </span>
   </div>
</body>
</html>