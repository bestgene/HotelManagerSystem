function showOrder(id) {
	var cxfs=document.getElementById(id).value
	$.ajax({
		url : "order/showAllOrder",
		type : "POST",
		data : {
			"cxfs":cxfs
		},
		success : function(data) {
			var content = '<table class="layui-table" id="order_table">'
					+ '<thead>' + '<tr>' + 
					'<th>订单编号</th>'
					+ '<th>用户名</th>'
					+ '<th>身份证号码</th>' 
					+ '<th>联系方式</th>' 
					+ '<th>订单金额</th>'

					+ '<th>入住时间</th>' 
					+ '<th>离开时间</th>' 
					+ '<th>住房类型</th>'
					+ '<th>房间号</th>' 
					+ '<th>订单状态</th>' 
					+ '<th>留言</th>'
					+ '<th>其它操作</th>'
					+ '</tr>' + '</thead>';
			for (var i = 0; i < data.length; i++) {
				var order_number=""+data[i].order_number;
				var items = data[i].items;
				var state = "";
				var b1="";
				var b2="";
				if (data[i].flag == 0) {
					state = "失效";
				} else if (data[i].order_state == 1 && data[i].flag == 1) {
					state = "预定";
				} else if (data[i].order_state == 2 && data[i].flag == 1) {
					state = "入住";
				} else if (data[i].order_state == 3 && data[i].flag == 2) {
					state = "退房";
				}
				
				if(state=="预定"){
					b1="入住";
					b2="退款";
				}

				content += '<tr>' + '<th>' + order_number + '</th>'
						+ '<th>' + data[i].userInfo.user_info_name + '</th>'
						+ '<th>' + data[i].userInfo.user_info_idcard + '</th>'
						+ '<th>' + data[i].userInfo.user_info_tel + '</th>'
						+ '<th>' + data[i].order_totalpay + '</th>' 
						+ '<th>' + items[0].item_checkintime + '</th>' 
						+ '<th>' + items[0].item_checkouttime + '</th>' 
						+ '<th>' + items[0].house.houseType.house_type_name + '</th>'
						+ '<th><select>';
						for(var x=0;x<items.length;x++){
							content += '<option>'+items[x].house.house_name+'</option>'
						}
						content += '</select></th>'
						+ '<th>'+ state + '</th>' 
						+ '<th>' + data[i].order_message+ '</th>' ;
						if(state=="预定"){
							var b1='<button onclick=checkin("'+order_number+'")>入住</button>';
							var b2='<button onclick=cancelOrder("'+data[i].order_number+'")>取消</button>';
							content+='<th>'+b1+b2+'</th></tr>';
						}else if(state=="入住"){
							var b3='<button onclick=pay("'+data[i].order_number+'")>结账</button>';
							content+='<th>'+b3+'</th></tr>';
						}
								
			}
			content = content + "</table>";

			$("#order_table").html(content);
		}
	})
};

//入住
function checkin(orderNumber) {
	
	alert(orderNumber);
	$.ajax({
		url:"order/checkin",
		type:"POST",
		data:{
			order_number:orderNumber
		},
		success:function(data){
			alert(data);
			location.reload();
		}
	})	
}

//退款（取消）
function cancelOrder(orderNumber) {
	$.ajax({
		url:"order/qcOrder",
		type:"POST",
		data:{
			order_number:orderNumber
		},
		success:function(data){
			alert(data);
			location.reload();
		}
	})	
}

//结账
function pay(orderNumber) {

	location.href="/HotelManagerSystem/order/payAccounts?order_number="+orderNumber;
}

$(function(){
	showOrder("yd")
})
	