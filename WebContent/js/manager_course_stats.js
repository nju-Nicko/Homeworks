function init(){
	var text=document.getElementById("a_c").value;
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
	   warn2.innerText="选择一位学生进行查看";
	   
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
				 var result=xhr.responseText;
				 var split=result.split(";");
				if(split[0]==""){   //没有课程
					var warn=document.getElementById("warn2");
					if(warn!=null)
						warn.innerText="该课程没有学生！";
				       warn.style.left="17%";
				    }
				else{					
					 var split2=split[0].split(" ");
					var ul=document.getElementById("his_course");
					for(var i=0; i<=split2.length-2; i++){
						var li=document.createElement("li");
						li.className="list-group-item";
						var label=document.createElement("label");
						label.className="course_name";
						label.innerText=split2[i];

						label.onclick=function(){
						    colorChange2(this);
						    removeOthers2();
	                        getHomeworkSubmitState(stu_name, this.innerText);
						};
						li.appendChild(label);
						ul.appendChild(li);
					}
				}
			}
		};
		
		var post="course_name="+stu_name;
		post=encodeURI(post);
		post=encodeURI(post);
		
		xhr.open("post", "/Homeworks/GetCourseStuAssis?"+post, true);
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
		
	}
	
	function getHomeworkSubmitState(cou_name, stu_name){
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
					    var id=split2[2];
					    var desc=split2[3];
					    var score=split2[4];
					    var remark=split2[5];
					    addHomeworkRemarkTable(i, id, desc, remark, score);
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
		
		var post="course_name="+cou_name+"&student_name="+stu_name;
		post=encodeURI(post);
		post=encodeURI(post);
		
		xhr.open("POST", "/Homeworks/GetStudentHomeworkRemark?"+post, true);
		xhr.send(null);
	}

	function addHomeworkRemarkTable(y, id, desc, remark, score){
		  var top=y*170+110;
		  var table=document.createElement("table");
		  var tbody=document.createElement("tbody");
		  table.className="table table-bordered";
		  table.style.cssText="position: absolute; left: 25%; top:"+top+"px; width: 50%; font-family: 微软雅黑;";
		  
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
		  th2.innerText="作业描述";
		  var td2=document.createElement("td");
		  td2.innerText=desc;
		  tr2.appendChild(th2);
		  tr2.appendChild(td2);
		  tbody.appendChild(tr2);
		  
		  var tr3=document.createElement("tr");
		  var th3=document.createElement("th");
		  th3.innerText="作业成绩";
		  var td3=document.createElement("td");
		  td3.innerText=score;
		  tr3.appendChild(th3);
		  tr3.appendChild(td3);
		  tbody.appendChild(tr3);
		  
		  var tr4=document.createElement("tr");
		  var th4=document.createElement("th");
		  th4.innerText="助教点评";
		  var td4=document.createElement("td");
		  td4.innerText=remark;
		  tr4.appendChild(th4);
		  tr4.appendChild(td4);
		  tbody.appendChild(tr4);
		  
	    table.appendChild(tbody);
		  
		  document.body.appendChild(table); 
	}