function submit() {
	$.ajax({
		url:"order/createOrder",
		type:"POST",
		data:{
			"userInfo.user_info_name":$("#username").val(),
			"userInfo.user_info_idcard":$("idcard1").val(),
			"userInfo.user_info_tel":$("#usertel1").val(),
			"reserve_checkintime":$("#livetime1").val(),
			"reserve_checkouttime":$("#leavetime1").val(),
			"reserve_arrivetime":$("#checkinTime").val(),
			"reserve_isauto":$("#reserve_isauto").val(),
			"reserve_canceltime":$("#reserve_canceltime").val(),
			"reserve_message":$("#reserve_message").val(),
			"houseMap.drj":$("#drj").val(),
			"houseMap.drj":$("#srj").val(),
		}
		
	})
}