<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="assets/css/style.css">
<link rel="stylesheet" href="layui/css/layui.css">
<script src="assets/js/jquery.min.js"></script>
</head>
<body>
	<div class="rightinfo">
		<table class="tablelist" class="layui-table" lay-data="{width: 800, height:332, url:'/demo/table/user/', page:true, id:'idTest'}" lay-filter="demo">
				<thead>
					<tr>

						<th>入住人姓名</th>
						<th>入住期限</th>
						<th>房间类型</th>
						
						<th>房间号</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody">
				</tbody>
			</table>
		<!-- 分页控件显示的div -->
		<div id="demo7"></div>
	</div>

	<script type="text/javascript" src="layui/layui.js"></script>
	
	<script type="text/javascript">
	$(function () {
		$.ajax({
			url : 'dingdan',
			type : 'post',
			dataType : 'json',
			success : function(obj) {
				var tb = $("#tbody");
				tb.empty();
				var str = "";
			
				$.each(obj, function(i, user) {
					str += "<tr>";
					str += "<td>" + user.uname + "</td>";
					str += "<td>" + user.date + "</td>";
					str += "<td>" + user.roomtype + "</td>";
					debugger;
					str += "<td>" + user.roomid + "</td>";
					str += " <td><button class='layui-btn layui-btn-xs' value='"+user.roomid+"' onclick='update("+user.roomid+")'>退订</button></td></tr>";
				});
				tb.append(str);
			}
			});
	})
	</script>
	<script type="text/javascript">
	
		//s
		function update(value){
			
			
			var con=confirm("确定退订吗！");
			if(con==true){
				$.get("dingdan2",{"roomid":value},
						function(data){
					
					if(data=="1"){
						location.href="dingdan.jsp";
						
					}
			});				
			}		
		}
		
		//e
	
	
	</script>
	
	
	
</body>
</body>
</html>