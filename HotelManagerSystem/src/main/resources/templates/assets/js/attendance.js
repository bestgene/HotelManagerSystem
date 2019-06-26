/**
 * 
 */
layui.use('table', function() {
	var table = layui.table;
	// 方法级渲染
	table.render({
		elem : '#LAY_table_user',
		url : 'tol',
		cols : [ [ {
			field : 'e_name',
			title : '员工姓名',
			width : 120,
			fixed : true
		}, {
			field : 'e_datetime',
			title : '签到时间',
			width : 200,
			sort : true
		}, {
			field : 'e_status',
			title : '签到状态',
			width : 120
		}, {
			field : 'e_text',
			title : '备注',
			width : 265
		} ] ],
		id : 'testReload',
		page : true,
		limit : 5,
		limits : [5,10,15,20],
		width : 710,
		done : function(res, curr, count) {
			$('th').css({
				'background-color' : '#009688',
				'color' : '#fff'
			});
		}
	});

	var $ = layui.$, active = {
		reload : function() {
			var demoReload = $('#demoReload').val();
			// 执行重载
			table.reload('testReload', {
				page : {
					curr : 1
				// 重新从第 1 页开始
				},
				where : {
					e_name : demoReload
				}
			});
		}
	};

	$('.demoTable .layui-btn').on('click', function() {
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});

	table.render({
		elem : '#test',
		url : 'sum',
		cellMinWidth : 80,
		skin : 'nob',
		width : 500,
		cols : [ [ {
			field : 'a',
			title : '员工姓名',
			fixed : true
		}, {
			field : 'b',
			title : '签到次数',
			sort : true,
		} , {
			field : 'c',
			title : '迟到次数',
			sort : true,
		} ] ],
		done : function(res, curr, count) {
			$('th').css({
				'background-color' : '#009688',
				'color' : '#fff'
			});
		}
	});

});
