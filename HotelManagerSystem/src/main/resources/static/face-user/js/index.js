function selectHouse(){
	var startTime = $("#date-start").val();
	var endTime = $("#date-end").val();
	var startArray = startTime.split("/");
	var start = startArray[2]+"-"+startArray[0]+"-"+startArray[1]
	var endArray = endTime.split("/");
	var end = endArray[2]+"-"+endArray[0]+"-"+endArray[1]
	alert(start);
	if (startTime.length!=0&&endTime.length!=0){
		$.ajax({
			url:"/HotelManagerSystem/house/alltype/"+start+"/"+end,
			type:"post",
			success:function(data){
				console.log(data);
			}
		});
	}else{
		alert("请填写入住时间");
	}
}