window.onload = function() {	// 在页面加载的时候进行内容的设置
	document.getElementById("date-start").addEventListener("blur",function(){
		validateStart() ;	// 验证数据
	},false) ; 
	document.getElementById("date-end").addEventListener("blur",function(){
		validateEnd() ;	// 验证数据
	},false) ;
	document.getElementById("selectHome").addEventListener("submit",function(e){
		if (validateForm()) {	// 正常提交了
			this.submit() ;
		} else {
			e.preventDefault() ;	// 进行提交的拦截
		}
	},false) ;
}
function validateForm() {
	return validateStart()&&validateEnd() ;
}

function validateStart(){
	var startObj = document.getElementById("date-start") ;
	if (startObj.value == "") {	// 内容如果是空字符串则表示错误
		startObj.className = "failure" ;
		return false ;
	} else {	// 此时没有输入数据
		startObj.className = "success" ;
		return true;
	}
}

function validateEnd(){
	var endObj = document.getElementById("date-end") ;
	if (endObj.value == "") {	// 内容如果是空字符串则表示错误
		endObj.className = "failure" ;
		return false ;
	} else {	// 此时已经正确的输入了数据
		endObj.className = "success" ;
		return true;
	}
}