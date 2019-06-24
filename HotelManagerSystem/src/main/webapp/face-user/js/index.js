function selectHouse(){
	var startTime = $("#date-start").val();
	var endTime = $("#date-end").val();
	if (startTime.length!=0&&endTime.length!=0){
		location.href="booknews.html";
	}else{
		alert("请填写入住时间");
	}
}