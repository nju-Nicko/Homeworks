function init(){
	var text=document.getElementById("a_tc").value;
	var split=text.split(";");
	if(text==""){   //没有课程
		var warn=document.getElementById("warn");
		if(warn!=null)
			warn.innerText="您还没有课程";
	}
	else{
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
				var i=document.getElementById("hm_cou_name");
				i.value=this.innerText;
				reset();
				removeOthers();
				if(isInTime(this.innerText)){
					var table=document.getElementById("homework_detail");
					table.style.display="";
				    getOldHomework(this.innerText);
				}
				else{
					var span=document.createElement("span");
					span.innerText="课程尚未开始或已经结束";
					span.id="warn";
					document.body.appendChild(span);
					var table=document.getElementById("homework_detail");
					table.style.display="none";
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

function reset(){
	var o2=document.getElementById("assi_info");
	var o3=document.getElementById("assi_sub_ddl");
	var o4=document.getElementById("assi_judge_ddl");
	var o5=document.getElementById("docx");
	var o6=document.getElementById("s1");
	var o7=document.getElementById("d1");
	o2.value="";
	o3.value="";
	o4.value="";
	o5.selected=true;
	o6.selected=true;
	o7.selected=true;
}

function removeOthers(){
	var warn=document.getElementById("warn");
	if(warn!=null)
		document.body.removeChild(warn);
	var sub_assi_table=document.getElementById("homework_detail");
	if(sub_assi_table!=null)
		sub_assi_table.style.display="";
	var tables=document.getElementsByClassName("table table-bordered");
	var len=tables.length-1;
	var arr=new Array();
	for(var i=0; i<=len; i++){
		arr[i]=tables[i];
	}
	for(var i=0; i<=len; i++){
		if(arr[i].style.top!="100px"){
			document.body.removeChild(arr[i]);
		}
	}
}

function submitData(){
	var form0=document.getElementById("form0");
	var text=document.getElementById("assi_detail");
	text.value=getNewHomeworkInfo();
	form0.submit();
}

function getNewHomeworkInfo(){
	var o1=document.getElementById("assi_number");
	var o2=document.getElementById("assi_info");
	var o3=document.getElementById("assi_sub_ddl");
	var o4=document.getElementById("assi_judge_ddl");
	var o5=document.getElementById("type");
	var o6=document.getElementById("score");
	var o7=document.getElementById("difficulty");
	var i=document.getElementById("hm_cou_name");
	var result= i.value+" "+o1.value+" "+o2.value+" "+o3.value+" "+o4.value+" "+o5.value+" "+ o6.value+" "+o7.value;
	return result;
}

function getOldHomework(c_name){
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
			var number=document.getElementById("assi_number");
			number.value=split.length;
			for(var i=0; i<=split.length-2; i++){
				    var split2=split[i].split(" ");
				    var id=split2[0]; var desc=split2[1]; var sub_ddl=split2[2]; var judge_ddl=split2[3]; var type=split2[4];
				    var score=split2[5]; var difficulty=split2[6];
				   addOldHomeworkTable(i, id, desc, sub_ddl, judge_ddl, type, score, difficulty, c_name);
		     	}
			}
			else{
				var number=document.getElementById("assi_number");
				number.value="1";
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
	  var top=(y+1)*270+100;
	  var table=document.createElement("table");
	  table.className="table table-bordered";
	  table.style.cssText="position: absolute; left: 20%; top:"+top+"px; width: 50%; font-family: 微软雅黑;";
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
	  table.appendChild(tr);
	  
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
	  table.appendChild(tr2);
	  
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
	  table.appendChild(tr3);
	  
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
	  th8.innerText="操作";
	  var td8=document.createElement("td");
	  var form=document.createElement("form");
	  var post="course_name="+c_name+"&homework_id="+id;
	  post=encodeURI(post);
	  post=encodeURI(post);
	  form.method="post";
	  form.enctype="multipart/form-data";
	  form.action="/Homeworks/UploadDemo?"+post;
	  var input8=document.createElement("input");
	  input8.type="file";
	  input8.style.cssText="width: 70%;";
	  input8.name="demo";
	  var button=document.createElement("button");
	  button.className="btn btn-warning";
	  button.innerText="上传样例";
	  button.style.marginLeft="3px";
	  button.onclick=function(){
		  this.form.submit();
	  };
	  form.appendChild(input8);
	  form.appendChild(button);
	  td8.appendChild(form);
	  tr4.appendChild(th7);
	  tr4.appendChild(td7);
	  tr4.appendChild(th8);
	  tr4.appendChild(td8);
	  table.appendChild(tr4);
	  
	  document.body.appendChild(table);
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