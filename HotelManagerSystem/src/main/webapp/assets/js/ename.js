/*获取员工姓名*/
$(function() {
	$.ajax({
		url : "ename",
		type : "post",
		dataType : "json",
		success : function(data) {
//			alert(data);
			var ename = document.getElementById("ename");
			for (var i = 0; i < data.length; i++) {
				var option = document.createElement("option");
				option.innerHTML = data[i];
				ename.append(option);
			}
		},
		error : function() {

		}
	});
})