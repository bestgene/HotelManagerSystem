$(function() {
	//登陆成功
	$.get("../face_user",function(data){
		//后台传来的null为字符串
		if (data!="null") {			
			$(".fh5co-sub-ddown").remove();
			$(".app2").find("a").remove();
			$(".app1").append("<a href=\"#\" class=\"fh5co-sub-ddown\">您好！"+data+"</a><ul class=\"fh5co-sub-menu\"><li><a href=\"../dingdan.jsp\">我的订单</a></li><li><a href=\"../person.html\">个人资料</a></li> </ul>");
			$(".app2").append("<a style='cursor: pointer'>[退出]</a>");
		}	
	});
//退出
	$(".app2").click(function() {
		var param=$(".app2").find("a").html();
		
		if (param=="[退出]") {
			var r=confirm("确认退出吗！");
			if (r==true) {
				$.get("../leave_user",
						{"getout":1},
						function(data){		
							if (data==1) {
								location.href = "index.html";
							}					
						});
				
				
				
			}
		}
		
	});
	
	
});



