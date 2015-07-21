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
				getHomework(this.innerText);
				}
				else{
					var span=document.createElement("span");
					span.innerText="课程尚未开始或已经结束";
					span.id="warn";
					document.body.appendChild(span);
				}
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
			    var id=split2[0]; var desc=split2[1]; var type=split2[4];
			    var score=split2[5]; var difficulty=split2[6];
			   addHomeworkTable(i, id, desc, type, score, difficulty, c_name);
	     	}
		}
		else{
			var warn=document.createElement("span");
			warn.id="warn";
			warn.innerText="该课程没有布置作业！";
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

function addHomeworkTable(y, id, desc, type, score, difficulty, c_name){
	  var top=y*240+100;
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
	  th4.innerText="助教的点评";
	  var td4=document.createElement("td");
	  getAssisJudgeFile(id, c_name, td4);
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

	  table.appendChild(tbody);
	  
	  document.body.appendChild(table);
}

function getAssisJudgeFile(h_id, course_name, td ){
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
				 var split2=split[1].split(" ");
				 for(var i=0; i<=split2.length-1; i++){
					 if(split2[i]!=""){
						 var a=document.createElement("a");
						 var post="course_name="+course_name+"&student_name="+split2[i]+"&homework_id="+h_id;
						 post=encodeURI(post);
						 post=encodeURI(post);
						 a.href="/Homeworks/DownloadAssisJudgeFile?"+post;
						 a.style.cssText="margin-left: 5px;";
						 a.innerText=split2[i];
						 td.appendChild(a);

						  var form0=document.createElement("form");
						  form0.method="post";
						  form0.action="/Homeworks/TeacherJudge";
	                      form0.style.cssText="display:inline;";
						  var tmp=document.createElement("input");
						  tmp.type="text";
						  tmp.style.display="none";
						  tmp.name="flag";
						  form0.appendChild(tmp);
						  var div=document.createElement("div");
						  div.className="btn-group";
						  var btn=document.createElement("button");
						  btn.type="button";
						  btn.className="btn btn-success";
						  btn.innerText="通过";
						  btn.onclick=function(){
							  var tmp=this.parentNode.previousSibling;
							  tmp.value=course_name+" "+h_id+" "+this.parentNode.parentNode.previousSibling.innerText+" 1";
		
							  this.form.submit();
						  };
						  var btn2=document.createElement("button");
						  btn2.type="button";
						  btn2.className="btn btn-danger";
						  btn2.innerText="否决";
						  btn2.onclick=function(){
							  var tmp=this.parentNode.previousSibling;
							  tmp.value=course_name+" "+h_id+" "+this.parentNode.parentNode.previousSibling.innerText+" 2";
							  this.form.submit();
						  };
						  div.appendChild(btn);
						  div.appendChild(btn2);
						  form0.appendChild(div);
						  td.appendChild(form0);
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