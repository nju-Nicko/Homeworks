function addOneColumn(){
	var table=document.getElementById("um_tbb");
	var row=document.createElement("tr");
	var d1=document.createElement("td");
	var d2=document.createElement("td");
	var d3=document.createElement("td");
	var d4=document.createElement("td");
	var d5=document.createElement("td");
	var i1=document.createElement("input");
	i1.type="text";
	i1.className="tb_val";
	d1.appendChild(i1);
	var i2=document.createElement("input");
	i2.type="text";
	i2.className="tb_val";
	d2.appendChild(i2);	
	var s1=document.createElement("select");
	s1.className="form-control";
	s1.onchange=function(){
		change(s1);
	};
    var o1=document.createElement("option");
    o1.innerText="学生";
    s1.appendChild(o1);
    var o2=document.createElement("option");
    o2.innerText="教师";
    s1.appendChild(o2);
	d3.appendChild(s1);
	var button=document.createElement("button");
	button.className="btn btn-danger btn-md";
	button.innerText="删除这条记录";
	button.onclick=function(){
		var tr=button.parentNode.parentNode;
		var tbody=tr.parentNode;
		tbody.removeChild(tr);	
	};
	d5.appendChild(button);
	row.appendChild(d1);
	row.appendChild(d2);
	row.appendChild(d3);
	row.appendChild(d4);
	row.appendChild(d5);
	table.appendChild(row);
}

function deleteOneColumn(obj){
	var tr=obj.parentNode.parentNode;
	var tbody=tr.parentNode;
	tbody.removeChild(tr);	
}

function change(obj){
    var td=obj.parentNode;
    var td2=td.nextSibling;
    if(obj.value=="教师"){
    	var s=document.createElement("select");
    	var o1=document.createElement("option");
    	o1.innerText="授课教师";
    	var o2=document.createElement("option");
    	o2.innerText="教学负责人";
    	var o3=document.createElement("option");
    	o3.innerText="授课&负责";
    	s.appendChild(o1);
    	s.appendChild(o2);
    	s.appendChild(o3);
    	s.className="form-control";
    	td2.appendChild(s);
    }
    else{
    	while(td2.hasChildNodes()){
			td2.removeChild(td2.firstChild);
		}
    }
}

function submitData(){
	if(!isComplete()){
		showSubmitError();
		return;
	}
	var result="";
	var table=document.getElementById("um_tb");
	var rows=table.rows.length;    	
	if(rows>1)
    	for(var i=1; i<=rows-1; i++){
		    result=result+getCellValue(i, 0)+" "+getCellValue(i, 1)+" "+getCellValue(i, 2)+" "+getCellValue(i, 3)+";";
	}
	var res=document.getElementById("res");
	res.value=result;
	res.form.submit();
}

function getCellValue(i, j){
	var table=document.getElementById("um_tb");
	if(table.rows(i).cells[j].childNodes[0]!=null)
	return table.rows[i].cells[j].childNodes[0].value;
	else
		return "none";
}

function isComplete(){
	var table=document.getElementById("um_tb");
	var length=table.rows.length;
	if(length>1){
		for(var i=1; i<=length-1; i++){
			if(getCellValue(i, 0)!=""&&getCellValue(i, 1)!=""){
				
			}
			else{
				return false;
			}
	    }
	}
	return true;
}

function showSubmitError(){
	var se1=document.getElementById("submitSuccess");
	if(se1!=null){
		document.body.removeChild(se1);
	}
	var se=document.getElementById("submitError");
	var div=document.createElement("div");
	div.id="submitError";
	div.className="alert alert-danger";
	div.innerText="请填写完整信息！";
	div.style.cssText="position: absolute; top: 50px; left: 18%; width: 12%; border-width: 0px; background-color: rgba(0, 0, 0, 0);";
	if(se==null)
	document.body.appendChild(div);
}

function showSubmitSuccess(){
	var se1=document.getElementById("submitError");
	if(se1!=null){
		document.body.removeChild(se1);
	}
	var se=document.getElementById("submitSuccess");
	var div=document.createElement("div");
	div.id="submitSucess";
	div.className="alert alert-success";
	div.innerText="用户信息上传成功";
	div.style.cssText="position: absolute; top: 50px; left: 18%; width: 12%;";
	if(se==null)
	document.body.appendChild(div);
}

function init(){
	var input=document.getElementById("a_u");			
	var table=document.getElementById("um_tbb");
	if(input!=null){
		var text=input.value;
		var rows=text.split(";");
		for(var i=0; i<=rows.length-2; i++){
			var cells=rows[i].split(" ");
			var name=cells[0];
			var pass=cells[1];
			var type=cells[2];
			var row=document.createElement("tr");
			var d1=document.createElement("td");
			var d2=document.createElement("td");
			var d3=document.createElement("td");
			var d4=document.createElement("td");
			var d5=document.createElement("td");
			var i1=document.createElement("input");
			i1.type="text";
			i1.className="tb_val";
			i1.value=name;
			d1.appendChild(i1);
			var i2=document.createElement("input");
			i2.type="text";
			i2.className="tb_val";
			i2.value=pass;
			d2.appendChild(i2);	
			var s1=document.createElement("select");
			s1.className="form-control";
		    var o1=document.createElement("option");
		    o1.innerText="学生";
		    s1.appendChild(o1);
		    var o2=document.createElement("option");
		    o2.innerText="教师";	
		    s1.appendChild(o2);
			s1.onchange=function(){
				change(this);
			};
		    switch(type){
		    case "s": o1.selected=true; break;
		    case "t": o2.selected=true; break;
		    case "m": o2.selected=true; break;
		    case "mt": o2.selected=true; break;
		    }
			d3.appendChild(s1);
			if(type=="t"||type=="m"||type=="mt"){
				var s=document.createElement("select");
		    	var o1=document.createElement("option");
		    	o1.innerText="授课教师";
		    	var o2=document.createElement("option");
		    	o2.innerText="教学负责人";
		    	var o3=document.createElement("option");
		    	o3.innerText="授课&负责";
		    	s.appendChild(o1);
		    	s.appendChild(o2);
		    	s.appendChild(o3);
		    	s.className="form-control";
		    	d4.appendChild(s);
		    	switch(type){
		    	    case "t": o1.selected=true; break;
				    case "m": o2.selected=true; break;
				    case "mt": o3.selected=true; break;
		    	}
			}
			var button=document.createElement("button");
			button.className="btn btn-danger btn-md";
			button.innerText="删除这条记录";
			button.onclick=function(){
				var tr=this.parentNode.parentNode;
				var tbody=tr.parentNode;
				tbody.removeChild(tr);	
			};
			d5.appendChild(button);
			row.appendChild(d1);
			row.appendChild(d2);
			row.appendChild(d3);
			row.appendChild(d4);
			row.appendChild(d5);
			table.appendChild(row);
		}
	}
}
