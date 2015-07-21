function addOneColumn(){
	var table=document.getElementById("um_tbb");
	var row=document.createElement("tr");
	var d1=document.createElement("td");
	var d2=document.createElement("td");
	var d4=document.createElement("td");
	var d3=document.createElement("td");
	var i1=document.createElement("input");
	i1.type="text";
	i1.className="tb_val";
	d1.appendChild(i1);
	var i2=document.createElement("input");
	i2.type="text";
	i2.className="tb_val";
	d2.appendChild(i2);	
	var select=document.createElement("select");
	select.className="form-control";
	var o1=document.createElement("option");
	o1.innerText="第一学期";
	var o2=document.createElement("option");
	o2.innerText="第二学期";
	select.appendChild(o1);
	select.appendChild(o2);
	d4.appendChild(select);
	var button=document.createElement("button");
	button.className="btn btn-danger btn-md";
	button.innerText="删除这条记录";
	button.onclick=function(){
		var tr=button.parentNode.parentNode;
		var tbody=tr.parentNode;
		tbody.removeChild(tr);	
	};
	d3.appendChild(button);
	row.appendChild(d1);
	row.appendChild(d2);
	row.appendChild(d4);
	row.appendChild(d3);
	table.appendChild(row);
}

function deleteOneColumn(obj){
	var tr=obj.parentNode.parentNode;
	var tbody=tr.parentNode;
	tbody.removeChild(tr);	
}

function init(){
	var input=document.getElementById("a_c");
	var table=document.getElementById("um_tbb");
	if(input!=null){
		var text=input.value;
		var rows=text.split(";");
		for(var i=0; i<=rows.length-2; i++){
			var cells=rows[i].split(" ");
			var cn=cells[0];
			var tn=cells[1];
			var term=parseInt(cells[2]);
			var row=document.createElement("tr");
			var d1=document.createElement("td");
			var d2=document.createElement("td");
			var d4=document.createElement("td");
			var d3=document.createElement("td");
			var i1=document.createElement("input");
			i1.type="text";
			i1.className="tb_val";
			i1.value=cn;
			d1.appendChild(i1);
			var i2=document.createElement("input");
			i2.type="text";
			i2.className="tb_val";
			i2.value=tn;
			d2.appendChild(i2);	
			var select=document.createElement("select");
			select.className="form-control";
			var o1=document.createElement("option");
			o1.innerText="第一学期";
			var o2=document.createElement("option");
			o2.innerText="第二学期";
			select.appendChild(o1);
			select.appendChild(o2);
			d4.appendChild(select);
			if(term==1){
				o1.selected=true;
			}
			else
				o2.selected=true;
			var button=document.createElement("button");
			button.className="btn btn-danger btn-md";
			button.innerText="删除这条记录";
			button.onclick=function(){
				var tr=this.parentNode.parentNode;
				var tbody=tr.parentNode;
				tbody.removeChild(tr);	
			};
			d3.appendChild(button);
			row.appendChild(d1);
			row.appendChild(d2);
			row.appendChild(d4);
			row.appendChild(d3);
			table.appendChild(row);
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
		    result=result+getCellValue(i, 0)+" "+getCellValue(i, 1)+" "+getCellValue(i, 2)+";";
	}
	var res=document.getElementById("res");
	res.value=result;
	res.form.submit();
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

function getCellValue(i, j){
	var table=document.getElementById("um_tb");
	if(table.rows.item(i).cells[j].childNodes[0]!=null)
	return table.rows[i].cells[j].childNodes[0].value;
	else
		return "none";
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