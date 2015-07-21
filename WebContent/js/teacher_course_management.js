var course_name;

function set_s(obj){
	switch(obj.innerText){
	  case "设为选课学生": obj.innerText="删除该生选课"; disable(obj.nextSibling.nextSibling); break;
	  case "删除该生选课": obj.innerText="设为选课学生"; enable(obj.nextSibling.nextSibling); break;
	}
}

function disable(obj){
	obj.disabled="disabled";
	obj.style.color="lightgray";
}

function enable(obj){
	obj.disabled="";
	obj.style.color="rgb(97, 92, 235)";
}

function set_a(obj){
	switch(obj.innerText){
		case "设为课程助教": obj.innerText="取消该生助教"; disable(obj.previousSibling.previousSibling); break;
		case "取消该生助教": obj.innerText="设为课程助教"; enable(obj.previousSibling.previousSibling); break;
	}
}

function init(){
	var text=document.getElementById("a_tc").value;
	var split=text.split(";");
	if(text==""){   //没有课程
		var warn=document.getElementById("warn");
		if(warn!=null)
			warn.innerText="您还没有课程";
	}
	else{  //有课程
		var ul=document.getElementById("my_course");
		for(var i=0; i<=split.length-2; i++){		
			var split2=split[i].split(" ");
			var li=document.createElement("li");
			li.className="list-group-item";
			var label=document.createElement("label");
			label.className="course_name";
			label.innerText=split2[0];
			label.onclick=function(){
				colorChange(this);
				removeOthers();
				if(isInTime(this.innerText)){
				getCourseStudent(this.innerText);
				var submit_btn=document.getElementById("submit-btn");
				submit_btn.style.display="";
				var go=document.getElementById("go_search");
				go.style.display="";
				var warn=document.getElementById("warn");
				if(warn!=null)
				document.body.removeChild(warn);
				var students=document.createElement("span");
				students.id="students";
				students.innerText="选课学生：";
				var assistants=document.createElement("span");
				assistants.id="assistants";
				assistants.innerText="课程助教：";
				var div1=document.createElement("div");
				div1.id="stu_div";
				var div2=document.createElement("div");
				div2.id="assis_div";
				document.body.appendChild(students);
				document.body.appendChild(assistants);
				document.body.appendChild(div1);			
				document.body.appendChild(div2);	
				allStudentTable(this.innerText);
				course_name=this.innerText;
				}
				else{
					var span=document.createElement("span");
					span.innerText="课程尚未开始或已经结束";
					span.id="warn";
					document.body.appendChild(span);
					var submit_btn=document.getElementById("submit-btn");
					submit_btn.style.display="none";
					var go=document.getElementById("go_search");
					go.style.display="none";
				}
			};
			li.appendChild(label);
			ul.appendChild(li);
		}
	}
}

function addAStudent(stu_name){
	var div=document.getElementById("stu_div");
	var btn=document.createElement("button");
	btn.className="btn btn-default";
	btn.innerText=stu_name;
	btn.style.marginLeft="2px";
	div.appendChild(btn);
}

function removeAStudent(stu_name){
	var div=document.getElementById("stu_div");
	var nodes=div.childNodes;
	for(var i=0; i<=nodes.length-1; i++){
		if(nodes[i].innerText==stu_name){
			div.removeChild(nodes[i]);
			break;
		}
	}
}

function addAnAssistant(assis_name){
	var div=document.getElementById("assis_div");
	var btn=document.createElement("button");
	btn.className="btn btn-default";
	btn.innerText=assis_name;
	btn.style.marginLeft="2px";
	div.appendChild(btn);
}

function removeAnAssistant(assis_name){
	var div=document.getElementById("assis_div");
	var nodes=div.childNodes;
	for(var i=0; i<=nodes.length-1; i++){
		if(nodes[i].innerText==assis_name){
			div.removeChild(nodes[i]);
			break;
		}
	}
}

function allStudentTable(course_name){
	var table=document.createElement("table");
	table.id="all_stu";	
	table.className="table table-bordered";
	table.style.cssText="position: absolute; left: 20%; top: 310px; width: 60%;";
	var thead=document.createElement("thead");
	var tr=document.createElement("tr");
	var th1=document.createElement("th");
	th1.style.cssText="font-family: 微软雅黑;";
	th1.innerText="学生ID";
	var th2=document.createElement("th");
	th2.style.cssText="font-family: 微软雅黑;";
	th2.innerText="详细设置";
	tr.appendChild(th1);
	tr.appendChild(th2);
	thead.appendChild(tr);
	table.appendChild(thead);
	var tbody=document.createElement("tbody");
	table.appendChild(tbody); 
	var text=document.getElementById("a_stu").value;
	var split=text.split(";");
	if(text!=""){
	for(var i=0; i<=split.length-2; i++){
		var split2=split[i].split(" ");
		var tr=document.createElement("tr");
		var td1=document.createElement("td");
		td1.style.cssText="font-family: 微软雅黑;";
		td1.innerText=split2[0];
		var td2=document.createElement("td");
		td2.style.cssText="font-family: 微软雅黑; color: rgb(97, 92, 235);";
		var label1=document.createElement("label");
		label1.className="set";
		label1.onclick=function(){
			set_s(this);
			if(this.innerText=="删除该生选课")
			addAStudent(this.parentNode.previousSibling.innerText);
			else{
				removeAStudent(this.parentNode.previousSibling.innerText);
			}
		};
		label1.innerText="设为选课学生";
		var label2=document.createElement("label");
		label2.className="set";
		label2.onclick=function(){
			set_a(this);
			if(this.innerText=="取消该生助教"){
				 addAnAssistant(this.parentNode.previousSibling.innerText);
			}
			else{
				removeAnAssistant(this.parentNode.previousSibling.innerText);
			}
		};
		label2.innerText="设为课程助教";
		var gang=document.createTextNode(" / ");   //创建文本节点
		td2.appendChild(label1);
		td2.appendChild(gang);
		td2.appendChild(label2);
		tr.appendChild(td1);
		tr.appendChild(td2);
		tbody.appendChild(tr);
		judge(label1, label2, split2[0], course_name);
     	}
	   document.body.appendChild(table);
	}
}

function submitData(){
	var c_name=course_name;
	var stu_div=document.getElementById("stu_div");
	var assis_div=document.getElementById("assis_div");
    var n1=stu_div.childNodes;
    var result1=c_name+" ";
    for(var i=0; i<=n1.length-1; i++){
    	result1=result1+n1[i].innerText+" ";
    }
    var n2=assis_div.childNodes;
    var result2=course_name+" ";
    for(var j=0; j<=n2.length-1; j++){
    	result2=result2+n2[j].innerText+" ";
    }
    var input1=document.getElementById("new_stu");
    var input2=document.getElementById("new_assis");
    input1.value=result1;
    input2.value=result2;
    input1.form.submit();
}

function removeOthers(){
	var warn=document.getElementById("warn");
	if(warn!=null)
		document.body.removeChild(warn);
	var label1=document.getElementById("students");
	var label2=document.getElementById("assistants");
	var div1=document.getElementById("stu_div");
	var div2=document.getElementById("assis_div");
	if(label1!=null)
	document.body.removeChild(label1);
	if(label2!=null)
	document.body.removeChild(label2);
	if(div1!=null)
	document.body.removeChild(div1);
	if(div2!=null)
	document.body.removeChild(div2);
	var table=document.getElementById("all_stu");
	if(table!=null)
	document.body.removeChild(table);
}

function colorChange(obj){
	var list_items=document.getElementsByClassName("list-group-item");
	for(var i=0; i<=list_items.length-1; i++){
		if(list_items[i].firstChild.innerText!=obj.innerText){
			list_items[i].firstChild.style.color="rgb(132, 132, 255)";
		}
		else{
			list_items[i].firstChild.style.color="purple";
		}
	}
}

function getCourseStudent(course_name){
	var xhr;
	if(window.XMLHttpRequest)
		xhr=new XMLHttpRequest();
	else
		xhr=new ActiveXObject("Microsoft.XMLHTTP");
	
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			 var result=xhr.responseText;
			 var split=result.split(";");
			 if(split[0]==""){
				 //do nothing
			 }
			 else{
				 var split2=split[0].split(" ");
				 for(var i=0; i<=split2.length-1; i++){
					 if(split2[i]!="")
					 addAStudent(split2[i]);
				 }
			 }
			 if(split[1]==""){
				 
			 }
			 else{
				 var split2=split[1].split(" ");
				 for(var i=0; i<=split2.length-1; i++){
					 if(split2[i]!="")
					 addAnAssistant(split2[i]);
				 } 
			 }
		}
	};
	
	var post="course_name="+course_name;
	post=encodeURI(post);
	post=encodeURI(post);
	
	xhr.open("POST", "/Homeworks/GetCourseStuAssis?"+post, true);
	xhr.send(null);
}

function judge(l1, l2, stu_name, course_name){
	var xhr;
	if(window.XMLHttpRequest)
		xhr=new XMLHttpRequest();
	else
		xhr=new ActiveXObject("Microsoft.XMLHTTP");
	
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			 var result=xhr.responseText;
			 var split=result.split(";");
			 if(split[0]==""){
				 //do nothing
			 }
			 else{
				 var split2=split[0].split(" ");
				 for(var i=0; i<=split2.length-1; i++){
					 if(split2[i]==stu_name){   //该学生已经选了课
						 l1.innerText="删除该生选课"; disable(l1.nextSibling.nextSibling);
					 }
				 }
			 }
			 if(split[1]==""){
				 
			 }
			 else{
				 var split2=split[1].split(" ");
				 for(var i=0; i<=split2.length-1; i++){
					 if(split2[i]==stu_name){   //该学生已经是助教
						 l2.innerText="取消该生助教"; disable(l2.previousSibling.previousSibling);
					 }
				 } 
			 }
		}
	};
	
	var post="course_name="+course_name;
	post=encodeURI(post);
	post=encodeURI(post);
	
	xhr.open("POST", "/Homeworks/GetCourseStuAssis?"+post, true);
	xhr.send(null);
}

function filter(){
	var key=document.getElementById("key").value;
	var old=document.getElementById("all_stu");
	if(old!=null){
		document.body.removeChild(old);
	}
	var table=document.createElement("table");
	table.id="all_stu";	
	table.className="table table-bordered";
	table.style.cssText="position: absolute; left: 20%; top: 310px; width: 60%;";
	var thead=document.createElement("thead");
	var tr=document.createElement("tr");
	var th1=document.createElement("th");
	th1.style.cssText="font-family: 微软雅黑;";
	th1.innerText="学生ID";
	var th2=document.createElement("th");
	th2.style.cssText="font-family: 微软雅黑;";
	th2.innerText="详细设置";
	tr.appendChild(th1);
	tr.appendChild(th2);
	thead.appendChild(tr);
	table.appendChild(thead);
	var tbody=document.createElement("tbody");
	table.appendChild(tbody); 
	var text=document.getElementById("a_stu").value;
	var split=text.split(";");
	if(text!=""){
		for(var i=0; i<=split.length-2; i++){
			var split2=split[i].split(" ");
			if(split2[0].indexOf(key)!=-1){
			var tr=document.createElement("tr");
			var td1=document.createElement("td");
			td1.style.cssText="font-family: 微软雅黑;";
			td1.innerText=split2[0];
			var td2=document.createElement("td");
			td2.style.cssText="font-family: 微软雅黑; color: rgb(97, 92, 235);";
			var label1=document.createElement("label");
			label1.className="set";
			label1.onclick=function(){
				set_s(this);
				if(this.innerText=="删除该生选课")
				addAStudent(this.parentNode.previousSibling.innerText);
				else{
					removeAStudent(this.parentNode.previousSibling.innerText);
				}
			};
			label1.innerText="设为选课学生";
			var label2=document.createElement("label");
			label2.className="set";
			label2.onclick=function(){
				set_a(this);
				if(this.innerText=="取消该生助教"){
					 addAnAssistant(this.parentNode.previousSibling.innerText);
				}
				else{
					removeAnAssistant(this.parentNode.previousSibling.innerText);
				}
			};
			label2.innerText="设为课程助教";
			var gang=document.createTextNode(" / ");   //创建文本节点
			td2.appendChild(label1);
			td2.appendChild(gang);
			td2.appendChild(label2);
			tr.appendChild(td1);
			tr.appendChild(td2);
			tbody.appendChild(tr);
			judge(label1, label2, split2[0], course_name);
	     	}
		}
		   document.body.appendChild(table);
		}
}

function isInTime(c_name){
	var text=document.getElementById("a_tc").value;
	var split=text.split(";");
	var t=null;
	for(var i=0; i<=split.length-2; i++){		
		var split2=split[i].split(" ");
		if(split2[0]==c_name)
			t=parseInt(split2[1]);
	}

	var flag=null;
	if(t==1){
		if(getMonth()==9||getMonth()==10||getMonth()==11||getMonth()==12||getMonth()==1||getMonth()==2){
		    flag=true;
		}
		else{
			flag=false;
		}
	}	
	if(t==2){
		if(getMonth()==3||getMonth()==4||getMonth()==5||getMonth()==6||getMonth()==7||getMonth()==8){
			flag=true;
		}
		else
			flag=false;
	}
	return flag;
}

function getMonth(){
	 var date=new Date();
	 var month=date.getMonth()+1;
	 return month;
}