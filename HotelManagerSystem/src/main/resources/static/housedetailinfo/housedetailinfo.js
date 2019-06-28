function queryTypeAndTime() {
	var startTime=$("#startTime").html();
	var endTime=$("#endTime").html();
	//alert(1);
	var house_type_id=$("#house_type option:selected").val(); //获取选中的项
	//alert(house_type_id);
	if (startTime.length!=0&&endTime.length!=0&&house_type_id.length!=0){
		console.info(startTime);
		console.info(endTime);
		console.info(house_type_id);
		
		$.ajax({
			url:"/HotelManagerSystem/house/queryHouse/"+startTime+"/"+endTime+"/"+house_type_id,
			type:"get",
			contentType:"application/json;charset=utf-8",
			success:function(data){
				//alert(data);
				var singleType=data.houseTypes;
				var content="";
					content+='<div>'
					+'<div id="house_stead">'	
					+'<div id="house-show">'
				   		+'<img src="'+singleType.house_type_img+'" id="house_img">'
					+'</div>' 		
				    +'<div id="house-info">'
					+'<table id="house-table">'
					   +'<tr class="tr-info">'
							+'<td class="td-title">类型：</td>'
							+'<td>'+singleType.house_type_name+'</td>'
						+'</tr>'
						+'<tr class="tr-info">'
							+'<td class="td-title">描述：</td>'
							+'<td>'+singleType.house_type_msg+'</td>'
						+'</tr>'
						+'<tr class="tr-info">'
							+'<td class="td-title">价格：</td>'
							+'<td><span class="s-money">'+singleType.house_type_price+'</span></td>'
						+'</tr>'
						+'<tr class="tr-info">'
							+'<td class="td-title">数量：</td>'
							+'<td>'+singleType.num+'</td>'
							+'<td>'
								+'<button type="button" class="btn btn-danger" onclick="order('+singleType.house_type_id+')">跳转到填写订单的页面，可以选择数量</button>'
							+'</td>'
						+'</tr>'
					+'</table>'
				   +'</div>'	  	    	      
				+'</div>'			
			+'</div>';
			$("#houseContext").html(content);
			}
		});
	}else{
		alert("数据不完整");
	}	
};
// 跳转订单填写页面
function order(house_type_id){
	var startTime=$("#startTime").html();
	var endTime=$("#endTime").html();
	if (startTime.length!=0&&endTime.length!=0&&house_type_id.length!=0){
		location.href="../house/allSingleType/"+startTime+"/"+endTime+"/"+house_type_id;
	}else{
		alert("订单信息不足");
	}
}
