function queryTypeAndTime() {
	var startTime=$("#starttime").val();
	var endTime=$("#endtime").val();
	var type=$("#house_type").val();
	$.ajax({
		url:"house/alltype/startTime/endTime/type",
		type:"get",
		contentType:"application/json;charset=utf-8",
		success:function(data){
			var allhouses=data.houses;
			var content="";
			for(var i=0;i<allhouses.length;i++){
				content+='<div id="house-show">'
							+'<img src="'+allhouses[i].house_type_img+'">'
							+'</div>'
							+'<div id="house-info">'
										+'<table id="house-table">'
										+'<tr class="tr-info">'
										+'<td class="td-title">类型：</td>'
										+'<td>'+allhouses[i].house_type_name+'</td>'
									+'</tr>'
									+'<tr class="tr-info">'
										+'<td class="td-title">描述：</td>'
										+'<td>'+allhouses[i].house_type_desciption+'</td>'
									+'</tr>'
									+'<tr class="tr-info">'
										+'<td class="td-title">价格：</td>'
										+'<td><span class="s-money">'+allhouses[i].house_type_price+'</span></td>'
									+'</tr>'
			
									+'<tr class="tr-info">'
										+'<td class="td-title">数量：</td>'
										+'<td>'+allhouses[i].num+'</td>'
										+'<td>'
											+'<button type="button" class="btn btn-danger" onclick="order()">跳转到填写订单的页面，可以选择数量</button>'
										+'</td>'
									+'</tr>'
								+'</table>'
							+'</div>'
			}
			$("#house_stead").html(content);
		}
	});
};
//跳转订单填写页面
function order(house_type_id){
	var startTime=$("#startTime").html();
	var endTime=$("#endTime").html();
	alert(house_type_id);
	if (startTime.length!=0&&endTime.length!=0&&house_type_id.length!=0){
		location.href="../house/allSingleType/"+startTime+"/"+endTime+"/"+house_type_id;
	}else{
		alert("订单信息不足");
	}
}
