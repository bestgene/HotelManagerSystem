/**
 * 
 */

layui.use('table', function() {
	var table = layui.table;
	// 方法级渲染
	table.render({
		elem : '#LAY_table_user',
		url : 'bill',
		cellMinWidth : 80,
		cols : [ [ {
			field : 'id',
			title : 'ID',
			width : 120,
			sort : true,
			fixed : true
		}, {
			field : 'name',
			title : '用户名',
			width : 120
		}, {
			field : 'date_reside',
			title : '入住时间',
			width : 200,
			sort : true
		}, {
			field : 'date_leave',
			title : '退房时间',
			width : 200
		}, {
			field : 'type',
			title : '房间类型',
			width : 150
		}, {
			field : 'money',
			title : '价格',
			sort : true,
			width : 110
		}, {
			field : 'roomnum',
			title : '房间号',
			width : 110
		} ] ],
		id : 'testReload',
		height : 300,
		width : 1000,
		page : true,
		limit : 5,
		limits : [ 5, 10, 15, 20 ],
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
					name : demoReload
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
		url : 'revenue',
		cellMinWidth : 80,// 全局定义常规单元格的最小宽度，layui 2.2.1 新增
		skin : 'nob',
		size : 'lg',
		width : 500,
		totalRow : true,
		cols : [ [ {
			field : 'type',
			title : '房间类型',
			height : 50,
			totalRowText : '合计',
			style : 'font-size:18px'
		}, {
			field : 'money',
			title : '收入',
			height : 50,
			align : 'right',
			sort : true,
			totalRow : true,
			style : 'font-size:18px'
		} // width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1
		// 新增
		] ],
		done : function(res, curr, count) {
			$('th').css({
				'background-color' : '#009688',
				'color' : '#fff'
			});
		}
	});

});
