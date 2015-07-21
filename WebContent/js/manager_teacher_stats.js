function init(){
	var text=document.getElementById("a_t").value;
	var split=text.split(";");
	if(text!=""){
		var ul=document.getElementById("students");
		for(var i=0; i<=split.length-2; i++){
			var split2=split[i].split(" ");
			var li=document.createElement("li");
			li.className="list-group-item";
			var label=document.createElement("label");
			label.className="student_name";
			label.innerText=split2[0];
			label.onclick=function(){
				colorChange(this);
				removeOthers();	
				getHisData(this.innerText);
			
			};
			li.appendChild(label);
			ul.appendChild(li);
		}
	}
}

function colorChange(obj){
	var list_items=document.getElementById("students").childNodes;
	for(var i=0; i<=list_items.length-1; i++){
		if(list_items[i].firstChild.innerText!=obj.innerText){
			list_items[i].firstChild.style.color="rgb(132, 132, 255)";
		}
		else{
			list_items[i].firstChild.style.color="purple";
		}
	}
}

function removeOthers(){
	var warn=document.getElementById("warn");
	if(warn!=null)
		document.body.removeChild(warn);
	
	var ul=document.getElementById("his_course");
	if(ul!=null)
		document.body.removeChild(ul);
	
	var warn2=document.getElementById("warn2");
	if(warn2!=null){
		document.body.removeChild(warn2);
	}
	
	var tables=document.getElementsByClassName("table table-bordered");
	var len=tables.length-1;
	var arr=new Array();
	for(var i=0; i<=len; i++){
		arr[i]=tables[i];
	}
	for(var i=0; i<=len; i++){
			document.body.removeChild(arr[i]);
	}
	
}

function getHisData(stu_name){
	   var ul=document.createElement("ul");
	   ul.className="list-group";
	   ul.id="his_course";
	   ul.style.cssText="font-family: 微软雅黑;";
	   
	   document.body.appendChild(ul);
	   
	   var warn2=document.createElement("span");
	   warn2.id="warn2";
	   warn2.innerText="选择一门课程进行查看";
	   
	   document.body.appendChild(warn2);
	   
	   getStuCourse(stu_name);
	}

function getStuCourse(stu_name){
	
	var xhr;
	if(window.XMLHttpRequest)
		xhr=new XMLHttpRequest();
	else
		xhr=new ActiveXObject("Microsoft.XMLHTTP");
	
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			var text=xhr.responseText;
			if(text==""){   //没有课程
				var warn=document.getElementById("warn");
				if(warn!=null)
					warn.innerText="该教师没有课程！";
			}
			else{
				var split=text.split(";");
				var ul=document.getElementById("his_course");
				for(var i=0; i<=split.length-2; i++){
					var split2=split[i].split(" ");
					var li=document.createElement("li");
					li.className="list-group-item";
					var label=document.createElement("label");
					label.className="course_name";
					label.innerText=split2[0];
					label.onclick=function(){
						colorChange2(this);
						removeOthers2();
						    getOldHomework(this.innerText);
					};
					li.appendChild(label);
					ul.appendChild(li);
				}
			}
		}
	};
	
	var post="stu_name="+stu_name;
	post=encodeURI(post);
	post=encodeURI(post);
	
	xhr.open("post", "/Homeworks/GetTeacherCourses?"+post, true);
	xhr.send(null);
	
}

function colorChange2(obj){
	var list_items=document.getElementById("his_course").childNodes;
	for(var i=0; i<=list_items.length-1; i++){
		if(list_items[i].firstChild.innerText!=obj.innerText){
			list_items[i].firstChild.style.color="rgb(132, 132, 255)";
		}
		else{
			list_items[i].firstChild.style.color="purple";
		}
	}
}

function removeOthers2(){
	
	var warn2=document.getElementById("warn2");
	if(warn2!=null){
		document.body.removeChild(warn2);
	}
	
	var tables=document.getElementsByClassName("table table-bordered");
	var len=tables.length-1;
	var arr=new Array();
	for(var i=0; i<=len; i++){
		arr[i]=tables[i];
	}
	for(var i=0; i<=len; i++){
			document.body.removeChild(arr[i]);
	}
	
}function getOldHomework(c_name){
	var xhr;
	if(window.XMLHttpRequest)
		xhr=new XMLHttpRequest();
	else
		xhr=new ActiveXObject("Microsoft.XMLHTTP");
	
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			var res=xhr.responseText;
			if(res!=""){
			var split=res.split(";");
			for(var i=0; i<=split.length-2; i++){
				    var split2=split[i].split(" ");
				    var id=split2[0]; var desc=split2[1]; var sub_ddl=split2[2]; var judge_ddl=split2[3]; var type=split2[4];
				    var score=split2[5]; var difficulty=split2[6];
				   addOldHomeworkTable(i, id, desc, sub_ddl, judge_ddl, type, score, difficulty, c_name);
		     	}
			}
			else{
				var warn=document.createElement("span");
				warn.id="warn2";
				warn.innerText="该课程暂未发布作业！";
				document.body.appendChild(warn);
			}
		}
	};
	
	var post="course_name="+c_name;
	post=encodeURI(post);
	post=encodeURI(post);
	
	xhr.open("POST", "/Homeworks/GetCourseHomework?"+post, true);
	xhr.send(null);
}

function addOldHomeworkTable(y, id, desc, ddl1, ddl2, type, score, difficulty, c_name){
	  var top=(y)*270+110;
	  var table=document.createElement("table");
	  table.className="table table-bordered";
	  table.style.cssText="position: absolute; left: 25%; top:"+top+"px; width: 50%; font-family: 微软雅黑;";
	  var tbody=document.createElement("tbody");
	  var tr=document.createElement("tr");
	  var th=document.createElement("th");
	  th.innerText="作业编号";
	  var td=document.createElement("td");
	  var input=document.createElement("input");
	  input.type="text";
	  input.className="form-control";
	  input.value=id;
	  td.appendChild(input);
	  var th2=document.createElement("th");
	  th2.innerText="作业描述";
	  var td2=document.createElement("td");
	  var input2=document.createElement("input");
	  input2.type="text";
	  input2.className="form-control";
	  input2.value=desc;
	  td2.appendChild(input2);
	  tr.appendChild(th);
	  tr.appendChild(td);
	  tr.appendChild(th2);
	  tr.appendChild(td2);
	  tbody.appendChild(tr);
	  
	  var tr2=document.createElement("tr");
	  var th3=document.createElement("th");
	  th3.innerText="提交期限";
	  var td3=document.createElement("td");
	  var input3=document.createElement("input");
	  input3.type="text";
	  input3.className="form-control";
	  input3.value=ddl1;
	  td3.appendChild(input3);
	  var th4=document.createElement("th");
	  th4.innerText="批改期限";
	  var td4=document.createElement("td");
	  var input4=document.createElement("input");
	  input4.type="text";
	  input4.className="form-control";
	  input4.value=ddl2;
	  td4.appendChild(input4);
	  tr2.appendChild(th3);
	  tr2.appendChild(td3);
	  tr2.appendChild(th4);
	  tr2.appendChild(td4);
	  tbody.appendChild(tr2);
	  
	  var tr3=document.createElement("tr");
	  var th5=document.createElement("th");
	  th5.innerText="文件格式";
	  var td5=document.createElement("td");
	  var input5=document.createElement("input");
	  input5.type="text";
	  input5.className="form-control";
	  input5.value=type;
	  td5.appendChild(input5);
	  var th6=document.createElement("th");
	  th6.innerText="分数";
	  var td6=document.createElement("td");
	  var input6=document.createElement("input");
	  input6.type="text";
	  input6.className="form-control";
	  input6.value=score;
	  td6.appendChild(input6);
	  tr3.appendChild(th5);
	  tr3.appendChild(td5);
	  tr3.appendChild(th6);
	  tr3.appendChild(td6);
	  tbody.appendChild(tr3);
	  
	  var tr4=document.createElement("tr");
	  var th7=document.createElement("th");
	  th7.innerText="难度";
	  var td7=document.createElement("td");
	  var input7=document.createElement("input");
	  input7.type="text";
	  input7.className="form-control";
	  input7.value=difficulty;
	  td7.appendChild(input7);
	  var th8=document.createElement("th");
	  var td8=document.createElement("td");
	  tr4.appendChild(th7);
	  tr4.appendChild(td7);
	  tr4.appendChild(th8);
	  tr4.appendChild(td8);
	  tbody.appendChild(tr4);
	  
	  table.appendChild(tbody);
	  
	  document.body.appendChild(table);
}