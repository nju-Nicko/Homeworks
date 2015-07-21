var c_name;

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
				c_name=this.innerText;
	
				colorChange(this);
				removeOthers();		
				if(isInTime(this.innerText)){
				getCourseHomeworkNums(this.innerText);	
				var form0=document.getElementById("form0");
				form0.style.display="";
				var select=document.createElement("select");
				select.className="form-control";
				select.style.cssText="width: 100px;";
				select.id="select";
				select.onchange=function(){
					var table=document.getElementById("stu_tb");
					if(table!=null)
						document.body.removeChild(table);
					getCourseStudentScore(c_name, this.value);
				};
				form0.firstChild.nextSibling.appendChild(select);


				getCourseStudentScore(this.innerText, 1);  //用编号为1的作业的成绩的情况初始化表格
				}
				else{
					var span=document.createElement("span");
					span.innerText="课程尚未开始或已经结束";
					span.id="warn";
					document.body.appendChild(span);
					
					var form0=document.getElementById("form0");
					form0.style.display="none";
				}
				
			};
			li.appendChild(label);
			ul.appendChild(li);
		}
	}
}

function getCourseHomeworkNums(c_name){
	var xhr;
	if(window.XMLHttpRequest)
		xhr=new XMLHttpRequest();
	else
		xhr=new ActiveXObject("Microsoft.XMLHTTP");
	
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4&&xhr.status==200){
		var res=xhr.responseText;
		if(res!=""){
		var split=res.split(";");
		var select=document.getElementById("select");
		for(var i=0; i<=split.length-2; i++){
			var option=document.createElement("option");
			option.innerText=i+1;
			select.appendChild(option);
		   }
		}
		else{
			var select=document.getElementById("select");
			var option=document.createElement("option");
			option.innerText=1;
			select.appendChild(option);
			select.disabled="disabled";
		}
		}
	};
	
	var post="course_name="+c_name;
	post=encodeURI(post);
	post=encodeURI(post);
	
	xhr.open("POST", "/Homeworks/GetCourseHomework?"+post, true);
	xhr.send(null);
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
	
	var select=document.getElementById("select");
	var form0=document.getElementById("form0");
	if(select!=null&&form0!=null){ 
		 form0.firstChild.nextSibling.removeChild(select);
	}
	
	var table=document.getElementById("stu_tb");
	if(table!=null)
		document.body.removeChild(table);
}

function getCourseStudentScore(cou_name, h_id){
	var table=document.createElement("table");
	table.className="table table-striped";
	table.id="stu_tb";
	var thead=document.createElement("thead");
	var tr=document.createElement("tr");
	var th=document.createElement("th");
	th.innerText="学生名";
	var th2=document.createElement("th");
	th2.innerText="作业编号";
	var th3=document.createElement("th");
	th3.innerText="得分";
	var th4=document.createElement("th");
	th4.innerText="点评";
	tr.appendChild(th);
	tr.appendChild(th2);
	tr.appendChild(th3);
	tr.appendChild(th4);
	thead.appendChild(tr);
	var tbody=document.createElement("tbody");
	table.appendChild(thead);
	table.appendChild(tbody);
	document.body.appendChild(table);
	
	var xhr;
	if(window.XMLHttpRequest)
		xhr=new XMLHttpRequest();
	else
		xhr=new ActiveXObject("Microsoft.XMLHTTP");
	
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			 var result=xhr.responseText;
		     if(!result==""){
		    	 	 var split=result.split(";");
		    	 	 for(var i=0; i<=split.length-2; i++){
		    	 		 var split2=split[i].split(" ");
		    	 		 var tr2=document.createElement("tr");
		    	 		 var td=document.createElement("td");
		    	 		 td.innerText=split2[0];
		    	 		 var td2=document.createElement("td");
		    	 		 td2.innerText=h_id;
		    	 		 var td3=document.createElement("td");
		    	 		var i1=document.createElement("input");
		    			i1.type="text";
		    			i1.value=split2[1];
		    			i1.className="tb_val";
		    			td3.appendChild(i1);
		    	 		 var td4=document.createElement("td");
		    	 		var i2=document.createElement("input");
		    			i2.type="text";
		    			i2.className="tb_val";
		    			i2.value=split2[2];
		    			td4.appendChild(i2);
		    			tr2.appendChild(td);
		    			tr2.appendChild(td2);
		    			tr2.appendChild(td3);
		    			tr2.appendChild(td4);
		    			tbody.appendChild(tr2);
		    	 	 }
		     }
		}
	};
	
	var post="course_name="+cou_name+"&homework_id="+h_id;
	post=encodeURI(post);
	post=encodeURI(post);
	
	xhr.open("POST", "/Homeworks/GetCourseStudentScore?"+post, true);
	xhr.send(null);
	
}

function submitData(){
	if(!isComplete()){
		showSubmitError();
		return;
	}
	var result=c_name+" ";
	var table=document.getElementById("stu_tb");
	var length=table.rows.length;
	if(length>1){
		for(var i=1; i<=length-1; i++){
			result=result+table.rows[i].cells[0].innerText+" "+table.rows[i].cells[1].innerText+" "+
			table.rows[i].cells[2].childNodes[0].value+" "+
			table.rows[i].cells[3].childNodes[0].value+";";
		}
	}
	var input=document.getElementById("scores");
	input.value=result;
	
	var form0=document.getElementById("form0");
	form0.submit();
}

function isComplete(){
	var table=document.getElementById("stu_tb");
	var length=table.rows.length;
	if(length>1){
		for(var i=1; i<=length-1; i++){
			if(getCellValue(i, 2)!=""&&getCellValue(i, 3)!=""){
				
			}
			else{
				return false;
			}
	    }
	}
	return true;
}

function getCellValue(i, j){
	var table=document.getElementById("stu_tb");
	if(table.rows.item(i).cells[j].childNodes[0]!=null)
	return table.rows[i].cells[j].childNodes[0].value;
	else
		return "none";
}

function showSubmitError(){
	var se=document.getElementById("submitError");
	var div=document.createElement("div");
	div.id="submitError";
	div.className="alert alert-danger";
	div.innerText="请填写完整信息！";
	div.style.cssText="position: absolute; top: 90px; left: 44%; width: 12%; border-width: 0px; background-color: rgba(0, 0, 0, 0);";
	if(se==null)
	document.body.appendChild(div);
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
	 var date=new Date;
	 var month=date.getMonth()+1;
	 return month;
}