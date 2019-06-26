$(function() {
	$("#check-sub").click(function() {
		var e_name = $("#ename").val();
		var e_datetime = $("#dtime").val();
		var e_status = $("#estatus").val();
		var e_text = $("#remarks").val();
		var json = {
			"e_name" : e_name,
			"e_datetime" : e_datetime,
			"e_status" : e_status,
			"e_text" : e_text
		};
		console.log(json);
		if (e_name == "") {
			alert("请选择您的姓名!");
		} else {
			$.ajax({
				url : "echeck",
				data : json,
				type : "post",
				dataType : "text",
				success : function(date) {
					alert(date);
				},
				error : function() {
					alert("添加失败啦！");
				}
			})
		}
		return false;
	});
});