function selectHouse(){
	var startTime0 = $("#date-start").val();
	var endTime0 = $("#date-end").val();
	var startArray = startTime0.split("/");
	var start = startArray[2]+"-"+startArray[0]+"-"+startArray[1]
	var endArray = endTime0.split("/");
	var end = endArray[2]+"-"+endArray[0]+"-"+endArray[1]
	alert(start);
	if (startTime0.length!=0&&endTime0.length!=0){
		$.ajax({
			url:"/HotelManagerSystem/house/alltype",
			type:"get",
			data:{
				startTime:start,
				endTime:end,
			}
		});
	}else{
		alert("请填写入住时间");
	}
}