layui.use(
				[ 'form', 'layedit', 'laydate' ],
				function() {
					var form = layui.form, layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

					// 系统当前时间
					var date = new Date;
					document.getElementById("dtime").value = date
							.toLocaleString();

					// 打卡状态
					var hour = date.getHours();
					var min = date.getMinutes();
					if (hour >= 9 && min > 0) {
						document.getElementById("estatus").value = "迟到";
					} else {
						document.getElementById("estatus").value = "准时";
					}

					// 自定义验证规则
					form.verify({
						title : function(value) {
							if (value.length < 5) {
								return '标题至少得5个字符啊';
							}
						},
						pass : [ /(.+){6,12}$/, '密码必须6到12位' ],
						content : function(value) {
							layedit.sync(editIndex);
						}
					});
				});