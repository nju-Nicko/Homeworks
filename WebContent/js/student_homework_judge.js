function init(){
	var text=document.getElementById("a_ac").value;
	var split=text.split(";");
	if(text==""){   //没有课程

	         }
	else{  //有课程
		var ul=document.getElementById("my_course");
		for(var i=0; i<=split.length-2; i++){
			var li=document.createElement("li");
			li.className="list-group-item";
			var label=document.createElement("label");
			label.className="course_name";
			label.innerText=split[i];
			label.onclick=function(){
				colorChange(this);
				removeOthers();
			    getHomework(this.innerText);
			};
			li.appendChild(label);
			ul.appendChild(li);
	  }
	}
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

function removeOthers(){
	var warn=document.getElementById("warn");
	if(warn!=null)
		document.body.removeChild(warn);
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

function getHomework(c_name){
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
			    var id=split2[0]; var desc=split2[1]; var judge_ddl=split2[3]; var type=split2[4];
			    var score=split2[5]; var difficulty=split2[6];
			   addHomeworkTable(i, id, desc, judge_ddl, type, score, difficulty, c_name);
	     	}
		}
		else{
			var warn=document.createElement("span");
			warn.id="warn";
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

function addHomeworkTable(y, id, desc, ddl1, type, score, difficulty, c_name){
	  var top=y*300+100;
	  var table=document.createElement("table");
	  var tbody=document.createElement("tbody");
	  table.className="table table-bordered";
	  table.style.cssText="position: absolute; left: 20%; top:"+top+"px; width: 50%; font-family: 微软雅黑;";
	  
	  var tr=document.createElement("tr");
	  var th=document.createElement("th");
	  th.innerText="作业编号";
	  var td=document.createElement("td");
	  td.innerText=id;
	  tr.appendChild(th);
	  tr.appendChild(td);
	  tbody.appendChild(tr);
	  
	  var tr2=document.createElement("tr");
	  var th2=document.createElement("th");
	  th2.innerText="批改期限";
	  var td2=document.createElement("td");
	  td2.innerText=ddl1;
	  tr2.appendChild(th2);
	  tr2.appendChild(td2);
	  tbody.appendChild(tr2);
	  
	  var tr3=document.createElement("tr");
	  var th3=document.createElement("th");
	  th3.innerText="作业描述";
	  var td3=document.createElement("td");
	  td3.innerText=desc;
	  tr3.appendChild(th3);
	  tr3.appendChild(td3);
	  tbody.appendChild(tr3);
	  
	  var tr4=document.createElement("tr");
	  var th4=document.createElement("th");
	  th4.innerText="同学们的提交";
	  var td4=document.createElement("td");
	  getCourseStudent(id, c_name, td4);
	  tr4.appendChild(th4);
	  tr4.appendChild(td4);
	  tbody.appendChild(tr4);
	  
	  var tr5=document.createElement("tr");
	  var th5=document.createElement("th");
	  th5.innerText="作业总分";
	  var td5=document.createElement("td");
	  td5.innerText=score;
	  tr5.appendChild(th5);
	  tr5.appendChild(td5);
	  tbody.appendChild(tr5);
	  
	  var tr6=document.createElement("tr");
	  var th6=document.createElement("th");
	  th6.innerText="作业难度";
	  var td6=document.createElement("td");
	  td6.innerText=difficulty;
	  tr6.appendChild(th6);
	  tr6.appendChild(td6);
	  tbody.appendChild(tr6);
	  
	  var tr7=document.createElement("tr");
	  var th7=document.createElement("th");
	  th7.innerText="提交点评";
	  var td7=document.createElement("td");
	  var split=ddl1.split("-");
	  var split2=split[0].split("/");
	  var split3=split[1].split(":");
	  var year=parseInt(split2[2]);
	  var month=parseInt(split2[0]);
	  var date=parseInt(split2[1]);
	  var hour=parseInt(split3[0]);
	  var minute=parseInt(split3[1]);
	  var myDate = new Date();
	  if((myDate.getFullYear()>year)||(myDate.getFullYear()==year&&(myDate.getMonth()+1)>month)||
			  (myDate.getFullYear()==year&&(myDate.getMonth()+1)==month&&myDate.getDate()>date)||
			     ((myDate.getFullYear()==year&&(myDate.getMonth()+1)==month&&myDate.getDate()==date&&myDate.getHours()>hour)||
					  (myDate.getFullYear()==year&&(myDate.getMonth()+1)==month&&myDate.getDate()==date&&myDate.getHours()==hour&&myDate.getMinutes()>minute))){
		  td7.innerText="Deadline is over！";
		  td7.style.color="gray";
	  }
	  else{
		  var form=document.createElement("form");
		  form.method="post";
		  form.action="/Homeworks/UploadJudgeFile";
		  form.enctype="multipart/form-data";
		  var input=document.createElement("input");
		  input.type="file";
		  input.style.cssText="display: inline;";
		  input.name="judge";
		  var input2=document.createElement("input");
		  input2.type="text";
		  input2.style.cssText="display: none;";
		  input2.name="infos";
		  input2.value=id+" "+type+" "+c_name;
		  form.appendChild(input2);
		  form.appendChild(input);
		  var button=document.createElement("button");
		  button.className="btn btn-warning";
	      button.innerText="上传点评";
	      button.style.marginLeft="10px";
	      button.onclick=function(){
	    	  this.form.submit();
	      };
		  form.appendChild(button);
		  td7.appendChild(form);
	  }
	  tr7.appendChild(th7);
	  tr7.appendChild(td7);
	  tbody.appendChild(tr7);
	  
	  table.appendChild(tbody);
	  
	  document.body.appendChild(table);
}

function getCourseStudent(h_id, course_name, td){
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
					 if(split2[i]!=""){
						 var a=document.createElement("a");
						 var post="course_name="+course_name+"&student_name="+split2[i]+"&homework_id="+h_id;
						 post=encodeURI(post);
						 post=encodeURI(post);
						 a.href="/Homeworks/Download?"+post;
						 a.style.cssText="margin-left: 5px;";
						 a.innerText=split2[i];
						 td.appendChild(a);
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