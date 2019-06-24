function queryHouse(){
	$.ajax({
		url:"",
		type:post,
		data:json.stringify({
			//预定时间
			//退房时间
			//房间类型
		}),
	    contentType:"application/json;charset=utf-8",
	    success:function(data){	    	
	    	var table=$("<table></table>");
	    	var th= $("<th></th>");
	    	
	    }
	});
}